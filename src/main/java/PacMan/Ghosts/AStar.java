package PacMan.Ghosts;


import PacMan.MainFunction.PacEngine;
import PacMan.PlayerFunction.Player;

import java.awt.*;
import java.util.ArrayList;

public class AStar {
    private final Node[][] nodes;
    private final ArrayList<Node> openList;
    private final ArrayList<Node> closedList;
    public ArrayList<Node> pathList = new ArrayList<>();
    private final PacEngine engine;
    private Node start, end, current;
    private boolean goalReached = false;

    public AStar(final PacEngine engine) {
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        this.engine = engine;
        nodes = new Node[engine.getField().getMap().length][engine.getField().getMap().length];
        for (int i = 0; i < getNodes().length; i++) {
            for (int j = 0; j < getNodes().length; j++) {
                getNodes()[i][j] = new Node((i * Player.WIDTH), (j * Player.HEIGHT));
            }
        }
        for (Node[] node : getNodes()) {
            for (int j = 0; j < getNodes().length; j++) {
                addNeighbors(node[j]);

            }
        }
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public ArrayList<Node> getOpenList() {
        return openList;
    }

    public ArrayList<Node> getClosedList() {
        return closedList;
    }

    public ArrayList<Node> getPathList() {
        return pathList;
    }

    public PacEngine getEngine() {
        return engine;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public boolean isGoalReached() {
        return goalReached;
    }

    public void setGoalReached(boolean goalReached) {
        this.goalReached = goalReached;
    }

    /**
     * resets the whole settings for the astar
     */
    private void reset() {
        setGoalReached(false);
        getOpenList().clear();
        getClosedList().clear();
        getPathList().clear();
        for (Node[] node : getNodes()) {
            for (int j = 0; j < getNodes().length; j++) {
                node[j].setBlocked(false);
                node[j].setCostF(0);
                node[j].setCostH(0);
                node[j].setCostG(0);
            }
        }
    }

    /**
     * Safes all the Tiles on the map, so it can be used with a star algorithm and blocks the ones
     * where ghosts are on. Also setting the start and end node
     */
    public void setNodes(int startX, int startY, int endX, int endY) {
        reset();
        setStart(getNodes()[(startX / Player.WIDTH)][(startY / Player.HEIGHT)]);
        setEnd(getNodes()[(endX / Player.WIDTH)][(endY / Player.HEIGHT)]);
        setCurrent(getStart());
        getOpenList().add(getCurrent());

        for (int i = 0; i < getNodes().length; i++) {
            for (int j = 0; j < getNodes().length-2; j++) {
                if (getEngine().getField().getMap()[i][j] == 1 || getEngine().getField().getMap()[i][j] == 0) {
                    getNodes()[j][i].setBlocked(true);
                }
                if (getEngine().getBlueGhost().getX() == getNodes()[j][i].getX() && getEngine().getBlueGhost().getY() == getNodes()[j][i].getY()) {
                    getNodes()[j][i].setBlocked(true);
                }
                if (getEngine().getOrangeGhost().getX() == getNodes()[j][i].getX() && getEngine().getOrangeGhost().getY() == getNodes()[j][i].getY()) {
                    getNodes()[j][i].setBlocked(true);
                }
                if (getEngine().getRedGhost().getX() == getNodes()[j][i].getX() && getEngine().getRedGhost().getY() == getNodes()[j][i].getY()) {
                    getNodes()[j][i].setBlocked(true);
                }
            }
        }
    }

    /**
     * tracks the shortest way, so that the ghost can walk it
     */
    private void trackPath() {
        Node temp = getCurrent();
        getPathList().add(temp);
        while (temp.hasParent() && temp.getParent() != getStart()) {
            getPathList().add(0, temp.getParent());
            temp = temp.getParent();
        }
    }

    /**
     * checks if there is a wall or a ghost is in the way
     * @return returns a boolean if there is something in the way
     */
    private boolean checkCollision(){
        if (getCurrent().getX() >= getEnd().getX() &&
                getCurrent().getY() == getEnd().getY() &&
                getCurrent().getX() + Player.WIDTH <= getEnd().getX() + Player.WIDTH){
            return true;
        }else return getCurrent().getX() >= getEnd().getX() &&
                getCurrent().getY() >= getEnd().getY() &&
                getCurrent().getY() + getCurrent().getY() + Player.HEIGHT <= getEnd().getY() + Player.HEIGHT &&
                getCurrent().getX() + Player.WIDTH <= getEnd().getX() + Player.WIDTH;
    }


    /**
     * starts the Astar Algorithm
     */
    public void search() {
        while (!getOpenList().isEmpty() && (!checkCollision())) {
            int lowestFIndex = 0;
            for (int i = 0; i < getOpenList().size(); i++) {
                if (getOpenList().get(lowestFIndex).getCostF() > getOpenList().get(i).getCostF()) {
                    lowestFIndex = i;
                }
            }
            setCurrent(getOpenList().get(lowestFIndex));
            if (checkCollision()) {
                trackPath();
                setGoalReached(true);
            }
            getOpenList().remove(getCurrent());
            getClosedList().add(getCurrent());
            var neighbors = getCurrent().getNeighbors();
            for (Node neighbor : neighbors) {
                if (!getClosedList().contains(neighbor)) {
                    var tempG = getGCost(getStart(), neighbor) + getGCost(getCurrent(), neighbor);
                    if (getOpenList().contains(neighbor)) {
                        if (tempG < neighbor.getCostG()) {
                            neighbor.setCostG(tempG);
                        }
                    } else if (!neighbor.isBlocked()) {
                        neighbor.setCostG(tempG);
                        getOpenList().add(neighbor);
                        neighbor.setParent(getCurrent());
                    }
                }

                neighbor.setCostH(getHCost(getEnd(), neighbor));
                neighbor.setCostF(getFCost(neighbor));
            }
        }
    }

    /**
     * adds the neighbors around the node
     */
    private void addNeighbors(Node block) {
        for (int i = 0; i < getNodes().length; i++) {
            for (int j = 0; j < getNodes().length; j++) {
                if (getNodes()[i][j].getX() == block.getX() && getNodes()[i][j].getY() == block.getY()) {
                    if (i + 1 < getNodes().length) {
                        block.getNeighbors().add(getNodes()[i + 1][j]);

                    }
                    if (i > 0) {
                        block.getNeighbors().add(getNodes()[i - 1][j]);

                    }
                    if (j + 1 < getNodes().length) {
                        block.getNeighbors().add(getNodes()[i][j + 1]);

                    }
                    if (j > 0) {
                        block.getNeighbors().add(getNodes()[i][j - 1]);
                    }
                }
            }
        }
    }

    /**
     * Draws the Path of the AStar
     */
    public void draw(Graphics g) {
        if (!getPathList().isEmpty())
            for (Node nodes : getPathList()) {
                g.setColor(Color.green);
                g.drawRect(nodes.getX() + 1, nodes.getY() + 1, Player.WIDTH - 2, Player.HEIGHT - 2);
            }
    }

    /**
     * Calculates the FCost for the current Node
     * @param current Node which is currently the point where the algorithm is
     * @return the GCost plus the HCost
     */
    private int getFCost(Node current) {
        return current.getCostG() + current.getCostH();
    }

    /**
     * Calculates the GCost for the current Node with pythagoras
     * @param start Node where the algorithm started
     * @param current Node which is currently the point where the algorithm is
     * @return the GCost of the Node
     */
    private int getGCost(Node start, Node current) {
        return (int)Math.sqrt(Math.abs(start.getX() - current.getX()) + Math.abs(start.getY() - current.getY()));
    }

    /**
     * Calculates the HCost for the current Node with pythagoras
     * @param end Node where the algorithm ends
     * @param current Node which is currently the point where the algorithm is
     * @return the HCost of the Node
     */
    private int getHCost(Node end, Node current) {
        return (int) Math.sqrt(Math.abs(end.getX() - current.getX()) + Math.abs(end.getY() - current.getY()));
    }


    /**
     * This Class represents one Tile on the Map which is being used for the AStar Algorithm to determine which
     * is the better way to reach the destination
     */
    public static class Node {
        private int costH = 0;
        private int costF = 0;
        private int costG = 0;
        private Node parent;
        private final ArrayList<Node> neighbors = new ArrayList<>();
        private boolean blocked = false;
        private final int x, y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
        public ArrayList<Node> getNeighbors() {
            return neighbors;
        }

        public boolean hasParent() {
            return this.getParent() != null;
        }

        public int getCostH() {
            return costH;
        }

        public void setCostH(int costH) {
            this.costH = costH;
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

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public void setBlocked(boolean blocked) {
            this.blocked = blocked;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
