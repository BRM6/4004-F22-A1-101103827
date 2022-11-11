package cucumberTest.testcode;

import comp4004.PirateGame;
import comp4004.Player;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class playerDieCases {
    private PirateGame game;
    private Player player;

    //background
    @Given("Game has started")
    public void gameHasStarted() {
        game = new PirateGame();
    }

    @And("Player with name {string} is playing the game")
    public void playerWithNameIsPlayingTheGame(String arg0) {
        player = new Player(arg0);
        game.setNewPlayer(player);
    }

    @And("Player gets coin as FC")
    public void playerGetsCoinAsFC() {
        game.drawForturnCard(game.player);
        game.player.setFortuneCard("coin");
    }

    //row 45
    @When("Player roll dice and get three skulls and five swords")
    public void playerRollDiceAndGetSkullsAndFiveSwords() {
        String[] current = {"skull", "skull", "skull", "sword", "sword", "sword", "sword", "sword"};
        game.player.setCurrentRoll(current);
    }

    @Then("Player die")
    public void playerDie() {
        assertTrue(game.checkIfDie(game.player.getCurrentRoll(), game.player));
    }

    @And("Player finished the round")
    public void playerFinishedTheRound() {
        assertFalse(game.isGoing);
    }

    @And("Player scored zero")
    public void playerScoredZero() {
        assertEquals(0, game.player.getScore());
    }

    //row46
    @When("Player roll dice and get one skull four parrots three sword")
    public void playerRollDiceAndGetOneSkullFourParrotsThreeSword() {
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
            if (i>=5){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll three swords and get two skull one sword")
    public void playerRerollThreeSwordsAndGetTwoSkullOneSword() {
        String[] hold = new String[] {"1", "2", "3", "4"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[5] = "skull";
        newCurrent[6] = "skull";
        newCurrent[7] = "sword";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 47
    @When("Player roll dice and get two skull four parrots two sword")
    public void playerRollDiceAndGetTwoSkullFourParrotsTwoSword() {
        String[] current = new String[8];
        for (int i=0; i<8; i++){               //roll die
            current[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                current[i] = "skull";
            }
            if (i>=2 && i<6){
                current[i] = "parrots";
            }
            if (i>=6){
                current[i] = "sword";
            }
        }
        game.player.setCurrentRoll(current);
    }

    @And("Player reroll two swords and get one skull one sword")
    public void playerRerollTwoSwordsAndGetOneSkullOneSword() {
        String[] hold = new String[] {"2", "3", "4", "5"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[6] = "skull";
        newCurrent[7] = "sword";
        game.player.setCurrentRoll(newCurrent);
    }

    //row 48
    @And("Player reroll three swords and get one skull two monkey")
    public void playerRerollThreeSwordsAndGetOneSkullTwoMonkey() {
        String[] hold = new String[] {"1", "2", "3", "4"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[5] = "skull";
        newCurrent[6] = "monkey";
        newCurrent[7] = "monkey";
        game.player.setCurrentRoll(newCurrent);
    }

    @And("Player reroll two monkey and get one skull one monkey")
    public void playerRerollTwoMonkeyAndGetOneSkullOneMonkey() {
        String[] hold = new String[] {"1", "2", "3", "4"};
        String[] newCurrent = game.RerollWithHold(game.player.getCurrentRoll(), hold);
        newCurrent[6] = "skull";
        newCurrent[7] = "monkey";
        game.player.setCurrentRoll(newCurrent);
    }
}
