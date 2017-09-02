package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reshape-the-matrix/description/" />
 * 566. Reshape the Matrix
 * In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

 You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

 The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

 If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

 Example 1:
 Input:
 nums =
 [[1,2],
 [3,4]]
 r = 1, c = 4
 Output:
 [[1,2,3,4]]
 Explanation:
 The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
 Example 2:
 Input:
 nums =
 [[1,2],
 [3,4]]
 r = 2, c = 4
 Output:
 [[1,2],
 [3,4]]
 Explanation:
 There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
 Note:
 The height and width of the given matrix is in range [1, 100].
 The given r and c are all positive.
 * <p>
 * 0. 本质：function
 * 1. 建模：将两个数组做映射，
 * nums1[i,j] 行列(r1,c1) -> nums2[m,n] 行列(r2,c2)，有关系式：r2=(i*r1+c1)/m, c2=(i*r1+c1)%m
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class ReshapeTheMatrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums == null || nums.length * nums[0].length != r * c) {
            return nums;
        }

        int[][] matrix = new int[r][c];
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums[0].length; j++) {
                // 注意别写为r
                int row = (i * nums[0].length + j) / c;
                int column = (i * nums[0].length + j) % c;
                matrix[row][column] = nums[i][j];
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2},{3,4}};
        int[][] ints = new ReshapeTheMatrix().matrixReshape(nums, 1, 4);
        System.out.println(ints);
    }
}
