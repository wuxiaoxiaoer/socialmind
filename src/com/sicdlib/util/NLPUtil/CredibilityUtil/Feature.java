package com.sicdlib.util.NLPUtil.CredibilityUtil;

import java.util.List;

/**
 * Created by DeMH on 2017/7/16.
 */
public class Feature {
    private String article_id;
    //信息传播者
    //  影响力
    private Double DISSEM_influence = 0.0;
    //  权威度
    private Double DISSEM_authority = 0.0;
    //  活跃度
    private Double DISSEM_liveness = 0.0;
    /**
     * 兴趣偏好一致性可单独写一篇论文
     */
    //  兴趣偏好一致性
    private Double DISSEM_preferSimi = 0.0;
    //内容特征
    private Double CONTENT_hots = 0.0;
    //  参与度
    private Double CONTENT_participant = 0.0;
    //  情感向量
    private List<Double> CONTENT_sentiments;
    //  情感值
    private Double CONTENT_sentiment = 0.0;
    //  主题向量（大小为10）
    private List<Double> CONTENT_topics;
    //媒介特征
    //  媒介流行度
    private Double MEDIA_popularity = 0.0;
    //  媒介重要性
    private Double MEDIA_importance = 0.0;
    //  媒介活跃度
    private Double MEDIA_liveness = 0.0;
    //评论特征
    //  评论支持度
    private Double REPLY_support = 0.0;
    //  评论情感值
    private Double REPLY_sentiment = 0.0;
    //  评论权威度
    private Double REPLY_authority = 0.0;

    public Double getREPLY_support() {
        return REPLY_support;
    }

    public void setREPLY_support(Double REPLY_support) {
        this.REPLY_support = REPLY_support;
    }

    public Double getCONTENT_sentiment() {
        return CONTENT_sentiment;
    }

    public void setCONTENT_sentiment(Double CONTENT_sentiment) {
        this.CONTENT_sentiment = CONTENT_sentiment;
    }

    public List<Double> getCONTENT_sentiments() {
        return CONTENT_sentiments;
    }

    public void setCONTENT_sentiments(List<Double> CONTENT_sentiments) {
        this.CONTENT_sentiments = CONTENT_sentiments;
    }

    public List<Double> getCONTENT_topics() {
        return CONTENT_topics;
    }

    public void setCONTENT_topics(List<Double> CONTENT_topics) {
        this.CONTENT_topics = CONTENT_topics;
    }

    public Double getREPLY_sentiment() {
        return REPLY_sentiment;
    }

    public void setREPLY_sentiment(Double REPLY_sentiment) {
        this.REPLY_sentiment = REPLY_sentiment;
    }

    public Double getREPLY_authority() {
        return REPLY_authority;
    }

    public void setREPLY_authority(Double REPLY_authority) {
        this.REPLY_authority = REPLY_authority;
    }

    public Double getMEDIA_popularity() {
        return MEDIA_popularity;
    }

    public void setMEDIA_popularity(Double MEDIA_popularity) {
        this.MEDIA_popularity = MEDIA_popularity;
    }

    public Double getMEDIA_importance() {
        return MEDIA_importance;
    }

    public void setMEDIA_importance(Double MEDIA_importance) {
        this.MEDIA_importance = MEDIA_importance;
    }

    public Double getMEDIA_liveness() {
        return MEDIA_liveness;
    }

    public void setMEDIA_liveness(Double MEDIA_liveness) {
        this.MEDIA_liveness = MEDIA_liveness;
    }

    public Double getCONTENT_hots() {
        return CONTENT_hots;
    }

    public void setCONTENT_hots(Double CONTENT_hots) {
        this.CONTENT_hots = CONTENT_hots;
    }

    public Double getCONTENT_participant() {
        return CONTENT_participant;
    }

    public void setCONTENT_participant(Double CONTENT_participant) {
        this.CONTENT_participant = CONTENT_participant;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public Double getDISSEM_influence() {
        return DISSEM_influence;
    }

    public void setDISSEM_influence(Double DISSEM_influence) {
        this.DISSEM_influence = DISSEM_influence;
    }

    public Double getDISSEM_authority() {
        return DISSEM_authority;
    }

    public void setDISSEM_authority(Double DISSEM_authority) {
        this.DISSEM_authority = DISSEM_authority;
    }

    public Double getDISSEM_liveness() {
        return DISSEM_liveness;
    }

    public void setDISSEM_liveness(Double DISSEM_liveness) {
        this.DISSEM_liveness = DISSEM_liveness;
    }

    public Double getDISSEM_preferSimi() {
        return DISSEM_preferSimi;
    }

    public void setDISSEM_preferSimi(Double DISSEM_preferSimi) {
        this.DISSEM_preferSimi = DISSEM_preferSimi;
    }
}
