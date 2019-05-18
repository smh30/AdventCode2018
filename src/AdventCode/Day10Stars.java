package AdventCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day10Stars extends JPanel implements ActionListener {
    Rectangle rect;
    List<Star> stars;
    Timer timer;
    JButton button;
    int seconds;
    
    public Day10Stars(){
        setBackground(Color.white);
        seconds=0;
        stars = new ArrayList<Star>();
        
        try (BufferedReader br =
                new BufferedReader(new FileReader(new File("input10.html")))){
            String nextLine = "";
            String pattern = "(-?\\d+),\\s+(-?\\d+)[^<]*<\\s?(-?\\d+),\\s+(-?\\d+)";
            Pattern r = Pattern.compile(pattern);
            
            while((nextLine=br.readLine())!=null){
                Matcher m = r.matcher(nextLine);
                
                if (m.find()){
                    Star s = new Star(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
                            Integer.parseInt(m.group(3)),
                            Integer.parseInt(m.group(4)));
                    stars.add(s);
                }
            }
        }catch (IOException e){
            System.out.println("an io exception");
        }
        System.out.println("number of stars: " + stars.size());
        int smallestX = stars.get(0).x;
        int smallestY = stars.get(0).y;
        int largestX = stars.get(0).x;
        int largestY = stars.get(0).y;
        for (Star star : stars) {
            if (star.x > largestX){
                largestX=star.x;
            }
            if (star.y > largestY){
                largestY=star.y;
            }
            if (star.x < smallestX){
                smallestX=star.x;
            }
            if (star.y < smallestY){
                smallestY=star.y;
            }
        }
        
        int dffX = largestX-smallestX;
        int diffY = largestY-smallestY;
        while(dffX>100){
            seconds++;
            for (Star star : stars) {
                star.x = star.x+star.deltaX;
                star.y = star.y+star.deltaY;
            }
            smallestX = stars.get(0).x;
            smallestY = stars.get(0).y;
            largestX = stars.get(0).x;
            largestY = stars.get(0).y;
            for (Star star : stars) {
                if (star.x > largestX){
                    largestX=star.x;
                }
                if (star.y > largestY){
                    largestY=star.y;
                }
                if (star.x < smallestX){
                    smallestX=star.x;
                }
                if (star.y < smallestY){
                    smallestY=star.y;
                }
            }
            dffX = largestX-smallestX;
            diffY = largestY-smallestY;
    
        }
        System.out.println("diffx ="+dffX + " diffy: " +diffY);
        button = new JButton("stop");
        this.add(button);
        button.addActionListener(this);
        
        timer = new Timer(500, this);
        timer.start();

    }
    
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    
        for (Star star : stars) {
            g.drawRect(star.x, star.y, 1, 1);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==timer){
            System.out.println("the timer happeened");
            for (Star star : stars) {
                star.x = star.x+star.deltaX;
                star.y = star.y+star.deltaY;
            }
            seconds++;
            repaint();
        }
        
        if (e.getSource()==button){
            timer.stop();
            System.out.println("seconds = "+seconds);
        }
    }
    
    public class Star{
        int x;
        int y;
        int deltaX;
        int deltaY;
        
        public Star(int x, int y, int deltaX, int deltaY){
            this.x = x;
            this.y = y;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }
}
