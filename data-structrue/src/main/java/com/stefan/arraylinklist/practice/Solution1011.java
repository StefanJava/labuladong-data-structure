package com.stefan.arraylinklist.practice;

/**
 * @Description: 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。
 * 我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * @Date: 2023/1/17 13:55
 * @Author: stefanyang
 */
public class Solution1011 {

    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        // 注意，right 是开区间，所以额外加一
        int right = 1;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(weights, mid) == days) {
                // 搜索左侧边界，则需要收缩右侧边界
                right = mid;
            } else if (f(weights, mid) < days) {
                // 需要让 f(x) 的返回值大一些
                right = mid;
            } else if (f(weights, mid) > days) {
                // 需要让 f(x) 的返回值小一些
                left = mid + 1;
            }
        }

        return left;
    }

    // 定义：当运载能力为 x 时，需要 f(x) 天运完所有货物
    // f(x) 随着 x 的增加单调递减
    int f(int[] weights, int x) {
        int days = 0;
        for (int i = 0; i < weights.length; ) {
            // 尽可能多装货物
            int cap = x;
            while (i < weights.length) {
                if (cap < weights[i]) break;
                else cap -= weights[i];
                i++;
            }
            days++;
        }
        return days;
    }

}
