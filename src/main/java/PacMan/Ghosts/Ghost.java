package PacMan.Ghosts;

import PacMan.MainFunction.Body;
import PacMan.MainFunction.PacEngine;
import PacMan.PlayerFunction.Player;

import java.awt.*;

/**
 * This class represents the Ghosts general Functions, which they all have in common
 */
public class Ghost extends Body {

    private final PacEngine engine;
    private final AStar aStar;
    private int x, y;

    public Ghost(final int x, final int y, final PacEngine engine) {
        this.x = x;
        this.y = y;
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

    /**
     * Draws the ghost
     */
    @Override
    public void draw(Graphics g) {
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Starts the search of the ghost for a star and lets it move
     */
    public void search() {
        getaStar().search();
        if (!getaStar().getPathList().isEmpty()) {
            move();
        }
    }

    /**
     * Calculates the distance between the ghost and Pacman
     * @param range the range of the ghost where it hunts Pacman
     * @return true or false if Pacman is in range
     */
    public boolean inRange(int range){
        int yDistance = Math.abs(getEngine().getPacman().getY()-(getY()));
        int xDistance = Math.abs(getEngine().getPacman().getX()-(getX()));
        return ((int)Math.sqrt(yDistance+xDistance)*25 <= range);
    }


    /**
     * Checks if the ghost reached pac man and returns a boolean
     */
    public boolean isReachedGoal() {
        return getX() == getEngine().getPacman().getX() && getY() == getEngine().getPacman().getY();
    }

    /**
     * Updates the destination of the Ghost, so it is always following Pacman, if the Ghost has caught Pacman
     */
    public void update() {
        getaStar().setNodes(getX(), getY(), getEngine().getPacman().getX(), getEngine().getPacman().getY());
    }
}
