package depthfirstsearch.mediu;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/01-matrix/description/" />
 * 542. 01 Matrix
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

 The distance between two adjacent cells is 1.
 Example 1:
 Input:

 0 0 0
 0 1 0
 0 0 0
 Output:
 0 0 0
 0 1 0
 0 0 0
 Example 2:
 Input:

 0 0 0
 0 1 0
 1 1 1
 Output:
 0 0 0
 0 1 0
 1 2 1
 * <p>
 * 1. 建模：
 * （1）dynamic programming
 * matrix[i][j] = min(matrix[i-1][j], matrix[i+1][j],matrix[i][j-1],matrix[i][j+1]) + 1，如果matrix[i-][j], matrix[i+1][j],matrix[i][j-1],matrix[i][j+1]都不为0
 * matrix[i][j] = 1，如果matrix[i-][j], matrix[i+1][j],matrix[i][j-1],matrix[i][j+1]有一个为0
 * 但是有个问题，在遍历的时候，顺序是从左到右、上到下，而右边和下边都是原始值，没有过遍历，此时拿来的使用，会造成污染。
 *
 * （2）DFS，深度遍历，找到最小的。
 * 对于元素不是0的，固定方向，只能向右、向下深度遍历，直到找到0，获取最短长度；左边和上边由于已经是遍历过的，可以直接拿来使用。
 * 最后matrix[i][j] = min(matrix[i-1][j] + 1, min_left,matrix[i][j-1] + 1,min_down)
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：为了改进深度遍历，将上边和下边的最小距离作为向下向右深度遍历的最大深度，避免过度递归。
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/08/2017
 * @see
 * @since cgs-leetcode on  19/08/2017
 */
public class The01Matrix {
    // 深度右边和下边
    int dfs(int[][] matrix, int i, int j, int deep, int currentMinDeep) {
        if(i >= matrix.length || j >= matrix[0].length) {
            // 找到边际，此时已经找不到0，那么返回无穷大
            return Integer.MAX_VALUE;
        }
        if(matrix[i][j] == 0) {
            return deep;
        }
        // 限制深度，避免浪费时间 // FIXME: 19/08/2017 对于1集中在矩阵右下角的情况很有用处。但是对于左上角全是1的极端情况，这个currentMinDeep就不那么好了
        if(deep >= currentMinDeep) {
            return currentMinDeep;
        }

        int rightDeep = dfs(matrix, i, j + 1, deep + 1, currentMinDeep);
        int downDeep = dfs(matrix, i + 1, j, deep + 1, currentMinDeep);
        return Math.min(rightDeep, downDeep);
    }

    public int[][] updateMatrix(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int minimal = Integer.MAX_VALUE;
                if (i - 1 >= 0) {
                    minimal = Math.min(matrix[i - 1][j] + 1, minimal);
                }
                if (j - 1 >= 0) {
                    minimal = Math.min(matrix[i][j - 1] + 1, minimal);
                }
                minimal = Math.min(dfs(matrix, i, j, 0, minimal), minimal);
                matrix[i][j] = minimal;
            }
        }
        return matrix;

    }

    public static void main(String[] args) {
//        int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] matrix = {{0},{1}};
        int[][] ints = new The01Matrix().updateMatrix(matrix);
        System.out.println(ints);
    }

        //------------------------------DP---------------
/*
    public int[][] updateMatrix1(int[][] matrix) {
        if(null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }

        // 对于n*n矩阵，如果使用的DP，这里要使用n*n*n负责度。时间超时
//        for(int times = 1; times < matrix.length; times++) {
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    rightAndBottom(matrix, i, j);
                }
            }
            for (int i = matrix.length - 1; i >= 0; i--) {
                for (int j = matrix[0].length; j >= 0; j--) {
                    leftAndUp(matrix, i, j);
                }
            }
//        }
        return matrix;
    }

    void rightAndBottom(int[][] matrix, int i, int j) {
        if(i < 0 || j < 0) {
            return;
        }
        if(matrix[i][j] == 0) {
            return;
        }
        int minimal = Integer.MAX_VALUE;

        if(i > 0) {
            if(matrix[i - 1][j] == 0) {
                matrix[i][j] = 1;
                return;
            }
            minimal = Math.min(matrix[i - 1][j] + 1, minimal);
            matrix[i][j] = minimal;
        }
        if(j > 0) {
            if(matrix[i][j - 1] == 0) {
                matrix[i][j] = 1;
                return;
            }
            minimal = Math.min(matrix[i][j - 1] + 1, minimal);
            matrix[i][j] = minimal;
        }

    }

    void leftAndUp(int[][] matrix, int i, int j) {
        if(i >= matrix.length || j >= matrix[0].length) {
            return;
        }
        if(matrix[i][j] == 0) {
            return;
        }
        int minimal = matrix[i][j];

        if(i < matrix.length - 1) {
            if(matrix[i + 1][j] == 0) {
                matrix[i][j] = 1;
                return;
            }
            minimal = Math.min(matrix[i + 1][j] + 1, minimal);
        }
        if(j < matrix[0].length - 1) {
            if(matrix[i][j + 1] == 0) {
                matrix[i][j] = 1;
                return;
            }
            minimal = Math.min(matrix[i][j + 1] + 1, minimal);
        }
        matrix[i][j] = minimal;
    }


//    void handler(int[][] matrix, int i, int j) {
//        if(i < 0 || j < 0) {
//            return;
//        }
//        if(matrix[i][j] == 0) {
//            return;
//        }
//        int minimal = Integer.MAX_VALUE;
//
//        if(i > 0) {
//            if(matrix[i - 1][j] == 0) {
//                matrix[i][j] = 1;
//                return;
//            }
//            minimal = Math.min(matrix[i - 1][j], minimal);
//        }
//        if(i < matrix.length - 1) {
//            if(matrix[i + 1][j] == 0) {
//                matrix[i][j] = 1;
//                return;
//            }
//            minimal = Math.min(matrix[i + 1][j], minimal);
//        }
//        if(j > 0) {
//            if(matrix[i][j - 1] == 0) {
//                matrix[i][j] = 1;
//                return;
//            }
//            minimal = Math.min(matrix[i][j - 1], minimal);
//        }
//        if(j < matrix[0].length - 1) {
//            if(matrix[i][j + 1] == 0) {
//                matrix[i][j] = 1;
//                return;
//            }
//            minimal = Math.min(matrix[i][j + 1], minimal);
//        }
//        matrix[i][j] = minimal + 1;
//    }
*/
    //------------------DP--------

}
