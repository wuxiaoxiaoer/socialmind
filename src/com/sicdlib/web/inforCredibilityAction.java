package com.sicdlib.web;

import com.sicdlib.entity.ArticleCommentEntity;
import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.AuthorEntity;
import com.sicdlib.entity.ObjectEntity;
import com.sicdlib.service.ObjectEntityService;
import com.sicdlib.util.NLPUtil.CredibilityUtil.Feature;
import com.sicdlib.util.NLPUtil.SentimentUtil.SentimentUtil;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 获得文章、作者、评论、媒介的多个特征
 */
@Controller("/*")
public class inforCredibilityAction {

    @Autowired
    private ObjectEntityService objectEntityService;

    /**
     * 1. 获得事件 --> 将事件开始结束时间分为n份 --> 在各个时间长度上找相关的文章集 -->
     * 在各个时间段内获得文章集中的文章、作者、评论的平均值 --> 得到Feature
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("inforCredibility")
    public void inforCredibility(HttpServletRequest req) throws Exception {
        String eventName = req.getParameter("eventName");
        PropertyFilter filter = new PropertyFilter("eventName", eventName);
        ObjectEntity objectEntity = null;
        if (objectEntityService.search(filter).size() != 0){
            objectEntity = objectEntityService.search(filter).get(0);
        }
        String startTime = objectEntity.getStartTime();
        String endTime = objectEntity.getEndTime();
        //将事件开始事件到结束时间分为n等份
//        getNTimeFields(startTime, endTime);
        Set<ArticleEntity> articles = objectEntity.getArticles();


        //获得文章的特征

        //获得作者的特征

        //获得评论的特征

    }

    /**
     * 获得时间:
     * 按照20分之一的总天数不断递增，例：20分之一为1天，那么，时间序列为0,1,3,6,10,15,21...
     * @param
     * @param
     */
    @org.junit.Test
    public void getNTimeFields() throws Exception{
        String startTime = "2016-10-10 12:00:00";
        String endTime = "2017-10-10 12:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse(startTime);
        Date endDate = sdf.parse(endTime);
        long intervalDays = (endDate.getTime() - startDate.getTime()) / (1000*3600*24);
        System.out.println(intervalDays);
        int n = 0;
        Calendar calendar = new GregorianCalendar();
        for (int i = 1; n<= intervalDays; i++){
            n = i*(i-1)/2;
            System.out.println(n);

        }

    }

    /**
     * 获得文章内容的特征：
     * 热度、参与度、情感向量（情感值）、主题向量
     * @param articleEntity
     * @param feature
     * @return
     */
    public Feature getArticleFeatures(ArticleEntity articleEntity, Feature feature){
        //数据量大小排序：评论量，浏览量，回复量，喜欢量，打赏量，转发量 // 参与量、推荐量、收藏量、踩量
        //热度：评论量*10 + 浏览量*5 + 回复量*5 + 喜欢量*3 + 打赏量*1 + 转发量*1
        Double CONTENT_hots = 0.0;
        //参与度：评论量*10 + 回复量*3 + 参与量*3 + 推荐量*1 + 收藏量*1 + 踩量*1
        Double CONTENT_participant = 0.0;
        //情感向量
        List<Double> CONTENT_sentiments;
        Double CONTENT_sentiment = 0.0;
        //主题向量
        List<Double> CONTENT_topics;
        //源文章中字段：
        //评论量
        Integer commentNum = 0;
        if(articleEntity.getCommentNumber() != null){ commentNum = articleEntity.getCommentNumber();}
        //浏览量
        Integer scanNum = 0;
        if(articleEntity.getScanNumber() != null){ scanNum = articleEntity.getScanNumber();}
        //回复量
        Integer replyNum = 0;
        if(articleEntity.getReplyNumber() != null){ replyNum = articleEntity.getReplyNumber();}
        //喜欢量
        Integer likeNum = 0;
        if(articleEntity.getLikeNumber() != null){ likeNum = articleEntity.getLikeNumber();}
        //打赏量
        Integer rewardNum = 0;
        if(articleEntity.getGiveReward() != null){ rewardNum = articleEntity.getGiveReward();}
        //转发量
        Integer reportNum = 0;
        if(articleEntity.getTransmitNumber() != null){ reportNum = articleEntity.getTransmitNumber();}
        //参与量
        Integer participantNum = 0;
        if(articleEntity.getParticipationNumber() != null){ participantNum = articleEntity.getParticipationNumber();}
        //推荐量
        Integer recommendNum = 0;
        if(articleEntity.getRecommendNumber() != null){ recommendNum = articleEntity.getRecommendNumber();}
        //收藏量
        Integer collectNum = 0;
        if(articleEntity.getCollectNumber() != null){ collectNum = articleEntity.getCollectNumber();}
        //踩量
        Integer trampleNum = 0;
        if(articleEntity.getTrampleNumber() != null){ trampleNum = articleEntity.getTrampleNumber();}
        //热度：评论量*10 + 浏览量*5 + 回复量*5 + 喜欢量*3 + 打赏量*1 + 转发量*1
        CONTENT_hots = (double)(commentNum*10 + scanNum*5 + replyNum*3 + likeNum*3 + rewardNum*1 + reportNum*1);
        //参与度：评论量*10 + 回复量*5 + 参与量*1 + 推荐量*1 + 收藏量*1 + 踩量*1
        CONTENT_participant = (double)(commentNum*10 + replyNum*5 + participantNum*1 + recommendNum*1 + collectNum*1 + trampleNum*1);
        String content = "";
        if (articleEntity.getContent() != null){
            content = articleEntity.getContent();
        }
        Double title_scores[] = SentimentUtil.sentimentScore(content);
        //情感值
        CONTENT_sentiment = title_scores[2];

//        情感向量
//        CONTENT_sentiments = ;
        //主题向量
//        CONTENT_topics = ;
        feature.setCONTENT_hots(CONTENT_hots);
        feature.setCONTENT_participant(CONTENT_participant);
        feature.setCONTENT_sentiment(CONTENT_sentiment);
        return feature;
    }

