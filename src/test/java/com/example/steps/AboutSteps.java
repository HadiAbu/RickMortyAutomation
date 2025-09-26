package com.example.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.config.DriverManager;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AboutSteps {
    private static final Logger logger = LoggerFactory.getLogger(AboutSteps.class);
    private final WebDriver driver;
    private String BASE_URL;
    private WebDriverWait wait;

    public AboutSteps(DriverManager driverManager) {
        this.driver = driverManager.getDriver();
    }

    @Given("I am on {string}")
    public void I_am_on(String s) {
        this.BASE_URL = s;
        logger.info("Opening {} page", this.BASE_URL);
        this.driver.get(this.BASE_URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hero-image")));
        logger.info("Page opened successfully");
        Assert.assertEquals("The Rick and Morty API", driver.getTitle());
    }
    @Then("I should see {string} mentioned")
    public void I_should_see_mentioned(String s) {
        Assert.assertTrue(driver.getPageSource().contains(s));  
    }
    @When("I click on the {string} link")
    public void I_click_on_the_link(String s) {
        WebElement aboutLink = driver.findElement(By.cssSelector("a[href='/about']"));
        aboutLink.click();
    }
    @Then("I should be redirected to the about page")
    public void I_should_be_redirected_to_the_about_page() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("what-is-this")));
        Assert.assertEquals("About", driver.getTitle());
    }
    @Then("I should see information about the technical stuff")
    public void I_should_see_information_about_the_technical_stuff() { 
        WebElement techStuff = driver.findElement(By.id("technical-stuff"));
        Assert.assertTrue(techStuff.isDisplayed()); 
    }
}
