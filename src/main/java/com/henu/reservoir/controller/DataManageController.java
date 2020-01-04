package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.OpticalImgDao;
import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.domain.SarImgDao;
import com.henu.reservoir.service.OpticalImgService;
import com.henu.reservoir.service.ReservoirInfoService;
import com.henu.reservoir.service.SarImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DataManageController {
    private SarImgService sarImgService;
    private OpticalImgService opticalImgService;
    private ReservoirInfoService reservoirInfoService;

    @Autowired
    private void setService(
            SarImgService sarImgService,
            OpticalImgService opticalImgService,
            ReservoirInfoService reservoirInfoService
    ){
        this.sarImgService = sarImgService;
        this.opticalImgService = opticalImgService;
        this.reservoirInfoService = reservoirInfoService;
    }

    private ObjectMapper mapper = new ObjectMapper();
    private HashMap<Integer, ReservoirInfoDao> reservoirInfoDaoHashMap;

    private void buildReservoirInfoDaoHashMap(){
        List<ReservoirInfoDao> reservoirInfoDaoList = reservoirInfoService.findAllReservoirInfo();
        reservoirInfoDaoHashMap = new HashMap<>();
        for (ReservoirInfoDao dao:
             reservoirInfoDaoList) {
            reservoirInfoDaoHashMap.put(dao.getId(), dao);
        }
    }

    @GetMapping("api/data/image")
    @ResponseBody
    public String getSarImageList(){
        buildReservoirInfoDaoHashMap();
        List<SarImgDao> list1 = sarImgService.findAllSarImg();
        List<OpticalImgDao> list2 = opticalImgService.findAllOpticalImg();
        List<DownloadItem> dlist = new ArrayList<>();
        for (SarImgDao dao:
                list1) {
            dlist.add(new DownloadItem(
                    reservoirInfoDaoHashMap.get(dao.getreservoir_id()).getName(),
                    dao.getPath(),
                    new SimpleDateFormat("yyyy-MM-dd").format(dao.getDate()),
                    "sar",
                    dao.getId()
            ));
        }
        for (OpticalImgDao dao:
                list2) {
            dlist.add(new DownloadItem(
                    reservoirInfoDaoHashMap.get(dao.getreservoir_id()).getName(),
                    dao.getPath(),
                    new SimpleDateFormat("yyyy-MM-dd").format(dao.getDate()),
                    "optical",
                    dao.getId()
            ));
        }
        try {
            return mapper.writeValueAsString(dlist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    private class DownloadItem {
        private String rname;

        public String getRname() {
            return rname;
        }

        public void setRname(String rname) {
            this.rname = rname;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        private String path;
        private String date;
        private String type;
        private int id;

        public DownloadItem(String rname, String path, String date, String type, int id) {
            this.rname = rname;
            this.path = path;
            this.date = date;
            this.type = type;
            this.id = id;
        }

        public DownloadItem() {
        }
    }
}


