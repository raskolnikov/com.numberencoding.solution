package com.mehmetaktas.numberencoding.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mehmetaktas.numberencoding.solution.util.ContactName;
import com.mehmetaktas.numberencoding.solution.util.ContactPhone;
import com.mehmetaktas.numberencoding.solution.util.NumberEncodingConstant;
import com.mehmetaktas.numberencoding.solution.util.StringUtil;

public class NumberEncoding {
	private static final Logger logger = LoggerFactory.getLogger(NumberEncoding.class);

	public static Map<ContactPhone, Map<Integer, List<ContactName>>> findMatchedNames(ContactPhone[] inputPhoneNumber, List<ContactName> allContactInDictionary) {

		long oldTime = System.currentTimeMillis();

		Map<ContactPhone, List<String>> matchedContactNameMap = new HashMap<ContactPhone, List<String>>();
		Map<ContactPhone, Map<Integer, List<ContactName>>> contactPhoneContactNameListMap = new HashMap<ContactPhone, Map<Integer, List<ContactName>>>();

		for (ContactPhone phoneNumber : inputPhoneNumber) {
			
			Map<Integer, List<ContactName>> contactNameIndexMap = new TreeMap<Integer, List<ContactName>>();
			
			for (ContactName contact : allContactInDictionary) {

				int index = phoneNumber.getFormattedNumber().indexOf(contact.getEncodedNameNumber());
				if (index > -1) {
					if (!contactNameIndexMap.containsKey(index)) {
						List<ContactName> contactNameListIndex = new ArrayList<ContactName>();
						contactNameListIndex.add(contact);
						contactNameIndexMap.put(index, contactNameListIndex);
					} else {
						List<ContactName> contactNameListIndex = contactNameIndexMap.get(index);
						contactNameListIndex.add(contact);
						contactNameIndexMap.put(index, contactNameListIndex);
					}
					
					 System.out.println (contact.getOriginalName() + " " + contact.getEncodedNameNumber());
				} 
			}
			
			for (int i = 0; i < phoneNumber.getFormattedNumber().length(); i++) {
				if(!contactNameIndexMap.containsKey(i)){
					String indexChar =	""+phoneNumber.getFormattedNumber().charAt(i) ;
					ContactName phoneContact = new ContactName(indexChar, indexChar, indexChar);
					List<ContactName> contactNameListIndex = new ArrayList<ContactName>();
					contactNameListIndex.add(phoneContact);
					contactNameIndexMap.put(i, contactNameListIndex);
				}
					
				
			}
			
			
			
			contactPhoneContactNameListMap.put(phoneNumber, contactNameIndexMap);
		}

		for (Map.Entry<ContactPhone, Map<Integer, List<ContactName>>> contactPhoneNameMap : contactPhoneContactNameListMap.entrySet()) {

			mergeContactNameList(contactPhoneNameMap.getKey(), contactPhoneNameMap.getValue(), 0);

		}

		long currentTime = System.currentTimeMillis();

		logger.info("match operation for : {} takes {}", inputPhoneNumber.toString(), currentTime - oldTime + " ms");
		return contactPhoneContactNameListMap;
	}

