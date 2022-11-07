package cucumberTest.testcode;

import comp4004.PirateGame;
import comp4004.Player;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlayerDieTest {
    private PirateGame game;
    private Player player;

    //row 45
    @Given("Game has started")
    public void gameHasStarted() {
        game = new PirateGame();
    }

    @When("Player with name {string} is playing the game")
    public void playerWithNameIsPlayingTheGame(String arg0) {
        player = new Player(arg0);
        game.setNewPlayer(player);
    }

    @And("Player gets coin as FC")
    public void playerGetsCoinAsFC() {
        game.player.setFortuneCard("coin");
    }

    @And("Player roll dice and get three skulls and five swords")
    public void playerRollDiceAndGetSkullsAndFiveSwords() {
        String[] current = {"skull", "skull", "skull", "sword", "sword", "sword", "sword", "sword"};
        game.player.setCurrentRoll(current);
    }

    @Then("Player die and death reminder showed")
    public void playerDieAndDeathReminderShowed() {
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
}
