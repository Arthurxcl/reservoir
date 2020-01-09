package com.henu.reservoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController1 {
    //用来跳至主页
   @RequestMapping("/")
   public String goToIndex(){
       return "index";
   }

}
