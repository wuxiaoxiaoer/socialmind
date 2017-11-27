package com.sicdlib.web;

import com.sicdlib.entity.EventEssaySimi;
import com.sicdlib.entity.NamedEntityInfo;
import com.sicdlib.util.NLPUtil.Word2VecUtil.OtherUtil.Segment;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Test.Word2Vec;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Vec.domain.WordEntry;
import org.ansj.library.UserDefineLibrary;

import java.io.IOException;
import java.util.*;

/**
 * Created by DeMH on 2017/6/21.
 */
public class CommmonUtil {

    /**
     * 输入事件名，得到该事件的分词
     */
    public static List<String> getKeywordsBySeg(String eventName){

        List<String> eventWords = Segment.getWords(eventName);
        return eventWords;
    }
    /**
     * 输入该事件名,得到该事件的词性,然后对该事件的动词进行扩充
     */
    public static List<String> getKeywordsPOS(String eventName, Word2Vec vec1, Integer num){
        List<String> termPoss = Segment.getPOS(eventName);
        List<String> eventWords = Segment.getWords(eventName);
        List<String> keyWords = new ArrayList<String>();
        //用来存放 所有关键词 取得的前50位相似性词的数组
        Set<WordEntry> eventWord_similarWords = new HashSet<WordEntry>();
        try {
//            vec1.loadJavaModel("D:\\project\\spidersManager\\data\\wikichinese.model");
            vec1.loadGoogleModel("D:\\project\\spidersManager\\data\\wiki_chinese_word2vec(Google).model");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //找到事件名分词词性为 “v” 的对应的词的扩充词
        for (int i = 0; i<termPoss.size(); i++){
            if (termPoss.get(i).equals("v")) {
                Set<WordEntry> similarWords = vec1.getSimilarWords(eventWords.get(i), num);
                System.out.println("与 "+eventWords.get(i)+" 语义相似的词语:"+similarWords.size());
                for (WordEntry word : similarWords) {
                    System.out.println(word.name + " : " + word.score);
                }
                eventWord_similarWords.addAll(similarWords);
            }
        }
        //将HashSet转换为List, 获得wordEntry中的word.
        List<WordEntry> wordEntryList = new ArrayList<WordEntry>(eventWord_similarWords);
        for (WordEntry wordEntry : wordEntryList){
            keyWords.add(wordEntry.name);
        }
        //将事件分词也加入到事件的近义词组中
        for (String eword : eventWords){
            keyWords.add(eword);
        }
        return keyWords;
    }

    //测试
    public static void main(String args[]){
        List<String> termList = getKeywordsPOS("上海女孩逃离江西", new Word2Vec(), 5);
        for (String term : termList){
            System.out.println(term);
        }
    }

    /**
     * 4.输入事件名，得到该事件相关的近义词组
     * @param eventName
     * @param vec1
     * @return
     */
    public static List<String> getKeyWords(String eventName, Word2Vec vec1){
        UserDefineLibrary.insertWord("一带一路", "n", 1000);
        UserDefineLibrary.insertWord("两学一做", "n", 1001);
        List<String> eventWords = Segment.getWords(eventName);
        //4.1 用来存放 所有关键词 取得的前10位相似性词的数组
        Set<WordEntry> eventWord_similarWords = new HashSet<WordEntry>();
//        Word2Vec vec_word = new Word2Vec();
        try {
//            vec1.loadJavaModel("D:\\project\\spidersManager\\data\\wikichinese.model");
            vec1.loadGoogleModel("D:\\project\\spidersManager\\data\\wiki_chinese_word2vec(Google).model");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String eword : eventWords) {
            //获取相似的词语
            Set<WordEntry> similarWords = vec1.getSimilarWords(eword, 2);
            System.out.println("与 "+eword+" 语义相似的词语:"+similarWords.size());
            for (WordEntry word : similarWords) {
                System.out.println(word.name + " : " + word.score);
            }
            eventWord_similarWords.addAll(similarWords);
        }
        //4.2利用前10位相似关键词与数据库中每条内容进行对比，构建相似度，并将结果 "相似度,文章" 存放到List<EventEssaySimi>中
        List<WordEntry> wordEntryList = new ArrayList<WordEntry>(eventWord_similarWords);
        //将HashSet转换为List, 获得wordEntry中的word.
        List<String> keyWords = new ArrayList<String>();
        for (WordEntry wordEntry : wordEntryList){
            keyWords.add(wordEntry.name);
        }
        //将事件分词也加入到事件的近义词组中
        for (String eword : eventWords){
            keyWords.add(eword);
        }
        return keyWords;
    }

    /**
     * 对文章的相似度进行排序
     * @param eventEssaySimis
     * @return
     */
    public static List<EventEssaySimi> getSortEventEssaySimis(List<EventEssaySimi> eventEssaySimis){
        Collections.sort(eventEssaySimis, new Comparator<EventEssaySimi>() {
            @Override
            public int compare(EventEssaySimi o1, EventEssaySimi o2) {
                //降序排序
                if (o1.getSimi() > o2.getSimi()){
                    return -1;
                }else if(o1.getSimi() < o2.getSimi()){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        return eventEssaySimis;
    }

    /**
     * 对命名实体数量进行排序
     * @param neInfos
     * @return
     */
    public static List<NamedEntityInfo> getNEInfoSort(List<NamedEntityInfo> neInfos){
        Collections.sort(neInfos, new Comparator<NamedEntityInfo>() {
            @Override
            public int compare(NamedEntityInfo o1, NamedEntityInfo o2) {
                //降序排序
                if (o1.getCount() > o2.getCount()){
                    return -1;
                }else if(o1.getCount() < o2.getCount()){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        return neInfos;
    }

}
