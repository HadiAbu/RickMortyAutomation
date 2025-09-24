package com.example.steps;

import com.example.model.CharacterDTO;
import com.example.util.ApiClient;
import io.cucumber.java.en.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class CharacterSteps {

    private static final Logger logger = LoggerFactory.getLogger(CharacterSteps.class);
    private final ApiClient apiClient = new ApiClient();
    private CharacterDTO character;

    @Given("I request character with id {int}")
    public void i_request_character_with_id(int id) throws Exception {
        logger.info("Requesting character with id {}", id);
        character = (CharacterDTO) apiClient.getCharacterById(id);
    }

    @Then("the response should contain the name {string}")
    public void the_response_should_contain_the_name(String expectedName) {
        assertEquals(character.getName(),expectedName);
        logger.info("Validating response name: {}", character.getName());
    }

    @And("the species should be {string}")
    public void the_species_should_be(String expectedRes) {
        logger.info("the species should be: {}" + expectedRes);
        assertEquals(character.getSpecies(), expectedRes);
    }

    @And("the status should be {string}")
    public void the_status_should_be(String expectedStatus) {
        logger.info("The valid status should be: {}", expectedStatus);
        assertEquals(character.getStatus(), expectedStatus);
    }
}
