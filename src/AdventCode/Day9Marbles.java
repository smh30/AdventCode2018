package AdventCode;

//credit to https://stackoverflow.com/questions/29803724/circular-linkedlist-in-java
//I've never attempted a linked list before

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class Day9Marbles {
    public static void main(String[] args) {
        Day9Marbles game = new Day9Marbles();
        //long winningScore = game.playMarbles(411, 7205900);
        long winningScore = game.playMarbles(21, 6111);
        System.out.println("The winning score is: " + winningScore);
        
    }
    
    List<Player> players;
    
    public long playMarbles(int numplayers, int marbles) {
        long winningScore = 0;
        MarbleCircleList gameCircle = new MarbleCircleList();
        
        //each player has a score, saved as a Player
        players = new ArrayList<Player>();
        for (int i = 0; i < numplayers; i++) {
            Player player = new Player("player" + (i + 1));
            players.add(player);
        }
        
        //the first marble is inserted before starting
        gameCircle.insert(0);
        
        for (int i = 1; i <= marbles; i++) {
            //which player's turn is it?
            int currentTurn = (i-1)%numplayers;
           // System.out.println("current player " + players.get(currentTurn).name);
            
            
            //if marble is multiple of 23:
            
             if (i % 23 == 0) {
                
                long currentScore = players.get(currentTurn).getScore();
                int removedMarble = gameCircle.remove();
                
                long updatedScore = currentScore + i + removedMarble;
                
                players.get(currentTurn).setScore(updatedScore);
                
            } else {
                
                gameCircle.insert(i);
//
            }
        }
        
        winningScore = 0;
        for (Player player : players) {
            if (player.getScore()>winningScore){
                winningScore = player.getScore();
                System.out.println("winner is: " +player.name + " score: " + winningScore);
            }
        }
        // after the game, see what player has the highest score and retturn it
        return winningScore;
    }
    
    public class Marble {
        public int value;
        private Marble next;
        private Marble previous;
        
        public Marble(int value) {
            this.value = value;
        }
        
        public Marble getNext() {
            return next;
        }
        
        public void setNext(Marble next) {
            this.next = next;
        }
        
        public Marble getPrevious() {
            return previous;
        }
        
        public void setPrevious(Marble previous) {
            this.previous = previous;
        }
    }
    
    public class MarbleCircleList {
        public Marble current;
        private Marble first;
        
        public void advance() {
            current = current.next;
        }
        
        public void retreat() {
            current = current.previous;
        }
        
        //inserts after the current marble
        public void insert(int value) {
            Marble newMarble = new Marble(value);
            if (value == 0) {
                first = current = newMarble;
                newMarble.setNext(newMarble);
                newMarble.setPrevious(newMarble);
            } else {
                advance();
                newMarble.setNext(current.getNext());
                newMarble.setPrevious(current);
                current.getNext().setPrevious(newMarble);
                current.setNext(newMarble);
                
               // System.out.println("the previous marble: " + newMarble.getPrevious().value);
                
            }
            current = newMarble;
        }
        
        //removes and closes the space, returns value to be addeed to score
        public int remove() {
            for (int i = 0; i <= 7 ; i++) {
//                current = first;
//                System.out.print(first.value + " ");
//                for (int j = 0; j <22 ; j++) {
//                    retreat();
//                    System.out.print(current.value + " ");
//                }
                
                //System.out.println("current before retreat" + current.value);
                retreat();
               // System.out.println("current after retreat" + current.value);
            }
            
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            
            advance();
            int removedMarble = current.value;
            advance();
            System.out.println("removing "+ removedMarble);
            return removedMarble;
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
