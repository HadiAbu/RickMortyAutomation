package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected static WebDriver driver;

    public BasePage(WebDriver webDriver) {
        // only initialize if driver is set
        if (driver != null) {
            PageFactory.initElements(driver, this);
            
        }
    }

    // Initialize driver once (e.g., via DriverManager)
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }
    
    protected void initElements() {
        PageFactory.initElements(driver, this);
    }
}
