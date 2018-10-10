package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/counting-bits/description/" />
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

 Example 1:

 Input: 2
 Output: [0,1,1]
 Example 2:

 Input: 5
 Output: [0,1,1,2,1,2]
 Follow up:

 It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 Space complexity should be O(n).
 Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 * <p>
 * 0. 本质：
 * 1. 建模：（1）recurrence relation -》（2）directed acyclic graph
 * 先列出一些值，然后找规律，找到重复子问题：从头开始列出一些，就很容发现子问题
 * 如
 * 0        ->            00
 * 1        ->            01
 * 2        ->            10
 * 3        ->            11
 * 4        ->           100
 * 5        ->           101
 * 6        ->           110
 * 7        ->           111
 * 8        ->          1000
 * 9        ->          1001
 * ...
 * 可以看出DAG图，每次进入到2的n次方后，开始一次循环，除了最高位，后面几个bit位的都和前面2的n-1次方的bit位保持一致
 * 2. 算法范式：DP
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/10/2018
 * @see
 * @since cgs-leetcode on  09/10/2018
 */
public class CountingBits {
    public int[] countBits(int num) {
        if (num < 0) {
            return null;
        }

        int[] result = new int[num + 1];
        for (int i = 0; i < Math.min(num + 1, 2); i++) {
            if(i == 0) {
                result[i] = 0;
            }
            if(i == 1) {
                result[i] = 1;
            }
        }

        int powIndex = 1;
        int currentPow = (int)Math.pow(2, powIndex);
        int nextPow = (int)Math.pow(2, ++powIndex);
        for(int i = 2; i < num + 1; i++) {
            if (i == currentPow) {
                result[i] = 1;
            } else if (i == nextPow) {
                result[i] = 1;
                currentPow = nextPow;
                nextPow = (int)Math.pow(2, ++powIndex);
            } else {
                result[i] = 1 + result[i - currentPow];
            }
        }
        return result;
    }

    // TODO 参考 https://leetcode.com/problems/counting-bits/discuss/174673/Java-simple-DP-one-line-solution 该解更简洁


    public static void main(String[] args) {
        CountingBits test = new CountingBits();
        int[] result = test.countBits(16);
        for(int i: result) {
            System.out.println(i);
        }
    }
}
