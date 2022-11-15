Feature: Player has a special fortune card and they will get a score value after all the movements
  Player will get a value of score according to their special fortune card an eventually at the end of the scenario

  Background:
    Given Game has started for miscellaneous FC and Full Chest
    And Player with name "DI" is playing the game for miscellaneous FC and Full Chest

#    sorceress
  @Row77
  Scenario: roll 2 diamonds, 1 (sword/monkey/coin), 3 parrots, reroll 3 parrots, get 1 skull, 2 monkeys, reroll skull, get monkey (SC 500)
    When Player gets sorceress as FC for player get score
    And Player roll dice and get two diamond one sword one monkey one coin three parrot
    And Player reroll three parrot and get one skull two monkey
    And Player press 1 and reroll one skull and get monkey
    Then Player score 500 with sorceress card
    And Player card is not sorceress
    And Player finished the round for miscellaneous

  @Row78
  Scenario: roll 3 skulls, 3 parrots, 2 swords, reroll skull, get parrot, reroll 2 swords, get parrots, score (SC 1000)
    When Player gets sorceress as FC for player get score
    And Player roll dice and get three skull three parrots two sword
    And Player press 1 and reroll one skull and get parrot
    And Player reroll two sword and get two parrots
    Then Player score 1000 with sorceress card
    And Player card is not sorceress
    And Player finished the round for miscellaneous

  @Row79
  Scenario: roll 1 skull, 4 parrots, 3 monkeys, reroll 3 monkeys, get 1 skull, 2 parrots, reroll skull, get parrot, score (SC 2000)
    When Player gets sorceress as FC for player get score
    And Player roll dice and get one skull four parrots three monkey
    And Player reroll three monkey and get one skull two parrots
    And Player press 1 and reroll one skull and get parrot
    Then Player score 2000 with sorceress card
    And Player card is not sorceress
    And Player finished the round for miscellaneous

#  monkey business
  @Row82
  Scenario: roll 3 monkeys 3 parrots  1 skull 1 coin  SC = 1100  (i.e., sequence of of 6 + coin)
    When Player gets monkey business as FC for player get score
    And Player roll dice and get three monkey three parrots one skull one coin
    Then Player score 1100 with monkey business
    And Player finished the round for miscellaneous

  @Row83
  Scenario: roll 2 (monkeys/swords/parrots/coins), reroll 2 swords, get 1 monkey, 1 parrot, score 1700
    When Player gets monkey business as FC for player get score
    And Player roll dice and get two monkey two sword two parrots two coin
    And Player reroll two sword and get one monkey one parrots
    Then Player score 1700 with monkey business
    And Player finished the round for miscellaneous

  @Row84
  Scenario: roll 3 skulls, 3 monkeys, 2 parrots => die scoring 0
    When Player gets monkey business as FC for player get score
    And Player roll dice and get three skull three monkey two parrots
    Then Player score 0 with monkey business
    And Player finished the round for miscellaneous

#    Treasure Chest
  @Row87
  Scenario: Treasure chest test cases 1 with score 1100
    When Player gets treasure chest as FC for player get score
    And Player roll dice and get three parrots two sword two diamond one coin
    And Player put two diamonds one coin in chest
    And Player reroll with chest hold two sword and get two parrots
    And Player put five parrots in chest take out two diamond and coin
    And Player roll dice and get one skull one coin one parrot
    Then Player score 1100 with treasure chest card
    And Player finished the round for miscellaneous

  @Row92
  Scenario: Treasure chest test cases 2 player die
    When Player gets treasure chest as FC for player get score
    And Player roll dice and get two skull three parrots three coin
    And Player put three coin in chest
    And Player reroll with chest hold three parrots and get two diamond one coin
    And Player put one coin in chest
    And Player reroll with chest hold two diamond and get one skull one coin
    Then Player score 600 with treasure chest card just the chest
    And Player finished the round for miscellaneous

# full chest
  @Row97
  Scenario: 3 monkeys, 3 swords, 1 diamond, 1 parrot FC: coin   => SC 400  (ie no bonus)
    When Player gets coin as FC for player get score with full chest
    And Player roll dice and get three monkey three sword one diamond one parrot
    Then Player score 400 with full chest
    And Player finished the round for miscellaneous

  @Row98
  Scenario: 3 monkeys, 3 swords, 2 coins FC: captain   => SC (100+100+200+500)*2 =  1800
    When Player gets captain as FC for player get score with full chest
    And Player roll dice and get three monkey three sword two coin
    Then Player score 1800 with full chest with captain
    And Player finished the round for miscellaneous

  @Row99
  Scenario: 3 monkeys, 4 swords, 1 diamond, FC: coin   => SC 1000  (ie 100++200+100+100+bonus)
    When Player gets coin as FC for player get score with full chest
    And Player roll dice and get three monkey four sword two coin
    Then Player score 1000 with full chest
    And Player finished the round for miscellaneous

  @Row100
  Scenario: full chest scenario one
    When Player gets two sword sea battle as FC for player get score with full chest
    And Player roll dice and get four monkey one sword two parrots one coin
    And Player reroll two parrots and get one coin and one sword
    Then Player score 1200 with sea battle with full chest
    And Player finished the round for miscellaneous

  @Row103
  Scenario: FC: monkey business and roll 2 monkeys, 1 parrot, 2 coins, 3 diamonds   SC 1200
    When Player gets monkey business as FC for player get score
    And Player roll dice and get two monkey one parrot two coin three diamond
    Then Player score 1200 with full chest with monkey
    And Player finished the round for miscellaneous