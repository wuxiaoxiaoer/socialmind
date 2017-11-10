package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/2.
 */
public class ArticleEntity {
    private String articleId;
    private String sourceArticleId;
    private String authorId;
    private String title;
    private String keyWords;
    private String content;
    private String postTime;
    private Integer commentNumber;
    private Integer scanNumber;
    private Integer participationNumber;
    private Integer replyNumber;
    private Integer likeNumber;
    private Integer recommendNumber;
    private Integer collectNumber;
    private Integer giveReward;
    private Integer transmitNumber;
    private Integer trampleNumber;
    private String newsResource;
    private Double similarDegree;
    private String websiteId;
    /** 多个文章对应这一个对象 */
    private ObjectEntity objectEntity;

    public ObjectEntity getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(ObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getSourceArticleId() {
        return sourceArticleId;
    }

    public void setSourceArticleId(String sourceArticleId) {
        this.sourceArticleId = sourceArticleId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getScanNumber() {
        return scanNumber;
    }

    public void setScanNumber(Integer scanNumber) {
        this.scanNumber = scanNumber;
    }

    public Integer getParticipationNumber() {
        return participationNumber;
    }

    public void setParticipationNumber(Integer participationNumber) {
        this.participationNumber = participationNumber;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getRecommendNumber() {
        return recommendNumber;
    }

    public void setRecommendNumber(Integer recommendNumber) {
        this.recommendNumber = recommendNumber;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Integer getGiveReward() {
        return giveReward;
    }

    public void setGiveReward(Integer giveReward) {
        this.giveReward = giveReward;
    }

    public Integer getTransmitNumber() {
        return transmitNumber;
    }

    public void setTransmitNumber(Integer transmitNumber) {
        this.transmitNumber = transmitNumber;
    }

    public Integer getTrampleNumber() {
        return trampleNumber;
    }

    public void setTrampleNumber(Integer trampleNumber) {
        this.trampleNumber = trampleNumber;
    }

    public String getNewsResource() {
        return newsResource;
    }

    public void setNewsResource(String newsResource) {
        this.newsResource = newsResource;
    }

    public Double getSimilarDegree() {
        return similarDegree;
    }

    public void setSimilarDegree(Double similarDegree) {
        this.similarDegree = similarDegree;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleEntity that = (ArticleEntity) o;

        if (articleId != null ? !articleId.equals(that.articleId) : that.articleId != null) return false;
        if (sourceArticleId != null ? !sourceArticleId.equals(that.sourceArticleId) : that.sourceArticleId != null)
            return false;
        if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (keyWords != null ? !keyWords.equals(that.keyWords) : that.keyWords != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (postTime != null ? !postTime.equals(that.postTime) : that.postTime != null) return false;
        if (commentNumber != null ? !commentNumber.equals(that.commentNumber) : that.commentNumber != null)
            return false;
        if (scanNumber != null ? !scanNumber.equals(that.scanNumber) : that.scanNumber != null) return false;
        if (participationNumber != null ? !participationNumber.equals(that.participationNumber) : that.participationNumber != null)
            return false;
        if (replyNumber != null ? !replyNumber.equals(that.replyNumber) : that.replyNumber != null) return false;
        if (likeNumber != null ? !likeNumber.equals(that.likeNumber) : that.likeNumber != null) return false;
        if (recommendNumber != null ? !recommendNumber.equals(that.recommendNumber) : that.recommendNumber != null)
            return false;
        if (collectNumber != null ? !collectNumber.equals(that.collectNumber) : that.collectNumber != null)
            return false;
        if (giveReward != null ? !giveReward.equals(that.giveReward) : that.giveReward != null) return false;
        if (transmitNumber != null ? !transmitNumber.equals(that.transmitNumber) : that.transmitNumber != null)
            return false;
        if (trampleNumber != null ? !trampleNumber.equals(that.trampleNumber) : that.trampleNumber != null)
            return false;
        if (newsResource != null ? !newsResource.equals(that.newsResource) : that.newsResource != null) return false;
        if (similarDegree != null ? !similarDegree.equals(that.similarDegree) : that.similarDegree != null)
            return false;
        if (websiteId != null ? !websiteId.equals(that.websiteId) : that.websiteId != null) return false;
        return objectEntity != null ? objectEntity.equals(that.objectEntity) : that.objectEntity == null;

    }

    @Override
    public int hashCode() {
        int result = articleId != null ? articleId.hashCode() : 0;
        result = 31 * result + (sourceArticleId != null ? sourceArticleId.hashCode() : 0);
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (keyWords != null ? keyWords.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (postTime != null ? postTime.hashCode() : 0);
        result = 31 * result + (commentNumber != null ? commentNumber.hashCode() : 0);
        result = 31 * result + (scanNumber != null ? scanNumber.hashCode() : 0);
        result = 31 * result + (participationNumber != null ? participationNumber.hashCode() : 0);
        result = 31 * result + (replyNumber != null ? replyNumber.hashCode() : 0);
        result = 31 * result + (likeNumber != null ? likeNumber.hashCode() : 0);
        result = 31 * result + (recommendNumber != null ? recommendNumber.hashCode() : 0);
        result = 31 * result + (collectNumber != null ? collectNumber.hashCode() : 0);
        result = 31 * result + (giveReward != null ? giveReward.hashCode() : 0);
        result = 31 * result + (transmitNumber != null ? transmitNumber.hashCode() : 0);
        result = 31 * result + (trampleNumber != null ? trampleNumber.hashCode() : 0);
        result = 31 * result + (newsResource != null ? newsResource.hashCode() : 0);
        result = 31 * result + (similarDegree != null ? similarDegree.hashCode() : 0);
        result = 31 * result + (websiteId != null ? websiteId.hashCode() : 0);
        result = 31 * result + (objectEntity != null ? objectEntity.hashCode() : 0);
        return result;
    }
}
