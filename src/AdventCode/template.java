package AdventCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class template {

    public void template(){
        File input = new File("input.html");

        try(BufferedReader reader = new BufferedReader(new FileReader(input))) {





        } catch(
                IOException e) {
            System.out.println("an IO exception with the input");
        }

    }


}
