package 热题.双指针;

public class 盛最多水的容器 {

    // 难度：中等

    // 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
    // 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    // 返回容器可以储存的最大水量

    // 输入：[1,8,6,2,5,4,8,3,7]
    // 输出：49

    public static void main(String[] args) {
        int[] nums = new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea2(nums));
    }

    private static int maxArea(int[] height) {
        int l = 0, r = height.length - 1, res = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                res = Math.max(height[l] * (r - l), res);
                l++;
            } else {
                res = Math.max(height[r] * (r - l), res);
                r--;
            }
        }
        return res;
    }

    // 递归去把数组往下拆分

    public static int water;

    private static int maxArea2(int[] height) {
        int len = height.length;
        int i = 0, j = len - 1;
        findMaxWater(height, i, j);
        return water;
    }

    private static void findMaxWater(int[] height, int i, int j) {
        if (i >= j) {
            return;
        }
        water = Math.max((j - i) * Math.min(height[i], height[j]), water);
        int left = height[i];
        int right = height[j];
        if (left > right) {
            while (j > i && height[j] <= right) {
                j--;
            }
            findMaxWater(height, i, j);
        } else {
            while (i < j && height[i] <= left) {
                i++;
            }
            findMaxWater(height, i, j);
        }
    }
}
