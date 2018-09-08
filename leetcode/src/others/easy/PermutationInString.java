package others.easy;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/permutation-in-string/description/" />
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 Example 1:
 Input:s1 = "ab" s2 = "eidbaooo"
 Output:True
 Explanation: s2 contains one permutation of s1 ("ba").
 Example 2:
 Input:s1= "ab" s2 = "eidboaoo"
 Output: False
 Note:
 The input strings only contain lower case letters.
 The length of both given strings is in range [1, 10,000].
 * <p>
 * 0. 本质：函数映射
 * 1. 建模：
 * 参考{@link FindAllAnagramsInAString}算法3
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 * 参考{@link FindAllAnagramsInAString}，特别是{@link FindAllAnagramsInAString#findAnagrams3(String, String)}
 *
 * @author changgan on 28/08/2018
 * @see
 * @since cgs-leetcode on  28/08/2018
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (null == s1 || s1.length() == 0 || null == s2 || s2.length() < s1.length()) {
            return false;
        }

        Map<Character, Integer> char2countMap = new HashMap<>();
        for (char c: s1.toCharArray()) {
            Integer count = char2countMap.getOrDefault(c, 0);
            count++;
            char2countMap.put(c, count);
        }

        Map<Character, Integer> tmpMap = new HashMap<>();
        // sliding windows
        LinkedList<Character> windowList = new LinkedList<>();
        Set<Character> zeroCharSet = new HashSet<>();
        for (int i = 0; i < s2.length(); i++) {
            // 维护滑动窗口内的字符和Map的映射关系
            if (windowList.size() >= s1.length()) {
                Character character = windowList.removeFirst();
                if (char2countMap.containsKey(character)) {
                    int count = char2countMap.get(character);
                    count++;
                    char2countMap.put(character, count);
                    if (count == 0) {
                        zeroCharSet.add(character);
                    } else {
                        zeroCharSet.remove(character);
                    }
                }
            }
            windowList.addLast(s2.charAt(i));

            if (char2countMap.containsKey(s2.charAt(i))) {
                Integer count = char2countMap.get(s2.charAt(i));
                count--;
                char2countMap.put(s2.charAt(i), count);
                if (count == 0) {
                    zeroCharSet.add(s2.charAt(i));
                } else {
                    zeroCharSet.remove(s2.charAt(i));
                }
            }

            if (zeroCharSet.size() == char2countMap.size()) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
//        List<Integer> anagrams = new FindAllAnagramsInAString().findAnagrams3("cbaebabacdbcabcab", "abc");
        boolean b = new PermutationInString().checkInclusion("ccc", "cbcccaccac");
        System.out.println(b);
    }
}
