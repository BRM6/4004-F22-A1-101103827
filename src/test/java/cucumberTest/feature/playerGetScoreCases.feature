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

  @Row58
  Scenario: roll 1 skull, 2 coins/parrots & 3 swords, reroll parrots, get 1 coin and 1 sword, score (SC = 200+400+200 = 800)
    When Player gets coin as FC for player get score
    And Player roll dice and get one skull two coin two parrot three sword
    And Player reroll two parrot and get one coin one sword
    Then Player score 800
    And Player finished the round for player get score

  @Row59
  Scenario: same as previous row but with captain fortune card  (SC = (100 + 300 + 200)*2 = 1200
    When Player gets captain as FC for player get score
    And Player roll dice and get one skull two coin two parrot three sword
    And Player reroll two parrot and get one coin one sword
    Then Player score 1200 with captain card
    And Player finished the round for player get score

  @Row60
  Scenario: roll 1 skull, 2 (monkeys/parrots) 3 swords, reroll 2 monkeys, get 1 skull 1 sword, then reroll parrots get 1 sword 1 monkey (SC 600)
    When Player gets coin as FC for player get score
    And Player roll dice and get one skull two monkey two parrot three sword
    And Player reroll two monkey and get one skull one sword
    And Player reroll two parrots and get one sword and one monkey second version
    Then Player score 600
    And Player finished the round for player get score

  @Row62
  Scenario: score set of 6 monkeys and 2 skulls on first roll (SC 1100)
    When Player gets coin as FC for player get score
    And Player roll dice and get six monkey two skull
    Then Player score 1100
    And Player finished the round for player get score

  @Row63
  Scenario: score set of 7 parrots and 1 skull on first roll (SC 2100)
    When Player gets coin as FC for player get score
    And Player roll dice and get seven parrot one skull
    Then Player score 2100
    And Player finished the round for player get score

  @Row64
  Scenario: score set of 8 coins on first roll (SC 5400)  seq of 8 + 9 coins(FC is coin) +  full chest  (no extra points for 9 coins)
    When Player gets coin as FC for player get score
    And Player roll dice and get eight coin
    Then Player score 5400
    And Player finished the round for player get score

  @Row65
  Scenario: score set of 8 coins on first roll and FC is diamond (SC 5400)
    When Player gets diamond as FC for player get score
    And Player roll dice and get eight coin
    Then Player score 5400
    And Player finished the round for player get score

  @Row66
  Scenario: score set of 8 swords on first roll and FC is captain (SC 4500x2 = 9000) since full chest
    When Player gets captain as FC for player get score
    And Player roll dice and get eight sword
    Then Player score 9000 with captain card
    And Player finished the round for player get score

  @Row67
  Scenario: roll 6 monkeys and 2 swords, reroll swords, get 2 monkeys, score (SC 4600 because of FC is coin and full chest)
    When Player gets coin as FC for player get score
    And Player roll dice and get six monkey two sword
    And Player reroll two sword and get two monkey
    Then Player score 4600
    And Player finished the round for player get score

  @Row68
  Scenario: roll 2 (monkeys/skulls/swords/parrots), reroll parrots, get 2 diamonds, score with FC is diamond (SC 400)
    When Player gets diamond as FC for player get score
    And Player roll dice and get two monkey two skull two sword two parrot
    And Player reroll two parrot and get two diamond
    Then Player score 400
    And Player finished the round for player get score