package PacMan.MainFunction;


import PacMan.PlayerFunction.Food;
import PacMan.Ghosts.BlueGhost;
import PacMan.Ghosts.Ghost;
import PacMan.Ghosts.OrangeGhost;
import PacMan.Ghosts.RedGhost;
import PacMan.PlayerFunction.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PacEngine extends JPanel implements Runnable {

    public static final int WIDTH = 575;
    public static final int HEIGHT = 850;
    private final PacField pacField;
    private final Player pacman;
    private final RedGhost redGhost;
    private final BlueGhost blueGhost;
    private final OrangeGhost orangeGhost;
    private final Score score;
    private boolean win = false;
    private boolean lost = false;
    private final ArrayList<Food> foodList = new ArrayList<>();
    private boolean moveGhost = false;
    private int speed = 0;

    public PacEngine() {
        pacField = new PacField();
        pacman = new Player(((getField().getMap().length) * Player.WIDTH) - 150, HEIGHT / 2, 3);
        redGhost = new RedGhost((getField().getMap().length / 2) * Player.WIDTH, (getField().getMap().length / 2) * Player.HEIGHT, this);
        orangeGhost = new OrangeGhost(((getField().getMap().length / 2) * Player.WIDTH) - Player.WIDTH, (getField().getMap().length / 2) * Player.HEIGHT, this);
        blueGhost = new BlueGhost(((getField().getMap().length / 2) * Player.WIDTH) - Player.WIDTH * 2, ((getField().getMap().length / 2) * Player.HEIGHT), this);
        score = new Score(0, getPacman());
        try {
            score.setHighscore(readTxt());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < getField().getMap().length; i++) {
            for (int j = 0; j < getField().getMap()[0].length; j++) {
                if (getField().getMap()[i][j] == 2) {
                    if ((i == 1 && j == 4) || (i == PacField.map.length - 4 && j == 4) || (i == 1 && j == PacField.map.length - 6) ||
                            (i == PacField.map.length - 4 && j == PacField.map.length - 6)) {
                        getFoodList().add(new Food(j * Player.WIDTH, i * Player.HEIGHT, true));
                    } else {
                        getFoodList().add(new Food(j * Player.WIDTH, i * Player.HEIGHT, false));
                    }
                }
            }
        }

        this.setBackground(Color.black);
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new KeyFunction());
        this.setPreferredSize(dimension);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void showFood(Graphics g) {
        for (Food food : getFoodList()) {
            food.draw(g);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Resets all Ghosts to their starting position
     */
    private void resetGhost() {
        setMoveGhost(false);
        getRedGhost().setX((getField().getMap().length / 2) * Player.WIDTH);
        getRedGhost().setY((getField().getMap().length / 2) * Player.HEIGHT);
        getOrangeGhost().setX(((getField().getMap().length / 2) * Player.WIDTH) - Player.WIDTH);
        getOrangeGhost().setY((getField().getMap().length / 2) * Player.HEIGHT);
        getBlueGhost().setX(((getField().getMap().length / 2) * Player.WIDTH) - Player.WIDTH * 2);
        getBlueGhost().setY((getField().getMap().length / 2) * Player.HEIGHT);
    }

    /**
     * Resets pacman to its starting position
     */
    private void resetPlayerPosition() {
        getPacman().setLeft(false);
        getPacman().setRight(false);
        getPacman().setUp(false);
        getPacman().setDown(false);
        getPacman().setX((getField().getMap().length * Player.WIDTH) - 150);
        getPacman().setY(HEIGHT / 2);
    }

    /**
     * Lets Pacman die
     */
    private void playerDeath() {
        getPacman().setLife(getPacman().getLife() - 1);
        getScore().setLives(getPacman().getLife());
        resetGhost();
        resetPlayerPosition();
    }

    /**
     * IN CONSTRUCTION NO FUNCTIONS
     */
    private void weakMode(){
        int currentTime = (int) (System.currentTimeMillis()/1000);
        System.out.println(currentTime);
    }

    /**
     * Checks if a ghost collided with pacman
     */
    private void checkCollision() {
        if (getRedGhost().isReachedGoal() || getOrangeGhost().isReachedGoal() || getBlueGhost().isReachedGoal()) {
            playerDeath();
        }


        for (int i = 0; i < getFoodList().size(); i++) {
            if (getFoodList().get(i).getX() > getPacman().getX() &&
                    getFoodList().get(i).getY() > getPacman().getY() &&
                    getFoodList().get(i).getX() + getFoodList().get(i).getWidth() < getPacman().getX() + Player.WIDTH &&
                    getFoodList().get(i).getY() + getFoodList().get(i).getHeight() < getPacman().getY() + Player.HEIGHT) {
                if(getFoodList().get(i).isSuperfood()){
                    weakMode();
                    Ghost.setWeak(true);
                }
                getFoodList().remove(getFoodList().get(i));
                getScore().setScore(getScore().getScore() + 10);
            }
        }
        checkGhostCollision();
    }

    private void checkGhostCollision() {
        if (!getRedGhost().getaStar().getClosedList().isEmpty() && !getOrangeGhost().getaStar().getClosedList().isEmpty())
            for (int i = 0; i < getField().getMap().length; i++) {
                for (int j = 0; j < getField().getMap().length; j++) {
                    if (getRedGhost().getaStar().getClosedList().contains(getRedGhost().getaStar().getNodes()[i][j]) == getOrangeGhost().getaStar().getClosedList().contains(getOrangeGhost().getaStar().getNodes()[i][j])) {
                        getRedGhost().getaStar().getNodes()[i][j].setBlocked(true);
                    }
                }
            }
    }

    private void showGhostImage(Graphics g) {
        Image red = new ImageIcon("C:\\Users\\Niklas\\OneDrive\\Eigene Projekte\\GAMES\\Ghost\\redGhost.png").getImage();
        Image orange = new ImageIcon("C:\\Users\\Niklas\\OneDrive\\Eigene Projekte\\GAMES\\Ghost\\orangeGhost.png").getImage();
        Image blue = new ImageIcon("C:\\Users\\Niklas\\OneDrive\\Eigene Projekte\\GAMES\\Ghost\\blueGhost.png").getImage();
        //Image pac = new ImageIcon("C:\\Users\\Niklas\\OneDrive\\Eigene Projekte\\GAMES\\Ghost\\pacman.gif").getImage();

        g.drawImage(red, getRedGhost().getX(), getRedGhost().getY(), getRedGhost().getWidth(), getRedGhost().getHeight(), null);
        g.drawImage(blue, getBlueGhost().getX(), getBlueGhost().getY(), getBlueGhost().getWidth(), getBlueGhost().getHeight(), null);
        g.drawImage(orange, getOrangeGhost().getX(), getOrangeGhost().getY(), getOrangeGhost().getWidth(), getOrangeGhost().getHeight(), null);
    }

    private void search() {
        if (getSpeed() % 4 == 0) {
            setSpeed(getSpeed() + 1);
            if (!getBlueGhost().isReachedGoal() || !getOrangeGhost().isReachedGoal() || !getRedGhost().isReachedGoal()) {
                getRedGhost().search();
                getOrangeGhost().search();
                if (isMoveGhost()) getBlueGhost().search();
            }
        }
        setSpeed(getSpeed() + 1);
    }

    private int readTxt() throws FileNotFoundException {
        File file = new File("C:\\Users\\Niklas\\OneDrive\\Eigene Projekte\\GAMES\\highscore.txt");
        Scanner scanner = new Scanner(file);
        int number = 0;
        while (scanner.hasNextLine()) {
            scanner.skip("Highscore: ");
            number = scanner.nextInt();
        }
        return number;
    }

    private void editTxt() throws FileNotFoundException {
        File file = new File("C:\\Users\\Niklas\\OneDrive\\Eigene Projekte\\GAMES\\highscore.txt");
        Scanner scanner = new Scanner(file);
        int number;
        while (scanner.hasNextLine()) {
            scanner.skip("Highscore: ");
            number = scanner.nextInt();
            if (number < getScore().getScore()) {
                getScore().setHighscore(getScore().getScore());

            }
        }
    }


    private void update() {
        if (getFoodList().isEmpty()) {
            setWin(true);
        } else if (getPacman().getLife() <= 0) {
            setWin(true);
            setLost(true);
        }
        if (getRedGhost().getX() % 3 == 0) {
            setMoveGhost(true);
        }

        getRedGhost().update();
        getOrangeGhost().update();
        getBlueGhost().update();
        search();
        try {
            editTxt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        Graphics graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        g.translate(25, 25);
        if (isWin()) {
            showEnd(g);
        } else {
            getField().draw(g);
            showFood(g);
            getPacman().draw(g);
            getRedGhost().draw(g);
            getOrangeGhost().draw(g);
            getBlueGhost().draw(g);
            getScore().draw(g);
            showGhostImage(g);
        }
    }

    private void showPath(Graphics g) {
        getRedGhost().getaStar().draw(g);
        getBlueGhost().getaStar().draw(g);
        getOrangeGhost().getaStar().draw(g);
    }

    private void showEnd(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.white);
        if (isLost()) {
            g.drawString("!!YOU HAVE LOST THE GAME!!", 180, (getHeight() / 4));
        } else {
            g.drawString("!!YOU WON THE GAME!!", 190, (getHeight() / 4));
        }
        g.drawString("press SPACE to restart", 195, (getHeight() / 4) + 25);
        g.drawString("Your Score: " + getScore().getScore(), 215, (getHeight() / 4) + 100);
    }

    @Override
    public void run() {
        while (true) {
            if (!isWin() || !isLost()) {
                update();
                checkCollision();
                getPacman().move();
            }
            repaint();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public class KeyFunction extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> {
                    getPacman().setDown(true);
                    getPacman().setUp(false);
                    getPacman().setRight(false);
                    getPacman().setLeft(false);
                }
                case KeyEvent.VK_UP -> {
                    getPacman().setDown(false);
                    getPacman().setUp(true);
                    getPacman().setRight(false);
                    getPacman().setLeft(false);
                }
                case KeyEvent.VK_LEFT -> {
                    getPacman().setDown(false);
                    getPacman().setUp(false);
                    getPacman().setRight(false);
                    getPacman().setLeft(true);
                }
                case KeyEvent.VK_RIGHT -> {
                    getPacman().setDown(false);
                    getPacman().setUp(false);
                    getPacman().setRight(true);
                    getPacman().setLeft(false);
                }
                case KeyEvent.VK_D -> setWin(true);
                case KeyEvent.VK_SPACE -> {
                    if (win) {
                        new PacEngine();
                        setWin(false);
                        setLost(false);
                        resetGhost();
                        resetPlayerPosition();
                        getScore().setScore(0);
                        getPacman().setLife(3);
                        getScore().setLives(getPacman().getLife());
                        for (int i = 0; i < getField().getMap().length; i++) {
                            for (int j = 0; j < getField().getMap()[0].length; j++) {
                                if (getField().getMap()[i][j] == 2) {
                                    if ((i == 1 && j == 4) || (i == PacField.map.length - 4 && j == 4) || (i == 1 && j == PacField.map.length - 6) ||
                                            (i == PacField.map.length - 4 && j == PacField.map.length - 6)) {
                                        getFoodList().add(new Food(j * Player.WIDTH, i * Player.HEIGHT, true));
                                    } else {
                                        getFoodList().add(new Food(j * Player.WIDTH, i * Player.HEIGHT, false));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public PacField getPacField() {
        return pacField;
    }

    public boolean isMoveGhost() {
        return moveGhost;
    }

    public void setMoveGhost(boolean moveGhost) {
        this.moveGhost = moveGhost;
    }

    public PacField getField() {
        return pacField;
    }

    public Player getPacman() {
        return pacman;
    }

    public RedGhost getRedGhost() {
        return redGhost;
    }

    public Score getScore() {
        return score;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public BlueGhost getBlueGhost() {
        return blueGhost;
    }

    public OrangeGhost getOrangeGhost() {
        return orangeGhost;
    }
}
