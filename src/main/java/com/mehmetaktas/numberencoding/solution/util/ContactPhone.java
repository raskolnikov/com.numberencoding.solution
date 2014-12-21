package com.mehmetaktas.numberencoding.solution.util;

public class ContactPhone {
	
	private String originalNumber;
	private String formattedNumber;
	public ContactPhone(String originalName, String formattedName) { 
		this.originalNumber = originalName;
		this.formattedNumber = formattedName;
	}
	public String getOriginalName() {
		return originalNumber;
	}
	public void setOriginalName(String originalName) {
		this.originalNumber = originalName;
	}
	public String getFormattedName() {
		return formattedNumber;
	}
	public void setFormattedName(String formattedName) {
		this.formattedNumber = formattedName;
	}
	
	
	

}
