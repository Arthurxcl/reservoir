package com.henu.reservoir.util.countWaterArea;

public class PicPoint {

	int RGB ;//灰度值
	int P_x;//该点在图像中的x坐标
	int P_y;//该点在图像中的y坐标
	
	public  PicPoint(){
		
	}
	public  PicPoint(int RGB,int P_x,int P_y){
		this.RGB = RGB;
		this.P_x = P_x;
		this.P_y = P_y;
	}
	
	public void setPointRGB(int RGB){
		
		this.RGB = RGB;
	}
	
	public int getPointRGB(){
		return this.RGB;
	}
	
	public void setPointX(int P_x){
		
		this.P_x = P_x;
	}
	
	public int getPointX(){
		return this.P_x;
	}
	
	public void setPointY(int P_y){
		
		this.P_y = P_y;
	}
	
	public int getPointY(){
		return this.P_y;
	}
}
