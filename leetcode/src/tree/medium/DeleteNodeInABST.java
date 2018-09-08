package tree.medium;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 和题目所有，可以有两种方式，（1）把左节点提升为父节点；（2）把右节点提升为父节点
 * 调整后要保持BST不变，这里选择提升右节点，此时需要把左节点的子树挂着到右节点的最左叶子节点的左节点上
 * 需要记忆父节点，便于操作
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：增加哨兵节点，简化操作
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 25/08/2018
 * @see
 * @since cgs-leetcode on  25/08/2018
 */
public class DeleteNodeInABST {

    TreeNode targetNode;
    TreeNode targetNodeParent;
    public TreeNode deleteNode(TreeNode root, int key) {

        // 哨兵节点
        TreeNode guardNode = new TreeNode(0);
        guardNode.left = root;

        findTargetNode(guardNode, key);

        if (null == targetNode) {
            return root;
        }
        
        TreeNode newNode = delete();

        // targetNodeParent.right 判断NPU 防止哨兵右节点null
        if (null != targetNodeParent.right && targetNodeParent.right.val == targetNode.val) {
            targetNodeParent.right = newNode;
        } else if (null != targetNodeParent.left && targetNodeParent.left.val == targetNode.val){
            targetNodeParent.left = newNode;
        }
        return guardNode.left;
    }

    /**
     * @return 返回删除节点后的根节点
     */
    private TreeNode delete() {
        
        if (null == targetNode.left && null == targetNode.right) {
            return null;
        } else if (null == targetNode.left && null != targetNode.right) {
            return targetNode.right;
        } else if (null != targetNode.left && null == targetNode.right) {
            return targetNode.left;
        } else {
            TreeNode leftLeaf = getLeftLeafOfRightChild(targetNode.right);
            leftLeaf.left = targetNode.left;
            return targetNode.right;
        }
    }
    
    private TreeNode getLeftLeafOfRightChild(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return getLeftLeafOfRightChild(root.left);
    }

    private void findTargetNode(TreeNode root, int key) {
        if (root == null) {
            return;
        }

        if (null != root.left && root.left.val == key) {
            targetNode = root.left;
            targetNodeParent = root;
            return;
        }
        if (null != root.right && root.right.val == key) {
            targetNodeParent = root;
            targetNode = root.right;
            return;
        }
        if (null == targetNode) {
            findTargetNode(root.left, key);
        }
        if (null == targetNode) {
            findTargetNode(root.right, key);
        }
    }

}
