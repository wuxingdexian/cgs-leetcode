package others.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/" />
 * 116. Populating Next Right Pointers in Each Node
 * Given a binary tree

 struct TreeLinkNode {
 TreeLinkNode *left;
 TreeLinkNode *right;
 TreeLinkNode *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.

 Note:

 You may only use constant extra space.
 You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 For example,
 Given the following perfect binary tree,
 1
 /  \
 2    3
 / \  / \
 4  5  6  7
 After calling your function, the tree should look like:
 1 -> NULL
 /  \
 2 -> 3 -> NULL
 / \  / \
 4->5->6->7 -> NULL
 * <p>
 * 0. 本质：关系-》树
 * 1. 建模：每层从左到右构成一个序列
 *
 * 2. 算法范式：
 * 3. 算法：breath-first search
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/09/2017
 * @see
 * @since cgs-leetcode on  10/09/2017
 */
public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
        if(root == null) {
            return;
        }

        List<TreeLinkNode> currentLevelNodes = new ArrayList();
        currentLevelNodes.add(root);

        // 这步是判断的currentLevelNodes是否为空，别混淆了
        while (!currentLevelNodes.isEmpty()) {
            for (int i = 0; i < currentLevelNodes.size(); i++) {
                currentLevelNodes.get(i).next = i < currentLevelNodes.size() - 1 ? currentLevelNodes.get(i + 1) : null;
            }
            currentLevelNodes = getNextLevelNodes(currentLevelNodes);
        }
    }

    List<TreeLinkNode> getNextLevelNodes(List<TreeLinkNode> currentLevelNodes) {
        List<TreeLinkNode> nextLevelNodes  = new ArrayList();
        for(TreeLinkNode node: currentLevelNodes) {
            if(node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if(node.right != null) {
                nextLevelNodes.add(node.right);
            }
        }
        return nextLevelNodes;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }


    //---------------------------dfs，摘自LeetCode，不错----------------------
    public void connectDfs(TreeLinkNode root) {
        if(root == null)
            return;

        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
    }
    //---------------------------dfs----------------------


    public static void main(String[] args) {
        PopulatingNextRightPointersInEachNode pointersInEachNode = new PopulatingNextRightPointersInEachNode();
        pointersInEachNode.connect(pointersInEachNode.build());
        System.out.println(pointersInEachNode);
    }

    TreeLinkNode build() {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        return root;
    }
}
