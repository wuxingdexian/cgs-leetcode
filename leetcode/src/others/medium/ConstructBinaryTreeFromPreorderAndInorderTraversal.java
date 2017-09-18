package others.medium;

import depthfirstsearch.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/" />
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 * <p>
 * 0. 本质：序列-》关系，较少见的转换类型
 * 1. 建模：二叉树
 * 找到根元素，前面的元素构成根元素的左子树，后面的元素构成根元素的右子树。
 * 存在重复子问题，存在recurrence relation，公式化描述有点麻烦，就直接看算法描述好了
 *           1
 *         /   \
 *        2    3
 *       / \  / \
 *      4  5 6  7
 * preorder: 1,2,4,5,3,6,7
 * inorder:4,2,5,1,6,3,7
 *
 * 注意没有子树或只有一个子树的情况
 * 2. 算法范式：
 * 3. 算法：
 * 这里要充分利用JDK的二叉搜索
 * （1）拿到preorder范围数组的第一个元素值x，
 * （2）从inorder范围数组定位到x
 * （3）将inorder范围数组的x前面和后面的子数组分别构建子树，然后分别左右x节点左右子树
 * （4）不断递归（1）~（3）步骤，
 * （5）终止条件为：子数组只有1一个元素
 *
 * 注意：在找inorder范围数组的x前面和后面的子数组分别构建子树时，要保持preorder的同步。同步过程以个数为准，不要使用中序遍历的middle的前一个值来找preorder的子数组范围。
 * 这在{3,2,1,4,5,6} 和{1,2,3,4,5,6}是失败的。 因为preorder的性质，肯定是先遍历左子树，所以使用<code>preStart + (inMiddle - inStart) + 1</code>而非注释部分代码
 * 4. 数据结构：
 * 5. 改进：// FIXME: 15/09/2017 数组遍历优化
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/09/2017
 * @see
 * @since cgs-leetcode on  15/09/2017
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    Map<Integer, Integer> cacheInorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        cacheInorder = cacheInorder(inorder);
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart >= preEnd || inStart >= inEnd || preEnd > preorder.length || inEnd > inorder.length) {
            return null;
        }
        if(inStart == inEnd - 1) {
            return new TreeNode(inorder[inStart]);
        }

        TreeNode root = new TreeNode(preorder[preStart]);

//        int inMiddle = searchIndex(inorder, inStart, inEnd, preorder[preStart]);
        int inMiddle = searchIndex(preorder[preStart]);
        TreeNode leftTree = buildTree(preorder, preStart+1, preStart + (inMiddle - inStart) + 1, inorder, inStart, inMiddle);
        root.left = leftTree;
        TreeNode rightTree = buildTree(preorder, preStart + (inMiddle - inStart) + 1, preEnd, inorder, inMiddle+1, inEnd);
        root.right = rightTree;

//        int preMiddle = inMiddle <= inStart? preStart: searchIndex(preorder, preStart, preEnd, inorder[inMiddle-1]);
//        if(inMiddle > inStart) {
//            // inMiddle > inStart 说明有右子树
////            int preMiddle = searchIndex(preorder, preStart, preEnd, inorder[inMiddle-1]);
//            TreeNode leftTree = buildTree(preorder, preStart+1, preMiddle+1, inorder, inStart, inMiddle);
//            root.left = leftTree;
//        }
//        if(inMiddle + 1 < inEnd) {
//            // inMiddle <= inStart 说明没有没有左子树
////            int preMiddle = inMiddle <= inStart? preStart: searchIndex(preorder, preStart, preEnd, inorder[inMiddle-1]);
//            TreeNode rightTree = buildTree(preorder, preMiddle+1, preEnd, inorder, inMiddle+1, inEnd);
//            root.right = rightTree;
//        }
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
        ConstructBinaryTreeFromPreorderAndInorderTraversal tree = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
//        TreeNode root = tree.build();
//        int[] preOrder= {4,1,2,3};
        int[] preOrder= {1,2,3,4};
        int[] inOrder= {1,2,3,4};
//        int[] preOrder= {6,4,1,2,3,5};
//        int[] inOrder= {1,2,3,4,5,6};
//        int[] preOrder= {3,2,1,4,5,6};
//        int[] inOrder= {1,2,3,4,5,6};
        TreeNode treeNode = tree.buildTree(preOrder, inOrder);

        System.out.println(treeNode);
    }

    TreeNode build() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        return root;
    }
}
