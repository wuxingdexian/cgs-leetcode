package others.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/excel-sheet-column-number/description/" />
 * 171. Excel Sheet Column Number
 * Related to question Excel Sheet Column Title

 Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 * <p>
 * 0. 本质：function/sequence
 * 1. 建模：如xyz, (x mod 27) * 26 * 26+ (y mod 27)*26 + (z mod 27)
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int solution = 0;
        for(int i = 0; i < chars.length; i++) {
            int tmp = chars[chars.length - 1 - i] - 'A' + 1;
            solution += tmp * (int)Math.pow(26, i) ;
        }
        return solution;
    }

    public int titleToNumberStandard(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            result = result*26 + (s.charAt(i) - 'A' + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int ab = new ExcelSheetColumnNumber().titleToNumberStandard("AB");
        System.out.println(ab);
    }
}