    /**
     * 获得作者的特征：
     * 影响力、权威度、活跃度、（兴趣偏好一致性）
     * 源数据：
     * name / sex / birthday / address / focusNumber / fansNumber
     * / age / friendNumber / replayNumber / clickNumber / loginNumber
     * / introduction / registrationTime / educationBackground / career
     * / elitePostNumber / score / onlineTime / fameDegree / lastLoginTime
     * / workUnit / updateArticalTime / phoneNumber / reputation / getReward
     * / giveReward / isCertification / portraitURL / realName / sexualOrientation
     * / email / QQ / bloodType / isBigV
     * @param authorEntity
     * @param feature
     * @return
     */
    public Feature getAuthorFeatures(AuthorEntity authorEntity, Feature feature){
        //影响力：Num + 是否大V + 得分
        Double DISSEM_influence = 0.0;
        //权威度： 基本信息是否全
        Double DISSEM_authority = 0.0;
        //活跃度： Num
        Double DISSEM_liveness = 0.0;
        //姓名
        String name = "";
        if (authorEntity.getName() != null){
            name = authorEntity.getName();
        }
        //表示量级
        Integer number = 0;
        //表示基本信息
        Integer info = 0;

        //精华量
        Integer elitePostNumber = 0;
        if (authorEntity.getElitePostNumber() != null){ elitePostNumber = authorEntity.getElitePostNumber(); }
        //得分
        Integer score = 0;
        if (authorEntity.getScore() != null){ score = authorEntity.getScore(); }
        //关注量
        Integer focusNumber = 0;
        if (authorEntity.getFocusNumber() != null){ focusNumber = authorEntity.getFocusNumber(); }
        //粉丝量
        Integer fansNumber = 0;
        if (authorEntity.getFansNumber() != null){ fansNumber = authorEntity.getFansNumber(); }
        //好友量
        Integer friendNumber = 0;
        if (authorEntity.getFriendNumber() != null){ friendNumber = authorEntity.getFriendNumber(); }
        //好友量
        Integer replayNumber = 0;
        if (authorEntity.getReplayNumber() != null){ replayNumber = authorEntity.getReplayNumber(); }
        //点击量
        Integer clickNumber = 0;
        if (authorEntity.getClickNumber() != null){ clickNumber = authorEntity.getClickNumber(); }
        //登陆量
        Integer loginNumber = 0;
        if (authorEntity.getLoginNumber() != null){ loginNumber = authorEntity.getLoginNumber(); }
        //名望值
        Integer fameDegree = 0;
        if (authorEntity.getFameDegree() != null){ fameDegree = authorEntity.getFameDegree(); }
        //尊望值
        Integer reputation = 0;
        if (authorEntity.getReputation() != null){ reputation = authorEntity.getReputation(); }
        //获得打赏
        Integer getReward = 0;
        if (authorEntity.getGetReward() != null){ getReward = authorEntity.getGetReward(); }
        //给予打赏
        Integer giveReward = 0;
        if (authorEntity.getGiveReward() != null){ giveReward = authorEntity.getGiveReward(); }
        // number量级求和
        number = elitePostNumber + score + focusNumber + fansNumber + friendNumber + replayNumber
                + clickNumber + loginNumber + fameDegree + reputation + getReward + giveReward;

        //年龄
        Integer age = 0;
        if (authorEntity.getAge() != null){ age = authorEntity.getAge(); }
        //是否认证
        Integer isCertification = 0;
        if (authorEntity.getIsCertification() != null){ isCertification = (int)authorEntity.getIsCertification(); }
        //性别
        Integer sex = 0;
        if (authorEntity.getSex() != null){ sex = 1; }
        //生日
        Integer birthday = 0;
        if (authorEntity.getSex() != null){ birthday = 1; }
        //地址
        Integer address = 0;
        if (authorEntity.getAddress() != null){ address = 1; }
        //介绍
        Integer introduction = 0;
        if (authorEntity.getIntroduction() != null){ introduction = 1; }
        //注册时间
        Integer registrationTime = 0;
        if (authorEntity.getRegistrationTime() != null){ registrationTime = 1; }
        //教育背景
        Integer educationBackground = 0;
        if (authorEntity.getEducationBackground() != null){ educationBackground = 1; }
        //职业生涯
        Integer career = 0;
        if (authorEntity.getCareer() != null){ career = 1; }
        //在线时长
        Integer onlineTime = 0;
        if (authorEntity.getOnlineTime() != null){ onlineTime = 1; }
        //最近登陆时间
        Integer lastLoginTime = 0;
        if (authorEntity.getLastLoginTime() != null){ lastLoginTime = 1; }
        //工作单位
        Integer workUnit = 0;
        if (authorEntity.getWorkUnit() != null){ workUnit = 1; }
        //更新文章时间
        Integer updateArticalTime = 0;
        if (authorEntity.getUpdateArticalTime() != null){ updateArticalTime = 1; }
        //手机号码
        Integer phoneNumber = 0;
        if (authorEntity.getPhoneNumber() != null){ phoneNumber = 1; }
        //头像链接
        Integer portraitURL = 0;
        if (authorEntity.getPortraitUrl() != null){ portraitURL = 1; }
        //真实姓名
        Integer realName = 0;
        if (authorEntity.getRealName() != null){ realName = 1; }
        //性取向
        Integer sexualOrientation = 0;
        if (authorEntity.getSexualOrientation() != null){ sexualOrientation = 1; }
        //邮箱
        Integer email = 0;
        if (authorEntity.getEmail() != null){ email = 1; }
        //QQ
        Integer QQ = 0;
        if (authorEntity.getQq() != null){ QQ = 1; }
        //血型
        Integer bloodType = 0;
        if (authorEntity.getBloodType() != null){ bloodType = 1; }
        //是否大V
        Integer isBigV = 0;
        if (authorEntity.getEmail() != null){ isBigV = 1; }
        info = age + isCertification + sex + birthday + address + introduction + registrationTime + educationBackground
                + career + onlineTime + lastLoginTime + workUnit + updateArticalTime + phoneNumber + portraitURL + realName
                + sexualOrientation + email + QQ + bloodType + isBigV;

        //影响力：Num + 是否大V + 得分
        DISSEM_influence = (double)(number + isBigV * 10 + score);
        //权威度： 基本信息是否全
        DISSEM_authority = (double)info * 5;
        //活跃度： Num
        DISSEM_liveness = (double)number;

        //设置作者特征
        feature.setDISSEM_influence(DISSEM_influence);
        feature.setDISSEM_authority(DISSEM_authority);
        feature.setDISSEM_liveness(DISSEM_liveness);
        return feature;
    }

