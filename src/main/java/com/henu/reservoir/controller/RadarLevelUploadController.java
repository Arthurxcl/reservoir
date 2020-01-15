package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.domain.RadarLevelDao;
import com.henu.reservoir.domain.RadarResultDao;
import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.service.RadarLevelService;
import com.henu.reservoir.service.RadarResultService;
import com.henu.reservoir.service.ReservoirInfoService;
import com.mathworks.toolbox.javabuilder.MWException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import Altitude_Sentinel3_A.Radar;

/**
 * 雷达高度计文件夹上传
 */

@Controller
@EnableAutoConfiguration
public class RadarLevelUploadController {

    private ReservoirInfoService reservoirInfoService;
    private RadarResultService radarResultService;
    private RadarLevelService radarLevelService;

    @Value("${path.resource-path}")
    private String resourcePath;

    @Autowired
    private void setServices(
            ReservoirInfoService reservoirInfoService,
            RadarResultService radarResultService,
            RadarLevelService radarLevelService
    ){
        this.reservoirInfoService = reservoirInfoService;
        this.radarResultService = radarResultService;
        this.radarLevelService = radarLevelService;
    }

    private ObjectMapper mapper = new ObjectMapper();
    private List<RadarResultDao> radarResultDaoList = new ArrayList<>();
    private RadarLevelDao radarLevelDao = new RadarLevelDao();


