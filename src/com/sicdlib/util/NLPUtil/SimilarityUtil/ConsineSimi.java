package com.sicdlib.util.NLPUtil.SimilarityUtil;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.sicdlib.util.NLPUtil.HanLPUtil.HanLPUtil;
import com.sicdlib.util.NLPUtil.HanLPUtil.NERNameValue;
import org.junit.Test;

import java.util.List;

/**
 * Created by DeMH on 2017/7/18.
 */
public class ConsineSimi {

    /**
     * 输入ners1事件的NER权重列表
     * 输入ners2 text2的内容的NER权重列表
     * 输入text2内容
     * @param ners1
     * @param ners2
     * @param text2
     * @return
     */
    public static Double calSimiByNERNameValue(List<NERNameValue> ners1, List<NERNameValue> ners2, String text2){
        Integer simi_values = 0;
        Integer ners1_values = 0;
        Integer ners2_values = 0;
        System.out.println("文本1的表示：");
        for (NERNameValue ner1 : ners1) {
            System.out.println(ner1.getName() +" : " + ner1.getValue());
            int flag = 0;
            for (NERNameValue ner2 : ners2){
                if (ner1.getName().equals(ner2.getName())){
                    flag = 1; break;
                }
            }
            if (flag == 1) {
                simi_values += ner1.getValue() * ner1.getValue();
            }
            ners1_values += ner1.getValue() * ner1.getValue();
        }
        System.out.println("文本2的表示：");
        for (NERNameValue ner2 : ners2){
            System.out.println(ner2.getName() +" : " + ner2.getValue());
            ners2_values += ner2.getValue()*ner2.getValue();
        }
        double simi = 0.0;
        //包含不同分词大小
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        List<Term> termList = segment.seg(text2);
        int seg_num = termList.size();
        if (ners1.size() < ners2.size()){
//            simi = (simi_values + ners1.size()) / (Math.sqrt(ners1_values) * Math.sqrt(ners2_values) + seg_num + 1);
            simi = simi_values / (Math.sqrt(ners1_values) * Math.sqrt(ners2_values) + seg_num + 1);
        }else{
            simi = simi_values / (Math.sqrt(ners1_values) * Math.sqrt(ners2_values) + 1);
        }
        System.out.println("calSimiByNERNameValue的相似度：" + simi);
        return simi;
    }

//    public static Double calSimiByNERValue(List<NERNameValue> ners1, List<NERNameValue> ners2, String text2){
//        Integer simi_values = 0;
//        Integer ners1_values = 0;
//        Integer ners2_values = 0;
//        for (NERNameValue ner1 : ners1) {
//            int flag = 0;
//            for (NERNameValue ner2 : ners2){
//                if (ner1.getName().equals(ner2.getName())){
//                    flag = 1; break;
//                }
//            }
//            if (flag == 1) {
//                simi_values += ner1.getValue();
//            }
//            ners1_values += ner1.getValue() * ner1.getValue();
//        }
//
//        for (NERNameValue ner2 : ners2){
//            ners2_values += ner2.getValue()*ner2.getValue();
//        }
//        double simi = 0.0;
//        //包含不同分词大小
//        Segment segment = HanLP.newSegment().enableNameRecognize(true);
//        List<Term> termList = segment.seg(text2);
//        int seg_num = termList.size();
//        if (ners1.size() < ners2.size()){
//            simi = (simi_values + ners1.size()) / (Math.sqrt(ners1_values) * Math.sqrt(ners2_values) + seg_num + 1);
//        }else{
//            simi = simi_values / (Math.sqrt(ners1_values) * Math.sqrt(ners2_values) + 1);
//        }
//        System.out.println("calSimiByNERNameValue的相似度：" + simi);
//        return simi;
//    }

    @Test
    public void test(){
//        String text1 = "签约仪式前，秦光荣、李纪恒、李纪恒、李纪恒、仇和等一同会见了参加学习签约的企业家。";
//        String text2 = "签约仪式前，秦光荣、李纪恒、李纪恒、李纪恒、李纪恒、仇和等一同会见了参加学习签约的企业家。";
        String text1 = "上海女孩逃离江西农村";
        String text2 = "上海女孩逃离江西农村";
        //获得NER
        List<NERNameValue> ners1 = HanLPUtil.getNERNameValuesMap(text1);
        //将NER，text传入获得所有分词并赋予权重
        ners1 = HanLPUtil.getAllNERAndSegs(ners1, text1);
        //将ners1和text2传入获得text2中和text1相同的分词并赋予权重
        List<NERNameValue> ners2 = HanLPUtil.getNER2ByNameValue(ners1, text2);

        for (NERNameValue ner1 : ners1) {
            System.out.println("ners1: " + ner1.getName());
        }
        System.out.println();
        for (NERNameValue ner2 : ners2) {
            System.out.println(ner2.getName());
        }
        Double simi = calSimiByNERNameValue(ners1, ners2, text2);
        System.out.println(simi);
    }
}
