package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/" />
 * 17. Letter Combinations of a Phone Number
 * Given a digit string, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below.


 Input:Digit string "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 Note:
 Although the above answer is in lexicographical order, your answer could be in any order you want.
 * <p>
 * 1. 建模：决策树
 * 有序对1. (number, letter[])，建立数字和字母数组的映射关系，注意和{@link PermutationsII}比对。PermutationsII中的有序对包含数字的可使用个数，而本题数字对应的字母是不限制次数的。
 * 有序对2. ((_,_,...),(number_1, letter_1[]),(number_2, letter_2[]),...)，(_,_,...)中的不同位置的数，分别从letter_1、letter_2数组中获取
 *                                                                                                         (_,_,_),(2,[a,b,c]),(3,[d,e,f]),(4,[g,h,i])
 *                                                          (a,_,_),(2,[a,b,c]),(3,[d,e,f]),(4,[g,h,i])                                 |                       (b,_,_),(2,[a,b,c]),(3,[d,e,f]),(4,[g,h,i])              |               (c,_,_),(2,[a,b,c]),(3,[d,e,f]),(4,[g,h,i]) ....
 * (a,d,_),(2,[a,b,c]),(3,[d,e,f]),(4,[g,h,i]) | (a,e,_),(2,[a,b,c]),(3,[d,e,f]),(4,[g,h,i]) | (a,f,_),(2,[a,b,c]),(3,[d,e,f]),(4,[g,h,i]) .....
 * 条件限制：当(_,_,...)已经遍历到最后时，得到结果，退出
 *
 * 2. 算法范式：backtracking
 * 3. 算法：注意这里包含了两个数组形式的集合，(_,_,...)和(number, letter[])，设计backtracking时要注意，不要重复跨位了。
 * 本题算法步骤：在(_,_,...)的每一位，遍历对应number的letter[]。遍历的(_,_,...)，使用一个int参数来表示到达第几位。
 *
 * 在<code>PermutationsII</code>中的有序对包含数字的可使用个数，是数字的属性，有使用次数限制。
 *
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：决策树模型，涉及到两个集合遍历的情况，要特别注意了。
 * // TODO: 10/08/2017 目前做下来的backtracking ，函数中只涉及到一次for循环，当涉及到两个集合遍历时，要看清决策树模型，其中一个集合的遍历使用参数step逐渐递进和回退
 * 7. jdk知识：
 * （1）{@link StringBuilder#deleteCharAt(int)}和delete方法，底层要拷贝数组的。性能应该很低。
 * （2）基本类型的数组可以初始化不规则（不对称，长度不一致）的二维数组
 * （3）String转char[]，{@link String#toCharArray()}
 * （4）List<Chacter>转String，最好额外写函数吧。因为List转的string或带双引号，和本题集合为空是不是空字符串，而是null
 * （5）清除一个char数组中的元素值，可以使用char[i]='\0'; 这是ASSCII码的空字符，第0位是“NUL(null)空字符” 。https://baike.baidu.com/item/ASCII/309296?fr=aladdin&fromid=19660475&fromtitle=ascii%E7%A0%81%E8%A1%A8
 * （6）char转数字，如果这个char正好是数字字符，则直接使用i-'0';
 * （7）如果0到15的数字i转16进制的字符，可以：i < 10，则i + '0'，若i > 9 并 i < 16，则i-10+'a'
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/08/2017
 * @see
 * @since DiscreteMathematics on  10/08/2017
 */
public class LetterCombinationsOfAPhoneNumber {
    char[][] letters = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},
            {'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};

    public List<String> letterCombinations(String digits) {

        List<String> solutions = new ArrayList();
        if(digits.length() == 0) {
            return solutions;
        }
        generate(digits.toCharArray(), 0, solutions, new char[digits.length()]);
        return solutions;

    }

    private void generate(char[] digits, int step,
                          List<String> solutions, char[] combinations) {
        if(step == digits.length) {
            if (combinations[0] != '\0') {
                solutions.add(String.valueOf(combinations));
            }
            return;
        }
        int number = digits[step] - '0';
        for (int j = 0; j < letters[number].length; j++) {
            combinations[step] = letters[number][j];
            generate(digits, step + 1, solutions, combinations);
            combinations[step] = '\0';
        }
    }

    public static void main(String[] args) {

        List<String> strings = new LetterCombinationsOfAPhoneNumber().letterCombinations("234");
        System.out.println(strings);
    }
}
