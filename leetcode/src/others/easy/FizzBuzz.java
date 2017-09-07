package others.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/fizz-buzz/description/" />
 * 412. Fizz Buzz
 * Write a program that outputs the string representation of numbers from 1 to n.

 But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

 Example:

 n = 15,

 Return:
 [
 "1",
 "2",
 "Fizz",
 "4",
 "Buzz",
 "Fizz",
 "7",
 "8",
 "Fizz",
 "Buzz",
 "11",
 "Fizz",
 "13",
 "14",
 "FizzBuzz"
 ]
 * <p>
 * 0. 本质：序列
 * 1. 建模：
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
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> solutions = new ArrayList();
        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 && i % 5 == 0) {
                solutions.add("FizzBuzz");
            } else if(i % 3 == 0) {
                solutions.add("Fizz");
            } else if(i % 5 == 0) {
                solutions.add("Buzz");
            } else {
                solutions.add(String.valueOf(i));
            }
        }
        return solutions;
    }
}
