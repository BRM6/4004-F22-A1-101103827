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

  @Row134
  Scenario: multiplayer cases two
    When Player one gets captain as FC for multiplayer
    And Player one roll dice and get seven sword one skull get 4000 score
    And Player two gets coin as FC for multiplayer
    And Player two roll dice and get three skull five coin get 0 score
    And Player three gets captain as FC for multiplayer
    And Player three roll dice and six skull two parrots press 0 get 0 score make -1200 deduction to other players
    And Player one score 2800 after deduction
    And Player two score 0 after deduction
    And Player one gets coin as FC for multiplayer
    And Player one roll dice and get four monkey four parrots get 1000 score
    And Player one current score 3800
    And Player two gets captain as FC for multiplayer
    And Player two roll dice and get three skull five monkey get 0 score
    And Player three gets  one skull as FC for multiplayer
    And Player three roll dice and two skull six monkey get 0 score
    Then Winner is "p1"
    And Players finished the round

  @Row142
  Scenario: multiplayer cases three
    When Player one gets captain as FC for multiplayer
    And Player one roll dice and get three skull five monkey get 0 score
    And Player two gets captain as FC for multiplayer
    And Player two roll dice and get seven sword one skull get 4000 score
    And Player three gets two skull as FC for multiplayer
    And Player three roll dice and one skull seven sword get 0 score
    And Player one gets captain as FC for multiplayer
    And Player one roll dice and get eight sword get 9000 score
    And Player one current score 9000
    Then Winner is "p1"
    And Players finished the round