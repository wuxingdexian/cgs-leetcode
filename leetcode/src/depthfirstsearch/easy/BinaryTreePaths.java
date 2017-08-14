package depthfirstsearch.easy;

import com.shen.depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-tree-paths/description/" />
 * 257. Binary Tree Paths
 *
 * Given a binary tree, return all root-to-leaf paths.

 For example, given the following binary tree:

 1
 /   \
 2     3
 \
 5
 All root-to-leaf paths are:

 ["1->2->5", "1->3"]
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link LinkedList#removeLast()}是该list特有的，List没有，所以dfs函数valpath变量要用LinkedList<Integer>类型
 * {@link AbstractStringBuilder#subSequence(int, int)}有获取字串得方法，返回String
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since DiscreteMathematics on  14/08/2017
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> allpath = new ArrayList();
        dfs(root, new LinkedList<Integer>(), allpath);
        return allpath;
    }

    void dfs(TreeNode root, LinkedList<Integer> valpath, List<String> allpath) {
        if(root != null) {
            valpath.add(root.val);
            if(root.left == null && root.right == null) {
                allpath.add(format(valpath));
            } else {
                dfs(root.left, valpath, allpath);
                dfs(root.right, valpath, allpath);
            }
            valpath.removeLast();
        }
    }

    String format(List<Integer> valpath) {
        StringBuilder sb = new StringBuilder();
        for(Integer val: valpath) {
            sb.append(val);
            sb.append("->");
        }
        int index = sb.lastIndexOf("->");
        return sb.subSequence(0, index >=0? index: 0).toString();
    }
}
