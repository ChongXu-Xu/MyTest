package 热题.动态规划;

public class 接雨水 {

    // 难度：困难

    // 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

    // 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    // 输出：6
    // 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

    // 思路：每个下标的蓄水量 = 左侧最大高度 和 右侧最大高度的最小值 - 下标的高度

    public static void main(String[] args) {
        int[] nums = new int[] {4, 2, 0, 3, 2, 5};
        System.out.println(trap(nums));
    }

    private static int trap(int[] height) {
        int n = height.length;
        if (n == 0 || n == 1) {
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            rightMax[n - 1 - i] = Math.max(rightMax[n - i], height[n - 1 - i]);
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return result;
    }
}
