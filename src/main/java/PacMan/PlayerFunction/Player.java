package PacMan.PlayerFunction;


import PacMan.MainFunction.Body;
import PacMan.MainFunction.PacEngine;
import PacMan.MainFunction.PacField;

import java.awt.*;

/**
 * This class represents the Player/Pacman and gives it the ability to move
 */

public class Player extends Body {
    private int x, y, life;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    public Player(final int x, final int y, final int life) {
        this.x = x;
        this.y = y;
        this.life = life;
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

    /**
     * Lets the player move on the horizontal and vertical line if the front isn't blocked.
     * The player only moves if one of the arrow keys has been used otherwise it doesn't
     */
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

    /**
     * Checks if there is a Wall in front of the Player
     * @return true or false if there is one in front
     */
    public boolean frontIsBlocked() {
        if ((PacField.map[(getY() / HEIGHT) + 1][getX() / WIDTH] == 1 || PacField.map[(y / HEIGHT) + 1][getX() / WIDTH] == 3) && isDown()) {
            return false;
        } else if (PacField.map[(getY() / HEIGHT) - 1][getX() / WIDTH] == 1 && isUp()) {
            return false;
        } else if (PacField.map[(getY() / HEIGHT)][(getX() / WIDTH) + 1] == 1 && isRight()) {
            return false;
        } else return PacField.map[(getY() / HEIGHT)][(getX() / WIDTH) - 1] != 1 || !isLeft();
    }

    /**
     * Draws Pacman
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(getX(), getY(), WIDTH, HEIGHT);
    }

    /**
     * Lets the Player go left
     */
    public void goLeft(){
        setDown(false);
        setUp(false);
        setRight(false);
        setLeft(true);
    }
    /**
     * Lets the Player go right
     */
    public void goRight(){
        setDown(false);
        setUp(false);
        setRight(true);
        setLeft(false);
    }

    /**
     * Lets the Player go up
     */
    public void goUp(){
        setDown(false);
        setUp(true);
        setRight(false);
        setLeft(false);
    }

    /**
     * Lets the Player go down
     */
    public void goDown(){
        setDown(true);
        setUp(false);
        setRight(false);
        setLeft(false);
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
