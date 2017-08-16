package binarysearch.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/guess-number-higher-or-lower/description/" />
 * 374. Guess Number Higher or Lower
 * We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number is higher or lower.

 You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

 -1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
 Example:
 n = 10, I pick 6.

 Return 6.
 * <p>
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
 * @author changgan on 15/08/2017
 * @see
 * @since cgs-leetcode on  15/08/2017
 */
public class GuessNumberHigherOrLower {

    public int guessNumber(int n) {

        // 使用long，避免low+high时溢出
        long low = 0,high = n,middle;
        while(low <= high) {
            middle = (low + high) >>> 1;
            int tmp = guess((int) middle);
            if(tmp == 0) {
                return (int) middle;
            } else if(tmp < 0) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int guess = new GuessNumberHigherOrLower().guessNumber(10);
        System.out.println(guess);
    }

    int guess(int n) {
        return n == 6? 0: (n < 6? 1: -1);
    }
}
