package PacMan.MainFunction;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the Frame for the Game Pacman
 */
public class PacFrame extends JFrame {

    public PacFrame(){
        this.setTitle("Pac-Man");
        this.add(new PacEngine());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setVisible(true);
    }
}
