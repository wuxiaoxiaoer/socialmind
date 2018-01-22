package com.sicdlib.util;


import com.sicdlib.entity.errorLog;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class esUtil {

    private Client client;

    // private static List<Map> speedList=new ArrayList<Map>();//写到这里每次会重复插两遍


    private SortBuilder sortBuilder = SortBuilders.fieldSort("log_time")
            .order(SortOrder.DESC);


    //入口
    public Map<String,Object> esClient() {
        Map<String,Object> map=new HashMap<String,Object>();

        esUtil client = new esUtil();
        client.init();
        Map<String, List> peopleSpeedmap = client.querySpeed();
        Map levelMap=client.queryError();
//        Map sizeMap=client.querySize();

        map.put("peopleSpeedmap",peopleSpeedmap);
        map.put("levelMap",levelMap);
//        map.put("sizeMap",sizeMap);

        System.out.println(map.size());
        client.close();
        return map;

    }


    //创建连接
    public void init() {
        // on startup
        try {
            Settings settings = Settings.builder()
                    // .put("client.transport.sniff",true)
                    .put("cluster.name", "log-application").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.201"), 9300));
            System.out.println(client.admin());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        //on shutdown 断开集群
        client.close();
    }

    public void testGet() {

        GetResponse response = client.prepareGet("spider", "log", "1").get();
        System.out.println(response.getSourceAsString());
    }

    /**
     * 查找最近的出错比
     */
    public Map queryError() {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        Date myDate1 = sdf.parse("2017-07-21T08:18:40.000Z");
//        Date myDate2 = sdf.parse("2017-07-21T08:18:43.814Z");

//        SortBuilder sortBuilder = SortBuilders.fieldSort("log_time")
//                .order(SortOrder.DESC);
        SearchResponse response = client.prepareSearch("spider")  //设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                //.setQuery(QueryBuilders.termQuery("log_level", "error"))
                //.setPostFilter(QueryBuilders.rangeQuery("log_time").from("2017-07-24T05:13:20.000Z")
                //                                                            .to("2017-07-24T05:15:20.000Z"))
                .addSort(sortBuilder)
                .setFrom(0).setSize(1000)//查出来几条
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();

        int errorNum=0;
        int infoNum=0;
        int debugNum=0;

        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.println(hits.getHits()[i].getSource());//map形式
            String log_level = hits.getHits()[i].getSource().get("log_level").toString();
            if(log_level.equals("INFO")){
                infoNum=infoNum+1;
            }
            else if(log_level.equals("ERROR")){
                errorNum=errorNum+1;
            }
            else if(log_level.equals("DEBUG")){
                debugNum=debugNum+1;
            }
        }

        Map levelMap =new HashMap();
        levelMap.put("infoNum",infoNum);
        levelMap.put("errorNum",errorNum);
        levelMap.put("debugNum",debugNum);


        return levelMap;
//          分割字符串
//        String sourceStr = "1,2,3,4,5";
//        String[] sourceStrArray = sourceStr.split(",");
//        for (int i = 0; i < sourceStrArray.length; i++) {
//            System.out.println(sourceStrArray[i]);
//        }



    }


    /**
     * 查找数据量
     */
    public Map querySize() {

        SearchResponse response = client.prepareSearch("spider")  //设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                //.setQuery(QueryBuilders.termQuery("log_level", "error"))
                //.setPostFilter(QueryBuilders.rangeQuery("log_time").from("2017-07-24T05:13:20.000Z")
//                                                         .to("2017-07-24T05:15:20.000Z"))
                .addSort(sortBuilder)
                .setFrom(0).setSize(1000)//查出来几条
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();

        long size=0;


        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.println(hits.getHits()[i].getSource());//map形式

            size=size+(long)hits.getHits()[i].getSource().get("bytes");
        }

        Map sizeMap =new HashMap();
        sizeMap.put("size",size);

        return sizeMap;


    }





    /**
     * 查找耗时
     */
    public Map queryDelay() {

        SearchResponse response = client.prepareSearch("spider")  //设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                //.setQuery(QueryBuilders.termQuery("log_level", "error"))
                //.setPostFilter(QueryBuilders.rangeQuery("log_time").from("2017-07-24T05:13:20.000Z")
//                                                         .to("2017-07-24T05:15:20.000Z"))
                .addSort(sortBuilder)
                .setFrom(0).setSize(1000)//查出来几条
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();

        long delay=0;


        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.println(hits.getHits()[i].getSource());//map形式

            delay=delay+(long)hits.getHits()[i].getSource().get("delay");
        }

        Map delayMap =new HashMap();
        delayMap.put("delay",delay);

        return delayMap;


    }


    /**
     * 查找最近的出错信息
     */
    public Map queryErrorDetail() {

        SearchResponse response = client.prepareSearch("spider")  //设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("log_level", "error"))
                .addSort(sortBuilder)
                .setFrom(0).setSize(1000)//查出来几条
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();


        List<errorLog> errorList = new ArrayList<errorLog>();

        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.println(hits.getHits()[i].getSource());//map形式

            errorLog e=new errorLog();
            e.setErrorItem( hits.getHits()[i].getSource().get("bytes").toString());
            e.setErrorDetail(hits.getHits()[i].getSource().get("error_detail").toString());
            e.setLogTime(hits.getHits()[i].getSource().get("host_Name").toString());
            e.setUrl( hits.getHits()[i].getSource().get("host_Name").toString());
            e.setWebsite( hits.getHits()[i].getSource().get("host_Name").toString());
            errorList.add(e);
        }

        Map errorDetailMap =new HashMap();
        errorDetailMap.put("errorDetail",errorList);
        return errorDetailMap;
    }













    /**
     * 查找最近的十条速度
     */
    public  Map querySpeed()  {

//获得最近的10条速率
        SortBuilder sortBuilder = SortBuilders.fieldSort("log_time")
                .order(SortOrder.DESC);
        SearchResponse response = client.prepareSearch("spider")  //设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("log_level", "info"))
                .addSort(sortBuilder)
                .setPostFilter(QueryBuilders.rangeQuery("page_speed"))
                .setFrom(24).setSize(10)//查出来几条
                .execute()
                .actionGet();


        List<String> speedList = new ArrayList<String>();
        List<String> logtimeList = new ArrayList<String>();
        Map<String, List> peopleSpeedmap = new HashMap<String, List>();
        SearchHits hits = response.getHits();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat targetFmt = new SimpleDateFormat( "yyyy-MM-dd HH:mm");

        String logTime="";
        for (int i = hits.getHits().length-1; i >0 ; i--) {
            //Map<String, String> speedmap = new HashMap<String, String>();
            System.out.println(hits.getHits()[i].getSource());//map形式

            String log_time = hits.getHits()[i].getSource().get("log_time").toString();
            String speed = hits.getHits()[i].getSource().get("page_speed").toString();

            //将日期格式转换
            //将日期格式转换
            try {
                Date date = sdf.parse(log_time);
                logTime = targetFmt.format(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //存入map加入List
            //speedmap.put(logTime,speed);
            logtimeList.add(logTime);
            speedList.add(speed);
            //speedList.add(speedmap);

        }

        peopleSpeedmap.put("logtimeList",logtimeList);
        peopleSpeedmap.put("speedList",speedList);
        return peopleSpeedmap;
    }


}