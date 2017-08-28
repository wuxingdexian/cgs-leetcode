package tree.easy;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/convert-bst-to-greater-tree/description/" />
 * 538. Convert BST to Greater Tree
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

 Example:

 Input: The root of a Binary Search Tree like this:
 5
 /   \
 2     13

 Output: The root of a Greater Tree like this:
 18
 /   \
 20     13
 * <p>
 * 0. 本质：组合+偏序
 * 1. 建模：
 * 这里默认左子树所有节点小于父节点，右子树所有节点大于父节点
 *
 * 设定要加入的差值为difference = sum(father_tree) + sum(right_child_tree)
 * 也即（1）父节点及以上的所有节点的和；（2）当前节点的右子树的所有节点和。
 * 2. 算法范式：backtracking
 * 3. 算法：
 * （1）遍历右子树的所有节点，返回累加和，传入左节点；
 * （2）当前节点的值a更新为a + sum(father_tree) + sum(right_child_tree)
 *
 * 执行（1）直到叶子节点，叶子节点的值从当前值a更新为a+sum(father_tree)，即当前值加上父节点传入的累加和，并返回最后的累加和
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 26/08/2017
 * @see
 * @since cgs-leetcode on  26/08/2017
 */
public class ConvertBSTToGreaterTree {
    public TreeNode convertBST(TreeNode root) {
        convertBST(root, 0);
        return root;
    }

    // sum right child nodes' val
    int convertBST(TreeNode root, int parentSum) {
        if(root == null) {
            return 0;
        }

        int rightSum = convertBST(root.right, parentSum);
        root.val = root.val + rightSum + parentSum;
        int leftSum = convertBST(root.left, root.val);
        // 返回右子树的所有节点和，这里返回的是一整颗树的和。
        return root.val - parentSum + leftSum;

        // 这步有重复计算
        // int rightSum = sumRightNodeVal(root.right);
        // root.val = root.val + parentSum + rightSum;
        // convertBST(root.left, root.val);
        // convertBST(root.right, parentSum);
        // return 0;
    }

    // 这步有点啰嗦
    int sumRightNodeVal(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftSum = sumRightNodeVal(root.left);
        int rightSum = sumRightNodeVal(root.right);
        return leftSum + rightSum + root.val;
    }

    public static void main(String[] args) {
        ConvertBSTToGreaterTree validateBinarySearchTree = new ConvertBSTToGreaterTree();
        int validBST = validateBinarySearchTree.convertBST(validateBinarySearchTree.build(), 0);
        System.out.println(validBST);
    }

    TreeNode build() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(13);
        root.left = left;
        root.right = right;
        return root;
    }
}
