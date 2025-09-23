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
        System.out.println("Requesting character with id " + id);
        character = apiClient.getCharacterById(id);
        System.out.println("and the character is: "+  id);
    }

    @Then("the response should contain the name {string}")
    public void the_response_should_contain_the_name(String expectedName) {
        logger.info("Validating response name: {}", character.getName());
        assertEquals(expectedName, character.getName());
    }
}
