package dynamicprogramming.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/arithmetic-slices/description/" />
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

 For example, these are arithmetic sequence:

 1, 3, 5, 7, 9
 7, 7, 7, 7
 3, -1, -5, -9
 The following sequence is not arithmetic.

 1, 1, 2, 5, 7

 A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

 A slice (P, Q) of array A is called arithmetic if the sequence:
 A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

 The function should return the number of arithmetic slices in the array A.


 Example:

 A = [1, 2, 3, 4]

 return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 * <p>
 * 0. 本质：组合
 * 1. 建模：
 * （1）recurrence relation -》（2）DAG，同时要找到符合条件的DAG的子图
 * A = [1,3,5,6,7,8,9]
 * B = [_,2,2,1,1,1,1] 两个相邻的数的差值
 * C = [0,1,2,1,2,3,4] 两个相邻的数的差值相等的连续子数组长度
 *
 * 两个相邻的数的差值相等的连续子数组，设R(i)为截止第i位时与第i-1位不同的子数组个数。
 * 则上面例子中
 * R = [0,0,1,0,1,2,3]
 *
 * 举例：
 * [6,7,8,9]下标从0开始，R(2)=1，因为[6,7,8]符合； R(3)=R(2)+1，因为[7,8,9]和[6,7,8,9]符合，且和前面的[6,7,8]不相同。
 * 注意：相同长度的子数组，具有相同的个数值
 * 2. 算法范式：DP  一个DP算有多少个连续的子数组，每个子数组最长；另一个DP算子数组可有多少个slices
 * 3. 算法：同时为了连续的值，需要借助
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/10/2018
 * @see
 * @since cgs-leetcode on  10/10/2018
 */
public class ArithmeticSlices {

    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }

        long[] B = new long[A.length];
        int[] C = new int[A.length];
        int[] R = new int[A.length];
        B[0] = Long.MIN_VALUE;
        C[0] = 0;
        R[0] = 0;

        List<Integer> solutionIndex = new ArrayList();
        for(int i = 1; i < A.length; i++) {
            B[i] = A[i] - A[i-1];
            C[i] = B[i] == B[i-1]? C[i-1] + 1: 1;
            if(C[i]>1) {
                R[i] = R[i-1] + 1;
            }
        }

        int result = 0;
        for(int i = 0; i < R.length; i++) {
            result = result + R[i];
        }
        return result;
    }

}
