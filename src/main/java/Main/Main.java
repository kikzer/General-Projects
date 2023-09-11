package Main;

import Algorithm.Sort;
import PacMan.MainFunction.PacFrame;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //new BrickMain();
        //new UI();
        //new GameField();
        //new TestStuff();
        //new Matrix();
        //new Frame();
        new PacFrame();
        //algo();
    }

    static void algo(){
        Integer[] array = {8,2,1,5,9,7,3};

        System.out.println(Arrays.toString(new Sort().quickSort(array,0,array.length-1)));
    }

    static int ggT(int num1, int num2) {
        int storage;

        while (num1 % num2 != 0) {
            storage = num1 % num2;
            num1 = num2;
            num2 = storage;

        }
        return num2;
    }

    static boolean isPrim(final int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
