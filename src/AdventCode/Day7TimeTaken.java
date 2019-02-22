package AdventCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day7TimeTaken {
    
    
    Map<String, List<String>> steps = new TreeMap<>();
    String correctOrder = "";
    int[] workerTimes = new int[5];
    int currentTime = 0;
    
    public void start() {
        List<String> lines = getInput();
        makeMap(lines);
        System.out.println(steps);
        
        doSteps();
        
    }
    
    public void doSteps() {
        List<String> done = new ArrayList<>();
        done.add("done");
        int todo = steps.size();
        List<String> wip = new ArrayList<>();
        List<String> fin = new ArrayList<>();
        
        
        while (todo != 0) {
            //if any takse have just finished, remove thier prereq
            if (!wip.isEmpty()) {
                for (String job : wip) {
                    int finish = Integer.parseInt(job.substring(1));
                    if (finish <= currentTime) {
                        correctOrder += job.charAt(0);
                        todo--;
                        fin.add(job);
                        
                        //then the job is done
                        for (List<String> prereqs : steps.values()) {
                            if (prereqs != null && prereqs.contains(""+job.charAt(0))) {
                                prereqs.remove(""+job.charAt(0));
                                
                            }
                        }
                    }
                }
                System.out.println("do delete");
                wip.removeAll(fin);
            }
            
            // if workers are free, have them get a task
            for (int i = 0; i < workerTimes.length; i++) {
                if (workerTimes[i] <= currentTime) {
                    System.out.println("worker " + i + " is free");
                    for (String step : steps.keySet()) {
                        if (steps.get(step) == null || steps.get(step).size() == 0) {
                            steps.put(step, done);
                            wip.add(step + (currentTime+stepTime(step)));
                            
                            workerTimes[i] = currentTime+stepTime(step);
                            break;
                        }
                    }
                }
            }
            currentTime++;
        }
        System.out.println("correctt order =" +correctOrder);
        
        int maxtime = workerTimes[0];
        for (int workerTime : workerTimes) {
            if (workerTime > maxtime){
                maxtime = workerTime;
            }
        }
        System.out.println("maxtime = "+ maxtime);
        
    }
    
    
    private int stepTime(String chara) {
        return chara.charAt(0) - 'A' + 61;
    }
    
    public void makeMap(List<String> lines) {
        for (String line : lines) {
            String[] lineWords = line.split(" ");
            String prereq = lineWords[1];
            String step = lineWords[7];
            if (steps.containsKey(step) && steps.get(step) != null) {
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
