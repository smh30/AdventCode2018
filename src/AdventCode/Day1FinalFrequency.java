package AdventCode;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class Day1FinalFrequency {



    public void freq(int initial) {
        boolean finished = false;
        int end = initial;
        String x;
        Boolean y;
        Set<Integer> endPoints = new TreeSet<>();
        File input = new File("input.html");

        while (!finished) {
            try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
                while ((x = reader.readLine()) != null) {
                    end += Integer.valueOf(x);
                    y = endPoints.add(end);
                    if (!y) {
                        System.out.println("Recurring value: " + end);
                        finished = true;
                        break;
                    }

                }

                System.out.println("Final frequency: " + end);


            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            } catch (IOException e) {
                System.out.println("an IO ex");
            }

        }
    }


}
