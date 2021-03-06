package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.*;
import com.henu.reservoir.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class DataManageController {
    private SarImgService sarImgService;
    private OpticalImgService opticalImgService;
    private ReservoirInfoService reservoirInfoService;
    private MeasuredResultService measuredResultService;
    private RadarResultService radarResultService;
    private RadarLevelService radarLevelService;
    private WaterAreaService waterAreaService;

    @Autowired
    private void setService(
            SarImgService sarImgService,
            OpticalImgService opticalImgService,
            ReservoirInfoService reservoirInfoService,
            MeasuredResultService measuredResultService,
            RadarResultService radarResultService,
            WaterAreaService waterAreaService,
            RadarLevelService radarLevelService
    ){
        this.sarImgService = sarImgService;
        this.opticalImgService = opticalImgService;
        this.reservoirInfoService = reservoirInfoService;
        this.measuredResultService = measuredResultService;
        this.radarResultService = radarResultService;
        this.waterAreaService = waterAreaService;
        this.radarLevelService = radarLevelService;
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

    @GetMapping("api/data/measured")
    @ResponseBody
    public String getMeasuredList(){
        List<MeasuredResultDao> list1 = measuredResultService.findAllMeasuredResult();
        try {
            return mapper.writeValueAsString(list1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/data/radar")
    @ResponseBody
    public String getRadarList(){
        buildReservoirInfoDaoHashMap();
        List<RadarResultDao> list1 = radarResultService.findAllRadarResult();
        List<RadarItem> list = new ArrayList<>();
        for (RadarResultDao item : list1){
            RadarItem i = new RadarItem();
            System.out.println(item.getRadarLevelId());
            RadarLevelDao levelDao = radarLevelService.findRadarLevelById(item.getRadarLevelId());
            i.setId(item.getId());
            i.setDate(item.getDate());
            i.setReservoirName(reservoirInfoDaoHashMap.get(item.getReservoirId()).getName());
            i.setLevel(item.getWaterLevel());
            i.setSatelliteName(levelDao.getSatelliteName());
            i.setLng(item.getSiteLongitude());
            i.setLat(item.getSiteLatitude());
            list.add(i);
        }
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/data/area")
    @ResponseBody
    public String getAreaList(){
        buildReservoirInfoDaoHashMap();
        List<WaterAreaDao> list1 = waterAreaService.findAllWaterArea();
        List<AreaItem> list = new ArrayList<>();
        for (WaterAreaDao dao : list1){
            AreaItem item = new AreaItem();
            item.setId(dao.getId());
            item.setDate(dao.getDate());
            item.setReservoirName(reservoirInfoDaoHashMap.get(dao.getReservoirId()).getName());
            item.setArea(dao.getArea());
            if(dao.getIsSarArea() == 1){
                item.setType("SAR");
            }
            else {
                item.setType("光学");
            }
            list.add(item);
        }
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/data/reservoir")
    @ResponseBody
    public String getReservoirList(){
        List<ReservoirInfoDao> list1 = reservoirInfoService.findAllReservoirInfo();
        try {
            return mapper.writeValueAsString(list1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("api/data/reservoir-edit")
    @ResponseBody
    public void editReservoir(
            int id,
            String name,
            String location,
            String longitudeLeft,
            String longitudeRight,
            String latitudeLeft,
            String latitudeRight
    ){
        ReservoirInfoDao dao = new ReservoirInfoDao();
        dao.setId(id);
        dao.setName(name);
        dao.setLocation(location);
        dao.setLongitudeRight(longitudeRight);
        dao.setLatitudeRight(latitudeRight);
        dao.setLongitudeLeft(longitudeLeft);
        dao.setLatitudeLeft(latitudeLeft);
        reservoirInfoService.updateReservoirInfo(dao);
    }

    @GetMapping("api/data/reservoir-delete")
    @ResponseBody
    public void deleteReservoir(int id){
        reservoirInfoService.deleteReservoirInfo(id);
    }

    @GetMapping("api/data/image")
    @ResponseBody
    public String getImageList(){
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
                    dao.getSatelliteName(),
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
                    dao.getSatelliteName(),
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

        public String getSatelliteName() {
            return satelliteName;
        }

        public void setSatelliteName(String satelliteName) {
            this.satelliteName = satelliteName;
        }

        private String satelliteName;
        private int id;

        public DownloadItem(String rname, String path, String date, String type, String satelliteName, int id) {
            this.rname = rname;
            this.path = path;
            this.date = date;
            this.type = type;
            this.satelliteName = satelliteName;
            this.id = id;
        }

        public DownloadItem() {
        }
    }
    private class RadarItem{
        Date date;
        String reservoirName;
        int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getReservoirName() {
            return reservoirName;
        }

        public void setReservoirName(String reservoirName) {
            this.reservoirName = reservoirName;
        }

        public String  getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSatelliteName() {
            return satelliteName;
        }

        public void setSatelliteName(String satelliteName) {
            this.satelliteName = satelliteName;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        String  level;
        String satelliteName;
        String lng;
        String lat;

        public RadarItem(){};
        public RadarItem(int id, Date date,String reservoirName,String  level,String satelliteName,String lng,String lat){
            this.id = id;
            this.date = date;
            this.reservoirName = reservoirName;
            this.level = level;
            this.satelliteName = satelliteName;
            this.lng = lng;
            this.lat = lat;
        }
    }
    private class AreaItem{
        private int id;
        private String reservoirName;

        public AreaItem(int id, String reservoirName, Date date, String area, String type) {
            this.id = id;
            this.reservoirName = reservoirName;
            this.date = date;
            this.area = area;
            this.type = type;
        }

        private Date date;
        private String area;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReservoirName() {
            return reservoirName;
        }

        public void setReservoirName(String reservoirName) {
            this.reservoirName = reservoirName;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public AreaItem() {
        }
    }
}


