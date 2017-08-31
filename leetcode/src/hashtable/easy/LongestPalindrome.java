package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-palindrome/description/" />
 * 409. Longest Palindrome
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

 This is case sensitive, for example "Aa" is not considered a palindrome here.

 Note:
 Assume the length of given string will not exceed 1,010.

 Example:

 Input:
 "abccccdd"

 Output:
 7

 Explanation:
 One longest palindrome that can be built is "dccaccd", whose length is 7.

 * <p>
 * 0. 本质：组合，从集合找一个子集，该子集满足：最多只有一个元素的个数为奇数，其他都为偶数
 * 1. 建模：因为求长度，不需要找具体序列，不涉及排列。
 * longest_p = sum(floor(length(a[i]) / 2) * 2) + special(1|0), where i={0,1,2,...,n-1},
 * special is to check whether the palindrome's length is odd or even
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：HashMap保存每个字符的个数
 * 5. 改进：// TODO: 31/08/2017 可以直接使用char数组进行，速度回更快些
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        Map<Character, Integer> charMap = new HashMap();
        for(int i = 0 ; i < s.length(); i++) {
            charMap.put(s.charAt(i), charMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        boolean isOdd = false;
        int longestPath = 0;
        for(Map.Entry<Character, Integer> entry: charMap.entrySet()) {
            longestPath += (entry.getValue() / 2) * 2;
            isOdd = !isOdd? ((entry.getValue() & 1) == 1? true: false): isOdd;
        }
        if(isOdd) {
            longestPath++;
        }
        return longestPath;
    }

    public static void main(String[] args) {
        int i = new LongestPalindrome().longestPalindrome("abccccdd");
        System.out.printf("i");
    }
}
