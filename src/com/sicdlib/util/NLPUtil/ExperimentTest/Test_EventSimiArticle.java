package com.sicdlib.util.NLPUtil.ExperimentTest;

import com.sicdlib.util.NLPUtil.HanLPUtil.HanLPUtil;
import com.sicdlib.util.NLPUtil.HanLPUtil.NERNameValue;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Test.Word2Vec;
import com.sicdlib.web.CommmonUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by DeMH on 2017/7/18.
 */
public class Test_EventSimiArticle {

    ClassPathXmlApplicationContext apt = new ClassPathXmlApplicationContext("beans.xml");
//    ITableService tableService = (ITableService)apt.getBean("tableService");
//    ITableCountDAO tableCountDAO = (ITableCountDAO)apt.getBean("tableCountDAO");
    String eventName = "春节前夕,上海女孩逃离江西农村,上海女孩，春节前去“男朋友”家乡江西过年，被第一顿饭“吓”而逃离江西,受不了男友家贫困，决定和男友分手并立即回上海";
    Double userSetupSimiDegree = 0.70;
    String urset_starttime = "2016-01-01 00:00:00";
    String urset_endtime = "2017-04-01 00:00:00";
    //数据库每次取数据的数量
    Integer LimitNUM = 1000;
    //得到相关文章数
    Integer get_num = 100;

    /**
     * 方案：NER-Consine相似度比对
     * 首先命名实体抽取：获得人名、地名、组织名、动词名并设置不同权重，设定事件发生的时间区间，进行consine相似度对比
     *
     */
    @Test
    public void test_simi2_NER(){
        Long beginTime = new Date().getTime();
        //1. 将事件分词进行NER获得带有权值的人名、地名、组织名列表
        List<NERNameValue> event_nerNameValues = HanLPUtil.getNERNameValuesMap(eventName);
        //将NER，text传入获得所有分词并赋予权重
        event_nerNameValues = HanLPUtil.getAllNERAndSegs(event_nerNameValues, eventName);
        //1.2 还需要word2vec扩充事件动词V的同义词
        Word2Vec vec = new Word2Vec();
        List<String> keyWords = CommmonUtil.getKeywordsPOS(eventName, vec, 2);
        for (String kw : keyWords){
            NERNameValue ner = new NERNameValue(kw, "normal");
            event_nerNameValues.add(ner);
        }
//        //2. 根据事件发生的大概时间T从各个网站post中获得id和内容，将其分词进行NER获得人名、地名、组织名
//        //首先获得所有表名
//        List<String> allTableNames = tableService.getAllTableNames();
//        List<String> allpostTbNames = new ArrayList<>();
//        //获得所有"_post"表名
//        for (String tb : allTableNames) {
//            if (tb.endsWith("_post") || tb.endsWith("_news") || tb.endsWith("_sanqin") || tb.endsWith("_sina") || tb.endsWith("_blog")){
//                allpostTbNames.add(tb);
//            }
//        }
//        List<String []> essays = new ArrayList<>();
//        //遍历所有post表
//        int flag_num = 0;
//        for (String tbname : allpostTbNames){
//            Integer tbCounts = tableCountDAO.getTableCountByTbName(tbname).getCount();
//            Integer num = tbCounts / LimitNUM;
//            for (int i = 0; i<= num; i++){
//                List<Object[]> idsContents = tableService.getContentsByTbName(tbname, urset_starttime, urset_endtime, i*LimitNUM, LimitNUM);
//                if (idsContents.size() != 0) {
//                    for (Object[] idcnt : idsContents) {
//                        //获得每条内容的带有权值的NER
//                        //将 event_nerNameValues 和 content 传入获得text2中和text1相同的分词并赋予权重
//                        List<NERNameValue> cnt_nameValues = HanLPUtil.getNER2ByNameValue(event_nerNameValues, idcnt[1].toString());
//                        //与事件NERNameValue进行余弦相似性对比
//                        Double simi = ConsineSimi.calSimiByNERNameValue(event_nerNameValues, cnt_nameValues, idcnt[1].toString());
//                        if (simi > userSetupSimiDegree) {
//                            String idAndcnt[] = new String[3];
//                            //id
//                            idAndcnt[0] = idcnt[0].toString();
//                            //content
//                            idAndcnt[1] = idcnt[1].toString();
//                            idAndcnt[2] = simi.toString();
//                            essays.add(idAndcnt);
//                            if (essays.size() > get_num) {
//                                break;
//                            }
//                            System.out.println();
//                            flag_num ++;
//                            System.out.println(flag_num + " : " + new Date().toLocaleString() + " : " + simi + " : " + idcnt[0] + " : " + idcnt[1]);
//                            System.out.println();
//                        }
//                    }
//                }
//                if (essays.size() > get_num){
//                    break;
//                }
//            }
//            if (essays.size() > get_num){
//                break;
//            }
//        }
//        int i = 0;
//        for (String [] idAndcnt : essays) {
//            System.out.println("文章" + i + ": "+ idAndcnt[2] +":  " +idAndcnt[1]);
//            i++;
//        }
    }
}
