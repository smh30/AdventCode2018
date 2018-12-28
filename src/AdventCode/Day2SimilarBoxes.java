package AdventCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2SimilarBoxes {
    String[] answer = new String[2];

public void compare(){
    File input = new File("input02.html");
    String boxID = "";
    List <String> boxes = new ArrayList();

    try(BufferedReader reader = new BufferedReader(new FileReader(input))) {
        while ((boxID = reader.readLine()) != null) {
            boxes.add(boxID);
        }
    } catch(
    IOException e) {
        System.out.println("an IO exception with the input");
    }
    //compare each string to the following ones
    for (int i = 0; i < boxes.size(); i++) {
        thisOne(boxes, i);
    }
    System.out.println(answer[0] + ", " + answer[1]);
    String matches =createMatchString();
    System.out.println(matches);


}

public String createMatchString(){
 String matches = "";
 for(int i =0; i< answer[0].length(); i++){
     if (answer[0].charAt(i)==answer[1].charAt(i)){
         matches+= answer[0].charAt(i);
     }
 }
 return matches;

}

public void thisOne(List<String> boxes, int i) {

    for (int j = i + 1; j < boxes.size(); j++) {
        thisCombo(boxes, i, j);

    }
}

    public void thisCombo(List<String> boxes, int i, int j){
    //String[] answer = new String[2];
        boolean maybe = false;
        for (int n = 0; n< boxes.get(i).length(); n++){
            if(boxes.get(i).charAt(n)!=boxes.get(j).charAt(n) && maybe==true){
                //System.out.println("not this one: " + boxes.get(i) + "," + boxes.get(j));
                return;
            } else if (boxes.get(i).charAt(n)!=boxes.get(j).charAt(n) && maybe==false){
                maybe= true;

            }
        }//System.out.println("this one" + boxes.get(i) + "," + boxes.get(j));
        answer[0] = boxes.get(i);
        answer[1] = boxes.get(j);
        return;
    }

    }




