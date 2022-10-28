package comp4004;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PirateGameUnitTest {
    private ByteArrayOutputStream testOut;
    private final PrintStream systemOut = System.out;
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    PirateGame game;

    private void scannerInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @BeforeEach
    void creatingGame(){
        game = new PirateGame();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    @DisplayName("get winner test 1")
    void getWinnerTest1() {
        Player[] playerList = new Player[3];
        Player p1 = new Player("name1");
        p1.setScore(100);
        playerList[0] = p1;
        Player p2 = new Player("name2");
        p2.setScore(200);
        playerList[1] = p2;
        Player p3 = new Player("name3");
        p3.setScore(300);
        playerList[2] = p3;

        Player winner = game.getWinner(playerList);
        Assertions.assertEquals("name3", winner.getName());
    }

    @Test
    @DisplayName("get winner test 2")
    void getWinnerTest2() {
        Player[] playerList = new Player[3];
        Player p1 = new Player("name1");
        p1.setScore(100);
        playerList[0] = p1;
        Player p2 = new Player("name2");
        p2.setScore(200);
        playerList[1] = p2;
        Player p3 = new Player("name3");
        p3.setScore(-300);
        playerList[2] = p3;

        Player winner = game.getWinner(playerList);
        Assertions.assertEquals("name2", winner.getName());
    }

    @Test
    @DisplayName("check if die test 1")
    void checkIfDieTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("sorceress");
        game.setNewPlayer(player);
        String[] roll = {"skull", "sword", "parrots", "parrots", "parrots", "parrots", "parrots", "parrots"};
        boolean isDie = game.checkIfDie(roll, player);
        assertFalse(isDie);
    }

    @Test
    @DisplayName("check if die test 2")
    void checkIfDieTest2() {
        scannerInput("1");
        Player player = new Player("Di");
        player.setFortuneCard("sorceress");
        game = new PirateGame();
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "skull", "parrots", "parrots", "parrots", "parrots"};
        boolean isDie = game.checkIfDie(roll, player);
        assertFalse(isDie);
    }

    @Test
    @DisplayName("check if die test 3")
    void checkIfDieTest3() {
        Player player = new Player("Di");
        player.setFortuneCard("captain");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "parrots", "parrots", "parrots", "parrots", "parrots"};
        boolean isDie = game.checkIfDie(roll, player);
        assertTrue(isDie);
    }

    @Test
    @DisplayName("roll single die test 1")
    void rollSingleDieTest() {
        String[] roll = {"uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        roll[0] = game.rollSingleDie();
        assertNotEquals("uncoin", roll[0]);
    }


}
