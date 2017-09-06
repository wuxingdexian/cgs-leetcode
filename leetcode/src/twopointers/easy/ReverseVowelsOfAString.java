package twopointers.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reverse-vowels-of-a-string/description/" />
 * 345. Reverse Vowels of a String
 * Write a function that takes a string as input and reverse only the vowels of a string.

 Example 1:
 Given s = "hello", return "holle".

 Example 2:
 Given s = "leetcode", return "leotcede".

 Note:
 The vowels does not include the letter "y".
 * <p>
 * 0. 本质：combinatorics 排列
 * 1. 建模：
 * 元音字母 vowels: a, e, i, o, u
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：// TODO: 03/09/2017 别忘了大小写
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int start=0,end=chars.length-1;
        while(start < end) {
            // vowel in the front
            while(start < chars.length && !isVowel(chars[start])) {
                start++;
            }

            // vowel int the back
            while(end >= 0 && !isVowel(chars[end])) {
                end--;
            }
            if(start < end) {
                swap(chars, start, end);
            }
            start++;
            end--;
        }
        return String.valueOf(chars);
    }

    boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    void swap(char[] chars, int start, int end) {
        char tmp = chars[start];
        chars[start] = chars[end];
        chars[end] = tmp;
    }
}
