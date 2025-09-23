Feature: Rick and Morty API testing

  Scenario: Get character by ID
    Given I request character with id 1
    Then the response should contain the name "Rick Sanchez"
