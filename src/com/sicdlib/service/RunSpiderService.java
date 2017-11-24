package com.sicdlib.service;

import com.google.gson.Gson;
import com.jcraft.jsch.Session;
import com.sicdlib.entity.ConfigConfigitemEntity;
import com.sicdlib.entity.Constant;
import com.sicdlib.entity.SpiderConfigEntity;
import com.sicdlib.entity.SpiderInfoEntity;
import com.sicdlib.util.JSCHUtil.ExecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by YH on 2017/5/9.
 */
@Service()
public class RunSpiderService {

    @Autowired
    private SpiderService spiderService;
    @Autowired
    private SpiderConfigService spiderConfigService;
    @Autowired
    private SCHSessionService schSessionService;


    public String runSpider(String spiderID, String configID) throws Exception {

        //获得爬虫和爬虫配置
        SpiderInfoEntity spiderInfo = spiderService.load(spiderID);
        SpiderConfigEntity spiderConfig = spiderConfigService.load(configID);

        //获取爬虫的py文件位置
        String spiderSource = spiderInfo.getSpiderSourcePath();

        //获取爬虫的配置条目信息
        Set<ConfigConfigitemEntity> configItemSet=spiderConfig.getConfigItems();
        List<ConfigConfigitemEntity> configItemList = new ArrayList<ConfigConfigitemEntity>();
        //转换成List
        configItemList.addAll(configItemSet);

        //configMap保存配置项的名字和值，resultMap保存配置的信息
        Map<String, String> configMap = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();

        //循环此List并放入Map
        for(ConfigConfigitemEntity c: configItemList) {
            String name =c.getConfigItem().getName();
            String value = c.getConfigItemValue();
            configMap.put(name, value);
        }

        resultMap.put("name", spiderInfo.getSpiderName());
        resultMap.put("logPath", spiderConfig.getLogPath() + "scrapy.log");
       // resultMap.put("filePath", spiderConfig.getFilePath());
        resultMap.put("configs", configMap);

        Gson gson = new Gson();
        String resultJSON = gson.toJson(resultMap).replace("\"", "\\\"");
        Session session = schSessionService.getSession(Constant.VM.VM01);

        //在爬虫目录中创建文件，写入配置ID
        String catCommand = "echo \"" + resultJSON + "\" > " + spiderSource + "config\n";
        String mkdirCommand = "mkdir -p " + spiderConfig.getLogPath() + " " + spiderConfig.getFilePath() + "\n";
        String runCommand = "python3 " + spiderSource + "run.py > log.log 2>&1 & \n";
        String catResult = "";
        String result;
        try {
            catResult = ExecUtils.getInstance().execCmd(session, catCommand);// 多条命令之间以;分隔
            catResult = ExecUtils.getInstance().execCmd(session, mkdirCommand);// 多条命令之间以;分隔
            catResult = ExecUtils.getInstance().execCmd(session, runCommand);// 多条命令之间以;分隔
            result = catResult;
        } catch (Exception e) {
            System.out.println(e + catResult);
            result = "ERROR";

        } finally {
            ExecUtils.getInstance().clear(session);
        }

        return result;
    }
}
