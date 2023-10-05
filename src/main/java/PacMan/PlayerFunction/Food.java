package PacMan.PlayerFunction;

import PacMan.MainFunction.PacField;

import java.awt.*;

/**
 * This class represents the Food and which size it has
 */
public class Food {
    private final int x, y;
    private final int width, height;
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

    public boolean isSuperfood() {
        return superfood;
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

    /**
     * Draws the Color of Food Tile
     */
    public void draw(Graphics g) {
        g.setColor(new Color(255, 255, 0));
        g.fillRect(x, y, width, height);
    }


}
