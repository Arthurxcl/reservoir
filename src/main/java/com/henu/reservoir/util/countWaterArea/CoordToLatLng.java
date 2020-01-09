package com.henu.reservoir.util.countWaterArea;

public class CoordToLatLng {
	double lat;//纬度
	double lng;//经度
	int p_y;//y坐标（纵坐标）
	int p_x;//x坐标（横坐标）
	
	//y坐标转纬度
	public double getLat(double lat_south,double lat_north,int pic_height,int p_y){
		double lat_Height = lat_north - lat_south;//图像总纬度距离
		double pc = lat_Height/pic_height;//图像单位纬度距离
		double p_pc = p_y*pc;
		this.lat = p_pc+lat_south;
		//System.out.println();
		return this.lat;
	}
	
	//x坐标转经度
	public double getLng(double lng_west,double lng_east,int pic_width,int p_x){
		double lng_Width = lng_east - lng_west;//图像总经度距离
		double pc = lng_Width/pic_width;//图像单位经度距离
		double p_pc = p_x*pc;
		this.lng = p_pc+lng_west;
		return this.lng;
	}
	//纬度转y坐标
	public int getP_y(double lat_south,double lat_north,int pic_height,double lat){
		double lat_Height = lat_north - lat_south;//图像总纬度距离
		double lat_h = lat - lat_south;//所选点的纬度到图像底部的距离
		double lat_proportion = lat_h/lat_Height;
		this.p_y = (int)Math.round(lat_proportion*pic_height);//所选点的纵坐标（四舍五入取整）
		return this.p_y;
	}
	//经度转x坐标
	public int getP_x(double lng_west,double lng_east,int pic_width,double lng){
		double lng_Width = lng_east - lng_west;//图像总经度距离
		double lng_w = lng - lng_west;//所选点的经度到图像左部的距离
		double lng_proportion = lng_w/lng_Width;
		this.p_x = (int)Math.round(lng_proportion*pic_width);//所选点的横坐标（四舍五入取整）
		return this.p_x;
	}
}
