package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/21.
 */
public class KeywordRelatedDegreeEntity {
    private String keywordDegreeId;
    private String relatedDegree;

    public String getKeywordDegreeId() {
        return keywordDegreeId;
    }

    public void setKeywordDegreeId(String keywordDegreeId) {
        this.keywordDegreeId = keywordDegreeId;
    }

    public String getRelatedDegree() {
        return relatedDegree;
    }

    public void setRelatedDegree(String relatedDegree) {
        this.relatedDegree = relatedDegree;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = keywordDegreeId != null ? keywordDegreeId.hashCode() : 0;
        result = 31 * result + (relatedDegree != null ? relatedDegree.hashCode() : 0);
        return result;
    }
}
