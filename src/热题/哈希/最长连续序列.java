package 热题.哈希;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 最长连续序列 {

    // 难度：中等

    // 输入：nums = [100,4,200,1,3,2]
    // 输出：4
    // 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

    public static int longestConsecutive(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int i : nums) {
            hashSet.add(i);
        }
        int result = 0;
        for (int i : hashSet) {
            if (!hashSet.contains(i - 1)) {
                // 没有前一个值的时候，证明是数组起始位
                int y = i;
                while (hashSet.contains(y + 1)) {
                    y++;
                }
                result = Math.max(result, y - i + 1);
            }
        }
        return result;
    }

    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;
        Arrays.sort(nums);
        int max = 1;
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1)
                temp++;
            else if (nums[i] == nums[i - 1])
                continue;
            else {
                max = Math.max(max, temp);
                temp = 1;
            }
        }
        return Math.max(max, temp);
    }
}
