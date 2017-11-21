package com.sicdlib.entity;

/**
 * Created by DeMH on 2017/11/21.
 */
public class KeywordEntity {
    private String keywordId;
    private String keyword;
    private String weight;

    public String getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(String keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeywordEntity that = (KeywordEntity) o;

        if (keywordId != null ? !keywordId.equals(that.keywordId) : that.keywordId != null) return false;
        if (keyword != null ? !keyword.equals(that.keyword) : that.keyword != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = keywordId != null ? keywordId.hashCode() : 0;
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
