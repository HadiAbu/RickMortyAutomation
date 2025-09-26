package com.example.steps;

import org.junit.Assert;
import com.example.config.DriverManager;
import com.example.pages.AboutPage;
import com.example.pages.BasePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AboutSteps {

    private final AboutPage aboutPage;

    public AboutSteps(DriverManager driverManager) {
        BasePage.setDriver(driverManager.getDriver()); // Set driver once for all pages
        this.aboutPage = new AboutPage();             // now safe
        log.info("Initialized AboutSteps with AboutPage");
    }

    @Given("I am on {string}")
    public void I_am_on(String s) {
        log.info("Opening {} page", s);
        
        aboutPage.open(s);
        Assert.assertEquals("The Rick and Morty API", aboutPage.getTitle());
    }
    @Then("I should see {string} mentioned")
    public void I_should_see_mentioned(String s) {
        Assert.assertTrue(aboutPage.containsText(s));
        log.info("'{}' exists in About page", s);
        
    }
    @When("I click on the {string} link")
    public void I_click_on_the_link(String s) {
        if (s.equalsIgnoreCase("About")) {
            aboutPage.clickAboutLink();
        }
        log.info("clicking on the link '{}'", s);
    }
    @Then("I should be redirected to the about page")
    public void I_should_be_redirected_to_the_about_page() {
        aboutPage.waitForAboutPage();
        Assert.assertEquals("About", aboutPage.getTitle());
        log.info("redirected to the about page");
    }
    @Then("I should see information about the technical stuff")
    public void I_should_see_information_about_the_technical_stuff() { 
        Assert.assertTrue(aboutPage.isTechnicalStuffVisible());
        log.info("information about the technical stuff is visible");
    }
}
