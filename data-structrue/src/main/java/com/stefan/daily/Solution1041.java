package com.stefan.daily;

/**
 * @description: 1041. 困于环中的机器人
 * @author: stefanyang
 * @date: 2023/4/11 14:15
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution1041 {
    public static boolean isRobotBounded(String instructions) {
        // 0->北 1->东 2->南 3->西
        int direct = 0;
        int x = 0;
        int y = 0;
        int len = instructions.length();
        for (int i = 0; i < len; i++) {
            char c = instructions.charAt(i);
            if (c == 'G') {
                if (direct == 0) {
                    y++;
                }
                if (direct == 1) {
                    x++;
                }
                if (direct == 2) {
                    y--;
                }
                if (direct == 3) {
                    x--;
                }
            }
            if (c == 'L') {
                direct = (--direct + 4) % 4;
            }
            if (c == 'R') {
                direct = ++direct % 4;
            }
        }
        return direct != 0 || (x == 0 && y == 0);
    }

    public static void main(String[] args) {
        String instructions = "GLRLLGLL";
        System.out.println(isRobotBounded(instructions));
    }
}
