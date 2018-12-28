package AdventCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2BoxChecksum {

    public void checksum() {

        File input = new File("input02.html");
        int pairs = 0;
        int trips = 0;
        String boxId = "";

        char[] chars = new char[26];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)('a'+i);
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {

            while ((boxId = reader.readLine()) != null) {
                System.out.println(boxId);
                boolean containsTrip = false;
                boolean containsPair = false;
                for (int i = 0; i < chars.length; i++) {
                    int num = instancesOfChar(chars[i], boxId);
                    switch (num) {
                        case 3:
                            if (!containsTrip){
                            trips++;
                            System.out.println("trips++");
                            containsTrip = true;}
                            break;
                        case 2:
                            if (!containsPair){
                            pairs++;
                            System.out.println("pairs++");
                            containsPair = true;}
                            break;
                        default:
                            ;
                    }

                }

            }
            System.out.println("pairs =  " + pairs);
            System.out.println("trips = " + trips);
            System.out.println(pairs * trips);
        } catch (IOException e) {
            System.out.println("an IO exception with the input");
        }

    }

    public int instancesOfChar(char x, String letters){
        int num = 0;
        for(int i=0; i<letters.length(); i++){
            if (letters.charAt(i)==x){
                num++;
            }
        }
        return num;
    }
}