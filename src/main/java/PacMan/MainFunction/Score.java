package PacMan.MainFunction;

import PacMan.PlayerFunction.Player;

import java.awt.*;

public class Score {
    private int score, lives, highScore;

    public Score(final int highScore, final Player player) {
        this.score = 0;
        this.highScore = highScore;
        lives = player.getLife();
    }

    private void showScore(Graphics g) {
        g.drawString("Score: " + getScore(), 0, -8);
    }

    private void showSHighScore(Graphics g) {
        g.drawString("High-Score: " + getHighScore(), PacEngine.WIDTH / 2, -8);
    }

    private void showLife(Graphics g) {
        g.drawString("Lives: " + getLives(), 0, (PacField.map.length * Player.HEIGHT) + 25);
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        showLife(g);
        showScore(g);
        showSHighScore(g);
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

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
