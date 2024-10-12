package 热题.哈希;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 字母异位词分组 {

    // 难度：中等

    // 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    // 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

    // 输入: strs = [""]
    // 输出: [[""]]
    public static void main(String[] args) {
        String[] s = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groupAnagrams = groupAnagrams(s);
        System.out.println(groupAnagrams.toString());
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> resultList = new ArrayList<>();
        if (strs.length == 0) {

        } else if (strs.length == 1) {
            List<String> oneList = new ArrayList<>();
            oneList.add(strs[0]);
            resultList.add(oneList);
        } else {
            Map<String, List<String>> map = new HashMap<>();
            for (String string : strs) {
                char[] charArray = string.toCharArray();
                Arrays.sort(charArray);
                String genKey = new String(charArray);
                if (!map.containsKey(genKey)) {
                    map.put(genKey, new ArrayList<>());
                }
                map.get(genKey).add(string);
            }
            for (List<String> list : map.values()) {
                resultList.add(list);
            }
        }
        return resultList;
    }
}
