package com.henu.reservoir.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henu.reservoir.dao.FittingFormulaDaoMapper;
import com.henu.reservoir.dao.WaterAreaDaoMapper;
import com.henu.reservoir.domain.FittingFormulaDao;
import com.henu.reservoir.domain.OpticalImgDao;
import com.henu.reservoir.domain.WaterAreaDao;
import com.henu.reservoir.service.CutAlgoService;
import com.henu.reservoir.service.OpticalImgService;
import com.henu.reservoir.service.ReservoirInfoService;
import com.henu.reservoir.service.WaterAreaService;
import com.henu.reservoir.util.CalculateByDate;
import com.henu.reservoir.util.FittingFormula;
import com.henu.reservoir.util.countWaterArea.Count;
import fcm_java.ltycl.Sblty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@EnableAutoConfiguration
public class OpticalUploadController {
    @Autowired
    private OpticalImgService opticalImgService;
    @Autowired
    private ReservoirInfoService reservoirInfoService;
    @Autowired
    private CutAlgoService cutAlgoService;
    @Autowired
    private WaterAreaService waterAreaService;
    @Autowired
    private WaterAreaDaoMapper waterAreaDaoMapper;
    @Autowired
    private FittingFormulaDaoMapper fittingFormulaDaoMapper;

    @Value("${path.resource-path}")
    private String resourcePath;

    private String originFilePath;
    private String newFilePath;
    private String newFilePathRelative;
    private String waterArea;

