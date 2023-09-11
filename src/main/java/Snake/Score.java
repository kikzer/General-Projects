package Snake;

import java.awt.*;

public class Score extends Rectangle {
    protected static int WINDOW_WIDTH;
    protected static int WINDOW_HEIGHT;
    private int score;
    private final int speed = SnakeField.SPEED;

    public Score(int width, int height, int score) {
        WINDOW_HEIGHT = height;
        WINDOW_WIDTH = width;
        this.score = score;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consoles", Font.PLAIN, 20));
        g.drawString("Score: "+ score, 10, 20);
        g.drawString("Speed: "+ (speed-SnakeField.SPEED), WINDOW_WIDTH-110, 20);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
