package person.liushuang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FrameUI extends JFrame {
    static int blanks = 20;
    private Panel_StartPage start_page = new Panel_StartPage();
    private Panel_GamePage ganme_page = new Panel_GamePage();
    int startGameKey = 0;

    public FrameUI(){
        setResizable(false); // 窗体不能改变大小；
        setTitle("Sudoku"); // 标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //窗体关闭则停止程序
        setBounds( 500, 100, 800, 600);//位置大小
        init();
        StartGame();
    }

    class Panel_StartPage extends JPanel {

        private static final int sx = 50;//游戏区域10*10方块的起始横坐标
        private static final int sy = 50;//游戏区域10*10方块的起始纵坐标
        private static final int w = 40;//每个小方格的边长
        private static final int rw = 400;//游戏区域10*10方块的边长

        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setFont( new Font("TimesRoman",Font.PLAIN,50));
            g.drawString("Sudoku！！",170 ,210);
            g.setFont( new Font("TimesRoman", Font.CENTER_BASELINE, 15));
            g.drawString("Press Commond+enter To Get Start", 160, 400);
        }


        public Panel_StartPage(){
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_ENTER: startGameKey = 1;break;
                    }

                    if (startGameKey == 1){
                        StartGame();
                    }
//                    repaint();
                }
            });
        }
    }

    static class Panel_GamePage extends JPanel {
        private static final int sx = 50;//游戏区域10*10方块的起始横坐标
        private static final int sy = 50;//游戏区域10*10方块的起始纵坐标
        private static final int w = 40;//每个小方格的边长
        private static final int rw = 360;//游戏区域10*10方块的边长

        static int[][] map = Algorithm.createMap(blanks);
        int[] location = {0, 0};//选中框位置
        private static final int[][] map_Clone = map.clone();

        public Panel_GamePage(){
            JButton check_Button = new JButton("check!");
            check_Button.setBounds(500, 500, 65, 30);
            super.add(check_Button);
            check_Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Algorithm.check(map) == 1){
                        JFrame win = new JFrame();
                        win.setTitle("you win");
                        win.setLocation(500, 300);
                        win.setSize(250, 250);
                        win.setVisible(true);
                    } else {
                        JFrame lose = new JFrame();
                        lose.setTitle("you lose");
                        lose.setLocation(500, 300);
                        lose.setSize(250, 250);
                        lose.setVisible(true);
                    }

                }
            });
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int moveKey = -1;
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_LEFT: moveKey = 0;break;
                        case KeyEvent.VK_UP: moveKey = 1;break;
                        case KeyEvent.VK_RIGHT: moveKey = 2; break;
                        case KeyEvent.VK_DOWN:moveKey = 3; break;
                        case KeyEvent.VK_ENTER:moveKey = 4;break;

                        case KeyEvent.VK_1:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 10;
                            }
                        }break;
                        case KeyEvent.VK_2:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 20;
                            }
                        }break;
                        case KeyEvent.VK_3:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 30;
                            }
                        }break;
                        case KeyEvent.VK_4:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 40;
                            }
                        }break;
                        case KeyEvent.VK_5:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 50;
                            }
                        }break;
                        case KeyEvent.VK_6:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 60;
                            }
                        }break;
                        case KeyEvent.VK_7:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 70;
                            }
                        }break;
                        case KeyEvent.VK_8:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 80;
                            }
                        }break;
                        case KeyEvent.VK_9:{
                            if (map[location[0]][location[1]] == 0 || map[location[0]][location[1]] > 9){
                                map[location[0]][location[1]] = 90;
                            }
                        }break;
                    }

                    switch (moveKey){
                        case 0:{
                            if (location[0] > 0){
                                location[0] --;
                            }
                        }break;
                        case 1:{
                            if (location[1] > 0){
                                location[1] --;
                            }
                        }break;
                        case 2:{
                            if (location[0] < 8){
                                location[0] ++;
                            }
                        }break;
                        case 3:{
                            if (location[1] < 8){
                                location[1] ++;
                            }
                        }break;
                    }
                    repaint();
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 设置线条颜色为红色
            g.setColor(Color.LIGHT_GRAY);
            // 绘制外层矩形框
            g.drawRect(sx, sy, rw, rw);
            /* 绘制水平10个，垂直10个方格。
             * 即水平方向9条线，垂直方向9条线，
             * 外围四周4条线已经画过了，不需要再画。
             * 同时内部64个方格填写数字。
             */
            for (int i = 1; i < 9; i++) {
                // 绘制第i条竖直线
                g.drawLine(sx + (i * w), sy, sx + (i * w), sy + rw);
                // 绘制第i条水平线
                g.drawLine(sx, sy + (i * w), sx + rw, sy + (i * w));
            }

            g.setColor(Color.RED);
            g.drawRect(sx + location[0] * 40, sy + location[1] * 40, w, w);

            g.setFont(new Font("Tahoma", Font.BOLD, 16));
            for (int i = 0; i < 9; i ++){
                for (int j = 0; j < 9; j ++){
                    if (map[i][j] == 0)continue;
                    if (map[i][j] > 9){
                        g.setColor(Color.BLUE);
                        g.drawString(String.valueOf(map[i][j] / 10), 65 + i * 40, 75 + j * 40);
                        continue;
                    } else
                        g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(map[i][j]), 65 + i * 40, 75 + j * 40);
                }
            }
        }
    }

    private void init(){
        add( start_page);
        start_page.setFocusable(true);
        start_page.setVisible(true);
    }
    private void StartGame(){
        start_page.setVisible(false);
        add( ganme_page);
        ganme_page.setFocusable(true);
        ganme_page.setVisible(true);
    }
}
