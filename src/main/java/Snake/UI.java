package Snake;

import javax.swing.*;

/**
 * @author Niklas
 * @version 1.0000000002 BETA --> ROAD TO RELEASE 01.01.23
 */
public class UI extends JFrame {
    public UI(){
        SnakeField field = new SnakeField();
        this.add(field);
        this.setTitle("SNAKE");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
