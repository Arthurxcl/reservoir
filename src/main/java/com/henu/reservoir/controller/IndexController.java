package com.henu.reservoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/result")
    public String result(Model model) {
        return "result";
    }

    @GetMapping("/upload")
    public String form(Model model) {
        return "form";
    }

    @GetMapping("/tables")
    public String tables(Model model) {
        return "tables";
    }
}
