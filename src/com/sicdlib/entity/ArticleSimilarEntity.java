package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/21.
 */
public class ArticleSimilarEntity {
    private String articleSimilarId;
    private String similarDegree;

    public String getArticleSimilarId() {
        return articleSimilarId;
    }

    public void setArticleSimilarId(String articleSimilarId) {
        this.articleSimilarId = articleSimilarId;
    }

    public String getSimilarDegree() {
        return similarDegree;
    }

    public void setSimilarDegree(String similarDegree) {
        this.similarDegree = similarDegree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleSimilarEntity that = (ArticleSimilarEntity) o;

        if (articleSimilarId != null ? !articleSimilarId.equals(that.articleSimilarId) : that.articleSimilarId != null)
            return false;
        if (similarDegree != null ? !similarDegree.equals(that.similarDegree) : that.similarDegree != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleSimilarId != null ? articleSimilarId.hashCode() : 0;
        result = 31 * result + (similarDegree != null ? similarDegree.hashCode() : 0);
        return result;
    }
}
