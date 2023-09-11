package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


//TODO
// TESTS ERSTELLEN/ÜBEN
// IN TEXT DATEI ABSPEICHER --> HIGHSCORE
// DEATHSCREEN MACHEN


public class SnakeField extends JPanel implements Runnable {
    protected static final int FIELD_WIDTH = 600;
    protected static final int FIELD_HEIGHT = 600;
    protected static final int CUBE_SIZE = 25;
    private static final Dimension FIELD_SIZE = new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
    public static final Random RANDOM = new Random();
    private final ArrayList<Integer> xPosition = new ArrayList<>();
    private final ArrayList<Integer> yPosition = new ArrayList<>();
    private final Integer[] xDirection = {0, 0, 1, -1};
    private final Integer[] yDirection = {1, -1, 0, 0};
    private int direction = 2;
    private Food food;
    private Grid grid;
    private Score score;
    private boolean gameOver = false;
    protected static int SPEED = 200;
    boolean moveT = false;

    public SnakeField() {
        xPosition.add(10);
        yPosition.add(10);
        newFood();
        newGrid();
        newScore();
        this.setFocusable(true);
        this.addKeyListener(new KeyFunction());
        this.setPreferredSize(FIELD_SIZE);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Function resets snake to 1
     */
    public void reset() {
        xPosition.clear();
        yPosition.clear();
        xPosition.add(RANDOM.nextInt(FIELD_WIDTH/CUBE_SIZE));
        yPosition.add(RANDOM.nextInt(FIELD_HEIGHT/CUBE_SIZE));
        newScore();
        SPEED = 200;

    }

    /**
     * creates a new score
     */
    public void newScore() {
        score = new Score(FIELD_WIDTH, FIELD_HEIGHT, xPosition.size()-1);
    }

    public void paint(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        Graphics graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * draws the whole game
     * @param g
     */
    public void draw(Graphics g) {
        grid.draw(g);
        food.draw(g);
        drawSnake(g);
        score.draw(g);
    }

    /**
     * draws the snake
     * @param g
     */
    public void drawSnake(Graphics g) {
        for (int i = 0; i < xPosition.size(); i++) {
            if (i == 0)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.black);
            g.fillRect(xPosition.get(i) * SnakeField.CUBE_SIZE, yPosition.get(i) * SnakeField.CUBE_SIZE, SnakeField.CUBE_SIZE, SnakeField.CUBE_SIZE);
        }
    }
    public void killSnake(){
        int counter = xPosition.size() - 1;
        if (counter > 0 && gameOver) {
            xPosition.remove(counter);
            yPosition.remove(counter);
        }
    }

    /**
     * lets the snake move
     */
    public void move() {
        xPosition.add(0, xPosition.get(0) + xDirection[direction]);
        yPosition.add(0, yPosition.get(0) + yDirection[direction]);
        moveT = true;
        checkCollision();
    }

    /**
     * creates a new grid
     */
    public void newGrid() {
        grid = new Grid();
    }

    /**
     * creates new food
     */
    public void newFood() {
        food = new Food(RANDOM.nextInt((FIELD_WIDTH / SnakeField.CUBE_SIZE)), RANDOM.nextInt((FIELD_HEIGHT / SnakeField.CUBE_SIZE)));
    }

    /**
     * checks if snake is on food, in itself or over the border
     */
    public void checkCollision() {
        if (xPosition.get(0) == food.getX() && yPosition.get(0) == food.getY()) {
            newFood();
            score.setScore(score.getScore() + 1);
            if (SPEED > 0)
                SPEED -= 5;
        } else {
            xPosition.remove(xPosition.size() - 1);
            yPosition.remove(yPosition.size() - 1);
        }
        if (xPosition.get(0) < 0) {
            xPosition.set(0, (FIELD_WIDTH / SnakeField.CUBE_SIZE)-1);
        } else if (xPosition.get(0) > (FIELD_WIDTH / SnakeField.CUBE_SIZE)-1) {
            xPosition.set(0, -1);
        }
        if (yPosition.get(0) < 0) {
            yPosition.set(0, (FIELD_HEIGHT / SnakeField.CUBE_SIZE)-1);
        } else if (yPosition.get(0) > (FIELD_HEIGHT / SnakeField.CUBE_SIZE)-1) {
            yPosition.set(0, -1);
        }
        for (int i = 1; i < xPosition.size(); i++) {
            if (Objects.equals(xPosition.get(i), xPosition.get(0)) && Objects.equals(yPosition.get(0), yPosition.get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            if (!gameOver) {
                move();
            }else
                killSnake();
            repaint();
            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class KeyFunction extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if(moveT)
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> {
                    if (direction != 0) {
                        direction = 1;
                        moveT = false;
                    }
                }

                case KeyEvent.VK_DOWN -> {
                    if (direction != 1)
                        direction = 0;
                    moveT = false;
                }
                case KeyEvent.VK_RIGHT -> {
                    if (direction != 3) {
                        direction = 2;
                        moveT = false;
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if (direction != 2) {
                        direction = 3;
                        moveT = false;
                    }
                }
                case KeyEvent.VK_R -> {
                    gameOver = false;
                    reset();
                }
            }
        }
    }
}
