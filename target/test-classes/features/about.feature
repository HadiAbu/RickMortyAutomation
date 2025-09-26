Feature: Rick and Morty API Website Navigation

    Scenario: Explore the About page
        Given I am on "https://rickandmortyapi.com/"
        When I click on the "About" link
        Then I should be redirected to the about page
        And I should see information about the technical stuff
        And I should see "Axel Fuhrmann" mentioned