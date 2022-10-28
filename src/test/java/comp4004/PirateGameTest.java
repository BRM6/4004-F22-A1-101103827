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


}
