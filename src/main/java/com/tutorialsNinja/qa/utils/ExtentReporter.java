package com.tutorialsNinja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReports() {
		ExtentReports extentreports = new ExtentReports();
		File extentreortfile = new File(System.getProperty("user.dir")+"\\test-output\\extentreportfolder\\extentreports.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentreortfile);
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("tutorials ninja test report");
		sparkreporter.config().setDocumentTitle("TN DOcument");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh/mm/ss");
		extentreports.attachReporter(sparkreporter);
		Properties configprop = new Properties(); 
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsNinja\\qa\\config\\config.properties");
		
		try {
		FileInputStream fis = new FileInputStream(configPropFile);
		configprop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		extentreports.setSystemInfo("Application URL",configprop.getProperty("url"));
		extentreports.setSystemInfo("Browser Name", configprop.getProperty("browser"));
		extentreports.setSystemInfo("Email", configprop.getProperty("validEmail"));
		extentreports.setSystemInfo("Email", configprop.getProperty("validPassword"));
		extentreports.setSystemInfo("operating system", configprop.getProperty("os.name"));
		extentreports.setSystemInfo("user name", configprop.getProperty("user.name"));
		extentreports.setSystemInfo("java version", configprop.getProperty("java.version"));
		
		return extentreports;
		
	}

}
