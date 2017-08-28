package backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/generate-parentheses/description/" />
 * 22. Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 * <p>
 * 0. 本质：排列，关系
 * 1. 建模：
 * （1）决策树建模，开始为一个“()”，下面有三个三个节点，分别对应在左中右三个位置插入新的括号；期间会有重复的括号生成，需要set去重；
 *               ()
 *        ()(), (()), ()()
 *        ....
 *  这个方法比较挫，建立的决策树模型不好
 *
 * （2）决策树建模，建立有序对(parentheses_char[], L_char[], R_char[])，TmpParentheses临时的括号串，L_char[]左括号数组，R_char[]右括号数组。
 * 必须满足条件：length(L_char[]) >= length(R_char[]) >=0
 *                                              (parentheses_char[2n], L_char[n], R_char[n])
 *                                  (parentheses_char[2n](其中parentheses_char[0]='('), L_char[n-1], R_char[n])
 *  (parentheses_char[2n](其中parentheses_char[0]='('，parentheses_char[1]='('), L_char[n-2], R_char[n]),  (parentheses_char[2n](其中parentheses_char[0]='('，parentheses_char[1]=')'), L_char[n-1], R_char[n-1]),
 *  .....
 *
 * （3）recurrence relation
 * 设定num('(')为左括号的个数，num(')')为右括号的个数，构建过程两个的关系必须满足num('(') >= num(')')，构建完成时num('(') = num(')')
 * 给定有序对ordered-pair：p(i,j)，代表合法的括号排列个数，i和j分别为num('('),num(')')，并满足上述关系式。
 * 有
 * p(i,j)=0，当i < j
 * p(i,j)=p(i,j-1)，当i=j
 * p(i,j)=p(i-1,j) + p(i,j-1)，当i>j
 * 初始条件p(0,0)=1
 * 提示：列出二维表格，将会很容易观察，给与更多启示
 *
 * 2. 算法范式：backtracking
 * 3. 算法：backtracking总是跟深度优先遍历紧密联系，或者说backtracking的执行上都是使用深度优先的方式。从上面决策树建模，特别是模型（2）
 * 4. 数据结构：
 * （1）模型1使用StringBuilder，在进行insert操纵时还是会有很大的性能浪费，建议换为char数组。因为存在重复，所以还需要借助set去重；
 * （2）模型2比较好，不好出现重复情况，借助char数组保存括号字符，最后再转为String。
 * 5. 改进：StringBuilder在中间插入数据时，性能太低。对于字符串的操作，建议改为char数组，因为string、StringBuilder等底层都是用char来存储的。
 * 参考别人的代码，重新建立决策树（2）
 * 使用hashset比treeset要快
 * 6. 注意：set转list，可以直接使用new ArrayList<String>(set)
 * String转char[]，使用{@link String#toCharArray()}; char[]转String，使用{@link String#valueOf(char[])}
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * 7. 启发：找到一些的关键属性，对于建立决策树模型很很有用
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class GenerateParentheses {

    //-------------------------dynamic programming--------------------------------------
    // 若是使用dp来解这个字符串，那么中间需要的临时变量有点多，这里变换下，使用dp来求有多少个合法的括号字符串吧
    public int generateParenthesisDP(int n) {
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if(i == j) {
                    dp[i][j] = (dp[i][j - 1]);
                } else if (i > j){
                    dp[i][j] = dp[i - 1][j] + (j != 0? dp[i][j - 1]: 0);
                }
            }
        }

        return dp[n][n];
    }

    public static void main(String[] args) {
        int i = new GenerateParentheses().generateParenthesisDP(3);
        System.out.println(i);
    }
    //-------------------------dynamic programming--------------------------------------

    public List<String> generateParenthesis(int n) {

        Set<String> set = new HashSet<String>();
        if(n < 1) {
            return new ArrayList();
        }
        StringBuilder sb = new StringBuilder("()");
        // set.add(sb.toString());
        generate(sb, 1, n, set);
        // set 2 list
        return new ArrayList<String>(set);
    }

    private void generate(StringBuilder sb, int step, int n, Set<String> parenthesisSet) {
        if(step > n) {
            return;
        }
        if(step == n) {
            parenthesisSet.add(sb.toString());
            return;
        }

        for(int i = 0; i < sb.length(); i++) {
            StringBuilder tmpSb = new StringBuilder(sb);
            // this function
            tmpSb.insert(i, "()");
            generate(tmpSb, step + 1, n, parenthesisSet);
        }
    }


    public List<String> generateParenthesisStandard(int n) {
        List<String> parenthesesList = new ArrayList<>();
        if (n == 0) {
            return parenthesesList;
        }
        generate(new char[2 * n], n, n, n, parenthesesList);
        return parenthesesList;
    }

    private void generate(char[] parentheses, int leftNum, int rightNum, int n, List<String> parenthesesList) {
        if(rightNum == 0) {
            // char[] 转string 是否有更高效方法？
            parenthesesList.add(String.valueOf(parentheses));
        }
        if(leftNum > 0) {
            parentheses[2 * n - leftNum - rightNum] = '(';
            generate(parentheses, leftNum - 1, rightNum, n, parenthesesList);
        }
        if(rightNum > leftNum) {
            parentheses[2 * n - leftNum - rightNum] = ')';
            generate(parentheses, leftNum, rightNum - 1, n, parenthesesList);
        }
    }

}
