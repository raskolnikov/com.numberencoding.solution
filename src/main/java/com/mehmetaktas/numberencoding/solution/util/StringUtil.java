package com.mehmetaktas.numberencoding.solution.util;

public class StringUtil {

	public static String removeNonAsciiChars(String subjectString) {
		String resultString = subjectString.replaceAll("[^A-Za-z0-9]", "");
		return resultString;
		
	}

	public static String encodeNameToNumber(String name) {
		String encodedName = name.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_0, "0")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_1, "1")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_2, "2")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_3, "3")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_4, "4")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_5, "5")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_6, "6")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_7, "7")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_8, "8")
				.replaceAll(NumberEncodingConstant.CHAR_SET_REGEX_9, "9");
		
		return encodedName;
	}

}
