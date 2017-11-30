package com.sicdlib.util.Mysql;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wlw
 */
public class HbaseMysqlColumnsMapping {

    /**
     * @ wlw
     * 将hbase数据字段与mysql数据字段对应： 文章<>mysql:hbase = 17:29</>
     * @return
     */
    public static Map<String, String[]> getHbaseMysqlColumnsMapping(){
        Map<String, String[]> hmcolumnsmapping = new HashMap<>();
        //Map<Mysql, HBase> ：post文章映射
        hmcolumnsmapping.put("sourceArticleID", new String[]{"post_id"});
        hmcolumnsmapping.put("sourceAuthorID", new String[]{"author_id"});
        hmcolumnsmapping.put("content", new String[]{"content", "news_content", "article_content"});
        hmcolumnsmapping.put("title", new String[]{"title","news_title", "article_title"});
        hmcolumnsmapping.put("keyWords", new String[]{"key_words"});
        hmcolumnsmapping.put("postTime", new String[]{"date_time", "news_time"});
        hmcolumnsmapping.put("commentNumber", new String[]{"comment_num"});
        hmcolumnsmapping.put("scanNumber", new String[]{"read_num", "hits", "article_read_num"});
        hmcolumnsmapping.put("participationNumber", new String[]{"participant_num"});
        hmcolumnsmapping.put("replyNumber", new String[]{"reply_num"});
        hmcolumnsmapping.put("likeNumber", new String[]{"like_num", "enjoy_num", "hand_up_num"});
        hmcolumnsmapping.put("recommendNumber", new String[]{"recommend_num"});
        hmcolumnsmapping.put("collectNumber", new String[]{"collect_num"});
        hmcolumnsmapping.put("giveReward", new String[]{"praise_num", "prise_num", "get_golden_num"});
        hmcolumnsmapping.put("transmitNumber", new String[]{"forward_num"});
        hmcolumnsmapping.put("trampleNumber", new String[]{""});
        hmcolumnsmapping.put("newsResource", new String[]{"news_source", "source_text", "from_media"});
        return hmcolumnsmapping;
    }

    /**
     * @ wlw
     * 将hbase数据字段与mysql数据字段对应： 作者<>mysql:hbase = 35:58</>
     * @return
     */
    public static Map<String, String[]> getAuthorHbaseMysqlColumnsMapping(){
        Map<String, String[]> hmcolumnsmapping = new HashMap<>();
        //Map<Mysql, Hbase> ：author作者映射
        hmcolumnsmapping.put("sourceAuthorID", new String[]{"author_id", "user_id"});
        hmcolumnsmapping.put("name", new String[]{"author_name", "nick_name", "nick"});
        hmcolumnsmapping.put("sex", new String[]{"sex"});
        hmcolumnsmapping.put("birthday", new String[]{"birthday"});
        hmcolumnsmapping.put("address", new String[]{"address", "location", "apartment"});
        hmcolumnsmapping.put("focusNumber", new String[]{"focuse_num"});
        hmcolumnsmapping.put("fansNumber", new String[]{"fans_num"});
        hmcolumnsmapping.put("age", new String[]{"age"});
        hmcolumnsmapping.put("friendNumber", new String[]{"friends_num"});
        hmcolumnsmapping.put("replayNumber", new String[]{"reply_num"});
        hmcolumnsmapping.put("clickNumber", new String[]{"hits", "visit_num", "read_num"});
        hmcolumnsmapping.put("loginNumber", new String[]{"login_num"});
        hmcolumnsmapping.put("introduction", new String[]{"introduce", "introduction", "info", "signature", "description"});
        hmcolumnsmapping.put("registrationTime", new String[]{"register_date", "register_time", "join_time"});
        hmcolumnsmapping.put("educationBackground", new String[]{"education"});
        hmcolumnsmapping.put("career", new String[]{"career"});
        hmcolumnsmapping.put("elitePostNumber", new String[]{"post_num", "elite_num", "article_num", "blog_num"});
        hmcolumnsmapping.put("score", new String[]{"point"});
        hmcolumnsmapping.put("onlineTime", new String[]{"online_time"});
        hmcolumnsmapping.put("fameDegree", new String[]{""});
        hmcolumnsmapping.put("lastLoginTime", new String[]{"last_login", "logoff_time"});
        hmcolumnsmapping.put("workUnit", new String[]{"company"});
        hmcolumnsmapping.put("updateArticalTime", new String[]{"update_time"});
        hmcolumnsmapping.put("phoneNumber", new String[]{"contact_way", "contact"});
        hmcolumnsmapping.put("reputation", new String[]{"reputation", "popularity", "influence"});
        hmcolumnsmapping.put("getReward", new String[]{"get_golden"});
        hmcolumnsmapping.put("giveReward", new String[]{"give_golden"});
        hmcolumnsmapping.put("isCertification", new String[]{"certification"});
        hmcolumnsmapping.put("portraitURL", new String[]{"picture_head_url", "image_url", "head_img_url", "b_head_img_url"});
        hmcolumnsmapping.put("realName", new String[]{"real_name", "name"});
        hmcolumnsmapping.put("sexualOrientation", new String[]{"sexual_orientation"});
        hmcolumnsmapping.put("email", new String[]{"mail"});
        hmcolumnsmapping.put("QQ", new String[]{"qq"});
        hmcolumnsmapping.put("bloodType", new String[]{"blood_type"});
        hmcolumnsmapping.put("isBigV", new String[]{"is_v"});
        return hmcolumnsmapping;
    }

    /**
     * @ wlw
     * 将hbase数据字段与mysql数据字段对应： 评论表<>mysql:hbase = 6:9</>
     * @return
     */
    public static Map<String, String[]> getCommentHbaseMysqlColumnsMapping(){
        Map<String, String[]> hmcolumnsmapping = new HashMap<>();
        //Map<Mysql, Hbase> ：author作者映射
        hmcolumnsmapping.put("sourceCommentID", new String[]{"comment_id"});
        hmcolumnsmapping.put("likeNumber", new String[]{"prise_num", "praise_num"});
        hmcolumnsmapping.put("content", new String[]{"content"});
        hmcolumnsmapping.put("commentTime", new String[]{"date_time", "comment_date_time"});
        hmcolumnsmapping.put("replayNumber", new String[]{"reply_num", "replay_num"});
        hmcolumnsmapping.put("graphURL", new String[]{"img_url_list"});

        return hmcolumnsmapping;
    }

}
