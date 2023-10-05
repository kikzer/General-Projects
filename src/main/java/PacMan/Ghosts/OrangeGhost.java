package PacMan.Ghosts;

import PacMan.MainFunction.PacEngine;
import PacMan.PlayerFunction.Player;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents the orange Ghost, which is following Pacman if it is in Range
 */
public class OrangeGhost extends Ghost {

    private int x, y;
    private int positionX;
    private int positionY;
    private int ghostPositionX;
    private int ghostPositionY;


    public OrangeGhost(final int x, final int y, final PacEngine engine) {
        super(x, y, engine);
        this.x = x;
        this.y = y;
        ghostPositionX = 4;
        ghostPositionY = getEngine().getField().getMap().length - 4;
        setPositionX(getGhostPositionX());
        setPositionY(getGhostPositionY());

    }

    public int getGhostPositionX() {
        return ghostPositionX;
    }

    public void setGhostPositionX(int ghostPositionX) {
        this.ghostPositionX = ghostPositionX;
    }

    public int getGhostPositionY() {
        return ghostPositionY;
    }

    public void setGhostPositionY(int ghostPositionY) {
        this.ghostPositionY = ghostPositionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
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

    public int getWidth() {
        return Player.WIDTH;
    }

    public int getHeight() {
        return Player.HEIGHT;
    }

    public int getRadius() {
        return 306;
    }

    public void draw(Graphics g) {
        g.setColor(Color.orange);
        super.draw(g);
    }


    /**
     * when the ghost reached the destination, it gets a new random destination
     */
    private void setNewPosition() {
        if (!getaStar().getNodes()[getPositionX()][getPositionY()].isBlocked()) {
            setGhostPositionX(getPositionX());
            setGhostPositionY(getPositionY());
            getaStar().setNodes(getX(), getY(), getGhostPositionX() * Player.WIDTH, getGhostPositionY() * Player.HEIGHT);
        } else {
            setPositionX(ThreadLocalRandom.current().nextInt(2, getEngine().getField().getMap().length / 2));
            setPositionY(ThreadLocalRandom.current().nextInt((getEngine().getField().getMap().length / 2) + 3, (getEngine().getField().getMap().length - 3)));
        }
    }

    /**
     * checks if pac man is in the range of the ghost and so changes the behavior
     */
    @Override
    public void update() {
        if (inRange(getRadius())) {
            getaStar().setNodes(getX(), getY(), getEngine().getPacman().getX(), getEngine().getPacman().getY());
        } else {
            setNewPosition();
        }
        if (getX() == getGhostPositionX() * Player.WIDTH && getY() == getGhostPositionY() * Player.HEIGHT) {
            setNewPosition();
        }
    }
}
