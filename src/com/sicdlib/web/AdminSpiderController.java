package com.sicdlib.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zcx on 2017/11/10.
 */
@Controller
@RequestMapping("/*")
public class AdminSpiderController {

    @Autowired(required=true)
    private SpiderService spiderService;
    @Autowired(required=true)
    private WebsiteService websiteService;
    @Autowired(required=true)
    private SpiderConfigService spiderConfigService;
    @Autowired(required=true)
    private ConfigitemService configitemService;
    @Autowired(required=true)
    private ConfigConfigItemService configConfigitemService;

    /**
     * 爬虫列表
     */
    @RequestMapping("spiderList")
    public String spiderList(Model model){

        List<SpiderInfoEntity> spiderList=new ArrayList<SpiderInfoEntity>();
        List<WebsiteEntity> websiteList=new ArrayList<WebsiteEntity>();
        List<SpiderConfigEntity> configList=new ArrayList<SpiderConfigEntity>();
        spiderList=spiderService.loadAll();
        websiteList= websiteService.loadAll();

        configList=spiderConfigService.loadAll();


        //configmap中<配置ID,配置项Map<展示名，(配置——配置项)obj>>
        Map<String, Map<String,Object>> configmap = new HashMap<String, Map<String,Object>>();
        for(int i=0;i<configList.size();i++){
           // Map<String, Map<String,Object>> configmap = new HashMap<String, Map<String,Object>>();
            Map<String, Object> itemMap = new HashMap<String,Object>();
            Set<ConfigConfigitemEntity> items=configList.get(i).getConfigItems();
            for(ConfigConfigitemEntity c : items) {
                itemMap.put(c.getConfigItem().getDisplayName(), c.getConfigItemValue());
            }
            configmap.put(configList.get(i).getSpiderConfigId(),itemMap);
        }

        List<ConfigitemEntity> configItemList = new ArrayList<ConfigitemEntity>();
        configItemList = configitemService.loadAll();
        model.addAttribute("configItemList", configItemList);

        model.addAttribute("configmap",configmap);
        model.addAttribute("spiderList",spiderList);
        model.addAttribute("websiteList",websiteList);
        model.addAttribute("configList",configList);
        return "/WEB-INF/admin/spiderList";
    }

    /**
     * 爬虫配置列表
     */
    @RequestMapping("spiderConfigList")
    public String spiderConfigList(Model model){

        List<SpiderConfigEntity> spiderList=new ArrayList<SpiderConfigEntity>();


        return "/WEB-INF/admin/spiderConfig";
    }




    /**
     * 跳转到增加爬虫
     */
    @RequestMapping("toAddSpider")
    public String toAddSpider(Model model){

        List<WebsiteEntity> websiteList=new ArrayList<WebsiteEntity>();
        List<SpiderConfigEntity> configList=new ArrayList<SpiderConfigEntity>();
        websiteList= websiteService.loadAll();
        configList=spiderConfigService.loadAll();
        model.addAttribute("websiteList",websiteList);
        model.addAttribute("configList",configList);
        return "/WEB-INF/admin/addSpider";
    }


    /**
     * 增加爬虫
     */
    @RequestMapping("addSpider")
    public String addSpider( HttpServletRequest req) {

        String spiderName = req.getParameter("spiderName");
        String websiteId = req.getParameter("websiteId");
        String fileName = req.getParameter("fileName");
        String spiderConfigId = req.getParameter("spiderConfigId");

        SpiderInfoEntity spider = new SpiderInfoEntity();
        spider.setSpiderId(UUIDUtil.getUUID().toString());
        spider.setWebsiteId(websiteId);
        spider.setSpiderName(spiderName);
        spider.setFileName(fileName);

        spider.setSpiderState("关闭");
        spider.setSpiderConfigId(spiderConfigId);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String addTime = sdf.format(d);
        spider.setAddTime(addTime);

        spiderService.saveOrUpdate(spider);
        return "redirect:/spiderList";
    }
     /**
     * 删除爬虫
     * @return
     * @throws IOException
     */
    @RequestMapping("deleteSpider")
    public void deleteSpider(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String spiderId = req.getParameter("spiderId");
        PrintWriter out = res.getWriter();
        spiderService.remove(spiderId);
        out.print("success");
    }



    /**
     * 修改爬虫
     * @return
     * @throws IOException
     */
    @RequestMapping("editSpider")
    public String  editSpider(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String spiderId = req.getParameter("id");
        System.out.print("####"+spiderId);

        SpiderInfoEntity spider = spiderService.load(spiderId);
        System.out.print("####"+spider.getSpiderId());

        String spiderName = req.getParameter("spiderNewName");
        String configId = req.getParameter("configId");

        spider.setSpiderName(spiderName);
        spider.setSpiderConfigId(configId);
        spiderService.saveOrUpdate(spider);

        return "redirect:/spiderList";
    }


