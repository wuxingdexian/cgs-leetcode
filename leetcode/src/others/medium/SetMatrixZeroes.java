package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/set-matrix-zeroes/description/" />
 * 73. Set Matrix Zeroes
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

 click to show follow up.

 Follow up:
 Did you use extra space?
 A straight forward solution using O(mn) space is probably a bad idea.
 A simple improvement uses O(m + n) space, but still not the best solution.
 Could you devise a constant space solution?
 * <p>
 * 0. 本质：序列
 * 1. 建模：对于二维数组这个序列
 * 依次遍历，只要遇到0，并且该0不是被刷新的0，就执行操作：将改行该列都刷新为0；
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）为了记录哪些0时被刷新的，简单的方式是相同的二维数组O(mn) space，分别记录哪些值时被刷新的0；
 * （2）另一个办法是行和列分别记录，分别使用两个数组O(m + n) space
 * （3）一种O(1)空间复杂度的算法，参考https://leetcode.com/problems/set-matrix-zeroes/discuss/
 *  直接在二维数组上进行标记~。~
 * Use the first column and the first row as marker:

 first scan through the whole matrix, and if one row i has zero, label matrix[i][0] = 0, if column j has zero, then label matrix[0][j] = 0.
 if we find the first row has zero, then mark a boolean row = true, if the first column has zeros, mark a boolean col = true;

 By the markers on the first row and first col, set the other columns and rows to zeros. (first row and first column already contain zeros)

 According to booleans row and col, decide whether to set first row and column to zeros.
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/09/2017
 * @see
 * @since cgs-leetcode on  11/09/2017
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];

        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == 0) {
                    rows[r] = true;
                    columns[c] = true;
                }
            }
        }

        // set rows to 0
        for(int r = 0; r < rows.length; r++) {
            if(rows[r]) {
                for(int c = 0; c < columns.length; c++) {
                    matrix[r][c] = 0;
                }
            }
        }

        // set columns to 0
        for(int c = 0; c < columns.length; c++) {
            if(columns[c]) {
                for(int r = 0; r < rows.length; r++) {
                    matrix[r][c] = 0;
                }
            }
        }
    }
}
