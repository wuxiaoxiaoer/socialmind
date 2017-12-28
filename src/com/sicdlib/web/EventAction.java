package com.sicdlib.web;

import Jama.Matrix;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.NLPUtil.Word2VecUtil.OtherUtil.Segment;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Test.Word2Vec;
import com.sicdlib.util.SNAUtil.SNAUtil;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/event/")
public class EventAction {

    @Autowired
    private ObjectEntityService objectEntityService;
    @Autowired
    private EventEntityService eventEntityService;
    @Autowired
    private ArticleEntityService articleEntityService;
    @Autowired
    private ArticleCommentEntityService articleCommentEntityService;
    @Autowired
    private ArticleSimilarityService articleSimilarityService;
    @Autowired
    private CommentEntityService commentEntityService;
    @Autowired
    private KeywordRelatedDegreeService keywordRelatedDegreeService;
    @Autowired
    private DataDictionaryEntityService dataDictionaryEntityService;
    @Autowired
    private KeywordEntityService keywordEntityService;
    @Autowired
    private AuthorEntityService authorEntityService;
    @Autowired
    private WebsiteEntityService websiteEntityService;
    @Autowired
    private IndicatorValueEntityService indicatorValueEntityService;

    //查找事件列表
    @RequestMapping("all")
    public String findAll(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        List<ObjectEntity> objectList = objectEntityService.findObject();
        List<EventEntity> eventList = eventEntityService.findEvent();
        List event = new ArrayList();
        int end = 30;
        for(int i = 0 ; i < objectList.size() ; i++) {
            for(int j = 0 ; j < eventList.size() ; j++) {
                if (objectList.get(i).getObjectId().equals(eventList.get(j).getObjectId())){
                    if (eventList.get(j).getIntroduction().length()<end){
                        end = eventList.get(j).getIntroduction().length();
                    }
                    eventList.get(j).setIntroduction(eventList.get(j).getIntroduction().substring(0,end));
                    DynamicObject dy = new DynamicObject();
                    dy.setObject(objectList.get(i));
                    dy.setEvent(eventList.get(j));
                    event.add(dy);
                }
            }
        }
        mode.addAttribute("objectList", objectList);
        mode.addAttribute("eventList", eventList);
        mode.addAttribute("event", event);
        return "/WEB-INF/foreground/event";
    }

