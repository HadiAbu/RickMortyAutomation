package com.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AboutSteps {
    @Then("I should see {string} mentioned")
    public void I_should_see_mentioned(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("I should see information about the technical stuff")
    public void I_should_see_information_about_the_technical_stuff() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("I should be redirected to the about page")
    public void I_should_be_redirected_to_the_about_page() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I click on the {string} link")
    public void I_click_on_the_link(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("I am on {string}")
    public void I_am_on(String s) {
        // Write code here that turns the phrase above into concrete actions
    }
}
