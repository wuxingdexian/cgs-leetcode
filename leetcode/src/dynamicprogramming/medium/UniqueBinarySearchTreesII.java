package dynamicprogramming.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 1. 建模：
 * （1）dynamic programming
 * // TODO: 11/08/2017 未找到~~
 *
 * （2）decision tree
 *  从第一个元素开始，建立二叉搜索树，执行下面操作：
 *  将元素x添加到树中；
 *  将x和其父节点交换得到另一个棵树；将得到树再讲x和其父节点交换，得到另一个棵树...执行该步骤，直到根节点。
 *  这里交换：父节点作为x的子节点，x替换到那个位置
 *  举例
 *   执行到第一个元素：                               1
 *
 *   执行到第二个元素：                      (1)                                    (2)
 *                                      (    2(R)   ) 符合要求，执行一次旋转  (  1(L)    )
 *
 *   执行到第三个元素：          (1)                                     （1）                             (3)
 *                           (    2(R)   )                             (    3(R)   )            (    1(L)   )
 *                          (       3(R)   ) 符合要求，2和3交换        (   2(L)   )   1和3交换          (   2(R)   )  依次执行剩下的
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class UniqueBinarySearchTreesII {
    List<TreeNode> treeNodes = new ArrayList<>();
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public List<TreeNode> generateTrees(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }
        return generate(1, n);
//        return generateSubtrees(1, n);
    }

    List<TreeNode> generate(int start, int end) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (start > end) {
            // 必须加元素null~~
            treeNodes.add(null);
            return treeNodes;
        }

        for (int i = start; i <= end; i++) {

            List<TreeNode> leftTreeNode = generate(start, i - 1);
            List<TreeNode> rightTreeNode = generate(i + 1, end);
            for (TreeNode left: leftTreeNode) {
                for (TreeNode right: rightTreeNode) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = left;
                    treeNode.right = right;
                    treeNodes.add(treeNode);
                }
            }

        }

        return treeNodes;
    }


    //----------------------------------------------------------------------------------------------------------------------------------------
    /*  很烂的代码，并且性能很慢
    public List<TreeNode> generateTrees(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }

        List<TreeNodeInner> treeNodes = new ArrayList();
        treeNodes.add(new TreeNodeInner(-1));

        for (int i = 1; i <= n; i++) {
            List<TreeNodeInner> tmpTreeNodes = new ArrayList<>();
            for (TreeNodeInner treeNode: treeNodes) {
                insert(treeNode, i);
                TreeNodeInner copyTree1 = copy(index(treeNode, -1), null);
                tmpTreeNodes.add(copyTree1);

                TreeNodeInner indexTree = index(treeNode, i);

                do {
                    if(change(indexTree)) {
                        TreeNodeInner indexRoot = index(treeNode, -1);
                        TreeNodeInner copyTree = copy(indexRoot, null);
                        tmpTreeNodes.add(copyTree);
                        indexTree = index(treeNode, i);
                    }
                } while(indexTree != null && indexTree.parent.val != -1);
            }
            treeNodes = tmpTreeNodes;
        }

        List<TreeNode> treeNodeList = new ArrayList();
        for (TreeNodeInner treeNode: treeNodes) {
            treeNodeList.add(copy(treeNode.right));
        }

        return treeNodeList;

    }

    TreeNodeInner index(TreeNodeInner treeNode, int n) {
        TreeNodeInner tmpTreeNode = treeNode;
        while (null != tmpTreeNode) {
            if (tmpTreeNode.val == n) {
                return tmpTreeNode;
            }
            if (tmpTreeNode.val > n) {
                tmpTreeNode = tmpTreeNode.left;
            } else {
                tmpTreeNode = tmpTreeNode.right;
            }
        }
        return null;
    }

    void insert(TreeNodeInner treeNode, int n) {
        TreeNodeInner treeNodeIndex = treeNode;
        while (treeNodeIndex.right != null) {
            if (treeNodeIndex.val > n) {
                treeNodeIndex = treeNodeIndex.left;
            } else {
                treeNodeIndex = treeNodeIndex.right;
            }
        }
        TreeNodeInner newTree = new TreeNodeInner(n);
        newTree.parent = treeNodeIndex;
        treeNodeIndex.right = newTree;
        return;

    }

    TreeNodeInner copy(TreeNodeInner treeNode, TreeNodeInner parentTreeNode) {
        if (null !=  treeNode) {
            TreeNodeInner treeNodeCopy = new TreeNodeInner(treeNode.val);
            treeNodeCopy.parent = parentTreeNode;
            if (treeNode.left != null) {
                treeNodeCopy.left = copy(treeNode.left, treeNodeCopy);
            }
            if (treeNode.right != null) {
                treeNodeCopy.right = copy(treeNode.right, treeNodeCopy);
            }
            return treeNodeCopy;
        }

        return null;
    }

    boolean change(TreeNodeInner treeNode) {
        TreeNodeInner parentTree = treeNode.parent;
        if (null != parentTree && parentTree.val == -1) {
            return false;
        }

        parentTree.parent.right = treeNode;

        parentTree.right = treeNode.left;
        if (null != treeNode.left) {
            treeNode.left.parent = parentTree;
        }

        treeNode.left = parentTree;
        treeNode.parent = parentTree.parent;

        parentTree.parent = treeNode;

        return true;
    }

    TreeNode copy(TreeNodeInner treeNodeInner) {
        if (null !=  treeNodeInner) {
            TreeNode treeNodeCopy = new TreeNode(treeNodeInner.val);
            if (treeNodeInner.left != null) {
                treeNodeCopy.left = copy(treeNodeInner.left);
            }
            if (treeNodeInner.right != null) {
                treeNodeCopy.right = copy(treeNodeInner.right);
            }
            return treeNodeCopy;
        }

        return null;
    }


    public class TreeNodeInner {
        int val;
        TreeNodeInner left;
        TreeNodeInner right;
        TreeNodeInner parent;

        TreeNodeInner(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "val:" + val + ",left:" + (null != left? left.val: "") + ",right:" + (null != right? right.val: right);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    */
    //----------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        List<TreeNode> treeNodes = new UniqueBinarySearchTreesII().generateTrees(3);
        System.out.println(treeNodes);
    }
}
