package cucumberTest.testcode;

import comp4004.PirateGame;
import comp4004.Player;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class miscellaneousFCAndFullChestBonusCases {
    private ByteArrayOutputStream testOut;
    private final PrintStream systemOut = System.out;
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private PirateGame game;
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

    //background
    @Given("Game has started for miscellaneous FC and Full Chest")
    public void gameHasStartedForMiscellaneousFCAndFullChest() {
        game = new PirateGame();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @And("Player with name {string} is playing the game for miscellaneous FC and Full Chest")
    public void playerWithNameIsPlayingTheGameForMiscellaneousFCAndFullChest(String arg0) {
        player = new Player(arg0);
        game.setNewPlayer(player);
    }

    //row 77
    @When("Player gets sorceress as FC for player get score")
    public void playerGetsSorceressAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("sorceress");
    }

    @And("Player roll dice and get two diamond one sword one monkey one coin three parrot")
    public void playerRollDiceAndGetTwoDiamondOneSwordOneMonkeyOneCoinThreeParrot() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                current[i] = "diamond";
            }
            if (i>=2 && i<3){
                current[i] = "sword";
            }
            if (i>=3 && i<4){
                current[i] = "monkey";
            }
            if (i>=4 && i<5){
                current[i] = "coin";
            }
            if (i>=5){
                current[i] = "parrots";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll three parrot and get one skull two monkey")
    public void playerRerollThreeParrotAndGetOneSkullTwoMonkey() {
        String[] hold = new String[] {"0", "1", "2", "3", "4"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[5] = "skull";
        newCurrent[6] = "monkey";
        newCurrent[7] = "monkey";
        game.player.setCurrentRoll(newCurrent);
    }

    @And("Player press {int} and reroll one skull and get monkey")
    public void playerPressAndRerollOneSkullAndGetMonkey(int arg0) {
        //simulate user input
        scannerInput(Integer.toString(arg0));
        game = new PirateGame();
        game.setNewPlayer(player);
        String[] newCurrent = game.useSorceress(game.player.getCurrentRoll(), game.player);
        newCurrent[5] = "monkey";
        game.player.setCurrentRoll(newCurrent);
    }

    @Then("Player score {int} with sorceress card")
    public void playerScoreWithSorceressCard(int arg0) {
        int final_score = game.scoreForKindsAndChest(game.player.getCurrentRoll(), game.player) + game.scoreForDC(game.player.getCurrentRoll(), game.player);
        game.player.setScore(final_score);
        assertEquals(arg0, game.player.getScore());
    }

    @And("Player card is not sorceress")
    public void playerCardIsNotSorceress() {
        assertNotEquals("sorceress", game.player.getFortuneCard());
    }

    @And("Player finished the round for miscellaneous")
    public void playerFinishedTheRoundForMiscellaneous() {
        assertFalse(game.isGoing);
    }

    //row 78
    @And("Player roll dice and get three skull three parrots two sword")
    public void playerRollDiceAndGetThreeSkullThreeParrotsTwoSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<3){
                current[i] = "skull";
            }
            if (i>=3 && i<6){
                current[i] = "parrots";
            }
            if (i>=6 && i<8){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player press {int} and reroll one skull and get parrot")
    public void playerPressAndRerollOneSkullAndGetParrot(int arg0) {
        //simulate user input
        scannerInput(Integer.toString(arg0));
        game = new PirateGame();
        game.setNewPlayer(player);
        String[] newCurrent = game.useSorceress(game.player.getCurrentRoll(), game.player);
        newCurrent[0] = "parrots";
        game.player.setCurrentRoll(newCurrent);
    }

    @And("Player reroll two sword and get two parrots")
    public void playerRerollTwoSwordAndGetTwoParrots() {
        String[] hold = new String[] {"0", "3", "4", "5"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[6] = "parrots";
        newCurrent[7] = "parrots";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 82
    @When("Player gets monkey business as FC for player get score")
    public void playerGetsMonkeyBusinessAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("monkey business");

    }

    @And("Player roll dice and get three monkey three parrots one skull one coin")
    public void playerRollDiceAndGetThreeMonkeyThreeParrotsOneSkullOneCoin() {
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "monkey";
            }
            if (i>=3 && i<6){
                die[i] = "parrots";
            }
            if (i>=6 && i<7){
                die[i] = "skull";
            }
            if (i>=7 && i<8){
                die[i] = "coin";
            }
        }
        game.player.setCurrentRoll(die);
    }

    @Then("Player score {int} with monkey business")
    public void playerScoreWithMonkeyBusiness(int arg0) {
        game.calculateScoreForARoundWithCapMonkey(game.player, game.player.getCurrentRoll());
        assertEquals(arg0, game.player.getScore());
    }

    //row 83
    @And("Player roll dice and get two monkey two sword two parrots two coin")
    public void playerRollDiceAndGetTwoMonkeyTwoSwordTwoParrotsTwoCoin() {
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<2){
                die[i] = "monkey";
            }
            if (i>=2 && i<4){
                die[i] = "sword";
            }
            if (i>=4 && i<6){
                die[i] = "parrots";
            }
            if (i>=6 && i<8){
                die[i] = "coin";
            }
        }
        game.player.setCurrentRoll(die);
    }

    @And("Player reroll two sword and get one monkey one parrots")
    public void playerRerollTwoSwordAndGetOneMonkeyOneParrots() {
        String[] hold = {"0", "1", "5", "6", "4", "7"}; //select dice to hold
        String[] newDie = game.RerollWithHold(game.player.getCurrentRoll(), hold); //reroll
        newDie[2] = "monkey";
        newDie[3] = "parrots";
        game.player.setCurrentRoll(newDie);
    }

    //row 84
    @And("Player roll dice and get three skull three monkey two parrots")
    public void playerRollDiceAndGetThreeSkullThreeMonkeyTwoParrots() {
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "skull";
            }
            if (i>=3 && i<6){
                die[i] = "monkey";
            }
            if (i>=6 && i<8){
                die[i] = "parrots";
            }
        }
        game.player.setCurrentRoll(die);
    }

}
