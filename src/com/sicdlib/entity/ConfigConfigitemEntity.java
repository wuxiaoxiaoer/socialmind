package com.sicdlib.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by zcx on 2017/11/14.
 */
public class ConfigConfigitemEntity {
    private String spiderConfigItemId;
    private String spiderConfigId;
    private String configItemId;
    private String configItemValue;
    private String note;

    /** 表之间的映射：多个(配置_配置项)属于一个配置 */
//    private SpiderConfigEntity spiderConfig;


    /** 表之间的映射：多个(配置_配置项)属于一个配置项 */
    @JsonIgnore
    private ConfigitemEntity configItem;

    public String getSpiderConfigId() {
        return spiderConfigId;
    }

    public void setSpiderConfigId(String spiderConfigId) {
        this.spiderConfigId = spiderConfigId;
    }

//    public SpiderConfigEntity getSpiderConfig() {
//        return spiderConfig;
//    }
//
//    public void setSpiderConfig(SpiderConfigEntity spiderConfig) {
//        this.spiderConfig = spiderConfig;
//    }
//
    public ConfigitemEntity getConfigItem() {
        return configItem;
    }

    public void setConfigItem(ConfigitemEntity configItem) {
        this.configItem = configItem;
    }


    public String getSpiderConfigItemId() {
        return spiderConfigItemId;
    }

    public void setSpiderConfigItemId(String spiderConfigItemId) {
        this.spiderConfigItemId = spiderConfigItemId;
    }

    public String getConfigItemId() {
        return configItemId;
    }

    public void setConfigItemId(String configItemId) {
        this.configItemId = configItemId;
    }

    public String getConfigItemValue() {
        return configItemValue;
    }

    public void setConfigItemValue(String configItemValue) {
        this.configItemValue = configItemValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



}
