Feature: Player die eventually and scoring
  Player will die after get 3 skulls

  @Row45
  Scenario: Player die with 3 skulls and 5 swords
    Given Game has started
    When Player with name "DI" is playing the game
    And Player gets coin as FC
    And Player roll dice and get three skulls and five swords
    Then Player die and death reminder showed
    And Player finished the round
    And Player scored zero
