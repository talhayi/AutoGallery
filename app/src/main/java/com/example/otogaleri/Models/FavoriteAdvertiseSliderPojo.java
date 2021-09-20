package com.example.otogaleri.Models;

public class FavoriteAdvertiseSliderPojo{
	private String resimyolu;
	private boolean tf;
	private String ilanid;

	public void setResimyolu(String resimyolu){
		this.resimyolu = resimyolu;
	}

	public String getResimyolu(){
		return resimyolu;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	@Override
 	public String toString(){
		return 
			"FavoriteAdvertiseSliderPojo{" + 
			"resimyolu = '" + resimyolu + '\'' + 
			",tf = '" + tf + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			"}";
		}
}
