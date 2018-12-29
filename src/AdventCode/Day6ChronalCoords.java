package AdventCode;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

//this one drove me a bit crazy, I had forgotten to account for the points which are equidistant to two reference points.

public class Day6ChronalCoords {
    Map<Point, Integer> mainPoints = new HashMap<>();
    int largestX = 0;
    int largestY = 0;
    int smallestX = 1000;
    int smallestY = 1000;
    Set<Point> containsEdge = new HashSet<Point>();


    public void start(){
        partOne();

    }

    public void partOne(){
        getInput();

        findBorders();

        checkAllPoints();

        removeEdges();

        int answer = getAnswer();
        System.out.println(answer);
    }

    public int getAnswer(){
        int maxValue = 0;
        for (int value : mainPoints.values()){
            if (value > maxValue){
                maxValue = value;

            }
        }
        return maxValue;


    }

    public void removeEdges(){
        for (Point point : containsEdge) {
            mainPoints.remove(point);
        }
    }

    public void checkAllPoints(){
        for (int x = 0; x<= largestX; x++){
            for (int y =0; y<= largestY; y++){
                int lowest=1000;
                Point closestPoint = null;
                List<Point> possibleClosest = new ArrayList<>();
                for (Point key : mainPoints.keySet()){
                    int distance = Math.abs(x - (int)key.getX())+(Math.abs(y - (int)key.getY()));
                    if (distance < lowest){
                        possibleClosest.clear();
                        lowest = distance;
                        possibleClosest.add(key);
                    } else if (distance == lowest){
                        possibleClosest.add(key);
                    }
                    //need somthing for if two points are equally close

                }
                if (possibleClosest.size()==1) {
                    closestPoint = possibleClosest.get(0);
                    mainPoints.put(closestPoint, mainPoints.get(closestPoint) + 1);
                }
                if (x==smallestX||y==smallestY||x==largestX||y==largestY){
                    containsEdge.add(closestPoint);
                }
            }
        }
    }

    public void findBorders(){
        for(Point key : mainPoints.keySet()){
            if (key.getX() > largestX){
                largestX = (int)key.getX();
            }
            if (key.getY() > largestY){
                largestY = (int)key.getY();
            }
            if (key.getX() < smallestX){
                smallestX = (int)key.getX();
            }
            if (key.getY() < smallestY){
                smallestY = (int)key.getY();
            }
            if (key.getX()==smallestX || key.getY()==smallestY || key.getX()==largestX || key.getY()==largestY){
                //edgesTwo.add(key);
            }
        }
    }

    public void getInput(){
        File input = new File("input06.html");
        try(Scanner scanner = new Scanner(input)){
            scanner.useDelimiter("\\n|, ");
            //read in the file and make them all into points???
            while(scanner.hasNext()) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Point a = new Point(x, y);
                mainPoints.put(a, 0);
            }
        }catch(IOException e){
            System.out.println("An IO exception with the file reader");
        }
    }
}
