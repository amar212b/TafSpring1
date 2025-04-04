package com.project.controller;

import com.project.utils.ReportingUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @PostMapping("/run")
    public ResponseEntity<String> runTests(@RequestParam String browser, @RequestParam(required = false) String tags) {
        try {
            System.setProperty("browser", browser);
            if (tags != null) {
                System.setProperty("cucumber.filter.tags", tags);
            }

            // Run tests using Cucumber Runner
            Process process = Runtime.getRuntime().exec("mvn clean test");
            process.waitFor();

            return ResponseEntity.ok("Tests executed successfully. Reports available.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Test execution failed: " + e.getMessage());
        }
    }

    @GetMapping("/report")
    public ResponseEntity<String> getReport() {
        String reportPath = "target/cucumber-reports/cucumber-html-reports/overview-features.html";
        return ResponseEntity.ok("Report available at: " + reportPath);
    }
}
