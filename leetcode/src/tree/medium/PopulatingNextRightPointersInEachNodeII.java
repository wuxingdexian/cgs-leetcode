package tree.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：BFS
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2018
 * @see
 * @since cgs-leetcode on  24/08/2018
 */
public class PopulatingNextRightPointersInEachNodeII {
    class TreeLinkNode {
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
    }

    public void connect(TreeLinkNode root) {

        List<TreeLinkNode> parents = new LinkedList<>();
        if (null != root) {
            parents.add(root);
        }

        while (!parents.isEmpty()) {
            List<TreeLinkNode> children = new LinkedList<>();
            TreeLinkNode tmpNode = parents.get(0);
            addChildren(tmpNode, children);
            for (int i = 1; i < parents.size(); i++) {
                tmpNode.next = parents.get(i);
                tmpNode = parents.get(i);
                addChildren(parents.get(i), children);
            }
            parents = children;
        }
    }

    private void addChildren(TreeLinkNode root, List<TreeLinkNode> children) {
        if (root.left != null) {
            children.add(root.left);
        }
        if (root.right != null) {
            children.add(root.right);
        }
    }

}
