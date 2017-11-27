package com.sicdlib.entity;

import com.hankcs.hanlp.corpus.tag.Nature;

/**
 * Created by DeMH on 2017/7/6.
 */
public class NamedEntityInfo {
    //网站名称
    private String websiteName;
    //实体命名词性
    private Nature nature;
    //实体名称
    private String name;
    //实体数量
    private Integer count;

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
