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
        List<String> date=new ArrayList<String>();
        List<String> heat=new ArrayList<String>();

        for(int i=0;i<heatList.size();i++){
            date.add(heatList.get(i).getStartTime());
            heat.add(heatList.get(i).getIndicatorValue());
        }

        List<IndicatorValueEntity> mheatList = heatService.getHeatListInSex("1","男");
        List<IndicatorValueEntity> fheatList = heatService.getHeatListInSex("1","女");
        List<String> sexdate=new ArrayList<String>();
        List<String> mheat=new ArrayList<String>();
        List<String> fheat=new ArrayList<String>();

        for(int i=0;i<mheatList.size();i++){
            sexdate.add(mheatList.get(i).getStartTime());
            mheat.add(mheatList.get(i).getIndicatorValue());
        }
        for(int i=0;i<fheatList.size();i++){
            fheat.add(fheatList.get(i).getIndicatorValue());
        }



        List<IndicatorValueEntity> sheatList = heatService.getHeatListInProfession("1","学生");
        List<IndicatorValueEntity> theatList = heatService.getHeatListInProfession("1","教师");
        List<String> professiondate=new ArrayList<String>();

        List<String> sheat=new ArrayList<String>();
        List<String> theat=new ArrayList<String>();

        for(int i=0;i<sheatList.size();i++){
            professiondate.add(sheatList.get(i).getStartTime());
            sheat.add(sheatList.get(i).getIndicatorValue());
        }
        for(int i=0;i<theatList.size();i++){
            theat.add(theatList.get(i).getIndicatorValue());
        }


        List<IndicatorValueEntity> ageheatList = heatService.getHeatListInDomain("1","age");
        List<String> agedate=new ArrayList<String>();
        List<String> heat1=new ArrayList<String>();
        List<String> heat2=new ArrayList<String>();
        List<String> heat3=new ArrayList<String>();
        List<String> heat4=new ArrayList<String>();

        for(int i=0;i<ageheatList.size();i++){

            if(ageheatList.get(i).getDimensionValue().equals("18-25")){
                agedate.add(ageheatList.get(i).getStartTime());
                heat1.add(ageheatList.get(i).getIndicatorValue());
            }

            if(ageheatList.get(i).getDimensionValue().equals("25-35")){
                heat2.add(ageheatList.get(i).getIndicatorValue());
            }

            if(ageheatList.get(i).getDimensionValue().equals("35-45")){
                heat3.add(ageheatList.get(i).getIndicatorValue());
            }

            if(ageheatList.get(i).getDimensionValue().equals("45以上")){
                heat4.add(ageheatList.get(i).getIndicatorValue());
            }


        }


        model.addAttribute("date", JSON.toJSONString(date));
        model.addAttribute("heat", JSON.toJSONString(heat));
        model.addAttribute("sexdate", JSON.toJSONString(sexdate));
        model.addAttribute("mheat", JSON.toJSONString(mheat));
        model.addAttribute("fheat", JSON.toJSONString(fheat));
        model.addAttribute("professiondate", JSON.toJSONString(professiondate));
        model.addAttribute("sheat", JSON.toJSONString(sheat));
        model.addAttribute("theat", JSON.toJSONString(theat));
        model.addAttribute("agedate", JSON.toJSONString(agedate));
        model.addAttribute("heat1", JSON.toJSONString(heat1));
        model.addAttribute("heat2", JSON.toJSONString(heat2));
        model.addAttribute("heat3", JSON.toJSONString(heat3));
        model.addAttribute("heat4", JSON.toJSONString(heat4));

        return "/WEB-INF/foreground/heatGraph";
    }



}
