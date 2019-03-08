package backtracking.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/letter-case-permutation/description/" />
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

 Examples:
 Input: S = "a1b2"
 Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

 Input: S = "3z4"
 Output: ["3z4", "3Z4"]

 Input: S = "12345"
 Output: ["12345"]
 Note:

 S will be a string with length between 1 and 12.
 S will consist only of letters or digits.
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：DFS，backtracking
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
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {

        char[] chars = S.toCharArray();
        int count=getLetterCount(chars);
        List<String> solution = new LinkedList<>();
        solution.add(S);
        for(int i=0;i<count;i++) {
            dfs(chars, solution, 0, i+1);
        }
        return solution;
    }

    private int getLetterCount(char[] chars) {
        int count =0;

        for(int i=0;i<chars.length;i++) {
            if(('a' <= chars[i] && chars[i] <= 'z') || ('A' <= chars[i] && chars[i] <= 'Z')) {
                count++;
            }
        }
        return count;
    }

    private void dfs(char[] chars, List<String> solution, int index, int count) {
        if(count <= 0) {
            solution.add(String.valueOf(chars));
            return;
        }

        for(int i=index;i<chars.length;i++) {
            if(('a' <= chars[i] && chars[i] <= 'z') || ('A' <= chars[i] && chars[i] <= 'Z')) {
                changeOneChar(chars, i);
                dfs(chars, solution, i+1, count-1);
                changeOneChar(chars, i);
            }
        }
    }

    private String changeOneChar(char[] chars, int index) {
        int difference = ('a' - 'A');
        if('a' <= chars[index] && chars[index] <= 'z') {
            chars[index] = (char) (chars[index] - difference);
        } else if('A' <= chars[index] && chars[index] <= 'Z') {
            chars[index] = (char) (chars[index] + difference);
        }
        return String.valueOf(chars);
    }
}
