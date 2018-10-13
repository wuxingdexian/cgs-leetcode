package dynamicprogramming.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/" />
 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

 Example 1:
 Input: s1 = "sea", s2 = "eat"
 Output: 231
 Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 Deleting "t" from "eat" adds 116 to the sum.
 At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 Example 2:
 Input: s1 = "delete", s2 = "leet"
 Output: 403
 Explanation: Deleting "dee" from "delete" to turn the string into "let",
 adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
 At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 Note:

 0 < s1.length, s2.length <= 1000.
 All elements of each string will have an ASCII value in [97, 122].
 * <p>
 * 0. 本质：组合
 * 1. 建模：
 * recurrence relation -》 DAG
 * 这题使用表格（二维）一开始不好分析，比较时，如果两个字符不相等，则需要拆解到一次删除一个字符，而非同时删除两个，会更容易拆解步骤
 *
 * 参考“https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/solution/”的DP模型
 * 从后往前比较，设dp[i][j]为s1[i,n], s2[j,m]的子串的比较
 * 有如下关系式：
 *      （a）若s1[i]=s2[j]，则dp[i][j]=dp[i+1][j+1]
 *      （b）若s1[i]!=s2[j]，则dp[i][j]=min(dp[i+i][j] + s1[i], dp[i][j+1] + s2[j])。注意：这里取min，
 *      理解为当前两个字符不相等，则分别尝试删除两个字符，得到剩下两个子问题：
 *      s1[i+1,n]和s2[j,m]两个子串比较的子问题，和s1[i,n]和s2[j+1,m]两个子串比较的子问题，然后取两个子问题的最小值。
 *
 * 从前往后比较 TODO
 *
 *
 * 2. 算法范式：DP
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：（1）做些预处理，排除嘈杂；（2）两个字符串比较，不同的字符串内将目标字符相连，使得两个字符串相等，相连的字符串即构成DAG。
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 12/10/2018
 * @see
 * @since cgs-leetcode on  12/10/2018
 */
public class MinimumASCIIDeleteSumForTwoStrings {

    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        /* 从后往前 */
        for(int r = s1.length()-1; r>=0; r--) {
            dp[r][s2.length()] = dp[r+1][s2.length()] + s1.charAt(r);
        }
        for(int c=s2.length()-1; c>=0; c--) {
            dp[s1.length()][c] = dp[s1.length()][c+1] + s2.charAt(c);
        }

        for(int r = s1.length()-1; r>=0;r--) {
            for(int c=s2.length()-1; c>=0; c--) {
                if(s1.charAt(r) == s2.charAt(c)) {
                    dp[r][c] = dp[r+1][c+1];
                } else {
                    dp[r][c] = Math.min(dp[r+1][c] + s1.charAt(r), dp[r][c+1] + s2.charAt(c));
                }
            }
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
        String s1="ccaccjp";
        String s2="fwosarcwge";

        MinimumASCIIDeleteSumForTwoStrings min = new MinimumASCIIDeleteSumForTwoStrings();
        int i = min.minimumDeleteSum(s1, s2);
        System.out.println(i);
    }
    
}
