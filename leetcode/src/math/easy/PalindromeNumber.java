package math.easy;

import others.easy.ReverseInteger;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/palindrome-number/description/" />
 * 9. Palindrome Number
 *Determine whether an integer is a palindrome. Do this without extra space.

 click to show spoilers.

 Some hints:
 Could negative integers be palindromes? (ie, -1)

 If you are thinking of converting the integer to string, note the restriction of using extra space.

 You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

 There is a more generic way of solving this problem.
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：求一个整数的十进制有多少位，用对数
 * 编译器优化，当y为最小int时，即0x80000000（-2147483648），此时int已经无最大的整数可以表示这个数。此时想到用long来保存，
 * 执行 x = (0 - (y + 1) + 1); y + 1得到最大int正整数的反，然后0 - (y + 1)得到最大正整数，最后加1；
 * 想法不错，但是执行下来，结果不变。应该是编译器执行优化的问题。x = (0 - (y + 1) + 1)相当于一条语句，编译器会执行优化，得到x=(0-y)。
 * 从补码知识可知，0=0x00000000，-2147483648=0x80000000，机器只执行加法，此时相加得到的还是0x80000000
 * 补发知识：http://blog.csdn.net/zq602316498/article/details/39404043
 *
 * TODO : 用栈也行，参考{@link ReverseInteger}
 *
 *
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class PalindromeNumber {
    public boolean isPalindrome(int y) {
        long x = y;
        // 负数不算
//        if((y & 0x80000000) != 0) {
////            x = (0 - (y + 1) + 1);
//            x = (0 - (y + 1));
//            x++;
//        } else {
//            x = (y < 0? 0 - y: y);
//        }
        // 当x为0，取对数得到负无穷大。。。
        int decimalLength = x != 0? (int)(Math.log10(x) + 1): 1;
        long[] nums = new long[decimalLength];
        for(int i = 0; i < decimalLength; i++) {
            nums[i] = x % 10;
            x /= 10;
        }
        for(int i = 0; i < decimalLength / 2; i++) {
            if(nums[i] != nums[decimalLength - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 这里取消数组的辅助，直接for循环比较
     * @param x
     * @return
     */
    public boolean isPalindromeImprove(int x) {
        if(x < 0) {
            return false;
        }
        // 当x为0，则。。。
        int decimalLength = x != 0? (int)(Math.log10(x) + 1): 1;
        for(int i = 1; i <= decimalLength; i += 2) {
            int remainder = x % 10;
            int quotient = (int)(x / (Math.pow(10, decimalLength - i)));
            if (remainder != quotient) {
                return false;
            }
            x = x - (int)(quotient * Math.pow(10, decimalLength - i));
            x /= 10;
        }

        return true;
    }

    public static void main(String[] args) {
        boolean palindrome = new PalindromeNumber().isPalindromeImprove(11222311);//-2147483648
        System.out.println("PalindromeNumber.main: " + palindrome);

    }
}
