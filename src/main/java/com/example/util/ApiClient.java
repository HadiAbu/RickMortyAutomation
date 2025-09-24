package com.example.util;

import com.example.model.CharactersResponseDTO;
import com.example.model.ErrorResponseDTO;
import com.example.model.CharacterDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private final Gson gson = new Gson();
    private int responseCode;
    private String errorMessage;

    public int getResponseCode() {
        return responseCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public CharacterDTO getCharacterById(int id) throws IOException {
        String urlString = BASE_URL + "/character/" + id;

        return (CharacterDTO) getByUrl(urlString);
    }

    public CharacterDTO getCharacterByName(String name) throws IOException{
        String urlString = BASE_URL + "/character/?name=" + name;

        return (CharacterDTO) getByUrl(urlString);
    }

    public CharacterDTO getCharacaterByUrl(String urlString) throws IOException{
        return (CharacterDTO) getByUrl(urlString);
    }
 /**
     * Sends GET request and always returns a List<CharacterDTO>
     * Handles single character, list of characters, or error
     */
    public List<CharacterDTO> getCharactersByUrl(String urlString) throws IOException {
         URL url = java.net.URI.create(BASE_URL).toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        responseCode = connection.getResponseCode();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        responseCode >= 200 && responseCode < 300
                                ? connection.getInputStream()
                                : connection.getErrorStream()
                )
        );

        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        String responseBody = json.toString();

        // parse JSON
        JsonElement element = JsonParser.parseString(responseBody);

        // Case 1: list of characters
        if (element.isJsonObject() && element.getAsJsonObject().has("results")) {
            CharactersResponseDTO resp = gson.fromJson(responseBody, CharactersResponseDTO.class);
            return resp.getResults();
        }

        // Case 2: single character
        if (element.isJsonObject() && element.getAsJsonObject().has("name")) {
            CharacterDTO character = gson.fromJson(responseBody, CharacterDTO.class);
            return List.of(character);
        }

        // Case 3: error response
        if (element.isJsonObject() && element.getAsJsonObject().has("error")) {
            return Collections.emptyList();
        }

        return Collections.emptyList(); // fallback
    }
    
    public Object getByUrl(String urlString) throws IOException {
        URL url = java.net.URI.create(urlString).toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        responseCode = conn.getResponseCode();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        responseCode >= 200 && responseCode < 300
                                ? conn.getInputStream()
                                : conn.getErrorStream()
                )
        );

        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        String responseBody = json.toString();

        // Use Gson + simple JSON parsing to determine type
        JsonElement element = JsonParser.parseString(responseBody);

        if (element.isJsonObject()) {
            if (element.getAsJsonObject().has("results")) {
                // List of characters
                return gson.fromJson(responseBody, CharactersResponseDTO.class);
            } else if (element.getAsJsonObject().has("error")) {
                // Error response
                return gson.fromJson(responseBody, ErrorResponseDTO.class);
            } else if (element.getAsJsonObject().has("name")) {
                // Single character
                return gson.fromJson(responseBody, CharacterDTO.class);
            }
        }

        // fallback
        return null;
    }

}