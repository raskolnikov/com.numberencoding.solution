package com.mehmetaktas.numberencoding.solution.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

	@Test
	public final void whenStringGivenItShouldRemoveNonAsciiChars() {

		Assert.assertEquals("od", StringUtil.removeNonAsciiChars("o\"d"));
		Assert.assertEquals("Bo", StringUtil.removeNonAsciiChars("Bo\""));
		Assert.assertEquals("Abanderungsantrage",
				StringUtil.removeNonAsciiChars("Aba\"nderungsantra\"ge"));
		Assert.assertEquals("Fest", StringUtil.removeNonAsciiChars("Fest"));
	}

	@Test
	public final void whenCharStringGivenItShouldReturnEncodedNumber() {
		Assert.assertEquals("51", StringUtil.encodeNameToNumber("an"));
		Assert.assertEquals("562", StringUtil.encodeNameToNumber("Mix"));
		Assert.assertEquals("562", StringUtil.encodeNameToNumber("mir"));
		Assert.assertEquals("482", StringUtil.encodeNameToNumber("Tor"));
		Assert.assertEquals("000111", StringUtil.encodeNameToNumber("EEeJNQ"));

	}

}
