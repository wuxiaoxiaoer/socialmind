package com.sicdlib.util.GraphUtil;

import com.sicdlib.service.ArticleEntityService;
import com.sicdlib.service.WebsiteEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Graph {
    @Autowired
    private WebsiteEntityService websiteEntityService;
    @Autowired
    private ArticleEntityService articleEntityService;

    public List<String> webs(String objectId){
        return websiteEntityService.findWebsitesByEvent(objectId);
    }

    public List<Map> timeList(String objectId){
        //1.找来源网站
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);
        //2.找每个网站下最早文章的时间
        List<Map> timeList = new ArrayList<>();
        for (int k = 0;k<webs.size();k++){
            String webName = webs.get(k).toString();
            Map map = new HashMap();
            map.put(k, articleEntityService.findFirstTimeByWeb(webName,objectId));
            timeList.add(map);
        }
        return timeList;
    }

    public double[][] transferWebs(String objectId){
        List webs = websiteEntityService.findWebsitesByEvent(objectId);
        List<Map> timeList = timeList(objectId);

        //构造矩阵，因为存放的是时间，所以将矩阵定为double类型,矩阵的行高为网站的数量
        double[][] timeMatrix = new double[webs.size()][webs.size()];
        for(int m =0;m<timeMatrix.length;m++){
            for (int n = 0;n<timeMatrix[m].length;n++){
                if(m ==n){
                    timeMatrix[m][n] =0;
                }else {
                    String time1 = timeList.get(m).get(m).toString();
                    String time2 = timeList.get(n).get(n).toString();
                    timeMatrix[m][n] =subTime(time1, time2);
                    if (timeMatrix[m][n]<0){
                        timeMatrix[m][n]=0;
                    }
                }
            }
        }
/*
        for (int i = 0; i < timeMatrix.length; i++) {
            for (int j = 0; j < timeMatrix[i].length; j++) {
                System.out.print(timeMatrix[i][j] + " ");
            }
        }*/
        return timeMatrix;


    }


    public List<Map> treeGraph(String objectId){
        //先遍历第一行，因为第一行代表最早的时间，如果有大于0的，分别记录大于0的行和列
        double[][] timeMatrix = transferWebs(objectId);
        List<String> webs = websiteEntityService.findWebsitesByEvent(objectId);
        List<Map> treeMap = new ArrayList<>();

        Map map = new HashMap();
        map.put("name",webs.get(0).toString());
        String children = "";
        if (webs(objectId).size()!=0){
            for (int j=1;j<timeMatrix[0].length;j++){
                double path = timeMatrix[0][j];
                recursion(timeMatrix,j,path);
                children = webs.get(j).toString();
                map.put("children",recursionPath(timeMatrix,j,children,objectId));
            }
        }
        System.out.println("children"+children);
        treeMap.add(map);
        return treeMap;
    }
    //递归求最小时间
    public double recursion(double[][] timeMatrix,int j,double path){
        for (int i=j+1;i<timeMatrix[j].length;i++){
            path+= timeMatrix[j][i];
            return recursion(timeMatrix,j,path);
        }
        return path;
    }

    //递归求最小路径
    public String recursionPath(double[][] timeMatrix,int j,String path,String objectId){
        for (int i=j+1;i<timeMatrix[j].length;i++){
            path+= webs(objectId).get(j).toString();
            return recursionPath(timeMatrix,j,path,objectId);
        }
        return path;
    }

    //计算时间差
    public double subTime(String time1,String time2){
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin= null;
        Date end = null;
        try {
            begin = dfs.parse(time1);
            end = dfs.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
        return between;

    }

    /*for (int j=1;j<timeMatrix[0].length;j++){

            if (timeMatrix[0][j]>0){
                for (int k=0;k<timeMatrix[j].length;k++){
                    Map map1 = new HashMap();
                    map1.put("name",webs.get(j).toString());
                    map1.put("children",webs.get(k).toString());
                    map.put("children",map);
                }
            }
            else {
                map.put("children",webs.get(j).toString());
            }
            *//*if (timeMatrix[0][j]>0&&timeMatrix[0][j]<min){
                min = timeMatrix[0][j];
            }
            treeMap.add(map);

        }*/

    /*
        long day1=between/(24*3600);
        long hour1=between%(24*3600)/3600;
        long minute1=between%3600/60;
        long second1=between%60/60;
        System.out.println(""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒");*/
}

