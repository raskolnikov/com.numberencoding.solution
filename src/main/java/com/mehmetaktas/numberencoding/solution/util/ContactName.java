package com.mehmetaktas.numberencoding.solution.util;

public class ContactName {
	
	private String originalName;
	private String formattedName;
	private String encodedNameNumber;
	public ContactName(String originalName, String formattedName, String encodedNameNumber) { 
		this.originalName = originalName;
		this.formattedName = formattedName;
		this.encodedNameNumber = encodedNameNumber;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getFormattedName() {
		return formattedName;
	}
	public void setFormattedName(String formattedName) {
		this.formattedName = formattedName;
	}
	public String getEncodedNameNumber() {
		return encodedNameNumber;
	}
	public void setEncodedNameNumber(String encodedNameNumber) {
		this.encodedNameNumber = encodedNameNumber;
	}
	

}