    @PostMapping(value = "/upload/radar")
    @ResponseBody
    public String upload_SAR(Model model, @RequestParam("radarFile") MultipartFile[] radarFile,
                             @RequestParam("reservoirName") String reservoirName, @RequestParam("satelliteName")String satelliteName,
                             @RequestParam("cycle") Integer cycle, @RequestParam("date") Date date, @RequestParam("topLeft") String topLeft,
                             @RequestParam("lowerRight") String lowerLeft) throws MWException, IOException {
        //由水库名获得水库id
        ReservoirInfoDao reservoirInfoDao = reservoirInfoService.findReservoirInfoByName(reservoirName);
        if (reservoirInfoDao == null){
            return "reservoir name error";
        }
        int reservoirId = reservoirInfoDao.getId();

        //将信息填入radarLevelDao，后面要用
        radarLevelDao.setId(0);
        radarLevelDao.setDate(date);
        radarLevelDao.setCycle(cycle);
        radarLevelDao.setSatelliteName(satelliteName);
        radarLevelDao.setReservoirId(reservoirId);

        //清空上一次的结果
        radarResultDaoList.clear();

        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        //生成不重复的文件夹名称
        String uploadDirName = formatDate + Integer.toString(random);
        //判断文件夹是否存在
        String radarFilePath = System.getProperty("user.dir") + resourcePath + "static\\upload\\radarData\\";
        File radarDataDir = new File(radarFilePath);
        if (!radarDataDir.exists()) {
            radarDataDir.mkdir();
        }
        //为上传的文件创建文件夹
        String uploadDirPath = radarFilePath + uploadDirName;
        File uploadDir = new File(uploadDirPath);
        uploadDir.mkdir();
        //将上传的文件存入文件夹
        try {
            for (MultipartFile file : radarFile) {
                String fileName = file.getOriginalFilename();
                String name = fileName.substring(fileName.lastIndexOf("/")+1);
                file.transferTo(new File(uploadDirPath + "\\" + name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //分解上传的经纬度
        String topLatitude = topLeft.substring(topLeft.indexOf(" "));
        String lowerLatitude = lowerLeft.substring(lowerLeft.indexOf(" "));

        //处理上传的雷达高度计数据, 生成文本文件
        String str_in = uploadDirPath + "\\enhanced_measurement.nc";
        String out01 = uploadDirPath + "\\01.txt";
        String out20 = uploadDirPath + "\\20.txt";
        Radar radar = new Radar();
        //32.93, 32.97
        radar.Altitude_Sentinel3_A(str_in, out01, out20, Double.parseDouble(lowerLatitude), Double.parseDouble(topLatitude));

        //删除.nc文件
        deleteFilesBySuffix(uploadDirPath, ".txt", true);

        //处理文本文件
        File file01 = new File(out01);
        File file20 = new File(out20);
        FileInputStream[] fin = new FileInputStream[]{new FileInputStream(file01), new FileInputStream(file20)};
        //处理 01.txt, 将每行数据存入 list01
        ArrayList<String> list01 = new ArrayList<>();
        InputStreamReader reader = new InputStreamReader(fin[0]);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        while((strTmp = buffReader.readLine())!=null){
            list01.add(strTmp);
        }
        //处理 20.txt, 将每行数据存入 list20
        ArrayList<String> list20 = new ArrayList<>();
        reader = new InputStreamReader(fin[1]);
        buffReader = new BufferedReader(reader);
        while((strTmp = buffReader.readLine())!=null){
            list20.add(strTmp);
        }
        buffReader.close();

        //从list01中任取一个减数
        double[] dlist = new double[list01.size()];
        for (int i = 0; i<list01.size();i++){
            String sitem = list01.get(i);
            String[] strings = sitem.split(",");
            dlist[i] = Double.parseDouble(strings[9]);
        }
        double d = dlist[new Random().nextInt(dlist.length)];



        //将list20中的结果存入radarResultDaoList, 同时装入radarItemList20，以转成json发向前端
        List<RadarItem> radarItemList20 = new ArrayList<>();
        for (int i = 0; i<list20.size(); i++) {
            String sitem = list20.get(i);
            String[] strings = sitem.split(",");
            double level = Double.parseDouble(strings[7]) - d + 1.46;
            double lng = Double.parseDouble(strings[2]);
            double lat = Double.parseDouble(strings[3]);
            radarItemList20.add(new RadarItem(i,lng,lat, level));
            //-----------------改
            radarResultDaoList.add(new RadarResultDao(0, date, level + "", radarLevelDao.getId(), lng + "", lat+"", reservoirId));
        }

        //转换成json返回
        try {
            return mapper.writeValueAsString(radarItemList20);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("upload/radar/choose")
    @ResponseBody
    public String chooseRadarData(int index){
        //使用date和satelliteName查找radarLevelId，如没有则新建一条radarLevel
        Date date = radarLevelDao.getDate();
        String satelliteName = radarLevelDao.getSatelliteName();
        int reservoirId = radarLevelDao.getReservoirId();
        RadarLevelDao dao = radarLevelService.findRadarLevelByDateAndNameAndReservoirId(date, satelliteName, reservoirId);
        if (dao == null){
            radarLevelService.addRadarLevel(radarLevelDao);
            dao = radarLevelService.findRadarLevelByDateAndNameAndReservoirId(date, satelliteName, reservoirId);
        }

        //替换掉RadarLevelId
        RadarResultDao radarResultDao = radarResultDaoList.get(index);
        radarResultDao.setRadarLevelId(dao.getId());
        try {
            radarResultService.addRadarResult(radarResultDao);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";

    }

    //转Json用的数据项
    private class RadarItem{
        private int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        private double lng;
        private double lat;
        private double level;

        public double getLevel() {
            return level;
        }

        public void setLevel(double level) {
            this.level = level;
        }

        public RadarItem(int index, double lng, double lat, double level){
            this.index = index;
            this.lng = lng;
            this.lat = lat;
            this.level = level;
        }
    }

    //批量删除相同后缀的文件
    private boolean deleteFilesBySuffix(String path, String suffix, boolean reverse){
        //        路径->path;
        //        后缀->suffix;
        //    是否反选->reverse;
        if ("".equals(path) || "".equals(suffix)){
            return false;
        }
        FileFilter fileFilter = pathname -> {
            if (pathname.isDirectory()){
                return false;
            }
            else {
                if (pathname.getName().endsWith(suffix)){
                    return !reverse;
                }
            }
            return reverse;
        };
        File[] files = new File(path).listFiles(fileFilter);
        if (files != null){
            for (File file : files) {
                file.delete();
            }
        }
        return false;
    }
}

