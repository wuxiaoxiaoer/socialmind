package com.sicdlib.util.NLPUtil.Word2VecUtil.Test;

import com.sicdlib.util.NLPUtil.Word2VecUtil.OtherUtil.Segment;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Vec.domain.WordEntry;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws Exception {
		Word2Vec vec = new Word2Vec();
		try {
			vec.loadGoogleModel("E:\\wiki_chinese_word2vec(Google).model");
//			vec.loadJavaModel("data/wikichinese.model");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//计算词语相似度
//		System.out.println("-----词语相似度-----");
//		System.out.println("狗|猫: " + vec.wordSimilarity("一带", "一路"));
//		System.out.println("计算机|电脑: " + vec.wordSimilarity("计算机", "电脑"));
//		System.out.println("计算机|人: " + vec.wordSimilarity("计算机", "人"));
		//获取相似的词语
//		Set<WordEntry> similarWords = vec.getSimilarWords("习近平", 10);
//		System.out.println("与 [花儿] 语义相似的词语:");
//		for(WordEntry word : similarWords) {
//			System.out.println(word.name + " : " + word.score);
//		}
		//计算句子相似度
//		System.out.println("-----句子相似度-----");
//		String s1 = "春节前夕,上海女孩逃离江西农村,上海女孩，春节前去“男朋友”家乡江西过年，被第一顿饭“吓”而逃离江西,受不了男友家贫困，决定和男友分手并立即回上海";
//		String s0 = "上海女孩逃离江西农村";
//		String s2 = "他说，凤凰男、孔雀女、城乡差距、阶层差距、贫富差距、逼婚，这些引发争议的社会现实，在“上海女逃饭”的故事中鲜明地表现出来，并以“乡村爱情故事”为包装，以春节为爆发点，让冲突变得尖锐而极具爆点。上海大都市与江西乡村，城市姑娘与“凤凰男”，还有灰暗影像中分不清食材的饭菜，以及参差不齐难成双的餐具，刺目地插在米饭上的不锈钢筷子，白富美逃离小山村，所有这些充满戏剧效果的因素,在众多人返乡过春节的大背景下，极易引起“乡愁”这个共鸣，所以成功吸引了大家的眼球。";
//		String s3 = "苏州是一座美丽的城市，四季分明，雨量充沛。";
		String test = "江西九江发生6.9级地震";
		String title = "新媒体是重新打造出来的：其一新媒体没有包袱，完全按照理想的商务模式架构运行规则，而老媒体的新化要面对历史包袱，兼顾原有运作模式，所以必然会在经营管理上多少打下折扣；其二新媒体是根据运行的需要去整合内容，在有多元内容供应者的情形下可以优化选择所需内容，而老媒体虽具有原有内容优势，但也往往被历史锁定在原有的内容特色之上，为此内容而改造新媒体，往往只落得把新媒体作为其原有内容的分布渠道之一来使用；其三新媒体由新人打造，新人新思路新资本使得新媒体的创意水平处在新高度上，而相反老媒体的老人老习惯即使嫁接上新模式新资本，其创新成效不如全新人才与全新模式的新媒体";
//		System.out.println("s0: " + s0);
//		System.out.println("s1: " + s1);
//		System.out.println("s2: " + s2);
//		System.out.println("s3: " + s3);
		//分词，获取词语列表
//		List<String> wordList1 = Segment.getWords(s1);
//		List<String> wordList2 = Segment.getWords(s2);
//		List<String> wordList3 = Segment.getWords(s3);
//		List<String> wordList0 = Segment.getWords(s0);
		List<String> wordList4 = Segment.getWords(test);
		List<String> wordList5 = Segment.getWords(title);
		//句子相似度(所有词语权值设为1)
//		System.out.println("句子相似度:");
//		System.out.println("s1|s1: " + vec.sentenceSimilarity(wordList1, wordList1));
//		System.out.println("s1|s2: " + vec.sentenceSimilarity(wordList1, wordList2));
		System.out.println("s1|s3: " + vec.sentenceSimilarity(wordList5,wordList4 ));
//		//句子相似度(名词、动词权值设为1，其他设为0.8)
//		System.out.println("句子相似度(名词、动词权值设为1，其他设为0.8):");
//		float[] weightArray1 = Segment.getPOSWeightArray(Segment.getPOS(s1));
//		float[] weightArray2 = Segment.getPOSWeightArray(Segment.getPOS(s2));
//		float[] weightArray3 = Segment.getPOSWeightArray(Segment.getPOS(s3));
//		float[] weightArray0 = Segment.getPOSWeightArray(Segment.getPOS(s0));
//		float[] weightArray4 = Segment.getPOSWeightArray(Segment.getPOS(test));
//		System.out.println("666666666666666:"+weightArray4);

//		System.out.println("s0|s1: " + vec.sentenceSimilarity(wordList0, wordList1, weightArray0, weightArray1));
//		System.out.println("s0|s2: " + vec.sentenceSimilarity(wordList0, wordList2, weightArray0, weightArray2));
//		System.out.println("s0|s3: " + vec.sentenceSimilarity(wordList0, wordList3, weightArray0, weightArray3));
//		System.out.println("s1|s0: " + vec.sentenceSimilarity(wordList1, wordList0, weightArray1, weightArray0));
//		try {
//			Word2Vec.trainJavaModel("data/train.txt", "data/test.model");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
