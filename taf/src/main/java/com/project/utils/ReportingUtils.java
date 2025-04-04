package com.project.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Collections;

public class ReportingUtils {

    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");
        Configuration config = new Configuration(reportOutputDirectory, "Automation Framework");
        ReportBuilder reportBuilder = new ReportBuilder(
                Collections.singletonList("target/cucumber-reports/cucumber.json"), config);
        reportBuilder.generateReports();
    }
}
