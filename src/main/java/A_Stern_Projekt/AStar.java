package A_Stern_Projekt;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Astar Algorithm is the better version of the Dijkstra.
 * It knows the targets location and so it works it way step by step.
 * In comparison to Dijkstra it doesn't check every tile, which is in the target direction.
 * This means it only checks the tiles it needs to check.
 */
public class AStar {
    Node start, end, current;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> closedList = new ArrayList<>();
    boolean goalReached = false;
    ArrayList<Node> path = new ArrayList<>();

    public AStar(Node start, Node end) {
        this.start = start;
        this.end = end;
        openList.add(start);
        for (int i = 0; i < Panel.position.length; i++) {
            for (int j = 0; j < Panel.position.length; j++) {
                addNeighbors(Panel.position[i][j]);
            }
        }
    }

    /**
     * Uses the base of Dijkstra to find the goal as fast as possible,
     * by deciding which tile in the area is the nearest to the target
     */
    public void search() {
        if (!openList.isEmpty() && !goalReached) {
            int lowestFIndex = 0;
            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(lowestFIndex).getCostF() > openList.get(i).getCostF()) {
                    lowestFIndex = i;
                }
            }
            current = openList.get(lowestFIndex);
            if (current == end) {
                trackPath();
                System.out.println("DONE");
                goalReached = true;
            }
            openList.remove(current);
            closedList.add(current);
            var neighbors = current.getNeighbors();
            for (Node neighbor : neighbors) {
                if (!closedList.contains(neighbor)) {
                    var tempG = getGCost(start, neighbor) + getGCost(current, neighbor);
                    if (openList.contains(neighbor)) {
                        if (tempG < neighbor.getCostG()) {
                            neighbor.setCostG(tempG);
                        }
                    } else if(!neighbor.isBlocked()){
                        neighbor.setCostG(tempG);
                        openList.add(neighbor);
                        neighbor.setParent(current);
                    }
                }

                neighbor.setCostH(getHCost(end, neighbor));
                neighbor.setCostF(getFCost(neighbor));
            }
        }

    }

    /**
     * Backtraces the path from the end to the goal,
     * so that the fastest way could be seen
     */
    public void trackPath() {
        Node temp = current;
        path.add(temp);
        while (temp.hasParent()){
            System.out.println("X: "+temp.getX());
            System.out.println("Y: "+temp.getY());
            path.add(temp.getParent());
            temp = temp.getParent();
        }
    }

    /**
     * Adds all reachable tiles around the current tile and adds them to their neighbor array
     */
    public void addNeighbors(Node block) {
        for (int i = 0; i < Panel.position.length; i++) {
            for (int j = 0; j < Panel.position.length; j++) {
                if (Panel.position[i][j].getX() == block.getX() && Panel.position[i][j].getY() == block.getY()) {
                    if (i + 1 < Panel.position.length) {
                        block.getNeighbors().add(Panel.position[i + 1][j]);

                    }
                    if (i > 0) {
                        block.getNeighbors().add(Panel.position[i - 1][j]);

                    }
                    if (j + 1 < Panel.position.length) {
                        block.getNeighbors().add(Panel.position[i][j + 1]);

                    }
                    if (j > 0) {
                        block.getNeighbors().add(Panel.position[i][j - 1]);
                    }
                }
            }
        }
    }

    public int getFCost(Node current) {
        return current.getCostG() + current.getCostH();
    }

    public int getGCost(Node start, Node current) {
        return Math.abs(start.getX() - current.getX()) + Math.abs(start.getY() - current.getY());
    }

    public int getHCost(Node end, Node current) {
        return Math.abs(end.getX() - current.getX()) + Math.abs(end.getY() - current.getY());
    }

    public void checkNeighbors(Graphics g, Node block) {
        for (int j = 0; j < block.getNeighbors().size(); j++) {
            block.getNeighbors().get(j).setColor(Color.pink);
            block.getNeighbors().get(j).setType(0);
        }
    }

    /**
     * Draws the Map so it can be visualized
     */
    public void draw(Graphics g) {
        for (Node value : closedList) {
            value.setColor(Color.LIGHT_GRAY);
            value.setType(0);
            value.draw(g);
        }

        for (Node value : openList) {
            value.setColor(Color.ORANGE);
            value.setType(0);
            value.draw(g);
        }


        for (Node node : path) {
            node.setType(0);
            node.setColor(Color.CYAN);
            node.draw(g);
        }




        start.setType(0);
        start.setColor(Color.red);
        start.draw(g);
        end.setType(0);
        end.setColor(Color.green);
        end.draw(g);

    }
}
