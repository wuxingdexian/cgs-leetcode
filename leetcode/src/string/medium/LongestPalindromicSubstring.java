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
 * 参考
 * http://www.zrzahid.com/longest-palindromic-substring-in-on2-time/
 *
 * （3）马拉车算法，利用对称性，从数学角度说明，很🐂B
 * http://www.zrzahid.com/longest-palindromic-substring-in-linear-time/ 从数学的角度阐明了该算法。很厉害，比中文博客一堆描述最后还不懂，厉害。可见数学功底
 *
 * 根据马拉车算法，建模：
 * 核心：回文的对称性！
 * 设定点i的对称半径为p(i)，当前的中心center的坐标为点c，点c的辐射范围（半径）为p(c)，坐标半径最小的坐标点为min，右边为max。
 * 设i为点c右边的点，则根据点c对称性，设左边的对称点为i'，分情况讨论可以得知
 *  1）当i <= max时，若
 *      a) p(i') < i'-min = max-i，则p(i)=p(i')
 *      b) p(i') >= i'-min = max-i，则p(i)=max-i+extending，并且c=i，max=i+p(i)，更换中心点。extending为扩充半径，需要左右对称比较字符来计算
 *  2）当i > max时，p(i)=0+extending，并且c=i，max=i+p(i)，更换中心点。extending为扩充半径，需要左右对称比较字符来计算
 * 注意：因为回文有偶数和奇数长度，当为奇数时，上面建模时成立的，但是为偶数时则失败。
 * 比如：abaaba和abcba。此时在字符串前后以及两两字符间要填充固定的特殊字符，得到2k+1长度的串，为奇数长度。666
 *  3）当计算完所有，找出p(i)最大的点i，然后即得知最大回文串的长度，此时定位原始串的开始或结束为止即可找出该串。
 *  若数组使用0作为起点，那么原始串字符通过x' = 2*x + 1的方式映射到处理串。通过(x'-1)/2即返回定位
 *  画个图即清楚
 *
 * （4）follow up找出所有的回文序列
 *
 * 2. 算法范式：
 * 3. 算法：
 * Manacher’s Algorithm
 * （1）原始字符串预处理
 * （2）遍历处理后的字符串，计算每个字符的p(i)
 * （3）遍历处理后的字符串，找到p(x)最大的点x，通过(x-1)/2定位原始串的开始位置
 * （4）找到长度为p(x)的子串
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 在处理数组边界或映射时很容易搞错。。。。
 * 需要注意计算机整除重复后得到的时下取整
 * 2*i-1 < 2*i < 2*i+1
 * 上面式子都除以2，由于计算机下取整，得到i-1 < i <=i
 * {@link LongestPalindromicSubstring#findTheStartIndex(int, int)} 更加上面下取整的公式，分别对偶数和奇数长度字符串预处理后的字符和#号演练即得到结果
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2017
 * @see
 * @since cgs-leetcode on  20/08/2017
 */
public class LongestPalindromicSubstring {


    /**
     * 参考 TODO 修复
     * http://www.zrzahid.com/longest-palindromic-substring-in-on2-time/
     * @param in
     * @return
     */
    public static String longestPalindromeDP(final String in) {
        if (null == in || in.length() == 0) {
            return in;
        }
        // 记录每个起始index 到 目标 index的最大长度
        int[][] dp = new int[in.length()][in.length()];
        // 初始化都为1
        for (int i = 0; i < in.length(); i++) {
            for (int j = 0; j < in.length(); j++) {
            dp[i][j] = 1;
            }
        }

        int maxLength = 1;
        int startIndex = 0;
        for (int end = 1; end < in.length(); end++) {
            for (int start = 0; start < end; start++) {
                if (in.charAt(start) == in.charAt(end)) {
                    dp[start][end] = 2 + (start+1 <= end-1? dp[start+1][end-1]: 0);
                } else {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
                if (dp[start][end] > maxLength) {
                    startIndex = start;
                    maxLength = dp[start][end];
                }
            }
        }

        return in.substring(startIndex, startIndex + maxLength);
    }
    
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
    //-------------------------------参考答案------------------------

    //-------------------------------独自实现------------------------
    private final char special = '#';
    public String longestPalindrome(String s) {
        char[] preString = preprocess(s);
        int[] radius = new int[preString.length];
        calculatTheLengthOfPalindrome(preString, radius);
        int center = findTheLongestPalindromeCenter(radius);
        int start = findTheStartIndex(center, radius[center]);
        return findTheLongestPalindrome(start, radius[center], s);
    }

    char[] preprocess(String s) {
        char[] preString = new char[2 * s.length() + 1];
        preString[0] = special;
        for(int i = 0; i < s.length(); i++) {
            preString[2 * i + 1] = s.charAt(i);
            preString[2 * i + 2] = special;
        }
        return preString;
    }

    void calculatTheLengthOfPalindrome(char[] preString, int[] radius) {
        radius[0] = 0;
        int center = 0;
        int max = 0;
        for(int i = 1; i < preString.length; i++) {
            if(i <= max) {
                int symmatric = 2 * center - i;
                if(symmatric < 0) {
                    radius[i] = findExtendingLength(preString, i, max + 1);
                    center = i;
                    max = i + radius[i];
                    continue;
                }

                if(radius[symmatric] < max - i) {
                    radius[i] = radius[symmatric];
                } else {
                    radius[i] = max - i + findExtendingLength(preString, i, max + 1);
                    center = i;
                    max = i + radius[i];
                }

            } else {
                radius[i] = findExtendingLength(preString, i, i + 1);
                center = i;
                max = i + radius[i];
            }
        }
    }

    private int findExtendingLength(char[] preString, int center, int rightIndex) {
        int extendingLength = 0;
        while(rightIndex < preString.length && 2 * center - rightIndex >= 0) {
            if(preString[rightIndex] == preString[2 * center - rightIndex]) {
                extendingLength++;
                rightIndex++;
            } else {
                break;
            }
        }
        return extendingLength;
    }

    int findTheLongestPalindromeCenter(int[] radius) {
        int index = 0;
        int r = radius[0];
        for(int i = 0; i < radius.length; i++) {
            if(radius[i] > r) {
                r = radius[i];
                index = i;
            }
        }
        return index;
    }

    // 下取整，容易搞错
    int findTheStartIndex(int center, int radius) {
        return (center - radius) / 2;
    }

    String findTheLongestPalindrome(int start, int length, String s) {
        return s.substring(start, start + length);
    }
    //-------------------------------独自实现------------------------

    public static void main(String[] args) {
        String babad = new LongestPalindromicSubstring().longestPalindromeDP("abcda");
        System.out.println(babad);
    }
}
