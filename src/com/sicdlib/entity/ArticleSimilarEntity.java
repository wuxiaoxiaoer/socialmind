package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/21.
 */
public class ArticleSimilarEntity {
    private String articleSimilarId;
    private double similarDegree;
    /*表之间的映射*/
    private ArticleEntity articleEntityOne;
    private ArticleEntity articleEntityTwo;

    public String getArticleSimilarId() {
        return articleSimilarId;
    }

    public void setArticleSimilarId(String articleSimilarId) {
        this.articleSimilarId = articleSimilarId;
    }

    public double getSimilarDegree() {
        return similarDegree;
    }

    public void setSimilarDegree(double similarDegree) {
        this.similarDegree = similarDegree;
    }

    public ArticleEntity getArticleEntityOne() {
        return articleEntityOne;
    }

    public void setArticleEntityOne(ArticleEntity articleEntityOne) {
        this.articleEntityOne = articleEntityOne;
    }

    public ArticleEntity getArticleEntityTwo() {
        return articleEntityTwo;
    }

    public void setArticleEntityTwo(ArticleEntity articleEntityTwo) {
        this.articleEntityTwo = articleEntityTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleSimilarEntity that = (ArticleSimilarEntity) o;

        if (Double.compare(that.similarDegree, similarDegree) != 0) return false;
        if (articleSimilarId != null ? !articleSimilarId.equals(that.articleSimilarId) : that.articleSimilarId != null)
            return false;
        if (articleEntityOne != null ? !articleEntityOne.equals(that.articleEntityOne) : that.articleEntityOne != null)
            return false;
        return articleEntityTwo != null ? articleEntityTwo.equals(that.articleEntityTwo) : that.articleEntityTwo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = articleSimilarId != null ? articleSimilarId.hashCode() : 0;
        temp = Double.doubleToLongBits(similarDegree);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (articleEntityOne != null ? articleEntityOne.hashCode() : 0);
        result = 31 * result + (articleEntityTwo != null ? articleEntityTwo.hashCode() : 0);
        return result;
    }
}
