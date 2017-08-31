package string.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-uncommon-subsequence-i/description/" />
 * 521. Longest Uncommon Subsequence I
 * Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

 A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

 The input will be two strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

 Example 1:
 Input: "aba", "cdc"
 Output: 3
 Explanation: The longest uncommon subsequence is "aba" (or "cdc"),
 because "aba" is a subsequence of "aba",
 but not a subsequence of any other strings in the group of two strings.
 Note:

 Both strings' lengths will not exceed 100.
 Only letters from a ~ z will appear in input strings.

 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class LongestUncommonSubsequenceI {
    public int findLUSlength(String a, String b) {
        return !a.equals(b)? Math.max(a.length(), b.length()): -1;
    }
}
