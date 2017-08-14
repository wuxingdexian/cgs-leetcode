package dynamicprogramming.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximum-length-of-pair-chain/description/" />
 * 646. Maximum Length of Pair Chain
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

 Example 1:
 Input: [[1,2], [2,3], [3,4]]
 Output: 2
 Explanation: The longest chain is [1,2] -> [3,4]
 Note:
 The number of given pairs will be in the range [1, 1000].
 * <p>
 * 1. 建模：recurrence relation
 * 上述有序对，按second number非递减排序 e_1<e_2<....<e_n
 * longest_n = max(longest_n-1, 1 + longest_i), i是到n的链条中最近的那个
 * 2. 算法范式：dynamic programming
 * 3. 算法：按第二元素非递减排序后，找到第n个有序对最近的可以处于相同链条上的有序对
 * 4. 数据结构：创建一个长度和有序对相同长度的数组，存放链中最近的有序对
 * 5. 改进：需要掌握jdk中常用的排序接口
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 精简代码，这才是
                return o1[1] - o2[1];
//                if (o1[1] < o2[1]) {
//                    return -1;
//                } else if (o1[1] == o2[1]) {
//                    return 0;
//                } else {
//                    return 1;
//                }
            }
        });

        int[] chainList = new int[pairs.length + 1];
        for(int i = 0; i < pairs.length; i++) {
            for(int j = i - 1; j >=0; j--) {
                if(pairs[i][0] > pairs[j][1]) {
                    chainList[i + 1] = j + 1;
                    break;
                }
            }
        }
        int[] chainNumList = new int[pairs.length + 1];
        for(int i = 1; i <= pairs.length; i++) {
            chainNumList[i] = Math.max(chainNumList[i - 1], 1 + chainNumList[chainList[i]]);
        }
        return chainNumList[chainNumList.length - 1] + 1;
    }

    public static void main(String[] args) {
        int[][] pairs = {{9,10},{9,10},{4,5},{-9,-3},{-9,1},{0,3},{6,10},{-5,-4},{-7,-6}};

        int maximumLengthOfPairChain = new MaximumLengthOfPairChain().findLongestChain(pairs);
        System.out.println(maximumLengthOfPairChain);
    }

}


