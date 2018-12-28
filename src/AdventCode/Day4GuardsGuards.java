package AdventCode;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day4GuardsGuards {
    List<Day4xEvent> events = new ArrayList<Day4xEvent>();
    //Set<Day4xEvent> chrono = new TreeSet<Day4xEvent>();
    int currentGuard;
    Map<Integer, Integer> sleepytime = new HashMap<>();
    int sleepiest=0;
    List<Day4xEvent> snoozeEvents = new ArrayList<Day4xEvent>();
    int[] times = new int[60];
    Map<Integer, int[]> guards = new HashMap<>();

    public void start(){
        readInput();
        sortEvents();
        assignType();
        assignGuard();
        measureSleepytime();
        findSleepiest();
        //create a list of the events for 10
        snoozeFestTime(sleepiest);
        countTimes();

//        for (int i = 0; i < times.length; i++) {
//            System.out.println("minute " +i +": sleeping times" + times[i]);
//        }
        mostCommonTime();
        System.out.println("-------------------");

        partTwo();


    }

    public void partTwo(){
        // need to find the sleepiest time for each guard
        //ie creat a map of guardID: sleepiest time

        for (Integer guard : guards.keySet()){
            snoozeEvents.clear();
            snoozeFestTime(guard);
            for (int i = 0; i < times.length; i++) {
                times[i]=0;
            }
            countTimes();
            int time =mostCommonTime();
            int often = times[time];
            int[] timeoften = new int[]{time, often};
            guards.put(guard, timeoften);
            System.out.println(guard +", sleepytime: " + timeoften[0] +", howoften: " + timeoften[1] );
        }
//System.out.println(guards);


        // and how often they're sleeping then



        // find the max of the how oftens - which guard is it?
        int highesttimes =0;
        int winningGuard =0;

            for(Integer guard: guards.keySet()){
                int[] x = guards.get(guard);
                if (x[1]>highesttimes){
                    highesttimes = x[1];
                    winningGuard=guard;
                }

            }

System.out.println("most times: " + highesttimes+", winning Guard: " + winningGuard + ", sleepytime: ");
System.out.println("answer: " +(winningGuard *guards.get(winningGuard)[0]));





    }








    public int mostCommonTime(){
        int max=0;
        int mode = 0;
        for (int i = 0; i < times.length; i++) {
            if (times[i]>max){
                max = times[i];
                mode = i;
            }
        }
        System.out.println("the sleepiest times is: " + mode);
        //System.out.println("the answer is: " +mode*sleepiest);
        return mode;
    }

    public void countTimes(){
        int toSleep=0;
        int wakey=0;
        for (Day4xEvent event : snoozeEvents) {
            if(event.getHappening().contains("falls")){
                toSleep=event.getMinute();
            } else if (event.getHappening().contains("wakes")){
                wakey=event.getMinute();
                for (int i = 0; i < times.length; i++) {
                    if (toSleep<=i && i<wakey){
                        times[i]++;
                    }
                }
            }
        }

    }

    public void snoozeFestTime(int aguard){
        for (Day4xEvent event : events) {
            if (event.getGuard()==aguard){
                snoozeEvents.add(event);
            }
        }
        System.out.println("snooze events for guard #" +aguard +": " +snoozeEvents);
    }

    public void findSleepiest(){
        int longestSleep=0;

        for (Integer key : sleepytime.keySet()){
            int value = sleepytime.get(key);
            if (value > longestSleep){
                longestSleep = value;
                sleepiest = key;
            }
        }
        System.out.println("the sleepiest guard is: " + sleepiest);
    }

    public void measureSleepytime(){
        int toSleep =0;
        for (Day4xEvent event : events) {
            if(event.getSW()==true){
                if(event.getHappening().contains("asleep")){
                    toSleep = event.getMinute();
                } else if (event.getHappening().contains("wakes")){
                    int wakes = event.getMinute();
                    int sleeptime = wakes-toSleep;
                    if(sleepytime.containsKey(event.getGuard())) {
                        sleepytime.put(event.getGuard(), sleepytime.get(event.getGuard()) + sleeptime);
                    }else {
                        sleepytime.put(event.getGuard(), sleeptime);
                    }
                }
            }
        }
    }

    public void assignGuard(){
        for (Day4xEvent event : events) {
            if (event.getSW()==false){
                currentGuard = event.getGuardID();
                guards.put(currentGuard, null);
            } else if (event.getSW()==true){
                event.setGuard(currentGuard);
            }
        }
    }

    public void assignType(){
        for (Day4xEvent event : events) {
            if (event.getHappening().contains("falls")||event.getHappening().contains("wakes")){
                event.setSW(true);
            }
        }
    }

    public void sortEvents(){
Collections.sort(events);
System.out.println(events);
    }

    public void readInput(){
        File input = new File("input04.html");
        try(Scanner scanner = new Scanner(input)){
            scanner.useDelimiter("\\]\\s|\\n");
            while (scanner.hasNext()){
                String x = scanner.next();
                String y = scanner.next();
                events.add(new Day4xEvent(x, y));
            }

        }catch(IOException e){
            System.out.println("an io ex w/ the scanner");
        }
    }
}
