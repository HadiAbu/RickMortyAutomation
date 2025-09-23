package com.example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.example.steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
          "json:target/cucumber-reports/Cucumber.json",  // JSON report
        "html:target/cucumber-reports/Cucumber.html",  // HTML report
        "junit:target/cucumber-reports/Cucumber.xml" , // JUnit XML report
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)
public class TestRunner {
}