    //根据事件ID查找详细信息
    @RequestMapping("eventInfo")
    public String findEventInfo(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {

        String objectId = req.getParameter("objectId");
        //查找事件下所有文章列表
        List<ArticleEntity> artileList = articleEntityService.findArticleList(objectId);
        //查找事件下文章的相似度
        findEventSimilar(objectId,artileList);
        //查找事件关键词
        List<Map> keywords = findKeywords(objectId);
        //查找事件关键词之间的相关度
        List<Map> keywordRelatedList = keywordRelated(objectId,keywords);
        //事件关键词的关联度
        List<Map> keywordRelated = new ArrayList<>();
        for (int i =1 ;i<keywordRelatedList.size();i++){
            Map keyrelatedMap = new HashMap();
            keyrelatedMap.put("source",keywordRelatedList.get(0).get("name").toString());
            keyrelatedMap.put("target",keywordRelatedList.get(i).get("name").toString());
            keyrelatedMap.put("value",keywordRelatedList.get(i).get("value"));
            keywordRelated.add(keyrelatedMap);
        }

        //文章传播
        List<String> categoryName = websiteEntityService.findWebsitesByEvent(objectId);
        Map<String, List> nodesAndEdges = getNodeAndEdgeAttributes(objectId);
        List<Object> SNAList = getSNA(nodesAndEdges.get("nodes"),nodesAndEdges.get("edges"));

        //查找事件下媒体来源信息
        List<Map> mediaSource = websiteEntityService.findMediaSource(objectId);

        //各媒体的观点
//      List<ArticleCommentEntity> articleCommentList = articleCommentEntityService.findArticleComment(hotAuthor.get(0).getAuthorId());
        List mediaList = new ArrayList();
        for(int i = 0 ; i < allMedias().size() ; i++) {
            mediaList.add(allMedias().get(i).getAttributeValue());
            Map map = new HashMap();
            for (int j = 0; j< mediaSource.size();j++){
                if (!mediaSource.get(j).get("name").equals(allMedias().get(i).getAttributeValue())){
                    map.put("name",allMedias().get(i).getAttributeValue());
                    map.put("value",0);
                    mediaSource.add(map);
                }
            }

        }

        List hotOpinionList = new ArrayList();
        List opinionSource = new ArrayList();
        for (int m = 0; m< allMedias().size();m++){
            String media = allMedias().get(m).getAttributeValue();
            List hotOpinion = articleEntityService.finfHotOpinion(media,objectId);
            Map map = new HashMap();
            map.put("name",media);
            map.put("value",hotOpinion.size());
            opinionSource.add(map);
            hotOpinionList.add(hotOpinion);
        }

        //统计事件下所有时间下的网站上数据最多的信息


        mode.addAttribute("objectId", objectId);
        mode.addAttribute("hotOpinionList", JSON.toJSON(hotOpinionList));
        mode.addAttribute("keywordRelateds", JSON.toJSON(keywordRelated));
        mode.addAttribute("mediaList", JSON.toJSON(mediaList));
        mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
        mode.addAttribute("opinionSource", JSON.toJSON(opinionSource));
        mode.addAttribute("areaSource", JSON.toJSON(areaSource(objectId)));
        mode.addAttribute("periodList", JSON.toJSON(findPeriod(objectId)));
        mode.addAttribute("webs",JSON.toJSON(getWebsiteName()));
        mode.addAttribute("websiteStatistic",JSON.toJSON(websiteStatistic(objectId)));
        mode.addAttribute("artileList", artileList);
        mode.addAttribute("keywords", JSON.toJSON(keywords).toString());
        mode.addAttribute("keywordList", JSON.toJSON(keywordList(keywords)).toString());
        mode.addAttribute("event", event(objectId));
        mode.addAttribute("category", JSON.toJSONString(categoryName).replace("'", "\\\'"));
        mode.addAttribute("nodesAndEdges", JSON.toJSONString(nodesAndEdges).replace("'", "\\\'"));
        mode.addAttribute("SNAList", JSON.toJSONString(SNAList, SerializerFeature.DisableCircularReferenceDetect));//各个阈值下的SNA参数列表
        //mode.addAttribute("listkey", JSON.toJSON(listkey).toString());
        //mode.addAttribute("articleCommentList", articleCommentList);
        mode.addAttribute("keywordRelatedList", JSON.toJSON(keywordRelatedList).toString());
//        mode.addAttribute("reliablity", reliablity);
        return "/WEB-INF/foreground/eventInfo";
    }

    @Test
    public void test(){
        double[][] a = {{0,3,2},{5,6,4},{7,8,9}};
        Matrix matrix = new Matrix(a);
        matrix.print(4,0);
    }
    //获取事件的全部信息
    public List event(String objectId){
        List<ObjectEntity> objectInfo = objectEntityService.findObjectInfo(objectId);
        List<EventEntity> eventInfo = eventEntityService.findEventInfo(objectId);
        List event = new ArrayList();
        for(int i = 0 ; i < objectInfo.size() ; i++) {
            for(int j = 0 ; j < eventInfo.size() ; j++) {
                if (objectInfo.get(i).getObjectId().equals(eventInfo.get(j).getObjectId())){
                    DynamicObject dy = new DynamicObject();
                    dy.setObject(objectInfo.get(i));
                    dy.setEvent(eventInfo.get(j));
                    event.add(dy);
                }
            }
        }
        return event;
    }

    //点击文章标题获取文章信息
    @RequestMapping("articleInfo")
    public String findArticleInfo(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        String articleId = req.getParameter("articleId");
        //点击文章标题
        List<ArticleEntity> articleInfo = articleEntityService.findArticleInfo(articleId);
        mode.addAttribute("articleInfo", articleInfo);
        return "/WEB-INF/foreground/infodetail";
    }
    //统计事件的国内国外信息
    public List areaSource(String objectId){
        return indicatorValueEntityService.getObjectArea(objectId);
    }

    //查找数据字典中的所有媒体名称
    public List<DataDictionaryEntity> allMedias(){
        return dataDictionaryEntityService.findAllMedias();
    }

    //查事件的事件段
    public List findPeriod(String objectId){
        return articleEntityService.findPeriod(objectId);
    }



    /*//统计事件下热门信息
    public List hotInformation(String objectId){
        return articleEntityService.findHotInformation(objectId);
    }
    //统计事件下热门作者
    public List<AuthorEntity> hotAuthor(String objectId){
        return authorEntityService.findHotAuthor(objectId);
    }*/
    //统计所有网站
    public List<WebsiteEntity> webs(){
        return websiteEntityService.findWebsite();
    }

    //获取网站名称
    public List getWebsiteName(){
        List webs_store = new ArrayList();
        for(int i = 0 ; i < webs().size() ; i++) {
            webs_store.add(webs().get(i).getWebsiteName());
        }
        return webs_store;
    }

    //统计事件的网站
    public List websiteStatistic(String objectId){
        List websiteStatistic = new ArrayList();
        for(int i = 0 ; i < webs().size() ; i++) {
            String websiteID = webs().get(i).getWebsiteId();
            String websiteName = webs().get(i).getWebsiteName();
            List<Map> websiteList = articleEntityService.findWebsites(objectId,websiteID,websiteName);
            Map map = new HashMap();
            map.put("name",websiteName);
            map.put("type","line");
            map.put("stack","发布文章的数量");
            map.put("data",websiteList);
            websiteStatistic.add(map);
        }
        return websiteStatistic;
    }

    //计算事件中文章之间的相似度
    public List<ArticleSimilarEntity> findEventSimilar(String objectId,List<ArticleEntity> artileList){

        List<ArticleSimilarEntity> findEventSimilar = articleSimilarityService.findEventSimilar(objectId);
        if(findEventSimilar==null||findEventSimilar.size()==0){
            Word2Vec vec = new Word2Vec();
            try {
                vec.loadGoogleModel("E:\\wiki_chinese_word2vec(Google).model");
//			vec.loadJavaModel("data/wikichinese.model");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i=0;i<artileList.size();i++){
                for (int j=i+1;j>i &&j<artileList.size();j++){
                    ArticleSimilarEntity similarEntity = new ArticleSimilarEntity();
                    ArticleEntity articleEntity1 = new ArticleEntity();
                    articleEntity1.setArticleId(artileList.get(i).getArticleId());
                    ArticleEntity articleEntity2 = new ArticleEntity();
                    articleEntity2.setArticleId(artileList.get(j).getArticleId());

                    String content1 = artileList.get(i).getContent();
                    String content2 = artileList.get(j).getContent();
                    //分词，获取词语列表
                    List<String> wordList1 = Segment.getWords(content1);
                    List<String> wordList2 = Segment.getWords(content2);
                    System.out.println("相似度："+vec.sentenceSimilarity(wordList1, wordList2));
                    similarEntity.setArticleSimilarId(UUIDUtil.getUUID());
                    similarEntity.setArticleEntityOne(articleEntity1);
                    similarEntity.setArticleEntityTwo(articleEntity2);
                    similarEntity.setSimilarDegree(vec.sentenceSimilarity(wordList1, wordList2));
                    articleSimilarityService.addEventArticleSimi(similarEntity);
                }
            }
        }
        return findEventSimilar;
    }

    //查找事件是否已经抽取过关键词
    public List<Map> findKeywords(String objectId){
        boolean isHaveKeywords = keywordEntityService.findHaveKeywords(objectId);//查找关键词表中是否已经进行该事件关键词的抽取
        if (!isHaveKeywords){
            articleEntityService.findKeywords(objectId);//查找文章表中每篇文章的关键词
        }
        List<Map> list = new ArrayList<>();
        List<Map> keywords = keywordEntityService.findKeywords(objectId);//在关键词表中查找已经抽取好的事件关键词

        for (int i = 0;i<keywords.size();i++){
            Map map = new HashMap();
            map.put("name",keywords.get(i).get("name"));
            map.put("value",keywords.get(i).get("value"));
            /*map.put("keywordId",keywords.get(i).get("keywordId"));
            map.put("draggable",keywords.get(i).get("draggable"));
            if (i==0){
                map.put("category","核心");
            }else {
                map.put("category", "非核心");
            }*/
            list.add(map);
        }
        return list;
    }

    //前台生成关键词列表
    public List keywordList(List<Map> keywords){
        List keywordList = new ArrayList();
        for(int m = 0 ; m < keywords.size() ; m++) {
            Map map = new HashMap();
            if (m==0){
                map.put("category",0);
            }else {
                map.put("category",1);
            }
            map.put("name",keywords.get(m).get("name"));
//            map.put("value",keywords.get(m).get("value"));
            map.put("draggable",true);
            keywordList.add(map);
        }
        return keywordList;
    }

    //文章传播生成节点和边
    public Map<String, List> getNodeAndEdgeAttributes(String objectId) {
        int sliceNum = 10; //将相似度分为10份
        double sliceSize = 1.0 / sliceNum;
//        double simi = 0.35;
        //找见该事件下所有相似文章的相似度
        List<ArticleSimilarEntity> articleSimiList = articleSimilarityService.findEventSimilar(objectId);

        //存放在不同相似度情况下每个节点的大小
        Map<String, Vector<Integer>> articleSimiNumMap = new ConcurrentHashMap<>();
        Map<String, String> articleTitleMap = new ConcurrentHashMap<>();
        Map<String, String> articleWebsite = new ConcurrentHashMap<>();
        Map<String, String> tableNameMap = new ConcurrentHashMap<>();

        //??????????
        Vector<Vector<Map<String, Object>>> edgeList = new Vector<>();
        for(int i = 0; i <= sliceNum; i++) {
            AtomicReference<Vector<Map<String, Object>>> vector = new AtomicReference<>(new Vector<>());
            edgeList.add(vector.get());
        }

        for (ArticleSimilarEntity articleSimi:articleSimiList) {
            String articleAID = articleSimi.getArticleEntityOne().getArticleId();
            String articleBID = articleSimi.getArticleEntityTwo().getArticleId();
            //初始化节点相关节点的个数
            if (!articleSimiNumMap.containsKey(articleAID)) {
                //获取文章标题
                String articleATitle = articleEntityService.findTitle(articleAID,objectId);
                //初始化每个节点在不同相似度下节点的大小,初始化为0
                Vector<Integer> nodeSizeList = new Vector<>();
                for (int i = 0; i <= sliceNum; i++) {
                    nodeSizeList.add(0);
                }
                articleSimiNumMap.put(articleAID, nodeSizeList);
                articleTitleMap.put(articleAID, articleATitle);
                //来源网站
                String sourceWebsite = websiteEntityService.findWebsitesByArticle(objectId,articleAID);
                articleWebsite.put(articleAID, sourceWebsite);
                //文章来源表
                tableNameMap.put(articleAID, "article");
            }
            if(!articleSimiNumMap.containsKey(articleBID)) {
                //获取文章标题
                String articleBTitle = articleEntityService.findTitle(articleBID,objectId);
                //初始化每个节点在不同相似度下节点的大小,初始化为0
                Vector<Integer> nodeSizeList = new Vector<>();
                for (int i = 0; i <= sliceNum; i++) {
                    nodeSizeList.add(0);
                }
                articleSimiNumMap.put(articleBID, nodeSizeList);
                //文章标题
                articleTitleMap.put(articleBID, articleBTitle);
                //来源网站
                String sourceWebsite = websiteEntityService.findWebsitesByArticle(objectId,articleBID);
                articleWebsite.put(articleBID, sourceWebsite);
                //文章来源表
                tableNameMap.put(articleBID, "article");
            }

            //时间早的作为source，晚的为target
            //如果相似度高于simi，连接两个节点
            String edgeSource = "";
            String edgeTarget = "";
            if(articleTitleMap.get(articleAID).compareTo(articleTitleMap.get(articleBID)) > 0) {
                edgeSource = articleBID;
                edgeTarget = articleAID;
            } else {
                edgeSource = articleAID;
                edgeTarget = articleBID;
            }

            //对相应的相似度阈值+1
            Vector<Integer> simiAList = articleSimiNumMap.get(articleAID);
            Vector<Integer> simiBList = articleSimiNumMap.get(articleBID);

            //循环，加入相应的阈值数组
            for (int i = 0; i <= (int)(articleSimi.getSimilarDegree() / sliceSize); i++) {
                AtomicInteger countA = new AtomicInteger(simiAList.get(i));
                AtomicInteger countB = new AtomicInteger(simiBList.get(i));

                simiAList.set(i, countA.incrementAndGet());
                simiBList.set(i, countB.incrementAndGet());

                Map<String, Object> edgeMap = new HashMap<>();
                edgeMap.put("source", edgeSource);
                edgeMap.put("target", edgeTarget);

                Vector<Map<String, Object>> vector = edgeList.get(i);
                vector.add(edgeMap);
                edgeList.set(i, vector);
            }
            articleSimiNumMap.put(articleAID, simiAList);
            articleSimiNumMap.put(articleBID, simiBList);
        }

        List<Map<String, Object>> nodeList = new ArrayList<>();
        for (Map.Entry<String, String> entry : articleTitleMap.entrySet()) {
            Map<String, Object> element = new HashMap<>();

            element.put("title", entry.getValue().replaceAll("\\\\s*|\\t|\\r|\\n", ""));
            element.put("num", articleSimiNumMap.get(entry.getKey()));
            element.put("website", articleWebsite.get(entry.getKey()));
            element.put("id", entry.getKey());
            element.put("table", tableNameMap.get(entry.getKey()));

            nodeList.add(element);
        }

        Map<String, List> result = new HashMap<>();
        result.put("nodes", nodeList);
        result.put("edges", edgeList);
        return result;
    }


    public List<Map<String, Object>> getSNA(List<Map<String, Object>> nodes, List<List<Map<String, Object>>> edgesList){

        List<Map<String, Object>> SNAList=new ArrayList<Map<String, Object>>();

        for(int i=0;i<edgesList.size();i++){

            Map<String, Object> SNA= new HashMap<String, Object>();
            SNA= SNAUtil.SNA(nodes,edgesList.get(i));

            SNAList.add(SNA);
            System.out.println("###############"+SNAList.get(i).get("nodeSNAList"));
        }
        System.out.println("$$$$$$$$$$$$$$"+SNAList.get(0).get("nodeSNAList"));
        return SNAList;
    }

    //关键词的关联度
    //计算关键词的关联度：采用互信息的计算方法
    /**
     * 先计算出第一个关键词出现的文章数Nx，第二个关键词出现的文章数Ny,两个关键词都出现的文章数Nxy,所有的文章数N
     * 根据公式 corr(x,y)=Math.log10(N/Nx)*Math.log10(N/Ny)*Nxy/(Nx+Ny-Nxy)
     */
    public List<Map> keywordRelated(String objectId,List<Map> keywords){

        String coreKeyword = " ";
        //事件是否已经生成关联度
        List<KeywordRelatedDegreeEntity> keywordRelatedList = keywordRelatedDegreeService.findKeywordRelated(objectId);

        if (keywordRelatedList==null||keywordRelatedList.size()==0){
            //否，生成关联度，再进行查询
            List<KeywordRelatedDegreeEntity> list = new ArrayList<>();
            //查找某事件下所有文章的总数
            double allArticleNum = Double.parseDouble(articleEntityService.findArticleSumNum(objectId));
            //查找含有第一个关键词的文章的数量
            coreKeyword = keywords.get(0).get("name").toString();
            String coreKeywordId =  keywords.get(0).get("keywordId").toString();
            double coreKeywordArticleNum = Double.parseDouble(articleEntityService.findOneKeywordArticleNum(objectId,coreKeyword));
            //查找含有第二个关键词的文章数量
            for (int i = 1;i<keywords.size();i++){
                String secondKeyword = keywords.get(i).get("name").toString();
                String secondKeywordId = keywords.get(i).get("keywordId").toString();
                double secongKeywordArticleNum = Double.parseDouble(articleEntityService.findOneKeywordArticleNum(objectId,secondKeyword));
                double twoKeywordArticleNum = Double.parseDouble(articleEntityService.findTwoKeywordArticleNum(objectId,coreKeyword,secondKeyword));
                double correlated = Math.log10(allArticleNum/coreKeywordArticleNum)*Math.log10(allArticleNum/secongKeywordArticleNum)*allArticleNum/(coreKeywordArticleNum+secongKeywordArticleNum-twoKeywordArticleNum);
                KeywordRelatedDegreeEntity keywordRelatedDegreeEntity = new KeywordRelatedDegreeEntity();
                KeywordEntity keywordEntityOne = new KeywordEntity();
                keywordEntityOne.setKeywordId(coreKeywordId);
                keywordEntityOne.setKeyword(coreKeyword);
                KeywordEntity keywordEntityTwo = new KeywordEntity();
                keywordEntityTwo.setKeywordId(secondKeywordId);
                keywordEntityTwo.setKeyword(secondKeyword);
                keywordRelatedDegreeEntity.setKeywordDegreeId(UUIDUtil.getUUID());
                keywordRelatedDegreeEntity.setKeywordEntityOne(keywordEntityOne);
                keywordRelatedDegreeEntity.setKeywordEntityTwo(keywordEntityTwo);
                keywordRelatedDegreeEntity.setRelatedDegree(correlated);
                //向关联度表中添加数据，调用方法
                int insert = keywordRelatedDegreeService.insertKeywordRelated(keywordRelatedDegreeEntity);
                System.out.println("000000000000000000insert:::"+insert);

            }
        }
        //
        List<KeywordRelatedDegreeEntity> keywordRelated = keywordRelatedDegreeService.findKeywordRelated(objectId);
        List<Map> listkey = new ArrayList();

        for(int a = 0 ; a < keywordRelated.size() ; a++) {
//            List list1 = new ArrayList();
            if (a==0){
                Map map = new HashMap();
                map.put("name",keywords.get(0).get("name"));
                map.put("value",keywordRelated.get(0).getRelatedDegree());
//                map.put("isnode","true");
                map.put("draggable","true");
//                map.put("x",300);
//                map.put("y",300);
                listkey.add(map);
            }else {
                double r = 200;
//                        Double.parseDouble(keywordRelated.get(a).getRelatedDegree().toString())*100*3;
                double x = Math.random()*400+100;
                double z = Math.sqrt(r*r-(x-300)*(x-300));
                double[] y = {300-z,300+z};
                int index=(int)(Math.random()*y.length);
//                        Math.random()*400+100;
//                        Math.random()*(2*z)+300-z;
//                list1.add(x);
//                list1.add(y);
//                list1.add(keywordRelated.get(a).getKeywordEntityOne().getKeyword());
                Map map = new HashMap();
                map.put("name",keywordRelated.get(a).getKeywordEntityOne().getKeyword());
                map.put("value",keywordRelated.get(a).getRelatedDegree());
//                map.put("isnode","true");
                map.put("draggable","true");
//                map.put("x",x);
//                map.put("y",y[index]);
                listkey.add(map);
            }
//            listkey.add(list1);
        }
        return listkey;
    }

    //统计事件的可信度
    public void reliablity(){
        //        List<IndicatorValueEntity> reliablity = indicatorValueEntityService.getObjectReliablity(objectId);
    }


}
