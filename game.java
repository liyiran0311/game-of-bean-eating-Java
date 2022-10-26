package Demo1;

import java.util.Scanner;

public class game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //创建二维数组
        String[][] place = new String[7][5];
        //赋初值，填空地
        int playerX;
        int playerY;
        //定义玩家积分
        int score1 = 0;
        int score2 = 0;
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[0].length; j++) {
                double rand = Math.random();
                //随机生成豆子，如果小于
                if (rand < 0.3) {
                    place[i][j] = "★";
                } else if (rand < 0.5) {
                    place[i][j] = "※";
                } else if (rand < 0.7) {
                    place[i][j] = "¤";
                } else {
                    place[i][j] = "□";
                }
            }
        }
        //插入玩家,玩家在随机生成，
        playerX = (int) (Math.random() * place[0].length);
        playerY = (int) (Math.random() * place.length);
        place[playerY][playerX] = "㊣";
        int targetX = playerX;
        int targetY = playerY;
        //插入传送门
        int[][] Change = new int[2][2];
        boolean flag = false;

        do {
            Change[0][0] = (int) (Math.random() * place[0].length);  //第一个传送门的x坐标
            Change[0][1] = (int) (Math.random() * place.length);   //第一个传送门的y坐标
            Change[1][0] = (int) (Math.random() * place[0].length);   //第二个传送门的x坐标
            Change[1][1] = (int) (Math.random() * place.length);   //第二个传送门的y坐标
        } while (Change[0][0] != Change[1][0] && Change[0][1] != Change[1][1] && playerX != Change[0][0] && playerX != Change[1][0] && playerY != Change[1][0] && playerY != Change[1][1]);
        place[Change[0][1]][Change[0][0]] = "卍";
        place[Change[1][1]][Change[1][0]] = "卍";
        while (true) {
            for (int i = 0; i < place.length; i++) {
                for (int j = 0; j < place[0].length; j++) {
                    System.out.printf(place[i][j] + " ");
                }
                System.out.println();
            }
            System.out.printf("玩家1积分为" + score1);
            //上移
            System.out.printf("请输入：");
            char cr = sc.next().charAt(0);

            switch (cr) {
                case 'n':
                case 'N':
                    //向上移动
                    //先把y值得到
                    if (targetY == 0) {
                        targetY = place.length - 1; //上下左右贯穿，遇到上而且刚好在最上面，那就自动划到相反位置就可以
                    } else {
                        targetY--;
                    }
                    break;
                case 's':
                case 'S':
                    //向下移动
                    if (targetY == place.length) {
                        targetY = 0;
                    } else {
                        targetY++;
                    }
                    break;
                case 'w':
                case 'W':
                    //向左移动
                    if (targetX == 0) {
                        targetX = place[0].length - 1;
                    } else {
                        targetX--;
                    }
                    break;
                case 'e':
                case 'E':
                    //向右移动
                    if (targetX == place[0].length) {
                        targetX = 0;
                    } else {
                        targetX++;
                    }
                    break;
            }
            int index = -1;
            if (place[targetY][targetX] == "★") {
                score1++;
            } else if (place[targetY][targetX] == "※") {
                score1--;
            } else if (place[targetY][targetX] == "¤") {
                System.exit(0);
            } else if (place[targetY][targetX] == "卍") {
                //到了一个传送门就得到另一个传送门的位置
                for(int i = 0; i < Change.length; i++){
                    if(place[Change[i][1]][Change[i][0]] == place[targetY][targetX]) {
                        index = 1 - i;
                    }
                }
                targetY = Change[index][1];
                targetX = Change[index][0];
                //关联两个传送门
            }
            place[targetY][targetX] = "㊣";
            place[playerY][playerX] = "□";
            playerX = targetX;
            playerY = targetY;
            //添加一个新玩家，两个玩家的位置不一样
            //新玩家要有个位置，两个玩家是轮流走的，一个走完另一个走

        }
    }
}