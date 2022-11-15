Feature: Player has a special fortune card and they will get a score value after all the movements
  Player will get a value of score according to their special fortune card an eventually at the end of the scenario

  Background:
    Given Game has started for miscellaneous FC and Full Chest
    And Player with name "DI" is playing the game for miscellaneous FC and Full Chest

#    sorceress
#  @Row77
#  Scenario: roll 2 diamonds, 1 (sword/monkey/coin), 3 parrots, reroll 3 parrots, get 1 skull, 2 monkeys, reroll skull, get monkey (SC 500)
#    When Player gets sorceress as FC for player get score
#    And Player roll dice and get two diamond one sword one monkey one coin three parrot
#    And Player reroll three parrot and get one skull two monkey
#    And Player press 1 and reroll one skull and get monkey
#    Then Player score 500 with sorceress card
#    And Player card is not sorceress
#    And Player finished the round for miscellaneous

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
#  @Row90
#  Scenario: Treasure chest test cases 1 with score 1100
#    When Player gets treasure chest as FC for player get score
#    And Player roll dice and get three parrots two sword two diamond one coin
#    And Player put two diamonds one coin in chest
#    And Player reroll two sword and get two parrots
#    And Player put five parrots in chest take out two diamond and coin
#    And Player roll dice and get one skull one coin one parrot
#    Then Player score 1100 with treasure chest card
#    And Player finished the round for miscellaneous