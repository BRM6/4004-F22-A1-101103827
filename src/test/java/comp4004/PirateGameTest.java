package comp4004;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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


}
