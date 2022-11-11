package cucumberTest.testcode;

import comp4004.PirateGame;
import comp4004.Player;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class playerGetScoreCases {
    private PirateGame game;
    private Player player;

    //background
    @Given("Game has started for player get score")
    public void gameHasStartedForPlayerGetScore() {
        game = new PirateGame();
    }

    @And("Player with name {string} is playing the game for player get score")
    public void playerWithNameIsPlayingTheGameForPlayerGetScore(String arg0) {
        player = new Player(arg0);
        game.setNewPlayer(player);
    }

    //row 50
    @When("Player gets coin as FC for player get score")
    public void playerGetsCoinAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("coin");
    }

    @And("Player roll dice and get one skull two parrots three sword two coin")
    public void playerRollDiceAndGetOneSkullTwoParrotsThreeSwordTwoCoin() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                current[i] = "skull";
            }
            if (i>=1 && i<3){
                current[i] = "parrots";
            }
            if (i>=3 && i<6){
                current[i] = "sword";
            }
            if (i>=6){
                current[i] = "coin";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll two parrots and get two coin")
    public void playerRerollTwoParrotsAndGetTwoCoin() {
        String[] hold = new String[] {"3", "4", "5", "6", "7"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[1] = "coin";
        newCurrent[2] = "coin";
        game.player.setCurrentRoll(newCurrent);
    }

    @And("Player reroll three sword and get three coin")
    public void playerRerollThreeSwordAndGetThreeCoin() {
        String[] hold = new String[] {"1", "2", "6", "7"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[3] = "coin";
        newCurrent[4] = "coin";
        newCurrent[5] = "coin";
        game.player.setCurrentRoll(newCurrent);
    }

    @Then("Player score {int}")
    public void playerScore(int arg0) {
        int final_score = game.scoreForKindsAndChest(game.player.getCurrentRoll(), game.player) + game.scoreForDC(game.player.getCurrentRoll(), game.player);
        game.player.setScore(final_score);
        assertEquals(arg0, game.player.getScore());
    }

    @And("Player finished the round for player get score")
    public void playerFinishedTheRoundForPlayerGetScore() {
        assertFalse(game.isGoing);
    }

    //row 52
    @When("Player gets captain as FC for player get score")
    public void playerGetsCaptainAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("captain");
    }

    @And("Player roll dice and get two monkey two parrot two diamond two coin")
    public void playerRollDiceAndGetTwoMonkeyTwoParrotTwoDiamondTwoCoin() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                current[i] = "monkey";
            }
            if (i>=2 && i<4){
                current[i] = "parrots";
            }
            if (i>=4 && i<6){
                current[i] = "diamond";
            }
            if (i>=6){
                current[i] = "coin";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @Then("Player score {int} with captain card")
    public void playerScoreWithCaptainCard(int arg0) {
        game.calculateScoreForARoundWithCapMonkey(game.player, player.getCurrentRoll());
        int final_score = player.getScore();
        assertEquals(arg0, final_score);
    }

    //row 53
    @And("Player roll dice and get two monkey two skull two sword two parrots")
    public void playerRollDiceAndGetTwoMonkeyTwoSkullTwoSwordTwoParrots() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                current[i] = "monkey";
            }
            if (i>=2 && i<4){
                current[i] = "skull";
            }
            if (i>=4 && i<6){
                current[i] = "sword";
            }
            if (i>=6){
                current[i] = "parrots";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll two parrots and get one sword and one monkey")
    public void playerRerollTwoParrotsAndGetOneSwordAndOneMonkey() {
        String[] hold = new String[] {"0", "1", "4", "5"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[6] = "sword";
        newCurrent[7] = "monkey";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 54
    @And("Player roll dice and get three monkey three sword two skull")
    public void playerRollDiceAndGetThreeMonkeyThreeSwordTwoSkull() {
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
            if (i>=6){
                current[i] = "skull";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 55
    @And("Player roll dice and get three diamond two skull one monkey one sword one parrot")
    public void playerRollDiceAndGetThreeDiamondTwoSkullOneMonkeyOneSwordOneParrot() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<3){
                current[i] = "diamond";
            }
            if (i>=3 && i<5){
                current[i] = "skull";
            }
            if (i>=5 && i<6){
                current[i] = "monkey";
            }
            if (i>=6 && i<7){
                current[i] = "sword";
            }
            if (i>=7 && i<8){
                current[i] = "parrots";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 56
    @When("Player gets diamond as FC for player get score")
    public void playerGetsDiamondAsFCForPlayerGetScore() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("diamond");
    }

    @And("Player roll dice and get four coin two skull two sword")
    public void playerRollDiceAndGetFourCoinTwoSkullTwoSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<4){
                current[i] = "coin";
            }
            if (i>=4 && i<6){
                current[i] = "skull";
            }
            if (i>=6){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 57
    @And("Player roll dice and get three sword four parrots one skull")
    public void playerRollDiceAndGetThreeSwordFourParrotsOneSkull() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<3){
                current[i] = "sword";
            }
            if (i>=3 && i<7){
                current[i] = "parrots";
            }
            if (i>=7){
                current[i] = "skull";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 58
    @And("Player roll dice and get one skull two coin two parrot three sword")
    public void playerRollDiceAndGetOneSkullTwoCoinTwoParrotThreeSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                current[i] = "skull";
            }
            if (i>=1 && i<3){
                current[i] = "coin";
            }
            if (i>=3 && i<5){
                current[i] = "parrots";
            }
            if (i>=5 && i<8){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll two parrot and get one coin one sword")
    public void playerRerollTwoParrotAndGetOneCoinOneSword() {
        String[] hold = new String[] {"1", "2", "5", "6", "7"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[3] = "coin";
        newCurrent[4] = "sword";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 60
    @And("Player roll dice and get one skull two monkey two parrot three sword")
    public void playerRollDiceAndGetOneSkullTwoMonkeyTwoParrotThreeSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                current[i] = "skull";
            }
            if (i>=1 && i<3){
                current[i] = "monkey";
            }
            if (i>=3 && i<5){
                current[i] = "parrots";
            }
            if (i>=5 && i<8){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll two monkey and get one skull one sword")
    public void playerRerollTwoMonkeyAndGetOneSkullOneSword() {
        String[] hold = new String[] {"3", "4", "5", "6", "7"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[1] = "skull";
        newCurrent[2] = "sword";
        game.player.setCurrentRoll(newCurrent);
    }

    @And("Player reroll two parrots and get one sword and one monkey second version")
    public void playerRerollTwoParrotsAndGetOneSwordAndOneMonkeySecondVersion() {
        String[] hold = new String[] {"2", "5", "6", "7"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[3] = "sword";
        newCurrent[4] = "monkey";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 62
    @And("Player roll dice and get six monkey two skull")
    public void playerRollDiceAndGetSixMonkeyTwoSkull() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<6){
                current[i] = "monkey";
            }
            if (i>=6){
                current[i] = "skull";
            }
        }
        game.player.setCurrentRoll(current);
    }

    //row 63
    @And("Player roll dice and get seven parrot one skull")
    public void playerRollDiceAndGetSevenParrotOneSkull() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<7){
                current[i] = "parrots";
            }
            if (i>=7){
                current[i] = "skull";
            }
        }
        game.player.setCurrentRoll(current);
    }
}
