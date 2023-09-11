package A_Stern_Projekt;



import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private static final int MAXROW = WIDTH / Node.WIDTH;
    private static final int MAXCOL = HEIGHT / Node.HEIGHT;

    public static final Node[][] position = new Node[MAXROW][MAXCOL];
    AStar aStar;

    public Panel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(MAXROW, MAXCOL));
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position.length; j++) {
                position[i][j] = new Node(i * Node.WIDTH, j * Node.WIDTH, 1, Color.black);
            }
        }
        for(int i = 10; i<20; i++){
            position[i][i].setBlocked(true);
            position[i][i].setColor(Color.black);
            position[i][i].setType(0);
        }
        for(int i = 10; i<20; i++){
            position[9][i].setBlocked(true);
            position[9][i].setColor(Color.black);
            position[9][i].setType(0);
        }
        aStar = new AStar(position[0][0], position[10][11]);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void paint(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        Graphics graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        for (Node[] node : position) {
            for (int j = 0; j < position.length; j++) {
                node[j].draw(g);
            }
        }
        aStar.draw(g);
    }

    @Override
    public void run() {
        while (true) {
            aStar.search();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
