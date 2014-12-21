package com.mehmetaktas.numberencoding.solution.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.mehmetaktas.numberencoding.solution.excetion.FileFormatExcetion;

public class FileOperationUtil {
	
	public static boolean isFileExist(String filePath) {
		File file  = new File(filePath) ;
		if(file.isFile()) {
			return true;
		}
		return false ;
	}

	public static boolean isFileContentProperForReading(File inputFile,
			int maxAllowedLineLength) throws IOException {
		
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(inputFile);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        if(line.length() > maxAllowedLineLength){
		        	throw new FileFormatExcetion("\""+line+"\" is longer than "+maxAllowedLineLength + " in the file: "+inputFile.getPath()+" .Please check your file format " ) ;
		        }
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		
		return true;
	}
	
	public static List<String> readAllLines(String filesname) throws IOException{
		Path file = Paths.get(filesname);
		return Files.readAllLines(file, Charset.defaultCharset());
	}

}
