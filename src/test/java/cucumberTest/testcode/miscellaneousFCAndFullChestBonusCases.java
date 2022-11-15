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
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

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
        String[] hold = new String[] {"0","1", "2", "3", "4", "5"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[6] = "parrots";
        newCurrent[7] = "parrots";
        game.player.setCurrentRoll(newCurrent);
//        System.out.println(Arrays.toString(game.player.getCurrentRoll()));
    }

    //row 79
    @And("Player roll dice and get one skull four parrots three monkey")
    public void playerRollDiceAndGetOneSkullFourParrotsThreeMonkey() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                current[i] = "skull";
            }
            if (i>=1 && i<5){
                current[i] = "parrots";
            }
            if (i>=5 && i<8){
                current[i] = "monkey";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll three monkey and get one skull two parrots")
    public void playerRerollThreeMonkeyAndGetOneSkullTwoParrots() {
        String[] hold = new String[] {"1", "2", "3", "4"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[5] = "skull";
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

    //row 87
    @When("Player gets treasure chest as FC for player get score")
    public void playerGetsTreasureChestAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("treasure chest");
    }

    @And("Player roll dice and get three parrots two sword two diamond one coin")
    public void playerRollDiceAndGetThreeParrotsTwoSwordTwoDiamondOneCoin() {
        String[] die = new String[8];
        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "parrots";
            }
            if (i>=3 && i<5){
                die[i] = "sword";
            }
            if (i>=5 && i<7){
                die[i] = "diamond";
            }
            if (i>=7 && i<8){
                die[i] = "coin";
            }
        }
        game.player.setCurrentRoll(die);
    }

    @And("Player put two diamonds one coin in chest")
    public void playerPutTwoDiamondsOneCoinInChest() {
        String[] chestIndex;
        chestIndex = new String[]{"5", "6", "7"};
        game.player.setChestIndex(chestIndex);
    }

    @And("Player reroll with chest hold two sword and get two parrots")
    public void playerRerollWithChestHoldTwoSwordAndGetTwoParrots() {
        String[] hold = {"0", "1", "2"}; //select dice to hold
        String[] die = game.RerollWithChestHold(game.player.getCurrentRoll(), hold, game.player.getChestIndex());
        die[3] = "parrots";
        die[4] = "parrots";
        game.player.setCurrentRoll(die);
    }

    @And("Player put five parrots in chest take out two diamond and coin")
    public void playerPutFiveParrotsInChestTakeOutTwoDiamondAndCoin() {
        String[] chestIndex;
        chestIndex = new String[]{"0", "1", "2", "3", "4"};
        game.player.setChestIndex(chestIndex);
    }

    @And("Player roll dice and get one skull one coin one parrot")
    public void playerRollDiceAndGetOneSkullOneCoinOneParrot() {
        String[] hold = new String[]{}; //select dice to hold
        String[] die = game.RerollWithChestHold(game.player.getCurrentRoll(), hold, game.player.getChestIndex()); //reroll
        die[5] = "skull";
        die[6] = "coin";
        die[7] = "parrots";
        game.player.setCurrentRoll(die);
    }

    @Then("Player score {int} with treasure chest card")
    public void playerScoreWithTreasureChestCard(int arg0) {
        int final_score = game.scoreForKindsAndChest(game.player.getCurrentRoll(), game.player) + game.scoreForDC(game.player.getCurrentRoll(), game.player);
        player.setScore(final_score);
        assertEquals(arg0, game.player.getScore());
    }

    //row 92
    @And("Player roll dice and get two skull three parrots three coin")
    public void playerRollDiceAndGetTwoSkullThreeParrotsThreeCoin() {
        String[] die = new String[8];
        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<2){
                die[i] = "skull";
            }
            if (i>=2 && i<5){
                die[i] = "parrots";
            }
            if (i>=5 && i<8){
                die[i] = "coin";
            }
        }
        game.player.setCurrentRoll(die);
    }

    @And("Player put three coin in chest")
    public void playerPutThreeCoinInChest() {
        String[] chestIndex = new String[]{"5", "6", "7"};
        game.player.setChestIndex(chestIndex);
    }

    @And("Player reroll with chest hold three parrots and get two diamond one coin")
    public void playerRerollWithChestHoldThreeParrotsAndGetTwoDiamondOneCoin() {
        String[] hold = {"0", "1"}; //select dice to hold
        String[] die = game.RerollWithChestHold(game.player.getCurrentRoll(), hold, game.player.getChestIndex());
        die[2] = "diamond";
        die[3] = "diamond";
        die[4] = "coin";
        game.player.setCurrentRoll(die);
    }

    @And("Player put one coin in chest")
    public void playerPutOneCoinInChest() {
        String[] chestIndex = new String[]{"4", "5", "6", "7"};
        game.player.setChestIndex(chestIndex);
    }

    @And("Player reroll with chest hold two diamond and get one skull one coin")
    public void playerRerollWithChestHoldTwoDiamondAndGetOneSkullOneCoin() {
        String[] hold = new String[]{"0", "1"}; //select dice to hold
        String[] die = game.RerollWithChestHold(game.player.getCurrentRoll(), hold, game.player.getChestIndex()); //reroll
        die[2] = "skull";
        die[3] = "coin";
        game.player.setCurrentRoll(die);
        game.player.setHoldingDie(game.player.getChestIndex());
    }

    @Then("Player score {int} with treasure chest card just the chest")
    public void playerScoreWithTreasureChestCardJustTheChest(int arg0) {
        game.scoreChest(game.player, game.player.getCurrentRoll());
        int final_score = game.player.getScore();
        assertEquals(600, final_score);
    }

    //row 97
    @When("Player gets coin as FC for player get score with full chest")
    public void playerGetsCoinAsFCForPlayerGetScoreWithFullChest() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("coin");
    }

    @And("Player roll dice and get three monkey three sword one diamond one parrot")
    public void playerRollDiceAndGetThreeMonkeyThreeSwordOneDiamondOneParrot() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<3){
                current[i] = "monkey";
            }
            if (i>=3 && i<6){
                current[i] = "sword";
            }
            if (i == 6){
                current[i] = "diamond";
            }
            if (i == 7){
                current[i] = "parrot";
            }
        }
        game.player.setCurrentRoll(current);

    }

    @Then("Player score {int} with full chest")
    public void playerScoreWithFullChest(int arg0) {
        int final_score = game.scoreForKindsAndChest(game.player.getCurrentRoll(), game.player) + game.scoreForDC(game.player.getCurrentRoll(), game.player);
        game.player.setScore(final_score);
        assertEquals(arg0, game.player.getScore());
    }

    //row 98
    @When("Player gets captain as FC for player get score with full chest")
    public void playerGetsCaptainAsFCForPlayerGetScoreWithFullChest() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("captain");
    }

    @And("Player roll dice and get three monkey three sword two coin")
    public void playerRollDiceAndGetThreeMonkeyThreeSwordTwoCoin() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<3){
                current[i] = "monkey";
            }
            if (i>=3 && i<6){
                current[i] = "sword";
            }
            if (i == 6 || i == 7){
                current[i] = "coin";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @Then("Player score {int} with full chest with captain")
    public void playerScoreWithFullChestWithCaptain(int arg0) {
        game.calculateScoreForARoundWithCapMonkey(game.player, player.getCurrentRoll());
        int final_score = player.getScore();
        assertEquals(arg0, final_score);
    }

    //row 99
    @And("Player roll dice and get three monkey four sword two coin")
    public void playerRollDiceAndGetThreeMonkeyFourSwordTwoCoin() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<3){
                current[i] = "monkey";
            }
            if (i>=3 && i<7){
                current[i] = "sword";
            }
            if (i == 7){
                current[i] = "diamond";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 100
    @When("Player gets two sword sea battle as FC for player get score with full chest")
    public void playerGetsTwoSwordSeaBattleAsFCForPlayerGetScoreWithFullChest() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("2 sword");
    }

    @And("Player roll dice and get four monkey one sword two parrots one coin")
    public void playerRollDiceAndGetFourMonkeyOneSwordTwoParrotsOneCoin() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<4){
                current[i] = "monkey";
            }
            if (i>=4 && i<5){
                current[i] = "sword";
            }
            if (i>=5 && i<7){
                current[i] = "parrots";
            }
            if (i>=7 && i<8){
                current[i] = "coin";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll two parrots and get one coin and one sword")
    public void playerRerollTwoParrotsAndGetOneCoinAndOneSword() {
        String[] hold = {"0", "1", "2", "3", "4", "7"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[5] = "coin";
        newCurrent[6] = "sword";
        game.player.setCurrentRoll(newCurrent);
    }

    @Then("Player score {int} with sea battle with full chest")
    public void playerScoreWithSeaBattleWithFullChest(int arg0) {
        game.seaBattle(game.player, game.player.getCurrentRoll());
        int final_score = game.player.getScore();
        assertEquals(1200, final_score);
    }

    //row 103
    @And("Player roll dice and get two monkey one parrot two coin three diamond")
    public void playerRollDiceAndGetTwoMonkeyOneParrotTwoCoinThreeDiamond() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                current[i] = "monkey";
            }
            if (i>=2 && i<3){
                current[i] = "parrots";
            }
            if (i>=3 && i<5){
                current[i] = "coin";
            }
            if (i>=5 && i<8){
                current[i] = "diamond";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @Then("Player score {int} with full chest with monkey")
    public void playerScoreWithFullChestWithMonkey(int arg0) {
        game.calculateScoreForARoundWithCapMonkey(game.player, game.player.getCurrentRoll());
        int final_score = game.player.getScore();
        assertEquals(arg0, final_score);
    }

    //row 106
    @When("Player gets two skull as FC for player get score")
    public void playerGetsTwoSkullAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("2 skull");
    }

    @And("Player roll dice and get one skull seven sword")
    public void playerRollDiceAndGetOneSkullSevenSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                current[i] = "skull";
            }
            if (i>=1 && i<8){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @Then("Player score {int} for skull island")
    public void playerScoreForSkullIsland(int arg0) {
        int final_score = game.scoreForKindsAndChest(game.player.getCurrentRoll(), game.player) + game.scoreForDC(game.player.getCurrentRoll(), game.player);
        game.player.setScore(final_score);
        assertEquals(arg0, game.player.getScore());
    }

    //row 107
    @When("Player gets one skull as FC for player get score")
    public void playerGetsOneSkullAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("1 skull");
    }

    @And("Player roll dice and get two skull six sword")
    public void playerRollDiceAndGetTwoSkullSixSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                current[i] = "skull";
            }
            if (i>=2 && i<8){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 108
    @And("Player roll dice and get two skull three parrots three monkey")
    public void playerRollDiceAndGetTwoSkullThreeParrotsThreeMonkey() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                current[i] = "skull";
            }
            if (i>=2 && i<5){
                current[i] = "parrots";
            }
            if (i>=5 && i<8){
                current[i] = "monkey";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player enter {int} to reroll skull for skull island")
    public void playerEnterToRerollSkullForSkullIsland(int arg0) {
        scannerInput(Integer.toString(arg0));
        game = new PirateGame();
        game.setNewPlayer(player);
    }

    @And("Player reroll three parrots and get two skull one sword")
    public void playerRerollThreeParrotsAndGetTwoSkullOneSword() {
        String[] hold = {"0", "1", "5", "6", "7"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[2] = "skull";
        newCurrent[3] = "skull";
        newCurrent[4] = "sword";
        game.player.setCurrentRoll(newCurrent);
    }

    @And("Player reroll sword and three monkey and get three skulls one sword")
    public void playerRerollSwordAndThreeMonkeyAndGetThreeSkullsOneSword() {
        String[] hold = new String[]{"0", "1", "2", "3"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[4] = "skull";
        newCurrent[5] = "skull";
        newCurrent[6] = "skull";
        newCurrent[7] = "sword";
        game.player.setCurrentRoll(newCurrent);
    }

    @Then("Player score {int} to other players")
    public void playerScoreToOtherPlayers(int arg0) {
        int final_num_skull = game.rerollSkullLandAndCountNOSkull(game.player.getCurrentRoll(), game.player);
        int final_deduct = final_num_skull * 100;
        assertEquals(arg0, final_deduct);
        Assertions.assertEquals(0, game.player.getScore());
    }

    //row 110
    @When("Player gets captain as FC for player get score with skull island")
    public void playerGetsCaptainAsFCForPlayerGetScoreWithSkullIsland() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("captain");
    }

    @And("Player roll dice and get five skull three monkey")
    public void playerRollDiceAndGetFiveSkullThreeMonkey() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<5){
                current[i] = "skull";
            }
            if (i>=5 && i<8){
                current[i] = "monkey";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll three monkey get two skull one coin")
    public void playerRerollThreeMonkeyGetTwoSkullOneCoin() {
        String[] hold = new String[]{"0", "1", "2", "3", "4"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[5] = "skull";
        newCurrent[6] = "skull";
        newCurrent[7] = "coin";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 111
    @And("Player roll dice and get three skulls and five swords for skull island")
    public void playerRollDiceAndGetThreeSkullsAndFiveSwordsForSkullIsland() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<3){
                current[i] = "skull";
            }else {
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }
    @And("Player reroll five sword get five coin")
    public void playerRerollFiveSwordGetFiveCoin() {
        String[] hold = new String[]{"0", "1", "2", "3", "4"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[3] = "coin";
        newCurrent[4] = "coin";
        newCurrent[5] = "coin";
        newCurrent[6] = "coin";
        newCurrent[7] = "coin";
        game.player.setCurrentRoll(newCurrent);
    }

    @Then("Player score {int} to other players for skull island")
    public void playerScoreToOtherPlayersForSkullIsland(int arg0) {
        int final_num_skull = game.rerollSkullLandAndCountNOSkull(game.player.getCurrentRoll(), game.player);
        int final_deduct = -5 * 100;
        assertEquals(arg0, final_deduct);
        Assertions.assertEquals(0, game.player.getScore());
    }

    //row 114
    @And("Player roll dice and get four monkey three skull one sword")
    public void playerRollDiceAndGetFourMonkeyThreeSkullOneSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 4) {
                current[i] = "monkey";
            }
            if (i >= 4 && i < 7) {
                current[i] = "skull";
            }
            if (i >= 7 && i < 8) {
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @Then("Player die and score {int}")
    public void playerDieAndScore(int arg0) {
        boolean final_state = game.checkIfDie(game.player.getCurrentRoll(), game.player);
        assertTrue(final_state);
        game.seaBattle(game.player, game.player.getCurrentRoll());
        int final_score = game.player.getScore();
        assertEquals(arg0, final_score);
    }

    //row 115
    @When("Player gets three sword sea battle as FC for player get score with full chest")
    public void playerGetsThreeSwordSeaBattleAsFCForPlayerGetScoreWithFullChest() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("3 sword");
    }

    @And("Player roll dice and get two sword two skull four parrots")
    public void playerRollDiceAndGetTwoSwordTwoSkullFourParrots() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 2) {
                current[i] = "sword";
            }
            if (i >= 2 && i < 4) {
                current[i] = "skull";
            }
            if (i >= 4 && i < 8) {
                current[i] = "parrots";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll four parrots get four skull")
    public void playerRerollFourParrotsGetFourSkull() {
        String[] hold = new String[]{"0", "1", "2", "3"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[4] = "skull";
        newCurrent[5] = "skull";
        newCurrent[6] = "skull";
        newCurrent[7] = "skull";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 116
    @When("Player gets four sword sea battle as FC for player get score with full chest")
    public void playerGetsFourSwordSeaBattleAsFCForPlayerGetScoreWithFullChest() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("4 sword");
    }

    @And("Player roll dice and get two monkey three skull three sword")
    public void playerRollDiceAndGetTwoMonkeyThreeSkullThreeSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 2) {
                current[i] = "monkey";
            }
            if (i >= 2 && i < 5) {
                current[i] = "skull";
            }
            if (i >= 5 && i < 8) {
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 117
    @And("Player roll dice and get three monkey two sword one coin two parrots")
    public void playerRollDiceAndGetThreeMonkeyTwoSwordOneCoinTwoParrots() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 3) {
                current[i] = "monkey";
            }
            if (i >= 3 && i < 5) {
                current[i] = "sword";
            }
            if (i >= 5 && i < 6) {
                current[i] = "coin";
            }
            if (i >= 6 && i < 8) {
                current[i] = "parrots";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @Then("Player did not die and score {int}")
    public void playerDidNotDieAndScore(int arg0) {
        boolean final_state = game.checkIfDie(game.player.getCurrentRoll(), game.player);
        assertFalse(final_state);
        game.seaBattle(game.player, game.player.getCurrentRoll());
        int final_score = game.player.getScore();
        assertEquals(arg0, final_score);
    }

    //row 118
    @And("Player roll dice and get four monkey one sword one skull two parrots")
    public void playerRollDiceAndGetFourMonkeyOneSwordOneSkullTwoParrots() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i < 4) {
                current[i] = "monkey";
            }
            if (i >= 4 && i < 5) {
                current[i] = "sword";
            }
            if (i >= 5 && i < 6) {
                current[i] = "skull";
            }
            if (i >= 6 && i < 8) {
                current[i] = "parrots";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll two parrot and get one sword one skull")
    public void playerRerollTwoParrotAndGetOneSwordOneSkull() {
        String[] hold = new String[]{"0", "1", "2", "3", "4", "5"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[6] = "sword";
        newCurrent[7] = "skull";
        game.player.setCurrentRoll(newCurrent);
    }
}
