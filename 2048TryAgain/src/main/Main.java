package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

    private Panel key = new Panel();

    public Main(){
        add( key);
        key.setFocusable( true);
    }

    public static void main(String[] args){
        Main frame = new Main();
        frame.setTitle("2048 made in China!");
        frame.setSize(400,400);
        frame.setLocationRelativeTo( null);
        frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        frame.setVisible( true);
    }

    //画图
    static class Panel extends JPanel{
        private int x = 70;
        private int y = 70;
        private int KeyCount = 0;
        int[] map = new int[16];



        public Panel(){
            addKeyListener(new KeyAdapter() {
                @Override

                public void keyPressed(KeyEvent e) {

                    switch (e.getKeyCode()){
                        case KeyEvent.VK_LEFT: KeyCount = 1;break;
                        case KeyEvent.VK_UP: KeyCount = 2;break;
                        case KeyEvent.VK_RIGHT: KeyCount = 3;break;
                        case KeyEvent.VK_DOWN: KeyCount = 4;break;
                        default:KeyCount = 0;
                    }

                    map = Main.move(map, KeyCount);

                    map = Main.locate(map);

                    //map = Main.move(map, KeyCount);

                    repaint();
                }
            });
        }

        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setFont( new Font("TimesRoman",Font.PLAIN,30));
            for (int i = 0; i < 16; i ++){
                int remander = i % 4;
                int LineNumber = i / 4;
                g.drawString(String.valueOf(map[i]),x + remander * 80 ,y + LineNumber * 80);
            }

            //g.drawString(String.valueOf(KeyChar),x +50, y);
        }
    }

    //生成
    static int crate(int i){
        double rand = Math.random();
        int crated;
        if (rand > 0.7){
            crated = 4;
        }
        else
            crated = 2;
        return crated;
    }

    //随机放到一个格子里，如果格子里有东西，就放到随机到的下一个
    static int[] locate(int[] temp){
        int zero = 0;
        int co = 0;
        for (int i : temp){
            if (i == 0){
                zero ++;
            }
        }
        double rand1 = Math.random();
        int rand = (int)(rand1 * zero);


        for (int i = 0; i < 16; i ++){
            if (temp[i] == 0 && co == rand) {
                temp[i] = crate(1);
                break;
            }
            else if (temp[i] == 0) {
                co++;
            }

        }
        return temp;
    }

    static int[] move(int[] temp, int keyinput){
        int[][] map = new int[4][4];
        for(int i = 0; i < 4; i ++){
            for (int j= 0; j < 4; j ++){
                map[i][j] = temp[i * 4 + j];
            }
        }

        switch (keyinput){
            case 1:{
                map = MoveLeft(map);
                break;
            }
            case 2:{
                map = MoveUp(map);
                break;
            }
            case 3:{
                map = MoveRight(map);
                break;
            }
            case 4:{
                map = MoveDown(map);
                break;
            }
        }

        for (int i = 0; i < 4; i ++){
            for (int j = 0; j < 4; j ++){
                temp[i * 4 + j] = map[i][j];
            }
        }

        return temp;

    }

    static int[][] MoveLeft(int[][] map){
        int len = 4;
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < len; j++) {
                if (map[i][j] == 0 && j < 3) {

                    for (int k = j; k < 3; k++) {
                        map[i][k] = map[i][k + 1];
                        map[i][k + 1] = 0;
                    }

                    j--;
                    len--;
                }
            }
            len = 4;

            if (map[i][0] == map[i][1]) {
                if (map[i][2] == map[i][3]) {
                    map[i][0] *= 2;
                    map[i][1] = map[i][2] * 2;
                    map[i][2] = 0;
                    map[i][3] = 0;
                } else {
                    map[i][0] *= 2;
                    map[i][1] = map[i][2];
                    map[i][2] = map[i][3];
                    map[i][3] = 0;
                }
            }
            else if (map[i][1] == map[i][2]){
                map[i][1] *= 2;
                map[i][2] = map[i][3];
                map[i][3] = 0;
            }
            else if (map[i][2] == map[i][3]){
                map[i][2] *= 2;
                map[i][3] = 0;
            }

        }
        return map;
    }

    static int[][] MoveUp(int[][] map){
        int len = 4;
        for (int j = 0; j < 4; j ++) {
            for (int i = 0; i < len; i++) {
                if (map[i][j] == 0 && i < 3) {

                    for (int k = i; k < 3; k++) {
                        map[k][j] = map[k+1][j];
                        map[k+1][j] = 0;
                    }

                    i--;
                    len--;
                }
            }
            len = 4;

            if (map[0][j] == map[1][j]) {
                if (map[2][j] == map[3][j]) {
                    map[0][j] *= 2;
                    map[1][j] = map[2][j] * 2;
                    map[2][j] = 0;
                    map[3][j] = 0;
                } else {
                    map[0][j] *= 2;
                    map[1][j] = map[2][j];
                    map[2][j] = map[3][j];
                    map[3][j] = 0;
                }
            }
            else if (map[1][j] == map[2][j]){
                map[1][j] *= 2;
                map[2][j] = map[3][j];
                map[3][j] = 0;
            }
            else if (map[2][j] == map[3][j]){
                map[2][j] *= 2;
                map[3][j] = 0;
            }

        }
        return map;
    }

    static int[][] MoveRight(int[][] map){
        int len = 4;
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < len; j++) {
                int n = 3 - j;
                if (map[i][n] == 0 && j < 3) {

                    for (int k = n; k > 0; k--) {
                        map[i][k] = map[i][k - 1];
                        map[i][k - 1] = 0;
                    }

                    j--;
                    len--;
                }
            }
            len = 4;

            if (map[i][3] == map[i][2]) {
                if (map[i][0] == map[i][1]) {
                    map[i][3] *= 2;
                    map[i][2] = map[i][1] * 2;
                    map[i][1] = 0;
                    map[i][0] = 0;
                } else {
                    map[i][3] *= 2;
                    map[i][2] = map[i][1];
                    map[i][1] = map[i][0];
                    map[i][0] = 0;
                }
            }
            else if (map[i][2] == map[i][1]){
                map[i][2] *= 2;
                map[i][1] = map[i][0];
                map[i][0] = 0;
            }
            else if (map[i][1] == map[i][0]){
                map[i][1] *= 2;
                map[i][0] = 0;
            }

        }
        return map;
    }

    static int[][] MoveDown(int[][] map){
        int len = 4;
        for (int j = 0; j < 4; j ++) {
            for (int i = 0; i < len; i++) {
                int n = 3 - i;
                if (map[n][j] == 0 && i < 3) {

                    for (int k = n; k > 0; k--) {
                        map[k][j] = map[k - 1][j];
                        map[k - 1][j] = 0;
                    }

                    i--;
                    len--;
                }
            }
            len = 4;

            if (map[3][j] == map[2][j]) {
                if (map[0][j] == map[1][j]) {
                    map[3][j] *= 2;
                    map[2][j] = map[1][j] * 2;
                    map[1][j] = 0;
                    map[0][j] = 0;
                } else {
                    map[3][j] *= 2;
                    map[2][j] = map[1][j];
                    map[1][j] = map[0][j];
                    map[0][j] = 0;
                }
            }
            else if (map[2][j] == map[1][j]){
                map[2][j] *= 2;
                map[1][j] = map[0][j];
                map[0][j] = 0;
            }
            else if (map[1][j] == map[0][j]){
                map[1][j] *= 2;
                map[0][j] = 0;
            }

        }
        return map;
    }

}
