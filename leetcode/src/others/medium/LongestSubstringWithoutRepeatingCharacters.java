package others.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/description/" />
 * 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * 0. 本质：序列-》子序列
 * 1. 建模：
 * recurrence relation
 * 设s为其实位置，元素为c(s)，p(i)
 * 设ls(i)为截止c(i)的最长子串；p(c(i))为和元素c(i)相等的最近位置，可等于自身
 * ls(i)=ls(i-1) + c(i) = string(s,i)，且(s,i)中间元素无重复，即p(c(i)) < s
 * 最长子串maxLs = max(ls(i)), where 0 <= i <= n
 *
 * 初始条件：p(c(0)) = -1;
 * 2. 算法范式：dynamic programming
 * 3. 算法：
 * （1）查看和当前元素相同的索引，和s比较，若大于等于s，则
 *    （a）截取s到当前位置的子串：若子串大于最大子串，则替换，否则不替换；
 *    （b）更新s为和当前元素重复的最近元素的下一个位置，TODO 这一步很重要 <code>s = indexMap.get(chars[i])+1;</code>
 *  （2）更新当前元素的位置为当前位置。
 * 4. 数据结构：
 * （1）若数组只包含小写字母字符，则
 * 数组保存最近索引
 * 5. 改进：
 * 6. 启发：注意边界问题，如abacd，此时遍历到最后一个元素，应该继续判断<code>maxLength = Math.max(maxLength, chars.length - s);</code>
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/09/2017
 * @see
 * @since cgs-leetcode on  18/09/2017
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        Map<Character, Integer> indexMap = new HashMap(1000);

        int s = 0;
        int maxLength = 0;
        for(int i = 0; i < chars.length; i++) {
            if(indexMap.containsKey(chars[i]) && indexMap.get(chars[i]) >= s) {
                maxLength = Math.max(maxLength, i - s);
                s = indexMap.get(chars[i])+1;
            }
            indexMap.put(chars[i], i);
        }
        // last situation
        maxLength = Math.max(maxLength, chars.length - s);
        return maxLength;
    }
}
