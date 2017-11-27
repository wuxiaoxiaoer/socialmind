package com.sicdlib.util.NLPUtil.HanLPUtil;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.sicdlib.entity.NamedEntityInfo;
import org.junit.Test;

import java.util.*;

/**
 * Created by DeMH on 2017/7/5.
 */
public class HanLPUtil {

    /**
     * 输入一句话，得到实体命名信息（人物、）
     * @param tableName：表名tableName
     * @param content: 要分词的内容
     * @param neInfos
     * @return
     */
    public static List<NamedEntityInfo> getNERInfo(String tableName, String content, List<NamedEntityInfo> neInfos){
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        List<Term> termList = segment.seg(content);
        int flag = 0;
        for (Term term : termList){
            if(term.nature.equals(Nature.nr)){
                //如果namedEntityInfos中存在该word
                for (NamedEntityInfo nei : neInfos){
                    if (nei.getName().equals(term.word)){
                        nei.setCount(nei.getCount()+1);
                        flag = 1;
                        break;
                    }
                }
                //如果namedEntityInfos中不存在该word, 该词第一次出现
                if (flag == 0){
                    NamedEntityInfo nei = new NamedEntityInfo();
                    nei.setNature(Nature.nr);
                    nei.setName(term.word);
                    nei.setCount(1);
                    nei.setWebsiteName(tableName);
                    neInfos.add(nei);
                }
            }
        }

        //输出
        for (NamedEntityInfo nei : neInfos){
            System.out.println(nei.getNature() + ":" + nei.getName() + ":" + nei.getCount());
        }
        return neInfos;
    }

    /**
     * NER：人名
     * @param text：要NER的文本
     * @return
     */
    public static HashMap<String, String> getNERPersonsMap(String text){
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        List<Term> termList = segment.seg(text);
        HashMap<String, String> persons = new HashMap<>();
        for (Term term : termList) {
            //Nature.nr 表示人名
            if (term.nature.equals(Nature.nr)){
                persons.put(term.word, "");
            }
        }
        return persons;
    }

    /**
     * NER：地名
     * @param text：要NER的文本
     * @return
     */
    public static HashMap<String, String> getNERPlacesMap(String text){
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        List<Term> termList = segment.seg(text);
        HashMap<String, String> places = new HashMap<>();
        for (Term term : termList) {
            //Nature.nr 表示人名
            if (term.nature.equals(Nature.ns)){
                places.put(term.word, "");
            }
        }
        return places;
    }

    /**
     * NER：机构名
     * @param text：要NER的文本
     * @return
     */
    public static HashMap<String, String> getNEROrganizationsMap(String text){
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        List<Term> termList = segment.seg(text);
        HashMap<String, String> orgs = new HashMap<>();
        for (Term term : termList) {
            //Nature.nr 表示人名
            if (term.nature.equals(Nature.nt)){
                orgs.put(term.word, "");
            }
        }
        return orgs;
    }

    /**
     * SEG：动词
     * @param text：得到分词的动词
     * @return
     */
    public static List<String> getNERVerbs(String text){
        Segment segment = HanLP.newSegment();
        List<Term> termList = segment.seg(text);
        List<String> verbs = new ArrayList<>();
        for (Term term : termList) {
            //Nature.v 表示动词
            if (term.nature.equals(Nature.v)){
                verbs.add(term.word);
            }
        }
        return verbs;
    }

