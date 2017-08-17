package divideandconquer.medium;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/search-a-2d-matrix-ii/description/" />
 * 240. Search a 2D Matrix II
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 For example,

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.
 * <p>
 * 1. 建模：
 * recurrence relation
 * ---------错误藐视-------------
 * 分治法，从中间的数开始比对，若相等则得到答案；大于则看右、下、右下的三个子矩阵；小于则看左上的小矩阵
 * ---------上面这句话时错误的-------------
 *
 * （1）矩阵右上角开始往左往下移动
 * （2）按行进行二叉搜索
 * 2. 算法范式：
 * divide-and-conquer
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 16/08/2017
 * @see
 * @since cgs-leetcode on  16/08/2017
 */
public class SearchA2DMatrixII {

    /*
    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0] == target;
        }

        return searchMatrix(matrix, target, 0, matrix.length, 0, matrix[0].length);
    }

    public boolean searchMatrix(int[][] matrix, int target, int rowStart, int rowEnd, int columeStart, int columeEnd) {

        if((rowStart > rowEnd) || (columeStart > columeEnd)) {
            return false;
        }
        if(rowStart == rowEnd && columeStart == columeEnd) {
            return matrix[rowStart][columeStart] == target;
        }

        int rowMiddle = (rowStart + rowEnd) >>> 1;
        int columeMiddle = (columeStart + columeEnd) >>> 1;

        if(matrix[rowMiddle][columeMiddle] == target) {
            return true;
        }

        if(0 == Arrays.binarySearch(matrix[rowMiddle], 0, columeMiddle, target)) {
            return true;
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[j][columeMiddle] == target) {
                return true;
            }
        }

        if(matrix[rowMiddle][columeMiddle] > target) {
            return searchMatrix(matrix, target, rowStart, rowMiddle - 1, columeStart, columeMiddle - 1);
        } else {
            return searchMatrix(matrix, target, rowStart, rowMiddle - 1, columeMiddle + 1, columeEnd)
                    || searchMatrix(matrix, target, rowMiddle + 1, rowEnd, columeMiddle + 1, columeEnd)
                    || searchMatrix(matrix, target, rowMiddle + 1, rowEnd, columeStart, columeMiddle - 1);
        }
    }
    */

    /**
     * 从矩阵的右上角开始，该位置特别，左边都比它小，下面都比它大~。~
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixStandard(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] <= target && matrix[i][matrix[0].length - 1] >= target) {
                if(Arrays.binarySearch(matrix[i], target) >= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};

        boolean b = new SearchA2DMatrixII().searchMatrix(matrix, 5);
        System.out.println(b);
    }
}
