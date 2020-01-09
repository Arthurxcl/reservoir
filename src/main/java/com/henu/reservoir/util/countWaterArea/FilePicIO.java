package com.henu.reservoir.util.countWaterArea;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FilePicIO {

	String path;
	int pic_width;
	int pic_height;
	int minx;
	int miny;
	BufferedImage bi;
	
	public FilePicIO(String path){
		this.path = path;
		File fpicdata=new File(path);
		//BufferedImage bi = null;
		try {
			this.bi = ImageIO.read(fpicdata);  
		} catch (Exception e) {
			e.printStackTrace();  
		}
		this.pic_width = bi.getWidth();
		this.pic_height = bi.getHeight();
		//pic_width = 925;
		//pic_height = 579;
		this.minx = bi.getMinX();
		this.miny = bi.getMinY();
	}
	public int getpic_width(){
		return this.pic_width;
	}
	public int getpic_height(){
		return this.pic_height;
	}
	public int getminx(){
		return this.minx;
	}
	public int getminy(){
		return this.miny;
	}
	public BufferedImage getbi(){
		return this.bi;
	}
}
