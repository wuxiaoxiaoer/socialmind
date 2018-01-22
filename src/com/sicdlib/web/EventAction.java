package com.sicdlib.web;

import Jama.Matrix;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.GraphUtil.Graph;
import com.sicdlib.util.NLPUtil.HanLPUtil.HanLPUtil;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        for (int i =0 ;i<keywordRelatedList.size();i++){
            Map keyrelatedMap = new HashMap();
            keyrelatedMap.put("source",keywords.get(0).get("name").toString());

            keyrelatedMap.put("target",keywordRelatedList.get(i).get("name").toString());
            keyrelatedMap.put("value",keywordRelatedList.get(i).get("value"));
            keywordRelated.add(keyrelatedMap);
        }
//        System.out.println(keywordRelated.toString());

        //文章传播
        List<String> categoryName = websiteEntityService.findWebsitesByEvent(objectId);
        Map<String, List> nodesAndEdges = getNodeAndEdgeAttributes(objectId);
        List<Object> SNAList = getSNA(nodesAndEdges.get("nodes"),nodesAndEdges.get("edges"));

        //网站传播
        /*Map<String, List> webNodesAndEdges = getWebsNodeAndEdge(objectId);
        List<Object> SNALists = getSNA(webNodesAndEdges.get("nodes"),webNodesAndEdges.get("edges"));

*/
//        Map<String, List> webNodesAndEdges = test(objectId);
//        List<Object> SNALists = getSNA(webNodesAndEdges.get("nodes"),webNodesAndEdges.get("edges"));

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
        List time = findPeriod(objectId);
        List timeList = new ArrayList();
        List<Map> mapList = new ArrayList<>();
        for (int i=0;i<time.size();i++){
            //先统计每天的数量
            int count  = articleEntityService.findArticleNum(objectId,time.get(i).toString());
            Map map = new HashMap();
            map.put("time",time.get(i).toString());
            map.put("count",count);
            mapList.add(map);
            timeList.add(count);
        }
        List maxArticleList = new ArrayList();
        int average = (Integer.parseInt(Collections.max(timeList).toString())+Integer.parseInt(Collections.min(timeList).toString()))/2;
        for (int j=0;j<mapList.size();j++){
            if(Integer.parseInt(mapList.get(j).get("count").toString())>=average){
                //按照最高的文章数的时间 统计文章信息
                String maxtime = mapList.get(j).get("time").toString();
                maxArticleList.add(articleEntityService.findMaxArticle(objectId,maxtime));
            }
        }

        List<Map> data = new ArrayList<>();
        Map datamap = new HashMap();
        datamap.put("name","askdjfaklsdj");
        datamap.put("size",16800);
        data.add(datamap);
//        mode.addAttribute("treeGraph", JSON.toJSON(treeGraph(objectId)));

        mode.addAttribute("objectId", objectId);
        mode.addAttribute("data", JSON.toJSON(data));
        mode.addAttribute("time", JSON.toJSON(time));
        mode.addAttribute("webAndTimeCount", JSON.toJSON(timeList));
        mode.addAttribute("topkeywords", findTopKeywords(objectId));
        mode.addAttribute("firstWeb", categoryName.get(0).toString());
        mode.addAttribute("allWebsiteArticle", allWebsiteArticle(objectId));
        mode.addAttribute("maxArticleList", maxArticleList);
        mode.addAttribute("transwebsList", JSON.toJSON(transwebList(objectId)));

        mode.addAttribute("timePeriodWeb", JSON.toJSON(timePeriodWeb(objectId)));
        mode.addAttribute("hotOpinionList", JSON.toJSON(hotOpinionList));
        mode.addAttribute("keywordRelateds", JSON.toJSON(keywordRelated));
        mode.addAttribute("mediaList", JSON.toJSON(mediaList));
        mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
        mode.addAttribute("opinionSource", JSON.toJSON(opinionSource));
