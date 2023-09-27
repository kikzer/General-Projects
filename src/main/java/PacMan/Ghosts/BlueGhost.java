package PacMan.Ghosts;


import PacMan.MainFunction.PacEngine;
import PacMan.PlayerFunction.Player;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class BlueGhost extends Ghost {

    private int x, y, escapePositionX, escapePositionY, positionX, positionY;

    public BlueGhost(final int x, final int y, final PacEngine engine) {
        super(x, y, engine);
        this.x = x;
        this.y = y;
        escapePositionX = getEngine().getField().getMap().length - 4;
        escapePositionY = 5;
        setPositionX(getEscapePositionX());
        setPositionY(getEscapePositionY());
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        super.draw(g);
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
        if (getX() == getEscapePositionX() * Player.WIDTH && getY() == getEscapePositionY() * Player.HEIGHT) {
            setNewPosition();
        }

    }

    public int getRadius() {
        return 400;
    }

    /**
     * when the ghost reached the destination, it gets a new random destination
     */
    private void setNewPosition() {
        if (!getaStar().getNodes()[getPositionX()][getPositionY()].isBlocked()) {
            setEscapePositionX(getPositionX());
            setEscapePositionY(getPositionY());
            getaStar().setNodes(getX(), getY(), getEscapePositionX() * Player.WIDTH, getEscapePositionY() * Player.HEIGHT);
        } else {
            setPositionX(ThreadLocalRandom.current().nextInt(getEngine().getField().getMap().length / 2, getEngine().getField().getMap().length - 2));
            setPositionY(ThreadLocalRandom.current().nextInt(2, (getEngine().getField().getMap().length / 2) - 3));
        }
    }

    public int getEscapePositionX() {
        return escapePositionX;
    }

    public void setEscapePositionX(int escapePositionX) {
        this.escapePositionX = escapePositionX;
    }

    public int getEscapePositionY() {
        return escapePositionY;
    }

    public void setEscapePositionY(int escapePositionY) {
        this.escapePositionY = escapePositionY;
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


}
