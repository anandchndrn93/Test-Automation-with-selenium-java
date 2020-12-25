package com.demoblaze.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenShotUtility {

	public static String getScreenShot(WebDriver driver, String methodName) {

		TakesScreenshot screenShotObj = (TakesScreenshot) driver;
		File src = screenShotObj.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/ScreenShots/" + methodName + "-" + System.currentTimeMillis()
				+ ".jpg";
		File dest = new File(path);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return path;

	}

}
