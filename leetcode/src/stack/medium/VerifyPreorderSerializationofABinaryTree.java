package stack.medium;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：思考前序遍历的DFS思路，然后列举几个树结构，从其前序序列中找规律，逆向看，在栈上操作有结果
 * 3. 算法：
 * 4. 数据结构：栈
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 21/08/2018
 * @see
 * @since cgs-leetcode on  21/08/2018
 */
public class VerifyPreorderSerializationofABinaryTree {
    public boolean isValidSerialization(String preorder) {

        if (null == preorder || preorder.length() == 0) {
            return false;
        }

        Stack<String> stack1 = new Stack<>();
        String[] letters = preorder.split(",");

        for (int i = letters.length - 1; i >= 0; i--) {
            if ("#".equals(letters[i])) {
                stack1.push(letters[i]);
            } else {
                if (stack1.size() < 2 || !"#".equals(stack1.pop()) || !"#".equals(stack1.pop())) {
                    return false;
                }
                stack1.push("#");
            }
        }

        return stack1.size() == 1 && stack1.peek().equals("#")? true: false;

    }
}
