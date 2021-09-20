package com.example.otogaleri.Models;

public class AdvertisesPojo{
	private String resim;
	private boolean tf;
	private String aciklama;
	private String ilce;
	private String uyeid;
	private String ilanid;
	private String fiyat;
	private int sayi;
	private String sehir;
	private String baslik;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setIlce(String ilce){
		this.ilce = ilce;
	}

	public String getIlce(){
		return ilce;
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

	public void setFiyat(String fiyat){
		this.fiyat = fiyat;
	}

	public String getFiyat(){
		return fiyat;
	}

	public void setSayi(int sayi){
		this.sayi = sayi;
	}

	public int getSayi(){
		return sayi;
	}

	public void setSehir(String sehir){
		this.sehir = sehir;
	}

	public String getSehir(){
		return sehir;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"AdvertisesPojo{" + 
			"resim = '" + resim + '\'' + 
			",tf = '" + tf + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",ilce = '" + ilce + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			",fiyat = '" + fiyat + '\'' + 
			",sayi = '" + sayi + '\'' + 
			",sehir = '" + sehir + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
