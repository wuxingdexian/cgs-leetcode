package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/plus-one/description/" />
 * 66. Plus One
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

 You may assume the integer do not contain any leading zero, except the number 0 itself.

 The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * TODO: 使用栈也很容易计算出来
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/08/2017
 * @see
 * @since DiscreteMathematics on  10/08/2017
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for(int i = digits.length - 1; i >= 0; i--) {
            int nextCarry = (digits[i] + carry) > 9? 1: 0;

            if(nextCarry == 1) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] += carry;
                carry = 0;
            }

        }
        if(carry == 1) {
            int[] digitsPlus = new int[digits.length + 1];
            digitsPlus[0] = 1;
            return digitsPlus;
        } else {
            return digits;
        }
    }

    public static void main(String[] args) {
        int[] nums = {9};

        System.out.println(new PlusOne().plusOne(nums));
    }
}
