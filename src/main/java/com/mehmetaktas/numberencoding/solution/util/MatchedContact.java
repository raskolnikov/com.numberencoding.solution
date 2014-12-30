package com.mehmetaktas.numberencoding.solution.util;

public class MatchedContact {

	private ContactPhone contactPhone;
	private String contactCombinedName;

	public MatchedContact(ContactPhone contactPhone,
			String contactCombinedName) {
		this.contactCombinedName = contactCombinedName ;
		this.contactPhone = contactPhone ;
	}

	public ContactPhone getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(ContactPhone contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactCombinedName() {
		return contactCombinedName;
	}

	public void setContactCombinedName(String contactCombinedName) {
		this.contactCombinedName = contactCombinedName;
	}

}
