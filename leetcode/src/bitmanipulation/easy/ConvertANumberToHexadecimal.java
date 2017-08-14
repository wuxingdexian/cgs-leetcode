package bitmanipulation.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * 405. Convert a Number to Hexadecimal
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

 Note:

 All letters in hexadecimal (a-f) must be in lowercase.
 The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 The given number is guaranteed to fit within the range of a 32-bit signed integer.
 You must not use any method provided by the library which converts/formats the number to hex directly.
 Example 1:

 Input:
 26

 Output:
 "1a"
 Example 2:

 Input:
 -1

 Output:
 "ffffffff"
 * <p>
 * 1. 建模：移位操作，一次移动4位
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：减少代码量，不需要map这么重的数据结构；<code>toHexImprovement</code>，同时运用上ascii码做加减法
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class ConvertANumberToHexadecimal {
    private static Map<Integer, Character> decimal2hexadecimalMap = new HashMap<Integer, Character>(16);
    static {
        decimal2hexadecimalMap.put(0, '0');
        decimal2hexadecimalMap.put(1, '1');
        decimal2hexadecimalMap.put(2, '2');
        decimal2hexadecimalMap.put(3, '3');
        decimal2hexadecimalMap.put(4, '4');
        decimal2hexadecimalMap.put(5, '5');
        decimal2hexadecimalMap.put(6, '6');
        decimal2hexadecimalMap.put(7, '7');
        decimal2hexadecimalMap.put(8, '8');
        decimal2hexadecimalMap.put(9, '9');
        decimal2hexadecimalMap.put(10, 'a');
        decimal2hexadecimalMap.put(11, 'b');
        decimal2hexadecimalMap.put(12, 'c');
        decimal2hexadecimalMap.put(13, 'd');
        decimal2hexadecimalMap.put(14, 'e');
        decimal2hexadecimalMap.put(15, 'f');
    }
    int[] hexadecimals = {0xf0000000,0xf000000,0xf00000,0xf0000,0xf000, 0xf00, 0xf0, 0xf};
    public String toHex(int num) {
        StringBuilder hexadecimalStr = new StringBuilder();
        char[] hexaChars = new char[8];
        for(int i = 0; i < hexadecimals.length; i++) {
            int tmp = hexadecimals[i] & num;
            char index = decimal2hexadecimalMap.get(tmp >>> ((7 - i) * 4));
            hexaChars[i] = index;
        }
        for(int i = 0; i < hexaChars.length - 1; i++) {
            if(hexadecimalStr.length() != 0) {
                hexadecimalStr.append(hexaChars[i]);
            } else if(hexaChars[i] != '0') {
                hexadecimalStr.append(hexaChars[i]);
            }
        }
        hexadecimalStr.append(hexaChars[hexaChars.length - 1]);
        return hexadecimalStr.toString();
    }

    /**
     * 改进算法，同时运用上<code>StringBuilder#reverse()</code>
     * @param num
     * @return
     */
    public String toHexImprovement(int num) {
        if (0 == num) {
            return "0";
        }
        StringBuilder hexStr = new StringBuilder();
        while (0 != num) {
            hexStr.append(conver2hex(num & 15));
            num >>>= 4;
        }
        return hexStr.reverse().toString();
    }
    /*运用ascii码做加减法，屌屌屌*/
    private char conver2hex(int num) {
        return num > 9? (char)(num - 10 + 'a'): (char) (num + '0');
    }

    public static void main(String[] args) {
        ConvertANumberToHexadecimal convertANumberToHexadecimal = new ConvertANumberToHexadecimal();
        String s = convertANumberToHexadecimal.toHexImprovement(-1);
        System.out.println(s);

    }

}
