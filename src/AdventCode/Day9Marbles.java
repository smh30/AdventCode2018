package AdventCode;

//credit to https://stackoverflow.com/questions/29803724/circular-linkedlist-in-java
//I've never attempted a linked list before

public class Day9Marbles {
    public static void main(String[] args) {
        Day9Marbles game = new Day9Marbles();
        int winningScore = game.playMarbles(9, 25);
        
    }
    
    public int playMarbles(int players, int marbles){
        int winningScore = 0;
        
        //each player has a score (player objeects? or just a list?)
        
        //first marble value = 0. up to given int
        //normal play:
        //place next between 1&2 clockwise oof current (most recent placed)
        
        //if marble is multtiple of 23:
          //add that number to thee player's score
          //remove marble 7 spaces anticlockwisee and add to scoore
        //now the cloockwis of removed will become current
    
        //game eends when all marbles are consumed
        
        
        // after the game, see what player has the highest score and retturn it
        return winningScore;
    }
    
    public class Marble{
        public int value;
        private Marble next;
        private Marble previous;
        
        public Marble(int value){
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
    
    public class MarbleCircleList{
        private Marble current;
        private Marble first;
        
        public Marble getCurrent(){
            return current;
        }
        
        public void setCurrent(Marble newCurrent){
            current = newCurrent;
        }
        
        public void advance(){
            current = current.next;
        }
        
        public void retreat(){
            current = current.previous;
        }
        
        //inserts after the current marble
        public void insert (int value){
            Marble newMarble = new Marble(value);
            if (first==null){
                first = current = newMarble;
                newMarble.setNext(newMarble);
                newMarble.setPrevious(newMarble);
            } else {
                newMarble.setNext(current.getNext());
                current.setNext(newMarble);
                newMarble.setPrevious(current);
                
            }
            current = newMarble;
        }
        
        //removes and closes the space, returns value to be addeed to score
        public int remove (Marble marble){
            marble.getPrevious().setNext(marble.getNext());
            marble.getNext().setPrevious(marble.getPrevious());
            
            current = marble.getNext();
            
            return marble.value;
        }
    }
    
}
