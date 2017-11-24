package com.sicdlib.entity;

/**
 * Created by Yh on 2016/9/12.
 */
//数据库中的常量定义
public class Constant {
    public static final String ROLE_NORMAL_USER = "USER";
    public static final String ROLE_ADMINISTRATOR = "ADMIN";
    public static final String ROLE_SUPER_ADMINISTRATOR = "SUPADMIN";
    public static final String GROUP_NORMAL_USER = "USERGROUP";
    public static final String PARAM_SEX = "USER_SEX";//用户性别字段
    public static final String SPIDER_LOG_BASE = "SPIDER_LOGS_PATH"; //爬虫日志的根目录
    public static final String SPIDER_SOURCE_BASE = "SPIDER_SOURCE_DEST"; //爬虫源文件的根目录
    public static final String SPIDER_FILE_BASE = "SPIDER_FILES_PATH"; //爬虫图片文件的根目录
    public static final String SPIDER_STATUS_RUN = "SPIDER_STATUS_RUN"; //爬虫状态为正在运行
    public static final String SPIDER_STATUS_STOP = "SPIDER_STATUS_STOP"; //爬虫状态为停止
    public static final String EVENT_INTRODUCTION = "EVENT_INTRODUCTION"; //事件简介模板
    public static final String TREND_1 = "TREND_1"; //事件的第一等级趋势
    public static final String TREND_2 = "TREND_2"; //事件的第二等级趋势
    /**
     * 数据字典中的网站类别字段
     */
    public static final String SITE_TYPE = "SITE_TYPE";

    public enum VM{
        VM00,
        VM01,
        VM02,
        VM03,
        VM04,
        VM05,
        VM06,
        VM07,
        VM08
    }
}
