package com.example.steps;

import com.example.model.CharacterDTO;
import com.example.util.ApiClient;
import io.cucumber.java.en.*;
import lombok.extern.log4j.Log4j2;

import static org.junit.Assert.assertEquals;

@Log4j2
public class CharacterSteps {

    private final ApiClient apiClient = new ApiClient();
    private CharacterDTO character;

    @Given("I request character with id {int}")
    public void i_request_character_with_id(int id) throws Exception {
        log.info("Requesting character with id {}", id);
        character = (CharacterDTO) apiClient.getCharacterById(id);
    }

    @Then("the response should contain the name {string}")
    public void the_response_should_contain_the_name(String expectedName) {
        assertEquals(character.getName(),expectedName);
        log.info("Validating response name: {}", character.getName());
    }

    @And("the species should be {string}")
    public void the_species_should_be(String expectedRes) {
        log.info("the species should be: {}" + expectedRes);
        assertEquals(character.getSpecies(), expectedRes);
    }

    @And("the status should be {string}")
    public void the_status_should_be(String expectedStatus) {
        log.info("The valid status should be: {}", expectedStatus);
        assertEquals(character.getStatus(), expectedStatus);
    }
}
