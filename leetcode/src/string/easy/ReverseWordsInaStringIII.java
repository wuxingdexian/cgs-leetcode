package string.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/submissions/detail/114711611/" />
 * 557. Reverse Words in a String III
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

 Example 1:
 Input: "Let's take LeetCode contest"
 Output: "s'teL ekat edoCteeL tsetnoc"
 Note: In the string, each word is separated by single space and there will not be any extra space in the string.

 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：{@link String#toCharArray()}一开始直接转为char数组，然后对这个数组进行遍历、操作，速度回更快
 * 使用string封装类的index等，还是太慢
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2017
 * @see
 * @since cgs-leetcode on  20/08/2017
 */
public class ReverseWordsInaStringIII {
    public String reverseWords(String s) {
        char[] val = new char[s.length()];
        int head = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                int tmp = head;
                for(int j = i - 1; j >= head; j--) {
                    val[tmp++] = s.charAt(j);
                }
                val[i] = ' ';
                head = i + 1;
            }
        }
        for(int i = s.length() - 1; i > s.lastIndexOf(' '); i--) {
            val[head++] = s.charAt(i);
        }

        return new String(val);
    }


    // 别人的答案，更快
    public String reverseWordsStandard(String s) {
        char[] arr = s.toCharArray();
        int start = 0;
        int space = s.indexOf(" ");
        while (space > 0) {
            reverseChars(arr, start, space-1);
            start = space + 1;
            space = s.indexOf(" ", start);
        }
        reverseChars(arr, start, s.length()-1);
        return new String(arr);
    }

    public char[] reverseChars(char[] arr, int s, int e) {
        while (s<e) {
            char t = arr[s];
            arr[s] = arr[e];
            arr[e] = t;
            s++;
            e--;
        }
        return arr;
    }
}
