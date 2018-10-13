package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/palindromic-substrings/description/" />
 * Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 Note:
 The input string length won't exceed 1000.
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 为练习DP，以recurrence relation
 * 参考如下链接找到子问题
 * http://www.zrzahid.com/longest-palindromic-substring-in-on2-time/
 *
 * 设
 * （1）S[0,n]表示长度为n的字符串的字符数组；
 * （2）P(i,j)为表示S[i,j]子串是否为回文，是则为1，不是则为0；其中i<=j。
 * 由于回文的对称性，中心可能为一个或两个字符，如“aba”和“abba”
 * 所以有
 * P(i,i)=1，
 * P(i,i+1) = 1，当S[i,i]=S[i+1,i+1]
 * P(i,i+1) = 0，当S[i,i]!=S[i+1,i+1]
 * P(i,j) = 1，当P(i+1,j-1)=1
 * P(i,j) = 0，当P(i+1,j-1)=0
 *
 * 2. 算法范式：DP
 * 3. 算法：
 * 4. 数据结构：二维数组，只需一半
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/10/2018
 * @see
 * @since cgs-leetcode on  11/10/2018
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();

        int[][] solutions = new int[s.length()][s.length()];

        for(int stepSize=0; stepSize<s.length();stepSize++) {
            for(int i=0;i<s.length()-stepSize;i++) {

                if(chars[i]==chars[i+stepSize]) {
                    if(i+1 > i+stepSize-1) {
                        solutions[i][i+stepSize]=1;
                    } else if(solutions[i+1][i+stepSize-1]==1) {
                        solutions[i][i+stepSize]=1;
                    } else {
                        solutions[i][i+stepSize]=0;
                    }
                } else {
                    solutions[i][i+stepSize]=0;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                count = count + solutions[i][j];
            }
        }
        return count;
    }
}
