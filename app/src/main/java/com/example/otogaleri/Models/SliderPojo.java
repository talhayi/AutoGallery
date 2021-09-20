package com.example.otogaleri.Models;

public class SliderPojo{
	private String resim;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	@Override
 	public String toString(){
		return 
			"SliderPojo{" + 
			"resim = '" + resim + '\'' + 
			"}";
		}
}
