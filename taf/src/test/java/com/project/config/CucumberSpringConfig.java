package com.project.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.project.taf.TafApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = TafApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfig {
}
