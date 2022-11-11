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

  @Row52
  Scenario: score first roll with 2 (monkeys/parrot/diamonds/coins) and FC is captain (SC 800)
    When Player gets captain as FC for player get score
    And Player roll dice and get two monkey two parrot two diamond two coin
    Then Player score 800 with captain card
    And Player finished the round for player get score

  @Row53
  Scenario: roll 2 (monkeys/skulls/swords/parrots), reroll parrots and get 1 sword & 1 monkey (SC 300 since FC is coin)
    When Player gets coin as FC for player get score
    And Player roll dice and get two monkey two skull two sword two parrots
    And Player reroll two parrots and get one sword and one monkey
    Then Player score 300
    And Player finished the round for player get score

  @Row54
  Scenario: roll 3 (monkey, swords) + 2 skulls and score   (SC 300)
    When Player gets coin as FC for player get score
    And Player roll dice and get three monkey three sword two skull
    Then Player score 300
    And Player finished the round for player get score