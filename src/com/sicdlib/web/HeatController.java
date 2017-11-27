package com.sicdlib.web;



import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.IndicatorValueEntity;
import com.sicdlib.service.HeatService;
import com.sicdlib.service.LogService;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcx on 2017/11/9.
 */
@Controller
@RequestMapping("/*")
public class HeatController  {


    @Autowired(required=true)
    private HeatService heatService;


    /**
     * 热度
     */
    @RequestMapping("heatIndex")
    public String heatIndex(Model model){

        return "/WEB-INF/foreground/heatIndex";
    }


    /**
     * 跳转热度图
     */
    @RequestMapping("heatGraph")
    public String heatGraph(Model model){


        List<IndicatorValueEntity> heatList = heatService.getHeatList("1");
        System.out.println( "############heatList .size()###########"+heatList .size());

        List<String> date=new ArrayList<String>();

        List<String> heat=new ArrayList<String>();

        for(int i=0;i<heatList.size();i++){

            date.add(heatList.get(i).getStartTime());
            heat.add(heatList.get(i).getIndicatorValue());

        }

        model.addAttribute("date", JSON.toJSONString(date));
        model.addAttribute("heat", JSON.toJSONString(heat));

        List<IndicatorValueEntity> mheatList = heatService.getHeatListInSex("1","男");

        List<IndicatorValueEntity> fheatList = heatService.getHeatListInSex("1","女");

        List<String> sexdate=new ArrayList<String>();

        List<String> mheat=new ArrayList<String>();
        List<String> fheat=new ArrayList<String>();

        for(int i=0;i<mheatList.size();i++){

            sexdate.add(mheatList.get(i).getStartTime());
            mheat.add(heatList.get(i).getIndicatorValue());

        }
        for(int i=0;i<fheatList.size();i++){

            fheat.add(heatList.get(i).getIndicatorValue());

        }

        model.addAttribute("sexdate", JSON.toJSONString(sexdate));
        model.addAttribute("mheat", JSON.toJSONString(mheat));
        model.addAttribute("fheat", JSON.toJSONString(fheat));

        return "/WEB-INF/foreground/heatGraph";
    }



}
