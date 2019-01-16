package main;

public class create {
    public static void main(String[] args){
        int[] temp = {
                8, 8, 8, 8,
                0, 0, 8, 8,
                8, 0, 0, 8,
                8, 0, 0, 8};
        temp = locate(temp);

        for (int i : temp)
            System.out.println(i);
    }

    static int crate(int i){
        double rand = Math.random();
        int crated;
        if (rand > 0.6){
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

}
