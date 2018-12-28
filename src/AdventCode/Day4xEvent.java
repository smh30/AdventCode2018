package AdventCode;

import java.util.SplittableRandom;

public class Day4xEvent implements Comparable<Day4xEvent> {
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String happening;
    private int guard;
    private boolean sw = false;

    public Day4xEvent(String x, String y){
        //System.out.println(x + " : " + y);
        String[] xsplit = x.split("-");
        this.month = Integer.parseInt(xsplit[1]);
        this.day = Integer.parseInt(xsplit[2].substring(0,2));
        //System.out.println("month: " + month);
        //System.out.println("day: " + day);
        String[] xxsplit = xsplit[2].substring(3).split(":");
        this.hour = Integer.parseInt(xxsplit[0]);
        this.minute = Integer.parseInt(xxsplit[1]);
        //System.out.println("hour: " + hour);
        //System.out.println("minute: " + minute);
        this.happening = y;
        //System.out.println(happening);
        //System.out.println(this);
    }

    @Override
    public int compareTo(Day4xEvent o) {
        int monthDiff = this.month-o.getMonth();
        int dayDiff = this.day-o.getDay();
        int hourDiff = this.hour-o.getHour();
        int minDiff = this.minute-o.getMinute();

        if (monthDiff != 0){
            return monthDiff;
        } else if (dayDiff != 0){
            return dayDiff;
        } else if (hourDiff != 0){
            return hourDiff;
        } else if (minDiff != 0){
            return minDiff;
        }
        return 0;
    }

    @Override
    public String toString() {
        return (month + "-"+ day +" "+ hour+":"+minute +" - " +happening);
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getHappening() {
        return happening;
    }

    public void setGuard(int guard){
        this.guard = guard;
    }

    public void setSW(boolean sleepywake){
        this.sw = sleepywake;
    }

    public boolean getSW(){
        return sw;
    }

    //for events which are guard change type, will return guard coming on duty
    public int getGuardID(){
        String[] words = happening.split(" ");
        int id = Integer.parseInt(words[1].substring(1));
        return id;
    }

    public int getGuard(){
        return guard;
    }
}
