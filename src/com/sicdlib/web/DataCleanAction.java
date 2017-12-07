package com.sicdlib.web;


import com.google.gson.Gson;
import com.sicdlib.entity.CleanStrategyEntity;
import com.sicdlib.service.CleanStrategyService;
import com.sicdlib.service.DataCleanService;
import com.sicdlib.util.aop.ForController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//数据清洗
@Controller
@RequestMapping("/*")
public class DataCleanAction {
    @Autowired
    private DataCleanService dataCleanService;
    @Autowired
    private CleanStrategyService cleanStrategyService;
    @RequestMapping("dataClean")
    public String dataClean(HttpServletRequest req, HttpServletResponse response, Model model){
        List<List<CleanStrategyEntity>> strategyList =cleanStrategyService.getStrategies();
        model.addAttribute("loseDataList",strategyList.get(0));
        model.addAttribute("errorString",strategyList.get(1));
        model.addAttribute("recordOperating",strategyList.get(2));
        model.addAttribute("dateOperating",strategyList.get(3));
        model.addAttribute("addressOperating",strategyList.get(4));
        return "/WEB-INF/admin/dataClean";
    }
// tbForm函数中的第四个参数currentTable是自己加的，用于PhoenixUtil调用本函数
@ForController(description="tbForm")
    @RequestMapping("/admin/TableServlet")
    public void tbForm(HttpServletRequest request, HttpServletResponse response, Model model, String currentTable) throws IOException {
        request.setCharacterEncoding("UTF-8");//传值编码
        response.setCharacterEncoding("UTF-8");
//        获取到表的名字
        String tablename =request.getParameter("tablename");
        //获取thead和tbody的值
//        List<String> tHeadValue = calendarService.getThead(tablename);
        List<String> tBodyValue = dataCleanService.getTbody(tablename);
//        model.addAttribute("tHeadValue",tHeadValue);
//        model.addAttribute("tbodyvalue",tbodyvalue);

        PrintWriter out=response.getWriter();       //向客户端发送字符数据
        response.setContentType("text/text");          //设置请求以及响应的内容类型以及编码方式
//    JSONArray json = JSONArray.fromObject(tHeadValue);   //将对象转换为json对象
//    String str = json.toString();                //将json对象转换为字符串
        Gson gson = new Gson();
//        String strHead = gson.toJson(tHeadValue);
        String strBody = gson.toJson(tBodyValue.get(0))+"++"+gson.toJson(tBodyValue.get(2));
        System.out.println("发送给前台:"+strBody);
    out.write(strBody);                     //将str字符传输到前台
    }

    @RequestMapping("/admin/ClickTableServlet")
    public void clickTable(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        request.setCharacterEncoding("UTF-8");//传值编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/text");          //设置请求以及响应的内容类型以及编码方式
        String tableName =request.getParameter("tableName");
        String columnName =request.getParameter("column");
        LinkedHashMap<String,Integer> orderResult = dataCleanService.getOrder(tableName,columnName);

        System.out.println("controller中的result:"+orderResult);
        PrintWriter out = response.getWriter();       //向客户端发送字符数据
        try{
//        Gson gson = new Gson();
////        String strHead = gson.toJson(tHeadValue);
//        String strBody = gson.toJson(result.get(0));

            JSONObject jsonObject = JSONObject.fromObject(orderResult);
            System.out.println("输出的结果是：" + jsonObject);
            //3、将json对象转化为json字符串
            String result = jsonObject.toString();
            out.write(result);
        }catch(Exception e){
            System.out.println(e);
            System.out.println("函数getOrder得到的map键有空值");
            out.write("此列有空值!");
        }finally {
            return;
        }
    }
    @RequestMapping("/admin/cleanProcessAction")

    public void cleanProcess(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        request.setCharacterEncoding("UTF-8");//传值编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/text");          //设置请求以及响应的内容类型以及编码方式
        String currentTable =request.getParameter("currentTable");
        String currentColumn =request.getParameter("currentColumn");
        String strategyID =request.getParameter("strategyID");
        String oldValue =request.getParameter("oldValue");
        String newValue =request.getParameter("newValue");
//        System.out.println("currentTable:"+currentTable+"\n"+"currentColumn:"+currentColumn+"\n"+"strategyID"+strategyID);
        Boolean cleanResult =dataCleanService.doClean(currentTable,currentColumn,strategyID,oldValue,newValue);
    }
}
