Feature: Player has a special fortune card and they will get a score value after all the movements
  Player will get a value of score according to their special fortune card an eventually at the end of the scenario

  Background:
    Given Game has started for miscellaneous FC and Full Chest
    And Player with name "DI" is playing the game for miscellaneous FC and Full Chest

#  @Row77
#  Scenario: roll 2 diamonds, 1 (sword/monkey/coin), 3 parrots, reroll 3 parrots, get 1 skull, 2 monkeys, reroll skull, get monkey (SC 500)
#    When Player gets sorceress as FC for player get score
#    And Player roll dice and get two diamond one sword one monkey one coin three parrot
#    And Player reroll three parrot and get one skull two monkey
#    And Player press 1 and reroll one skull and get monkey
#    Then Player score 500 with sorceress card
#    And Player card is not sorceress
#    And Player finished the round for player get score