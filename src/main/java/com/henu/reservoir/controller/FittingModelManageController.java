package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.FittingFormulaDao;
import com.henu.reservoir.service.FittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FittingModelManageController {
    private ObjectMapper mapper = new ObjectMapper();

    private FittingService fittingService;

    @Autowired
    private void setService(
            FittingService fittingService
    ) {
        this.fittingService = fittingService;
    }

    @GetMapping("/api/model/getLevelModels")
    @ResponseBody
    public String getLevelModels(int rid) {
        List<FittingFormulaDao> fittingFormulaDaoList = fittingService.findByTypeAndReservoirId(rid, "level");
        FittingFormulaDao radarModel = fittingService.findByNameAndReservoirId(rid, "遥测水位模型");
        if (null != radarModel) {
            fittingFormulaDaoList.add(0, radarModel);
        }
        FittingFormulaDao measuredModel = fittingService.findByNameAndReservoirId(rid, "实测水位模型");
        if (null != measuredModel) {
            fittingFormulaDaoList.add(0, measuredModel);
        }
        try {
            return mapper.writeValueAsString(fittingFormulaDaoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/api/model/setModel")
    @ResponseBody
    public String setModel(int rid, String name, String newName, String sDate, String params) {
        FittingFormulaDao dao = fittingService.findByNameAndReservoirId(rid, name);
        dao.setName(newName);
        FittingFormulaDao fittingFormulaDao = fittingService.findByNameAndReservoirId(rid, newName);
        if(fittingFormulaDao!=null){
            if (!fittingFormulaDao.getId().equals(dao.getId())){
                return "name";
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.parseLong(sDate);
        String d = format.format(time);
        Date date;
        try {
            date = format.parse(d);
        } catch (Exception e) {
            return "error in parsing date.";
        }
        dao.setFirstDate(date);
        dao.setOrders(params);
        int result = fittingService.update(dao);
        if (result == 1) {
            return "success";
        }
        return "error";
    }

    @GetMapping("/api/model/addModel")
    @ResponseBody
    public String addModel(int rid, String name, String type, String sDate, String params) {
        FittingFormulaDao dao = new FittingFormulaDao();
        dao.setReservoirId(rid);
        if(fittingService.findByNameAndReservoirId(rid, name)!=null){
            return "name";
        }
        dao.setName(name);
        dao.setType(type);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.parseLong(sDate);
        String d = format.format(time);
        Date date;
        try {
            date = format.parse(d);
        } catch (Exception e) {
            return "error in parsing date.";
        }
        dao.setFirstDate(date);
        dao.setDate(new Date());
        dao.setOrders(params);
        int result = fittingService.add(dao);
        if (result == 1) {
            return "success";
        }
        return "error";
    }

    @GetMapping("/api/model/getAreaModels")
    @ResponseBody
    public String getAreaModels(int rid) {
        List<FittingFormulaDao> fittingFormulaDaoList = fittingService.findByTypeAndReservoirId(rid, "area");
        try {
            return mapper.writeValueAsString(fittingFormulaDaoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/api/model/getStorageModels")
    @ResponseBody
    public String getStorageModels(int rid) {
        List<FittingFormulaDao> fittingFormulaDaoList = fittingService.findByTypeAndReservoirId(rid, "storage");
        try {
            return mapper.writeValueAsString(fittingFormulaDaoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/api/model/deleteModel")
    @ResponseBody
    public void deleteModel(int id){
        fittingService.deleteById(id);
    }


}
