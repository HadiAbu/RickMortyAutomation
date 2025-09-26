package com.example.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class DriverManager {

    private WebDriver driver;

    public void quitDriver() {
        if (this.driver != null) {
            this.driver.quit();
            log.info("Closing WebDriver");
            this.driver = null;
        }
    }

    public WebDriver getDriver() {
        if (this.driver == null) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            log.info("Launching new Chrome browser");
        }
        return driver;
    }
}
