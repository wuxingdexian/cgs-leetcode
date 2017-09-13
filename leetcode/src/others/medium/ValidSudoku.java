package others.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/valid-sudoku/description/" />
 * 36. Valid Sudoku
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


 A partially filled sudoku which is valid.


 * <p>
 * 0. 本质：序列 关系
 * 1. 建模：每行 每列  每个9块小区域都是1到9
 *
 * // FIXME: 13/09/2017 题设要求判断有效性，这个意思是不一定有正确解？从测试下列数据能通过来看，是这样的。。
 * {{'.','8','7','6','5','4','3','2','1'},
 {'2','.','.','.','.','.','.','.','.'},
 {'3','.','.','.','.','.','.','.','.'},
 {'4','.','.','.','.','.','.','.','.'},
 {'5','.','.','.','.','.','.','.','.'},
 {'6','.','.','.','.','.','.','.','.'},
 {'7','.','.','.','.','.','.','.','.'},
 {'8','.','.','.','.','.','.','.','.'},
 {'9','.','.','.','.','.','.','.','.'}}
 * 模型一
 *
 *
 * 模型二
 * 参考：http://www.cnblogs.com/ganganloveu/p/4170632.html
 * 依次检查每行，每列，每个子九宫格是否出现重复元素，如果出现返回false，否则返回true.

 难点在于表示第i个九宫格每个格点的坐标。

 观察行号规律：

 第0个九宫格：000111222; 第1个九宫格：000111222; 第2个九宫格：000111222;

 第3个九宫格：333444555; 第4个九宫格：333444555; 第5个九宫格：333444555;

 第6个九宫格：666777888; 第7个九宫格：666777888; 第8个九宫格：666777888;

 可见对于每三个九宫格行号增3；对于单个九宫格，每三个格点行号增1。

 因此第i个九宫格的第j个格点的行号可表示为i/3*3+j/3



 观察列号规律：

 第0个九宫格：012012012; 第1个九宫格：345345345; 第2个九宫格：678678678;

 第3个九宫格：012012012; 第4个九宫格：345345345; 第5个九宫格：678678678;

 第6个九宫格：012012012; 第7个九宫格：345345345; 第8个九宫格：678678678;

 可见对于下个九宫格列号增3，循环周期为3；对于单个九宫格，每个格点行号增1，周期也为3。

 周期的数学表示就是取模运算mod。

 因此第i个九宫格的第j个格点的列号可表示为i%3*3+j%3

 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 12/09/2017
 * @see
 * @since cgs-leetcode on  12/09/2017
 */
public class ValidSudoku {
    // have bug
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < board.length / 3; i++) {

            for(int j = 0; j < 3; j++) {
                boolean[] rowFlag = new boolean[9];
                boolean[] columnFlag = new boolean[9];
                boolean[] cubeFlag = new boolean[9];

                if(!checkRow(rowFlag, board, i * 3 + j)) {
                    return false;
                }
                if(!checkColumn(columnFlag, board, i * 3 + j)) {
                    return false;
                }
                if(!checkSubArray(cubeFlag, board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkRow(boolean[] flag, char[][] board, int r) {
        for(int i = 0; i < board.length; i++) {
            if(board[r][i] != '.') {
                if(flag[board[r][i] - '1']) {
                    return false;
                }
                flag[board[r][i] - '1'] = true;
            }
        }
        return true;
    }

    boolean checkColumn(boolean[] flag, char[][] board, int c) {
        for(int i = 0; i < board.length; i++) {
            if(board[i][c] != '.') {
                if(flag[board[i][c] - '1']) {
                    return false;
                }
                flag[board[i][c] - '1'] = true;
            }
        }
        return true;
    }

    boolean checkSubArray(boolean[] flag, char[][] board, int bigR, int bigC) {
        for(int i = 3 * bigR; i<3 * bigR + 3; i++) {
            for(int j = 3 * bigC; j<3 * bigC + 3; j++) {
                if(board[i][j] != '.') {
                    if(flag[board[i][j] - '1']) {
                        return false;
                    }
                    flag[board[i][j] - '1'] = true;
                }
            }
        }
        return true;
    }

    // 模型二参考
    public boolean isValidSudokuReference(char[][] board) {
        for(int i = 0; i<9; i++){
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        char[][] tmp =
                {
                        {'.','.','.','.','5','.','.','1','.'},
                        {'.','4','.','3','.','.','.','.','.'},
                        {'.','.','.','.','.','3','.','.','1'},
                        {'8','.','.','.','.','.','.','2','.'},
                        {'.','.','2','.','7','.','.','.','.'},
                        {'.','1','5','.','.','.','.','.','.'},
                        {'.','.','.','.','.','2','.','.','.'},
                        {'.','2','.','9','.','.','.','.','.'},
                        {'.','.','4','.','.','.','.','.','.'},};

//                {{'.','8','7','6','5','4','3','2','1'},
//                {'2','.','.','.','.','.','.','.','.'},
//                {'3','.','.','.','.','.','.','.','.'},
//                {'4','.','.','.','.','.','.','.','.'},
//                {'5','.','.','.','.','.','.','.','.'},
//                {'6','.','.','.','.','.','.','.','.'},
//                {'7','.','.','.','.','.','.','.','.'},
//                {'8','.','.','.','.','.','.','.','.'},
//                {'9','.','.','.','.','.','.','.','.'}};

        boolean validSudoku = new ValidSudoku().isValidSudoku(tmp);
        System.out.println(validSudoku);
    }
}
