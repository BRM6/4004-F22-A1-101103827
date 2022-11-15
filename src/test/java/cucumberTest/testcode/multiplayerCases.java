package cucumberTest.testcode;

import comp4004.PirateGame;
import comp4004.Player;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class multiplayerCases {
    private PirateGame game1;
    private PirateGame game2;
    private PirateGame game3;
    private Player player;

    @Given("Game has started for multiplayer")
    public void gameHasStartedForMultiplayer() {
        game1 = new PirateGame();
        game2 = new PirateGame();
        game3 = new PirateGame();
    }

    @And("Player one with name {string} is playing the game for multiplayer")
    public void playerOneWithNameIsPlayingTheGameForMultiplayer(String arg0) {
        player = new Player(arg0);
        game1.setNewPlayer(player);
    }

    @And("Player two with name {string} is playing the game for multiplayer")
    public void playerTwoWithNameIsPlayingTheGameForMultiplayer(String arg0) {
        player = new Player(arg0);
        game2.setNewPlayer(player);
    }

    @And("Player three with name {string} is playing the game for multiplayer")
    public void playerThreeWithNameIsPlayingTheGameForMultiplayer(String arg0) {
        player = new Player(arg0);
        game3.setNewPlayer(player);
    }

    //row 130
    @When("Player one gets captain as FC for multiplayer")
    public void playerOneGetsCaptainAsFCForMultiplayer() {
        game1.drawForturnCard(player);
        game1.player.setFortuneCard("captain");
    }

    @And("Player one roll dice and get seven sword one skull")
    public void playerOneRollDiceAndGetSevenSwordOneSkull() {
        String[] current1 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current1[i] = game1.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 7) {
                current1[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                current1[i] = "skull";
            }
        }
        game1.player.setCurrentRoll(current1);
    }

    @And("Player two gets one skull as FC for multiplayer")
    public void playerTwoGetsOneSkullAsFCForMultiplayer() {
        game2.drawForturnCard(player);
        game2.player.setFortuneCard("1 skull");
    }

    @And("Player two roll dice and get seven sword one skull")
    public void playerTwoRollDiceAndGetSevenSwordOneSkull() {
        String[] current2 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current2[i] = game2.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 7) {
                current2[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                current2[i] = "skull";
            }
        }
        game2.player.setCurrentRoll(current2);
    }

    @And("Player three gets coin as FC for multiplayer")
    public void playerThreeGetsCoinAsFCForMultiplayer() {
        game3.drawForturnCard(player);
        game3.player.setFortuneCard("coin");
    }

    @And("Player three roll dice and get three skull five monkey")
    public void playerThreeRollDiceAndGetThreeSkullFiveMonkey() {
        String[] current3 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current3[i] = game3.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 3) {
                current3[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                current3[i] = "monkey";
            }
        }
        game3.player.setCurrentRoll(current3);
    }

    @Then("Player one score is {int}")
    public void playerOneScoreIs(int arg0) {
        game1.calculateScoreForARoundWithCapMonkey(game1.player, game1.player.getCurrentRoll());//player1_final_score = p1.getScore()
        Assertions.assertEquals(arg0, game1.player.getScore());
    }

    @And("Player two score is {int}")
    public void playerTwoScoreIs(int arg0) {
        int player2_final_score = game2.scoreForKindsAndChest(game2.player.getCurrentRoll(), game2.player) + game2.scoreForDC(game2.player.getCurrentRoll(), game2.player);
        game2.player.setScore(player2_final_score);
        Assertions.assertEquals(arg0, game2.player.getScore());
    }

    @And("Player three score is {int}")
    public void playerThreeScoreIs(int arg0) {
        int player3_final_score = game3.scoreForKindsAndChest(game3.player.getCurrentRoll(), game3.player) + game3.scoreForDC(game3.player.getCurrentRoll(), game3.player);
        game3.player.setScore(player3_final_score);
        Assertions.assertEquals(arg0, game3.player.getScore());
    }

    @And("Winner is {string}")
    public void winnerIs(String arg0) {
        Player[] player_list = new Player[3];
        player_list[0] = game1.player;
        player_list[1] = game2.player;
        player_list[2] = game3.player;

        Player winner = game1.getWinner(player_list);
        String winner_name = winner.getName();
        assertEquals(arg0, winner_name);
    }

    @And("Players finished the round")
    public void playersFinishedTheRound() {
        assertFalse(game1.isGoing);
        assertFalse(game2.isGoing);
        assertFalse(game3.isGoing);
    }
}
