package com.demoblaze.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	static ExtentReports extent;

	public static ExtentReports getReport() {
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String path = System.getProperty("user.dir") + "\\test-output\\reports\\UITestReport-" + timeFormat.format(now)
				+ ".html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName(" Demoblaze Automation Results");
		reporter.config().setDocumentTitle("Automation Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
}
