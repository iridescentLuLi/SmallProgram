package person.liushuang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        FrameUI frame = new FrameUI();
        frame.setVisible(true);
    }




    public static void printMap(int[][] map){
        for (int i = 0; i < 9; i ++){
            for (int j = 0; j < 9; j ++){
                System.out.printf(map[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println();
    }
}