    /**
     * 获得评论的特征：
     * 支持度、情感值、权威度
     * likeNumber / content / commentTime / replayNumber
     * @param articleCommentEntity
     * @param feature
     * @return
     */
    public Feature getArticleFeatures(ArticleCommentEntity articleCommentEntity, Feature feature){
        double REPLY_support = 0.0;
        double REPLY_sentiment = 0.0;
        double REPLY_authority = 0.0;
        Integer likeNumber = 0;
        Integer replayNumber = 0;
        String content = "";
        String commentTime = "";
        if (articleCommentEntity.getLikeNumber() != null){ likeNumber = articleCommentEntity.getLikeNumber();}
        if (articleCommentEntity.getReplayNumber() != null){ likeNumber = articleCommentEntity.getReplayNumber();}
        if (articleCommentEntity.getContent() != null){ content = articleCommentEntity.getContent();}
        if (articleCommentEntity.getCommentTime() != null){ commentTime = articleCommentEntity.getCommentTime();}
        REPLY_support = likeNumber;
        Double title_scores[] = SentimentUtil.sentimentScore(content);
        //情感值
        REPLY_sentiment = title_scores[2];
        //权威度有待考量
        REPLY_authority = likeNumber + replayNumber;

        feature.setREPLY_support(REPLY_support);
        feature.setREPLY_authority(REPLY_authority);
        feature.setREPLY_sentiment(REPLY_sentiment);
        return feature;
    }

