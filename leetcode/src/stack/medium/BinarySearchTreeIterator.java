package stack.medium;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-search-tree-iterator/description/" />
 * 173. Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 * <p>
 * 0. 本质：排列 递增序列 关系
 * 1. 建模：
 * 二叉搜索树，自然中序遍历得到非递减序列
 *
 * 2. 算法范式：
 * 3. 算法：
 * 算法一：
 * （1）遍历树，构建线索二叉树，叶子节点的右指针指向中序遍历的后继。
 *
 * 算法二：
 * TODO 栈？
 * 看了其他的解，使用栈不断用维护截止当前节点的最小序列，虽然在hasNext()符合O(1)，但是内存空间利用上符合O(h)，但是在next()时需要不断遍历当前节点的最左节点拿到最小序列，从而不满足O(1)。不符合要求。
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：// FIXME: 28/08/2017 注意java的引用传入，传递的只是地址的复制值，这就导致
 * FIXME （1）引用传入后，在函数内可以根据地址引用改变这个内存地址块的值；（2）退出函数后，原来传入给函数的对象变量其地址值不会变更。
 * 所以threadTree(TreeNode root)有两个写法，一种是设置全局的TreeNode preNode，再函数内修改preNode指向的内存地址；另一中就是传递给函数后，再通过函数返回要更新的地址值，然后更新，这种比较麻烦，需要很注意。
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 28/08/2017
 * @see
 * @since cgs-leetcode on  28/08/2017
 */
public class BinarySearchTreeIterator {
    TreeNode startNode;
    TreeNode preNode;
//    public BSTIterator(TreeNode root) {
    public BinarySearchTreeIterator(TreeNode root) {
        this.startNode = getStart(root);
        threadTree(root);
    }

    // in-order traversal threading
    void threadTree(TreeNode root) {
        if(root == null) {
            return;
        }

        threadTree(root.left);
        if(preNode != null) {
            preNode.right = root;
        }
        preNode = root;

        threadTree(root.right);
    }

    TreeNode getStart(TreeNode root) {
        if(root == null) {
            return null;
        }

        if(root.left == null) {
            return root;
        }
        return getStart(root.left);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return startNode != null;
    }

    /** @return the next smallest number */
    public int next() {
        if(hasNext()) {
            int val = startNode.val;
            startNode = startNode.right;
            return val;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeIterator binarySearchTreeIterator = new BinarySearchTreeIterator(build());
        System.out.println(binarySearchTreeIterator);

    }

    static TreeNode build() {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        root.left = left;
        return root;
    }
}
