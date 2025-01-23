package 热题.子串;

public class 和为K的子数组 {

    // 难度：中等

    // 给你一个整数数组nums和一个整数k，请你统计并返回 该数组中和为k的子数组的个数。

    // 子数组是数组中元素的连续非空序列。

    // 输入：nums = [1,1,1], k = 2
    // 输出：2
    // 输入：nums = [1,2,3], k = 3
    // 输出：2

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 1, 1};
        System.out.println(subarraySum(nums, 2));
    }

    // 方法一：暴力枚举
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
