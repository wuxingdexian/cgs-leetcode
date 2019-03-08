package online;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * This is a demo task.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

 For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

 Given A = [1, 2, 3], the function should return 4.

 Given A = [−1, −3], the function should return 1.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [−1,000,000..1,000,000].
 * <p>
 * 0. 本质：
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
 * @author changgan on 25/11/2018
 * @see
 * @since cgs-leetcode on  25/11/2018
 */
public class DemoTask {

    public static void main(String[] args) {
        int[] A = {1,3,4,5,6,7,8,9};
        DemoTask demoTask = new DemoTask();
        int solution = demoTask.solution(A);
        System.out.println(solution);
    }


    public int solution(int[] A) {
        // write your code in Java SE 8

        Arrays.sort(A);

//        int firstPositive = findFirstPositive(A, 0, A.length - 1);
//
//        if (firstPositive < 0) {
//            return 1;
//        }

        int expectingPositiveNumber = 1;
        for (int i = 0;i < A.length;i++) {
            if (A[i] < 0) {
                continue;
            }
            if (A[i] == expectingPositiveNumber) {
                expectingPositiveNumber++;
                continue;
            }
            if (A[i] < expectingPositiveNumber) {
                continue;
            }
            if (A[i] > expectingPositiveNumber) {
                return expectingPositiveNumber;
            }
        }
        return expectingPositiveNumber;
    }

    private int findFirstPositive(int[] A, int start, int end) {
        if (start >= end) {
            return -1;
        }

        int middle = (end - start) / 2;
        if (A[middle] == 0) {
            return middle;
        } else if (A[middle] > 0) {
            return findFirstPositive(A, start, middle - 1);
        } else {
            return findFirstPositive(A, middle + 1, end);
        }
    }
}
