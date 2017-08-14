package depthfirstsearch.easy;

import com.shen.depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/same-tree/description/" />
 * 100. Same Tree
 * Given two binary trees, write a function to check if they are equal or not.

 Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

 * <p>
 * 1. 建模：
 * 树模型
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since DiscreteMathematics on  14/08/2017
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        StringBuilder sbp = new StringBuilder();
        StringBuilder sbq = new StringBuilder();

        dfs(p, sbp);
        dfs(q, sbq);
        return sbp.toString().equals(sbq.toString());
    }

    void dfs(TreeNode t, StringBuilder sb) {
        if(t != null) {
            sb.append(t.val);
            dfs(t.left, sb);
            dfs(t.right, sb);
        } else {
            sb.append("null");
        }
    }
}
