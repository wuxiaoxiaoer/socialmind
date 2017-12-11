package com.sicdlib.web;

import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.Mysql.HbaseMysqlColumnsMapping;
import com.sicdlib.util.NLPUtil.HanLPUtil.HanLPUtil;
import com.sicdlib.util.NLPUtil.HanLPUtil.NERNameValue;
import com.sicdlib.util.NLPUtil.SimilarityUtil.ConsineSimi;
import com.sicdlib.util.NLPUtil.Word2VecUtil.OtherUtil.Segment;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Test.Word2Vec;
import com.sicdlib.util.PhoenixUtil.PhoenixUtil;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author
 * 舆情对象相似度
 */
@Controller
@RequestMapping("/*")
public class ObjectSimilarityAction {

	@Autowired(required=true)
	private ArticleEntityService articleEntityService;
	@Autowired
	private DataSourceEntityService dataSourceEntityService;
	@Autowired
	private ObjectEntityService objectEntityService;
	@Autowired
	private AuthorEntityService authorEntityService;
	@Autowired
	private ArticleCommentEntityService articleCommentEntityService;

	//相似度测试
	@RequestMapping(value="test01")
	public String test01(HttpServletRequest req) throws Exception {
		Word2Vec vec = new Word2Vec();
		try {
			vec.loadGoogleModel("D:\\project\\wiki_chinese_word2vec(Google).model");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String eventName = "春节前夕,上海女孩逃离江西农村,上海女孩，春节前去“男朋友”家乡江西过年，被第一顿饭“吓”而逃离江西,受不了男友家贫困，决定和男友分手并立即回上海";
		List<NERNameValue> event_nerNameValues = getTextNERWord2Vec(eventName);
		String content = req.getParameter("content" );
		List<NERNameValue> titleCnt_nerNameValues = getTextNERWord2Vec(content);
		Double simiCompare = ConsineSimi.calSimiByNERNameValue(event_nerNameValues, titleCnt_nerNameValues, content);
		System.out.println("相似度（带有word2Vec）1：" + simiCompare);
		//方法2：使用word2Vec
		List<String> wordList1 = Segment.getWords(eventName);
		float[] weightArray1 = Segment.getPOSWeightArray(Segment.getPOS(eventName));
		List<String> wordList2 = Segment.getWords(content);
		float[] weightArray2 = Segment.getPOSWeightArray(Segment.getPOS(content));
		System.out.println("相似度（word2Vec）: " + vec.sentenceSimilarity(wordList1, wordList2, weightArray1, weightArray2));
		return "";
	}

	/**
	 * 根据事件名称及描述从hbase中搜索相关文章存入mysql数据库
	 * 已跑代码：bbs_news_post_copy、bbs_mop_post_copy、bbs_china_post_copy、bbs_tianya_post_copy、
	 */
	@RequestMapping(value="similarity")
	public String similarity(HttpServletRequest req) throws Exception {
		Double simi = Double.parseDouble(req.getParameter("simi"));
		String eventName = "春节前夕,上海女孩逃离江西农村,上海女孩，春节前去“男朋友”家乡江西过年，被第一顿饭“吓”而逃离江西,受不了男友家贫困，决定和男友分手并立即回上海";
		String eventKeywords = "上海女逃离江西农村";
		ObjectEntity object = new ObjectEntity();
		PropertyFilter filterObjectEname = new PropertyFilter("name", eventKeywords);
		List<ObjectEntity> objectsEventKeyList = objectEntityService.search(filterObjectEname);
		if (objectsEventKeyList.size() != 0){
			object = objectsEventKeyList.get(0);
		}else {
			String objectID = UUID.randomUUID().toString();
			object.setObjectId(objectID);
			object.setName(eventKeywords);
		}
		objectEntityService.saveOrUpdate(object);
		/**
		 *1、对事件eventName进行事件语义表示：word2vec + 词性权重语义扩充
		 */
		Word2Vec vec = new Word2Vec();
		try {
			vec.loadGoogleModel("D:\\project\\wiki_chinese_word2vec(Google).model");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> wordList1 = Segment.getWords(eventName);
		float[] weightArray1 = Segment.getPOSWeightArray(Segment.getPOS(eventName));
		/**
		 * 2.1 得到mysql中article表需要的数据字段 hmcolumnsmapping
		 * 2.2 根据hmcolumnsmapping从hbase中获得相关字段数据result
		 *   2.2.1 首先从mysql的datasource数据表中获得hbase中存在哪些表datasources
		 *   2.2.2 从datasources中获得带有(_post)形式的文章表datasouece
		 *   2.2.3 根据datasouece中的count属性获得hbase文章表的总条数，从hbase中分批获得得到result(其中,这里起始start=0,num=100)
		 * 2.3 对数据result中的content和title按照(1、)中方式进行NER和语义词扩充得到contentNerValues
		 * 2.4 (1、)中获得的event_nerNameValues与contentNerValues进行相似性比对得到相似度simiCompare
		 * 2.5 如果simiCompare大于设定阈值simi, 则将mysql中需要的数据保存到mysql中
		 */
		//2.1 得到mysql中article表需要的数据字段 hmcolumnsmapping
		Map<String, String[]> hmcolumnsmapping = HbaseMysqlColumnsMapping.getHbaseMysqlColumnsMapping();
		//获得Hbase表中数据
		//String tableName = "bbs_china_post_copy";
		//2.2.1 首先从mysql的datasource数据表中获得hbase中存在哪些表datasources
		List<DataSourceEntity> datasources = dataSourceEntityService.loadAll();
		//设置自定义读取的数据源表
		List<DataSourceEntity> setupDs = getSetupDataSources(datasources);
		List<Map<String, Object>> result = new ArrayList<>();
		for (DataSourceEntity datasource : setupDs){
			//2.2.2 从datasources中获得带有_post形式的文章表datasouece
			// 后面新增：以_news结尾datasource.getTableName().endsWith("_news")
			if (datasource.getTableName().contains("_post")){
				int per = 100;
				int NUM = Integer.parseInt(datasource.getCount()) / per;
				int i = 0;
				if (datasource.getTableName().equals("bbs_people_post_copy")){
					i = 925; //tianya: i = 382383;走不过去
				}
				for (; i< NUM; i++){
					int start = i * per;
					//2.2.3 根据datasouece中的count属性获得hbase文章表的总条数，从hbase中分批获得得到result
					//获得所有的hbase表:先使用 bbs_china_post_copy
					String sql = "SELECT * FROM \""+datasource.getTableName()+"\" AS a INNER JOIN (SELECT \"info\".\"post_id\" FROM \""+datasource.getTableName()+"\" LIMIT "+ per +" OFFSET "+start+") AS b ON a.\"post_id\" = b.\"post_id\"";
//					String sql = "select * from \""+ datasource.getTableName() + "\" limit "+ per +" offset " + (start+1);
					result = PhoenixUtil.selectHbaseBySql(sql);
					System.out.println("第  "+ start + "  条数 : " + result.size());
					//保存article文件
					for (Map<String, Object> row : result){
						//生成articleID
						String articleID = UUID.randomUUID().toString();
						//获得key和obj的拼接sql:mysql字段和hbase值，key形如"articleID, title, content"；obj形如"123, '标题', '内容'";
						Map<String, List<String>> keyObjsMap = getKeyObjects("articleID", articleID, row, hmcolumnsmapping);
						List<String> keyList = keyObjsMap.get("keyList");
						List<String> objList = keyObjsMap.get("objList");
						String title = "";
						String content = "";
						//如果不为空且包含标题或内容
						if (keyList != null && objList != null && keyList.contains("title") || keyList.contains("content")){
							for (int j = 0 ;j < keyList.size(); j++){
								if (keyList.get(j).equals("title")){
									title = objList.get(j);
								}
								if (keyList.get(j).equals("content")){
									content = objList.get(j);
								}
							}
						}
						List<String> wordList2 = Segment.getWords(content+title);
						float[] weightArray2 = Segment.getPOSWeightArray(Segment.getPOS(content+title));
						Double simiCompare = Double.parseDouble(vec.sentenceSimilarity(wordList1, wordList2, weightArray1, weightArray2)+"");
						System.out.println("相似度：" + simiCompare + " : " + (simiCompare >= simi));
						if (simiCompare >= simi){
							System.out.println("标题和内容：" + title+content);
							articleEntityService.insertArticleByPropAndValue(keyObjsMap.get("keyStr").get(0), keyObjsMap.get("objStr").get(0));
							PropertyFilter filter_ds = new PropertyFilter("tableName", datasource.getTableName());
							DataSourceEntity dataSourceEntity = dataSourceEntityService.search(filter_ds).get(0);
							ArticleEntity articleEntity = articleEntityService.load(articleID);
							System.out.println("article UUID：" + articleID);
							System.out.println("articleId是：" + articleEntity.getArticleId());
							articleEntity.setSimilarDegree(simiCompare);
							//设置article源文章所属源表datasource
							//articleEntity.setDataSourceEntity(dataSourceEntity);
							//设置website ******带测试******
							articleEntity.setWebsiteEntity(datasource.getWebsiteEntity());
							//设置article所属舆情对象object
							articleEntity.setObjectEntity(object);
							articleEntityService.saveOrUpdate(articleEntity);
						}
					}
				}
			}
		}
		return "/WEB-INF/foreground/similarity";
	}

	/**
	 * **********待测试****************
	 * 保存作者表：_Author表
	 * 根据事件名查找文章，然后保存作者表
	 * 从article表中获得datasource与authorID
	 * 根据datasource与authorID查询hbase记录，然后拼接相关字段保存
	 */
	@RequestMapping("saveAuthor")
	public void saveAuthor(HttpServletRequest req){
		String eventName = req.getParameter("eventName");
		PropertyFilter filter = new PropertyFilter("name" ,eventName);
		List<ObjectEntity> objectsByNameList = objectEntityService.search(filter);
		ObjectEntity object = new ObjectEntity();
		if (objectsByNameList.size() != 0){
			object = objectsByNameList.get(0);
		}
		Set<ArticleEntity> articles = object.getArticles();
		for (ArticleEntity article : articles){
			WebsiteEntity website = article.getWebsiteEntity();
			PropertyFilter filterWeb = new PropertyFilter("websiteEntity", website);
			List<DataSourceEntity> dataSourcesWebList = dataSourceEntityService.search(filterWeb);
			DataSourceEntity dataSourceAuthor = getDataSource(dataSourcesWebList, "_author");
			String sourceAuthorId = article.getSourceAuthorId();
			//判断是否为在mysql中存在，不存在则新增
			PropertyFilter filter_auth = new PropertyFilter("sourceAuthorId", sourceAuthorId);
			List<AuthorEntity> authorList = authorEntityService.search(filter_auth);
			if (authorList.size() == 0) {
				//hbase查询：article对应的author表
				String sql = "select * from \""+ dataSourceAuthor.getTableName() + "\" where \"author_id\" = '" + sourceAuthorId +"'";
				List<Map<String, Object>> result = PhoenixUtil.selectHbaseBySql(sql);
				if (result.size() != 0){
					for (Map<String, Object> row : result){
						String authorID = UUIDUtil.getUUID();
						Map<String, List<String>> keyObjsMap = getKeyObjects("authorID", authorID, row, HbaseMysqlColumnsMapping.getAuthorHbaseMysqlColumnsMapping());
						System.out.println("keyStr：" + keyObjsMap.get("keyStr").get(0));
						System.out.println("objStr：" + keyObjsMap.get("objStr").get(0));
						authorEntityService.insertAuthorByPropAndValue(keyObjsMap.get("keyStr").get(0), keyObjsMap.get("objStr").get(0));
						//建立author与article的关系
						AuthorEntity author = authorEntityService.load(authorID);
						article.setAuthorEntity(author);
						articleEntityService.saveOrUpdate(article);
					}
				}
			}
		}
	}

	/**
	 * 保存article的相关评论comments
	 * 根据事件名称获得事件articles
	 * 根据每篇文章article获得相关的评论comments，不为0，则不保存。
	 * 如果为0，则根据评论表和源文章id (dataSourceComment/sourceArticleId从hbase中获得评论，并保存到mysql的评论表中comment。
	 */
	@RequestMapping("saveComments")
	public void saveComments(HttpServletRequest req){
		String eventName = req.getParameter("eventName");
		PropertyFilter filter = new PropertyFilter("name" ,eventName);
		List<ObjectEntity> objectsByNameList = objectEntityService.search(filter);
		ObjectEntity object = new ObjectEntity();
		if (objectsByNameList.size() != 0){
			object = objectsByNameList.get(0);
		}
		Set<ArticleEntity> articles = object.getArticles();
		for (ArticleEntity article : articles){
			String sourceArticleId = article.getSourceArticleId();
			Set<ArticleCommentEntity> comments = article.getArticleComments();
			if (comments.size() == 0){
				WebsiteEntity website = article.getWebsiteEntity();
				PropertyFilter filterWeb = new PropertyFilter("websiteEntity", website);
				List<DataSourceEntity> dataSourcesWebList = dataSourceEntityService.search(filterWeb);
				DataSourceEntity dataSourceComment = getDataSource(dataSourcesWebList, "_comment");
				//hbase查询
				String sql = "select * from \""+ dataSourceComment.getTableName() + "\" where \"post_id\" ='" + sourceArticleId + "'";
				List<Map<String, Object>> result = PhoenixUtil.selectHbaseBySql(sql);
				for (Map<String, Object> row : result){
					String article_commentID = UUIDUtil.getUUID();
					//获得key和obj的拼接sql:mysql字段和hbase值，key形如"articleID, title, content"；obj形如"123, '标题', '内容'";
					Map<String, List<String>> keyObjsMap = getKeyObjects("article_commentID", article_commentID, row, HbaseMysqlColumnsMapping.getCommentHbaseMysqlColumnsMapping());
					articleCommentEntityService.insertCommentByPropAndValue(keyObjsMap.get("keyStr").get(0), keyObjsMap.get("objStr").get(0));
					//建立评论与文章表、评论与作者表之间的关系
					ArticleCommentEntity articleComment = articleCommentEntityService.load(article_commentID);
					articleComment.setArticleEntity(article);

					String authorId = String.valueOf(row.get("user_id"));
					if( authorId== null){
						authorId = String.valueOf(row.get("author_id"));
					}

					//判断是否为在mysql中存在，不存在则新增
					PropertyFilter filterAuth = new PropertyFilter("sourceAuthorId", authorId);
					List<AuthorEntity> authorList = authorEntityService.search(filterAuth);
					WebsiteEntity web = article.getWebsiteEntity();
					PropertyFilter fWeb = new PropertyFilter("websiteEntity", web);
					List<DataSourceEntity> dsWebList = dataSourceEntityService.search(fWeb);
					DataSourceEntity dsAuthor = getDataSource(dsWebList, "_author");
					if (authorList.size() == 0) {
						//hbase查询：article对应的author表
						String auth_sql = "select * from \""+ dsAuthor.getTableName() + "\" where \"author_id\" = '" + authorId +"'";
						List<Map<String, Object>> resultAuth = PhoenixUtil.selectHbaseBySql(auth_sql);
						if (resultAuth.size() != 0){
							for (Map<String, Object> r : resultAuth){
								String authorID = UUIDUtil.getUUID();
								Map<String, List<String>> keyObjsMapAuth = getKeyObjects("authorID", authorID, r, HbaseMysqlColumnsMapping.getAuthorHbaseMysqlColumnsMapping());
								authorEntityService.insertAuthorByPropAndValue(keyObjsMapAuth.get("keyStr").get(0), keyObjsMapAuth.get("objStr").get(0));
								AuthorEntity authorTemp = authorEntityService.load(authorID);
								articleComment.setAuthorEntity(authorTemp);
							}
						}
					}

					AuthorEntity author = article.getAuthorEntity();
					articleComment.setAuthorEntity(author);
					articleCommentEntityService.saveOrUpdate(articleComment);
				}
			}
		}
	}

	/**
	 * 设置自定义的要读取的hbase数据源表
	 * @param datasources
	 * @return
	 */
	public List<DataSourceEntity> getSetupDataSources(List<DataSourceEntity> datasources){
		List<DataSourceEntity> setupDsList = new ArrayList<>();
		String []setupDsNames = new String[]{"bbs_people_post_copy"};
		List<String> dsNamesList = Arrays.asList(setupDsNames);
		for (DataSourceEntity ds : datasources){
			if (dsNamesList.contains(ds.getTableName())){
				setupDsList.add(ds);
			}
		}
		return setupDsList;
	}

	/**
	 * 通过文章获得的源表和匹配字符串pattern获得同一网站下的源表
	 */
	public static DataSourceEntity getDataSource(List<DataSourceEntity> dataSourcesWebList, String pattern){
		//获得与article所在的源表同一个网站website下的作者author表
		DataSourceEntity dataSourceAuthor = new DataSourceEntity();
		for (DataSourceEntity ds : dataSourcesWebList){
			if (ds.getTableName().contains(pattern)){
				dataSourceAuthor = ds;
			}
		}
		return dataSourceAuthor;
	}

	/**
	 * 得到文本的NER和word2vec的语义表示
	 * @param text
	 * @return
	 */
	public List<NERNameValue> getTextNERWord2Vec(String text){
		//1.1 将事件分词进行NER获得带有权值的人名、地名、组织名列表
		List<NERNameValue> event_nerNameValues = HanLPUtil.getNERNameValuesMap(text);
		//将NER，text传入获得所有分词并赋予权重
		event_nerNameValues = HanLPUtil.getAllNERAndSegs(event_nerNameValues, text);
		//1.2 还需要word2vec扩充事件动词V的同义词. NER和V动词扩充构成event_nerNameValues的事件语义表示
		Word2Vec vec = new Word2Vec();
		List<String> keyWords = CommmonUtil.getKeywordsPOS(text, vec, 1);
		for (String kw : keyWords){
			NERNameValue ner = new NERNameValue(kw, "normal");
			event_nerNameValues.add(ner);
		}
		return event_nerNameValues;
	}

	/**
	 * 输入hbase表名(tableName),开始条(start),有限条数(num)，从Hbase中获得数据(result)返回
	 * @param tableName
	 * @param start
	 * @param num
	 * @return
	 */
	public static List<Map<String, Object>> getHbaseData(String tableName, int start, int num){
		//获得所有的hbase表:先使用bbs_china_post_copy
		String sql = "select * from \""+ tableName + "\" limit 10 offset 1";
		List<Map<String, Object>> result = PhoenixUtil.selectHbaseBySql(sql);
		return result;
	}

	/**
	 * 获得要保存的sql语句中属性和值的拼接字符串分别为keyStr和objStr
	 * 输入1行row,mysql行,输出string[2],分别为key:Mysql中的字段；obj:hbase中的值
	 * 两种形式返回key和obj，分别是拼接字符串形式和list列表形式
	 * @param IDField ID字段名称
	 * @param ID ID字段值
	 * @param row hbase中一行
	 * @param hmcolumnsmapping mysql与hbase匹配字段表
	 * @return
	 */
	public static Map<String, List<String>> getKeyObjects(String IDField, String ID, Map<String, Object> row, Map<String, String[]> hmcolumnsmapping){
		Map<String, List<String>> articleKeyObj = new HashMap<>();
		List<String> objList = new ArrayList<>();
		List<String> keyList = new ArrayList<>();
		List<String> keyStrList = new ArrayList<>();
		List<String> objStrList = new ArrayList<>();
		String [] keyAndObj = new String[2];
		//mysql数据字段
		String keyStr = ""+IDField+", ";
		//mysql值
		String objStr = "'"+ID+"', ";
		Iterator entries = row.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry)entries.next();
			String key = (String) entry.getKey();
			String obj = (String) entry.getValue();
			String column = isExistInMysql(hmcolumnsmapping, key);
			//如果存在
			if (!column.equals("-1")){
				keyList.add(key);
				keyStr += column + ", ";
				if (key.contains("_num")){
					objList.add(obj);
					objStr += obj + ", ";
				}
				else if (key.contains("content") || key.contains("title")){
					objList.add(obj);
					//将内容中英文:转为：
					if (obj != null){
						obj = getContentEngToCn(obj);
						obj = getESCStr(obj);
					}
					objStr += "'"+ obj + "', ";
				}
				else {
					objList.add(obj);
					if (obj != null){
						//对值进行转义
						obj = getESCStr(obj);
					}
					objStr += "'"+ obj + "', ";
				}
			}
		}
		keyStr = keyStr.substring(0, keyStr.length()-2);
		objStr = objStr.substring(0, objStr.length()-2);
		keyStrList.add(keyStr);
		objStrList.add(objStr);
		articleKeyObj.put("keyStr", keyStrList);
		articleKeyObj.put("objStr", objStrList);
		articleKeyObj.put("keyList", keyList);
		articleKeyObj.put("objList", objList);
		return articleKeyObj;
	}

