Feature: Player die after got 3 skulls
  Player will die eventually with 3 skulls at the end of the scenario

  Background:
    Given Game has started
    And Player with name "DI" is playing the game
    And Player gets coin as FC

  @Row45
  Scenario: die with 3 skulls 5 swords on first roll: player gets a score of 0
    When Player roll dice and get three skulls and five swords
    Then Player die
    And Player finished the round
    And Player scored zero

  @Row46
  Scenario: roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 2 skulls 1 sword  die
    When Player roll dice and get one skull four parrots three sword
    And Player reroll three swords and get two skull one sword
    Then Player die
    Then Player finished the round
    And Player scored zero

  @Row47
  Scenario: roll 2 skulls, 4 parrots, 2 swords, reroll swords, get 1 skull 1 sword  die
    When Player roll dice and get two skull four parrots two sword
    And Player reroll two swords and get one skull one sword
    Then Player die
    Then Player finished the round
    And Player scored zero

  @Row48
  Scenario: roll 1 skull, 4 parrots, 3 swords, reroll swords, get 1 skull 2 monkeys and reroll 2 monkeys, get 1 skull 1 monkey and die
    When Player roll dice and get one skull four parrots three sword
    And Player reroll three swords and get one skull two monkey
    And Player reroll two monkey and get one skull one monkey
    Then Player die
    Then Player finished the round
    And Player scored zero