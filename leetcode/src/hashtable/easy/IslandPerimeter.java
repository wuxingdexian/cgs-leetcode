package hashtable.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/island-perimeter/description/" />
 * 463. Island Perimeter
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 Example:

 [[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

 Answer: 16
 Explanation: The perimeter is the 16 yellow stripes in the image below:

 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * recurrence relation:
 * for every cell i with value 1,
 * num_side(i) = (grid(i-1,j)=0? 1: 0) + (grid(i+1,j)=0? 1: 0) + (grid(i,j-1)=0? 1: 0) + (grid(i,j+1)=0? 1: 0)
 * if i-1<0 or i+1>=grid.length, then return 1, the same to j;
 *
 * then we get the recurrence relation: perimeter(i) = perimeter(i-1) +  num_side(i)
 *
 *
 * 2. 算法范式：
 * 3. 算法：
 * // TODO: 31/08/2017 如果使用map时怎么个解法？
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：{@link dynamicprogramming.medium.OutOfBoundaryPaths}和这题是类似的
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    continue;
                }

                perimeter += (i-1 < 0? 1: (grid[i-1][j] == 0? 1: 0)) + (i+1 >= grid.length? 1: (grid[i+1][j] == 0? 1: 0))
                        + (j-1 < 0? 1: (grid[i][j-1] == 0? 1: 0)) + (j+1 >= grid[0].length? 1: (grid[i][j+1] == 0? 1: 0));

            }
        }
        return perimeter;
    }
}
