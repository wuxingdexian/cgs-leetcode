package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/number-of-islands/description/" />
 * 200. Number of Islands
 * <p>
 * 0. 本质：集合-》子集合
 * 1. 建模：
 * 垂直和水平相连的（1）都聚合在一起，看有多少个这样的子集。
 * 2. 算法范式：
 * backtracking、divide-and-conquer
 * 从整体看，{@link LongestSubstringWithAtLeastKRepeatingCharacters}类似，这里已经被0天然分割，不同子集的子问题，是可以用分治法解释的。
 * 但是具体遍历区域为1的子问题却可以用backtracking解释
 * 3. 算法：dfs
 * 4. 数据结构：直接在数组上标记，1->2
 * 5. 改进：
 * 6. 启发：测试用例场景没考虑全，最开始只记得往右往下dfs
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/09/2017
 * @see
 * @since cgs-leetcode on  15/09/2017
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int counter = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    counter++;
                    dfs(grid, i, j);
                }
            }
        }
        return counter;
    }

    // mark like color.  right, down and left dfs
    void dfs(char[][] grid, int r, int c) {
        if(r >= grid.length || r < 0 || c >= grid[0].length || c < 0 || grid[r][c] != '1') {
            return;
        }

        grid[r][c] = '2';
        // right
        dfs(grid, r, c+1);
        // down
        dfs(grid, r+1, c);
        // left
        dfs(grid, r, c-1);
    }

    public static void main(String[] args) {
//        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] grid = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};

        int i = new NumberOfIslands().numIslands(grid);
        System.out.println(i);
    }
}
