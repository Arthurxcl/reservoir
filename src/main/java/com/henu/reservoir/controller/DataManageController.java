package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.OpticalImgDao;
import com.henu.reservoir.domain.SarImgDao;
import com.henu.reservoir.service.OpticalImgService;
import com.henu.reservoir.service.SarImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataManageController {
    private SarImgService sarImgService;
    private OpticalImgService opticalImgService;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private void setService(
            SarImgService sarImgService,
            OpticalImgService opticalImgService
    ){
        this.sarImgService = sarImgService;
        this.opticalImgService = opticalImgService;
    }

    @GetMapping("api/data/sar")
    @ResponseBody
    public String getSarImageList(String key){
        List<SarImgDao> list;
        if (key==null) {
            //无关键字
            list = sarImgService.findAllSarImg();
        }
        else {
            list = sarImgService.findSarImgByKeyWord(key);
        }
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/data/optical")
    @ResponseBody
    public String getOpticalImageList(String key){
        List<OpticalImgDao> list;
        if (key==null) {
            //无关键字
            list = opticalImgService.findAllOpticalImg();
        }
        else {
            list = opticalImgService.findOpticalImgByKeyWord(key);
        }
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
