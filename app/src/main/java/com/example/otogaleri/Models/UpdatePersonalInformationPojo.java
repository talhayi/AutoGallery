package com.example.otogaleri.Models;

public class UpdatePersonalInformationPojo{
	private boolean tf;
	private String password;
	private String email;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
	public String toString(){
		return
				"Response{" +
						"tf = '" + tf + '\'' +
						",password = '" + password + '\'' +
						",email = '" + email + '\'' +
						"}";
	}
}
