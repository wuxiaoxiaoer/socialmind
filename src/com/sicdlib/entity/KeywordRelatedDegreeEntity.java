package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/21.
 */
public class KeywordRelatedDegreeEntity {
    private String keywordDegreeId;
    private Double relatedDegree;
    /*表之间映射*/
    private KeywordEntity keywordEntityOne;
    private KeywordEntity keywordEntityTwo;

    public String getKeywordDegreeId() {
        return keywordDegreeId;
    }

    public void setKeywordDegreeId(String keywordDegreeId) {
        this.keywordDegreeId = keywordDegreeId;
    }

    public Double getRelatedDegree() {
        return relatedDegree;
    }

    public void setRelatedDegree(Double relatedDegree) {
        this.relatedDegree = relatedDegree;
    }

    public KeywordEntity getKeywordEntityOne() {
        return keywordEntityOne;
    }

    public void setKeywordEntityOne(KeywordEntity keywordEntityOne) {
        this.keywordEntityOne = keywordEntityOne;
    }

    public KeywordEntity getKeywordEntityTwo() {
        return keywordEntityTwo;
    }

    public void setKeywordEntityTwo(KeywordEntity keywordEntityTwo) {
        this.keywordEntityTwo = keywordEntityTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeywordRelatedDegreeEntity that = (KeywordRelatedDegreeEntity) o;

        if (keywordDegreeId != null ? !keywordDegreeId.equals(that.keywordDegreeId) : that.keywordDegreeId != null)
            return false;
        if (relatedDegree != null ? !relatedDegree.equals(that.relatedDegree) : that.relatedDegree != null)
            return false;
        if (keywordEntityOne != null ? !keywordEntityOne.equals(that.keywordEntityOne) : that.keywordEntityOne != null)
            return false;
        return keywordEntityTwo != null ? keywordEntityTwo.equals(that.keywordEntityTwo) : that.keywordEntityTwo == null;
    }

    @Override
    public int hashCode() {
        int result = keywordDegreeId != null ? keywordDegreeId.hashCode() : 0;
        result = 31 * result + (relatedDegree != null ? relatedDegree.hashCode() : 0);
        result = 31 * result + (keywordEntityOne != null ? keywordEntityOne.hashCode() : 0);
        result = 31 * result + (keywordEntityTwo != null ? keywordEntityTwo.hashCode() : 0);
        return result;
    }
}
