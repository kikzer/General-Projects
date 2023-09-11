package Snake;

import java.awt.*;
import java.util.ArrayList;

public class DeathScreen extends Rectangle {
    private final int cornerCounter = 0;
    private final int width = (SnakeField.FIELD_WIDTH);
    private final int height = (SnakeField.FIELD_HEIGHT);
    private int counterX;
    private int counterY;
    private int step = 0;
    private int state = 0;
    private int stepper = 2;
    private int turn = 0;
    private final ArrayList<Integer> pX = new ArrayList<>();
    private final ArrayList<Integer> pY = new ArrayList<>();


    public DeathScreen(int x, int y) {
        counterX = SnakeField.RANDOM.nextInt((width / SnakeField.CUBE_SIZE)-1);
        counterY = SnakeField.RANDOM.nextInt((height / SnakeField.CUBE_SIZE)-1);
    }

    private void createSpiral() {

        int stepSize = 1;
        switch (state) {
            case 0 -> {
                if (counterX < (width / SnakeField.CUBE_SIZE)-1 &&counterY < (height / SnakeField.CUBE_SIZE)-1)
                    counterX += stepSize;
            }
            case 1 -> {
                if (counterY < (height / SnakeField.CUBE_SIZE)-1 &&counterX < (width / SnakeField.CUBE_SIZE)-1)
                    counterY += stepSize;
            }
            case 2 -> {
                if (counterX > 0 )
                    counterX -= stepSize;
            }
            case 3 -> {
                if (counterY > 0 )
                    counterY -= stepSize;
            }
        }
        if (step % stepper == 0) {
            state = (state + 1) % 4;
            turn++;
            if (turn % 2 == 0) {
                stepper++;
            }
        }
        step++;
        pX.add(counterX);
        pY.add(counterY);
    }


    public void draw(Graphics g) {

        g.setColor(Color.gray);
        for (int i = 0; i < pX.size(); i++) {
            g.fillRect(pX.get(i) * SnakeField.CUBE_SIZE, pY.get(i) * SnakeField.CUBE_SIZE, SnakeField.CUBE_SIZE, SnakeField.CUBE_SIZE);
        }
        createSpiral();
    }

    public void setCounterX(int counterX) {
        this.counterX = counterX;
    }

    public void setCounterY(int counterY) {
        this.counterY = counterY;
    }

}