    @PostMapping(value = "/upload/optical/opticalfile")
    @ResponseBody
    public String upload_Optical(@RequestParam("opticalFile") MultipartFile opticalFile) throws IOException, InterruptedException {
        //获取文件名
        String fileName = opticalFile.getOriginalFilename();
        //获取文件后缀名
        //String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取文件前缀
        assert fileName != null;
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
        String filePathUpload = projectPath + resourcePath + "static\\upload\\";
        //判断Upload文件夹是否存在，不存在则创建
        File fileDirUpload = new File(filePathUpload);
        if (!fileDirUpload.exists()) {
            fileDirUpload.mkdir();
        }
        //完整文件名
        String filePath = projectPath + resourcePath + "static\\upload\\opticalImg\\";
        //判断opticalImg文件夹是否存在，不存在则创建
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
            opticalFile.transferTo(new File(filePath + uploadDirName + File.separator + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileNameAfterCut = prefixName + ".png";
        //调用算法处理Optiocal图像
        String in = filePath + uploadDirName + File.separator + fileName;
        String out = projectPath + resourcePath + "static\\upload\\opticalAfterCut\\" + uploadDirName + File.separator + fileNameAfterCut;

        //判断opticalAfterCut文件夹是否存在，不存在则创建
        File fileDirAfter = new File(projectPath + resourcePath + "static\\upload\\opticalAfterCut\\");
        if (!fileDirAfter.exists()) {
            fileDirAfter.mkdir();
        }
        //创建不重复的输出目录
        File fileDirOut = new File(projectPath + resourcePath + "static\\upload\\opticalAfterCut\\" + uploadDirName);
        fileDirOut.mkdir();

        /*FCM fcm = null;
        try {
            fcm = new FCM();
            fcm.fcm(in, out);
        } catch (MWException e) {
            e.printStackTrace();
        }*/
        try {
            Sblty.ltycl(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        originFilePath = in;
        newFilePath = out;
        newFilePathRelative = "static\\upload\\opticalAfterCut\\" + uploadDirName + File.separator + fileNameAfterCut;

        return "success";
    }

    @GetMapping(value = "/upload/optical/getImageData", produces = MediaType.IMAGE_JPEG_VALUE)
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

    @PostMapping("/upload/optical/getArea")
    @ResponseBody
    public String getArea(Model model, @RequestParam("reservoirName") String reservoirName,
                             @RequestParam("satelliteName") String satelliteName, @RequestParam("cycle") String cycle,
                             @RequestParam("date") Date date, @RequestParam("topLeft") String topLeft,
                             @RequestParam("lowerRight") String lowerLeft, @RequestParam("cutAlgo") String cutAlgo) {
        //处理上传的数据
        //根据水库名称获取水库id
        Integer reservoir_id = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        //分解上传的经纬度
        String topLongitude = topLeft.substring(0, topLeft.indexOf(" "));
        String topLatitude = topLeft.substring(topLeft.indexOf(" "));
        String lowerLongitude = lowerLeft.substring(0, lowerLeft.indexOf(" "));
        String lowerLatitude = lowerLeft.substring(lowerLeft.indexOf(" "));
        String path = newFilePathRelative;
        OpticalImgDao opticalImgDao = new OpticalImgDao(0, reservoir_id, satelliteName, date, Integer.parseInt(cycle),
                path, topLongitude, lowerLongitude, topLatitude, lowerLatitude, cutAlgo);
        //将处理后的影像数据存入数据库
        opticalImgService.insert(opticalImgDao);
        //计算水域面积
        Count count = new Count(Double.parseDouble(topLatitude), Double.parseDouble(lowerLatitude), Double.parseDouble(topLongitude), Double.parseDouble(lowerLongitude));
        waterArea = count.getWaterArea(newFilePath);
        return waterArea;
    }

    @PostMapping("/upload/optical/saveArea")
    @ResponseBody
    public String save(@RequestParam("reservoirName") String reservoirName,
                       @RequestParam("date") Date date, @RequestParam("cutAlgo") String cutAlgo){
        Integer reservoir_id = reservoirInfoService.findReservoirInfoByName(reservoirName).getId();
        //根据分割算法名称获取id
        Integer cutId = cutAlgoService.selectByName(cutAlgo).getId();
        //获取影像id
        Integer imgId = opticalImgService.selectByPath(newFilePathRelative).getId();
        //将水域面积存入数据库
        WaterAreaDao waterAreaDao = new WaterAreaDao(0, reservoir_id, waterArea, imgId, cutId, date, (byte) 0);
        waterAreaService.insert(waterAreaDao);
        //拟合
        CalculateByDate calculateByDate = new CalculateByDate(fittingFormulaDaoMapper);
        //如果有实测水位和日期的拟合参数
        if(calculateByDate.judgeMeasured()) {
            getMeasuredFittingResult();
        }
        //如果有遥测水位和日期的拟合参数
        if(calculateByDate.judgeRadar()) {
            getRadarFittingResult();
        }
        return "success";
    }

    /**
     * 光学影像面积和实测水位拟合
     * 每次保存光学面积时调用
     * 但是此方法默认实测水位和遥测水位已经拟合完成，没有判空
     */
    public void getMeasuredFittingResult() {
        //从数据库中选出当前年份的数据
        List<WaterAreaDao> currentYearArea = waterAreaDaoMapper.selectCurrentYear(0);
        //如果当前年份面积数据个数大于1，则根据取出的数据进行拟合
        Integer num = currentYearArea.size();
        if(num > 1) {
            CalculateByDate calculateByDate = new CalculateByDate(fittingFormulaDaoMapper);
            //可以根据实测水位和遥测水位对面积进行拟合
            //获得每个水域面积对应的水位高度（日期）
            double[] measuredX = new double[num];
            double[] radarX = new double[num];
            double[] y = new double[num];
            Double currentMeasuredLevel;
            Double currentRadarLevel;
            for (int i = 0; i < num; i++) {
                //获得当前一条数据的日期
                Integer currentDay = CalculateByDate.getDayByDate(currentYearArea.get(i).getDate());
                //获取最近一条拟合参数，并根据拟合参数获得当前水位，并存入数组中
                //实测水位和遥测水位
                currentMeasuredLevel = calculateByDate.calMeasuredLevel(currentDay);
                measuredX[i] = currentMeasuredLevel;
                y[i] = Double.parseDouble(currentYearArea.get(i).getArea());
            }
            //sar水域面积和实测水位进行拟合
            double[] fittingResult = FittingFormula.waterlevelfit(measuredX, y);
            //将拟合结果存入拟合结果表
            FittingFormulaDao fittingFormulaDao = new FittingFormulaDao(0, fittingResult[0], fittingResult[1], fittingResult[2],
                    fittingResult[3], fittingResult[4], fittingResult[5], new Date(), "optical_measured");
            fittingFormulaDaoMapper.insert(fittingFormulaDao);
        }
    }

    public void getRadarFittingResult() {
        //从数据库中选出当前年份的数据
        List<WaterAreaDao> currentYearArea = waterAreaDaoMapper.selectCurrentYear(0);
        //如果当前年份面积数据个数大于1，则根据取出的数据进行拟合
        Integer num = currentYearArea.size();
        if(num > 1) {
            CalculateByDate calculateByDate = new CalculateByDate(fittingFormulaDaoMapper);
            //可以根据实测水位和遥测水位对面积进行拟合
            //获得每个水域面积对应的水位高度（日期）
            double[] measuredX = new double[num];
            double[] radarX = new double[num];
            double[] y = new double[num];
            Double currentMeasuredLevel;
            Double currentRadarLevel;
            for (int i = 0; i < num; i++) {
                //获得当前一条数据的日期
                Integer currentDay = CalculateByDate.getDayByDate(currentYearArea.get(i).getDate());
                //获取最近一条拟合参数，并根据拟合参数获得当前水位，并存入数组中
                currentRadarLevel = calculateByDate.calRadarLevel(currentDay);
                radarX[i] = currentRadarLevel;
                y[i] = Double.parseDouble(currentYearArea.get(i).getArea());
            }
            //sar水域面积和遥测水位进行拟合
            double[] fittingResult1 = FittingFormula.waterlevelfit(radarX, y);

            //将拟合结果存入拟合结果表
            FittingFormulaDao fittingFormulaDao1 = new FittingFormulaDao(0, fittingResult1[0], fittingResult1[1], fittingResult1[2],
                    fittingResult1[3], fittingResult1[4], fittingResult1[5], new Date(), "optical_radar");
            fittingFormulaDaoMapper.insert(fittingFormulaDao1);
        }
    }

    /**
     * 前端请求当前年份的光学面积数据
     * @return String
     */
    @GetMapping(value = "/getCurrentOpticalArea")
    @ResponseBody
    public String getCurrentOpticalArea() {
        //获取今年的光学面积数据
        List<WaterAreaDao> allSarArea = waterAreaDaoMapper.selectCurrentYear(0);
        ObjectMapper mapper = new ObjectMapper();
        //转换成json返回
        try {
            return mapper.writeValueAsString(allSarArea);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
