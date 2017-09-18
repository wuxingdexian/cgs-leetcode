package others.medium;

import depthfirstsearch.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/" />
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given inorder and postorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 * <p>
 * 0. 本质：序列-》关系
 * 1. 建模：
 * 找到根元素，前面的元素构成根元素的左子树，后面的元素构成根元素的右子树。
 * {@link ConstructBinaryTreeFromPreorderAndInorderTraversal}类似
 * 2. 算法范式：
 * 3. 算法：
 * （1）从postorder的最后一个元素作为根节点
 * （2）在inorder中定位根节点，根节点前面的子数组构成左子树；后面的子数组构成右子树
 * （3）递归该过程
 * 4. 数据结构：
 * 5. 改进：// FIXME: 15/09/2017 数组遍历优化 使用Map缓存inorder的坐标
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/09/2017
 * @see
 * @since cgs-leetcode on  15/09/2017
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    Map<Integer, Integer> cacheInorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        cacheInorder = cacheInorder(inorder);
        return buildTree(postorder, 0 , postorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd) {
        if(postStart >= postEnd || inStart >= inEnd || postEnd > postorder.length || inEnd > inorder.length) {
            return null;
        }

        if(inStart == inEnd - 1) {
            return new TreeNode(inorder[inStart]);
        }

        TreeNode root = new TreeNode(postorder[postEnd - 1]);

//        int inMiddle = searchIndex(inorder, inStart, inEnd, postorder[postEnd - 1]);
        int inMiddle = searchIndex(postorder[postEnd - 1]);
        TreeNode leftTree = buildTree(postorder, postStart, postEnd - (inEnd - inMiddle), inorder, inStart, inMiddle);
        root.left = leftTree;
        TreeNode rightTree = buildTree(postorder, postEnd - (inEnd - inMiddle), postEnd - 1, inorder, inMiddle+1, inEnd);
        root.right = rightTree;

        return root;

    }

    int searchIndex(int[] order, int fromIndex, int toIndex, int key) {
        int index = -1;
        while (fromIndex < toIndex) {
            if (order[fromIndex] == key) {
                index = fromIndex;
                break;
            }
            fromIndex++;
        }
        return index;
    }

    // cache inorder' indexes
    Map<Integer, Integer> cacheInorder(int[] inorder) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i], i);
        }
        return cache;
    }
    int searchIndex(int key) {
        return cacheInorder.containsKey(key)? cacheInorder.get(key): -1;
    }


    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal traversal = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
//        int[] inOrder= {1,3,2};
//        int[] postOrder= {3,2,1};
        int[] inOrder= {2,1,3};
        int[] postOrder= {2,3,1};
        TreeNode treeNode = traversal.buildTree(inOrder, postOrder);
        System.out.println(treeNode);
    }
}
