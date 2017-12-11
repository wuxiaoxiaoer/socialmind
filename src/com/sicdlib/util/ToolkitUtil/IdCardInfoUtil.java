package com.sicdlib.util.ToolkitUtil;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdCardInfoUtil {

    /**
     * 方法描述： 根据身份证获取年龄，性别
     * 2表示女
     * 1表示男
     * @param idNum
     * @return
     * String[]
     * @author  husheng
     */
    public static String[] getAgeAndSexById(String idNum) {
        String age = "";
        String sex = "";
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());//获取系统当前时间
        int currentYear = calendar.get(Calendar.YEAR);
        if (idNum.matches("^\\d{15}$|^\\d{17}[\\dxX]$")) {
            if (idNum.length() == 18) {
                Pattern pattern = Pattern.compile("\\d{6}(\\d{4})\\d{6}(\\d{1})[\\dxX]{1}");
                Matcher matcher = pattern.matcher(idNum);
                if (matcher.matches()) {

                    age = String.valueOf(currentYear - Integer.parseInt(matcher.group(1)));
                    sex = "" + Integer.parseInt(matcher.group(2))%2;
                }
            } else if (idNum.length() == 15) {
                Pattern p = Pattern.compile("\\d{6}(\\d{2})\\d{5}(\\d{1})\\d{1}");
                Matcher m = p.matcher(idNum);
                if (m.matches()) {
                    int year = Integer.parseInt(m.group(1));
                    year = 2000 + year;
                    if (year > 2020) {
                        year = year - 100;
                    }
                    age = String.valueOf(currentYear - year);
                    sex = "" + Integer.parseInt(m.group(2))%2;
                }
            }
        }
        if ("0".equals(sex)) {
            sex = "2";
        }
        return new String[]{age, sex};
    }

    @Test
    public void getInfo(){
        String idNum = "371202199208065313";
        String[] ageSex = getAgeAndSexById(idNum);
        System.out.println("年龄："+ageSex[0]);
        System.out.println("性别（1：男，2：女）："+ageSex[1]);
    }
}
