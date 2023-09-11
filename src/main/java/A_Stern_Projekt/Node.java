package A_Stern_Projekt;

import java.awt.*;
import java.util.ArrayList;

public class Node {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;
    private int x, y, type;
    private final ArrayList<Node> neighbors = new ArrayList<>();
    private int costH = 0;
    private int costG = 0;
    private int costF = 0;
    private Node parent;
    private Color color;
    private boolean blocked = false;

    public Node(int x, int y, int type, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;

    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node getParent() {
        return parent;
    }

    public boolean hasParent(){
        return this.getParent() != null;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public int getCostF() {
        return costF;
    }

    public void setCostF(int costF) {
        this.costF = costF;
    }

    public int getCostG() {
        return costG;
    }

    public void setCostG(int costG) {
        this.costG = costG;
    }

    public int getCostH() {
        return costH;
    }

    public void setCostH(int costH) {
        this.costH = costH;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g){
        if(type == 0){
            g.setColor(color);
            g.fillRect(x,y,WIDTH,HEIGHT);
        }else{
            g.setColor(color);
            g.drawRect(x,y,WIDTH,HEIGHT);
        }
        g.setColor(Color.black);
    }
}
