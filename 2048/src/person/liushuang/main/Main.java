package person.liushuang.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int[][] Map = new int[4][4];
        int[] temp = new int[16];
        int c = crate(1);
        Scanner input = new Scanner(System.in);

        while (true){
            temp = locate(temp, c);
            input.hasNext();

            System.out.println(Map);
        }



    }

    public static int crate(int i){
        double rand = Math.random();
        int crated;
        if (rand > 0.8){
            crated = 4;
        }
        else
            crated = 2;
        return crated;
    }

    public static int[] locate(int[] temp, int cra){
        int rand = (int)Math.random() * 16;
        while (rand == 0){
            rand ++;
        }
        temp[rand] = cra;
        return temp;
    }
}
