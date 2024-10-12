package 热题.哈希;

import java.util.HashMap;
import java.util.Map;

public class 两数之和 {

    // 难度：简单
    
    // 输入：nums = [2,7,11,15], target = 9
    // 输出：[0,1]
    // 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

    public static void main(String[] args) {
        int[] twoSum = twoSum(new int[] {2, 3, 6, 0}, 6);
        System.out.print("[");
        for (int x = 0; x < twoSum.length; x++) {
            if (x != 0) {
                System.out.print(",");
            }
            System.out.print(twoSum[x]);
        }
        System.out.print("]");
    }

    // 暴力破解
    public static int[] twoSum(int[] nums, int target) {
        for (int x = 0; x < nums.length; x++) {
            for (int y = x + 1; y < nums.length; y++) {
                if (nums[x] + nums[y] == target) {
                    return new int[] {x, y};
                }
            }
        }
        return new int[0];
    }

    // 哈希表（还有人使用双指针双向遍历）
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[] {hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
