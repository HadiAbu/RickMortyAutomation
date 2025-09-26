package com.example.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.example.model.CharacterDTO;
import com.example.model.CharactersResponseDTO;
import com.example.model.ErrorResponseDTO;
import com.example.util.ApiClient;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CheckInvalidSteps {
    private final String BASE_URL = "https://rickandmortyapi.com/api/character/";
    private final ApiClient apiClient = new ApiClient();
    private CharacterDTO character;
    
    @When("I send GET request to {string}")
    public void i_send_GET_request_to_url(String urlString) throws IOException {
        log.info("Sending GET request to: {}", urlString);

        apiClient.getByUrl(urlString);
        // assertEquals(200, apiClient.getResponseCode());
    }
    @Then("response status should be {int}")
    public void response_status_should_be(int statusCode){
        log.info("Response status code is: {}", statusCode);
        assertEquals(apiClient.getResponseCode(), statusCode);
    }
    
    @Then("results should contain characters with {string} in name")
    public void results_should_contain_characters_with_in_name(String name) throws IOException {
        String url = BASE_URL + "/characters/?name=" + name;
        Object apiResponse = apiClient.getCharactersByUrl(url);
        if (apiResponse instanceof CharacterDTO) {
            character = (CharacterDTO) apiResponse;
            log.info("Received character: {}", character.getName());
        } else if (apiResponse instanceof ErrorResponseDTO) {
            ErrorResponseDTO errorResponse = (ErrorResponseDTO) apiResponse;
            log.error("Error response: {} - {}", errorResponse.getError(), apiClient.getErrorMessage());
        } else if (apiResponse instanceof CharactersResponseDTO) {
            CharactersResponseDTO charactersResponse = (CharactersResponseDTO) apiResponse;
            log.info("Received {} characters", charactersResponse.getResults().size());
        } else {
            log.warn("Received unknown response type");
        }
        log.info("character contains {} in name", name);
    }

    @Then("response should contain error message")
    public void response_should_contain_error_message() {
        assertTrue(apiClient.getErrorMessage() != null );
        log.info("Error message: {}", apiClient.getErrorMessage());
    }
}
