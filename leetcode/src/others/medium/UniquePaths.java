package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/unique-paths/description/" />
 * 62. Unique Paths
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?


 Above is a 3 x 7 grid. How many possible unique paths are there?

 Note: m and n will be at most 100.

 * <p>
 * 0. 本质：combinatorics counting permutation
 * 1. 建模：recurrence relation
 * count(i,j) = count(i-1,j) + count(i,j-1) where i >0 and j > 0
 * count(i,j) = count(i-1,j) where i >0 and j = 0
 * count(i,j) = count(i,j-1) where i = 0 and j > 0
 *
 * 当然dfs也可以
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 05/09/2017
 * @see
 * @since cgs-leetcode on  05/09/2017
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m+1][n+1];
        paths[0][1] = 1;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                paths[i][j] = paths[i-1][j] + paths[i][j-1];
            }
        }
        return paths[m][n];
    }
}
