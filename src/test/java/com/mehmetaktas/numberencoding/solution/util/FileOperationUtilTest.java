package com.mehmetaktas.numberencoding.solution.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

import com.mehmetaktas.numberencoding.solution.excetion.FileFormatExcetion;

public class FileOperationUtilTest {
	
	@Test
	public final void whenFilePathIsEnteredThenItShouldCheckTheFileOnTheSystem(){
		  Assert.assertTrue(FileOperationUtil.isFileExist("src/test/resources/input-test.txt"));
		  Assert.assertTrue(FileOperationUtil.isFileExist("src/test/resources/dictionary-test.txt"));
	}
	
	
	@Test
	public final void whenPhoneNumberFileContainsPhoneNumberLongerThanAllowedMaxLenghtThenItShouldThrownExcetion() throws IOException{
		RuntimeException exception = null ;
		String formatFilePath = "src/test/resources/input-format-test.txt" ;
		String longPhoneNumber =  "62423864823648236476246236423862386482364723626427462374723648234==-3534576548654734565865563465358345345345345765888856554866548654865834989340900";
		PrintWriter writer = new PrintWriter(formatFilePath, "UTF-8");
		writer.println(longPhoneNumber);
		writer.close();
		
		File inputFile = new File(formatFilePath) ;
		try {
			FileOperationUtil.isFileContentProperForReading(inputFile,NumberEncodingConstant.MAX_ALLOWED_PHONE_NUMBER_LENGTH);		 
		} catch (FileFormatExcetion e) {
			exception = e;	
		}
		inputFile.deleteOnExit();
		Assert.assertNotNull("Exception was not thrown", exception);
        Assert.assertEquals("\""+longPhoneNumber+"\" is longer than "+NumberEncodingConstant.MAX_ALLOWED_PHONE_NUMBER_LENGTH + " in the file: "+formatFilePath+" .Please check your file format ", exception.getMessage());
		
	}
	
	@Test
	public final void whenDictionaryFileContainsNameLongerThanAllowedMaxLenghtThenItShouldThrownExcetion() throws IOException{
		RuntimeException exception = null ;
		String formatFilePath = "src/test/resources/dictionary-format-test.txt" ;
		String longName =  "sdbfsdbfnsd fsdnbf sbfdbfsdb sdbfbsdnfbdsn fbsdfdshfhsdsdfhsd fsdfhsf hsdhfgsdfghsdjfjhdsgfh sdfhgsdfhsdhsdfg fsdhfgsdhjfgshjd";
		PrintWriter writer = new PrintWriter(formatFilePath, "UTF-8");
		writer.println(longName);
		writer.close();
		
		File inputFile = new File(formatFilePath) ;
		try {
			FileOperationUtil.isFileContentProperForReading(inputFile,NumberEncodingConstant.MAX_ALLOWED_PHONE_NUMBER_LENGTH);		 
		} catch (FileFormatExcetion e) {
			exception = e;	
		}
		inputFile.deleteOnExit();
		Assert.assertNotNull("Exception was not thrown", exception);
        Assert.assertEquals("\""+longName+"\" is longer than "+NumberEncodingConstant.MAX_ALLOWED_PHONE_NUMBER_LENGTH + " in the file: "+formatFilePath+" .Please check your file format ", exception.getMessage());
		
	}
	
	

}
