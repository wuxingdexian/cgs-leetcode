package others.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/count-and-say/description/" />
 * 38. Count and Say
 * The count-and-say sequence is the sequence of integers with the first five terms as following:

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.

 Example 1:

 Input: 1
 Output: "1"
 Example 2:

 Input: 4
 Output: "1211"

 * <p>
 * 0. 本质：函数
 * 1. 建模：有序对（counter, number）个数和连续相同数字的有序对
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
public class CountAndSay {
    public String countAndSay(int n) {
        if(n < 0) {
            return null;
        }
        if(n == 0) {
            return "10";
        }

        List<Integer> nums = new ArrayList();
        nums.add(1);
        for(int i = 1; i < n; i++){
            // counter -> number
            List<Integer> newNums = new ArrayList();
            for(int j = 0; j < nums.size(); j++) {

                int counter = 1;
                while(j + 1 < nums.size() && nums.get(j) == nums.get(j+1)) {
                    j++;
                    counter++;
                }
                newNums.add(counter);
                newNums.add(nums.get(j));
            }
            nums = newNums;

        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.size(); i++) {
            sb.append(nums.get(i));
        }
        return sb.toString();
    }
}