//        mode.addAttribute("areaSource", JSON.toJSON(areaSource(objectId)));
        mode.addAttribute("periodList", JSON.toJSON(findPeriod(objectId)));
        mode.addAttribute("webs",JSON.toJSON(getWebsiteName()));
        mode.addAttribute("websiteStatistic",JSON.toJSON(websiteStatistic(objectId)));
        mode.addAttribute("artileSize",artileList.size() );
        mode.addAttribute("transferweb",JSON.toJSON(transferWebs(objectId)));
        mode.addAttribute("keywordList", JSON.toJSON(keywordList(keywords)).toString());
        mode.addAttribute("event", event(objectId));
        mode.addAttribute("category", JSON.toJSONString(categoryName).replace("'", "\\\'"));
//        mode.addAttribute("webnodesAndEdges", JSON.toJSONString(webNodesAndEdges).replace("'", "\\\'"));
//        mode.addAttribute("webSNAList", JSON.toJSONString(SNALists, SerializerFeature.DisableCircularReferenceDetect));//各个阈值下的SNA参数列表
        mode.addAttribute("nodesAndEdges", JSON.toJSONString(nodesAndEdges).replace("'", "\\\'"));
        mode.addAttribute("SNAList", JSON.toJSONString(SNAList, SerializerFeature.DisableCircularReferenceDetect));//各个阈值下的SNA参数列表
        //mode.addAttribute("listkey", JSON.toJSON(listkey).toString());
        //mode.addAttribute("articleCommentList", articleCommentList);
        mode.addAttribute("keywordRelatedList", JSON.toJSON(keywordRelatedList).toString());
