package com.henu.reservoir.controller;

import com.henu.reservoir.domain.MeasuredResultDao;
import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.service.ReservoirInfoService;
import com.henu.reservoir.util.ExtractMeasuredLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private ReservoirInfoService reservoirInfoService;

    @RequestMapping(value = "/api/reservoir/{id}", method = RequestMethod.GET)
    public String findOneReservoirInfo(Model model, @PathVariable("id") Integer id) throws IOException, ParseException {
        //test
        FileInputStream fileInputStream = new FileInputStream(new File(".\\src\\main\\resources\\upload\\test.xlsx"));
        ExtractMeasuredLevel extractMeasuredLevel = new ExtractMeasuredLevel(fileInputStream, 1);
        ArrayList<MeasuredResultDao> allResult = extractMeasuredLevel.ReadDataFromExcel();

        model.addAttribute("allResult", allResult);
        return "showReservoirInfo";
    }
}