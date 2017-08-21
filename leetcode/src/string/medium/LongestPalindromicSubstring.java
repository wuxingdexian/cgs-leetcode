package string.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/description/" />
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:

 Input: "babad"

 Output: "bab"

 Note: "aba" is also a valid answer.
 Example:

 Input: "cbbd"

 Output: "bb"
 * <p>
 * 1. 建模：
 * （1）后缀树
 * http://blog.csdn.net/v_july_v/article/details/6897097
 * 这里提到后缀树在处理字符串时的很多应用场景
 *
 * （2）dynamic programming
 * 尽然找到动态规划的子问题。
 *
 * （3）马拉车算法，利用对称性，从数学角度说明，很🐂B
 * http://www.zrzahid.com/longest-palindromic-substring-in-linear-time/ 从数学的角度阐明了该算法。很厉害，比中文博客一堆描述最后还不懂，厉害。可见数学功底
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2017
 * @see
 * @since cgs-leetcode on  20/08/2017
 */
public class LongestPalindromicSubstring {
    
    
    //------------------------参考说明------------------------
    // TODO: 20/08/2017 http://www.zrzahid.com/longest-palindromic-substring-in-linear-time/ 从数学的角度阐明了该算法。很厉害，比中文博客一堆描述最后还不懂，厉害。可见数学功底
    public static String normalize(final String in) {
        final StringBuffer sb = new StringBuffer();
        sb.append('$');
        for (int i = 0; i < in.length(); i++) {
            sb.append(in.charAt(i));
            sb.append('$');
        }
        return sb.toString();
    }

    public static String longestPalindromeLinear(final String in) {
    /*
     * Normalize the length of the string by inserting special character ‘$’ in the space between each pair of
     * characters in the input and the two outside edges. This is done merely to make the computation identical for
     * both odd and even length input string.
     */
        final String S = normalize(in);
        int c = 0; // contains center of current palindrome
        int max = 0; // contains the right edge of current palindrome

        // P[i] contains the length of longest palindrome (in S, not original) centered at i
        final int[] P = new int[S.length()];
        for (int i = 0; i < P.length; i++) {
            P[i] = 0;
        }
        // for each position find longest palindrome centered at the position, save length in P
        for (int i = 1; i < S.length() - 1; i++) {
            final int i_mirror = 2 * c - i; // as (C - i_mirror) = (i - C) due to symmetric property

        /*
         * When max-i > P[i_mirror] then palindrome at center i_prime contained completely within palindrome
         * centered at c. Else max-i <= P[i_mirror] means that the palindrome at ceter i_mirror doesn’t fully
         * contained in the palindrome centered at c. In that case palindrome at i could be extended past max.
         */

            P[i] = (max > i) ? Math.min(P[i_mirror], max - i) : 0;

            // Try to expand the palindrome centered at i. if the palindrome centered at i could be extended past max
            // then extend max to the right edge of newly extended palindrome
            while ((i + P[i] + 1) < S.length() && (i - P[i] - 1) >= 0
                    && S.charAt(i + P[i] + 1) == S.charAt(i - P[i] - 1)) {
                P[i]++;
            }
            // If palindrome centered at was extend past max then update Center to i and update the right edge
            if (i + P[i] > max) {
                c = i;
                max = i + P[i];
            }
        }
        return extractLongest(in, P);
    }

    public static String extractLongest(final String in, final int[] P) {
        int longestCenter = 0;
        int longestLength = 0;

        for (int i = 0; i < P.length; i++) {
            if (P[i] > longestLength) {
                longestLength = P[i];
                longestCenter = i;
            }
        }

        final int offset = (longestCenter - longestLength) / 2;
        return in.substring(offset, offset + longestLength);
    }

    public Set<String> allPalindromicSubstring(final String in, final int[] P) {
        final HashSet<String> all = new HashSet<String>();

        for (int i = 0; i < P.length; i++) {
            if (P[i] != 0) {
                final int offset = (i - P[i]) / 2;
                all.add(in.substring(offset, offset + P[i]));
            }
        }

        return all;
    }
    //-----------
}
