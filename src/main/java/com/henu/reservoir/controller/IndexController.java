package com.henu.reservoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/som")
    public String index1() {
        return "index";
    }

    @GetMapping("/upload/ml")
    public String index2() {
        return "index";
    }

    @GetMapping("/upload/rl")
    public String index3() {
        return "index";
    }

    @GetMapping("/upload/sar")
    public String index4() {
        return "index";
    }

    @GetMapping("/upload/opt")
    public String index5() {
        return "index";
    }

    @GetMapping("/reservoir")
    public String index6() {
        return "index";
    }

    @GetMapping("/dm")
    public String index7() {
        return "index";
    }

    @GetMapping("/add-reservoir")
    public String index8() {
        return "index";
    }

    @GetMapping("/edit-reservoir")
    public String index9() {
        return "index";
    }
    @GetMapping("/model/level")
    public String index10() {
        return "index";
    }
    @GetMapping("/model/area")
    public String index11() {
        return "index";
    }
    @GetMapping("/model/storage")
    public String index12() {
        return "index";
    }
    @GetMapping("/setting")
    public String index13() {
        return "index";
    }
}
