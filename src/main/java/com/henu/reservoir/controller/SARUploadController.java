package com.henu.reservoir.controller;

import com.henu.reservoir.dao.FittingFormulaDaoMapper;
import com.henu.reservoir.dao.WaterAreaDaoMapper;
import com.henu.reservoir.domain.FittingFormulaDao;
import com.henu.reservoir.domain.SarImgDao;
import com.henu.reservoir.domain.WaterAreaDao;
import com.henu.reservoir.service.CutAlgoService;
import com.henu.reservoir.service.ReservoirInfoService;
import com.henu.reservoir.service.SarImgService;
import com.henu.reservoir.service.WaterAreaService;
import com.henu.reservoir.util.countWaterArea.Count;
import fcm_java.sar_fcm.Fcm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@EnableAutoConfiguration
public class SARUploadController {
    @Autowired
    private SarImgService sarImgService;
    @Autowired
    private ReservoirInfoService reservoirInfoService;
    @Autowired
    CutAlgoService cutAlgoService;
    @Autowired
    WaterAreaService waterAreaService;
    @Autowired
    WaterAreaDaoMapper waterAreaDaoMapper;
    @Autowired
    FittingFormulaDaoMapper fittingFormulaDaoMapper;

    @Value("${path.resource-path}")
    private String resourcePath;

    private String originFilePath;
    private String newFilePath;
    private String newFilePathRelative;
    private String waterArea;

    @PostMapping(value = "/upload/sar/sarFile")
    @ResponseBody
    public String upload_SAR(@RequestParam("sarFile") MultipartFile sarFile) throws IOException, InterruptedException {
        //获取文件名
        String fileName = sarFile.getOriginalFilename();
        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取文件前缀
        String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
        //获取项目名称
        String projectPath = System.getProperty("user.dir");
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        //生成不重复的文件夹名称
        String uploadDirName = formatDate + Integer.toString(random);
        //完整文件名
        String filePathUpload = projectPath + resourcePath + "static\\upload\\";
        //判断Upload文件夹是否存在，不存在则创建
        File fileDirUpload = new File(filePathUpload);
        if (!fileDirUpload.exists()) {
            fileDirUpload.mkdir();
        }
        String filePath = projectPath + resourcePath + "static\\upload\\SARImg\\";
        //判断SARImg文件夹是否存在，不存在则创建
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        //创建不重复的源文件存放目录
        File fileDirNew = new File(filePath + uploadDirName);
        fileDirNew.mkdir();
        //将原图像存入服务器
        try {
            //将图片保存到static文件夹里
            sarFile.transferTo(new File(filePath + uploadDirName + File.separator + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileNameAfterCut = prefixName + ".png";
        //调用算法处理SAR图像
        String in = filePath + uploadDirName + File.separator + fileName;
        String out = projectPath + resourcePath + "static\\upload\\SARAfterCut\\" + uploadDirName + File.separator + fileNameAfterCut;


        //判断SARAfterCut文件夹是否存在，不存在则创建
        File fileDirAfter = new File(projectPath + resourcePath + "static\\upload\\SARAfterCut\\");
        if (!fileDirAfter.exists()) {
            fileDirAfter.mkdir();
        }
        //创建不重复的输出目录
        File fileDirOut = new File(projectPath + resourcePath + "static\\upload\\SARAfterCut\\" + uploadDirName);
        fileDirOut.mkdir();
        /*FCM fcm = null;
        try {
            fcm = new FCM();
            fcm.fcm(in, out);
        } catch (MWException e) {
            e.printStackTrace();
        }*/
        try {
            Fcm.fcm3(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        originFilePath = in;
        newFilePath = out;
        newFilePathRelative = "static\\upload\\SARAfterCut\\" + uploadDirName + File.separator + fileNameAfterCut;

        return "success";
    }

    @GetMapping(value = "/upload/sar/getImageData", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getOriginImageData(String type) throws IOException {
        String path = "";
        switch (type) {
            case "o":
                path = originFilePath;
                break;
            case "n":
                path = newFilePath;
                break;
        }
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        inputStream.close();
        return bytes;
    }

    //--------------------------------分割---------------------------------------------
    @PostMapping("/upload/sar/getArea")
    @ResponseBody
    public String getArea(Model model, @RequestParam("reservoirName") String reservoirName,
                             @RequestParam("satelliteName") String satelliteName, @RequestParam("cycle") String cycle,
                             @RequestParam("date") Date date, @RequestParam("topLeft") String topLeft,
                             @RequestParam("lowerRight") String lowerLeft, @RequestParam("cutAlgo") String cutAlgo){
        Integer reservoir_id = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        //分解上传的经纬度
        String topLongitude = topLeft.substring(0, topLeft.indexOf(" "));
        String topLatitude = topLeft.substring(topLeft.indexOf(" "));
        String lowerLongitude = lowerLeft.substring(0, lowerLeft.indexOf(" "));
        String lowerLatitude = lowerLeft.substring(lowerLeft.indexOf(" "));
        String path = newFilePathRelative;
        SarImgDao sarImgDao = new SarImgDao(0, reservoir_id, satelliteName, date, Integer.parseInt(cycle),
                path, topLongitude, lowerLongitude, topLatitude, lowerLatitude, cutAlgo);
        //将处理后的影像数据存入数据库
        sarImgService.insert(sarImgDao);
        //计算水域面积
        Count count = new Count(Double.parseDouble(topLatitude), Double.parseDouble(lowerLatitude), Double.parseDouble(topLongitude), Double.parseDouble(lowerLongitude));
        waterArea = count.getWaterArea(newFilePath);
        return waterArea;
    }

    @PostMapping("/upload/sar/saveArea")
    @ResponseBody
    public String save(@RequestParam("reservoirName") String reservoirName,
                             @RequestParam("date") Date date, @RequestParam("cutAlgo") String cutAlgo){
        Integer reservoir_id = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        Integer cutId = cutAlgoService.selectByName(cutAlgo).getId();
        //获取影像id
        Integer imgId = sarImgService.selectByPath(newFilePathRelative).getId();
        //判断当前年份是否有面积数据，如果有，则可以拟合
        //新建sql语句，从数据库中选出当前年份的数据
        List<WaterAreaDao> currentYear = waterAreaDaoMapper.selectCurrentYear(1);
        //将水域面积存入数据库
        WaterAreaDao waterAreaDao = new WaterAreaDao(0, reservoir_id, waterArea, imgId, cutId, date, (byte) 1);
        waterAreaService.insert(waterAreaDao);
        //如果当前年份面积数据个数大于0，则根据取出的数据进行拟合
        if(currentYear.size() > 0) {
            //获取水位最近一次拟合参数 ? 无法判断是否有关于 水位的拟合参数
            FittingFormulaDao recentMeasuredParameter = fittingFormulaDaoMapper.selectRecentlyByType("measured");

        } else{

        }
        return "success";
    }
}

