package com.sicdlib.web;

import com.sicdlib.entity.ObjectEntity;
import com.sicdlib.entity.UserEntity;
import com.sicdlib.service.ObjectEntityService;
import com.sicdlib.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/event/")
public class EventAction {

    @Autowired
    private ObjectEntityService objectEntityService;

    //查找事件列表
    @RequestMapping("all")
    public String findAll(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        List<ObjectEntity> eventList = objectEntityService.findEvent();
        mode.addAttribute("eventList", eventList);
        return "/WEB-INF/foreground/event";
    }

}
