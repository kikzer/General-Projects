package PacMan.PlayerFunction;

import PacMan.MainFunction.PacField;

import java.awt.*;

public class Food {
    private final int x, y;
    private final int width, height;
    private final int[] superFoodX = {1, PacField.map.length - 4, 1, PacField.map.length - 4};
    private final int[] superFoodY = {4, 4, PacField.map.length - 6, PacField.map.length - 6};
    private boolean superfood = false;

    public Food(final int x, final int y, final boolean isSuperfood) {
        superfood = isSuperfood;
        if (isSuperfood()) {
            width = 10;
            height = 10;
            this.x = x + 9;
            this.y = y + 9;
        } else {
            width = Player.WIDTH / 5;
            height = Player.HEIGHT / 5;
            this.x = x + 11;
            this.y = y + 11;
        }
    }

    public int[] getSuperFoodX() {
        return superFoodX;
    }

    public int[] getSuperFoodY() {
        return superFoodY;
    }

    public boolean isSuperfood() {
        return superfood;
    }

    public void setSuperfood(boolean superfood) {
        this.superfood = superfood;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(255, 255, 0));
        g.fillRect(x, y, width, height);
    }


}
