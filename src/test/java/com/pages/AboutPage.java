package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage {

    private WebDriver driver;

    @FindBy(css = "a[href='/about']")
    private WebElement aboutLink;

    public AboutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Make sure this is called!
    }

    public void clickAbout() {
        aboutLink.click();
    }
}
