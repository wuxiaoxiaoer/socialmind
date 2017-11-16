package com.sicdlib.entity;import java.util.HashSet;import java.util.Set;/** * Created by DeMH on 2017/11/2. */public class ArticleEntity {    private String articleId;    private String sourceArticleId;    private String title;    private String keyWords;    private String content;    private String postTime;    private Integer commentNumber;    private Integer scanNumber;    private Integer participationNumber;    private Integer replyNumber;    private Integer likeNumber;    private Integer recommendNumber;    private Integer collectNumber;    private Integer giveReward;    private Integer transmitNumber;    private Integer trampleNumber;    private String newsResource;    private Double similarDegree;    /** 多个文章对应着一个对象 */    private ObjectEntity objectEntity;    /** 多个文章对应着一个网站 */    private WebsiteEntity websiteEntity;    /** 一个文章对应多个文章评论 */    private Set<ArticleCommentEntity> articleComments = new HashSet<>();    /** 多个文章对应一个作者 */    private AuthorEntity authorEntity;    public Set<ArticleCommentEntity> getArticleComments() {        return articleComments;    }    public void setArticleComments(Set<ArticleCommentEntity> articleComments) {        this.articleComments = articleComments;    }    public AuthorEntity getAuthorEntity() {        return authorEntity;    }    public void setAuthorEntity(AuthorEntity authorEntity) {        this.authorEntity = authorEntity;    }    public WebsiteEntity getWebsiteEntity() {        return websiteEntity;    }    public void setWebsiteEntity(WebsiteEntity websiteEntity) {        this.websiteEntity = websiteEntity;    }    public ObjectEntity getObjectEntity() {        return objectEntity;    }    public void setObjectEntity(ObjectEntity objectEntity) {        this.objectEntity = objectEntity;    }    public String getArticleId() {        return articleId;    }    public void setArticleId(String articleId) {        this.articleId = articleId;    }    public String getSourceArticleId() {        return sourceArticleId;    }    public void setSourceArticleId(String sourceArticleId) {        this.sourceArticleId = sourceArticleId;    }    public String getTitle() {        return title;    }    public void setTitle(String title) {        this.title = title;    }    public String getKeyWords() {        return keyWords;    }    public void setKeyWords(String keyWords) {        this.keyWords = keyWords;    }    public String getContent() {        return content;    }    public void setContent(String content) {        this.content = content;    }    public String getPostTime() {        return postTime;    }    public void setPostTime(String postTime) {        this.postTime = postTime;    }    public Integer getCommentNumber() {        return commentNumber;    }    public void setCommentNumber(Integer commentNumber) {        this.commentNumber = commentNumber;    }    public Integer getScanNumber() {        return scanNumber;    }    public void setScanNumber(Integer scanNumber) {        this.scanNumber = scanNumber;    }    public Integer getParticipationNumber() {        return participationNumber;    }    public void setParticipationNumber(Integer participationNumber) {        this.participationNumber = participationNumber;    }    public Integer getReplyNumber() {        return replyNumber;    }    public void setReplyNumber(Integer replyNumber) {        this.replyNumber = replyNumber;    }    public Integer getLikeNumber() {        return likeNumber;    }    public void setLikeNumber(Integer likeNumber) {        this.likeNumber = likeNumber;    }    public Integer getRecommendNumber() {        return recommendNumber;    }    public void setRecommendNumber(Integer recommendNumber) {        this.recommendNumber = recommendNumber;    }    public Integer getCollectNumber() {        return collectNumber;    }    public void setCollectNumber(Integer collectNumber) {        this.collectNumber = collectNumber;    }    public Integer getGiveReward() {        return giveReward;    }    public void setGiveReward(Integer giveReward) {        this.giveReward = giveReward;    }    public Integer getTransmitNumber() {        return transmitNumber;    }    public void setTransmitNumber(Integer transmitNumber) {        this.transmitNumber = transmitNumber;    }    public Integer getTrampleNumber() {        return trampleNumber;    }    public void setTrampleNumber(Integer trampleNumber) {        this.trampleNumber = trampleNumber;    }    public String getNewsResource() {        return newsResource;    }    public void setNewsResource(String newsResource) {        this.newsResource = newsResource;    }    public Double getSimilarDegree() {        return similarDegree;    }    public void setSimilarDegree(Double similarDegree) {        this.similarDegree = similarDegree;    }}