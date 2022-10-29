package comp4004;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread implements Serializable {
    private static final long serialVersionUID = 1L;

    public boolean isAcceptingConnections;

    Server[] playerServer = new Server[3];
    Player[] players = new Player[3];

    ServerSocket ss;
    PirateGame game = new PirateGame();
    int netPort;
    int numOfPlayers;
    public int rounds = 0;

    //constructor
    public GameServer(){
        System.out.println("STARTING GAME SERVER");
        numOfPlayers = 0;
        netPort = 5000;
        for (int i = 0; i<3; i++){
            players[i] = new Player(" ");
        }
        try {
            ss = new ServerSocket(5000);
        } catch (IOException e) {
            System.out.println("SERVER FAILED OT OPEN");
        }
    }

    /////////
    @Override
    public void run(){
        try{
            acceptConnections();
            gameLoop();
            ss.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("FAILED TO CLOSE SERVER SOCKET");
        }

        System.out.println("CLOSING SERVER SOCKET");
    }

    public int deductPoint(int numOfSkull){
        return numOfSkull*100;
    }


    public void gameLoop(){
        try{
            playerServer[0].sendPlayer(players);
            playerServer[1].sendPlayer(players);
            playerServer[2].sendPlayer(players);

            //recording of players deduction point
            int player0Deduct = 0;
            int player1Deduct = 0;
            int player2Deduct = 0;



            while(true){

                rounds++;

                //players playing
                System.out.println("ROUND " + rounds);

                //player 0 is playing
                playerServer[0].sendRoundNo(rounds);
                playerServer[0].sendScore(players);
                int player0Receive = playerServer[0].receiveScore();
                System.out.println("Player 0 received score is : " + player0Receive);
                System.out.println();
                //if player 0 received num of skull, which is below 0, player 0 will deduct points from player 1 2
                if (player0Receive < 0){
                    player1Deduct = deductPoint(player0Receive);
                    player2Deduct = deductPoint(player0Receive);
                    System.out.println("PLAYER 0 AT SKULL ISLAND DEDUCT " + deductPoint(player0Receive) + "FROM PLAYER 1 AND PLAYER 2");

                    System.out.println("NOW CHECKING IF PLAYER 1 AND PLAYER 2 POINTS BELOW 0, IF ITS, SET TO 0");
                    // checking if player 1 and player 2 score is below 0, if it is, set to 0
                    if (players[1].getScore() < Math.abs(deductPoint(player0Receive))){
                        player1Deduct = (players[1].getScore() * -1);
                    }
                    if (players[2].getScore() < Math.abs(deductPoint(player0Receive))){
                        player2Deduct = (players[2].getScore() * -1);
                    }
                    players[0].setScore(players[0].getScore());
                    players[1].setScore(players[1].getScore() + player1Deduct);
                    players[2].setScore(players[2].getScore() + player2Deduct);
                    player1Deduct = 0; // reset player 1 deduct to 0
                    player2Deduct = 0; // reset player 2 deduct to 0
                }
                else{
                    players[0].setScore(player0Receive + player0Deduct);
                    player0Deduct = 0; // rest player 0's deduct to 0
                }

                if (players[0].getScore() >= 3000){
                    break;
                }

                //player 1 is playing
                playerServer[1].sendRoundNo(rounds);
                playerServer[1].sendScore(players);
                int player1Receive = playerServer[1].receiveScore();
                System.out.println("Player 1 received score is : " + player1Receive);
                System.out.println();
                //if player 1 received num of skull, which is below 0, player 1 will deduct points from player 0 2
                if (player1Receive < 0){
                    player0Deduct = deductPoint(player1Receive);
                    player2Deduct = deductPoint(player1Receive);
                    System.out.println("PLAYER 1 AT SKULL ISLAND DEDUCT " + deductPoint(player1Receive) + " FROM PLAYER 0 AND PLAYER 2");
                    System.out.println("NOW CHECKING IF PLAYER 0 AND PLAYER 2 POINTS BELOW 0, IF ITS, SET TO 0");
                    // checking if player 0 and player 2 score is below 0, if it is, set to 0
                    if (players[0].getScore() < Math.abs(deductPoint(player1Receive))){
                        player0Deduct = (players[0].getScore() * -1);
                    }
                    if (players[2].getScore() < Math.abs(deductPoint(player1Receive))){
                        player2Deduct = (players[2].getScore() * -1);
                    }
                    players[0].setScore(players[0].getScore() + player0Deduct);
                    players[1].setScore(players[1].getScore());
                    players[2].setScore(players[2].getScore() + player2Deduct);
                    player0Deduct = 0; // reset player 0 deduct to 0
                    player2Deduct = 0; // reset player 2 deduct to 0
                }
                else{
                    players[1].setScore(player1Receive + player1Deduct);
                    player1Deduct = 0; // reset player 1's deduct to 0
                }

                if (players[1].getScore() >= 3000){
                    break;
                }

                //player 2 is playing
                playerServer[2].sendRoundNo(rounds);
                playerServer[2].sendScore(players);
                int player2Receive = playerServer[2].receiveScore();
                System.out.println("Player 2 received score is : " + player2Receive);
                System.out.println();
                //if player 2 received num of skull, which is below 0, player 2 will deduct points from player 0 1
                if (player2Receive < 0){
                    player0Deduct = deductPoint(player2Receive);
                    player1Deduct = deductPoint(player2Receive);
                    System.out.println("PLAYER 2 AT SKULL ISLAND DEDUCT " + deductPoint(player2Receive) + "FROM PLAYER 0 AND PLAYER 1");

                    System.out.println("NOW CHECKING IF PLAYER 0 AND PLAYER 1 POINTS BELOW 0, IF ITS, SET TO 0");
                    // checking if player 0 and player 1 score is below 0, if it is, set to 0
                    if (players[0].getScore() < Math.abs(deductPoint(player2Receive))){
                        player0Deduct = (players[0].getScore() * -1);
                    }
                    if (players[1].getScore() < Math.abs(deductPoint(player2Receive))){
                        player1Deduct = (players[1].getScore() * -1);
                    }
                    players[0].setScore(players[0].getScore() + player0Deduct);
                    players[1].setScore(players[1].getScore() + player1Deduct);
                    players[2].setScore(players[2].getScore());
                    player0Deduct = 0; // reset player 0 deduct to 0
                    player1Deduct = 0; // reset player 1 deduct to 0
                }
                else{
                    players[2].setScore(player2Receive + player2Deduct);
                    player2Deduct = 0; // rest player 1's deduct to 0
                }

                if (players[2].getScore() >= 3000){
                    break;
                }
            }

            //winner already generated
            playerServer[0].sendRoundNo(-1);
            playerServer[1].sendRoundNo(-1);
            playerServer[2].sendRoundNo(-1);

            playerServer[0].sendScore(players);
            playerServer[1].sendScore(players);
            playerServer[2].sendScore(players);

            //*******************generate winner need to fill up

            Player p = game.getWinner(players);
            System.out.println("The winner is " + p.getName());
            for (int i = 0; i < playerServer.length; i++) {
                playerServer[i].dOut.writeObject(p);
                playerServer[i].dOut.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void acceptConnections() throws ClassNotFoundException {
        try{
            System.out.println("WAITING FOR PLAYERS TO JOIN...");
            while (numOfPlayers < 3){
                isAcceptingConnections = true;
                Socket s = ss.accept();
                numOfPlayers++;
                System.out.println(numOfPlayers);

                Server server = new Server(s, numOfPlayers);

                //send player number
                server.dOut.writeInt(server.playerId); //player id 1 2 3
                server.dOut.flush();

                //get player name
                Player playerIn = (Player) server.dIn.readObject();
                System.out.println("PLAYER " + playerIn.getName() + " HAS JOINED");
                players[server.playerId -1] = playerIn;
                playerServer[numOfPlayers - 1] = server;
            }
            System.out.println("THREE PLAYERS HAS JOINED NOW STARTING THE GAME!");

            for (int i = 0; i < playerServer.length; i++) {
                Thread t = new Thread(playerServer[i]);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isAcceptingConnections = false;
    }

    //server class
    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        private int playerId;

        //constructor for server
        public Server(Socket s, int id){
            socket = s;
            playerId = id;
            try {
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                System.out.println("SERVER CONNECTION FAILED");
            }
        }

        public void run(){
            try{
                while(true){}
            }catch (Exception ex){
                System.out.println("FAILED TO RUN");
                ex.printStackTrace();
            }
        }

        public void sendPlayer(Player[] pls){
            try{
                for(Player p: pls){
                    dOut.writeObject(p);
                    dOut.flush();
                }
            } catch (IOException e) {
                System.out.println("FAILED TO SEND PLAYER");
                e.printStackTrace();
            }
        }

        public void sendRoundNo(int r){
            try{
                dOut.writeInt(r);
                dOut.flush();
            } catch (IOException e) {
                System.out.println("FAILED TO SEND ROUND");
                e.printStackTrace();
            }
        }

        public void sendScore(Player[] pls){
            try{
                for(int i = 0; i < 3; i++){
                    dOut.writeInt(pls[i].getScore());
                }
                dOut.flush();
            } catch (IOException e) {
                System.out.println("FAILED TO SEND SCORE");
                e.printStackTrace();
            }
        }

        public int receiveScore(){
            try{
//                for(int i = 0; i < 3; i++){
                int sc = dIn.readInt();
                return sc;
//                }
            } catch (IOException e) {
                System.out.println("DID NOT RECEIVE SCORE");
                e.printStackTrace();
            }
            return 0;
        }

    }
}