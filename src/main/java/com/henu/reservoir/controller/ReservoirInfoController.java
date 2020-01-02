package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.MeasuredResultDao;
import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.service.MeasuredResultService;
import com.henu.reservoir.service.ReservoirInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReservoirInfoController {
    //水库相关信息查询相关
    @Autowired
    private ReservoirInfoService reservoirInfoService;
    @Autowired
    private MeasuredResultService measuredResultService;

    @GetMapping("api/reservoirInfo")
    @ResponseBody
    public String getInfoByReservoirName(String rname){
        //使用name获取ID
        ReservoirInfoDao reservoirInfoDao = reservoirInfoService.findReservoirInfoByName(rname);
        //使用ID查找信息

        return reservoirInfoDao.getName();
    }

    @GetMapping("api/reservoirInfo/measuredResult")
    @ResponseBody
    public String getMeasuredResult(String rid) {
        ObjectMapper mapper = new ObjectMapper();
        int id = Integer.parseInt(rid);
        List<MeasuredResultDao> list = measuredResultService.findMeasuredResultByReservoirId(id);
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
