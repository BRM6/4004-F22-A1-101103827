package comp4004;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
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
        return false;
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
