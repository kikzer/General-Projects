package PacMan.Ghosts;


import PacMan.MainFunction.PacEngine;
import PacMan.PlayerFunction.Player;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class BlueGhost extends Ghost {

    private int x, y, state, xPosi, yPosi, positionX, positionY;
    private boolean eaten;

    public BlueGhost(final int x, final int y, final PacEngine engine) {
        super(x, y, engine);
        this.x = x;
        this.y = y;
        xPosi = getEngine().getField().getMap().length - 4;
        yPosi = getEngine().getField().getMap().length - 4;
        setPositionX(getxPosi());
        setPositionY(getyPosi());
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        super.draw(g);
    }

    /**
     * checks if pac man is in the range of the ghost and so changes the behavior
     */
    public void update() {
        if ((getEngine().getPacman().getDistance(getX() + getWidth() / 2, getY() + getHeight() / 2) <
                getDestination(getX(),getY(),(int) (getX() - getRadius() * 0.475), (int) (getY() - getRadius() * 0.475))-150 ) && isWeak()) {
            getaStar().setNodes(getX(), getY(), getEngine().getPacman().getX(), getEngine().getPacman().getY() );
        }else {
            setNewPosition();
        }
        if(getX() == getxPosi()* Player.WIDTH && getY() == getyPosi()*Player.HEIGHT ){
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
            setxPosi(getPositionX());
            setyPosi(getPositionY());
            getaStar().setNodes(getX(), getY(), getxPosi()*Player.WIDTH,getyPosi()*Player.HEIGHT);
        } else {
            setPositionX(ThreadLocalRandom.current().nextInt(getEngine().getField().getMap().length / 2, getEngine().getField().getMap().length - 2));
            setPositionY(ThreadLocalRandom.current().nextInt(2, (getEngine().getField().getMap().length / 2)-3));
        }
    }

    public int getxPosi() {
        return xPosi;
    }

    public void setxPosi(int xPosi) {
        this.xPosi = xPosi;
    }

    public int getyPosi() {
        return yPosi;
    }

    public void setyPosi(int yPosi) {
        this.yPosi = yPosi;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }


}
