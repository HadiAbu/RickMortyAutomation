Feature: Rick and Morty API testing

  Scenario: Get character by ID
    Given I request character with id 1
    Then the response should contain the name "Rick Sanchez"


  Scenario: Get character by ID
    Given I request character with id 1
    Then the response should contain the name "Rick Sanchez"
    And the status should be "Alive"
    And the species should be "Human"