	private static Map<Integer, List<ContactName>> mergeContactNameList(ContactPhone currentContactPhone, Map<Integer, List<ContactName>> contactNameIndexMap, int currentIndex) {

		if (currentIndex == contactNameIndexMap.size() - 1) {
			return contactNameIndexMap;
		}

		List<ContactName> contactNameList = contactNameIndexMap.get(currentIndex);
		int nextMatchedIndex = currentIndex;
		List<ContactName> newMatchedContactNameMergedList = new  ArrayList<ContactName>();
		for (ContactName contactName : contactNameList) {
			
			if(currentIndex > 0) {
				String previousStringTag = currentContactPhone.getFormattedNumber().substring(0, currentIndex);
				String newFormattedContactName = previousStringTag + contactName.getFormattedName();
				String newOriginalContactName = previousStringTag + " " + contactName.getOriginalName();
				contactName.setFormattedName(newFormattedContactName);
				contactName.setOriginalName(newOriginalContactName);
			
			}
			
			nextMatchedIndex = contactName.getFormattedName().length();
			if (nextMatchedIndex <= contactNameIndexMap.size() - 1) {
				List<ContactName> nextMatchedContactNameList = contactNameIndexMap.get(nextMatchedIndex);
				List<ContactName> newMatchedContactNameList = nextMatchedContactNameMerge(contactName, nextMatchedContactNameList, "",currentContactPhone.getFormattedNumber().length());
				newMatchedContactNameMergedList.addAll(newMatchedContactNameList);
			}else {
				newMatchedContactNameMergedList.add(contactName) ;
			}

			
			nextMatchedIndex++;
			if (nextMatchedIndex <= contactNameIndexMap.size() - 1) {
				List<ContactName> nextMatchedContactNameList2 = contactNameIndexMap.get(nextMatchedIndex);
				String currentIndexPhoneNumberChar = Character.toString(currentContactPhone.getFormattedNumber().charAt(nextMatchedIndex-1));
				List<ContactName> newMatchedContactNameNextIndexList =	nextMatchedContactNameMerge(contactName, nextMatchedContactNameList2, currentIndexPhoneNumberChar,currentContactPhone.getFormattedNumber().length());
				newMatchedContactNameMergedList.addAll(newMatchedContactNameNextIndexList);
			}

		}
		// override previous list here
		if(newMatchedContactNameMergedList.size() > 0) {
			contactNameIndexMap.put(currentIndex, newMatchedContactNameMergedList) ;
		}else{
			//mergeContactNameList(currentContactPhone, contactNameIndexMap, ++currentIndex);
		}
		
		if (nextMatchedIndex <= contactNameIndexMap.size() - 1) {
			mergeContactNameList(currentContactPhone, contactNameIndexMap, currentIndex);
		} else {
			 mergeContactNameList(currentContactPhone, contactNameIndexMap, ++currentIndex);
		}

		return null;

	}

	private static List<ContactName> nextMatchedContactNameMerge(ContactName contactName, List<ContactName> nextMatchedContactNameList, String unMatchedNumberChar, int phoneNumberLenght) { 
		// Create a Pattern object
	    Pattern pattern = Pattern.compile(NumberEncodingConstant.PATTERN_TWO_SUBSEQUENT_DIGITS);
	    Pattern patternOnlyDigit = Pattern.compile(NumberEncodingConstant.PATTERN_ONLY_DIGITS);
	     
		List<ContactName> newMatchedContactNameList = new ArrayList<ContactName>() ;
		for (ContactName nextMatchedContactName : nextMatchedContactNameList) {
				String newFormattedContactName = contactName.getFormattedName() + unMatchedNumberChar + nextMatchedContactName.getFormattedName();
				String newOriginalContactName = contactName.getOriginalName().concat(" " + unMatchedNumberChar + " " + nextMatchedContactName.getOriginalName());

			      // Now create matcher object.
			    Matcher matcher = pattern.matcher(newFormattedContactName);
			    Matcher matcherOnlyDigit = patternOnlyDigit.matcher(newFormattedContactName);
			    
			    
			//if(!matcher.find() ){
					
					ContactName newMatchedContact = new ContactName(newOriginalContactName, newFormattedContactName, contactName.getEncodedNameNumber()) ;
					newMatchedContactNameList.add(newMatchedContact) ;
					
				//}

		}
		return newMatchedContactNameList ;
	}

	public static Map<String, ContactName> convertNameListToEncodedMap(List<String> allNamesInDictionary) {

		Map<String, ContactName> dictionaryMapping = new TreeMap<String, ContactName>();
		for (String name : allNamesInDictionary) {
			ContactName contactName = new ContactName(name, StringUtil.removeNonAsciiChars(name), StringUtil.encodeNameToNumber(name));
			dictionaryMapping.put(StringUtil.encodeNameToNumber(StringUtil.removeNonAsciiChars(name)), contactName);
		}

		return dictionaryMapping;

	}

}
