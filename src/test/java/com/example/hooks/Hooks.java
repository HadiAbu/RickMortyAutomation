package com.example.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.example.config.DriverManager;


public class Hooks {

 private final DriverManager driverManager;

    public Hooks(DriverManager driverManager) {
        this.driverManager = driverManager;
    }
    // Initialize WebDriver before each scenario
    @Before
    public void beforeScenario() {
        driverManager.getDriver(); 
    }
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driverManager.quitDriver();
    }
}