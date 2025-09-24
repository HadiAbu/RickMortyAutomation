package com.example.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import com.example.model.CharacterDTO;
import com.example.model.CharactersResponseDTO;
import com.example.model.ErrorResponseDTO;
import com.example.util.ApiClient;

import org.slf4j.Logger;

// import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckInvalidSteps {
    private final String BASE_URL = "https://rickandmortyapi.com/api/character/";
    private static final Logger logger = LoggerFactory.getLogger(CheckInvalidSteps.class);
    private final ApiClient apiClient = new ApiClient();
    private CharacterDTO character;
    
    @When("I send GET request to {string}")
    public void i_send_GET_request_to_url(String urlString) throws IOException {
        logger.info("Sending GET request to: {}", urlString);

        apiClient.getByUrl(urlString);
        assertEquals(200, apiClient.getResponseCode());
    }
    @Then("response status should be {int}")
    public void response_status_should_be(int statusCode){
        assertEquals(apiClient.getResponseCode(), statusCode);
        logger.info("Response status code is: {}", statusCode);
    }
    
    @Then("results should contain characters with {string} in name")
    public void results_should_contain_characters_with_in_name(String name) throws IOException {
        String url = BASE_URL + "/characters/?name=" + name;
        Object apiResponse = apiClient.getCharactersByUrl(url);
        if (apiResponse instanceof CharacterDTO) {
            character = (CharacterDTO) apiResponse;
            logger.info("Received character: {}", character.getName());
        } else if (apiResponse instanceof ErrorResponseDTO) {
            ErrorResponseDTO errorResponse = (ErrorResponseDTO) apiResponse;
            logger.error("Error response: {} - {}", errorResponse.getError(), apiClient.getErrorMessage());
        } else if (apiResponse instanceof CharactersResponseDTO) {
            CharactersResponseDTO charactersResponse = (CharactersResponseDTO) apiResponse;
            logger.info("Received {} characters", charactersResponse.getResults().size());
        } else {
            logger.warn("Received unknown response type");
        }
        logger.info("character contains {} in name", name);
    }

    @Then("response should contain error message")
    public void response_should_contain_error_message() {
        // Write code here that turns the phrase above into concrete actions
    }
}
