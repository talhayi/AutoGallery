package com.example.otogaleri.Models;

public class LoginPojo {
	private String kadi;
	private String id;

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"LoginPojo{" + 
			"kadi = '" + kadi + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
