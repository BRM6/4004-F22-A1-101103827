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