package com.sicdlib.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/*")
public class DataCleanDiaryAction {

    @RequestMapping("dataCleanDiary")
    public String dataCleanDiary(HttpServletRequest req, HttpServletResponse response, Model model){

        return "/WEB-INF/admin/dataClean_diary";
    }
}
