package AdventCode;

import javax.swing.*;
import java.awt.*;

public class Day10Frame extends JFrame {
    
    public Day10Frame(String title, int x, int y, int width, int height){
        setTitle(title);
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Day10Stars drawing = new Day10Stars();
        drawing.setPreferredSize(new Dimension(width, height));
        Container visibleArea = getContentPane();
        visibleArea.add(drawing);
        pack();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
        Day10Frame frame = new Day10Frame("Stars Align", 50, 50, 300, 300);
        frame.setVisible(true);
            }
        });
    }
}
