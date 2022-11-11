Feature: Player get a score number after all the movements
  Player will get a number of score eventually at the end of the scenario

  Background:
    Given Game has started for player get score
    And Player with name "DI" is playing the game for player get score

  @Row50
  Scenario: roll 1 skull, 2 parrots, 3 swords, 2 coins, reroll parrots get 2 coins and reroll 3 swords, get 3 coins (SC 4000 for seq of 8 (with FC coin) + 8x100=800 = 4800)
    When Player gets coin as FC for player get score
    And Player roll dice and get one skull two parrots three sword two coin
    And Player reroll two parrots and get two coin
    And Player reroll three sword and get three coin
    Then Player score 4800
    And Player finished the round for player get score