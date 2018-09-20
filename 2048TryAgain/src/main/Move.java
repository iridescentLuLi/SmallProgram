package main;

public class Move {
    public static void main(String[] args){
        int[] temp = {
                4, 0, 0, 2,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0};
        temp = move(temp,3);
    }

    //left = 1 ; up = 2; right = 3; down = 4;
    static int[] move(int[] temp, int keyinput){
        int[][] map = new int[4][4];
        for(int i = 0; i < 4; i ++){
            for (int j= 0; j < 4; j ++){
                map[i][j] = temp[i * 4 + j];
            }
        }

        switch (keyinput){
            case 1:{
                //先全部左移至顶
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
        int b = 0;
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
                if (map[i][1] == map[i][0]) {
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

        int c = 0;
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
        int a = 0;
        return map;
    }
}
