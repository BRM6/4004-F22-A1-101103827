package comp4004;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PirateGameTest {
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

    //PART 1
    @Test
    @DisplayName("test row 45")
    void testRow45() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "skull";
            }else {
                die[i] = "sword";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(0, final_score);
    }

    @Test
    @DisplayName("test row 46")
    void testRow46() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<5){
                die[i] = "parrots";
            }
            if (i>=5){
                die[i] = "sword";
            }
        }
        String[] hold = {"1", "2", "3", "4"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[5] = "skull";
        die[6] = "skull";
        die[7] = "sword";
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
    }

    @Test
    @DisplayName("test row 47")
    void testRow47() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                die[i] = "skull";
            }
            if (i>=2 && i<6){
                die[i] = "parrots";
            }
            if (i>=6){
                die[i] = "sword";
            }
        }
        String[] hold = {"0", "1", "2", "3", "4", "5"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[6] = "skull";
        die[7] = "sword";
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
    }

    @Test
    @DisplayName("test row 48")
    void testRow48() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<5){
                die[i] = "parrots";
            }
            if (i>=5){
                die[i] = "sword";
            }
        }
        String[] hold = {"0", "1", "2", "3", "4"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll 1st
        //assign dice
        die[5] = "skull";
        die[6] = "monkey";
        die[7] = "monkey";
        hold = new String[]{"0", "1", "2", "3", "4", "5"};
        die = game.RerollWithHold(die, hold); //reroll 2rd
        die[6] = "skull";
        die[7] = "monkey";
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
    }

    @Test
    @DisplayName("test row 50")
    void testRow50() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<3){
                die[i] = "parrots";
            }
            if (i>=3 && i<6){
                die[i] = "sword";
            }
            if (i>=6){
                die[i] = "coin";
            }
        }
        String[] hold = {"0", "3", "4", "5", "6", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll 1st
        //assign dice
        die[1] = "coin";
        die[2] = "coin";
        hold = new String[]{"0", "1", "2", "6", "7"};
        die = game.RerollWithHold(die, hold); //reroll 2rd
        die[3] = "coin";
        die[4] = "coin";
        die[5] = "coin";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(4800, final_score);
    }

    @Test
    @DisplayName("test row 52")
    void testRow52() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("captain");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<2) {
                die[i] = "monkey";
            }
            if (i>=2 && i<4){
                die[i] = "parrots";
            }
            if (i>=4 && i<6){
                die[i] = "diamond";
            }
            if (i>=6 && i<8){
                die[i] = "coin";
            }
        }
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(800, final_score);
    }

    @Test
    @DisplayName("test row 53")
    void testRow53() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<2) {
                die[i] = "monkey";
            }
            if (i>=2 && i<4){
                die[i] = "skull";
            }
            if (i>=4 && i<6){
                die[i] = "sword";
            }
            if (i>=6 && i<8){
                die[i] = "parrots";
            }
        }
        String[] hold = {"0", "1", "2", "3", "4", "5"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[6] = "sword";
        die[7] = "monkey";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(300, final_score);
    }

    @Test
    @DisplayName("test row 54")
    void testRow54() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "monkey";
            }
            if (i>=3 && i<6){
                die[i] = "sword";
            }
            if (i>=6 && i<8){
                die[i] = "skull";
            }

        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(300, final_score);
    }

    @Test
    @DisplayName("test row 55")
    void testRow55() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "diamond";
            }
            if (i>=3 && i<5){
                die[i] = "skull";
            }
            if (i>=5 && i<6){
                die[i] = "monkey";
            }
            if (i>=6 && i<7){
                die[i] = "sword";
            }
            if (i>=7 && i<8){
                die[i] = "parrots";
            }

        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(500, final_score);
    }

    @Test
    @DisplayName("test row 56")
    void testRow56() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("diamond");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<4){
                die[i] = "coin";
            }
            if (i>=4 && i<6){
                die[i] = "skull";
            }
            if (i>=5 && i<6){
                die[i] = "monkey";
            }
            if (i>=6 && i<8){
                die[i] = "sword";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(700, final_score);
    }

    @Test
    @DisplayName("test row 57")
    void testRow57() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "sword";
            }
            if (i>=3 && i<7){
                die[i] = "parrots";
            }
            if (i>=7 && i<8){
                die[i] = "skull";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(400, final_score);
    }

    @Test
    @DisplayName("test row 58")
    void testRow58() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<3){
                die[i] = "coin";
            }
            if (i>=3 && i<5){
                die[i] = "parrots";
            }
            if (i>=5){
                die[i] = "sword";
            }
        }
        String[] hold = {"0", "1", "2", "5", "6", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[3] = "coin";
        die[4] = "sword";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(800, final_score);
    }

    @Test
    @DisplayName("test row 59")
    void testRow59() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("captain");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<3){
                die[i] = "coin";
            }
            if (i>=3 && i<5){
                die[i] = "parrots";
            }
            if (i>=5){
                die[i] = "sword";
            }
        }
        String[] hold = {"0", "1", "2", "5", "6", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[3] = "coin";
        die[4] = "sword";
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(1200, final_score);
    }

    @Test
    @DisplayName("test row 60")
    void testRow60() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<3){
                die[i] = "monkey";
            }
            if (i>=3 && i<5){
                die[i] = "parrots";
            }
            if (i>=5 && i<8){
                die[i] = "sword";
            }
        }
        String[] hold = {"0", "5", "6", "3", "4", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll 1st
        //assign dice
        die[1] = "skull";
        die[2] = "sword";
        hold = new String[]{"0", "1", "2", "6", "7", "5"};
        die = game.RerollWithHold(die, hold); //reroll 2rd
        die[3] = "sword";
        die[4] = "monkey";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(600, final_score);
    }

    @Test
    @DisplayName("test row 62")
    void testRow62() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<6){
                die[i] = "monkey";
            }
            if (i>=6 && i<8){
                die[i] = "skull";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(1100, final_score);
    }

    @Test
    @DisplayName("test row 63")
    void testRow63() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<7){
                die[i] = "parrots";
            }
            if (i>=7 && i<8){
                die[i] = "skull";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(2100, final_score);
    }

    @Test
    @DisplayName("test row 64")
    void testRow64() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            die[i] = "coin";
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(5400, final_score);
    }

    @Test
    @DisplayName("test row 65")
    void testRow65() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("diamond");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            die[i] = "coin";
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(5400, final_score);
    }

    @Test
    @DisplayName("test row 66")
    void testRow66() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("captain");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            die[i] = "sword";
        }
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(9000, final_score);
    }

    @Test
    @DisplayName("test row 67")
    void testRow67() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<6){
                die[i] = "monkey";
            }
            if (i>=6 && i<7){
                die[i] = "sword";
            }

        }
        String[] hold = {"0", "1", "2", "3", "4", "5"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[6] = "monkey";
        die[7] = "monkey";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(4600, final_score);
    }

    @Test
    @DisplayName("test row 68")
    void testRow68() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("diamond");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                die[i] = "monkey";
            }
            if (i>=2 && i<4){
                die[i] = "skull";
            }
            if (i>=4 && i<6){
                die[i] = "sword";
            }
            if (i>=6 && i<8){
                die[i] = "parrots";
            }

        }
        String[] hold = {"0", "1", "2", "3", "4", "5"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[6] = "diamond";
        die[7] = "diamond";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(400, final_score);
    }

    @Test
    @DisplayName("test row 69")
    void testRow69() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<2){
                die[i] = "monkey";
            }
            if (i>=2 && i<4){
                die[i] = "skull";
            }
            if (i>=4 && i<6){
                die[i] = "sword";
            }
            if (i>=6 && i<7){
                die[i] = "diamond";
            }
            if (i>=7 && i<8){
                die[i] = "parrots";
            }
        }
        String[] hold = {"2", "3", "4", "5", "6", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[0] = "diamond";
        die[1] = "diamond";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(500, final_score);
    }

    @Test
    @DisplayName("test row 70")
    void testRow70() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<3){
                die[i] = "coin";
            }
            if (i>=3 && i<4){
                die[i] = "monkey";
            }
            if (i>=4 && i<5){
                die[i] = "parrots";
            }
            if (i>=5 && i<8){
                die[i] = "sword";
            }
        }
        String[] hold = {"0", "1", "2", "3", "4"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[5] = "coin";
        die[6] = "monkey";
        die[7] = "parrots";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(600, final_score);
    }

    @Test
    @DisplayName("test row 71")
    void testRow71() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("diamond");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<3){
                die[i] = "coin";
            }
            if (i>=3 && i<4){
                die[i] = "monkey";
            }
            if (i>=4 && i<5){
                die[i] = "parrots";
            }
            if (i>=5 && i<8){
                die[i] = "sword";
            }
        }
        String[] hold = {"0", "1", "2", "3", "4"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[5] = "coin";
        die[6] = "monkey";
        die[7] = "parrots";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(500, final_score);
    }

    @Test
    @DisplayName("test row 72")
    void testRow72() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dice
            if (i<4){
                die[i] = "monkey";
            }
            if (i>=4 && i<6){
                die[i] = "coin";
            }
            if (i>=6 && i<8){
                die[i] = "skull";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(600, final_score);
    }


    //PART 2
    //sorceress
    @Test
    @DisplayName("test row 77")
    void testRow77() {
        //init
        scannerInput("1"); //take user input
        Player p = new Player("Di");
        game = new PirateGame();
        game.drawForturnCard(p);
        p.setFortuneCard("sorceress");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<2){
                die[i] = "diamond";
            }
            if (i>=2 && i<3){
                die[i] = "sword";
            }
            if (i>=3 && i<4){
                die[i] = "monkey";
            }
            if (i>=4 && i<5){
                die[i] = "coin";
            }
            if (i>=5 && i<8){
                die[i] = "parrots";
            }
        }
        String[] hold = {"0", "1", "2", "3", "4"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[5] = "skull";
        die[6] = "monkey";
        die[7] = "monkey";
        die = game.useSorceress(die, p); //reroll skull
        die[5] = "monkey";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(500, final_score);
    }

    @Test
    @DisplayName("test row 78")
    void testRow78() {
        //init
        scannerInput("1"); //take user input
        Player p = new Player("Di");
        game = new PirateGame();
        game.drawForturnCard(p);
        p.setFortuneCard("sorceress");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "skull";
            }
            if (i>=3 && i<6){
                die[i] = "parrots";
            }
            if (i>=6 && i<8){
                die[i] = "sword";
            }
        }
        die = game.useSorceress(die, p); //reroll skull
        die[0] = "parrots";
        String[] hold = {"0", "3", "4", "5"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[6] = "parrots";
        die[7] = "parrots";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(1000, final_score);
    }

    @Test
    @DisplayName("test row 79")
    void testRow79() {
        //init
        scannerInput("1"); //take user input
        Player p = new Player("Di");
        game = new PirateGame();
        game.drawForturnCard(p);
        p.setFortuneCard("sorceress");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<5){
                die[i] = "parrots";
            }
            if (i>=5 && i<8){
                die[i] = "monkey";
            }
        }
        String[] hold = {"1", "2", "3", "4"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[5] = "skull";
        die[6] = "parrots";
        die[7] = "parrots";
        die = game.useSorceress(die, p); //reroll skull
        die[0] = "parrots";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(2000, final_score);
    }

    //monkey business
    @Test
    @DisplayName("test row 82")
    void testRow82() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("monkey business");
        game.setNewPlayer(p);
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
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(1100, final_score);
    }

    @Test
    @DisplayName("test row 83")
    void testRow83() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("monkey business");
        game.setNewPlayer(p);
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
        String[] hold = {"0", "1", "5", "6", "4", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[2] = "monkey";
        die[3] = "parrots";
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(1700, final_score);
    }

    @Test
    @DisplayName("test row 84")
    void testRow84() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("monkey business");
        game.setNewPlayer(p);
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
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(0, final_score);
    }

    //treasure chest
    @Test
    @DisplayName("test row 87")
    void testRow87() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("treasure chest");
        game.setNewPlayer(p);
        String[] die = new String[8];
        String[] chestIndex = new String[8];

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
        chestIndex = new String[]{"5", "6", "7"};
        String[] hold = {"0", "1", "2"}; //select dice to hold
        die = game.RerollWithChestHold(die, hold, chestIndex); //reroll
        //assign dice
        die[3] = "parrots";
        die[4] = "parrots";
        chestIndex = new String[]{"0", "1", "2", "3", "4"};
        hold = new String[]{}; //select dice to hold
        die = game.RerollWithChestHold(die, hold, chestIndex); //reroll
        die[5] = "skull";
        die[6] = "coin";
        die[7] = "parrots";
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(1100, final_score);
    }

    //treasure chest
    @Test
    @DisplayName("test row 92")
    void testRow92() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("treasure chest");
        game.setNewPlayer(p);
        String[] die = new String[8];
        String[] chestIndex = new String[8];

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
        chestIndex = new String[]{"5", "6", "7"};
        String[] hold = {"0", "1"}; //select dice to hold
        die = game.RerollWithChestHold(die, hold, chestIndex); //reroll
        //assign dice
        die[2] = "diamond";
        die[3] = "diamond";
        die[4] = "coin";
        chestIndex = new String[]{"4", "5", "6", "7"};
        hold = new String[]{"0", "1"}; //select dice to hold
        die = game.RerollWithChestHold(die, hold, chestIndex); //reroll
        die[2] = "skull";
        die[3] = "coin";
        p.setHoldingDie(chestIndex);
        game.scoreChest(p, die);
        int final_score = p.getScore();
        assertEquals(600, final_score, "Player die in this round, now score with the dice in chest");
    }

    //Full Chest tests
    @Test
    @DisplayName("test row 97")
    void testRow97() {
        PirateGame game = new PirateGame();
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){                //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "monkey";
            }
            if (i>=3 && i<6){
                die[i] = "sword";
            }
            if (i == 6){
                die[i] = "diamond";
            }
            if (i == 7){
                die[i] = "parrot";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(400, final_score);
    }

    @Test
    @DisplayName("test row 98")
    void testRow98() {
        PirateGame game = new PirateGame();
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("captain");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){                //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "monkey";
            }
            if (i>=3 && i<6){
                die[i] = "sword";
            }
            if (i == 6 || i == 7){
                die[i] = "coin";
            }
        }
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(1800, final_score);
    }

    @Test
    @DisplayName("test row 99")
    void testRow99() {                                       //row 99
        PirateGame game = new PirateGame();
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("coin");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){                //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<3){
                die[i] = "monkey";
            }
            if (i>=3 && i<7){
                die[i] = "sword";
            }
            if (i == 7){
                die[i] = "diamond";
            }
        }
        int final_score = game.scoreForKindsAndChest(die, p) + game.scoreForDC(die, p);
        assertEquals(1000, final_score);
    }

    @Test
    @DisplayName("test row 100")
    void testRow100() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("2 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<4){
                die[i] = "monkey";
            }
            if (i>=4 && i<5){
                die[i] = "sword";
            }
            if (i>=5 && i<7){
                die[i] = "parrots";
            }
            if (i>=7 && i<8){
                die[i] = "coin";
            }
        }
        String[] hold = {"0", "1", "2", "3", "4", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[5] = "coin";
        die[6] = "sword";
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(1200, final_score);
    }

    @Test
    @DisplayName("test row 103")
    void testRow103() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("monkey business");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<2){
                die[i] = "monkey";
            }
            if (i>=2 && i<3){
                die[i] = "parrots";
            }
            if (i>=3 && i<5){
                die[i] = "coin";
            }
            if (i>=5 && i<8){
                die[i] = "diamond";
            }
        }
        game.calculateScoreForARoundWithCapMonkey(p, die);
        int final_score = p.getScore();
        assertEquals(1200, final_score);
    }


    //skulls island and skull fortune card
    @Test
    @DisplayName("test row 106")
    void testRow106() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("2 skull");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<1){
                die[i] = "skull";
            }
            if (i>=1 && i<8){
                die[i] = "sword";
            }
        }
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
    }

    @Test
    @DisplayName("test row 107")
    void testRow107() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("1 skull");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<2){
                die[i] = "skull";
            }
            if (i>=2 && i<8){
                die[i] = "sword";
            }
        }
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
    }

    @Test
    @DisplayName("test row 108")
    void testRow108() {
        scannerInput("1");
        game = new PirateGame();
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("2 skull");
        game.setNewPlayer(p);
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
                die[i] = "monkey";
            }
        }
        String[] hold = {"0", "1", "5", "6", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[2] = "skull";
        die[3] = "skull";
        die[4] = "sword";
        hold = new String[]{"0", "1", "2", "3"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[4] = "skull";
        die[5] = "skull";
        die[6] = "skull";
        die[7] = "sword";
        int final_num_skull = game.rerollSkullLandAndCountNOSkull(die, p);
        int final_deduct = final_num_skull * 100;
        assertEquals(-900, final_deduct);
        Assertions.assertEquals(0, p.getScore());
    }

    @Test
    @DisplayName("test row 110")
    void testRow110() {
        //init
        scannerInput("1");
        game = new PirateGame();
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("captain");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++){            //assign dies
            if (i<5){
                die[i] = "skull";
            }
            if (i>=5 && i<8){
                die[i] = "monkey";
            }
        }
        String[] hold = new String[]{"0", "1", "2", "3", "4"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[5] = "skull";
        die[6] = "skull";
        die[7] = "coin";
        int final_num_skull = game.rerollSkullLandAndCountNOSkull(die, p);
        int final_deduct = final_num_skull * 100;
        assertEquals(-1400, final_deduct);
        Assertions.assertEquals(0, p.getScore());
    }

    //sea battle
    @Test
    @DisplayName("test row 114")
    void testRow114() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("2 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 4) {
                die[i] = "monkey";
            }
            if (i >= 4 && i < 7) {
                die[i] = "skull";
            }
            if (i >= 7 && i < 8) {
                die[i] = "sword";
            }
        }
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(0, final_score);
    }

    @Test
    @DisplayName("test row 115")
    void testRow115() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("3 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 2) {
                die[i] = "sword";
            }
            if (i >= 2 && i < 4) {
                die[i] = "skull";
            }
            if (i >= 4 && i < 8) {
                die[i] = "parrots";
            }
        }
        String[] hold = new String[]{"0", "1", "2", "3"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[4] = "skull";
        die[5] = "skull";
        die[6] = "skull";
        die[7] = "skull";
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(0, final_score);
    }

    @Test
    @DisplayName("test row 116")
    void testRow116() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("4 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 2) {
                die[i] = "monkey";
            }
            if (i >= 2 && i < 5) {
                die[i] = "skull";
            }
            if (i >= 5 && i < 8) {
                die[i] = "sword";
            }
        }
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(0, final_score);
    }

    @Test
    @DisplayName("test row 117")
    void testRow117() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("2 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die[i] = "monkey";
            }
            if (i >= 3 && i < 5) {
                die[i] = "sword";
            }
            if (i >= 5 && i < 6) {
                die[i] = "coin";
            }
            if (i >= 6 && i < 8) {
                die[i] = "parrots";
            }
        }
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(500, final_score);
    }

    @Test
    @DisplayName("test row 118")
    void testRow118() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("2 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 4) {
                die[i] = "monkey";
            }
            if (i >= 4 && i < 5) {
                die[i] = "sword";
            }
            if (i >= 5 && i < 6) {
                die[i] = "skull";
            }
            if (i >= 6 && i < 8) {
                die[i] = "parrots";
            }
        }
        String[] hold = new String[]{"0", "1", "2", "3", "4", "5"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[6] = "sword";
        die[7] = "skull";
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(500, final_score);
    }

    @Test
    @DisplayName("test row 120")
    void testRow120() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("3 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die[i] = "monkey";
            }
            if (i >= 3 && i < 7) {
                die[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                die[i] = "skull";
            }
        }
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(800, final_score);
    }

    @Test
    @DisplayName("test row 121")
    void testRow121() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("2 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 4) {
                die[i] = "monkey";
            }
            if (i >= 4 && i < 6) {
                die[i] = "sword";
            }
            if (i >= 6 && i < 8) {
                die[i] = "skull";
            }
        }
        String[] hold = new String[]{"4", "5", "6", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[0] = "skull";
        die[1] = "skull";
        die[2] = "sword";
        die[3] = "sword";
        boolean final_state = game.checkIfDie(die, p);
        assertTrue(final_state);
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(0, final_score);
    }

    @Test
    @DisplayName("test row 123")
    void testRow123() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("4 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die[i] = "monkey";
            }
            if (i >= 3 && i < 7) {
                die[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                die[i] = "skull";
            }
        }
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(1300, final_score);
    }

    @Test
    @DisplayName("test row 124")
    void testRow124() {
        //init
        Player p = new Player("Di");
        game.drawForturnCard(p);
        p.setFortuneCard("4 sword");
        game.setNewPlayer(p);
        String[] die = new String[8];

        for (int i=0; i<8; i++){               //roll die
            die[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die[i] = "monkey";
            }
            if (i >= 3 && i < 4) {
                die[i] = "sword";
            }
            if (i >= 4 && i < 5) {
                die[i] = "skull";
            }
            if (i >= 5 && i < 6) {
                die[i] = "diamond";
            }
            if (i >= 6 && i < 8) {
                die[i] = "parrots";
            }
        }
        String[] hold = new String[]{"0", "1", "2", "3", "4", "5"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[6] = "sword";
        die[7] = "sword";
        hold = new String[]{"3", "4", "5", "6", "7"}; //select dice to hold
        die = game.RerollWithHold(die, hold); //reroll
        //assign dice
        die[0] = "sword";
        die[1] = "parrots";
        die[2] = "parrots";
        game.seaBattle(p, die);
        int final_score = p.getScore();
        assertEquals(1300, final_score);
    }


    //multi-player scenarios
    @Test
    @DisplayName("test row 130")
    void testRow130() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("row130.txt");
        //init players
        Player p1 = new Player("name1");
//        PirateGame game = new PirateGame();
        game.drawForturnCard(p1);
        p1.setFortuneCard("captain");
        game.setNewPlayer(p1);
        String[] die1 = new String[8];

        Player p2 = new Player("name2");
//        PirateGame game = new PirateGame();
        game.drawForturnCard(p2);
        p2.setFortuneCard("1 skull");
        game.setNewPlayer(p2);
        String[] die2 = new String[8];

        Player p3 = new Player("name3");
//        PirateGame game = new PirateGame();
        game.drawForturnCard(p3);
        p3.setFortuneCard("coin");
        game.setNewPlayer(p3);
        String[] die3 = new String[8];

        Player[] player_list = new Player[3];
        player_list[0] = p1;
        player_list[1] = p2;
        player_list[2] = p3;

        //player 1 move
        for (int i=0; i<8; i++){               //roll die
            die1[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 7) {
                die1[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                die1[i] = "skull";
            }
        }
        game.calculateScoreForARoundWithCapMonkey(p1, die1);//player1_final_score = p1.getScore()
        Assertions.assertEquals(4000, p1.getScore());
        p1.setCurrentRoll(die1);
        printWriter.println(
                ("Round 1 Started"));
        printWriter.println(
                ("Player1's information is following......" +
                        "name : %s ----- card : %s ----- final current roll : %s ----- score : %d").formatted(p1.getName(), p1.getFortuneCard(), Arrays.toString(p1.getCurrentRoll()) ,p1.getScore())
        );

        //player 2 move
        for (int i=0; i<8; i++){               //roll die
            die2[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 7) {
                die2[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                die2[i] = "skull";
            }
        }
        int player2_final_score = game.scoreForKindsAndChest(die2, p2) + game.scoreForDC(die2, p2);
        p2.setScore(player2_final_score);
        Assertions.assertEquals(2000, p2.getScore());
        p2.setCurrentRoll(die2);
        printWriter.println(
                ("Player2's information is following......" +
                        "name : %s ----- card : %s ----- final current roll : %s ----- score : %d").formatted(p2.getName(), p2.getFortuneCard(), Arrays.toString(p2.getCurrentRoll()) ,p2.getScore())
        );

        //player 3 move
        for (int i=0; i<8; i++){               //roll die
            die3[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die3[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                die3[i] = "monkey";
            }
        }
        int player3_final_score = game.scoreForKindsAndChest(die3, p3) + game.scoreForDC(die3, p3);
        p3.setScore(player3_final_score);
        Assertions.assertEquals(0, p3.getScore());
        p3.setCurrentRoll(die3);
        printWriter.println(
                ("Player3's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p3.getName(), p3.getFortuneCard(), Arrays.toString(p3.getCurrentRoll()) ,p3.getScore())
        );

        Player winner = game.getWinner(player_list);
        String winner_name = winner.getName();
        assertEquals("name1", winner_name);
        printWriter.println(
                ("The winner is %s with %d points!!!").formatted(winner_name, winner.getScore())
        );
        printWriter.close();
    }

    @Test
    @DisplayName("test row 134")
    void testRow134() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("row134.txt");
        scannerInput("0");
        game = new PirateGame();
        //init players
        Player p1 = new Player("name1");
        game.drawForturnCard(p1);
        p1.setFortuneCard("captain");
        game.setNewPlayer(p1);
        String[] die1 = new String[8];

        Player p2 = new Player("name2");
        game.drawForturnCard(p2);
        p2.setFortuneCard("coin");
        game.setNewPlayer(p2);
        String[] die2 = new String[8];

        Player p3 = new Player("name3");
        game.drawForturnCard(p3);
        p3.setFortuneCard("captain");
        game.setNewPlayer(p3);
        String[] die3 = new String[8];

        Player[] player_list = new Player[3];
        player_list[0] = p1;
        player_list[1] = p2;
        player_list[2] = p3;

        //player 1 move
        for (int i=0; i<8; i++){               //roll die
            die1[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 7) {
                die1[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                die1[i] = "skull";
            }
        }
        game.calculateScoreForARoundWithCapMonkey(p1, die1);  //player1_final_score = p1.getScore()
        Assertions.assertEquals(4000, p1.getScore());
        p1.setCurrentRoll(die1);
        printWriter.println(
                ("Round 1 Started"));
        printWriter.println(
                ("Player1's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p1.getName(), p1.getFortuneCard(), Arrays.toString(p1.getCurrentRoll()) ,p1.getScore())
        );

        //player 2 move
        for (int i=0; i<8; i++){               //roll die
            die2[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die2[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                die2[i] = "monkey";
            }
        }
        int player2_final_score = game.scoreForKindsAndChest(die2, p2) + game.scoreForDC(die2, p2);
        p2.setScore(player2_final_score);
        Assertions.assertEquals(0, p2.getScore());
        p2.setCurrentRoll(die2);
        printWriter.println(
                ("Player2's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p2.getName(), p2.getFortuneCard(), Arrays.toString(p2.getCurrentRoll()) ,p2.getScore())
        );

        //player 3 move
        for (int i=0; i<8; i++){               //roll die
            die3[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 6) {
                die3[i] = "skull";
            }
            if (i >= 6 && i < 8) {
                die3[i] = "parrots";
            }
        }
        int player3_final_score = game.rerollSkullLandAndCountNOSkull(die3, p3);
        int player3_final_deduct = player3_final_score * 100;

        p1.setScore(p1.getScore() + player3_final_deduct);
        player2_final_score += player3_final_deduct;
        p2.setScore(player2_final_score);
        p3.setScore(p3.getScore());
        Assertions.assertEquals(0, p3.getScore());
        Assertions.assertEquals(2800, p1.getScore());
        Assertions.assertEquals(0, p2.getScore());
        p3.setCurrentRoll(die3);
        printWriter.println(
                ("Player3's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d" +
                        " Since player 3 was in skull island, he made a deduction %s points to other players.").formatted(p3.getName(), p3.getFortuneCard(), Arrays.toString(p3.getCurrentRoll()) ,p3.getScore(), player3_final_deduct)
        );
        printWriter.println(
                ("After Deduction....." +
                        " Player 1 score is %d " +
                        " Player 2 score is %d " +
                        " Player 3 score is %d ").formatted(p1.getScore(), p2.getScore(), p3.getScore())
        );

        //player 1 move again
        p1.setFortuneCard("coin");
        game.setNewPlayer(p1);
        for (int i=0; i<8; i++){               //roll die
            die1[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 4) {
                die1[i] = "monkey";
            }
            if (i >= 4 && i < 8) {
                die1[i] = "parrots";
            }
        }
        p1.setScore(p1.getScore() + (game.scoreForKindsAndChest(die1, p1) + game.scoreForDC(die1, p1)) );
        Assertions.assertEquals(3800, p1.getScore());
        p1.setCurrentRoll(die1);
        printWriter.println(
                ("Round 2 Started"));
        printWriter.println(
                ("Player1's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p1.getName(), p1.getFortuneCard(), Arrays.toString(p1.getCurrentRoll()) ,p1.getScore())
        );


        //player 2 move again
        p2.setFortuneCard("captain");
        game.setNewPlayer(p2);
        for (int i=0; i<8; i++){               //roll die
            die2[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die2[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                die2[i] = "monkey";
            }
        }
        boolean player2_state = game.checkIfDie(die2, p2);
        assertTrue(player2_state);
        p2.setScore(p2.getScore());
        Assertions.assertEquals(0, p2.getScore());
        p2.setCurrentRoll(die2);
        printWriter.println(
                ("Player2's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p2.getName(), p2.getFortuneCard(), Arrays.toString(p2.getCurrentRoll()) ,p2.getScore())
        );

        //player 3 move again
        p3.setFortuneCard("1 skull");
        game.setNewPlayer(p3);
        for (int i=0; i<8; i++){               //roll die
            die3[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 2) {
                die3[i] = "skull";
            }
            if (i >= 2 && i < 8) {
                die3[i] = "monkey";
            }
        }
        p3.setScore(p3.getScore() + (game.scoreForKindsAndChest(die3, p3) + game.scoreForDC(die3, p3)) );
        Assertions.assertEquals(0, p3.getScore());
        p3.setCurrentRoll(die3);
        printWriter.println(
                ("Player3's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p3.getName(), p3.getFortuneCard(), Arrays.toString(p3.getCurrentRoll()) ,p3.getScore())
        );

        Player winner = game.getWinner(player_list);
        String winner_name = winner.getName();
        assertEquals("name1", winner_name);
        printWriter.println(
                ("The winner is %s with %d points!!!").formatted(winner_name, winner.getScore())
        );
        printWriter.close();
    }

    @Test
    @DisplayName("test row 142")
    void testRow142() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("row142.txt");
        //init players
        Player p1 = new Player("name1");
        game.drawForturnCard(p1);
        p1.setFortuneCard("captain");
        game.setNewPlayer(p1);
        String[] die1 = new String[8];

        Player p2 = new Player("name2");
        game.drawForturnCard(p2);
        p2.setFortuneCard("captain");
        game.setNewPlayer(p2);
        String[] die2 = new String[8];

        Player p3 = new Player("name3");
        game.drawForturnCard(p3);
        p3.setFortuneCard("2 skull");
        game.setNewPlayer(p3);
        String[] die3 = new String[8];

        Player[] player_list = new Player[3];
        player_list[0] = p1;
        player_list[1] = p2;
        player_list[2] = p3;

        //player 1 move
        for (int i=0; i<8; i++){               //roll die
            die1[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 3) {
                die1[i] = "skull";
            }
            if (i >= 3 && i < 8) {
                die1[i] = "monkey";
            }
        }
        game.calculateScoreForARoundWithCapMonkey(p1, die1);  //player1_final_score = p1.getScore()
        int player1_final_score = p1.getScore();
        assertEquals(0, player1_final_score);
        p1.setCurrentRoll(die1);
        printWriter.println(
                ("Round 1 Started"));
        printWriter.println(
                ("Player1's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p1.getName(), p1.getFortuneCard(), Arrays.toString(p1.getCurrentRoll()) ,p1.getScore())
        );

        //player 2 move
        for (int i=0; i<8; i++){               //roll die
            die2[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 7) {
                die2[i] = "sword";
            }
            if (i >= 7 && i < 8) {
                die2[i] = "skull";
            }
        }
        game.calculateScoreForARoundWithCapMonkey(p2, die2);
        Assertions.assertEquals(4000, p2.getScore());
        p2.setCurrentRoll(die2);
        printWriter.println(
                ("Player2's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p2.getName(), p2.getFortuneCard(), Arrays.toString(p2.getCurrentRoll()) ,p2.getScore())
        );

        //player 3 move
        for (int i=0; i<8; i++){               //roll die
            die3[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 1) {
                die3[i] = "skull";
            }
            if (i >= 1 && i < 8) {
                die3[i] = "swords";
            }
        }
        int player3_final_score = game.scoreForKindsAndChest(die3, p3) + game.scoreForDC(die3, p3);
        p3.setScore(player3_final_score);
        Assertions.assertEquals(0, p3.getScore());
        p3.setCurrentRoll(die3);
        printWriter.println(
                ("Player3's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p3.getName(), p3.getFortuneCard(), Arrays.toString(p3.getCurrentRoll()) ,p3.getScore())
        );

        //player 1 move again
        p1.setFortuneCard("captain");
        game.setNewPlayer(p1);
        for (int i=0; i<8; i++){               //roll die
            die1[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            die1[i] = "sword";
        }
        game.calculateScoreForARoundWithCapMonkey(p1, die1);
        Assertions.assertEquals(9000, p1.getScore());
        p1.setCurrentRoll(die1);
        printWriter.println(
                ("Round 2 Started"));
        printWriter.println(
                ("Player1's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p1.getName(), p1.getFortuneCard(), Arrays.toString(p1.getCurrentRoll()) ,p1.getScore())
        );


        Player winner = game.getWinner(player_list);
        String winner_name = winner.getName();
        assertEquals("name1", winner_name);
        printWriter.println(
                ("The winner is %s with %d points!!!").formatted(winner_name, winner.getScore())
        );
        printWriter.close();
    }

    @Test
    @DisplayName("test row 147")
    void testRow147() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("row147.txt");
        //init players
        scannerInput("1");
        game = new PirateGame();
        Player p1 = new Player("name1");
        game.drawForturnCard(p1);
        p1.setFortuneCard("coin");
        game.setNewPlayer(p1);
        String[] die1 = new String[8];


        Player p2 = new Player("name2");
        scannerInput("1");
        scannerInput("1");
        game.drawForturnCard(p2);
        p2.setFortuneCard("sorceress");
        game.setNewPlayer(p2);
        PirateGame game2 = new PirateGame();
        game2.setNewPlayer(p2);
        String[] die2 = new String[8];

        Player p3 = new Player("name3");
        game.drawForturnCard(p3);
        p3.setFortuneCard("2 skull");
        game.setNewPlayer(p3);
        String[] die3 = new String[8];

        Player[] player_list = new Player[3];
        player_list[0] = p1;
        player_list[1] = p2;
        player_list[2] = p3;

        //player 1 move
        for (int i=0; i<8; i++){               //roll die
            die1[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 6) {
                die1[i] = "sword";
            }
            if (i >= 6 && i < 8) {
                die1[i] = "skull";
            }
        }
        int player1_final_score = game.scoreForKindsAndChest(die1, p1) + game.scoreForDC(die1, p1);
        p1.setScore(player1_final_score);
        Assertions.assertEquals(1100, p1.getScore());
        p1.setCurrentRoll(die1);
        printWriter.println(
                ("Round 1 Started"));
        printWriter.println(
                ("Player1's information is following......" +
                        "name : %s ----- card : %s ----- current roll : %s ----- score : %d").formatted(p1.getName(), p1.getFortuneCard(), Arrays.toString(p1.getCurrentRoll()) ,p1.getScore())
        );

        //player 2 move
        for (int i=0; i<8; i++){               //roll die
            die2[i] = game.rollSingleDie();
        }
        for (int i=0; i<8; i++) {            //assign dies
            if (i < 7) {
                die2[i] = "skull";
            }
            if (i >= 7 && i < 8) {
                die2[i] = "coin";
            }
        }
        String[] beforeSorceress = die2;
        String[] afterSorceress = game.useSorceress(die2, p2);;
        String[] hold = {"1", "2", "3", "4", "5", "6"}; //select dice to hold
        die2 = game.RerollWithHold(die2, hold); //reroll
        //assign dice
        die2[0] = "skull";
        die2[7] = "skull";

        int player2NumOfSkull = game2.rerollSkullLandAndCountNOSkull(die2, p2);
        int player2Deduct = player2NumOfSkull * 100;
        p1.setScore(p1.getScore() + player2Deduct);
        p2.setScore(p2.getScore());
        p3.setScore(p3.getScore() + player2Deduct);
        Assertions.assertEquals(0, p2.getScore());
        Assertions.assertEquals(0, p3.getScore());
        p3.setCurrentRoll(die3);
        printWriter.println(
                ("Player2's information is following......" +
                        "name : %s ----- card : %s ----- final current roll : %s ( before use sorceress card is %s ) ----- score : %d" +
                        " Since player 2 was in skull island, he made a deduction %s points to other players.").formatted(p2.getName(), p2.getFortuneCard(), Arrays.toString(afterSorceress), Arrays.toString(beforeSorceress) ,p2.getScore(), player2Deduct)
        );
        printWriter.println(
                ("After Deduction....." +
                        " Player 1 score is %d " +
                        " Player 2 score is %d " +
                        " Player 3 score is %d ").formatted(p1.getScore(), p2.getScore(), p3.getScore())
        );


        Player winner = game.getWinner(player_list);
        String winner_name = winner.getName();
        assertEquals("name1", winner_name);
        printWriter.println(
                ("The winner is %s with %d points!!!").formatted(winner_name, winner.getScore())
        );
        printWriter.close();
    }

}
