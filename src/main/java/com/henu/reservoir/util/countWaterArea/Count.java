package com.henu.reservoir.util.countWaterArea;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Count {
	//图片路径
	private String imgPath;

	public String getWaterArea(String path) {
		// TODO Auto-generated method stub
		imgPath = path;
		//String imgPath = "D:\\Documents\\JavaWeb\\reservoir-data\\optical\\90819.bmp";//目标图像
		
		double lat_north = 34.941;//图像的北纬度
		double lat_south = 34.897;//图像的南纬度
		double lng_west = 113.646;//图像的西经度
		double lng_east = 113.703;//图像的东经度
		
		FilePicIO fp = new FilePicIO(imgPath);
		int pic_width = fp.getpic_width();
		int pic_height = fp.getpic_height();
		int minx = fp.getminx();
		int miny = fp.getminy();
		BufferedImage bi = fp.getbi();
		
		ArrayList<ArrayList<PicPoint>> all_Point = new ArrayList<ArrayList<PicPoint>>();//存放所有水面像素点的数组
		ArrayList<PicPoint> arrPoint = new ArrayList<PicPoint>();
		UtilWaterPoint uwp = new UtilWaterPoint();
		
		all_Point=uwp.getAllPoint(pic_width, pic_height, minx, miny, bi, all_Point);//获得所有点（灰度值，横坐标，纵坐标）
		uwp.getWaterPoint(all_Point, arrPoint);
		double River_area = arrPoint.size();
		
		
		
		LocationUtils lu = new LocationUtils();
		double lat_distance = lu.getDistance(lat_north,lng_east,lat_south,lng_east);//高
		double lng_distance = lu.getDistance(lat_north,lng_east,lat_north,lng_west);//宽
		
		double pixel_height = lat_distance/pic_width;//像素点高
		double pixel_width = lng_distance/pic_height;//像素点宽
		double pixel_area = pixel_height * pixel_width;
		System.out.println("图片高(像素点个数)："+pic_height);
		System.out.println("图片宽(像素点个数)："+pic_width);
		System.out.println("像素点高:"+pixel_height+"米");
		System.out.println("像素点宽:"+pixel_width+"米");
		System.out.println("黑色区域面积(像素点个数):" + River_area);
		River_area = River_area*pixel_area;
		System.out.println("水域面积:" + River_area+"平方米");

		return Double.toString(River_area);
	}

}
