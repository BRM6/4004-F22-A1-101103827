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

  @Row55
  Scenario: roll 3 diamonds, 2 skulls, 1 monkey, 1 sword, 1 parrot, score (diamonds = 100 + 300 points)   (SC 500)
    When Player gets coin as FC for player get score
    And Player roll dice and get three diamond two skull one monkey one sword one parrot
    Then Player score 500
    And Player finished the round for player get score

  @Row56
  Scenario: roll 4 coins, 2 skulls, 2 swords and score (coins: 200 + 400 points) with FC is a diamond (SC 700)
    When Player gets diamond as FC for player get score
    And Player roll dice and get four coin two skull two sword
    Then Player score 700
    And Player finished the round for player get score

  @Row57
  Scenario: roll 3 swords, 4 parrots, 1 skull and score (SC 100+200+100= 400))
    When Player gets coin as FC for player get score
    And Player roll dice and get three sword four parrots one skull
    Then Player score 400
    And Player finished the round for player get score
    