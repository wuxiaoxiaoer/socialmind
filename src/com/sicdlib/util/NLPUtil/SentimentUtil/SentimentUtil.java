package com.sicdlib.util.NLPUtil.SentimentUtil;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by DeMH on 2017/7/23.
 */
public class SentimentUtil {
    //输入内容text获得：积极情感词数量，消极情感词数量，总句子数
    public static Double[] sentimentScore(String text){
        Double scores[] = new Double[3];
        Double pos_score = 0.0;
        Double neg_score = 0.0;
        Double sentences_num = Double.valueOf(text.split(",|，|。|？").length);
        //对text分词 -> 读取情感词字典 -> 匹配获得情感词性数量
        List<Term> text_seg_terms = HanLP.segment(text);
        List<String> text_segs = new ArrayList<>();
        for (Term term : text_seg_terms){
            text_segs.add(term.word);
        }
        List<String> pos_str_list = getPosOrNegList("data/sentimentData/正面情感词语（中文）.txt");
        List<String> neg_str_list = getPosOrNegList("data/sentimentData/负面情感词语（中文）.txt");
        List<String> deny_str_list = getPosOrNegList("data/sentimentData/否定词语（中文）.txt");
        HashMap<String, Double> level_str_map = getSentimentLevelList("data/sentimentData/程度级别词语（中文）.txt");
        for (int i = 0;i < text_segs.size(); i++){
            String seg = text_segs.get(i);
            if (pos_str_list.contains(seg) || neg_str_list.contains(seg)) {
                if (i-1 >= 0) {
                    //单个否定
                    if (deny_str_list.contains(text_segs.get(i-1))){
                        if (i-2 >= 0 && neg_str_list.contains(text_segs.get(i-2)) || deny_str_list.contains(text_segs.get(i-2))
                                || level_str_map.containsKey(text_segs.get(i-2))) {
                            //什么都不做
                        }else {
                            if (pos_str_list.contains(seg)) {
                                neg_score = neg_score + 1;
                            }
                            if (neg_str_list.contains(seg)) {
                                pos_score = pos_score + 1;
                            }
                        }
                    }
                }
                //防止越界
                if (i-1>=0 && i-2>=0){
                    // 双重否定
                    if (neg_str_list.contains(text_segs.get(i-2)) && neg_str_list.contains(text_segs.get(i-1))){
                        if (pos_str_list.contains(seg)){
                            pos_score = pos_score + 1;
                        }else {
                            neg_score = neg_score + 1;
                        }
                    }

                    // 程度副词(-2) 否定词(-1)
                    if (level_str_map.containsKey(text_segs.get(i-2)) && deny_str_list.contains(text_segs.get(i-1))){
                        if (pos_str_list.contains(seg)){
                            pos_score = pos_score + level_str_map.get(text_segs.get(i-2)) * (-1);
                        }else {
                            neg_score = neg_score + level_str_map.get(text_segs.get(i-2)) * (-1);
                        }
                    }

                    // 否定词(-2) 程度副词(-1)
                    if (deny_str_list.contains(text_segs.get(i-2)) && level_str_map.containsKey(text_segs.get(i-1))){
                        if (pos_str_list.contains(seg)){
                            pos_score = pos_score + level_str_map.get(text_segs.get(i-1)) * 0.5;
                        }else {
                            neg_score = neg_score + level_str_map.get(text_segs.get(i-1)) * 0.5;
                        }
                    }
                }
            }
        }
        scores[0] = + pos_score;
        scores[1] = - neg_score;
        scores[2] = sentences_num;
        return scores;
    }

    //得到 正面/负面 情感词语列表
    public static List<String> getPosOrNegList(String path){
        List<String> str_list = new ArrayList<>();
        File pos_file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(pos_file));
            String tempStr = null;
            while ((tempStr = reader.readLine()) != null){
                //将情感词放入列表中
                str_list.add(tempStr.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str_list;
    }

    //得到 程度级别的词 Map
    public static HashMap<String, Double> getSentimentLevelList(String path){
        File pos_file = new File(path);
        BufferedReader reader = null;
        HashMap<String, Double> degree_map = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(pos_file));
            String tempStr = null;
            int flag = 0;
            while ((tempStr = reader.readLine()) != null){
                //1.极其
                if (tempStr.startsWith("1") || flag == 1){
                    flag = 1;
                    degree_map.put(tempStr.trim(), 2.0);
                }
                //2.很
                if (tempStr.startsWith("2") || flag == 2){
                    flag = 2;
                    degree_map.put(tempStr.trim(), 1.25);
                }
                //3.较
                if (tempStr.startsWith("3") || flag == 3){
                    flag = 3;
                    degree_map.put(tempStr.trim(), 1.2);
                }
                //4.稍
                if (tempStr.startsWith("4") || flag == 4){
                    flag = 4;
                    degree_map.put(tempStr.trim(), 0.8);
                }
                //4.欠
                if (tempStr.startsWith("5") || flag == 5){
                    flag = 5;
                    degree_map.put(tempStr.trim(), 0.5);
                }
                //4.超
                if (tempStr.startsWith("6") || flag == 6){
                    flag = 6;
                    degree_map.put(tempStr.trim(), 1.5);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree_map;
    }

    @Test
    public void test(){
//        sentimentScore("我一点儿也不喜欢吃饭了");
//        sentimentScore("我不太喜欢吃饭了");
//        sentimentScore("我极其不喜欢吃饭了");
//        sentimentScore("我不讨厌上课");
//        sentimentScore("我不爱上课");
//        sentimentScore("我既不爱上课，又不爱吃饭？更不爱哈哈");
        Double title_scores[] = SentimentUtil.sentimentScore("颖儿宣布怀孕 付辛博甜蜜告白：从此有两个宝贝");
        Double stmt_score = title_scores[2];
        System.out.println(title_scores[0] +" : " + title_scores[1] + " : " + title_scores[2]);
        System.out.println(stmt_score);
    }
}
