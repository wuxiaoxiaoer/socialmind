package com.sicdlib.entity;

public class ArticleLabeledEntity {
    private String id;
    private String articleId;
    private String isTrue;
    private String credType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }

    public String getCredType() {
        return credType;
    }

    public void setCredType(String credType) {
        this.credType = credType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleLabeledEntity that = (ArticleLabeledEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (articleId != null ? !articleId.equals(that.articleId) : that.articleId != null) return false;
        if (isTrue != null ? !isTrue.equals(that.isTrue) : that.isTrue != null) return false;
        if (credType != null ? !credType.equals(that.credType) : that.credType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (articleId != null ? articleId.hashCode() : 0);
        result = 31 * result + (isTrue != null ? isTrue.hashCode() : 0);
        result = 31 * result + (credType != null ? credType.hashCode() : 0);
        return result;
    }
}
