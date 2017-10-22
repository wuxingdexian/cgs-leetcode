package others.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/spiral-matrix/description/" />
 * 54. Spiral Matrix
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example,
 Given the following matrix:

 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].
 * <p>
 * 0. 本质：序列
 * 1. 建模：外围走一圈，然后第二层继续走一圈
 * 建模matrix(r,c)，表示 有r行c列
 *
 * 2. 算法范式：
 * 3. 算法：
 * matrix(r,c)
 * （1）从左至右遍历第一行；
 * （2）从上到下遍历最后一列；
 * （3）从右到左遍历最后一行；
 * （4）从下到上遍历第一列；
 * （5）r--, c--， 然后继续重复（1）到（5），直到r或c为0；
 * 注意：在（2）到（4）过程会有重复获取行列公共数据的情况，以及在只有一行或一列时存在（1）、（3）或（2）、（4）重复读取数据的情况
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/10/2017
 * @see
 * @since cgs-leetcode on  22/10/2017
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> solution = new ArrayList();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return solution;
        }

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int columnStart = 0;
        int columnEnd = matrix[0].length - 1;
        while(rowEnd >= rowStart && columnEnd >= columnStart) {
            solution.addAll(getSequence(matrix, rowStart++, rowEnd--, columnStart++, columnEnd--));
        }
        return solution;
    }

    List<Integer> getSequence(int[][] matrix, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        List<Integer> solution = new ArrayList();

        // （1）从左至右遍历第一行；
        for(int i = columnStart; i <= columnEnd; i++) {
            solution.add(matrix[rowStart][i]);
        }
        // （2）从上到下遍历最后一列；
        for(int i = rowStart+1; i <= rowEnd; i++) {
            solution.add(matrix[i][columnEnd]);
        }
        // （3）从右到左遍历最后一行；
        for(int i = columnEnd-1; i >= columnStart && rowEnd > rowStart; i--) {
            solution.add(matrix[rowEnd][i]);
        }
        // （4）从下到上遍历第一列；
        for(int i = rowEnd-1; i > rowStart && columnEnd > columnStart; i--) {
            solution.add(matrix[i][columnStart]);
        }
        return solution;
    }

    public static void main(String[] args) {
        int[][] matrix = {{6,9,7},{5,0,1}};
        List<Integer> integers = new SpiralMatrix().spiralOrder(matrix);
        System.out.println(integers);

    }
}
