package twopointers.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/valid-palindrome/description/" />
 * 125. Valid Palindrome
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.

 * <p>
 * 0. 本质：序列 函数
 * 1. 建模：抛开中间的非字母数字类型的字符，对称的两头相等会相差26（字母）
 * 2. 算法范式：
 * 3. 算法：
 * （1）
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * TODO (1)SACII码，{A~Z}和{a~z}不连续，并且{A~Z}在{a~z}前面;
 * TODO (2)如果想判断两个拼音字母letter是否是大小写，Math.abs(c1-c2) == 'a' - 'A'; 但是必须排除数字，因为向 0和P相差正好等于'a' - 'A'
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        int start = getLegalForwordIndex(s, 0);
        int end = getLegalBackwardIndex(s, s.length() - 1);
        while(start < end && start < s.length() && end >= 0) {
            if(!isEqual(s.charAt(start), s.charAt(end))) {
                return false;
            }
            start = getLegalForwordIndex(s, start + 1);
            end = getLegalBackwardIndex(s, end - 1);
        }
        return true;
    }

    boolean isEqual(char c1, char c2) {
//        return c1 == c2 || Math.abs(c1-c2) == 'a' - 'A';
        if (c1 == c2) {
            return true;
        } else if(c1 >= 'A' && c2 >= 'A') {
            return Math.abs(c1-c2) == 'a' - 'A';
        }
        return false;
    }

    int getLegalForwordIndex(String s, int index) {
        while(index < s.length()) {
            char c = s.charAt(index);
            if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return index;
            }
            index++;
        }
        return s.length();
    }

    int getLegalBackwardIndex(String s, int index) {
        while(index >= 0) {
            char c = s.charAt(index);
            if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return index;
            }
            index--;
        }
        return -1;
    }

    public static void main(String[] args) {
        boolean ab = new ValidPalindrome().isPalindrome("ab@a");
        System.out.println(ab);
    }
}
