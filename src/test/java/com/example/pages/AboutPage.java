package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutPage extends BasePage {

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(className = "hero-image")
    private WebElement heroImage;

    @FindBy(css = "a[href='/about']")
    private WebElement aboutLink;

    @FindBy(id = "what-is-this")
    private WebElement aboutSection;

    @FindBy(id = "technical-stuff")
    private WebElement technicalStuffSection;

    public AboutPage() {
        super(); // PageFactory.initElements(driver, this) called in BasePage
        initElements(); // safe initialization
    }

    public void open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(heroImage));
    }

    public void clickAboutLink() {
        aboutLink.click();
    }

    public void waitForAboutPage() {
        wait.until(ExpectedConditions.visibilityOf(aboutSection));
    }

    public boolean isTechnicalStuffVisible() {
        return technicalStuffSection.isDisplayed();
    }

    public boolean containsText(String text) {
        return driver.getPageSource().contains(text);
    }
}
