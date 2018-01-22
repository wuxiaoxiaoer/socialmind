package com.sicdlib.util.ToolkitUtil;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileToList {

    /**
     * 输入文件名，输出list
     */
    public static List<String> readFileToList(String filePath){
        List<String> list = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) !=null){
                list.add(tempString);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Test
    public void getInfo(){
        String filePath = "E:\\learn\\ideaproject\\socialmind\\data\\stopWordData\\stop_words.txt";
        List<String> list = readFileToList(filePath);
        System.out.println(list.size());
    }
}
