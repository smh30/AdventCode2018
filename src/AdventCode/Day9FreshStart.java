package AdventCode;

import java.util.ArrayList;
import java.util.List;

public class Day9FreshStart {
    public static void main(String[] args) {
        Day9FreshStart game = new Day9FreshStart();
        long winningScore = game.playMarbles(411, 7205900);
        System.out.println("The winning score is: " + winningScore);
        
    }
    List<Player> players;
    
    public long playMarbles(int numPlayers, long lastMarble){
        long winningScore = 0;
        players = new ArrayList<Player>();
        for (int i=1;i<=numPlayers;i++){
            players.add(new Player("player"+i));
        }
        MarbleList game = new MarbleList();
        for (int i=0; i<=lastMarble; i++){
            
            if (i%23==0 && i!=0){
            game.backRotate(7);
            int rmovedValue = game.remove();
            
            //figure out who gets the points....
               int turn = i%numPlayers;
               if (turn ==0){turn=numPlayers;}
               Player currentPlayer = players.get(turn-1);
               long currentScore = currentPlayer.getScore();
               currentScore += rmovedValue+i;
               currentPlayer.setScore(currentScore);
               
            } else {
                if (i!=0) {
                    game.rotate(1);
                }
                game.add(new Marble(i));
            }
        }
    
        for (Player player : players) {
            if (player.getScore()>winningScore){
                winningScore=player.getScore();
            }
        }
        
        return winningScore;
    }
    
    public class Marble {
        private Marble prev;
    
        public Marble getPrev() {
            return prev;
        }
        public void setPrev(Marble prev) {
            this.prev = prev;
        }
        public Marble getNext() {
            return next;
        }
        public void setNext(Marble next) {
            this.next = next;
        }
        private Marble next;
        public int value;
        
        public Marble(int initPos){
            this.value=initPos;
        }
    }
    
    public class MarbleList {
        public Marble current;
        
        public MarbleList(){
        
        }
        
        public void add(Marble newMarble){
            if (newMarble.value==0){
                current=newMarble;
                newMarble.setNext(newMarble);
                newMarble.setPrev(newMarble);
            } else {
                current.getNext().setPrev(newMarble);
                newMarble.setNext(current.getNext());
                current.setNext(newMarble);
                newMarble.setPrev(current);
                current=newMarble;
            }
            
        }
        
        public int remove(){
            current.getNext().setPrev(current.getPrev());
            current.getPrev().setNext(current.getNext());
            int removed =current.value;
            current=current.getNext();
            return  removed;
        }
        
        public void rotate(int posn){
            for (int i=0; i<posn; i++){
                current=current.getNext();
            }
        }
        
        public void backRotate(int posn){
            for (int i=0; i<posn; i++){
                current=current.getPrev();
            }
        }
    }
    
    
    public class Player {
        public String name;
        private long score;
        public Player(String name) {
            this.name = name;
            score = 0;
        }
        public long getScore() {
            return score;
        }
        
        public void setScore(long score) {
            this.score = score;
        }
    }
}
