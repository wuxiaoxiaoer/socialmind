package com.sicdlib.web;

import com.sicdlib.entity.RoleEntity;
import com.sicdlib.entity.UserEntity;
import com.sicdlib.service.*;
import com.sicdlib.util.ToolkitUtil.IdCardInfoDetailUtil;
import com.sicdlib.util.ToolkitUtil.IdCardInfoUtil;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wlw
 */
//用户管理模块
@Controller
@RequestMapping("/*")
public class UserAnalysisAction {

	@Autowired(required=true)
	private UserService userService;
	@Autowired
	private UserEntityService userEntityService;
	@Autowired
	private PersonUserEntityService personUserEntityService;
    @Autowired
    private CompanyUserEntityService companyUserEntityService;
    @Autowired
    private InstitutionUserEntityService institutionUserEntityService;
    @Autowired
    private GovUserEntityService govUserEntityService;
    @Autowired
    private RoleEntityService roleEntityService;
    @Autowired
    private AdminEntityService adminEntityService;

    @RequestMapping("sysusersAnalysis")
    public String get(HttpServletRequest req, Model mode) throws ParseException {
        //获得用户信息：根据最近1周、最近1月、最近1年、整个周期
        //unit：week, month, year, ALL
        String unit = req.getParameter("unit");
        //times：7, 15, 12, 10次
        String times = req.getParameter("times");
        //unit,times,interval(时间间隔)
        String perieds[] = {unit, times, ""};
        Long allInterval = Long.valueOf(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        if (unit.equals("ALL")){
            UserEntity minRegTimeUser = userEntityService.getMaxOrMinUser("MIN");
            UserEntity maxRegTimeUser = userEntityService.getMaxOrMinUser("MAX");
            String minRegTime = minRegTimeUser.getRegTime();
            String maxRegTime = maxRegTimeUser.getRegTime();
            SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date minTime = sdfTemp.parse(minRegTime);
            Date maxTime = sdfTemp.parse(maxRegTime);
            Long days = (maxTime.getTime() - minTime.getTime()) / (24*3600*1000);
            allInterval = days / Integer.valueOf(times);
            perieds[2] = allInterval.toString();
        }
        Map<String, List<Integer>> allRoleCount = userEntityService.getUsersBetweenTime(perieds);
        //总用户数量
        List<Integer> counts = allRoleCount.get("ALL");
        List<Integer> adminCounts = allRoleCount.get("管理员");
        List<Integer> govCounts = allRoleCount.get("政府");
        List<Integer> insCounts = allRoleCount.get("事业单位");
        List<Integer> comCounts = allRoleCount.get("企业");
        List<Integer> perCounts = allRoleCount.get("个人");
        //总管理员用户
        Collections.reverse(counts);
        Collections.reverse(adminCounts);
        Collections.reverse(govCounts);
        Collections.reverse(insCounts);
        Collections.reverse(comCounts);
        Collections.reverse(perCounts);
        List<String> weekUnits = null;
        if (unit.equals("week")){
            weekUnits = Arrays.asList("'7天前'", "'6天前'", "'5天前'","'4天前'", "'3天前'", "'2天前'", "'1天前'");
        }
        if (unit.equals("month")){
            weekUnits = Arrays.asList("'30天前'", "'28天前'", "'26天前'","'24天前'", "'22天前'", "'20天前'", "'18天前'",
                    "'16天前'", "'14天前'", "'12天前'","'10天前'", "'8天前'", "'6天前'", "'4天前'", "'2天前'");
        }
        if (unit.equals("year")){
            weekUnits = Arrays.asList("'12月前'", "'11月前'", "'10月前'","'9月前'", "'8月前'", "'7月前'", "'6月前'",
                    "'5月前'", "'4月前'", "'3月前'","'2月前'", "'1月前'");
        }
        if (unit.equals("ALL")){
            String startTime = "";
            String unitArr [] = new String[Integer.valueOf(times)];
            for (int i = 1; i<= Integer.valueOf(times); i++) {
                startTime = sdf.format(new Date(currentDate.getTime() - i*allInterval* 24*3600*1000));
                unitArr[i-1] = "'"+startTime+"'";
            }
            weekUnits = Arrays.asList(unitArr);
            Collections.reverse(weekUnits);
        }

        int max = Collections.max(counts);
        int min = Collections.min(counts);
        int interval = (max - min) / 7;
        mode.addAttribute("counts", counts);
        mode.addAttribute("adminCounts", adminCounts);
        mode.addAttribute("govCounts", govCounts);
        mode.addAttribute("insCounts", insCounts);
        mode.addAttribute("comCounts", comCounts);
        mode.addAttribute("perCounts", perCounts);
        mode.addAttribute("units", weekUnits);
        mode.addAttribute("min", min);
        mode.addAttribute("interval", interval);
        mode.addAttribute("max", max);

        return "/WEB-INF/admin/sysusersAnalysis";
    }

    /**
     * 用户分析 - 属性分析: 性别、年龄、星座（constellation）、地区
     */
    @RequestMapping("userAnalysisAttr")
    public String userAnalysisAttr(HttpServletRequest req, Model mode){
        String column = "registrantId";
        List<String> idcards = userEntityService.getUserColumns(column);
        Integer menNum = 0;
        Integer womenNum = 0;
        List<String> ageStages = Arrays.asList(new String[]{"'>18'", "'18~30'", "'30~40'", "'40~50'",
                "'50~60'", "'60~70'", "'70~80'", "'80+'"});
        List<Integer> agesNum = new ArrayList<>();
        int age18 = 0, age30 = 0, age40 = 0, age50 = 0, age60 = 0, age70 = 0, age80 = 0, age80plus = 0;
        //省份
        Map<String, Integer> adminMap = new HashMap<>();
        Map<String, Integer> govMap = new HashMap<>();
        Map<String, Integer> insMap = new HashMap<>();
        Map<String, Integer> comMap = new HashMap<>();
        Map<String, Integer> perMap = new HashMap<>();
        //星座
        Map<String, Integer> adminConsMap = new HashMap<>();
        Map<String, Integer> govConsMap = new HashMap<>();
        Map<String, Integer> insConsMap = new HashMap<>();
        Map<String, Integer> comConsMap = new HashMap<>();
        Map<String, Integer> perConsMap = new HashMap<>();

        //将星座map转换为数组
        List<Integer> adminConsNumList = Arrays.asList(new Integer[]{0,0,0,0,0,0, 0,0,0,0,0,0});
        List<Integer> govConsNumList = Arrays.asList(new Integer[]{0,0,0,0,0,0, 0,0,0,0,0,0});
        List<Integer> insConsNumList = Arrays.asList(new Integer[]{0,0,0,0,0,0, 0,0,0,0,0,0});
        List<Integer> comConsNumList = Arrays.asList(new Integer[]{0,0,0,0,0,0, 0,0,0,0,0,0});
        List<Integer> perConsNumList = Arrays.asList(new Integer[]{0,0,0,0,0,0, 0,0,0,0,0,0});

        for (String idcard : idcards){
            String[] ageSex = IdCardInfoUtil.getAgeAndSexById(idcard);
            /**
             * 性别
             */
            //男
            if (ageSex[1].equals("1")){
                menNum ++;
            }
            //女
            if (ageSex[1].equals("2")){
                womenNum ++;
            }
            /**
             * 年龄
             */
            Integer age = Integer.parseInt(ageSex[0]);
            if (age<=18 && age>=0){
                age18 ++;
            }
            if (age<=30 && age>=18){
                age30 ++;
            }
            if (age<=40 && age>=30){
                age40 ++;
            }
            if (age<=50 && age>=40){
                age50 ++;
            }
            if (age<=60 && age>=50){
                age60 ++;
            }
            if (age<=70 && age>=60){
                age70 ++;
            }
            if (age<=80 && age>=70){
                age80 ++;
            }
            if (age>=80){
                age80plus ++;
            }

            /**
             * 省份、星座（constellation）
             */
            String province = IdCardInfoDetailUtil.getProvinceByIdCard(idcard);
            String constellation = IdCardInfoDetailUtil.getConstellationById(idcard);
            //获得角色
            String roleName = getRoleByUserIdCard(idcard).getRoleName();
//            province = "'"+province+"'";
            //对不同角色使用不同list
            if (roleName.equals("管理员")){
                //省份
                int num = 1;
                if (adminMap.get(province) == null){
                    adminMap.put(province, 1);
                }else {
                    num = adminMap.get(province);
                    adminMap.put(province, num+1);
                }
                //星座
//                int consNum = 1;
//                if(adminConsMap.get(constellation) == null){
//                    adminConsMap.put(constellation, 1);
//                }else {
//                    adminConsMap.put(constellation, consNum + 1);
//                }
                int ind = getConstellation(constellation);
                if (ind != -1) {
                    adminConsNumList.set(ind, adminConsNumList.get(ind) + 1);
                }
            }
            if (roleName.equals("政府")){
                int num = 1;
                if (govMap.get(province) == null){
                    govMap.put(province, 1);
                }else {
                    num = govMap.get(province);
                    govMap.put(province, num+1);
                }
                //星座
//                int consNum = 1;
//                if(govConsMap.get(constellation) == null){
//                    govConsMap.put(constellation, 1);
//                }else {
//                    govConsMap.put(constellation, consNum + 1);
//                }
                int ind = getConstellation(constellation);
                if (ind != -1) {
                    govConsNumList.set(ind, govConsNumList.get(ind) + 1);
                }
            }
            if (roleName.equals("事业单位")){
                int num = 1;
                if (insMap.get(province) == null){
                    insMap.put(province, 1);
                }else {
                    num = insMap.get(province);
                    insMap.put(province, num+1);
                }
                //星座
//                int consNum = 1;
//                if(insConsMap.get(constellation) == null){
//                    insConsMap.put(constellation, 1);
//                }else {
//                    insConsMap.put(constellation, consNum + 1);
//                }
                int ind = getConstellation(constellation);
                if (ind != -1) {
                    insConsNumList.set(ind, insConsNumList.get(ind) + 1);
                }
            }
            if (roleName.equals("企业")){
                int num = 1;
                if (comMap.get(province) == null){
                    comMap.put(province, 1);
                }else {
                    num = comMap.get(province);
                    comMap.put(province, num+1);
                }
                //星座
//                int consNum = 1;
//                if(comConsMap.get(constellation) == null){
//                    comConsMap.put(constellation, 1);
//                }else {
//                    comConsMap.put(constellation, consNum + 1);
//                }
                int ind = getConstellation(constellation);
                if (ind != -1) {
                    comConsNumList.set(ind, comConsNumList.get(ind) + 1);
                }
            }
            if (roleName.equals("个人")){
                int num = 1;
                if (perMap.get(province) == null){
                    perMap.put(province, 1);
                }else {
                    num = perMap.get(province);
                    perMap.put(province, num+1);
                }
                //星座
//                int consNum = 1;
//                if(perConsMap.get(constellation) == null){
//                    perConsMap.put(constellation, 1);
//                }else {
//                    perConsMap.put(constellation, consNum + 1);
//                }
                int ind = getConstellation(constellation);
                if (ind != -1){
                    perConsNumList.set(ind, perConsNumList.get(ind) + 1);
                }
            }
        }

        agesNum = Arrays.asList(new Integer[] {age18, age30, age40, age50, age60, age70, age80, age80plus});
        //性别
        mode.addAttribute("menNum",menNum);
        mode.addAttribute("womenNum",womenNum);
        //年龄
        mode.addAttribute("agesNum",agesNum);
        mode.addAttribute("ageStages",ageStages);
        //省份
        mode.addAttribute("adminMap",adminMap);
        JSONObject adminJson = JSONObject.fromObject(adminMap);
        mode.addAttribute("adminJson", adminJson);
        mode.addAttribute("govMap",govMap);
        JSONObject govJson = JSONObject.fromObject(govMap);
        mode.addAttribute("govJson", govJson);
        mode.addAttribute("insMap",insMap);
        JSONObject insJson = JSONObject.fromObject(insMap);
        mode.addAttribute("insJson", insJson);
        mode.addAttribute("comMap",comMap);
        JSONObject comJson = JSONObject.fromObject(comMap);
        mode.addAttribute("comJson", comJson);
        mode.addAttribute("perMap",perMap);
        JSONObject perJson = JSONObject.fromObject(perMap);
        mode.addAttribute("perJson", perJson);
        List<Integer> numList = Arrays.asList(new Integer[]{adminMap.size(), govMap.size(), insMap.size(), comMap.size(), perMap.size()});
        mode.addAttribute("maxNum", Collections.max(numList));
        mode.addAttribute("minNum", Collections.min(numList));

        //星座
        List<String> constellationList = Arrays.asList(new String[]{"'白羊座'", "'金牛座'", "'双子座'",
                "'巨蟹座'", "'狮子座'", "'处女座'", "'天秤座'", "'天蝎座'", "'射手座'", "'摩羯座'", "'水瓶座'", "'双鱼座'"});
        mode.addAttribute("adminConsNumList", adminConsNumList);
        mode.addAttribute("govConsNumList", govConsNumList);
        mode.addAttribute("insConsNumList", insConsNumList);
        mode.addAttribute("comConsNumList", comConsNumList);
        mode.addAttribute("perConsNumList", perConsNumList);
        mode.addAttribute("constellationList", constellationList);

        return "/WEB-INF/admin/sysusersAnalysisAttrs";
    }

    /**
     * 通过用户身份证id， 获得角色role信息
     */
    public RoleEntity getRoleByUserIdCard(String idcard){
        PropertyFilter filter = new PropertyFilter("registrantId", idcard);
        UserEntity userEntity = userEntityService.search(filter).get(0);
        RoleEntity role = userEntity.getRole();
        return role;
    }

    /**
     * 获得星座index
     * @param cons
     * @return
     */
    public int getConstellation(String cons){
        List<String> constellationList = Arrays.asList(new String[]{"白羊座", "金牛座", "双子座",
                "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"});
        return constellationList.indexOf(cons);
    }

    public void getEntity(){
        List<String> citys = Arrays.asList(new String[]{"北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江",
                "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南",
                "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "台湾", "香港", "澳门", "国外"});
    }

}
