package com.ProQuest.selenium.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtils {
	public static void captureScreenshot(WebDriver webdriver, String filePath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile=new File(filePath);

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

    }
	
	public static void fileWrite(String fileContent, String filePath) throws Exception{
		FileWriter w = new FileWriter(filePath);
		BufferedWriter out = new BufferedWriter(w);
		out.write(fileContent);
		out.flush();

    }
}
