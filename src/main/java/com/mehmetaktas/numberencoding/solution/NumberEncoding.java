package com.mehmetaktas.numberencoding.solution;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mehmetaktas.numberencoding.solution.util.ContactName;
import com.mehmetaktas.numberencoding.solution.util.ContactPhone;
import com.mehmetaktas.numberencoding.solution.util.StringUtil;

public class NumberEncoding {
	private static final Logger logger = LoggerFactory.getLogger(NumberEncoding.class);


	public static String[] findMatchedNames(ContactPhone[] inputPhoneNumber,
			Map<String, ContactName> dictionaryMapping) { 
		 
		 long oldTime = System.currentTimeMillis();
		 
		 
		 for (ContactPhone phoneNumber : inputPhoneNumber) {
			 
			 
			
		}
		 
		 
		 long currentTime = System.currentTimeMillis();
		 
		 logger.info("match operation for : {} takes {}",inputPhoneNumber.toString(), currentTime - oldTime +" ms");
		return null;
	}


	public static Map<String, ContactName>  convertNameListToEncodedMap(
			List<String> allNamesInDictionary) {
		
		Map<String, ContactName> dictionaryMapping = new TreeMap<String, ContactName>(); 
		for (String name : allNamesInDictionary) {
			ContactName contactName = new ContactName(name, StringUtil.removeNonAsciiChars(name), StringUtil.encodeNameToNumber(name));
			dictionaryMapping.put(StringUtil.encodeNameToNumber(StringUtil.removeNonAsciiChars(name)), contactName) ;
		}
		
		return dictionaryMapping ;

	}

	
}
