package PacMan.Ghosts;

import PacMan.MainFunction.PacEngine;
import PacMan.PlayerFunction.Player;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class RedGhost extends Ghost {

    private int x, y, state;
    private boolean eaten;
    int xPosi;
    int yPosi;
    int positionX;
    int positionY;

    public RedGhost(final int x, final int y, final PacEngine engine) {
        super(x, y, engine);
        this.x = x;
        this.y = y;
        xPosi = 1;
        yPosi = 1;
        setPositionX(getxPosi());
        setPositionY(getyPosi());
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        super.draw(g);
    }

    public void update() {
        if (isWeak()) {
            getaStar().setNodes(getX(), getY(), getEngine().getPacman().getX(), getEngine().getPacman().getY() );
        }else {
            setNewPosition();
        }
        if(getX() == getxPosi()*Player.WIDTH && getY() == getyPosi()*Player.HEIGHT ){
            setNewPosition();
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

    /**
     * when the ghost reached the destination, it gets a new random destination
     */
    private void setNewPosition() {
        if (!getaStar().getNodes()[getPositionX()][getPositionY()].isBlocked()) {
            setxPosi(getPositionX());
            setyPosi(getPositionY());
            getaStar().setNodes(getX(), getY(), getxPosi()*Player.WIDTH,getyPosi()*Player.HEIGHT);
        } else {
            setPositionX(ThreadLocalRandom.current().nextInt(1, getEngine().getField().getMap().length/2));
            setPositionY(ThreadLocalRandom.current().nextInt(1, (getEngine().getField().getMap().length / 2)-3));
        }
    }

    public int getWidth() {
        return Player.WIDTH;
    }

    public int getHeight() {
        return Player.HEIGHT;
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
