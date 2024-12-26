package 热题.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class 三数之和 {

    // 难度：中等

    // 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
    // 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
    // 请你返回所有和为 0 且不重复的三元组。

    // 输入：nums = [-1,0,1,2,-1,-4]
    // 输出：[[-1,-1,2],[-1,0,1]]
    // 解释：
    // nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
    // nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
    // nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
    // 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
    // 注意，输出的顺序和三元组的顺序并不重要。

    public static void main(String[] args) {
        List<List<Integer>> threeSum = threeSum(new int[] {3, 0, -2, -1, 1, 2});
        System.out.print("[");
        for (int x = 0; x < threeSum.size(); x++) {
            if (x != 0) {
                System.out.print(",");
            }
            List<Integer> list = threeSum.get(x);
            System.out.print("[");
            for (int y = 0; y < list.size(); y++) {
                if (y != 0) {
                    System.out.print(",");
                }
                System.out.print(list.get(y));
            }
            System.out.print("]");
        }
        System.out.print("]");
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int first = 0; first < n; first++) {
            if (nums[first] > 0) {
                break;
            }
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = n - 1;
            int target = -nums[first];
            for (int second = first + 1; second < n; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                if (second >= third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
