package com.mehmetaktas.numberencoding.solution.util;

import junit.framework.Assert;

import org.junit.Test;

public class ContactNameTest {
	@Test
	public final void whenConstructorParameterSetThenItShouldCreateInstanceSuccessfully(){
		ContactName contactName = new ContactName("Mix",
				StringUtil.removeNonAsciiChars("Mix"),
				StringUtil.encodeNameToNumber(StringUtil
						.removeNonAsciiChars("Mix")));
		Assert.assertEquals("Mix", contactName.getOriginalName());
		Assert.assertEquals(StringUtil.removeNonAsciiChars("Mix"), contactName.getFormattedName());
		Assert.assertEquals(StringUtil.encodeNameToNumber(StringUtil
				.removeNonAsciiChars("Mix")), contactName.getEncodedNameNumber());

	} 
	
	

}
