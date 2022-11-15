package cucumberTest.testcode;

import comp4004.PirateGame;
import comp4004.Player;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class multiplayerCases {
    private ByteArrayOutputStream testOut;
    private final PrintStream systemOut = System.out;
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private PirateGame game1;
    private PirateGame game2;
    private PirateGame game3;
    private Player player;
    private void scannerInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

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

    //row 134
    @And("Player one roll dice and get seven sword one skull get {int} score")
    public void playerOneRollDiceAndGetSevenSwordOneSkullGetScore(int arg0) {
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
        game1.calculateScoreForARoundWithCapMonkey(game1.player, current1);  //player1_final_score = p1.getScore()
        Assertions.assertEquals(arg0, game1.player.getScore());
        game1.player.setCurrentRoll(current1);
    }

    @And("Player two gets coin as FC for multiplayer")
    public void playerTwoGetsCoinAsFCForMultiplayer() {
        game2.drawForturnCard(player);
        game2.player.setFortuneCard("coin");
    }

    @And("Player two roll dice and get three skull five coin get {int} score")
    public void playerTwoRollDiceAndGetThreeSkullFiveCoinGetScore(int arg0) {
        String[] current2 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current2[i] = game2.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 3) {
                current2[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                current2[i] = "monkey";
            }
        }
        int player2_final_score = game2.scoreForKindsAndChest(current2, game2.player) + game2.scoreForDC(current2, game2.player);
        game2.player.setScore(player2_final_score);
        Assertions.assertEquals(arg0, game2.player.getScore());
        game2.player.setCurrentRoll(current2);
    }

    @And("Player three gets captain as FC for multiplayer")
    public void playerThreeGetsCaptainAsFCForMultiplayer() {
        game3.drawForturnCard(player);
        game3.player.setFortuneCard("captain");
    }

    @And("Player three roll dice and six skull two parrots press {int} get {int} score make {int} deduction to other players")
    public void playerThreeRollDiceAndSixSkullTwoParrotsPressGetScoreMakeDeductionToOtherPlayers(int arg0, int arg1, int arg2) {
        scannerInput(Integer.toString(arg0));
        game3 = new PirateGame();
        game3.setNewPlayer(player);
        String[] current3 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current3[i] = game3.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 6) {
                current3[i] = "skull";
            }
            if (i >= 6 && i < 8) {
                current3[i] = "parrots";
            }
        }
        assertEquals(arg1, game3.player.getScore());
        int player3_final_score = game3.rerollSkullLandAndCountNOSkull(current3, game3.player);
        int player3_final_deduct = player3_final_score * 100;
        assertEquals(arg2, player3_final_deduct);
        //deduction for others
        game1.player.setScore(game1.player.getScore() + arg2);
        game2.player.setScore(game2.player.getScore() + arg2);
        game3.player.setCurrentRoll(current3);
    }

    @And("Player one score {int} after deduction")
    public void playerOneScoreAfterDeduction(int arg0) {
        assertEquals(arg0, game1.player.getScore());
    }

    @And("Player two score {int} after deduction")
    public void playerTwoScoreAfterDeduction(int arg0) {
        assertEquals(arg0, game2.player.getScore());
    }

    @And("Player one gets coin as FC for multiplayer")
    public void playerOneGetsCoinAsFCForMultiplayer() {
        game1.drawForturnCard(player);
        game1.player.setFortuneCard("coin");
    }

    @And("Player one roll dice and get four monkey four parrots get {int} score")
    public void playerOneRollDiceAndGetFourMonkeyFourParrotsGetScore(int arg0) {
        String[] current1 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current1[i] = game1.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 4) {
                current1[i] = "monkey";
            }
            if (i >= 4 && i < 8) {
                current1[i] = "parrots";
            }
        }
        int score_before = game1.scoreForKindsAndChest(current1, game1.player) + game1.scoreForDC(current1, game1.player);
        Assertions.assertEquals(arg0, score_before);
        game1.player.setScore(game1.player.getScore() + (game1.scoreForKindsAndChest(current1, game1.player) + game1.scoreForDC(current1, game1.player)) );
        game1.player.setCurrentRoll(current1);
    }

    @And("Player one current score {int}")
    public void playerOneCurrentScore(int arg0) {
        assertEquals(arg0, game1.player.getScore());
    }

    @And("Player two gets captain as FC for multiplayer")
    public void playerTwoGetsCaptainAsFCForMultiplayer() {
        game2.drawForturnCard(player);
        game2.player.setFortuneCard("captain");
    }

    @And("Player two roll dice and get three skull five monkey get {int} score")
    public void playerTwoRollDiceAndGetThreeSkullFiveMonkeyGetScore(int arg0) {
        String[] current2 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current2[i] = game2.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 3) {
                current2[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                current2[i] = "monkey";
            }
        }
        int player2_final_score = game2.scoreForKindsAndChest(current2, game2.player) + game2.scoreForDC(current2, game2.player);
        assertEquals(arg0, player2_final_score);
        game2.player.setScore(player2_final_score);
        game2.player.setCurrentRoll(current2);
    }

    @And("Player three gets  one skull as FC for multiplayer")
    public void playerThreeGetsOneSkullAsFCForMultiplayer() {
        game3.drawForturnCard(player);
        game3.player.setFortuneCard("1 skull");
    }

    @And("Player three roll dice and two skull six monkey get {int} score")
    public void playerThreeRollDiceAndTwoSkullSixMonkeyGetScore(int arg0) {
        String[] current3 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current3[i] = game3.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 2) {
                current3[i] = "skull";
            }
            if (i >= 2 && i < 8) {
                current3[i] = "monkey";
            }
        }
        int player3_final_score = game3.scoreForKindsAndChest(current3, game3.player) + game3.scoreForDC(current3, game3.player);
        assertEquals(arg0, player3_final_score);
    }

    //row 142
    @And("Player one roll dice and get three skull five monkey get {int} score")
    public void playerOneRollDiceAndGetThreeSkullFiveMonkeyGetScore(int arg0) {
        String[] current1 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current1[i] = game1.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 3) {
                current1[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                current1[i] = "monkey";
            }
        }
        game1.calculateScoreForARoundWithCapMonkey(game1.player, current1);  //player1_final_score = p1.getScore()
        Assertions.assertEquals(arg0, game1.player.getScore());
        game1.player.setCurrentRoll(current1);
    }

    @And("Player two roll dice and get seven sword one skull get {int} score")
    public void playerTwoRollDiceAndGetSevenSwordOneSkullGetScore(int arg0) {
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
        game2.calculateScoreForARoundWithCapMonkey(game2.player, current2);
        Assertions.assertEquals(arg0, game2.player.getScore());
        game2.player.setCurrentRoll(current2);
    }

    @And("Player three gets two skull as FC for multiplayer")
    public void playerThreeGetsTwoSkullAsFCForMultiplayer() {
        game3.drawForturnCard(player);
        game3.player.setFortuneCard("2 skull");
    }

    @And("Player three roll dice and one skull seven sword get {int} score")
    public void playerThreeRollDiceAndOneSkullSevenSwordGetScore(int arg0) {
        String[] current3 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current3[i] = game3.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 1) {
                current3[i] = "skull";
            }
            if (i >= 1 && i < 8) {
                current3[i] = "swords";
            }
        }
        int player3_final_score = game3.scoreForKindsAndChest(current3, game3.player) + game3.scoreForDC(current3, game3.player);
        assertEquals(arg0, player3_final_score);
    }

    @And("Player one roll dice and get eight sword get {int} score")
    public void playerOneRollDiceAndGetEightSwordGetScore(int arg0) {
        String[] current1 = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current1[i] = game1.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            current1[i] = "sword";
        }
        game1.calculateScoreForARoundWithCapMonkey(game1.player, current1);  //player1_final_score = p1.getScore()
        Assertions.assertEquals(arg0, game1.player.getScore());
        game1.player.setCurrentRoll(current1);
    }
}
