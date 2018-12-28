package AdventCode;

public class Day3xClaim {
    String ID;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean intact = true;

    public Day3xClaim(String ID, int x, int y, int width, int height){
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    @Override
    public String toString() {
        return (ID + "point: " + x +"," +y);
    }



    public boolean containsPoint(int mx, int my){
        if ((mx >= x && mx < x+width)&&(my >= y && my < y+height)){
            return true;
        }
        return false;
    }

    public boolean getIntact(){
        return intact;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setIntact(Boolean x){
        this.intact = x;
    }
}
