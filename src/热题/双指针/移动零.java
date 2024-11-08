package 热题.双指针;

public class 移动零 {

    // 难度：简单

    // 输入: nums = [0,1,0,3,12]
    // 输出: [1,3,12,0,0]

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.print("[");
        for (int x = 0; x < nums.length; x++) {
            if (x != 0) {
                System.out.print(",");
            }
            System.out.print(nums[x]);
        }
        System.out.print("]");
    }

    private static void moveZeroes(int[] nums) {
        int n = nums.length, l = 0, r = 0;
        while (r < n) {
            if (nums[r] != 0) {
                nums[l] = nums[r];
                l++;
            }
            r++;
        }
        while (l < n) {
            nums[l] = 0;
            l++;
        }
    }
}
