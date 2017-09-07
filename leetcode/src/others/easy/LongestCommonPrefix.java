package others.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-common-prefix/description/" />
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * 0. 本质：序列 集合
 * 1. 建模：
 * 求所有序列的前缀的交集
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < Integer.MAX_VALUE; index++) {
            if(strs[0] == null || strs[0].length() == 0 || index >= strs[0].length()) {
                return sb.toString();
            }

            char c = strs[0].charAt(index);
            for(int i = 1; i < strs.length; i++) {
                if(strs[i] == null || strs[i].length() == 0 || index >= strs[i].length() || c != strs[i].charAt(index)) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }


    // ---------------另一种解法，不断缩小前缀长度----------
    public String longestCommonPrefixAnother(String[] strs) {
        if(strs == null || strs.length == 0) return "";

        String prefix = strs[0];

        for(int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if(prefix.equals("")) break;
        }

        return prefix;
    }
}
