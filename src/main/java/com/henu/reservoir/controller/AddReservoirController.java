package com.henu.reservoir.controller;

import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.service.ReservoirInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddReservoirController {
    //添加水库
    private ReservoirInfoService reservoirInfoService;

    @Autowired
    private void setReservoirInfoService(ReservoirInfoService reservoirInfoService) {
        this.reservoirInfoService = reservoirInfoService;
    }

    @GetMapping("/api/addReservoir")
    @ResponseBody
    public String add(
            String name,
            String location,
            String longitudeLeft,
            String latitudeLeft,
            String longitudeRight,
            String latitudeRight
    ) {
        //检查水库名字是否冲突
        if (checkReservoirName(name)) {
            //添加水库
            ReservoirInfoDao dao = new ReservoirInfoDao();
            dao.setName(name);
            dao.setLocation(location);
            dao.setLongitudeLeft(longitudeLeft);
            dao.setLatitudeLeft(latitudeLeft);
            dao.setLongitudeRight(longitudeRight);
            dao.setLatitudeRight(latitudeRight);
            return "success";
        }
        else {
            return "index";
        }
    }

    private boolean checkReservoirName(String rname) {
        List<ReservoirInfoDao> rlist = reservoirInfoService.findAllReservoirInfo();
        for (ReservoirInfoDao item : rlist){
            if (rname.equals(item.getName())){
                return false;
            }
        }
            return true;
    }
}
