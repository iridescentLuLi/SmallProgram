package person.liushuang;

import java.util.Random;


public class Algorithm {


    public static int[][] createMap( int blanks){
        int[][] map = new int[9][9];
        for (int i = 0; i < 9; i ++){

            for (int j = 0; j < 9; j ++){
                map[i][j] = (j + i) % 9 + 1;
            }
        }

        for (int i = 0; i < 50; i ++){
            Random r1 = new Random();
            Random r2 = new Random();
            Random r3 = new Random();
            Random r4 = new Random();

            //像拧魔方一样，将随机两行互换，随机两列互换，如此反复n次，达到将数组打乱的目的
            int a = r1.nextInt(9);
            int b = (r2.nextInt(8) + a + 1) % 9;

            int c = r3.nextInt(9);
            int d = (r4.nextInt(8) + a + 1) % 9;

            for (int j = 0; j < 9; j ++){
                int temp = map[b][j];
                map[b][j] = map[a][j];
                map[a][j] = temp;
            }
            for (int j = 0; j < 9; j ++){
                int temp = map[j][d];
                map[j][d] = map[j][c];
                map[j][c] = temp;
            }
        }


//        for (int i = 0; i < 9; i ++){
//            for (int j = 0; j < 9; j ++){
//                System.out.printf(map[i][j] + ",");
//            }
//            System.out.println();
//        }
        removeNumber(map, blanks);
        return map;
    }

    public static void removeNumber(int[][] map, int blanks){
        int sumNum = 81;
        int[] loc = new int[sumNum];
        for (int i = 0; i < sumNum; i ++){
            loc[i] = i;
        }

        for (int i = 0; i < blanks; i ++){
            Random ran = new Random();
            int randomBlank = ran.nextInt(sumNum);
            int temp = loc[randomBlank];
            loc[randomBlank] = loc[sumNum - 1];
            loc[sumNum - 1] = temp;
            sumNum --;
        }

        for (int i = 0; i < blanks; i ++){
            int location = loc[80 - i];
            int row = location / 9;
            int col = location % 9;
            map[row][col] = 0;
        }
    }

    public static int check(int[][] map){
        for (int i = 0; i < 9; i ++){
            for (int j = 0; j < 9; j ++){
                if (map[i][j] > 9){
                    map[i][j] /= 10;
                }
            }
        }
        for (int i = 0; i < 9; i ++){
            int[] temp1 = new int[10];
            int[] temp2 = new int[10];
            for (int j = 0; j < 9; j ++){
                temp1[map[i][j]] = 1;
                temp2[map[j][i]] = 1;
            }
            int sum_temp1 = 0;
            for (int j : temp1){
                sum_temp1 += temp1[j];
            }
            if (sum_temp1 != 9){
                return -1;
            }
            int sum_temp2 = 0;
            for (int j : temp2){
                sum_temp2 += temp2[j];
            }
            if (sum_temp2 != 9){
                return -1;
            }
        }
        return 1;
    }
}
