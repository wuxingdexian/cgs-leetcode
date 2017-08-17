package divideandconquer.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/different-ways-to-add-parentheses/description/" />
 * 241. Different Ways to Add Parentheses
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


 Example 1
 Input: "2-1-1".

 ((2-1)-1) = 0
 (2-(1-1)) = 2
 Output: [0, 2]


 Example 2
 Input: "2*3-4*5"

 (2*(3-(4*5))) = -34
 ((2*3)-(4*5)) = -14
 ((2*(3-4))*5) = -10
 (2*((3-4)*5)) = -10
 (((2*3)-4)*5) = 10
 Output: [-34, -14, -10, -10, 10]
 * <p>
 * 1. 建模：
 *
 * 递归定义的集合/结构 “参考离散数学第5章”
 * 基本数据集合{number、operation(+、-、*)、(、）}五种类型的字符
 * basic step：Let (number operation number) is well-form formula.
 * recursive step：if E and F are well-form formulae, then (E operation F) is well-form formulae.
 *
 * （1）模型1
 * 先生成合法的包含括号的字符串，然后结合栈来计算。
 * 其中生成合法括号过程，可以参考下面模型2.
 * // TODO: 16/08/2017 因为模型1需要代码较多，选择模型2进行。但模型1在实际应用中更方便理解和维护。
 * （2）模型2-树+recurrence relation+分治法
 * 依次以操作符合为根，对两边子树分别对应操作数。如果子树不是操作数，则对子串递归知道叶子节点都是操作数。
 * 如2*3-4*5，以第一个乘号为例
 *                              *
 *                          /       \
 *                        2        3-4*5
 *                         递归右子树
 *                          -               *
 *                      /       \       /       \
 *                    3        4*5    3-4       5
 *                    递归右子树         递归左子树
 *                          *           -
 *                       /      \     /     \
 *                       4      5     3     4
 * 结合起来分别得到两棵树
 *                           *                          *
 *                        /     \                    /     \
 *                        3     -                   3       *
 *                            /   \                       /   \
 *                            3    *                    -      5
 *                              /    \                /   \
 *                              4     5               3   4
 * 此时以*为根的树，获取了两个子树，如果根据此过程直接计算最后的值，那右子树分治后需要返回两个值，要注意了。
 *
 * 2. 算法范式：
 * divide-and-conquer
 *
 * 3. 算法：
 * （1）模型1，首先得到字符串合法组合形式；然后针对合法组合形式，使用两个栈来完成操作。
 * （2）模型2，分治法，递归
 * 计算步骤说明：
 * （1）先将合法组合串依次压入一个栈A，然后从栈顶开始pop()取数，
 * 如果遇到“(”左括号，则栈B连续pop()四个元素，依次得到操作数2、操作符、操作数1和右括号“)”；否则该字符压入栈B。
 * （2）执行操作后得到一个操作数，该操作数压入栈B；
 * 依次执行上述计算步骤，知道栈A为空。
 *
 *
 * 4. 数据结构：
 * （1）模型1使用栈来辅助计算
 *
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
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> operationResult = new ArrayList<>();

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                List<Integer> leftResult = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightResult = diffWaysToCompute(input.substring(i + 1, input.length()));
                for(Integer leftOperation: leftResult) {
                    for (Integer rightOperation: rightResult) {
                        if(input.charAt(i) == '+') {
                            operationResult.add(leftOperation + rightOperation);
                        } else if(input.charAt(i) == '-') {
                            operationResult.add(leftOperation - rightOperation);
                        } else {
                            operationResult.add(leftOperation * rightOperation);
                        }
                    }
                }
            }
        }
        if(operationResult.size() == 0) {
            operationResult.add(Integer.valueOf(input));
        }
        return operationResult;
    }

}