    /**
     * 管理员更改爬虫状态
     */
    @RequestMapping("updateState")
    public void updateState(HttpServletRequest req, HttpServletResponse res) throws IOException{

        String spiderId= req.getParameter("spiderId");
        SpiderInfoEntity spider=spiderService.load(spiderId);
        String state=spider.getSpiderState();
        if(state.equals("开启"))
        {
            spider.setSpiderState("暂停");
        }else {
            spider.setSpiderState("开启");
        }
        spiderService.saveOrUpdate(spider);
        state=spider.getSpiderState();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("state",state);
        Gson gson = new Gson();
        try {
            res.setContentType( "text/html;charset=UTF-8");
            res.getWriter().write( gson.toJson(map));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * 跳转到增加配置
     */
    @RequestMapping("toAddConfiger")
    public String toAddConfiger(Model model){

        List<ConfigitemEntity> configItemList = new ArrayList<ConfigitemEntity>();
        configItemList = configitemService.loadAll();
        model.addAttribute("configItemList", configItemList);
        return "/WEB-INF/admin/addConfig";
    }



    /**
     * 增加配置
     */
    @RequestMapping("addConfig")
    public String addConfig( HttpServletRequest req) {

        String configName = req.getParameter("configName");
        SpiderConfigEntity spiderConfig = new SpiderConfigEntity();

        //String spiderRunTime = req.getParameter("runTime");
       // String logPath = req.getParameter("logPath");
        //Integer spiderFrequency = Integer.parseInt(req.getParameter("frequence"));
        spiderConfig.setSpiderConfigId(UUIDUtil.getUUID().toString());
        spiderConfig.setConfigName(configName);
        //spiderConfig.setSpiderRunTime(spiderRunTime);
        //spiderConfig.setLogPath(logPath);
        //spiderConfig.setSpiderFrequency(spiderFrequency);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String addTime = sdf.format(d);
        spiderConfig.setConfigTime(addTime);
        spiderConfigService.saveOrUpdate(spiderConfig);

        List<ConfigitemEntity> configItemList = new ArrayList<ConfigitemEntity>();
        configItemList = configitemService.loadAll();

        for(int i=0;i<configItemList.size();i++) {
            ConfigConfigitemEntity configConfigItem = new ConfigConfigitemEntity();
            String value=req.getParameter(configItemList.get(i).getConfigItemId());
            configConfigItem.setSpiderConfigItemId(UUIDUtil.getUUID().toString());
            configConfigItem.setConfigItem(configitemService.load(configItemList.get(i).getConfigItemId()));
            configConfigItem.setConfigItemValue(value);
            //configConfigItem.setSpiderConfig(spiderConfig);
            configConfigItem.setSpiderConfigId(spiderConfig.getSpiderConfigId());

            configConfigitemService.saveOrUpdate(configConfigItem);
        }


        return "redirect:/spiderList";
    }

    /**
     * 删除配置
     * @return
     * @throws IOException
     */
    @RequestMapping("deleteConfig")
    public void deleteConfig(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String spiderConfigId = req.getParameter("spiderConfigId");
        SpiderConfigEntity spiderConfig = spiderConfigService.load(spiderConfigId);
        Set<ConfigConfigitemEntity> items= spiderConfig.getConfigItems();

        for(ConfigConfigitemEntity c : items) {
            configConfigitemService.remove(c.getSpiderConfigItemId());
        }
        spiderConfigService.remove(spiderConfigId);

        PrintWriter out = res.getWriter();
        out.print("success");
    }




    /**
     * 查找配置
     * @return
     * @throws IOException
     */
    @RequestMapping("searchSpiderConfig")
    public void  searchSpiderConfig(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String spiderConfigId = req.getParameter("spiderConfigId");
        SpiderConfigEntity spiderConfig = spiderConfigService.load(spiderConfigId);

        Set<ConfigConfigitemEntity> items= spiderConfig.getConfigItems();


        System.out.println("items####"+items.size()) ;

        //List<ConfigitemEntity> configItem= new ArrayList<ConfigitemEntity>();

        //configItem = configitemService.loadAll();


        Map<String, Object> itemMap = new HashMap<String,Object>();


        Iterator<ConfigConfigitemEntity> iter = items.iterator();
        while (iter.hasNext()){
            ConfigConfigitemEntity conConfigitem = iter.next();
            System.out.println("值：" + conConfigitem.getConfigItemValue());
            System.out.println("itemID：" + conConfigitem.getConfigItem().getConfigItemId());
            System.out.println("configID：" + conConfigitem.getSpiderConfigId());
            itemMap.put(conConfigitem.getConfigItem().getConfigItemId(),conConfigitem.getConfigItemValue());
           // configItem.add(conConfigitem.getConfigItem());

        }

        System.out.println("####itemMap.size()####"+itemMap.size() ) ;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("configName", spiderConfig.getConfigName());
        map.put("configId", spiderConfig.getSpiderConfigId());
        map.put("itemMap", itemMap);
       // map.put("configItem", configItem);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        //Gson gson = new Gson();
        try {
            /*设置编码格式，返回结果
            * ***/
            res.setContentType( "text/html;charset=UTF-8");
            Writer writer = res.getWriter();
            writer.write(gson.toJson(map));
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 修改配置
     */
    @RequestMapping("editConfig")
    public String editConfig( HttpServletRequest req) {


        String configName = req.getParameter("configName");
        String spiderConfigId = req.getParameter("sConfigId");

        SpiderConfigEntity spiderConfig = spiderConfigService.load(spiderConfigId);

        //spiderConfig.setSpiderConfigId(UUIDUtil.getUUID().toString());
        spiderConfig.setConfigName(configName);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String addTime = sdf.format(d);
        spiderConfig.setConfigTime(addTime);
        spiderConfigService.saveOrUpdate(spiderConfig);


        List<ConfigitemEntity> configItemList = new ArrayList<ConfigitemEntity>();
        configItemList = configitemService.loadAll();

        for(int i=0;i<configItemList.size();i++) {
            ConfigConfigitemEntity configConfigItem = new ConfigConfigitemEntity();
            String value=req.getParameter(configItemList.get(i).getConfigItemId());
            configConfigItem.setSpiderConfigItemId(UUIDUtil.getUUID().toString());
            configConfigItem.setConfigItem(configitemService.load(configItemList.get(i).getConfigItemId()));
            configConfigItem.setConfigItemValue(value);
            //configConfigItem.setSpiderConfig(spiderConfig);
            configConfigItem.setSpiderConfigId(spiderConfig.getSpiderConfigId());

            configConfigitemService.saveOrUpdate(configConfigItem);
        }


        return "redirect:/spiderList";
    }


}
