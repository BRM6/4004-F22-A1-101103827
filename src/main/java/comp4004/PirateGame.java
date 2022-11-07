package comp4004;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class PirateGame implements Serializable {
    private static final long serialVersionUID = 1L;

    public Player player;
    private Player[] players;
    private int playerId = 0;
    public boolean isGoing;
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
            System.out.println("PLAYER DIE, END OF TURN");
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

    public int scoreForKindsAndChest(String[] r, Player player){
        boolean isDie = checkIfDie(r, player);
        if (isDie){
            System.out.println("PLAYER DIE, END OF TURN");
            return 0;
        }
        int scoreKind = 0;

        // put value in hash map
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //count fortune card while calculate value of kind
        if (player.getFortuneCard() == "coin"){
            map.put("coin", 1);
        } else if (player.getFortuneCard() == "diamond") {
            map.put("diamond", 1);
        }

        for (String i : r){
            if (i != null){
                if (map.containsKey(i)){
                    map.put(i, (map.get(i) + 1) );
                }
                else {
                    map.put(i, 1);
                }
            }
        }

        //score for kinds
        for (String key : map.keySet()){
            if (key != "skull" && key != null) {
                if (map.get(key) == 3){
                    scoreKind += 100;
                }
                if (map.get(key) == 4){
                    scoreKind += 200;
                }
                if (map.get(key) == 5){
                    scoreKind += 500;
                }
                if (map.get(key) == 6){
                    scoreKind += 1000;
                }
                if (map.get(key) == 7){
                    scoreKind += 2000;
                }
                if (map.get(key) >= 8){
                    scoreKind += 4000;
                }

            }
        }


        //score for chest
        if (map.containsKey("skull")){ //if there is skull
            return scoreKind;
        }
        boolean isChest = true;
        for (String pat : map.keySet()){
            if (map.get(pat) < 3){
                if (pat == "coin" || pat == "diamond"){
                    continue;
                }else {
                    if (pat == "sword"){
                        if (map.get(pat) == 2 && player.getFortuneCard() == "2 sword"){
                            continue;
                        }
                        else {
                            isChest = false;
                        }
                    }
                    else {
                        isChest = false;
                    }
                }
            }
        }
        int count = 0;
        for (int i : map.values()){
            count += i;
        }
        if (isChest && count >= 8){
            scoreKind += 500;
        }

        return scoreKind;
    }

    public void seaBattle(Player p, String[] current){
        int player_score = 0;
        int numOfSword = 0;
        int numOfSkull = 0;

        for (String die : current){
            if (die == "sword"){
                numOfSword++;
            }
            if (die == "skull"){
                numOfSkull++;
            }
        }

        if (numOfSkull < 3){
            int player_score_kind = scoreForKindsAndChest(current, p);
            int player_score_DC = scoreForDC(current, p);
            player_score = player_score_kind + player_score_DC;
            System.out.println("Player's score before sea battle = " + player_score);
            p.setScore(player_score);

            if (p.getFortuneCard() == "2 sword"){
                current = useSorceress(current, player);
                if ( numOfSword >= 2 && !(checkIfDie(current, player)) ){
                    p.setScore(p.getScore() + 300);
                    System.out.println("PLAYER WON SEA BATTLE");
                    System.out.println("Player's score after applied sea battle " + p.getScore());
                }
                else{
                    p.setScore(p.getScore() - 300);
                    System.out.println("PLAYER LOST SEA BATTLE");
                    System.out.println("Player's score after applied sea battle " + p.getScore());
                }
            }
            if (p.getFortuneCard() == "3 sword"){
                current = useSorceress(current, player);
                if ( numOfSword >= 3 && !(checkIfDie(current, player)) ){
                    p.setScore(p.getScore() + 500);
                    System.out.println("PLAYER WON SEA BATTLE");
                    System.out.println("Player's score after applied sea battle " + p.getScore());
                }
                else{
                    p.setScore(p.getScore() - 500);
                    System.out.println("PLAYER LOST SEA BATTLE");
                    System.out.println("Player's score after applied sea battle " + p.getScore());
                }
            }
            if (p.getFortuneCard() == "4 sword"){
                current = useSorceress(current, player);
                if ( numOfSword >= 4 && !(checkIfDie(current, player)) ){
                    p.setScore(p.getScore() + 1000);
                    System.out.println("PLAYER WON SEA BATTLE");
                    System.out.println("Player's score after applied sea battle " + p.getScore());
                }
                else{
                    p.setScore(p.getScore() - 1000);
                    System.out.println("PLAYER LOST SEA BATTLE");
                    System.out.println("Player's score after applied sea battle " + p.getScore());
                }
            }

//        player score cant go below 0
            if (p.getScore() < 0){
                p.setScore(0);
            }
        }else{
            System.out.println("Your are in Sea battle and you can not go to skull island, since you have 3 or more than 3 skulls, your round end");
            p.setScore(p.getScore());
        }
    }
    public void calculateScoreForARoundWithCapMonkey(Player p, String[] current){
        if (p.getFortuneCard() == "captain"){
            scoreWhenCap(p, current);
        } else if (p.getFortuneCard() == "monkey business") {
            scoreForMonkeyB(p, current);
        }
    }

    public void scoreWhenCap(Player player, String[] r){
        int score = 0;
        score = scoreForKindsAndChest(r, player) +  scoreForDC(r, player);
        player.setScore(player.getScore() + (score * 2));
        System.out.println("Player card is Captain, current round score hsa been multiplied by 2");
        System.out.println("Player final score for this round is: " + (score*2));
    }

    public void scoreForMonkeyB(Player player, String[] r){
        int score = 0;
        for (int i = 0; i<8; i++){ //change parrot and monkey to be same
            if (r[i] == "parrots"){
                r[i] = "monkey";
                System.out.println("changed one parrots");
                System.out.println();
            }
        }
        System.out.println("After changing parrot to monkey, new roll is : " + Arrays.toString(r));
        score = scoreForKindsAndChest(r, player) + scoreForDC(r, player);
        player.setScore(player.getScore() + (score));
        System.out.println("Player card is Monkey Business, current round parrot and monkey will be the same");
        System.out.println("Player final score for this round is: " + score);
    }

    public String[] reRollWithoutHold(String[] current){
        String[] buffer = new String[8];
        for (int i = 0; i<8; i++){
            if (current[i] == "skull"){ //hold skull
                buffer[i] = "skull";
            }
        }
        for (int i = 0; i<8; i++){
            if (buffer[i] == null){ //re-roll except for skull
                buffer[i] = rollSingleDie();
            }
        }
        return buffer;
    }

    public String[] RerollWithHold(String[] current, String[] holding){
        String[] buffer = new String[8];
        int count = 0;
        if (holding.length < 7){
            for (int i = 0; i<8; i++){
                if (current[i] == "skull"){ //hold skull
                    buffer[i] = "skull";
                }
            }
            for (int i = 0; i< holding.length; i++ ){ //hold holding_dice
                buffer[Integer.parseInt(holding[i])] = current[Integer.parseInt(holding[i])];
            }
            for (int i = 0; i<8; i++){ //count # of re-roll die
                if (buffer[i] == null){
                    count++;
                }
            }
            if (count >= 2){
                for (int i = 0; i<8; i++){ //re-roll
                    if (buffer[i] == null){
                        buffer[i] = rollSingleDie();
                    }
                }
            }else {
                System.out.println("You have to re-roll at least two dice together");
                return current;
            }
        }else {
            return current;
        }

        return buffer;
    }

    public String[] RerollWithChestHold(String[] current,String[] hold ,String[] chest){
        String[] buffer = new String[8];
        int count = 0;

        for (int i=0; i<chest.length; i++){ //add dice in chest in after_roll
            if (current[Integer.parseInt(chest[i])] != null){
                buffer[Integer.parseInt(chest[i])] = current[Integer.parseInt(chest[i])];
            }
        }
        for (int i=0; i<hold.length; i++){ //add dice in hold in after_roll
            if (buffer[i] != "skull"){
                buffer[Integer.parseInt(hold[i])] = current[Integer.parseInt(hold[i])];
            }
        }
        for (int i = 0; i<8; i++){ //count # of re-roll die
            if (buffer[i] == null){
                count++;
            }
        }
        if (count >=2){
            for (int i = 0; i<8; i++){ //re-roll
                if (buffer[i] == null){
                    buffer[i] = rollSingleDie();
                }
            }
        }else{
            return current;
        }

        return buffer;
    }

    public void scoreChest(Player p, String[] current){
        System.out.println("Player die, value in the chest has been added to player's final score, and player turn ended");
        String[] buffer = new String[8];
        for (int i = 0; i< p.getHoldingDie().length; i++ ){ //add dice in chest into buffer
            if (p.getHoldingDie()[i] != null){
                buffer[Integer.parseInt(p.getHoldingDie()[i])] = current[Integer.parseInt(p.getHoldingDie()[i])];
            }
        }
        int scoreChest = scoreForKindsAndChest(buffer, p) + scoreForDC(buffer, p);
        System.out.println(scoreForKindsAndChest(p.getHoldingDie(), p));
        System.out.println(scoreForDC(p.getHoldingDie(), p));
        p.setScore(p.getScore() + scoreChest);
    }

    public int rerollSkullLandAndCountNOSkull(String [] r, Player player){ // return # of skull in total after skull island
        int count = 0;
        boolean continueThrow;
        System.out.println("PLAYER IS IN SKULL ISLAND");
        System.out.println("Enter 1 to re-roll for skull island, Enter 0 to end your turn");
        String ifSkullLand = scanner.nextLine();
        if (ifSkullLand.matches("-?\\d+") && (Integer.parseInt(ifSkullLand) == 1)){
            continueThrow = true;
        }else{
            continueThrow = false;
        }
        int numOfSkull = 0;
        for (int i=0; i<8; i++){
            if (r[i] == "skull"){
                numOfSkull++;
            }
        }

        while (continueThrow){ //re-roll step for skull island

            if (numOfSkull > 6){
                break;
            }else {
                continueThrow = false;
                for (int i=0; i<8; i++){
                    if (r[i] != "skull"){
                        r[i] = rollSingleDie();
                        if (r[i] == "skull"){
                            numOfSkull++;
                            continueThrow = true;
                        }
                    }
                }
                System.out.println("Player card is : " + player.getFortuneCard() + " Player current roll is : " + Arrays.toString(r));
                if (numOfSkull == 8){
                    continueThrow = false;
                }
            }
        }

        //after all, recount # of skull
        if (player.getFortuneCard() == "1 skull"){
            numOfSkull += 1;
        }
        if (player.getFortuneCard() == "2 skull"){
            numOfSkull += 2;
        }
        if (player.getFortuneCard() == "captain"){
            numOfSkull = numOfSkull * 2;
        }

        return numOfSkull * -1;
    }

    public boolean isSkullLand(String[] r){
        int numOfSkull = 0;
        if (player.getFortuneCard() == "1 skull"){
            numOfSkull += 1;
        }
        if (player.getFortuneCard() == "2 skull"){
            numOfSkull += 2;
        }

        for (int i=0; i<8; i++){
            if(r[i] != null && r[i] == "skull"){
                numOfSkull += 1;
            }
        }

        if (numOfSkull > 3){
            return true;
        }
        return false;
    }

    public void showSelections(){
        System.out.println("Enter a number (1-4) to make decision");
        System.out.println("1 : Start to re-roll");
        System.out.println("2 : End the current round and score");
        if (player.getFortuneCard() == "treasure chest"){
            System.out.println("3 : Put some dice in chest and re-roll");
        }
    }

    public void startGame(){
        players = clientConnection.receivePlayerInfo();
        while(true){
            int round = clientConnection.receiveRound();
            int[] pl = clientConnection.receivedScore();
            // if game ends
            if (round == -1){
                pl = clientConnection.receivedScore();
                for (int i = 0; i < 3; i++) {
                    players[i].setScore(pl[i]);
                }
                for (Player p : players) {
                    if (p.getScore() >= 3000)
                        System.out.println("Game over!!!\n" + p.getName() + " wins the game!");
                }
                break;
            }

            System.out.println("******************Round " + round + "********");
            for (int i = 0; i < 3; i++){
                //set final score for each player
                players[i].setScore(pl[i]);
            }

            //checking
            printPlayerScore(players);
            drawForturnCard(player);
            player.setHoldingDie(player.emptyHoldingDie()); //empty player holding die from previous round
            clientConnection.sendScore(playARound(player)); //player play a round and then send player score to server
            player.nextRound();
        }
    }

    public void printPlayerScore(Player[] pls){
        for (Player pl : pls) {
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("| Player : " + pl.getName());
            System.out.println("| Total Score : " + pl.getScore());
            System.out.println("|----------------------------------------------------------------|");
        }
    }

    public int playARound(Player player){ //actual game loop with game logic
        //init some values
        int numOfSkull = 0;
        isGoing = true;
        player.emptyHoldingDie();

        // first time roll all dice for player
        for (int i=0; i<8; i++){
            current_roll[i] = rollSingleDie();

        }
        System.out.println("PLAYER'S CARD IS : " + player.getFortuneCard() + "   AFTER FIRST ROLL PLAYER'S ROLL IS : " + Arrays.toString(current_roll));
        player.nextRound();

        current_roll = useSorceress(current_roll, player); //ask player if he has sorceress card and if he wants to use sorceress card
        boolean rollState = checkIfDie(current_roll, player);//ask roll state
        if (rollState){
            isGoing = false;
            System.out.println("PLAYER DIE, END OF TURN");
        }

        // game loop, all the game steps
        while (isGoing){
            //if its skull island
            if (isSkullLand(current_roll)){ //if its skull island
                isGoing = false;
                numOfSkull = rerollSkullLandAndCountNOSkull(current_roll, player);
                System.out.println("Player has " + numOfSkull + " skulls in total");
                break; // return # of skull in negative number
            }

            showSelections();
            int player_selection = Integer.parseInt(scanner.nextLine());
            System.out.println("Player's selection is : " + player_selection);

            //if player choose to hold dice and re-roll others
            if (player_selection == 1){                                                             // selection 1
                System.out.println("Select the dice index you want to hold (from 0 to 7),separate by one comma; Enter anynumber that greater than 7 will roll all dice except for skull");
                String input = scanner.nextLine();

                // checking if player input is not between 0-7
                if (input.matches("-?\\d+") && (Integer.parseInt(input) > 8 || 0 < Integer.parseInt(input))){
                    System.out.println("Before re-roll, roll is : " + Arrays.toString(current_roll));
                    current_roll = reRollWithoutHold(current_roll);
                    System.out.println("After re-roll without hold, new roll is : " + Arrays.toString(current_roll));

                }else{
                    holding_roll = input.replaceAll("\\s", "").split(",");
                    System.out.println("Player want to hold these dice index" + Arrays.toString(holding_roll));
                    System.out.println("Now reroll dice");
                    current_roll = RerollWithHold(current_roll, holding_roll);
                    System.out.println("After hold and reroll, new roll is : " + Arrays.toString(current_roll));
                }

                System.out.println();
                current_roll = useSorceress(current_roll, player);
                rollState = checkIfDie(current_roll, player);
                //check is dead
                if (rollState){
                    isGoing = false;
                    player.setScore(player.getScore());
                    System.out.println("PLAYER DIE, END OF TURN");
                }

                //if its skull island
                if (isSkullLand(current_roll)){ //if its skull island
                    isGoing = false;
                    numOfSkull = rerollSkullLandAndCountNOSkull(current_roll, player);
                    System.out.println("Player has " + numOfSkull + " skulls in total");
                    break; // return # of skull in negative number
                }


            } else if (player_selection == 2) {
                current_roll = useSorceress(current_roll, player);
                if (checkIfDie(current_roll, player)){
                    isGoing = false;
                    player.setScore(player.getScore());
                    System.out.println("PLAYER DIE, END OF TURN");
                }

                //checking sea battle
                if (player.getFortuneCard() == "2 sword" || player.getFortuneCard() == "3 sword" || player.getFortuneCard() == "4 sword"){
                    System.out.println("Dealing with sea battle");
                    seaBattle(player, current_roll);
                } else if (player.getFortuneCard() == "captain" || player.getFortuneCard() == "monkey business") {
                    calculateScoreForARoundWithCapMonkey(player, current_roll);
                }else {
                    int pScore = scoreForKindsAndChest(current_roll, player) +  scoreForDC(current_roll, player);
                    int player_total_score = player.getScore() + pScore;
                    player.setScore(player_total_score);
                    System.out.println("Your score for this round is : " + pScore);
                    System.out.println("Your final score is : " + player_total_score);
                }
                isGoing = false;

            } else if (player_selection == 3) {
                //ask player to select dice
                System.out.println("Player fortune card is : " + player.getFortuneCard() + " player current roll is : " + Arrays.toString(current_roll));
                System.out.println("Select the index of dice which you want to put into chest (from 0 - 7), separate by one comma");
                String[] diceToChest = scanner.nextLine().replaceAll("\\s", "").split(",");

                //putting selected dice into chest
                System.out.println("Player want to put these dice in chest" + Arrays.toString(diceToChest));
                System.out.println("Putting selected dice into chest...");

                player.setHoldingDie(diceToChest);
                System.out.println("Player's chest now has : " + Arrays.toString(player.getHoldingDie()));

                //player hold dice
                System.out.println("Select the dice you want to hold, separate by one comma");
                String[] input = scanner.nextLine().replaceAll("\\s", "").split(",");
                System.out.println("Player want to hold these dice (did not put into chest) : ");
                System.out.println(input);

                //re-roll
                current_roll = RerollWithChestHold(current_roll, input, diceToChest);

                System.out.println("After hold and re-roll, new roll is : " );
                System.out.println();
                System.out.println("Player current info showing below: ");
                System.out.println("Player fortune card is : " + player.getFortuneCard() + " player current roll is : " + Arrays.toString(current_roll));
                current_roll = useSorceress(current_roll, player);
                rollState = checkIfDie(current_roll, player);

                //check is dead
                if (rollState){
                    isGoing = false;
                    System.out.println("PLAYER DIE, END OF TURN");
                    scoreChest(player, current_roll);
                }
            }
        }
        System.out.println("Your turn end, now waiting for other players to play");
        emptyCurrentRoll();
        emptyHoldingRoll();
        if (Math.abs(numOfSkull) > 3){
            return numOfSkull;
        }else {
            return player.getScore();
        }
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PirateGame pirateGame = new PirateGame();
        System.out.println("ENTER PLAYER NAME: ");
        String name = scanner.next();
        pirateGame.setNewPlayer(new Player(name));
        pirateGame.connectToClient();
        pirateGame.startGame();
        scanner.close();
    }
}
