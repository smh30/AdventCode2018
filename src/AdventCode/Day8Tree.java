package AdventCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* part A:
this page: https://todd.ginsberg.com/post/advent-of-code/2018/day8/ helped me with the idea
I was struggling while trying not to create a Node class, since I'd make classes unnecessarily
on previous days. It was the way to go here though.*/

public class Day8Tree {
    List<Integer> nums;
    int total = 0;
    
    public void start() {
        nums = getInput();
        Iterator<Integer> it = nums.iterator();
        Node root = makeNodes(it);
        System.out.println("tottal = " + total);
        System.out.println(nodeValue(root));
    }
    
    public List<Integer> getInput() {
        List<Integer> nums = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input08.html"))) {
            String nextline = "";
            while ((nextline = br.readLine()) != null) {
                String[] strings = nextline.split("\\s");
                
                for (String string : strings) {
                    nums.add(Integer.parseInt(string));
                }
                System.out.println("nums = " + nums);
            }
            
        } catch (IOException e) {
            System.out.println("an io exception");
        }
        return nums;
    }
    
    
    public class Node {
        List<Node> children;
        List<Integer> metadata;
        
        public Node(List<Integer> metadata, List<Node> children) {
            this.metadata = metadata;
            this.children = children;
        }
    }
    
    public Node makeNodes(Iterator<Integer> it) {
        
        int childNum = it.next();
        int metaNum = it.next();
        System.out.println("children = " + childNum + " metadata = " + metaNum);
        
        
        List<Node> childs = new ArrayList<>();
        if (childNum != 0) {
            for (int i = 0; i < childNum; i++) {
                Node child = makeNodes(it);
                childs.add(child);
            }
        }
        
        List<Integer> meta = new ArrayList<>();
        for (int i = 0; i < metaNum; i++) {
            int x = it.next();
            meta.add(x);
            total += x;
        }
        Node node = new Node(meta, childs);
        
        return node;
    }
    
    public int nodeValue(Node rooot) {
        if (rooot.children.size() == 0) {
            int value = 0;
            for (Integer metadatum : rooot.metadata) {
                value += metadatum;
            }
            return value;
        } else {
            int value = 0;
            for (Integer metadatum : rooot.metadata) {
                try {
                    if (rooot.children.get(metadatum - 1) != null) {
                        value += nodeValue(rooot.children.get(metadatum - 1));
                    }
                } catch (IndexOutOfBoundsException e) {
                 value+=0;
                }
                
            }
            return value;
        }
    }
}
