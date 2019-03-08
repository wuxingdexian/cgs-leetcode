package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/unique-paths-ii/description/" />
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 Now consider if some obstacles are added to the grids. How many unique paths would there be?



 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 Note: m and n will be at most 100.

 Example 1:

 Input:
 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 Output: 2
 Explanation:
 There is one obstacle in the middle of the 3x3 grid above.
 There are two ways to reach the bottom-right corner:
 1. Right -> Right -> Down -> Down
 2. Down -> Down -> Right -> Right
 * <p>
 * 0. 本质：
 * 1. 建模：recurrence relation
 * 参考{@link UniquePaths}
 * 2. 算法范式：
 * dynamic programming
 *
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 06/02/2019
 * @see
 * @since cgs-leetcode on  06/02/2019
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] solution = new int[obstacleGrid.length+1][obstacleGrid[0].length+1];

        solution[1][1] = obstacleGrid[0][0]==0? 1: 0;

        for(int r=0; r<obstacleGrid.length; r++) {
            for(int c=0; c<obstacleGrid[0].length; c++) {
                if(obstacleGrid[r][c]!=0) continue;
                if(r==0 && c==0) continue;

                solution[r+1][c+1] = solution[r][c+1] + solution[r+1][c];
            }
        }

        return solution[obstacleGrid.length][obstacleGrid[0].length];

    }
}
