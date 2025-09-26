package com.example.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import com.example.config.DriverManager;
import com.example.pages.AboutPage;
import com.example.pages.BasePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AboutSteps {
    private static final Logger logger = LoggerFactory.getLogger(AboutSteps.class);
    private final AboutPage aboutPage;

    public AboutSteps(DriverManager driverManager) {
        BasePage.setDriver(driverManager.getDriver()); // Set driver once for all pages
        this.aboutPage = new AboutPage();             // now safe
    }

    @Given("I am on {string}")
    public void I_am_on(String s) {
        logger.info("Opening {} page", s);
        
        aboutPage.open(s);
        Assert.assertEquals("The Rick and Morty API", aboutPage.getTitle());
    }
    @Then("I should see {string} mentioned")
    public void I_should_see_mentioned(String s) {
        Assert.assertTrue(aboutPage.containsText(s));
    }
    @When("I click on the {string} link")
    public void I_click_on_the_link(String s) {
            if (s.equalsIgnoreCase("About")) {
            aboutPage.clickAboutLink();
        }
    }
    @Then("I should be redirected to the about page")
    public void I_should_be_redirected_to_the_about_page() {
        aboutPage.waitForAboutPage();
        Assert.assertEquals("About", aboutPage.getTitle());
    }
    @Then("I should see information about the technical stuff")
    public void I_should_see_information_about_the_technical_stuff() { 
        Assert.assertTrue(aboutPage.isTechnicalStuffVisible());
    }
}
