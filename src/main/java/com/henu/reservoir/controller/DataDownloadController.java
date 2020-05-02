package com.henu.reservoir.controller;

import com.henu.reservoir.domain.OpticalImgDao;
import com.henu.reservoir.domain.SarImgDao;
import com.henu.reservoir.service.OpticalImgService;
import com.henu.reservoir.service.SarImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
@EnableAutoConfiguration
public class DataDownloadController {
    private SarImgService sarImgService;
    private OpticalImgService opticalImgService;

    @Value("${path.resource-path}")
    private String resourcePath;

    @Autowired
    private void setService(
            SarImgService sarImgService,
            OpticalImgService opticalImgService
    ){
        this.sarImgService = sarImgService;
        this.opticalImgService = opticalImgService;
    }
    @RequestMapping("api/download/image")
    @ResponseBody
    public String downloadImage(HttpServletResponse response, String type, int id){
        if ("sar".equals(type)){
            //download sar file
            SarImgDao dao = sarImgService.findSarImgById(id);
            downloadFile(response, dao.getPath());
            return "Sar image downloaded.";
        }
        else if ("optical".equals(type)){
            //download optical file
            OpticalImgDao dao = opticalImgService.findOpticalImgById(id);
            downloadFile(response, dao.getPath());
            return "Optical image downloaded.";
        }
        else {
            return "error";
        }
    }

    private void downloadFile(HttpServletResponse response, String path){
        File file = new File(resourcePath + path);
        String filename = path.substring(path.lastIndexOf(File.separator) + 1);
        if (file.exists()){
            try {
                response.addHeader("Content-Disposition",  "attachment; filename=" +  java.net.URLEncoder.encode(filename, "utf-8"));
                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = new FileInputStream(file);
                OutputStream out = response.getOutputStream();
                int len = 0;
                while((len = fileInputStream.read(buffer)) > 0) {
                    out.write(buffer,0,len);
                }
                out.flush();
                fileInputStream.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
