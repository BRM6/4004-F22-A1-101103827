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

    @Test
    @DisplayName("draw forturn card test 1")
    void drawForturnCardTest() {
        Player player = new Player("Di");
        game.drawForturnCard(player);
        game.setNewPlayer(player);
        assertTrue(player.getFortuneCard() == "treasure chest" || player.getFortuneCard() == "sorceress" || player.getFortuneCard() == "captain" || player.getFortuneCard() == "monkey business" || player.getFortuneCard() == "diamond" || player.getFortuneCard() == "coin" || player.getFortuneCard() == "2 skull" || player.getFortuneCard() == "1 skull" || player.getFortuneCard() == "2 sword" || player.getFortuneCard() == "3 sword" || player.getFortuneCard() == "4 sword");
    }

    @Test
    @DisplayName("use sorceress test 1")
    void useSorceressTest1() {
        scannerInput("1");
        Player player = new Player("Di");
        player.setFortuneCard("sorceress");
        game = new PirateGame();
        game.setNewPlayer(player);
        String[] before_roll = {"skull", "sword", "parrots", "parrots", "parrots", "parrots", "parrots", "parrots"};
        game.useSorceress(before_roll, player);
        assertTrue(player.getFortuneCard() == "not sorceress");
    }

    @Test
    @DisplayName("use sorceress test 2")
    void useSorceressTest2() {
        scannerInput("0");
        Player player = new Player("Di");
        player.setFortuneCard("sorceress");
        game = new PirateGame();
        game.setNewPlayer(player);
        String[] before_roll = {"skull", "sword", "parrots", "parrots", "parrots", "parrots", "parrots", "parrots"};
        String[] after = game.useSorceress(before_roll, player);
        assertTrue(player.getFortuneCard() == "sorceress");
    }

    @Test
    @DisplayName("score for diamond and coin test 1")
    void scoreForDCTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("coin");
        game.setNewPlayer(player);
        String[] roll = {"skull", "parrots", "parrots", "parrots", "parrots", "coin", "coin", "coin"};
        int score = game.scoreForDC(roll, player);
        assertEquals(400, score);
    }

    @Test
    @DisplayName("score for diamond and coin test 2")
    void scoreForDCTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("diamond");
        game.setNewPlayer(player);
        String[] roll = {"skull", "parrots", "parrots", "parrots", "parrots", "coin", "coin", "coin"};
        int score = game.scoreForDC(roll, player);
        assertEquals(400, score);
    }

    @Test
    @DisplayName("score for diamond and coin test 3")
    void scoreForDCTest3() {
        Player player = new Player("Di");
        player.setFortuneCard("captain");
        game.setNewPlayer(player);
        String[] roll = {"skull", "parrots", "parrots", "parrots", "diamond", "coin", "coin", "coin"};
        int score = game.scoreForDC(roll, player);
        assertEquals(400, score);
    }

    @Test
    @DisplayName("score for kinds and chest test 1")
    void scoreForKindsAndChestTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("coin");
        game.setNewPlayer(player);
        String[] roll = {"skull", "parrots", "parrots", "parrots", "parrots", "coin", "coin", "coin"};
        int score = game.scoreForKindsAndChest(roll, player);
        assertEquals(400, score);
    }

    @Test
    @DisplayName("score for kinds and chest test 2")
    void scoreForKindsAndChestTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("captain");
        game.setNewPlayer(player);
        String[] roll = {"skull", "parrots", "parrots", "parrots", "parrots", "coin", "coin", "coin"};
        int score = game.scoreForKindsAndChest(roll, player);
        assertEquals(300, score);
    }

    @Test
    @DisplayName("score for kinds and chest test 3")
    void scoreForKindsAndChestTest3() {
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "parrots", "parrots", "parrots", "monkey", "monkey", "monkey"};
        int score = game.scoreForKindsAndChest(roll, player);
        assertEquals(200, score);
    }

    @Test
    @DisplayName("sea battle test 1")
    void seaBattleTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("2 sword");
        game.setNewPlayer(player);
        String[] roll = {"sword", "sword", "parrots", "parrots", "parrots", "parrots", "skull", "skull"};
        game.seaBattle(player, roll);
        Assertions.assertEquals(500, player.getScore());
    }

    @Test
    @DisplayName("sea battle test 2")
    void seaBattleTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("3 sword");
        game.setNewPlayer(player);
        String[] roll = {"sword", "sword", "parrots", "parrots", "parrots", "parrots", "skull", "skull"};
        game.seaBattle(player, roll);
        Assertions.assertEquals(0, player.getScore());
    }

    @Test
    @DisplayName("sea battle test 3")
    void seaBattleTest3() {
        Player player = new Player("Di");
        player.setFortuneCard("2 sword");
        game.setNewPlayer(player);
        String[] roll = {"sword", "sword", "parrots", "parrots", "skull", "skull", "skull", "skull"};
        game.seaBattle(player, roll);
        Assertions.assertEquals(0, player.getScore());
    }

    @Test
    @DisplayName("score with monkey bussiness card test 1")
    void scoreForMonkeyBTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] player_die = {"sword", "parrots", "parrots", "parrots", "monkey", "monkey", "sword", "sword"};
        game.calculateScoreForARoundWithCapMonkey(player, player_die);
        Assertions.assertEquals(1100, player.getScore());
    }

    @Test
    @DisplayName("score with monkey business card test 2")
    void scoreForMonkeyBTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] player_die = {"sword", "parrots", "parrots", "skull", "skull", "monkey", "sword", "sword"};
        game.calculateScoreForARoundWithCapMonkey(player, player_die);
        Assertions.assertEquals(200, player.getScore());
    }

    @Test
    @DisplayName("score with captain card test 1")
    void scoreWhenCapTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("captain");
        game.setNewPlayer(player);
        String[] player_die = {"sword", "parrots", "parrots", "parrots", "parrots", "parrots", "sword", "sword"};
        game.calculateScoreForARoundWithCapMonkey(player, player_die);
        Assertions.assertEquals(2200, player.getScore());
    }

    @Test
    @DisplayName("score with captain card test 2")
    void scoreWhenCapTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("captain");
        game.setNewPlayer(player);
        String[] player_die = {"sword", "parrots", "parrots", "parrots", "skull", "skull", "sword", "sword"};
        game.calculateScoreForARoundWithCapMonkey(player, player_die);
        Assertions.assertEquals(400, player.getScore());
    }

    @Test
    @DisplayName(" calculate score for a round with cap or monkey card test 1")
    void calculateScoreForARoundWithCapMonkeyTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("captain");
        game.setNewPlayer(player);
        String[] player_die = {"sword", "parrots", "parrots", "parrots", "parrots", "parrots", "sword", "sword"};
        game.calculateScoreForARoundWithCapMonkey(player, player_die);
        Assertions.assertEquals(2200, player.getScore());
    }

    @Test
    @DisplayName(" calculate score for a round with cap or monkey card test 2")
    void calculateScoreForARoundWithCapMonkeyTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] player_die = {"sword", "parrots", "parrots", "parrots", "monkey", "monkey", "sword", "sword"};
        game.calculateScoreForARoundWithCapMonkey(player, player_die);
        Assertions.assertEquals(1100, player.getScore());
    }

    @Test
    @DisplayName("reRoll without hold test 1")
    void reRollWithoutHoldTest1() {
        String[] before_roll = {"uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] after_roll = game.reRollWithoutHold(before_roll);
        assertTrue(before_roll != after_roll);
    }

    @Test
    @DisplayName("reRoll without hold test 2")
    void reRollWithoutHoldTest2() {
        String[] before_roll = {"skull", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] after_roll = game.reRollWithoutHold(before_roll);
        assertEquals("skull" , after_roll[0]);
        assertTrue(before_roll != after_roll);
    }

    @Test
    @DisplayName("reRoll with hold test 1")
    void rerollWithHoldTest1() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {"0", "1"};
        String[] after_roll = game.RerollWithHold(before_roll, hold);
        assertEquals("uncoin", after_roll[0]);
        assertEquals("undiamond", after_roll[1]);
        assertTrue(before_roll != after_roll);
    }

    @Test
    @DisplayName("reRoll with hold test 2")
    void rerollWithHoldTest2() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {"0", "1", "2", "3", "4", "5", "6"};
        String[] after_roll = game.RerollWithHold(before_roll, hold);
        assertTrue(before_roll == after_roll);
    }

    @Test
    @DisplayName("reRoll with hold test 3")
    void rerollWithHoldTest3() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {"0", "1", "2", "3", "4", "5", "6", "7"};
        String[] after_roll = game.RerollWithHold(before_roll, hold);
        assertTrue(before_roll == after_roll);
    }

    @Test
    @DisplayName("reRoll with hold test 4")
    void rerollWithHoldTest4() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {};
        String[] after_roll = game.RerollWithHold(before_roll, hold);
        assertTrue(before_roll != after_roll);
    }

    @Test
    @DisplayName("reRoll with hold test 5")
    void rerollWithHoldTest5() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] after_roll = game.RerollWithHold(before_roll, hold);
        assertTrue(before_roll == after_roll);
    }

    @Test
    @DisplayName("reroll with chest hold test 1 ")
    void rerollWithChestHoldTest1() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {"2", "3"};
        String[] chest = {"0", "1"};
        String[] after_roll = game.RerollWithChestHold(before_roll, hold, chest);
        assertEquals("uncoin", after_roll[0]);
        assertEquals("undiamond", after_roll[1]);
        assertEquals("uncoin", after_roll[2]);
        assertEquals("uncoin", after_roll[3]);
        assertTrue(before_roll != after_roll);
    }

    @Test
    @DisplayName("reroll with chest hold test 2 ")
    void rerollWithChestHoldTest2() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {"2", "3", "4", "5", "6"};
        String[] chest = {"0", "1"};
        String[] after_roll = game.RerollWithChestHold(before_roll, hold, chest);
        assertTrue(before_roll == after_roll);
    }

    @Test
    @DisplayName("reroll with chest hold test 3 ")
    void rerollWithChestHoldTest3() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {"0", "1"};
        String[] chest = {"2", "3", "4", "5", "6"};
        String[] after_roll = game.RerollWithChestHold(before_roll, hold, chest);
        assertTrue(before_roll == after_roll);
    }

    @Test
    @DisplayName("reroll with chest hold test 4 ")
    void rerollWithChestHoldTest4() {
        String[] before_roll = {"uncoin", "undiamond", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin", "uncoin"};
        String[] hold = {};
        String[] chest = {};
        String[] after_roll = game.RerollWithChestHold(before_roll, hold, chest);
        assertTrue(before_roll != after_roll);
    }

    @Test
    @DisplayName("score chest test 1")
    void scoreChestTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("treasure chest");
        game.setNewPlayer(player);
        String[] player_die = {"coin", "parrots", "monkey", "sword", "sword", "sword", "sword", "parrots"};
        String[] player_die_index = {"0","1","2","3","4","5","6","7"};
        player.setHoldingDie(player_die_index);
        game.scoreChest(player, player_die);
        Assertions.assertEquals(300, player.getScore());
    }

    @Test
    @DisplayName("score chest test 2")
    void scoreChestTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("treasure chest");
        game.setNewPlayer(player);
        String[] player_die = {"sword", "parrots", "parrots", "parrots", "parrots", "parrots", "sword", "sword"};
        String[] player_die_index = {"0","1","2","3","4","5","6","7"};
        player.setHoldingDie(player_die_index);
        game.scoreChest(player, player_die);
        Assertions.assertEquals(1100, player.getScore());
    }

    @Test
    @DisplayName("reroll skull land and count number of skull test 1")
    void rerollSkullLandAndCountNOSkullTest1() {
        scannerInput("1");
        game = new PirateGame();
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "skull", "skull", "skull", "skull", "skull"};
        int numOfSkull = game.rerollSkullLandAndCountNOSkull(roll, player);
        assertTrue(numOfSkull == -8);
    }

    @Test
    @DisplayName("reroll skull land and count number of skull test 2")
    void rerollSkullLandAndCountNOSkullTest2() {
        scannerInput("1");
        game = new PirateGame();
        Player player = new Player("Di");
        player.setFortuneCard("captain");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "skull", "skull", "skull", "skull", "skull"};
        int numOfSkull = game.rerollSkullLandAndCountNOSkull(roll, player);
        assertTrue(numOfSkull == -16);
    }

    @Test
    @DisplayName("reroll skull land and count number of skull test 3")
    void rerollSkullLandAndCountNOSkullTest3() {
        scannerInput("1");
        game = new PirateGame();
        Player player = new Player("Di");
        player.setFortuneCard("2 skull");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "skull", "skull", "skull", "skull", "skull"};
        int numOfSkull = game.rerollSkullLandAndCountNOSkull(roll, player);
        assertTrue(numOfSkull == -10);
    }

    @Test
    @DisplayName("reroll skull land and count number of skull test 4")
    void rerollSkullLandAndCountNOSkullTest4() {
        scannerInput("1");
        game = new PirateGame();
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "skull", "skull", "skull", "skull", "coin"};
        int numOfSkull = game.rerollSkullLandAndCountNOSkull(roll, player);
        assertTrue(numOfSkull == -7);
    }

    @Test
    @DisplayName("reroll skull land and count number of skull test 5")
    void rerollSkullLandAndCountNOSkullTest5() {
        scannerInput("1");
        game = new PirateGame();
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "skull", "skull", "coin", "coin", "coin"};
        int numOfSkull = game.rerollSkullLandAndCountNOSkull(roll, player);
        assertTrue(numOfSkull <= -5);
    }

    @Test
    @DisplayName("is skull island test 1")
    void isSkullLandTest1() {
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] roll = {"skull", "skull", "skull", "skull", "skull", "coin", "coin", "coin"};
        assertTrue(game.isSkullLand(roll) == true);
    }

    @Test
    @DisplayName("is skull island test 2")
    void isSkullLandTest2() {
        Player player = new Player("Di");
        player.setFortuneCard("monkey business");
        game.setNewPlayer(player);
        String[] roll = {"skull", "coin", "coin", "coin", "coin", "coin", "coin", "coin"};
        assertTrue(game.isSkullLand(roll) == false);
    }
}
