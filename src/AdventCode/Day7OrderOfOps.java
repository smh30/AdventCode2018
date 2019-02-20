package AdventCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day7OrderOfOps {
    Map<String, List<String>> steps = new TreeMap<>();
    String correctOrder = "";

    public void start() {
        List<String> lines = getInput();
        makeMap(lines);

        //now need to loop the map and do steps
//System.out.println("something");
        doSteps();

    }

    public void doSteps() {
        List<String> done = new ArrayList<>();
        done.add("done");
        int todo = steps.size();
        while (todo != 0) {
            for (String step : steps.keySet()) {
                if (steps.get(step) == null || steps.get(step).size()==0) {
                    correctOrder += step;
                    steps.put(step, done);
                    todo--;
                    //System.out.println("made it 1");
                    for (List<String> prereqs : steps.values()) {
                        //System.out.println("made 2");
                        //System.out.println(prereqs.size());
                        if (prereqs != null && prereqs.contains(step)) {
                            prereqs.remove(step);
                        }
                        //System.out.println(prereqs.size());
                    }
                    break;


                }
            }
        }
        System.out.println(correctOrder);
            //System.out.println(steps);
    }

    public void makeMap(List<String> lines) {
        for (String line : lines) {
            String[] lineWords = line.split(" ");
            String prereq = lineWords[1];
            String step = lineWords[7];
            if (steps.containsKey(step) && steps.get(step)!=null) {
                List<String> currentPrereqs = steps.get(step);
                currentPrereqs.add(prereq);
                steps.put(step, currentPrereqs);
            } else {
                List<String> firstreq = new ArrayList<String>();
                firstreq.add(prereq);
                steps.put(step, firstreq);
            }
            if (!steps.containsKey(prereq)) {
                steps.put(prereq, null);
            }
        }
    }

    public ArrayList<String> getInput() {
        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("input07.html"))) {
            String nextline = "";
            while ((nextline = br.readLine()) != null) {
                lines.add(nextline);

            }

        } catch (IOException e) {
            System.out.println("an io exeption");
        }
        return lines;
    }
}