    /**
     * 获得媒介特征：受欢迎度p、重要度i、活跃度l
     * @param articleCommentEntity
     */
    public Feature getMediaFeatures(ArticleCommentEntity articleCommentEntity, Feature feature){
        String tableName = articleCommentEntity.getArticleEntity().getWebsiteEntity().getWebsiteName();
        Double[] oneMedia = getMediaFeaturesAndIntegration(tableName);
        feature.setMEDIA_popularity(oneMedia[0]);
        feature.setMEDIA_importance(oneMedia[1]);
        feature.setMEDIA_liveness(oneMedia[2]);
        return feature;
    }

    /**
     * 获得媒介特征：受欢迎度p、重要度i、活跃度l
     * @param tableName
     */
    public static Double[] getMediaFeaturesAndIntegration(String tableName) {
        //流行度、重要性、活跃度
        //Double[] {Alexa, PR值, 活跃度(用户总数*日活量/月活量)}
        HashMap<String, Double[]> mediaFeatureMap = new HashMap<>();
        mediaFeatureMap.put("bbs_china_post",   new Double[]{-Math.log1p(58.0)+10, 6.0, Math.log1p(97500)});
        mediaFeatureMap.put("bbs_mop_post",     new Double[]{-Math.log1p(1099.0)+10, 7.0, Math.log1p(57750)});
        mediaFeatureMap.put("bbs_news_post",    new Double[]{-Math.log1p(128.0)+10, 9.0, Math.log1p(47250)});
        mediaFeatureMap.put("bbs_people_post",  new Double[]{-Math.log1p(38.0)+10, 7.0, Math.log1p(2242500)});
        mediaFeatureMap.put("bbs_sohu_post",    new Double[]{-Math.log1p(3.0)+10, 8.0, Math.log1p(32025000)});
        mediaFeatureMap.put("bbs_tianya_post",  new Double[]{-Math.log1p(34.0)+10, 7.0, Math.log1p(5340000)});
        mediaFeatureMap.put("bbs_xici_post",    new Double[]{-Math.log1p(535.0)+10, 7.0, Math.log1p(64500)});
        mediaFeatureMap.put("blogchina_blog",   new Double[]{-Math.log1p(2406.0)+10, 6.0, Math.log1p(2250)});
        mediaFeatureMap.put("blog_163_post",    new Double[]{-Math.log1p(13.0)+10, 7.0, Math.log1p(1177500)});
        mediaFeatureMap.put("blog_sina_post",   new Double[]{-Math.log1p(4.0)+10, 7.0, Math.log1p(30150000)});
        mediaFeatureMap.put("douban_group_post",new Double[]{-Math.log1p(12.0)+10, 7.0, Math.log1p(690000)});
        mediaFeatureMap.put("kdnet_post",       new Double[]{-Math.log1p(653.0)+10, 6.0, Math.log1p(73500)});
        mediaFeatureMap.put("moe_news",         new Double[]{-Math.log1p(2895.0)+10, 9.0, Math.log1p(12000)});
        mediaFeatureMap.put("news_sanqin",      new Double[]{-Math.log1p(10304.0)+10, 6.0, Math.log1p(2250)});
        mediaFeatureMap.put("news_sina",        new Double[]{-Math.log1p(4.0)+10, 8.0, Math.log1p(30150000)});
        mediaFeatureMap.put("people_news",      new Double[]{-Math.log1p(38.0)+10, 9.0, Math.log1p(2242500)});
        mediaFeatureMap.put("weibo_post",       new Double[]{-Math.log1p(33.0)+10, 9.0, Math.log1p(25050000)});
        mediaFeatureMap.put("xinhua_news",      new Double[]{-Math.log1p(128.0)+10, 9.0, Math.log1p(3382500)});
        mediaFeatureMap.put("xinwen110_news",   new Double[]{-Math.log1p(5000.0)+10, 4.0, Math.log1p(1000)});
        Double[] oneMedia = mediaFeatureMap.get(tableName);
        return oneMedia;
    }

}
