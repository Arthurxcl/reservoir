package com.henu.reservoir.util.countWaterArea;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.henu.reservoir.util.countWaterArea.PicPoint;

public class UtilWaterPoint {

	public ArrayList<ArrayList<PicPoint>> getAllPoint(int pic_width,int pic_height,
			int minx,int miny,BufferedImage bi,ArrayList<ArrayList<PicPoint>> all_Point)
	{
		int[] rgb = new int[3];
		for(int j = minx ; j < pic_width;j++){
			ArrayList<PicPoint> all_Element = new ArrayList<PicPoint>();
			
			for(int i = miny; i < pic_height;i++){
				int pixel = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				
				if(rgb[0]==0 && rgb[1]==0 && rgb[2]==0){
					PicPoint ppt = new PicPoint(255,j,i);//在灰度值中 黑色 = 255 ,RGB值中 黑色 = [0,0,0]
					all_Element.add(ppt);
					
				}else{
					PicPoint ppt = new PicPoint(0,j,i);
					all_Element.add(ppt);
				}
			}

			all_Point.add(all_Element);
			
		}
		return all_Point;
	}
	public void getWaterPoint(ArrayList<ArrayList<PicPoint>> all_Point,ArrayList<PicPoint> arrPoint){
		for(int j = 0 ; j < all_Point.size();j++){
			for(int i = 0 ; i < all_Point.get(j).size();i++){
				if(all_Point.get(j).get(i).getPointRGB() == 255){
					
					arrPoint.add(all_Point.get(j).get(i));
				}
			}
		}
	}
}
