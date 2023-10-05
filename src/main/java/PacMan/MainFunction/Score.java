package PacMan.MainFunction;

import PacMan.PlayerFunction.Player;

import java.awt.*;

/**
 * This class represents the Score, which is showing life and points of Pacman
 */
public class Score {
    private int score, lives;

    public Score(final Player player) {
        this.score = 0;
        lives = player.getLife();
    }

    /**
     * Draws the current Score of the Player
     */
    private void showScore(Graphics g) {
        g.drawString("Score: " + getScore(), 0, -8);
    }

    /**
     * Draws the current Score of the Player
     */
    private void showLife(Graphics g) {
        g.drawString("Lives: " + getLives(), 0, (PacField.map.length * Player.HEIGHT) + 25);
    }

    /**
     * Draws the current Score and Live the Player
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        showLife(g);
        showScore(g);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
