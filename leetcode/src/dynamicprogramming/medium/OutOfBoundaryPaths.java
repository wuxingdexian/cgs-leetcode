package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/out-of-boundary-paths/description/" />
 * <a href="https://leetcode.com/problems/out-of-boundary-paths/solution/#approach-3-dynamic-programming-accepted" />
 * 576. Out of Boundary Paths
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 * Example 1:
 Input:m = 2, n = 2, N = 2, i = 0, j = 0
 Output: 6

 Example 2:
 Input:m = 1, n = 3, N = 3, i = 0, j = 1
 Output: 12
 * <p>
 * 1. 建模：recurrence relation.
 * The idea behind this approach is that if we can reach some position in xx moves,
 * we can reach all its adjacent positions in x+1x+1 moves. Based on this idea,
 * we make use of a 2-D dpdp array to store the number of ways in which a particular position can be reached.
 * dp[i][j]dp[i][j] refers to the number of ways the position corresponding to the indices (i,j)(i,j)
 * can be reached given some particular number of moves.
 * dp[i][j] = dp[i-1][j] + dp[i+1][j] + dp[i][j-1] + dp[i][j+1]
 *
 * <p>思路：这道题给出的思路启发是，该道题目归类为计数问题，不是最优解问题。要记住，recurrence relation模型可以应用到很多场合，包括最优解、计数等场合。
 * <p>思维上一定要定位清楚，recurrence relation是high level的
 *
 * 2. 算法范式：dynamic programming
 * 3. 算法：注意判断边界情况，
 * 4. 数据结构：增加边界判断
 * 5. 改进：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class OutOfBoundaryPaths {
    public int findPaths(int m, int n, int N, int x, int y) {
        int M = 1000000000 + 7;

        int dp[][] = new int[m][n];
        dp[x][y] = 1;
        int count = 0;

        for (int step = 0; step < N; step++) {
            int[][] tmp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 这里需要分别判断，在单行的时候
                    if (i == 0) {
                        count = (count + dp[i][j]) % M;
                    }
                    if (i == m - 1) {
                        count = (count + dp[i][j]) % M;
                    }
                    if (j == 0) {
                        count = (count + dp[i][j]) % M;
                    }
                    if (j == n - 1) {
                        count = (count + dp[i][j]) % M;
                    }
                    tmp[i][j] = (((i > 0 ? dp[(i - 1)][j] : 0) + (i < m - 1 ? dp[(i + 1)][j] : 0)) % M
                            + ((j > 0 ? dp[i][j - 1] : 0) + (j < n - 1 ? dp[i][j + 1] : 0)) % M) % M;
                }
            }
            dp = tmp;
        }
        return count;
    }
}
