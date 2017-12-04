package com.sicdlib.util;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class Main {

    private Client client;


    //创建连接

    public void init(){
        // on startup
        try {
            Settings settings = Settings.builder()
                    // .put("client.transport.sniff",true)
                    .put("cluster.name", "log-application").build();

            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.201"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }



    }


    public void close(){
        //on shutdown 断开集群
        client.close();
    }

    public void testGet() {

        GetResponse response = client.prepareGet("spider", "log", "1").get();
        System.out.println(response.getSourceAsString());
    }


    public  void query(){
        SearchResponse response = client.prepareSearch("spider")  //设置要查询的索引(index)
                //.setTypes("log")    //设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("log_level", "info"))
                // .setFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Query 查询之一. 对于QueryBuilders接下来会解释. 在这里"multi"是要查询的field,"test"是要查询的内容
                //.setFrom(0).setSize(60).setExplain(true)       //  0-60 表示显示数量,Filter 查询之二, FilterBuilders接下来会解释. 在这里"age"是要查询的field, 后面的数字是查询的条件范围
                .setFrom(0).setSize(20)//查出来几条
                .execute()
                .actionGet();


        SearchHits hits = response.getHits();
        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.println(hits.getHits()[i].getSourceAsString());
            System.out.println(hits.getHits()[i].getSource()) ;//map形式
        }

//        String lat = searchResponse.getHits().getAt(i).getSource()
//                .get("latitude").toString();
//        String lon = searchResponse.getHits().getAt(i).getSource()
//                .get("longitude").toString();

    }


//    public void search(){
//        //匹配所有
//        //QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
//
//        //设置查询条件
//        QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
//                .add(QueryBuilders.termQuery("cmd-statistic", "http.avg5"))
//                .add(QueryBuilders.prefixQuery("info-type", "statistic"));
//
//        //System.out.println(queryBuilder.toString());
//
//        SearchResponse response = client.prepareSearch("INDEXNAME")
//                .setQuery(queryBuilder).execute().actionGet();
//
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println("---->>hit.getId(): " + hit.getId());
//            if (hit.getFields().containsKey("cmd-statistic")) {
//
//                System.out.println("field.cmd-statistic: "
//                        + hit.getFields().get("cmd-statistic").getValue());
//            }
//            System.out.println("cmd-statistic: " + hit.getSource().get("cmd-statistic"));
//        }
//    }


    public static void main(String[] args){
        Main client = new Main();
        client.init();
        //client.testGet();
        //client.createIndex();
        //client.get();
        //client.delete();
        client.query();
        client.close();


    }
}