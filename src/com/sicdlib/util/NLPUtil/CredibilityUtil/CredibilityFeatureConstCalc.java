//package com.sicdlib.util.NLPUtil.CredibilityUtil;
//
//import com.sicdlib.dao.IBaseDAO;
//import com.sicdlib.dao.ITableDAO;
//import com.sicdlib.dto.TbEventArticleEntity;
//import com.sicdlib.service.IEventArticleService;
//import com.sicdlib.util.NLPUtil.HanLPNerUtil.HanLPUtil;
//import com.sicdlib.util.NLPUtil.HanLPNerUtil.NERNameValue;
//import com.sicdlib.util.NLPUtil.SentimentUtil.SentimentUtil;
//import com.sicdlib.util.NLPUtil.SimilarityUtil.ConsineSimi;
//import org.apache.commons.lang.StringUtils;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * Created by DeMH on 2017/7/18.
// */
//public class CredibilityFeatureConstCalc {
//    //所有信息传播者的特征
//    String [] allAuthorFields = new String[]{
//            //bbs_china_author：性别/生日/地址/关注量/粉丝
//            "sex", "birthday", "address", "focuse_num", "fans_num",
//            //bbs_mop_author：年龄/等级/好友数/发布消息量/回复量/页面点击量
//            "age", "level", "friends_num", "post_num", "reply_num", "hits",
//            //  登陆量/介绍/注册时间/社团/联系方式/教育/职业
//            "login_num", "introduce", "register_date", "league", "contact_way", "education", "career",
//            //bbs_news_author/bbs_sohu_author：注册时间/精华量/主题/介绍/社区职责/积分/在线时长/声望分
//            "register_time", "elite_num", "title", "introduction", "duty", "point", "online_time", "reputation",
//            //  最后一次登陆时间/职业/地址/家乡/地址/愿望/圈子
//            "last_login", "profession", "location", "hometown", "apartment", "wish", "circle",
//            //  上次更新时间/经历/联系方式/访问量/声望分/获赠金笔/赠出金笔
//            "update_time", "experience", "contact", "visit_num", "popularity", "get_golden", "give_golden",
//            //  基本信息/认证类型/阅读量/文章量/注销时间/个人签名/影响力分数
//            "info", "certification", "read_num", "article_num", "logoff_time", "signature", "influence",
//            //weibo_author：真实姓名/性取向/博客地址/描述
//            "real_name", "sexual_orientation", "relationship_status", "blog_address", "description",
//            //  个人主页/标签/等级/是否为大V
//            "personal_url", "tag", "rank", "is_v"
//    };
//    //所有信息内容的特征字段
//    String []allContentFields = new String[]{"get_golden_num", "forward_num", "comment_num", "reply_num",
//            "recommend_num", "collect_num", "enjoy_num", "like_num", "praise_num", "participant_num",
//            "read_num" + "points" + "hand_up_num", "hand_down_num", "title", "content"};
//    //所有媒介的特征字段(并没用到)
//    String []allMediaFields;
//    String[] allReplyFields = new String[]{"content", "date_time", "prise_num", "praise_num", "refer", "quote_content", "comment_date_time", "author_id"};
//
//    ApplicationContext apt = new ClassPathXmlApplicationContext("beans.xml");
//
//    String eventInfo = "春节前夕,上海女孩逃离江西农村,上海女孩，春节前去“男朋友”家乡江西过年，被第一顿饭“吓”而逃离江西,受不了男友家贫困，决定和男友分手并立即回上海";
//
//    /**
//     * 1. 首先获得与事件相关的文章
//     * 2. 得到文章的发布者
//     * 3. 得到发布者中的各种指标
//     * @param eventID
//     * @return
//     * @throws Exception
//     */
//    public List<Feature> getEventArticles(String eventID) throws Exception {
//        //1.1 获得事件文章
//        IEventArticleService eventArticleService = (IEventArticleService)apt.getBean("eventArticleService");
//        List<TbEventArticleEntity> eventArticles = eventArticleService.getEventArticleByEventID(eventID);
//        //1.1.1 获得所有的author表
//        List<String> authorTableNames = tableDAO.getAllAuthorTables();
//        //1.1.2 获得与事件相关的作者ID、表名
//        List<String[]> EvtAuthorIDsAndTabNames = new ArrayList<>();
//        //1.2 获得事件文章对应的各个网站中的文章post类
//        //获得 article_id 和 table_id --> article_author_id --> 取table中的后缀为_author --> 取里面的指标
//        List<Feature> features = new ArrayList<>();
//        int flag = 0;
//        for (TbEventArticleEntity eventArticle : eventArticles){
//            System.out.println("第 " + flag + "个 : " + eventArticle.getSourceArticleId());
//            flag ++;
//            Feature feature = new Feature();
//            //获得 article_id, table_id
//            String article_id = eventArticle.getSourceArticleId();
//            String tableName = eventArticle.getTable().getTableName();
//            /**
//             * 获得Author特征：作者id, 作者表名, 所有作者字段
//             */
//            HashMap<String, Object> authorData = getOneRecordInforFeatures(article_id, tableName, 1, allAuthorFields);
//            /**
//             * 整合Author特征
//             */
//            if (authorData != null){
//                Feature author_feature = AuthorsFeaturesIntegration(authorData);
//                //设置传播者的整合特征
//                feature.setArticle_id(article_id);
//                feature.setDISSEM_influence(author_feature.getDISSEM_influence());
//                feature.setDISSEM_authority(author_feature.getDISSEM_authority());
//                feature.setDISSEM_liveness(author_feature.getDISSEM_liveness());
//                feature.setDISSEM_preferSimi(author_feature.getDISSEM_preferSimi());
//            }else{
//                feature.setArticle_id(article_id);
//                feature.setDISSEM_influence(0.0);
//                feature.setDISSEM_authority(0.0);
//                feature.setDISSEM_liveness(0.0);
//                feature.setDISSEM_preferSimi(0.0);
//            }
//
//            /**
//             * 获得Content特征：文章id, post表名, 所有内容字段
//             */
//            HashMap<String, Object> contentData = getOneRecordInforFeatures(article_id, tableName, 2, allContentFields);
//            Feature content_feature = contentFeaturesIntegration(contentData);
//            feature.setCONTENT_sentiment(content_feature.getCONTENT_sentiment());
//            feature.setCONTENT_hots(content_feature.getCONTENT_hots());
//            feature.setCONTENT_participant(content_feature.getCONTENT_participant());
//            feature.setCONTENT_distinctive(content_feature.getCONTENT_distinctive());
//            feature.setCONTENT_negative(content_feature.getCONTENT_negative());
//
//            /**
//             * 获得媒介Media特征：受欢迎度p, 重要度i, 活跃度l
//             */
//            Double[] oneMedia = getMediaFeaturesAndIntegration(tableName);
//            feature.setMEDIA_popularity(oneMedia[0]);
//            feature.setMEDIA_importance(oneMedia[1]);
//            feature.setMEDIA_liveness(oneMedia[2]);
//            /**
//             * 获得信息评论Reply特征：支持度、综合情感、负面指数
//             */
//            List<HashMap<String, Object>> replyDataList = getReplyFeaturesList(article_id, tableName, allReplyFields);
//            Feature reply_feature = replyFeaturesIntegration(replyDataList, article_id, tableName);
//            feature.setREPLY_negative(reply_feature.getREPLY_negative());
//            feature.setREPLY_sentiment(reply_feature.getREPLY_sentiment());
//            feature.setREPLY_authority(reply_feature.getREPLY_authority());
//            feature.setREPLY_confidence(reply_feature.getREPLY_confidence());
//            features.add(feature);
//            System.out.println("DI, DA, DL, DP : CH, CP, CD, CS, CN : MP, MI, ML : RC, RS, RN");
//            System.out.println(feature.getDISSEM_influence()+","+feature.getDISSEM_authority()+","+feature.getDISSEM_liveness()+","+feature.getDISSEM_preferSimi()
//                    +" : "+feature.getCONTENT_hots()+","+feature.getCONTENT_participant()+","+feature.getCONTENT_distinctive()
//                    +","+feature.getCONTENT_sentiment()+","+feature.getCONTENT_negative()
//                    +" : "+feature.getMEDIA_popularity()+","+feature.getMEDIA_importance()+","+feature.getMEDIA_popularity()
//                    +" : "+feature.getREPLY_confidence()+","+feature.getREPLY_negative() +","+feature.getREPLY_negative());
//        }
//        return features;
//    }
//
//
//    /**
//     * 获得相关特征：
//     * 根据不同的tableName输入获得不同的特征字段
//     * 2. & 3. 构建信息的数据库相关字段数组 --> 填充数据 --> 整合数组字段，构建信息传播者的特征指标数组
//     * 获得一个author表中某条author_id记录的多个特征字段
//     * 获得一个post表中某条author_id记录的多个特征字段
//     */
//    public HashMap<String, Object> getOneRecordInforFeatures(String article_id, String tableName, Integer flag,
//                                                                     String[] featureFields){
//        //所有信息传播者特征字段记录
//        HashMap<String, Object> authorData_map = new HashMap<>();
//        //article_id是唯一连接信息传播者/信息内容/信息评论者的桥梁
//        authorData_map.put("article_id", article_id);
//        //flag == 1: 表示信息传播者
//        if (flag == 1) {
//            String author_id = tableDAO.getArticleAuthorIDByAidAndTname(article_id, tableName);
//            if (author_id != null){
//                String authortbName = StringUtils.substringBefore(tableName, "_post").concat("_author");
//                // 获得所有的author表
//                List<String> authorTableNames = tableDAO.getAllAuthorTables();
//                if (authorTableNames.contains(authortbName)) {
//                    //2.2 通过article_id获得author_id, 是author表，是内容表，还是评论表
//                    // 如果是author表遍历所有与事件相关的author表, 取出单表所有字段与allAuthorFields数组对比, 如果存在将填充数据
//                    //获得单表所有字段
//                    List<String[]> columns = tableDAO.getAllColumnsByTableName(authortbName);
//                    Object[] columns_arr = columns.toArray();
//                    String fields_str = "";
//                    //查询的字段存储起来
//                    List<String> fields = new ArrayList<>();
//                    //拼接查询的字段
//                    for (Object co : columns_arr) {
//                        if (Arrays.asList(featureFields).contains(co)){
//                            fields_str += co + ", ";
//                            fields.add(co.toString());
//                        }
//                    }
//                    //去掉最后一个逗号
//                    fields_str = fields_str.substring(0, fields_str.length() - 2);
//                    //查询单表相关的信息传播者字段
//                    String sql_author = "select " + fields_str + " from " + authortbName + " where author_id = '" + author_id + "'";
//                    List<Object[]> authorDataArr = (List<Object[]>)baseDAO.getSqlList(sql_author);
//                    authorData_map.put("article_id", article_id);
//                    //将获得的数据填充到featureFields特征字段中, 其中authorDataArr大小为0或1
//                    for (Object[] auData : authorDataArr) {
//                        //设置每条记录的作者id
//                        for (int i = 0; i < auData.length; i++) {
//                            authorData_map.put(fields.get(i), auData[i]);
//                        }
//                    }
//                }
//            }else {
//                return null;
//            }
//        }
//
//        //flag == 2: 表示信息内容
//        if (flag == 2) {
//            //2.2 通过article_id获得author_id, 是author表，是内容表，还是评论表
//            // 如果是author表遍历所有与事件相关的author表, 取出单表所有字段与allAuthorFields数组对比, 如果存在将填充数据
//            //获得单表所有字段
//            List<String[]> columns = tableDAO.getAllColumnsByTableName(tableName);
//            Object[] columns_arr = columns.toArray();
//            String fields_str = "";
//            //查询的字段存储起来
//            List<String> fields = new ArrayList<>();
//            //拼接查询的字段
//            for (Object co : columns_arr) {
//                if (Arrays.asList(featureFields).contains(co)){
//                    fields_str += co + ", ";
//                    fields.add(co.toString());
//                }
//            }
//            //去掉最后一个逗号
//            fields_str = fields_str.substring(0, fields_str.length() - 2);
//            //查询单表相关的信息传播者字段
//            String sql_author = "select "+ fields_str +" from "+ tableName +" where id = '" + article_id + "'";
//            List<Object[]> authorDataArr = baseDAO.getSqlList(sql_author);
//            //将获得的数据填充到featureFields特征字段中, 其中authorDataArr大小为0或1
//            for (Object[] auData : authorDataArr){
//                //设置每条记录的作者id
//                for (int i = 0; i < auData.length; i++) {
//                    authorData_map.put(fields.get(i), auData[i]);
//                }
//            }
//        }
//        return authorData_map;
//    }
//
//    /**
//     * 获得评论的相关特征列表
//     * @param article_id
//     * @param tableName
//     * @param featureFields
//     * @return
//     */
//    public List<HashMap<String, Object>> getReplyFeaturesList(String article_id, String tableName,
//                                   String[] featureFields){
//        List<HashMap<String, Object>> replyFeaturesList = new ArrayList<>();
//        String post_id = tableDAO.getArticlePostIDByAidAndTname(article_id, tableName);
//        if (post_id != null){
//            String commentTbName = StringUtils.substringBefore(tableName, "_post").concat("_comment");
//            // 获得所有的author表
//            List<String> commentTableNames = tableDAO.getAllCommentTables();
//            if (commentTableNames.contains(commentTbName)){
//                //获得单表所有字段
//                List<String[]> columns = tableDAO.getAllColumnsByTableName(commentTbName);
//                Object[] columns_arr = columns.toArray();
//                String fields_str = "";
//                //查询的字段存储起来
//                List<String> fields = new ArrayList<>();
//                //拼接查询的字段
//                for (Object co : columns_arr) {
//                    if (Arrays.asList(featureFields).contains(co)){
//                        fields_str += co + ", ";
//                        fields.add(co.toString());
//                    }
//                }
//                //去掉最后一个逗号
//                fields_str = fields_str.substring(0, fields_str.length() - 2);
//                //查询单表相关的信息传播者字段
//                String sql_author = "select " + fields_str + " from " + commentTbName + " where post_id = '" + post_id + "'";
//                List<Object[]> commentDataArr = (List<Object[]>)baseDAO.getSqlList(sql_author);
//                //将获得的数据填充到featureFields特征字段中
//                for (Object[] auData : commentDataArr) {
//                    HashMap<String, Object> authorData_map = new HashMap<>();
//                    //设置每条记录的作者id
//                    for (int i = 0; i < auData.length; i++) {
//                        authorData_map.put(fields.get(i), auData[i]);
//                    }
//                    replyFeaturesList.add(authorData_map);
//                }
//            }
//        }
//        return replyFeaturesList;
//    }
//
//    /**
//     * 4. 构建信息传播者D的指标：影响力i、权威度a、活跃度l、兴趣偏好一致性p
//     * @param authMap
//     */
//    public Feature AuthorsFeaturesIntegration(HashMap<String, Object> authMap) {
//        //Di = log(是否为大V*50 + 发帖精华量*50 + 获赠金笔*50 + 粉丝量*10 + 声望得分*5 + 影响力分数*5 + 关注量*3 + 积分*2 + 访问量*1 +1)
//        List<Feature> features = new ArrayList<>();
//        Feature feature = new Feature();
//        Double DISSEM_influence = 0.0;
//        Double DISSEM_Authority = 0.0;
//        Double DISSEM_liveness = 0.0;
//        Double DISSEM_prefer_simi = 0.0;
//        DISSEM_influence = DissemInfluence(authMap);
//        DISSEM_Authority = DissemAuthority(authMap);
//        DISSEM_liveness = DissemLiveness(authMap);
//        DISSEM_prefer_simi = DissemPreferSimi(authMap);
//        feature.setDISSEM_influence(DISSEM_influence);
//        feature.setDISSEM_authority(DISSEM_Authority);
//        feature.setDISSEM_liveness(DISSEM_liveness);
//        feature.setDISSEM_preferSimi(DISSEM_prefer_simi);
//        return feature;
//    }
//
//    //1.1 传播者 - 影响力
//    public Double DissemInfluence(HashMap<String, Object> authMap){
//        Integer is_v = 1;
//        if (authMap.get("is_v") != null && !authMap.get("is_v").toString().equals("")) {
//            is_v = Integer.valueOf(authMap.get("is_v").toString());
//        }
//        Integer elite_num = 1;
//        if (authMap.get("elite_num") != null && !authMap.get("elite_num").toString().equals("")){
//            elite_num = Integer.valueOf(authMap.get("elite_num").toString());
//        }
//        Integer get_golden = 1;
//        if (authMap.get("get_golden") != null && !authMap.get("get_golden").toString().equals("")){
//            get_golden = Integer.valueOf(authMap.get("get_golden").toString());
//        }
//        Integer fans_num = 1;
//        if (authMap.get("fans_num") != null && !authMap.get("fans_num").toString().equals("")){
//            fans_num = Integer.valueOf(authMap.get("fans_num").toString());
//        }
//        Integer reputation = 1;
//        Integer popularity = 1;
//        if (authMap.get("reputation") != null && !authMap.get("reputation").toString().equals("")){
//            reputation = Integer.valueOf(authMap.get("reputation").toString());
//        }
//        if (authMap.get("popularity") != null && !authMap.get("popularity").toString().equals("")){
//            popularity = Integer.valueOf(authMap.get("popularity").toString());
//        }
//        reputation = reputation + popularity;
//        Integer influence = 1;
//        if (authMap.get("influence") != null && !authMap.get("influence").toString().equals("")){
//            influence = Double.valueOf(String.valueOf(authMap.get("influence"))).intValue();
//        }
//        Integer focuse_num = 1;
//        if (authMap.get("focuse_num") != null && !authMap.get("focuse_num").toString().equals("")){
//            focuse_num = Integer.valueOf(authMap.get("focuse_num").toString());
//        }
//        Integer point = 1;
//        if (authMap.get("point") != null && !authMap.get("point").toString().equals("")){
//            String point_str = authMap.get("point").toString();
//            if (point_str.contains("分")){
//                point_str = StringUtils.substringBefore(point_str, "分");
//            }
//            point = Integer.valueOf(point_str);
//        }
//        Integer hits = 1;
//        if (authMap.get("hits") != null && !authMap.get("hits").toString().equals("")){
//            hits = Integer.valueOf(authMap.get("hits").toString());
//        }
//        Integer visit_num = 1;
//        if (authMap.get("visit_num") != null && !authMap.get("visit_num").toString().equals("")){
//            visit_num = Integer.valueOf(authMap.get("visit_num").toString());
//        }
//        Integer read_num = 1;
//        if (authMap.get("read_num") != null && !authMap.get("read_num").toString().equals("")){
//            read_num = Integer.valueOf(authMap.get("read_num").toString());
//        }
//        hits = hits + visit_num + read_num;
//
//        Double DISSEM_influence = Math.log(is_v*50.0 + elite_num*50.0 + get_golden*50.0 + fans_num*50.0 + reputation*5.0
//                + influence*5.0 + focuse_num*3.0 + point*2.0 + hits*1.0 + 1);
//        return DISSEM_influence;
//    }
//
//    //1.2 传播者 - 权威度
//    public Double DissemAuthority(HashMap<String, Object> authMap){
//        Integer is_v = 1;
//        if (authMap.get("is_v") != null && !authMap.get("is_v").toString().equals("")) {
//            is_v = Integer.valueOf(authMap.get("is_v").toString());
//        }
//        Integer get_golden = 1;
//        if (authMap.get("get_golden") != null && !authMap.get("get_golden").toString().equals("")){
//            get_golden = Integer.valueOf(authMap.get("get_golden").toString());
//        }
//        Integer focuse_num = 1;
//        if (authMap.get("focuse_num") != null && !authMap.get("focuse_num").toString().equals("")){
//            focuse_num = Integer.valueOf(authMap.get("focuse_num").toString());
//        }
//        Integer give_golden = 1;
//        if (authMap.get("give_golden") != null && !authMap.get("give_golden").toString().equals("")){
//            give_golden = Integer.parseInt(authMap.get("give_golden").toString());
//        }
//        Integer level = 1;
//        if (authMap.get("level") != null && !authMap.get("level").toString().equals("")){
//            String level_str = authMap.get("level").toString();
//            if (level_str.contains("级")){
//                level_str = StringUtils.substringBefore(level_str, "级");
//            }
//            level = Integer.parseInt(level_str);
//        }
//        Integer duty = 0;
//        if (authMap.get("duty") != null && !authMap.get("duty").toString().equals("")){
//            duty = 1;
//        }
//        Integer real_name = 0;
//        if (authMap.get("real_name") != null && !authMap.get("real_name").toString().equals("")){
//            real_name = 1;
//        }
//        Integer contact = 0;
//        if (authMap.get("contact") != null && !authMap.get("contact").toString().equals("")){
//            contact = 1;
//        }
//        Integer contact_way = 0;
//        if (authMap.get("contact_way") != null && !authMap.get("contact_way").toString().equals("")){
//            contact_way = 1;
//        }
//        Integer education = 0;
//        if (authMap.get("education") != null && !authMap.get("education").toString().equals("")){
//            education = 1;
//        }
//        Integer career = 0;
//        if (authMap.get("career") != null && !authMap.get("career").toString().equals("")){
//            career = 1;
//        }
//        Integer profession = 0;
//        if (authMap.get("profession") != null && !authMap.get("profession").toString().equals("")){
//            profession = 1;
//        }
//        Integer experience = 0;
//        if (authMap.get("experience") != null && !authMap.get("experience").toString().equals("")){
//            experience = 1;
//        }
//        Integer info = 0;
//        if (authMap.get("info") != null && !authMap.get("info").toString().equals("")){
//            info = 1;
//        }
//        Integer introduce = 0;
//        if (authMap.get("introduce") != null && !authMap.get("introduce").toString().equals("")){
//            introduce = 1;
//        }
//        Integer introduction = 0;
//        if (authMap.get("introduction") != null && !authMap.get("introduction").toString().equals("")){
//            introduction = 1;
//        }
//        Integer signature = 0;
//        if (authMap.get("signature") != null && !authMap.get("signature").toString().equals("")){
//            signature = 1;
//        }
//        Integer description = 0;
//        if (authMap.get("description") != null && !authMap.get("description").toString().equals("")){
//            description = 1;
//        }
//        Integer address = 0;
//        if (authMap.get("address") != null && !authMap.get("address").toString().equals("")){
//            address = 1;
//        }
//        Integer location = 0;
//        if (authMap.get("location") != null && !authMap.get("location").toString().equals("")){
//            location = 1;
//        }
//        Integer apartment = 0;
//        if (authMap.get("apartment") != null && !authMap.get("apartment").toString().equals("")){
//            apartment = 1;
//        }
//        Integer hometown = 0;
//        if (authMap.get("hometown") != null && !authMap.get("hometown").toString().equals("")){
//            hometown = 1;
//        }
//        Integer birthday = 0;
//        if (authMap.get("birthday") != null && !authMap.get("birthday").toString().equals("")){
//            birthday = 1;
//        }
//        Integer age = 0;
//        if (authMap.get("age") != null && !authMap.get("age").toString().equals("")){
//            age = 1;
//        }
//        Integer sex = 0;
//        if (authMap.get("sex") != null && !authMap.get("sex").toString().equals("")){
//            sex = 1;
//        }
//        Integer sex_orientation = 0;
//        if (authMap.get("sex_orientation") != null && !authMap.get("sex_orientation").toString().equals("")){
//            sex_orientation = 1;
//        }
//        Integer relationship_status = 0;
//        if (authMap.get("relationship_status") != null && !authMap.get("relationship_status").toString().equals("")){
//            relationship_status = 1;
//        }
//        Integer blog_address = 0;
//        if (authMap.get("blog_address") != null && !authMap.get("blog_address").toString().equals("")){
//            blog_address = 1;
//        }
//        Integer personal_url = 0;
//        if (authMap.get("personal_url") != null && !authMap.get("personal_url").toString().equals("")){
//            personal_url = 1;
//        }
//        Double DISSEM_Authority = Math.log(is_v*50.0 + get_golden*50.0 + give_golden*20.0 + focuse_num*20.0 + level*15.0 + duty*15.0
//                + real_name*15 + (contact + contact_way)*10 + education*10 + (career + profession)*10 + experience *10
//                + info*10 + (introduce + introduction + signature + description)*10 + (address + location + apartment)*10
//                + hometown*10 + birthday*10 + age*10 + sex*10 + sex_orientation*10 + relationship_status*10
//                + (blog_address + personal_url)*10 + 1);
//        return DISSEM_Authority;
//    }
//
//    //1.3 传播者 - 活跃度
//    public Double DissemLiveness(HashMap<String, Object> authMap){
//        Integer level = 1;
//        if (authMap.get("level") != null && !authMap.get("level").toString().equals("")){
//            String level_str = authMap.get("level").toString();
//            if (level_str.contains("级")){
//                level_str = StringUtils.substringBefore(level_str, "级");
//            }
//            level = Integer.parseInt(level_str);
//        }
//        Integer update_days = 0;
//        Integer last_login_days = 0;
//        Date now_date = new Date();
//        String update_time = "";
//        if (authMap.get("update_time") != null && !authMap.get("update_time").toString().equals("")){
//            update_time = authMap.get("update_time").toString();
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(now_date);
//        Long now_time = cal.getTimeInMillis();
//        try {
//            if (!update_time.equals("")){
//                Date update_time_date = sdf.parse(update_time);
//                cal.setTime(update_time_date);
//                Long update_time_date_time = cal.getTimeInMillis();
//                Long update_time_days = (now_time - update_time_date_time) / (1000*3600*24);
//                update_days = Math.toIntExact(60 / (update_time_days + 1));
//            }
//            String last_login = "";
//            if (authMap.get("update_time") != null && !authMap.get("update_time").toString().equals("")){
//                last_login = authMap.get("last_login").toString();
//            }
//            if (!last_login.equals("")){
//                Date last_login_date = sdf.parse(last_login);
//                cal.setTime(last_login_date);
//                Long last_login_long = cal.getTimeInMillis();
//                String logoff_time = authMap.get("logoff_time").toString();
//                Date logoff_time_date = sdf.parse(logoff_time);
//                cal.setTime(logoff_time_date);
//                Long logoff_time_long = cal.getTimeInMillis();
//                Long last_login_time_days = (now_time - last_login_long) / (1000*3600*24);
//                Long logoff_time_days = (now_time - logoff_time_long) / (1000*3600*24);
//                last_login_days = Math.toIntExact(30 / (last_login_time_days + logoff_time_days +1));
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Integer online_num = 1;
//        if (authMap.get("online_num") != null && !authMap.get("online_num").toString().equals("")){
//            online_num = Integer.valueOf(authMap.get("online_num").toString());
//        }
//        Integer login_num = 1;
//        if (authMap.get("login_num") != null && !authMap.get("login_num").toString().equals("")){
//            login_num = Integer.valueOf(authMap.get("login_num").toString());
//        }
//        Integer post_num = 1;
//        if (authMap.get("post_num") != null && !authMap.get("post_num").toString().equals("")){
//            post_num = Integer.valueOf(authMap.get("post_num").toString());
//        }
//        Integer article_num = 1;
//        if (authMap.get("article_num") != null && !authMap.get("article_num").toString().equals("")){
//            article_num = Integer.valueOf(authMap.get("article_num").toString());
//        }
//        Integer reply_num = 1;
//        if (authMap.get("reply_num") != null && !authMap.get("reply_num").toString().equals("")){
//            reply_num = Integer.valueOf(authMap.get("reply_num").toString());
//        }
//        Double DISSEM_liveness = Math.log(level*10 + update_days*5 + last_login_days*5 + online_num*5 + login_num*3
//                +(post_num + article_num)*3 + reply_num*3 + 1);
//        return DISSEM_liveness;
//    }
//
//    //1.4 传播者 - 事件与作者偏好相似度
//    public Double DissemPreferSimi(HashMap<String, Object> authMap){
//        String author_prefer = "";
//        author_prefer = String.valueOf(authMap.get("tag")) + String.valueOf(authMap.get("introduce")) + String.valueOf(authMap.get("introduction"))
//                + String.valueOf(authMap.get("signature")) + String.valueOf(authMap.get("description"))
//                + String.valueOf(authMap.get("circle")) + String.valueOf(authMap.get("league")) + String.valueOf(authMap.get("info"))
//                + String.valueOf(authMap.get("experience")) + String.valueOf(authMap.get("title")) + String.valueOf(authMap.get("wish"));
//        //将ners1和text2传入获得text2中和text1相同的分词并赋予权重
//        //获得NER
//        List<NERNameValue> ners1 = HanLPUtil.getNERNameValuesMap(eventInfo);
//        //将NER，text传入获得所有分词并赋予权重
//        ners1 = HanLPUtil.getAllNERAndSegs(ners1, eventInfo);
//        //将ners1和text2传入获得text2中和text1相同的分词并赋予权重
//        List<NERNameValue> ners2 = HanLPUtil.getNER2ByNameValue(ners1, author_prefer);
//        Double DISSEM_prefer_simi = ConsineSimi.calSimiByNERNameValue(ners1, ners2, author_prefer);
//        return DISSEM_prefer_simi;
//    }
//
//    /**
//     * 整合内容特征
//     * 内容特征：热度h, 参与度p, 语法突出度d, 语义综合情感g, 语义正面情感n
//     * @param cntdata_map
//     * @return
//     */
//    public Feature contentFeaturesIntegration(HashMap<String, Object> cntdata_map) {
//        Feature feature = new Feature();
//        //内容热度Ch
//        Double CONTENT_hots = 0.0;
//        //内容参与度Cp
//        Double CONTENT_participant = 0.0;
//        //内容语法突出度
//        Double CONTENT_distinctive = 0.0;
//        //内容语义综合情感Cs
//        Double CONTENT_sentiment = 0.0;
//        //内容语义负面指数Cn
//        Double CONTENT_negative = 0.0;
//        Integer get_golden_num = 0;
//        if (cntdata_map.get("get_golden_num") != null){
//            get_golden_num = Integer.valueOf(String.valueOf(cntdata_map.get("get_golden_num")));
//        }
//        Integer forward_num = 0;
//        if (cntdata_map.get("forward_num") != null){
//            forward_num = Integer.valueOf(String.valueOf(cntdata_map.get("forward_num")));
//        }
//        Integer comment_num = 0;
//        if (cntdata_map.get("comment_num") != null) {
//            comment_num = Integer.valueOf(String.valueOf(cntdata_map.get("comment_num")));
//        }
//        Integer reply_num = 0;
//        if (cntdata_map.get("reply_num") != null) {
//            reply_num = Integer.valueOf(String.valueOf(cntdata_map.get("reply_num")));
//        }
//        Integer recommend_num = 0;
//        if (cntdata_map.get("recommend_num") != null) {
//            recommend_num = Integer.valueOf(String.valueOf(cntdata_map.get("recommend_num")));
//        }
//        Integer collect_num = 0;
//        if (cntdata_map.get("collect_num") != null) {
//            collect_num = Integer.valueOf(String.valueOf(cntdata_map.get("collect_num")));
//        }
//        Integer enjoy_num = 0;
//        if (cntdata_map.get("enjoy_num") != null) {
//            enjoy_num = Integer.valueOf(String.valueOf(cntdata_map.get("enjoy_num")));
//        }
//        Integer like_num = 0;
//        if (cntdata_map.get("like_num") != null) {
//            like_num = Integer.valueOf(String.valueOf(cntdata_map.get("like_num")));
//        }
//        Integer praise_num = 0;
//        if (cntdata_map.get("praise_num") != null) {
//            praise_num = Integer.valueOf(String.valueOf(cntdata_map.get("praise_num")));
//        }
//        Integer participant_num = 0;
//        if (cntdata_map.get("participant_num") != null) {
//            participant_num = Integer.valueOf(String.valueOf(cntdata_map.get("participant_num")));
//        }
//        Integer read_num = 0;
//        if (cntdata_map.get("read_num") != null) {
//            read_num = Integer.valueOf(String.valueOf(cntdata_map.get("read_num")));
//        }
//        Integer points = 0;
//        if (cntdata_map.get("points") != null) {
//            points = Integer.valueOf(String.valueOf(cntdata_map.get("points")));
//        }
//        Integer hand_up_num = 0;
//        if (cntdata_map.get("hand_up_num") != null) {
//            hand_up_num = Integer.valueOf(String.valueOf(cntdata_map.get("hand_up_num")));
//        }
//        Integer hand_down_num = 0;
//        if (cntdata_map.get("hand_down_num") != null) {
//            hand_down_num = Integer.valueOf(String.valueOf(cntdata_map.get("hand_down_num")));
//        }
//        //2.1 内容热度
//        CONTENT_hots = Math.log((get_golden_num*50 + forward_num*20 + (comment_num + reply_num)*10
//                + recommend_num*5 + collect_num*5 + (enjoy_num + like_num + praise_num)*5
//                + participant_num*1 + (read_num + points)*1 +1)*hand_up_num*30 + 1) - Math.log((read_num + points)*1*hand_down_num*30 + 1);
//
//        //2.2 内容参与度
//        CONTENT_participant = Math.log( (get_golden_num*50 + forward_num*20 + (comment_num + reply_num)*10
//                + recommend_num*5 + collect_num*5 + (enjoy_num + like_num + praise_num)*5 + participant_num*1)
//                / (points + read_num +1) +1);
//        String title = String.valueOf(cntdata_map.get("title"));
//        //获得title中的情感词:极情感词数量，消极情感词数量，总句子数
//        Double title_scores[] = SentimentUtil.sentimentScore(title);
//        Double stmt_title_score = (title_scores[0] + title_scores[1]);
//        //title标点符号个数
//        String title_str = title.replaceAll("[,，.。;；]", "");
//        Double punc_title_num = Double.valueOf(title.length() - title_str.length());
//        //title是否包含多媒体
//        Integer isMedia = 0;
//        if (title.contains("（组图）") || title.contains("（图）") || title.contains("图") || title.contains("图集")) {
//            isMedia = 1;
//        }
//        //title长度
//        Double title_length = Double.valueOf(title.length());
//        String content = String.valueOf(cntdata_map.get("content"));
//        //content情感词
//        Double content_scores[] = SentimentUtil.sentimentScore(content);
//        Double stmt_content_score = (content_scores[0] + content_scores[1]);
//        //content是否包含url
//        Integer isUrl = 0;
//        if (content.contains("http://")) {
//            isUrl = 1;
//        }
//        //content是否包含标签
//        Integer isTags = 0;
//        if (content.contains("#")) {
//            isTags = 1;
//        }
//        //content包含标点符号个数
//        String content_str = content.replaceAll("[,，.。;；]", "");
//        Double punc_content_num = (content.length() - content_str.length())/1.0;
//        //2.3 语法突出度
//        CONTENT_distinctive = Math.log((stmt_title_score*5.0 + title_length*1.0 + punc_title_num*3.0)*2
//                + stmt_content_score*5.0 + isUrl*10.0 + isMedia*10.0 + isTags*10 + punc_content_num*3 +1);
//        //2.4 语义综合情感
//        Double stmt_temp_scores = stmt_title_score*5.0 + stmt_content_score*1.0;
//        CONTENT_sentiment = stmt_temp_scores;
//        //2.5 语义的负面指数
//        CONTENT_negative = title_scores[1]/title_scores[2]*5 + content_scores[1]/content_scores[2]*1;
//        feature.setCONTENT_hots(CONTENT_hots);
//        feature.setCONTENT_participant(CONTENT_participant);
//        feature.setCONTENT_distinctive(CONTENT_distinctive);
//        feature.setCONTENT_sentiment(CONTENT_sentiment);
//        feature.setCONTENT_negative(CONTENT_negative);
//        return feature;
//    }
//
//    /**
//     * 获得媒介特征：受欢迎度p、重要度i、活跃度l
//     * @param tableName
//     */
//    public static Double[] getMediaFeaturesAndIntegration(String tableName) {
//        //Double[] {Alexa, PR值, 活跃度(用户总数*日活量/月活量)}
//        HashMap<String, Double[]> mediaFeatureMap = new HashMap<>();
//        mediaFeatureMap.put("bbs_china_post", new Double[]{-Math.log1p(58.0)+10, 6.0, Math.log1p(97500)});
//        mediaFeatureMap.put("bbs_mop_post",new Double[]{-Math.log1p(1099.0)+10, 7.0, Math.log1p(57750)});
//        mediaFeatureMap.put("bbs_news_post",new Double[]{-Math.log1p(128.0)+10, 9.0, Math.log1p(47250)});
//        mediaFeatureMap.put("bbs_people_post",new Double[]{-Math.log1p(38.0)+10, 7.0, Math.log1p(2242500)});
//        mediaFeatureMap.put("bbs_sohu_post",new Double[]{-Math.log1p(3.0)+10, 8.0, Math.log1p(32025000)});
//        mediaFeatureMap.put("bbs_tianya_post",new Double[]{-Math.log1p(34.0)+10, 7.0, Math.log1p(5340000)});
//        mediaFeatureMap.put("bbs_xici_post",new Double[]{-Math.log1p(535.0)+10, 7.0, Math.log1p(64500)});
//        mediaFeatureMap.put("blogchina_blog",new Double[]{-Math.log1p(2406.0)+10, 6.0, Math.log1p(2250)});
//        mediaFeatureMap.put("blog_163_post",new Double[]{-Math.log1p(13.0)+10, 7.0, Math.log1p(1177500)});
//        mediaFeatureMap.put("blog_sina_post",new Double[]{-Math.log1p(4.0)+10, 7.0, Math.log1p(30150000)});
//        mediaFeatureMap.put("douban_group_post",new Double[]{-Math.log1p(12.0)+10, 7.0, Math.log1p(690000)});
//        mediaFeatureMap.put("kdnet_post",new Double[]{-Math.log1p(653.0)+10, 6.0, Math.log1p(73500)});
//        mediaFeatureMap.put("moe_news",new Double[]{-Math.log1p(2895.0)+10, 9.0, Math.log1p(12000)});
//        mediaFeatureMap.put("news_sanqin",new Double[]{-Math.log1p(10304.0)+10, 6.0, Math.log1p(2250)});
//        mediaFeatureMap.put("news_sina",new Double[]{-Math.log1p(4.0)+10, 8.0, Math.log1p(30150000)});
//        mediaFeatureMap.put("people_news",new Double[]{-Math.log1p(38.0)+10, 9.0, Math.log1p(2242500)});
//        mediaFeatureMap.put("weibo_post",new Double[]{-Math.log1p(33.0)+10, 9.0, Math.log1p(25050000)});
//        mediaFeatureMap.put("xinhua_news",new Double[]{-Math.log1p(128.0)+10, 9.0, Math.log1p(3382500)});
//        mediaFeatureMap.put("xinwen110_news",new Double[]{-Math.log1p(5000.0)+10, 4.0, Math.log1p(1000)});
//        Double[] oneMedia = mediaFeatureMap.get(tableName);
//        return oneMedia;
//    }
//
//    /**
//     * 整合评论信息的特征：支持度、综合情感、负面指数、权威度
//     * @param replyDataList
//     * @return
//     */
//    public Feature replyFeaturesIntegration(List<HashMap<String, Object>> replyDataList, String article_id, String tableName) {
//        System.out.println("评论数量：" + replyDataList.size());
//        String article_content = tableDAO.getArticleContent(article_id, tableName);
//        System.out.println("文章内容: "+article_content);
//        //得到article的情感得分
//        Double[] article_scores = SentimentUtil.sentimentScore(article_content);
//        Double article_stmt_scores = article_scores[0] + article_scores[1];
//        System.out.println("内容情感得分："+article_stmt_scores);
//        Double REPLY_confidence = 0.0;
//        Double REPLY_sentiment = 0.0;
//        Double REPLY_negative = 0.0;
//        Double REPLY_authority = 0.0;
//        //对所有评论进行遍历，求各条评论的四个特征的和
//        for (HashMap<String, Object> replyData_map : replyDataList) {
//            Double PER_REPLY_confidence = 0.0;
//            Double PER_REPLY_sentiment = 0.0;
//            Double PER_REPLY_negative = 0.0;
//            Double PER_REPLY_authority = 0.0;
//            String content = String.valueOf(replyData_map.get("content"));
//            Double stmt_scores = 0.0;
//            Double[] cnt_scores = null;
//            if (content != null){
//                cnt_scores = SentimentUtil.sentimentScore(content);
//                stmt_scores = cnt_scores[0] + cnt_scores[1];
//            }
//            Double articleAndReplyCntSimi = 0.0;
//            List<String> deny_list = SentimentUtil.getPosOrNegList("data/sentimentData/否定词语（中文）.txt");
//            //获得评论中的否定词个数
//            List<String> content_segs = HanLPUtil.getSegMaptoList(content);
//            int deny_num = 0;
//            for (String seg : content_segs){
//                if (deny_list.contains(seg)){
//                    deny_num ++;
//                }
//            }
//            //如果情感得分==0, 认为是客观评价
//            if (stmt_scores == 0) {
//                List<NERNameValue> ner_article = HanLPUtil.getNERNameValuesMap(article_content);
//                List<NERNameValue> ner_content = HanLPUtil.getNERNameValuesMap(content);
//                articleAndReplyCntSimi = ConsineSimi.calSimiByNERNameValue(ner_article, ner_content, content);
//                PER_REPLY_confidence = articleAndReplyCntSimi - deny_num;
//            }
//            //如果情感得分!=0，认为是主观评价
//            else {
//                //评论与article内容情感一致，说明评论支持article观点，否则不支持
//                if (stmt_scores > 0 && article_stmt_scores > 0 || stmt_scores < 0 && article_stmt_scores < 0) {
//                    stmt_scores = Math.abs(stmt_scores);
//                    //情感得分
//                    PER_REPLY_confidence = stmt_scores + 1 - deny_num;
//                } else {
//                    stmt_scores = -Math.abs(stmt_scores);
//                    PER_REPLY_confidence = stmt_scores - 1 - deny_num;
//                }
//            }
//            //对支持度进行累加
//            REPLY_confidence += PER_REPLY_confidence;
//            //情感得分
//            PER_REPLY_sentiment = stmt_scores;
//            REPLY_sentiment += PER_REPLY_sentiment;
//            //负面指数：负面情感词/句子数
//            if (cnt_scores != null) {
//                PER_REPLY_negative = cnt_scores[1] / cnt_scores[2];
//            }
//            REPLY_negative += PER_REPLY_negative;
//            //权威度author_id
////            HashMap<String, Object> authorData = getOneRecordInforFeatures(article_id, tableName, 1, allAuthorFields);
////            PER_REPLY_authority = DissemAuthority(authorData);
////            REPLY_authority += PER_REPLY_authority;
//        }
//        Feature feature = new Feature();
//        feature.setREPLY_confidence(REPLY_confidence);
//        feature.setREPLY_authority(REPLY_authority);
//        feature.setREPLY_negative(REPLY_negative);
//        feature.setREPLY_sentiment(REPLY_sentiment);
//        return feature;
//    }
//
//    @Test
//    public void test01(){
//        try {
//            getEventArticles("3b831c50-29ab-4824-ac74-b8669162275e");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test02(){
//        String content = "不是";
//        List<String> deny_list = SentimentUtil.getPosOrNegList("data/sentimentData/否定词语（中文）.txt");
//        //获得评论中的否定词个数
//        List<String> content_segs = HanLPUtil.getSegMaptoList(content);
//        for (String ctnseg : content_segs) {
//            System.out.println(ctnseg);
//        }
//        int deny_num = 0;
//        for (String seg : content_segs){
//            if (deny_list.contains(seg)){
//                deny_num ++;
//            }
//        }
//        System.out.println(deny_num);
//    }
//
//}
