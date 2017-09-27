package others.hard;

import depthfirstsearch.TreeNode;
import others.selfpractice.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/" />
 * 297. Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 For example, you may serialize the following tree

 1
 / \
 2   3
 / \
 4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


 * <p>
 * 0. 本质：函数、序列
 * 1. 建模：
 * 如何找到那个函数，函数和函数逆
 * 二叉树
 *
 *                  1
 *             /        \
 *            2         3
 *          /              \
 *         4                5
 *          \
 *          1
 * 2. 算法范式：
 * 3. 算法：
 *
 * （1）使用前序+中序遍历来序列化，然后反序列化。注意判断当存在不同节点值相同的情况
 *
 * （2）bfs
 * 对于模型的二叉树，
 *      1）序列化二叉树，如上面模型，得{1;2,3;4,N,N,5;N,1}，不同层按分号“;”隔开；同一层之间按逗号隔开；数字对应下一层两倍数字。null使用N代表。
 *      2）反序列化二叉树，当前层和下一层的关系：如果当前层的不是N的第i个节点，那么对应下一层的2*i和2*i+1两个节点
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/09/2017
 * @see
 * @since cgs-leetcode on  19/09/2017
 */
public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return null;
        }

        List<TreeNode> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(root);
        return serializeBfs(currentLevelNodes);
    }

    String serializeBfs(List<TreeNode> currentLevelNodes) {
        StringBuilder sb = new StringBuilder();
        sb.append(currentLevelNodes.get(0).val);
        sb.append(";");

        while (!currentLevelNodes.isEmpty()) {
            List<TreeNode> nextLevelNodes = new ArrayList<>();
            for (TreeNode node: currentLevelNodes) {
                if (node.left != null) {
                    nextLevelNodes.add(node.left);
                }
                sb.append(node.left == null? "N": node.left.val);
                sb.append(",");
                if (node.right != null) {
                    nextLevelNodes.add(node.right);
                }
                sb.append(node.right == null? "N": node.right.val);
                sb.append(",");
            }
            currentLevelNodes = nextLevelNodes;
            sb.append(";");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) {
            return null;
        }

        String[] levelValues = data.split(";");
        if (levelValues.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(levelValues[0].split(",")[0]));
        List<TreeNode> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(root);

        deserializeBfs(currentLevelNodes, levelValues);

        return root;
    }

    void deserializeBfs(List<TreeNode> currentLevelNodes, String[] levelValues) {
        // {1;2,3;4,N,N,5;N,1}
        for(int i = 1; i < levelValues.length; i++) {
            String[] lastLevelValues = levelValues[i-1].split(",");
            String[] currentLevelValues = levelValues[i].split(",");
            List<TreeNode> nextLevelNodes = new ArrayList<>();
            int counter = 0;
            for (int j = 0; j < lastLevelValues.length; j++) {
                if (!lastLevelValues[j].equals("N")) {
                    TreeNode leftNode = currentLevelValues[2*counter].equals("N")? null: new TreeNode(Integer.valueOf(currentLevelValues[2*counter]));
                    TreeNode rightNode = currentLevelValues[2*counter+1].equals("N")? null: new TreeNode(Integer.valueOf(currentLevelValues[2*counter+1]));
                    currentLevelNodes.get(counter).left = leftNode;
                    currentLevelNodes.get(counter).right = rightNode;
                    counter++;

                    if (leftNode != null) {
                        nextLevelNodes.add(leftNode);
                    }
                    if (rightNode != null) {
                        nextLevelNodes.add(rightNode);
                    }
                }
            }
            currentLevelNodes = nextLevelNodes;
        }
    }

/*
// 使用前序+中序形式进行，但是在出现重复元素的时候有问题。// FIXME: 19/09/2017 等待修复
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> preOrder = new ArrayList();
        List<Integer> inOrder = new ArrayList();
        preOrderTraverse(root, preOrder);
        inOrderTraverse(root, inOrder);
        return getString(preOrder) + ";" + getString(inOrder);
    }

    String getString(List<Integer> path) {
        if(path.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(Integer val: path) {
            sb.append(val);
            sb.append(",");
        }

        return sb.substring(0, sb.length() - 1);
    }

    void preOrderTraverse(TreeNode root,List<Integer> path) {
        if(root == null) {
            return;
        }
        path.add(root.val);
        preOrderTraverse(root.left, path);
        preOrderTraverse(root.right, path);
    }

    void inOrderTraverse(TreeNode root,List<Integer> path) {
        if(root == null) {
            return;
        }

        inOrderTraverse(root.left, path);
        path.add(root.val);
        inOrderTraverse(root.right, path);
    }

    // Decodes your encoded data to tree.
    Map<Integer, Integer> cacheInorder;
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) {
            return null;
        }

        String[] orders = data.split(";");
        if (orders == null || orders.length == 0) {
            return null;
        }

        int[] preOrder = getValArray(orders[0]);
        int[] inOrder = getValArray(orders[1]);
        return buildTree(preOrder, inOrder);

    }

    int[] getValArray(String path) {
        String[] vals = path.split(",");
        int[] values = new int[vals.length];
        for(int i=0; i < values.length;i++) {
            values[i] = Integer.valueOf(vals[i]);
        }

        return values;
    }

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

//        int inMiddle = searchIndex(preorder[preStart]);
        int inMiddle = searchIndex(inorder, inStart, inEnd, preorder[preStart]);
        TreeNode leftTree = buildTree(preorder, preStart+1, preStart + (inMiddle - inStart) + 1, inorder, inStart, inMiddle);
        root.left = leftTree;
        TreeNode rightTree = buildTree(preorder, preStart + (inMiddle - inStart) + 1, preEnd, inorder, inMiddle+1, inEnd);
        root.right = rightTree;

        return root;

    }

    int searchIndex(int[] order, int fromIndex, int toIndex, int key) {
        int index = -1;
        while (fromIndex < toIndex) {
            if (order[toIndex] == key) {
                index = toIndex;
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
*/
    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree tr = new SerializeAndDeserializeBinaryTree();
        String serialize = tr.serialize(tr.build());
        TreeNode deserialize = tr.deserialize(serialize);
        System.out.println(serialize);
    }

    TreeNode build() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        return root;
    }
}
