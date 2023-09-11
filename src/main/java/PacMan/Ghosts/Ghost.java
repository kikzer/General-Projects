package PacMan.Ghosts;

import PacMan.Body;
import PacMan.MainFunction.PacEngine;
import PacMan.PlayerFunction.Player;

import java.awt.*;


public class Ghost extends Body {

    private final PacEngine engine;
    private final AStar aStar;
    private int x, y;
    private static boolean weak;

    public Ghost(final int x, final int y, final PacEngine engine) {
        this.x = x;
        this.y = y;
        weak = false;
        this.engine = engine;
        this.aStar = new AStar(engine);
    }

    public PacEngine getEngine() {
        return engine;
    }

    public AStar getaStar() {
        return aStar;
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

    public static boolean isWeak() {
        return !weak;
    }

    public static void setWeak(boolean weak) {
        weak = weak;
    }

    /**
     * Lets the ghost move which has been calculated by the astar
     */
    public void move() {
        final int pathX = getaStar().getPathList().get(0).getX();
        final int pathY = getaStar().getPathList().get(0).getY();
        final int ghostX = getX();
        final int ghostY = getY();


        //UP
        if (ghostX == pathX && ghostY < pathY) {
            setY(getY() + Player.HEIGHT);
            getaStar().getPathList().remove(0);
        } else

            //DOWN
            if (ghostX == pathX && ghostY > pathY) {
                setY(getY() - Player.HEIGHT);
                getaStar().getPathList().remove(0);
            }

        //LEFT
        if (pathY == ghostY && pathX < ghostX) {
            setX(getX() - Player.WIDTH);
            getaStar().getPathList().remove(0);

        } else

            //RIGHT
            if (pathY == ghostY && pathX > ghostX) {
                setX(getX() + Player.WIDTH);
                getaStar().getPathList().remove(0);
            }
    }

    public int getWidth() {
        return Player.WIDTH;
    }

    public int getHeight() {
        return Player.HEIGHT;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public void search() {
        getaStar().search();
        if (!getaStar().getPathList().isEmpty()) {
            move();
        }
    }

    /**
     * uses Pythagoras to calculate the destination between two points and returns it
     */
    public int getDestination(int xStart, int yStart, int xEnd, int yEnd) {
        return Math.abs(xStart - xEnd) + Math.abs(yStart - yEnd);
    }

    /**
     * Checks if the ghost reached pac man and returns a boolean
     */
    public boolean isReachedGoal() {
        return getX() == getEngine().getPacman().getX() && getY() == getEngine().getPacman().getY();
    }
}
