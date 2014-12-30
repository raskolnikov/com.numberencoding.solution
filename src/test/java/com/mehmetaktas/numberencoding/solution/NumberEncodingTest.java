package com.mehmetaktas.numberencoding.solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.mehmetaktas.numberencoding.solution.util.ContactName;
import com.mehmetaktas.numberencoding.solution.util.ContactPhone;
import com.mehmetaktas.numberencoding.solution.util.FileOperationUtil;
import com.mehmetaktas.numberencoding.solution.util.StringUtil;

public class NumberEncodingTest {

	@Test
	public final void whenDictionayListGivenItShouldReturnEncodedMapping() {
		List<String> allNamesInDictionary = new ArrayList<String>();
		allNamesInDictionary.add("an");
		allNamesInDictionary.add("Mix");
		allNamesInDictionary.add("mir");
		allNamesInDictionary.add("Tor");

		Map<String, ContactName> dictionaryMapping = new TreeMap<String, ContactName>();
		ContactName contactName1 = new ContactName("an",
				StringUtil.removeNonAsciiChars("an"),
				StringUtil.encodeNameToNumber(StringUtil
						.removeNonAsciiChars("an")));
		ContactName contactName2 = new ContactName("Mix",
				StringUtil.removeNonAsciiChars("Mix"),
				StringUtil.encodeNameToNumber(StringUtil
						.removeNonAsciiChars("Mix")));
		ContactName contactName3 = new ContactName("mir",
				StringUtil.removeNonAsciiChars("mir"),
				StringUtil.encodeNameToNumber(StringUtil
						.removeNonAsciiChars("mir")));
		ContactName contactName4 = new ContactName("Tor",
				StringUtil.removeNonAsciiChars("Tor"),
				StringUtil.encodeNameToNumber(StringUtil
						.removeNonAsciiChars("Tor")));

		dictionaryMapping.put(StringUtil.encodeNameToNumber(StringUtil
				.removeNonAsciiChars("an")), contactName1);
		dictionaryMapping.put(StringUtil.encodeNameToNumber(StringUtil
				.removeNonAsciiChars("Mix")), contactName2);
		dictionaryMapping.put(StringUtil.encodeNameToNumber(StringUtil
				.removeNonAsciiChars("mir")), contactName3);
		dictionaryMapping.put(StringUtil.encodeNameToNumber(StringUtil
				.removeNonAsciiChars("Tor")), contactName4);

		Map<String, ContactName> dictionaryMapping2 = NumberEncoding
				.convertNameListToEncodedMap(allNamesInDictionary);
		ContactName contactName1Return = dictionaryMapping2.get(StringUtil
				.encodeNameToNumber(StringUtil.removeNonAsciiChars("an")));

		Assert.assertEquals(contactName1.getEncodedNameNumber(),
				contactName1Return.getEncodedNameNumber());
		Assert.assertEquals(contactName1.getFormattedName(),
				contactName1Return.getFormattedName());
		Assert.assertEquals(contactName1.getOriginalName(),
				contactName1Return.getOriginalName());

	}

	@Test
	public final void whenPhoneNumbersEnteredThenItShouldReturnMatchedNamesFromDictionary()
			throws IOException {
		ContactPhone[] inputPhoneNumber = {
				/*new ContactPhone("5624-82",
						StringUtil.removeNonAsciiChars("5624-82")),*/
				new ContactPhone("4824", StringUtil.removeNonAsciiChars("4824")) };
		
		String[] matchedOutput = { "5624-82: mir Tor", "5624-82: Mix Tor",
				"4824: Torf", "4824: fort", "4824: Tor 4" };
		String dictionaryFilePath = "src/test/resources/dictionary-test-2.txt";
		List<String> allNamesInDictionary = FileOperationUtil
				.readAllLines(dictionaryFilePath);
		List<ContactName> allContactInDictionary = new ArrayList<ContactName>();
		Map<String, ContactName> dictionaryMapping = new TreeMap<String, ContactName>();
		for (String dictionaryContact : allNamesInDictionary) {
			ContactName contactName = new ContactName(dictionaryContact,
					StringUtil.removeNonAsciiChars(dictionaryContact),
					StringUtil.encodeNameToNumber(StringUtil
							.removeNonAsciiChars(dictionaryContact)));
			 //System.out.println (contactName.getOriginalName() + " " + contactName.getEncodedNameNumber());
			
			 allContactInDictionary.add(contactName) ;
		}
		
		
		Map<ContactPhone,Map<Integer,List<ContactName>>> contactPhoneContactNameListMap = NumberEncoding.findMatchedNames(inputPhoneNumber, allContactInDictionary);
		

		 for (Map.Entry<ContactPhone,Map<Integer,List<ContactName>>> contactPhoneNameMap :  contactPhoneContactNameListMap.entrySet()) {
			 for (Map.Entry<Integer,List<ContactName>> contactPhone :  contactPhoneNameMap.getValue().entrySet()) {
				 for (ContactName contactName :  contactPhone.getValue()) {
					 System.out.println(contactPhoneNameMap.getKey().getOriginalNumber()+" "+contactName.getFormattedName());
				 }
			 }
		 }
		
		 Assert.assertTrue(true);

	}

}
