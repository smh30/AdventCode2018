package AdventCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3OverlapInches {
    List<Day3xClaim> claims = new ArrayList<Day3xClaim>();
    //List overlap = new ArrayList();
    int[][] fabric;


    public void start(){

        createClaims();

        //this creates an array with 0 in each value. the array index is equivalent to the point
        createFabric();
//System.out.println(fabric.length);

        findOverlaps();
        //System.out.println(fabric[0][0]);

        // count the -1
        int overlaps = countOverlaps();
        System.out.println(overlaps);

        findIntact();

    }

    public void findIntact(){
        for (Day3xClaim claim : claims) {
            if (claim.getIntact()){
                System.out.println( claim.toString());
            }
        }

    }

    public int countOverlaps(){
        int overpaps = 0;
        for (int i = 0; i < fabric.length; i++) {
            for (int i1 = 0; i1 < fabric[i].length; i1++) {
                if(fabric[i][i1] == -1){
                    overpaps++;
                }
            }
        }
        return overpaps;
    }

    public void findOverlaps(){
        for (int i = 0; i < fabric.length; i++) {
            for (int i1 = 0; i1 < fabric[i].length; i1++) {
                boolean maybe = false;

                    for (Day3xClaim claim : claims) {
                        if(claim.containsPoint(i, i1) && maybe == false){
                            maybe=true;
                        } else if (claim.containsPoint(i,i1) && maybe== true){
                            fabric[i][i1]=-1;
                            chopFabric(i, i1);
                            break;
                        }
                    }

            }
        }

    }

    public void chopFabric(int i, int i1){
        for (Day3xClaim claim : claims) {
            if (claim.containsPoint(i, i1)){
                claim.setIntact(false);
            }
        }

    }

    public void createFabric(){
        int fabricWidth = getMaxX();
        int fabricHeight = getMaxY();
        fabric = new int[fabricWidth][fabricHeight];
    }

    public int getMaxX(){
        int maxX = 0;

        for (Day3xClaim claim : claims) {
            maxX = Math.max(maxX, claim.getX()+claim.getWidth());
        }

        return maxX;
    }

    public int getMaxY(){
        int maxY = 0;

        for (Day3xClaim claim : claims) {
            maxY = Math.max(maxY, claim.getY()+claim.getHeight());
        }

        return maxY;
    }
    public void createClaims() {
        File input = new File("input03.html");

        try(Scanner scanner = new Scanner(input)){
            while (scanner.hasNextLine()){
                scanner.useDelimiter("\\r\\n|\\s@\\s|,|:\\s|x|\\n");
                String ID = scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int width = scanner.nextInt();
                int height = scanner.nextInt();
                claims.add(new Day3xClaim(ID, x, y, width, height));
            }
            System.out.println(claims);
        } catch (IOException e){
            System.out.println("An IO ex with the scanner");
        }
    }
}


//How many square inches of fabric are within two or more claims?