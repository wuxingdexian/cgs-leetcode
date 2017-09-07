package others.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/4sum-ii/description/" />
 * 454. 4Sum II
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

 To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

 Example:

 Input:
 A = [ 1, 2]
 B = [-2,-1]
 C = [-1, 2]
 D = [ 0, 2]

 Output:
 2

 Explanation:
 The two tuples are:
 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * 0. 本质：关系，combinatorics-》combination-》counting
 * 1. 建模：
 * recurrence relation
 * 设定有序对(A(i), B(j), C(m), D(n)) = (A(i), B(j), C(m)) + D(n) = (A(i), B(j)) + C(m) + D(n) = A(i) + B(j) + C(m) + D(n)
 *
 * 2. 算法范式：
 * （1）backtracking
 * 3. 算法：
 * （1）dfs，先尝试。
 *
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
public class _4SumII {

    // bruce force
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if(A == null || B == null || C == null || D == null) {
            return 0;
        }
        return fourSumCount_(A,B,C,D);
    }

    int fourSumCount_(int[] A, int[] B, int[] C, int[] D) {
        int counter = 0;
        for(int i = 0; i < A.length; i++) {
            counter += fourSumCount(A[i], B, C, D);
        }

        return counter;
    }

    int fourSumCount(int valA, int[] B, int[] C, int[] D) {
        int counter = 0;
        for(int i = 0; i < B.length; i++) {
            counter += fourSumCount(valA + B[i], C, D);
        }
        return counter;
    }

    int fourSumCount(int valAB, int[] C, int[] D) {
        int counter = 0;
        for(int i = 0; i < C.length; i++) {
            counter += fourSumCount(valAB + C[i], D);
        }
        return counter;
    }

    int fourSumCount(int valABC, int[] D) {
        int counter = 0;
        for(int i = 0; i < D.length; i++) {
            if(valABC + D[i] == 0) {
                counter++;
            }
        }
        return counter;
    }


    // 从中可以发现，很多重复子问题 时间复杂度还是有点高
    public int fourSumCountR(int[] A, int[] B, int[] C, int[] D) {
        Map<String, Integer> cache = new HashMap<>();
        int counter = 0;
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                cache.put(i + "-" + j, A[i] + B[j]);
                for(int m = 0; m < C.length; m++) {
                    cache.put(i + "-" + j + "-" + m, cache.get(i + "-" + j) + C[m]);
                    for(int n = 0; n < C.length; n++) {
                        if(cache.get(i + "-" + j + "-" + m) + D[n] == 0) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;

    }

    // 从中可以发现，很多重复子问题 O(n*n)
    public int fourSumCountImprovement(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> cache = new HashMap<>();
        int counter = 0;
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                cache.put(A[i] + B[j], cache.getOrDefault(A[i] + B[j], 0) + 1);

            }
        }

        for(int m = 0; m < C.length; m++) {
            for(int n = 0; n < C.length; n++) {
                Integer nums = cache.get(-(C[m] + D[n]));
                if(nums != null) {
                    counter += nums;
                }
            }
        }
        return counter;

    }

    public static void main(String[] args) {
        int[] A ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] B = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] C ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] D = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        int i = new _4SumII().fourSumCountImprovement(A, B, C, D);
        System.out.println(i);
    }

}
