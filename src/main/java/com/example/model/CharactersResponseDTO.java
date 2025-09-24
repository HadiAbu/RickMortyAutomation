package com.example.model;

import java.util.List;

public class CharactersResponseDTO {
    private List<CharacterDTO> results;

    public List<CharacterDTO> getResults() { return results; }
    public void setResults(List<CharacterDTO> results) { this.results = results; }
}
