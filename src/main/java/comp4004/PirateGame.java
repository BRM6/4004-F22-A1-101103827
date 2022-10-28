package comp4004;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PirateGame implements Serializable {
    private static final long serialVersionUID = 1L;

    public Player player;
    private Player[] players;
    private int playerId = 0;
    public String[] current_roll = new String[8];
    public String[] holding_roll = new String[8];
    private Scanner scanner = new Scanner(System.in);
    private String[] diePattern = {"parrots", "monkey", "coin", "diamond", "sword", "skull"};
    static Client clientConnection;

    public void setNewPlayer(Player p){player = p;}
    public void emptyCurrentRoll(){current_roll = new String[8];}
    public void emptyHoldingRoll(){holding_roll = new String[8];}

    ////////////////////////////////
    public Player getWinner(Player[] pl) {
        Player winner = pl[0];
        for (int i=0; i<pl.length; i++){
            if (pl[i].getScore() >= winner.getScore()){
                winner = pl[i];
            }
        }
        return winner;
    }

    public boolean checkIfDie(String[] r, Player player){ //checking current roll if died, if died, return true; if not die return false
        int count = 0;
        //add fortune card skull
        if (player.getFortuneCard() == "1 skull"){
            count = 1;
        }
        if (player.getFortuneCard() == "2 skull"){
            count = 2;
        }

        for (int i = 0; i<r.length; i++){
            if (r[i] == "skull"){
                count++;
            }
        }

        if (count == 3){
            return true;
        }
        if ( (player.getFortuneCard() == "2 sword" || player.getFortuneCard() == "3 sword" || player.getFortuneCard() == "4 sword") && count >= 3){
            return true;
        }
        return false;
    }

    public String rollSingleDie(){
        Random rand = new Random();
        int index = rand.nextInt(6);
        return diePattern[index];
    }

    public void drawForturnCard(Player p){
        int cardIndex = (int) (Math.random() * 34);
        if (cardIndex < 4){
            p.setFortuneCard("treasure chest");
        } else if (cardIndex >= 4 && cardIndex < 8) {
            p.setFortuneCard("sorceress");
        } else if (cardIndex>=8 && cardIndex<12) {
            p.setFortuneCard("captain");
        } else if (cardIndex>=12 && cardIndex<16) {
            p.setFortuneCard("monkey business");
        } else if (cardIndex>=16 && cardIndex<20) {
            p.setFortuneCard("diamond");
        } else if (cardIndex>=20 && cardIndex<24) {
            p.setFortuneCard("coin");
        } else if (cardIndex>=24 && cardIndex<26){
            p.setFortuneCard("2 skull");
        } else if (cardIndex>=26 && cardIndex<29) {
            p.setFortuneCard("1 skull");
        } else if (cardIndex>=29 && cardIndex<31) {
            p.setFortuneCard("2 sword");
        } else if (cardIndex>=31 && cardIndex<33) {
            p.setFortuneCard("3 sword");
        } else if (cardIndex>=33 && cardIndex<35) {
            p.setFortuneCard("4 sword");
        }
    }

    public String[] useSorceress(String[] r, Player player){
        String[] buffer = Arrays.copyOf(r, 8);
        //if skull exist will ask use if user want to use sorceress
        if (player.getFortuneCard() == "sorceress"){
            System.out.println("Enter number 1 to use sorceress, Enter number 0 to not use sorceress");
            String ifSorceress = scanner.nextLine();
            if (ifSorceress.matches("-?\\d+") && (Integer.parseInt(ifSorceress) == 1)){
                for (int i=0; i<8; i++){
                    if (r[i].equals("skull")){
                        buffer[i] = rollSingleDie();// rerolled a skull
                        player.setFortuneCard("not sorceress");//sorceress only can use once
                        break;
                    }
                }
                System.out.println("AFTER PLAYER USING SORCERESS NOW ROLL IS : " + Arrays.toString(buffer));
            }
        }
        return buffer;
    }

    public int scoreForDC(String[] r, Player player){
        boolean isDie = checkIfDie(r, player);
        if (isDie){
            return 0;
        }
        int player_score = 0;

        //calculate value for player card
        if (player.getFortuneCard() == "coin" || player.getFortuneCard() == "diamond") {
            player_score += 100;
        }

        for (String i : r){
            if (i != null && (i == "coin" || i == "diamond") ){
                player_score += 100;
            }
        }
        return player_score;
    }







    public void connectToClient(){
        clientConnection = new Client();
    }

    private class Client{
        Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        public Client(){
            try{
                socket = new Socket("localhost", 5000); //game server port 5000
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                playerId = dIn.readInt();
                System.out.println("CONNECTED AS ID " + playerId);
                sendPlayer();

            } catch (IOException e) {
                System.out.println("FAILED TO CONNECT WITH CLIENT");
            }
        }

        public void sendPlayer(){
            try{
                dOut.writeObject(player);
                dOut.flush();
            } catch (IOException ex){
                System.out.println("FAILED TO SEND PLAYER");
                ex.printStackTrace();
            }
        }

        public void sendScore(int s){
            try{
                dOut.writeInt(s);
                dOut.flush();
            } catch (IOException e){
                System.out.println("FAILED TO SEND SCORE TO SERVER");
                e.printStackTrace();
            }
        }

        public Player[] receivePlayerInfo(){
            Player[] players = new Player[3];
            try{
                Player p = (Player) dIn.readObject();
                players[0] = p;
                p = (Player) dIn.readObject();
                players[1] = p;
                p = (Player) dIn.readObject();
                players[2] = p;
                return players;
            } catch (IOException e){
                System.out.println("FAILED TO RECEIVE PLAYER INFO");
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                System.out.println("CLASS NOT FOUND");
                e.printStackTrace();
            }
            return players;
        }

        public int[] receivedScore(){
            try{
                int[] score = new int[3];
                for (int i=0; i<3; i++){
                    score[i] = dIn.readInt();
                }
                return score;
            } catch (Exception e) {
                System.out.println("FAILED TO RECEIVE SCORE");
                e.printStackTrace();
            }
            return null;
        }


        public int receiveRound(){
            try{
                return dIn.readInt();
            } catch (IOException e) {
                System.out.println("FAILED TO RECEIVE ROUND NUMBER");
                e.printStackTrace();
            }
            return 0;
        }

    }
}
