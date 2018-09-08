package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximum-binary-tree/description/" />
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

 The root is the maximum number in the array.
 The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 Construct the maximum tree by the given array and output the root node of this tree.

 Example 1:
 Input: [3,2,1,6,0,5]
 Output: return the tree root node representing the following tree:

 6
 /   \
 3     5
 \    /
 2  0
 \
 1
 Note:
 The size of the given array will be in the range [1,1000].
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：分治法
 * 3. 算法：
 * 可以分两种
 * （1）分治法，则找数组中最大，然后分别针对两边构建子树
 * （2）从开始遍历法，辅助栈，依次遍历，头元素压栈，然后从第二个元素开始遍历
 *      （a）比栈顶小，则直接为栈顶node的右节点；
 *      （b）比栈顶大，则pop，继续遍历栈顶，直到栈为空或找到比栈顶小，
 *  以 3,2,1,6,0,5举例
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 25/08/2018
 * @see
 * @since cgs-leetcode on  25/08/2018
 */
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        if (null == nums || nums.length == 0) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();

        stack.push(new TreeNode(nums[0]));

        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i]);
            if (stack.peek().val > nums[i]) {
                stack.peek().right = node;
                stack.push(node);
            } else {
                TreeNode top = stack.peek();
                while (!stack.isEmpty()) {
                    if (stack.peek().val > nums[i]) {
                        top = stack.peek();
                        break;
                    } else {
                        top = stack.pop();
                    }
                }
                if (top.val < nums[i]) {
                    node.left = top;
                } else {
                    node.left = top.right;
                    top.right = node;
                }
                stack.push(node);
            }
        }
        return stack.get(0);
    }
}
