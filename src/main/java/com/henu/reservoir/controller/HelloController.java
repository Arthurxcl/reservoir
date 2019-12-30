package com.henu.reservoir.controller;

import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.service.ReservoirInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private ReservoirInfoService reservoirInfoService;

    @RequestMapping(value = "/api/reservoir/{id}", method = RequestMethod.GET)
    public String findOneReservoirInfo(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("reservoirInfo", reservoirInfoService.findReservoirInfoById(id));
        return "showReservoirInfo";
    }
}