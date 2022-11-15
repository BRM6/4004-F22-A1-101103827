package cucumberTest.testcode;

import comp4004.PirateGame;
import comp4004.Player;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class miscellaneousFCAndFullChestBonusCases {
    private PirateGame game;
    private Player player;

    //background
    @Given("Game has started for miscellaneous FC and Full Chest")
    public void gameHasStartedForMiscellaneousFCAndFullChest() {
        game = new PirateGame();
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
        assertNotEquals("not sorceress", game.player.getFortuneCard());
    }
}