	/**
	 * 转义字符串： 将'or" 变为\'和\"
	 */
	public static String getESCStr(String str){
		if (str.indexOf("\'") >= 0){
			str = str.replaceAll("\'", "\\\\'");
		}
		if (str.indexOf("\"") >= 0){
			str = str.replaceAll("\"", "\\\\\"");
		}
		if (str.indexOf("%") >= 0){
			str = str.replaceAll("%", "\\\\%");
		}
		return str;
	}
	/**
	 * 转义字符串： 将'or" 变为\'和\"
	 */
	public static String getContentEngToCn(String str){
		if (str.indexOf(":") >= 0){
			str = str.replaceAll(":", "：");
		}
		return str;
	}
	/**
	 * 判断hbase数据字段是否存在于mysql字段中，存在,则返回mysql字段；不存在，则返回-1
	 * @param hmcolumnsmapping
	 * @return
	 */
	public static String isExistInMysql(Map<String, String[]> hmcolumnsmapping, String hbaseColumn){
		Iterator entries_hmcolumn = hmcolumnsmapping.entrySet().iterator();
		while (entries_hmcolumn.hasNext()){
			Map.Entry entry_hmcolumn = (Map.Entry)entries_hmcolumn.next();
			String column = entry_hmcolumn.getKey().toString();
			String[] mysqlColumns = (String[]) entry_hmcolumn.getValue();
			List<String> columnsList = Arrays.asList(mysqlColumns);
			if (columnsList.contains(hbaseColumn)){
				return column;
			}
		}
		return "-1";
	}

}
