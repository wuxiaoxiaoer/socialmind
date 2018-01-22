package com.sicdlib.util;


import com.sicdlib.entity.errorLog;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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

    private SortBuilder sortBuilderDESC = SortBuilders.fieldSort("log_time")
            .order(SortOrder.DESC);
    private SortBuilder sortBuilderASC = SortBuilders.fieldSort("log_time")
            .order(SortOrder.ASC);

    //入口
    public Map<String,Object> esClient() {
        Map<String,Object> map=new HashMap<String,Object>();

        esUtil client = new esUtil();
        client.init();
        Map<String, List> speedmap = client.querySpeed("/hbasestorage/spider/spider_logs/douban_group/scrapy.logs");

        map.put("speedmap",speedmap);
        map.put("errorDetailMap",client.errorDetailMap());
        map.put("durationMap",client.durationMap());
        map.put("errorRateMap",client.errorRateMap());


        System.out.println(map.size());
        client.close();
        return map;

    }




    //AJAX入口
    public Map<String,List> speed(String source) {
        esUtil client = new esUtil();
        client.init();
        Map<String, List> speedmap = client.querySpeed(source);

        client.close();
        return speedmap;

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




    public void querySize(String source,String str1,String str2){
        //多条件设置
        MatchPhraseQueryBuilder mpq1 = QueryBuilders
                .matchPhraseQuery("source", source);
        RangeQueryBuilder mpq2 = QueryBuilders
                .rangeQuery("log_time").from(str1)
                .to(str2);


        QueryBuilder qb2 = QueryBuilders.boolQuery()
                .must(mpq1)
                .must(mpq2);

        SumAggregationBuilder aggregation = AggregationBuilders
                .sum("bytes")
                .field("log_time");

        //查询建立
        SearchResponse response=client
                .prepareSearch("spider_log")
                .setQuery(qb2)
                .addAggregation(aggregation)
                .addSort(sortBuilderDESC)
//                .setFrom(0).setSize(3)
                .execute()
                .actionGet();

        SearchHits hits = response.getHits();

        Sum bytes = response.getAggregations().get("bytes");
        double size = bytes.getValue()/Math.pow(2,30);//单位是

        System.out.println("######"+size);
//        System.out.println(hits.getHits()[1].getSource());//map形式

    }



    /**
     * 各爬虫爬取耗时
     *
     */
    public Map<String,Long> Map(){
        Map<String,Long> durationMap=new HashMap<String,Long>();

        durationMap.put("豆瓣",duration("/hbasestorage/spider/spider_logs/douban_group/scrapy.logs"));

        return durationMap;
    }








    /**
     * 查找数据量
     */
//    public Map querySize() {
//
//        SearchResponse response = client.prepareSearch("spider")  //设置要查询的索引(index)
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.matchAllQuery())
//                //.setQuery(QueryBuilders.termQuery("log_level", "error"))
//                //.setPostFilter(QueryBuilders.rangeQuery("log_time").from("2017-07-24T05:13:20.000Z")
////                                                         .to("2017-07-24T05:15:20.000Z"))
//                .addSort(sortBuilderDESC)
//                .setFrom(0).setSize(1000)//查出来几条
//                .execute()
//                .actionGet();
//        SearchHits hits = response.getHits();
//
//        long size=0;
//
//
//        for (int i = 0; i < hits.getHits().length; i++) {
//            System.out.println(hits.getHits()[i].getSource());//map形式
//
//            size=size+(long)hits.getHits()[i].getSource().get("bytes");
//        }
//
//        Map sizeMap =new HashMap();
//        sizeMap.put("size",size);
//
//        return sizeMap;
//
//
//    }


    public Map errorMap(){

        Map errorMap =new HashMap();


        return  errorMap;

    }



    /**
     * 查找最近的十条速度
     */
    public  Map querySpeed(String source)  {

//获得最近的10条速率
        SortBuilder sortBuilder = SortBuilders.fieldSort("log_time")
                .order(SortOrder.DESC);
        SearchResponse response = client.prepareSearch("spider_log")  //设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.commonTermsQuery("source",source))
                .addSort(sortBuilder)
                .setPostFilter(QueryBuilders.rangeQuery("page_speed"))
                .setFrom(24).setSize(10)//查出来几条
                .execute()
                .actionGet();


        List<String> speedList = new ArrayList<String>();
        List<String> logtimeList = new ArrayList<String>();
        Map<String, List> speedmap = new HashMap<String, List>();
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

        speedmap.put("logtimeList",logtimeList);
        speedmap.put("speedList",speedList);
        return speedmap;
    }




    /**
     * 各爬虫爬取耗时
     *
     */
    public Map<String,Long> durationMap(){
        Map<String,Long> durationMap=new HashMap<String,Long>();

        durationMap.put("豆瓣",duration("/hbasestorage/spider/spider_logs/douban_group/scrapy.logs"));
        durationMap.put("搜狐bbs",duration("/hbasestorage/spider/spider_logs/bbs_sohu/scrapy.logs"));
        durationMap.put("人民网bbs",duration("/hbasestorage/spider/spider_logs/bbs_people/scrapy.logs"));
        durationMap.put("163博客",duration("/hbasestorage/spider/spider_logs/blog_163/scrapy.logs"));
        durationMap.put("中华网bbs",duration("/hbasestorage/spider/spider_logs/china_bbs/scrapy.logs"));
        durationMap.put("凯迪",duration("/hbasestorage/spider/spider_logs/kdnet_spider/scrapy.logs"));
        durationMap.put("猫扑",duration("/hbasestorage/spider/spider_logs/mop/scrapy.logs"));
        durationMap.put("新浪博客",duration("/hbasestorage/spider/spider_logs/sina_blog/scrapy.logs"));
        durationMap.put("新浪新闻",duration("/hbasestorage/spider/spider_logs/sina_news_spider/scrapy.logs"));
        durationMap.put("天涯bbs",duration("/hbasestorage/spider/spider_logs/tianya_bbs/scrapy.logs"));
        durationMap.put("西祠",duration("/hbasestorage/spider/spider_logs/xici/scrapy.logs"));
        durationMap.put("微博",duration("/hbasestorage/spider/spider_logs/weibo_spider/scrapy.logs"));

        return durationMap;
    }

    public long duration(String source){
        SearchResponse response = client.prepareSearch("spider_log")  //设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                .setQuery(QueryBuilders.commonTermsQuery("source", source))
                .addSort(sortBuilderDESC)
                .setFrom(0).setSize(1)//查出来几条
                .execute()
                .actionGet();
        long count = response.getHits().getTotalHits();//第一次不返回数据
        System.out.println("总计: "+count);
        long hour=0;
        if(count!=0){
            String endTime=response.getHits().getHits()[0].getSource().get("log_time").toString();
            System.out.println("endTime: "+endTime);
            SearchResponse response2=client.prepareSearch("spider_log")  //设置要查询的索引(index)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.matchAllQuery())
                    .setQuery(QueryBuilders.commonTermsQuery("source", source))
                    .addSort(sortBuilderASC)
                    .setFrom(0).setSize(1)//查出来几条
                    .execute()
                    .actionGet();
            String startTime=response2.getHits().getHits()[0].getSource().get("log_time").toString();
            System.out.println("startTime: "+startTime);

            hour=getHourSub(startTime,endTime);
            System.out.println("cha : "+hour);
        }

        else {
            hour=0;
        }
        return hour;
    }


    /**
     * <li>功能描述：时间相减得到天数
     */
    public static long getHourSub(String beginDateStr,String endDateStr)
    {
        long day=0;
        long hour=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date beginDate;
        Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            hour=(endDate.getTime()-beginDate.getTime())/(60*60*1000);
            //System.out.println("相隔的天数="+day);
        } catch (ParseException e)
        {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return hour;
    }


    public Map errorRateMap(){

        Map<String,Map> errorRateMap=new HashMap<String,Map>();
        errorRateMap.put("douban",errorMap("/hbasestorage/spider/spider_logs/douban_group/scrapy.logs"));
        errorRateMap.put("bbs_sohu",errorMap("/hbasestorage/spider/spider_logs/bbs_sohu/scrapy.logs"));
        errorRateMap.put("bbs_people",errorMap("/hbasestorage/spider/spider_logs/bbs_people/scrapy.logs"));
        errorRateMap.put("blog_163",errorMap("/hbasestorage/spider/spider_logs/blog_163/scrapy.logs"));
        errorRateMap.put("china_bbs",errorMap("/hbasestorage/spider/spider_logs/china_bbs/scrapy.logs"));
        errorRateMap.put("kdnet",errorMap("/hbasestorage/spider/spider_logs/kdnet_spider/scrapy.logs"));
        errorRateMap.put("mop",errorMap("/hbasestorage/spider/spider_logs/mop/scrapy.logs"));
        errorRateMap.put("sina_blog",errorMap("/hbasestorage/spider/spider_logs/sina_blog/scrapy.logs"));
        errorRateMap.put("sina_news",errorMap("/hbasestorage/spider/spider_logs/sina_news_spider/scrapy.logs"));
        errorRateMap.put("tianya_bbs",errorMap("/hbasestorage/spider/spider_logs/tianya_bbs/scrapy.logs"));
        errorRateMap.put("xici",errorMap("/hbasestorage/spider/spider_logs/xici/scrapy.logs"));
        errorRateMap.put("weibo",errorMap("/hbasestorage/spider/spider_logs/weibo_spider/scrapy.logs"));

        return errorRateMap;
    }

    /**
     * 将出错比组织成map
     */
    public Map errorMap(String source){

        Map<String,Long> map=new HashMap<String,Long>();
        map.put("infoNum",queryErrorRate(source,"info"));
        map.put("debugNum",queryErrorRate(source,"debug"));
        map.put("errorNum",queryErrorRate(source,"error"));
        return map;
    }

    /**
     * 查找出错比
     */
    public long queryErrorRate(String source,String log_level) {


        long count=0;
//多条件设置
        MatchPhraseQueryBuilder mpq1 = QueryBuilders
                .matchPhraseQuery("source", source);
        MatchPhraseQueryBuilder mpq2 = QueryBuilders
                .matchPhraseQuery("log_level", log_level);
        QueryBuilder qb2 = QueryBuilders.boolQuery()
                .must(mpq1)
                .must(mpq2);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(qb2);

        //查询建立
        SearchResponse response=client
                .prepareSearch("spider_log")
                .setQuery(qb2)
                .setFrom(0).setSize(50)
                .setExplain(true).execute().actionGet();

        count = response.getHits().getTotalHits();
        System.out.println("count  "+count);
        return count;

    }



    /**
     * 查找各爬虫的出错信息
     */
    public Map errorDetailMap() {

        Map<String,List> errorMap=new HashMap<String,List>();
        errorMap.put("douban",queryErrorDetail("/hbasestorage/spider/spider_logs/douban_group/scrapy.logs"));
//        errorMap.put("bbs_sohu",queryErrorDetail("/hbasestorage/spider/spider_logs/bbs_sohu/scrapy.logs"));
//        errorMap.put("bbs_people",queryErrorDetail("/hbasestorage/spider/spider_logs/bbs_people/scrapy.logs"));
//        errorMap.put("blog_163",queryErrorDetail("/hbasestorage/spider/spider_logs/blog_163/scrapy.logs"));
//        errorMap.put("china_bbs",queryErrorDetail("/hbasestorage/spider/spider_logs/china_bbs/scrapy.logs"));
//        errorMap.put("kdnet",queryErrorDetail("/hbasestorage/spider/spider_logs/kdnet_spider/scrapy.logs"));
//        errorMap.put("mop",queryErrorDetail("/hbasestorage/spider/spider_logs/mop/scrapy.logs"));
//        errorMap.put("sina_blog",queryErrorDetail("/hbasestorage/spider/spider_logs/sina_blog/scrapy.logs"));
//        errorMap.put("sina_news",queryErrorDetail("/hbasestorage/spider/spider_logs/sina_news_spider/scrapy.logs"));
//        errorMap.put("tianya_bbs",queryErrorDetail("/hbasestorage/spider/spider_logs/tianya_bbs/scrapy.logs"));
//        errorMap.put("xici",queryErrorDetail("/hbasestorage/spider/spider_logs/xici/scrapy.logs"));
//        errorMap.put("weibo",queryErrorDetail("/hbasestorage/spider/spider_logs/weibo_spider/scrapy.logs"));

        System.out.println("@errorMap    "+errorMap.get("douban").size());
        return errorMap;
    }

    /**
     * 查找某爬虫最近的出错信息
     */
    public List queryErrorDetail(String source) {

        //多条件设置
        MatchPhraseQueryBuilder mpq1 = QueryBuilders
                .matchPhraseQuery("source", source);
        MatchPhraseQueryBuilder mpq2 = QueryBuilders
                .matchPhraseQuery("log_level","error");
        QueryBuilder qb2 = QueryBuilders.boolQuery()
                .must(mpq1)
                .must(mpq2);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(qb2);

        //查询建立
        SearchResponse response=client
                .prepareSearch("spider_log")
                .setQuery(qb2)
                .addSort(sortBuilderDESC)
                .setFrom(0).setSize(100)//查出来几条
                .execute()
                .actionGet();

        SearchHits hits = response.getHits();

        System.out.println("@@@@@@@@@@"+response.getHits().getTotalHits());


        //分割字符串
        String[] sourceStrArray = source.split("/");
        String website="";
        String url="";
        System.out.println("sourceStrArray[4] "+sourceStrArray[4]);

        switch(sourceStrArray[4]){
            case "douban_group":
                website="豆瓣小组";
                url="https://www.douban.com/game/22992396/";
                break;
            case "bbs_sohu":
                website="搜狐BBS";
                url="http://bbs.club.sohu.com/";
            case "bbs_people":
                website="人民网BBS";
                url="http://bbs1.people.com.cn/";
                break;
            case "blog_163":
                website="网易博客";
                url="http://blog.163.com/";
                break;
            case "china_bbs":
                website="中华网社区";
                url="http://club.china.com/data/threads/1011/";
                break;
            case "kdnet_spider":
                website="凯迪社区";
                url="https://www.douban.com/game/22992396/";
                break;
            case "mop":
                website="猫扑社区";
                url="http://www.mop.com/";
                break;
            case "sina_blog":
                website="新浪博客";
                url="http://blog.sina.com.cn/";
                break;
            case "sina_news_spider":
                website="新浪新闻";
                url="http://news.sina.com.cn/";
                break;
            case "tianya_bbs":
                website="天涯BBS";
                url="http://bbs.tianya.cn/";
                break;
            case "xici":
                website="西祠社区";
                url="http://www.xici.net/";
                break;
            case "weibo_spider":
                website="微博";
                url="https://weibo.com/";
                break;
            default:
                    break;
        }


        List<errorLog> errorList = new ArrayList<errorLog>();
        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.println(hits.getHits()[i].getSource());//map形式
            errorLog e=new errorLog();
            e.setErrorItem( hits.getHits()[i].getSource().get("error_item").toString());
            e.setErrorDetail(hits.getHits()[i].getSource().get("message").toString());//实际上errordetail是message
            e.setLogTime(hits.getHits()[i].getSource().get("log_time").toString());
            e.setUrl(url);
            e.setWebsite(website);
            errorList.add(e);
        }

        return errorList;
    }

}