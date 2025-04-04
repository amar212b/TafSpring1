package com.project.controller;

import com.project.utils.ReportingUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport() {
        try {
            ReportingUtils.generateReport();
            return ResponseEntity.ok("Report generated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to generate report: " + e.getMessage());
        }
    }
}
