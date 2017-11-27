package com.sicdlib.util.NLPUtil.Word2VecUtil.OtherUtil;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.recognition.impl.FilterRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Segment {

	public static void main(String[] args) {
		String str = "值此“一带一路”国际合作高峰论坛召开之际，" +
				"作为共建“一带一路”倡议的发起者，中国发表《共建“一带一路”：理念、实践与中国的贡献》，" +
				"以期增进国际社会对共建“一带一路”倡议的进一步了解，展示共建“一带一路”的丰富成果，" +
				"增进各国战略互信和对话合作，为携手打造你中有我、" +
				"我中有你的人类命运共同体作出新的更大贡献。";
		UserDefineLibrary.insertWord("一带一路", "n", 1000);
		Result terms = ToAnalysis.parse(str);
		System.out.println(terms.toString());
	}

	/**
	 * 分词
	 * @param sentence 待分词的句子
	 * @return 分词结果
	 */
	public static List<Term> Seg(String sentence) {
		FilterRecognition filter = new FilterRecognition();
		//过滤标点符号
		filter.insertStopWord(",", " ", ".", "，", "。", ":", "：", "'", "‘", "’", "　", "“", "”", "《", "》", "[", "]", "-");
		//增加用户自定义词库,格式为：“自定义词,词性,数字”。
		UserDefineLibrary.insertWord("一带一路", "n", 1000);
		return ToAnalysis.parse(sentence).recognition(filter).getTerms();
	}
	
	/**
	 * 获取词语列表
	 * @param sentence 待分词的句子
	 * @return 分词后的词语列表
	 */
	public static List<String> getWords(String sentence) {
		List<Term> termList = Seg(sentence);
		List<String> wordList = new ArrayList<String>();
		for (Term wordTerm : termList) {
			wordList.add(wordTerm.getName());
		}
		return wordList;
	}
	/**
	 * 获取词性列表
	 * @param sentence 带分词的句子
	 * @return 分词后的词性列表
	 */
	public static List<String> getPOS(String sentence) {
		List<Term> termList = Seg(sentence);
		List<String> natureList = new ArrayList<String>();
		for (Term wordTerm : termList) {
			natureList.add(wordTerm.getNatureStr());
		}
		return natureList;
	}
	/**
	 * 获取词性权值数组
	 * @param posList 词性列表
	 * @return 词性列表对应的权值数组
	 */
	public static float[] getPOSWeightArray(List<String> posList) {
		float[] weightVector = new float[posList.size()];
		for (int i = 0; i < weightVector.length; i++) {
			String POS = posList.get(i);
			switch(POS.charAt(0)) {
			case 'n':
			case 'v':weightVector[i] = 1;break;
			default:weightVector[i] = (float) 0.8;break;
			}
		}
		return weightVector;
	}

}
