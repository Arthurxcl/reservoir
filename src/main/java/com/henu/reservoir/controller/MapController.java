package com.henu.reservoir.controller;

import com.henu.reservoir.domain.ReservoirInfoDao;
import com.henu.reservoir.service.ReservoirInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class MapController {
    private ReservoirInfoService reservoirInfoService;

    @Autowired
    private void setReservoirInfoService(ReservoirInfoService reservoirInfoService){
        this.reservoirInfoService = reservoirInfoService;
    }

    @GetMapping("api/map/isWaterArea")
    @ResponseBody
    public String getStaticMap(double lng, double lat, int zoom){
        //用于判断百度地图中点击的区域是否为水域

        //百度地图静态地图API
        String sUrl = "http://api.map.baidu.com/staticimage?ak=nH5MljbSZqw21joZydvfbW6jGXZBOu6c&width=100&height=100&center="
                + lng + "," + lat + "&zoom=" + zoom;
        try {
            //使用HttpURLConnection发送http请求
            URL url = new URL(sUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);

            //获取返回的数据流
            byte[] data = readInputStream(httpURLConnection.getInputStream());
            httpURLConnection.disconnect();

            //创建临时文件，存放图像数据
            File file = File.createTempFile("temp", ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.close();

            //读取图像
            BufferedImage bufferedImage = ImageIO.read(file);

            //根据RGB值判断左上角、右上角、中心点、左下角、右下角像素是否为水体颜色
            int width = bufferedImage.getWidth()-1;
            int height = bufferedImage.getHeight()-1;
            int minX = bufferedImage.getMinX();
            int minY = bufferedImage.getMinY();
            if(isWaterAreaRGB(getRGB(bufferedImage.getRGB(minX, minY)))){
                return "true";
            }
            else if(isWaterAreaRGB(getRGB(bufferedImage.getRGB(width, minY)))){
                return "true";
            }
            else if(isWaterAreaRGB(getRGB(bufferedImage.getRGB((width-minX)/2, (height-minY)/2)))){
                return "true";
            }
            else if(isWaterAreaRGB(getRGB(bufferedImage.getRGB(minX, height)))){
                return "true";
            }
            else if(isWaterAreaRGB(getRGB(bufferedImage.getRGB(width, height)))){
                return "true";
            }

            //删除临时文件
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
    //将水库名转成水库id保存到session中
    @GetMapping("api/setReservoirId")
    @ResponseBody
    public String setReservoir(String rname, HttpSession session){
        ReservoirInfoDao dao = reservoirInfoService.findReservoirInfoByName(rname);
        if(dao!=null){
            session.setAttribute("reservoirId", dao.getId());
            return "success";
        }
        return "error";
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    private int[] getRGB(int data){
        int[] rgb = new int[3];
        rgb[0] = (data & 0xff0000) >> 16;
        rgb[1] = (data & 0xff00) >> 8;
        rgb[2] = data & 0xff;
        return rgb;
    }

    private boolean isWaterAreaRGB(int[] rgb){
        if(rgb[0] == 167 && rgb[1] == 192 && rgb[2] == 224){
            return true;
        }
        return false;
    }


}


