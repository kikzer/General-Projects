package Snake;

import java.awt.*;

public class Grid extends Rectangle {

    public Grid(){
    }
    public void draw(Graphics g){
        for (int i = 0; i < SnakeField.FIELD_WIDTH / SnakeField.CUBE_SIZE; i++) {
            for (int j = 0; j < SnakeField.FIELD_HEIGHT / SnakeField.CUBE_SIZE; j++) {
                if((i+j) % 2 == 0){
                    g.setColor(new Color(48,111,38));
                }else{
                    g.setColor(new Color(53,104,45));
                }
                g.fillRect(i * SnakeField.CUBE_SIZE, j * SnakeField.CUBE_SIZE, SnakeField.CUBE_SIZE, SnakeField.CUBE_SIZE);
            }
        }
    }
}