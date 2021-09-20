package com.example.otogaleri.Models;

public class AddAdvertiseImagePojo{
	private boolean tf;
	private String uyeid;
	private String ilanid;
	private String sonuc;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setUyeid(String uyeid){
		this.uyeid = uyeid;
	}

	public String getUyeid(){
		return uyeid;
	}

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	public void setSonuc(String sonuc){
		this.sonuc = sonuc;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
	public String toString(){
		return
				"Response{" +
						"tf = '" + tf + '\'' +
						",uyeid = '" + uyeid + '\'' +
						",ilanid = '" + ilanid + '\'' +
						",sonuc = '" + sonuc + '\'' +
						"}";
	}
}
