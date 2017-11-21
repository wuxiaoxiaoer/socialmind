package com.sicdlib.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zcx on 2017/11/14.
 */
public class ConfigitemEntity {
    private String configItemId;
    private String displayName;
    private String name;
    private String type;
    private String note;
    private String defaultValue;

    /** 表之间的映射：一个配置项可以出现在多个配置——配置项中间表 */
    @JsonIgnore
    private Set<ConfigitemEntity> configConfigItems = new HashSet<ConfigitemEntity>();

    public Set<ConfigitemEntity> getConfigConfigItems() {
        return configConfigItems;
    }

    public void setConfigConfigItems(Set<ConfigitemEntity> configConfigItems) {
        this.configConfigItems = configConfigItems;
    }

    public String getConfigItemId() {
        return configItemId;
    }

    public void setConfigItemId(String configItemId) {
        this.configItemId = configItemId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigitemEntity that = (ConfigitemEntity) o;

        if (configItemId != null ? !configItemId.equals(that.configItemId) : that.configItemId != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = configItemId != null ? configItemId.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
