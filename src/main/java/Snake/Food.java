package Snake;

import java.awt.*;

public class Food extends Rectangle {
    public Food(int x, int y){
        super(x,y,SnakeField.CUBE_SIZE,SnakeField.CUBE_SIZE);
    }
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x*SnakeField.CUBE_SIZE,y*SnakeField.CUBE_SIZE,SnakeField.CUBE_SIZE,SnakeField.CUBE_SIZE);
    }
}
