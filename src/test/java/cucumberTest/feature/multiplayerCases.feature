Feature: the following test cases are multiplayer test cases
  Following test cases will involve three players and find the winner in these three players

  Background:
    Given Game has started for multiplayer
    And Player one with name "p1" is playing the game for multiplayer
    And Player two with name "p2" is playing the game for multiplayer
    And Player three with name "p3" is playing the game for multiplayer

  @Row130
  Scenario: multiplayer cases one
    When Player one gets captain as FC for multiplayer
    And Player one roll dice and get seven sword one skull
    And Player two gets one skull as FC for multiplayer
    And Player two roll dice and get seven sword one skull
    And Player three gets coin as FC for multiplayer
    And Player three roll dice and get three skull five monkey
    Then Player one score is 4000
    And Player two score is 2000
    And Player three score is 0
    And Winner is "p1"
    And Players finished the round