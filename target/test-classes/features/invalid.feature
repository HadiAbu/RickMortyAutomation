Feature: Character API Testing

 Scenario: Search characters by name
 Given I send GET request to "https://rickandmortyapi.com/api/character/?name=rick"
 Then response status should be 200
 And results should contain characters with "rick" in name

#  Scenario: Handle invalid character ID
#  Given I send GET request to "https://rickandmortyapi.com/api/character/99999"
#  Then response status should be 404
#  And response should contain error message