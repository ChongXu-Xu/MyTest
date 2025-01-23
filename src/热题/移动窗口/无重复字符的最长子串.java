package 热题.移动窗口;

import java.util.HashSet;
import java.util.Set;

public class 无重复字符的最长子串 {

    // 难度：中等

    // 给定一个字符串s ，请你找出其中不含有重复字符的 最长子串的长度。

    // 输入: s = "pwwkew"
    // 输出: 3
    // 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    // 请注意，你的答案必须是子串的长度，"pwke" 是一个子序列，不是子串。

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int le = s.length();
        if (le == 0 || le == 1) {
            return le;
        }
        Set<Character> occ = new HashSet<Character>();
        int f = 0, r = 0;
        for (int i = 0; i < le; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (f < le && !occ.contains(s.charAt(f))) {
                // 不断地移动右指针
                occ.add(s.charAt(f));
                f++;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            r = Math.max(r, f - i);
        }
        return r;
    }
}
