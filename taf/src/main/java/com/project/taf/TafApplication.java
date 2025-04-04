package com.project.taf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.project") // Ensure all sub-packages are scanned

public class TafApplication {

    public static void main(String[] args) {
        System.setProperty("server.port", "9090"); // Set port explicitly
        SpringApplication.run(TafApplication.class, args);
    }

}
