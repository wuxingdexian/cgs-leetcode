package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/rotate-image/description/" />
 * 48. Rotate Image
 * You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).

 Note:
 You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 Example 1:

 Given input matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],

 rotate the input matrix in-place such that it becomes:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]
 Example 2:

 Given input matrix =
 [
 [ 5, 1, 9,11],
 [ 2, 4, 8,10],
 [13, 3, 6, 7],
 [15,14,12,16]
 ],

 rotate the input matrix in-place such that it becomes:
 [
 [15,13, 2, 5],
 [14, 3, 4, 1],
 [12, 6, 8, 9],
 [16, 7,10,11]
 ]
 * <p>
 * 0. 本质：
 * combinatorics-》permutation-》generating
 * 1. 建模：
 * （1）最简单的方式，新开一个数组，然后将数组转过去，容易实现，但是题设不允许；
 * （2）从最外层开始，每一层向右90度旋转，然后向内第二层，继续执行，直到结束
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/09/2017
 * @see
 * @since cgs-leetcode on  10/09/2017
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return;
        }
        for(int i = 0; i < matrix.length >>>1; i++) {
            rotate(matrix, i, matrix.length - 1 - i);
        }
    }

    void rotate(int[][] matrix, int start, int end) {
        int[] tmp = new int[end - start + 1];

        // cache up
        for(int i = start, j = 0; i <= end; i++, j++) {
            tmp[j] = matrix[start][i];
        }

        // left 2 up
        for(int i = start, j = end; i <= end; i++, j--) {
            matrix[start][i] = matrix[j][start];
        }

        // bottom 2 left
        for(int i = start, j = start; i <= end; i++, j++) {
            matrix[i][start] = matrix[end][j];
        }

        // right 2 bottom
        for(int i = start, j = end; i <= end; i++, j--) {
            matrix[end][i] = matrix[j][end];
        }

        // up 2 right
        for(int i = start, j = 0; i <= end; i++, j++) {
            matrix[i][end] = tmp[j];
        }
    }
}
