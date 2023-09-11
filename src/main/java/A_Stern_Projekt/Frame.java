package A_Stern_Projekt;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame(){
        this.add(new Panel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("A Stern Algorithmus");
        this.pack();
        this.setVisible(true);
    }
}
