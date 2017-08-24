package string.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/repeated-substring-pattern/description/" />
 * 459. Repeated Substring Pattern
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 Example 1:
 Input: "abab"

 Output: True

 Explanation: It's the substring "ab" twice.
 Example 2:
 Input: "aba"

 Output: False
 Example 3:
 Input: "abcabcabcabc"

 Output: True

 Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 * <p>
 * 0. 本质：排列
 * 1. 建模：
 * 符合条件时，有如下性质：
 * （1）s被某个子串sub_sn等分
 * （2）子串的首字符为s的首字符，子串的末尾字符为s的尾字符；
 * （3）若两个子串相等，则子串的长度相等；顺序字符排列顺序相等；可以推出两个子串的字符和或乘积或异或等映射后的值相等。
 *
 *  注意：根据（3），选择异或function
 * 2. 算法范式：
 * 3. 算法：
 * （1）首尾分别有head和tail指针，head用于向后找和未字符相等的字符；tail用于向前找和首字符相等的字符；
 * （2）两个异或函数，分别用于收尾收缩时子字符串内字符的计算
 * （3）当符合如下三个条件时，比较首尾两个字符串是否相等：
 *      1）只有head和tail分别找到需要的字符；
 *      2）并且两个异或函数都相等时；
 *      3）中间未遍历的字符串长度等于首字符串长度的倍数时
 * （4）若首尾两个字符串相等，则中间剩余字符串按首字符串来匹配，只有都匹配，则为子字符串的倍数，否则不是。
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2017
 * @see
 * @since cgs-leetcode on  24/08/2017
 */
public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        if(null == s || s.length() <= 1) {
            return false;
        }

        int headIndex = 0;
        int tailIndex = s.length() - 1;
        char headChar = s.charAt(0);
        char tailChar = s.charAt(s.length() - 1);

        int headHash = 0;
        int tailHash = 0;
        while(headIndex < tailIndex) {
            headHash ^= s.charAt(headIndex++);
            tailHash ^= s.charAt(tailIndex--);
            if(s.charAt(headIndex - 1) == tailChar && s.charAt(tailIndex + 1) == headChar
                    && headHash == tailHash
                    && (tailIndex - headIndex + 1) % (headIndex) == 0
                    && s.substring(0, headIndex).equals(s.substring(tailIndex + 1, s.length()))) {
                if(isRepeat(s, headIndex, tailIndex, s.substring(0, headIndex))) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isRepeat(String s, int start, int end, String subs) {
        while(start <= end) {
            for(int i = 0; i < subs.length(); i++, start++) {
                if(s.charAt(start) != subs.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean abab = new RepeatedSubstringPattern().repeatedSubstringPattern("abababababc");
        System.out.println(abab);
    }
}
