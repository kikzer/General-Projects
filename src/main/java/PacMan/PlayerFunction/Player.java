package PacMan.PlayerFunction;


import PacMan.Body;
import PacMan.MainFunction.PacEngine;
import PacMan.MainFunction.PacField;

import java.awt.*;

public class Player extends Body {
    private int x, y, life;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;
    private boolean left = true;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private int stepcounter = 0;

    public Player(final int x, final int y, final int life) {
        this.x = x;
        this.y = y;
        this.life = life;
    }

    public int getStepcounter() {
        return stepcounter;
    }

    public void setStepcounter(int stepcounter) {
        this.stepcounter = stepcounter;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDistance(int x, int y) {
        return Math.abs(getX() - x) + Math.abs(getY() - y);
    }

    @Override
    public void move() {
        if (right && !left && !up && !down && getX() < PacEngine.WIDTH - WIDTH * 3 && frontIsBlocked()) {

            setX(getX() + getSpeed());
        } else if (!right && left && !up && !down && getX() > 0 && frontIsBlocked()) {

            setX(getX() - getSpeed());
        } else if (!right && !left && up && !down && getY() > HEIGHT && frontIsBlocked()) {

            setY(getY() - getSpeed());
        } else if (!right && !left && !up && down && getY() < PacEngine.HEIGHT - HEIGHT * 3 && frontIsBlocked()) {

            setY(getY() + getSpeed());
        } else {
            setX(getX());
            setY(getY());
            right = false;
            left = false;
            up = false;
            down = false;
        }

    }

    public boolean frontIsBlocked() {
        if ((PacField.map[(getY() / HEIGHT) + 1][getX() / WIDTH] == 1 || PacField.map[(y / HEIGHT) + 1][getX() / WIDTH] == 3) && isDown()) {
            return false;
        } else if (PacField.map[(getY() / HEIGHT) - 1][getX() / WIDTH] == 1 && isUp()) {
            return false;
        } else if (PacField.map[(getY() / HEIGHT)][(getX() / WIDTH) + 1] == 1 && isRight()) {
            return false;
        } else return PacField.map[(getY() / HEIGHT)][(getX() / WIDTH) - 1] != 1 || !isLeft();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(getX(), getY(), WIDTH, HEIGHT);
    }

    public int getSpeed() {
        return WIDTH;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
