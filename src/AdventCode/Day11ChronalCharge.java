package AdventCode;

public class Day11ChronalCharge {
    
    int serial;
    int[][] cells;
    
    public static void main(String[] args) {
        Day11ChronalCharge run = new Day11ChronalCharge();
        run.start();
    }
    
    public Day11ChronalCharge() {
        serial = 9995;
        cells = new int[300][300];
    }
    
    public void start() {
        for (int x = 1; x <= 300; x++) {
            for (int y = 1; y <= 300; y++) {
                int power = getPower(x, y, serial);
                cells[x - 1][y - 1] = power;
            }
        }

// Part one...
//        int highestTotal = 0;
//        int highestX = 0;
//        int highestY = 0;
//        //int highestSize = 0;
//
//        for (int x = 0; x < 298; x++) {
//            for (int y = 0; y < 298; y++) {
//                int totalAtPoint = 0;
//                for (int i = 0; i < 3; i++) {
//                    for (int j = 0; j < 3 ; j++) {
//                        totalAtPoint+= cells[x+i][y+j];
//                    }
//                }
//                if (totalAtPoint > highestTotal){
//                    highestTotal = totalAtPoint;
//                    highestX = x+1;
//                    highestY = y+1;
//                }
//            }
//        }
//        System.out.println(highestX+","
//                + highestY+ ","+ highestTotal);
        
        
        int highestTotal = 0;
        int highestX = 0;
        int highestY = 0;
        int highestSize = 0;
        
        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                int maxSize = 300 - Math.max(x, y);
                for (int size = 0; size < maxSize; size++) {
                    
                    
                    int totalAtPointSize = 0;
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            totalAtPointSize += cells[x + i][y + j];
                        }
                    }
                    if (totalAtPointSize > highestTotal) {
                        highestTotal = totalAtPointSize;
                        highestX = x + 1;
                        highestY = y + 1;
                        highestSize = size;
                    }
                    
                }
            }
        }
        System.out.println(highestX + ","
                + highestY + ","+ highestSize+", total ==" + highestTotal);
        
    }
    
    
    public int getPower(int x, int y, int serial) {
        return ((((((x + 10) * y) + serial) * (x + 10)) / 100) % 10) - 5;
    }
}
