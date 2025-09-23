package com.example.util;

import com.example.model.CharacterDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    public CharacterDTO getCharacterById(int id) throws IOException {
        String urlString = BASE_URL + "/character/" + id;
        URL url = java.net.URI.create(urlString).toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();

        Gson gson = new Gson();
        return gson.fromJson(inline.toString(), CharacterDTO.class);
    }
}
