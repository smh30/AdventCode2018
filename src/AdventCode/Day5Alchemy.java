package AdventCode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Part one:
//I'm actually really happy at how this one went compared to previous days. Actually thought it through on paper beforehand
//rather than just typing wildly and hoping something would work (at least, once i happened across the fact there was a
//spare newline on the input....

//Part two:
//Okay, so I got too lazy to get it to spit out the minimum size, since it was obvious just by looking at the numbers
//and there's some copy-pasted code...

public class Day5Alchemy {
    List<Character> allChars = new ArrayList<Character>();
    List<Integer> indicesToDelete = new ArrayList<>();

    public void start() {
        getInput();

        boolean done = false;
        while (!done) {

           process();

            if (indicesToDelete.size()==0){
                done = true;
            }
        }

        System.out.println(allChars.size());
        System.out.println("---------------------");

        partTwo();

    }

    public void partTwo(){
        List<Integer> polymerSizes = new ArrayList<Integer>();

        for (char a = 'a'; a <= 'z'; a++){
            allChars.clear();
            getInput();

            allChars.removeAll(Collections.singleton(a));
            allChars.removeAll(Collections.singleton((char)(a-32)));
            //System.out.println(allChars.size());

            //System.out.println(allChars);
            boolean done = false;
            while (!done) {
                process();

                if (indicesToDelete.size()==0){
                    done = true;
                }
            }

            polymerSizes.add(allChars.size());

            System.out.println(allChars.size());

        }



    }

    public void process() {
        indicesToDelete.clear();
        findReactions();

        doDelete();


    }


    public void doDelete(){
        for (int i = allChars.size()-1; i >= 0; i--) {
            if (indicesToDelete.contains(i)){
                allChars.remove(i);
            }
        }

    }

    public void findReactions(){
        for (int i = 1; i < allChars.size(); i++) {
            if((allChars.get(i)-32 == allChars.get(i-1)||allChars.get(i)+32 == allChars.get(i-1))&& !indicesToDelete.contains(i-1)){
                indicesToDelete.add(i-1);
                indicesToDelete.add(i);
            }
        }

    }

    public void getInput() {
        File input = new File("input05.html");
        int x;
        try (FileReader reader = new FileReader(input)) {
            while ((x = reader.read()) != -1) {
                allChars.add((char) x);
            }
        } catch (
                IOException e) {
            System.out.println("an IO exception with the input");
        }

    }
}
