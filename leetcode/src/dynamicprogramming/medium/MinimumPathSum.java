package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/minimum-path-sum/description/" />
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.
 * <p>
 * 1. 建模：
 * recurrence relation
 * 移动时只能左边或下边。定义子问题，min_sum(i,j)为到(i,j)时花费最少。
 * min_sum(i,j) = min(min_sum(i,j-1) + a(i,j), min_sum(i-1,j) + a(i,j))
 * 注意边界问题。
 *
 * 2. 算法范式：
 * dynamic programming
 * 3. 算法：
 * dynamic programming 对应bottom-up
 * 4. 数据结构：二维数组，记录达到(i,j)的最小值和
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
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {

        if(null == grid || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[][] minSums = new int[grid.length + 1][grid[0].length + 1];

        for(int i = 1; i < minSums.length; i++) {
            for(int j = 1; j < minSums[0].length; j++) {
                if(i == 1) {
                    minSums[i][j] = minSums[i][j - 1] + grid[i - 1][j - 1];
                    continue;
                }
                if(j == 1) {
                    minSums[i][j] = minSums[i - 1][j] + grid[i - 1][j - 1];
                    continue;
                }
                minSums[i][j] = Math.min(minSums[i][j - 1], minSums[i - 1][j]) + grid[i - 1][j - 1];
            }
        }
        return minSums[minSums.length - 1][minSums[0].length - 1];
    }
}