//        mode.addAttribute("reliablity", reliablity); //可信度
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
    /*public List areaSource(String objectId){
        return indicatorValueEntityService.getObjectArea(objectId);
    }*/


    //查找数据字典中的所有媒体名称
    public List<DataDictionaryEntity> allMedias(){
        return dataDictionaryEntityService.findAllMedias();
    }

    //查事件的事件段
    public List findPeriod(String objectId){
        return articleEntityService.findPeriod(objectId);
    }


    //查事件各网站的数据量信息
    public List allWebsiteArticle(String objectId){
        List newlist = new ArrayList();
        for (int i = 0;i<webs().size();i++){
            String websiteId = webs().get(i).getWebsiteId();
            List<Map> mapList = articleEntityService.findWebsiteArticle(objectId,websiteId);
            for (int j = 0;j<mapList.size();j++){
                newlist.add(mapList.get(j).get("count"));
            }
        }
        return newlist;
    }

    //查事件相关网站的JSON格式

    public List<Map> transwebList(String objectId){
        List<Map> mapList = new ArrayList<>();
        List<String> list = websiteEntityService.findWebsitesByEvent(objectId);
        for (int i=0;i<list.size();i++){

            Map map = new HashMap();
            map.put("name",list.get(i));
            map.put("x",200+i*100);
            if (i%2==0){
                map.put("y",300+i*50);
            }else {
                map.put("y",300-i*50);
            }

//            if (i==0){
//                map.put("x",200);
//                map.put("y",300);
//            }else {
//                map.put("x",(int)(Math.random()*9+3)*100);
//                map.put("y",(int)(Math.random()*4+1)*100);
//            }

            mapList.add(map);
        }
        return mapList;
    }

    //查找网站信息的开始时间
    public List<Map> timeFirstList(String objectId){
        //1.找来源网站
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);
        //2.找每个网站下最早文章的时间
        List<Map> timeFirstList = new ArrayList<>();
        for (int k = 0;k<webs.size();k++){
            String webName = webs.get(k).toString();
            Map map = new HashMap();
            map.put(k, articleEntityService.findFirstTimeByWeb(webName,objectId));
//            map.put("value",webName);
            timeFirstList.add(map);
        }
        return timeFirstList;
    }

    //查找来源网站
    public List<String> webs(String objectId){
        return  websiteEntityService.findWebsitesByEvent(objectId);
    }
    //查找网站信息的结束时间
    public List<Map> timeEndList(String objectId){
        //1.找来源网站
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);
        //2.找每个网站下最早文章的时间
        List<Map> timeEndList = new ArrayList<>();
        for (int k = 0;k<webs.size();k++){
            String webName = webs.get(k).toString();
            Map map = new HashMap();
            map.put(k, articleEntityService.findEndTimeByWeb(webName,objectId));
//            map.put("value",webName);
            timeEndList.add(map);
        }
        return timeEndList;
    }

    //查找网站信息的持续时间
    public List timePeriodWeb(String objectId){
        List timePeriodList = new ArrayList<>();
        List<Map> timeFirstList = timeFirstList(objectId);
        List<Map> timeEndList = timeEndList(objectId);
        for (int i=0;i<timeFirstList.size();i++){
            String period = subTimeDay(timeEndList.get(i).get(i).toString(),timeFirstList.get(i).get(i).toString());
            timePeriodList.add(period);
        }
        return timePeriodList;
    }

    /*
     关于网络之间的传播：1.先找这个事件下，文章相似度大于均值的文章来源网站
     2. 形成延时矩阵的行和列
     3. 时间延时按照 每个网站的最早文章的的时间 算时间差
     */
    public List<Map> transferWebs(String objectId){
        List webs = websiteEntityService.findWebsitesByEvent(objectId);
        List<Map> timeFirstList = timeFirstList(objectId);
        List<Map> result = new ArrayList<>();
        //计算时间差
        //构造矩阵，因为存放的是时间，所以将矩阵定为String类型,矩阵的行高为网站的数量
        double[][] timeMatrix = new double[webs.size()][webs.size()];
        for(int m =0;m<timeMatrix.length;m++){
            for (int n = 0;n<timeMatrix[m].length;n++){
                if(m ==n){
                    timeMatrix[m][n] =0;
                }else {
                    String time1 = timeFirstList.get(m).get(m).toString();
                    String time2 = timeFirstList.get(n).get(n).toString();
                    timeMatrix[m][n] =subTime(time1, time2);
                    if (timeMatrix[m][n]<0){
                        timeMatrix[m][n]=0;
                    }
                    if (timeMatrix[m][n]>0){
                        Map map = new HashMap();
                        map.put("source",webs.get(m));
                        map.put("target",webs.get(n));
                        result.add(map);
                    }

                }
            }
        }

        return result;
    }



    //根据矩阵生成树图
   /* public List<Map> treeGraph(String objectId){
        //先遍历第一行，因为第一行代表最早的时间，如果有大于0的，分别记录大于0的行和列
        double[][] timeMatrix = transferWebs(objectId);
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);
        List<Map> treeMap = new ArrayList<>();

        Map map = new HashMap();
        map.put("name",webs.get(0).toString());
        String children = "";
        if (webs.size()!=0){
            for (int j=1;j<timeMatrix[0].length;j++){
                double path = timeMatrix[0][j];
                recursion(timeMatrix,j,path);
                children = webs.get(j).toString();
                map.put("children",recursionPath(timeMatrix,j,children,objectId));
            }
        }
        System.out.println("children"+children);
        treeMap.add(map);
        return treeMap;
    }*/
    //递归求最小时间
   /* public double recursion(double[][] timeMatrix,int j,double path){
        for (int i=j+1;i<timeMatrix[j].length;i++){
            path+= timeMatrix[j][i];
            if (i==timeMatrix.length-1){
                break;
            }
            return recursion(timeMatrix,i,path);
        }
        return path;
    }*/

    //递归求最小路径
    public String recursionPath(double[][] timeMatrix,int j,String path,String objectId){
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);
        for (int i=j+1;i<timeMatrix[j].length;i++){
            path+= webs.get(j).toString();
            return recursionPath(timeMatrix,j,path,objectId);
        }
        return path;
    }

    //时间差--具体到天
    public String subTimeDay(String time1,String time2){
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin= null;
        Date end = null;
        try {
            begin = dfs.parse(time1);
            end = dfs.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒

        long day1=between/(24*3600);
        long hour1=between%(24*3600)/3600;
        long minute1=between%3600/60;
        long second1=between%60/60;
//        String time = day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒";
        String time = day1+"";
        return time;
    }
    //时间差
    public double subTime(String time1,String time2){
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin= null;
        Date end = null;
        try {
            begin = dfs.parse(time1);
            end = dfs.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
        return between;
/*
        long day1=between/(24*3600);
        long hour1=between%(24*3600)/3600;
        long minute1=between%3600/60;
        long second1=between%60/60;
        System.out.println(""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒");*/
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
            List<Map> websiteList = articleEntityService.findWebsites(objectId,websiteID);
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

    //查找top5关键词
    public List<String> findTopKeywords(String objectId){
        boolean isHaveKeywords = keywordEntityService.findHaveKeywords(objectId);
        List list = new ArrayList();
        if (isHaveKeywords){
            List<Map> keywordsList = keywordEntityService.findKeywords(objectId);
            for (int i=0;i<5;i++){
                list.add(keywordsList.get(i).get("name"));
            }
        }else {
            findKeywords(objectId);
        }
        return list;
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
            map.put("keywordId",keywords.get(i).get("keywordId"));
            /*map.put("draggable",keywords.get(i).get("draggable"));
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

    //网站之间传播生成节点和边
    /*public Map<String, List> getWebsNodeAndEdge(String objectId) {

        String edgeSource = "";
        String edgeTarget = "";
        double[][] timeMatrix = transferWebs(objectId);

        int count = 0;
        List<Map> timeFirstList = timeFirstList(objectId);
        for (int i=0;i<timeMatrix.length;i++){
            for (int j=0;j<timeMatrix[i].length;j++){
                if (timeMatrix[i][j]>0){
                    for (int z=0;z<timeFirstList.size();z++){
                        edgeSource = timeFirstList.get(i).get(i).toString();
                        edgeTarget = timeFirstList.get(j).get(j).toString();

                    }
                    ++count;
                }
            }
        }
        System.out.println(count);
        Vector<Vector<Map<String, Object>>> edgeList = new Vector<>();
        for(int i = 0; i <= count; i++) {
            AtomicReference<Vector<Map<String, Object>>> vector = new AtomicReference<>(new Vector<>());
            edgeList.add(vector.get());
        }
        for (int m = 0; m<count;m++){
            Map<String, Object> edgeMap = new HashMap<>();
            edgeMap.put("source", edgeSource);
            edgeMap.put("target", edgeTarget);

            Vector<Map<String, Object>> vector = edgeList.get(m);
            vector.add(edgeMap);
            edgeList.set(m, vector);
        }

        List<Map<String, Object>> nodeList = new ArrayList<>();
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);
        for (int i =0;i<webs.size();i++) {
            Map<String, Object> element = new HashMap<>();
            element.put("website",webs.get(i).toString());
            nodeList.add(element);
        }

        Map<String, List> result = new HashMap<>();
        result.put("nodes", nodeList);
        result.put("edges", edgeList);
        return result;
    }*/

    public Map<String, List> test(String objectId) {
        int sliceNum = 10; //将相似度分为10份
        double sliceSize = 1.0 / sliceNum;
//        double simi = 0.35;
        //找见该事件下所有相似文章的相似度
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);

        //存放在不同相似度情况下每个节点的大小
        Map<String, Vector<Integer>> articleSimiNumMap = new ConcurrentHashMap<>();

        //??????????
        Vector<Vector<Map<String, Object>>> edgeList = new Vector<>();
        for(int i = 0; i <= sliceNum; i++) {
            AtomicReference<Vector<Map<String, Object>>> vector = new AtomicReference<>(new Vector<>());
            edgeList.add(vector.get());
        }
        List<Map> timeFirstList = timeFirstList(objectId);
        double[][] timeMatrix = new double[webs.size()][webs.size()];
        for(int m =0;m<timeMatrix.length;m++){
            for (int n = 0;n<timeMatrix[m].length;n++){
                if(m ==n){
                    timeMatrix[m][n] =0;
                }else {
                    String articleAID = timeFirstList.get(m).get(m).toString();
                    String articleBID = timeFirstList.get(n).get(n).toString();
                    timeMatrix[m][n] =subTime(articleAID, articleBID);
                    if (timeMatrix[m][n]<0){
                        timeMatrix[m][n]=0;
                    }
                    //初始化节点相关节点的个数
                    if (!articleSimiNumMap.containsKey(articleAID)) {
                        //初始化每个节点在不同相似度下节点的大小,初始化为0
                        Vector<Integer> nodeSizeList = new Vector<>();
                        for (int i = 0; i <= sliceNum; i++) {
                            nodeSizeList.add(0);
                        }
                        articleSimiNumMap.put(articleAID, nodeSizeList);
                    }
                    //时间早的作为source，晚的为target
                    //如果相似度高于simi，连接两个节点
                    String edgeSource = "";
                    String edgeTarget = "";
                    if (timeMatrix[m][n]>0){
                        edgeSource = articleAID;
                        edgeTarget = articleBID;
                    }else {
                        break;
                    }


                    //对相应的相似度阈值+1
                    Vector<Integer> simiAList = articleSimiNumMap.get(articleAID);
                    Vector<Integer> simiBList = articleSimiNumMap.get(articleBID);

                    //循环，加入相应的阈值数组
                    for (int i = 0; i <= 1; i++) {
//                        AtomicInteger countA = new AtomicInteger(simiAList.get(i));
//                        AtomicInteger countB = new AtomicInteger(simiBList.get(i));
//
//                        simiAList.set(i, countA.incrementAndGet());
//                        simiBList.set(i, countB.incrementAndGet());

                        Map<String, Object> edgeMap = new HashMap<>();
                        edgeMap.put("source", edgeSource);
                        edgeMap.put("target", edgeTarget);

                        Vector<Map<String, Object>> vector = edgeList.get(i);
                        vector.add(edgeMap);
                        edgeList.set(i, vector);
                    }
//                    articleSimiNumMap.put(articleAID, simiAList);
//                    articleSimiNumMap.put(articleBID, simiAList);
                }
            }
        }
       /* for (ArticleSimilarEntity articleSimi:articleSimiList) {
            String articleAID = articleSimi.getArticleEntityOne().getArticleId();
            String articleBID = articleSimi.getArticleEntityTwo().getArticleId();
            //初始化节点相关节点的个数
//            if (!articleSimiNumMap.containsKey(articleAID)) {
//                //获取文章标题
//                String articleATitle = articleEntityService.findTitle(articleAID,objectId);
//                //初始化每个节点在不同相似度下节点的大小,初始化为0
//                Vector<Integer> nodeSizeList = new Vector<>();
//                for (int i = 0; i <= sliceNum; i++) {
//                    nodeSizeList.add(0);
//                }
//                articleSimiNumMap.put(articleAID, nodeSizeList);
////                articleTitleMap.put(articleAID, articleATitle);
//                //来源网站
//                String sourceWebsite = websiteEntityService.findWebsitesByArticle(objectId,articleAID);
////                articleWebsite.put(articleAID, sourceWebsite);
//            }
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
//                articleTitleMap.put(articleBID, articleBTitle);
                //来源网站
                String sourceWebsite = websiteEntityService.findWebsitesByArticle(objectId,articleBID);
//                articleWebsite.put(articleBID, sourceWebsite);

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
        }*/

        List<Map<String, Object>> nodeList = new ArrayList<>();
        for (int i =0;i<webs.size();i++) {
            Map<String, Object> element = new HashMap<>();
            element.put("website",webs.get(i).toString());
            nodeList.add(element);
        }

        Map<String, List> result = new HashMap<>();
        result.put("nodes", nodeList);
        result.put("edges", edgeList);
        return result;
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
            double r = 200;
//                        Double.parseDouble(keywordRelated.get(a).getRelatedDegree().toString())*100*3;
            double x = Math.random()*400+100;
            double z = Math.sqrt(r*r-(x-300)*(x-300));
            double[] y = {300-z,300+z};

            Map map = new HashMap();
            map.put("name",keywordRelated.get(a).getKeywordEntityOne().getKeyword());
            map.put("value",keywordRelated.get(a).getRelatedDegree());

            map.put("draggable","true");
//                map.put("x",x);
//                map.put("y",y[index]);
            listkey.add(map);

        }
        return listkey;
    }

    //统计事件的可信度
    public void reliablity(){
        //        List<IndicatorValueEntity> reliablity = indicatorValueEntityService.getObjectReliablity(objectId);
    }


}
