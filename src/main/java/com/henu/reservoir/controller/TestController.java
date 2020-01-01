package com.henu.reservoir.controller;

import com.henu.reservoir.domain.MeasuredResultDao;
import com.henu.reservoir.util.ExtractMeasuredLevel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String findOneReservoirInfo(Model model) {
        return "upload";
    }
}