    /**
     * 得到带有权值的人名、地名、组织名列表
     * 对text进行NER, 根据不同权值NERNameValue返回人名、地名、组织名、动词列表
     */
    public static List<NERNameValue> getNERNameValuesMap(String text){
        HashMap<String, String> persons = getNERPersonsMap(text);
        HashMap<String, String> places = getNERPlacesMap(text);
        HashMap<String, String> orgs = getNEROrganizationsMap(text);
        List<String> verbs = getNERVerbs(text);
        List<NERNameValue> nerNameValues = new ArrayList<>();
        Iterator iter_psn = persons.entrySet().iterator();
        while (iter_psn.hasNext()){
            Map.Entry entry = (Map.Entry)iter_psn.next();
            //为了统一权重，必须以这种构造方法填充
            NERNameValue ner = new NERNameValue(entry.getKey().toString(),"person");
            nerNameValues.add(ner);
        }
        Iterator iter_plc = places.entrySet().iterator();
        while (iter_plc.hasNext()){
            Map.Entry entry = (Map.Entry)iter_plc.next();
            //为了统一权重，必须以这种构造方法填充
            NERNameValue ner = new NERNameValue(entry.getKey().toString(),"place");
            nerNameValues.add(ner);
        }
        Iterator iter_org = orgs.entrySet().iterator();
        while (iter_plc.hasNext()){
            Map.Entry entry = (Map.Entry)iter_plc.next();
            //为了统一权重，必须以这种构造方法填充
            NERNameValue ner = new NERNameValue(entry.getKey().toString(),"org");
            nerNameValues.add(ner);
        }
        return nerNameValues;
    }

    /**
     * 得到不重复的分词
     */
    public static List<String> getSegMaptoList(String text){
        HashMap<String, String> segs = new HashMap<>();
        Segment segment = HanLP.newSegment();
        List<Term> termList = segment.seg(text);
        for (Term term : termList){
            segs.put(term.word, "");
        }
        List<String> seg_txt1 = new ArrayList<>();
        Iterator iter_seg = segs.entrySet().iterator();
        while (iter_seg.hasNext()){
            Map.Entry entry = (Map.Entry) iter_seg.next();
            seg_txt1.add(entry.getKey().toString());
        }
        return seg_txt1;
    }

    /**
     * 得到text中所有的分词ners1：并NER赋予不同的权重
     */
    public static List<NERNameValue> getAllNERAndSegs(List<NERNameValue> ners1, String text){
        List<String> segs_txt1 = HanLPUtil.getSegMaptoList(text);
        //将seg分词
        for (String seg : segs_txt1) {
            int flag = 0;
            for (NERNameValue ner1 : ners1){
                if (seg.equals(ner1.getName())) {
                    flag = 1;break;
                }
            }
            if (flag == 0){
                NERNameValue ner = new NERNameValue(seg, "normal");
                ners1.add(ner);
            }
        }
        return ners1;
    }

    /**
     * 从text中获得与ners1相同的词
     */
    public static List<NERNameValue> getNER2ByNameValue(List<NERNameValue> ners1, String text){
        List<NERNameValue> ners2 = new ArrayList<>();
        HashMap<String, String> segs = new HashMap<>();
        Segment segment = HanLP.newSegment();
        List<Term> termList = segment.seg(text);
        for (Term term : termList){
            segs.put(term.word, "");
        }
        List<String> seg_txt1 = new ArrayList<>();
        Iterator iter_seg = segs.entrySet().iterator();
        while (iter_seg.hasNext()){
            Map.Entry entry = (Map.Entry) iter_seg.next();
            seg_txt1.add(entry.getKey().toString());
        }
        for (NERNameValue ner : ners1){
            if (seg_txt1.contains(ner.getName())){
                NERNameValue ner2 = new NERNameValue(ner.getName(), ner.getCategory());
                ners2.add(ner2);
            }
        }
        return ners2;
    }

    @Test
    public void test01(){
        String text_per = "签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。";
        String text_plc = "蓝翔给宁夏固原市彭阳县红河镇黑牛沟村捐赠了挖掘机";
        String text_org = "2016年7月，北京大学出版社";
        HashMap<String, String> persons = getNERPersonsMap(text_per);
        HashMap<String, String> places = getNERPlacesMap(text_plc);
        HashMap<String, String> orgs = getNEROrganizationsMap(text_org);
    }
}
