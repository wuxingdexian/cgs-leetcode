package others.hard;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/cut-off-trees-for-golf-event/description/" />
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

 0 represents the obstacle can't be reached.
 1 represents the ground can be walked through.
 The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

 You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

 You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

 Example 1:
 Input:
 [
 [1,2,3],
 [0,0,4],
 [7,6,5]
 ]
 Output: 6
 Example 2:
 Input:
 [
 [1,2,3],
 [0,0,0],
 [7,6,5]
 ]
 Output: -1
 Example 3:
 Input:
 [
 [2,3,4],
 [0,0,5],
 [8,7,6]
 ]
 Output: 6
 Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 Hint: size of the given matrix will not exceed 50x50.
 * <p>
 * 0. 本质：图模型
 * 1. 建模：
 * 审题注意几点：
 * （1）必须从(0,0)起点开始走，只要单元格的值不为0就可以通行，
 * （2）只有遇到当前全局最小高度的树，才能坎，否则只能同行，除非遇到0不能走
 * （3）全局最小意为排序关系，可以实现全局排序，按照这个顺序来遍历砍树
 * （4）从最小值到第二小值，这是一个遍历过程，一般想到BFS或DFS（回溯法）
 * （5）BFS，参考《算法导论》描述，需要队列辅助记录和遍历当前深度的所有节点；需要标记记录当前节点是否已经被访问过；需要记录深度
 * （6）由于每次从最小值到第二小值都是一次遍历过程，注意数据的重置reset
 * （7）距离的值，存在每个节点Node内，可能更好些
 * （7）注意图的邻接矩阵和邻接表两种方式，不同方式如何遍历
 * todo 使用DFS实现一次
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 26/08/2018
 * @see
 * @since cgs-leetcode on  26/08/2018
 */
public class CutOffTreesForGolfEvent {

    class Node {
        Integer val;
        int row;
        int column;
        List<Node> connectedNodes;
        int depth = 0;

        // 0: white, 1: gray; 2 black
        int color = 0;

        public Node(Integer val, int row, int column) {
            this.val = val;
            this.row = row;
            this.column = column;
            this.connectedNodes = new LinkedList<>();
        }
        public void addConnectedNode(Node node) {
            connectedNodes.add(node);
        }
    }

    Node[][] nodes;

    List<Node> candidateNodeList = new LinkedList<>();

    public int cutOffTree(List<List<Integer>> forest) {
        if (null == forest || forest.size() == 0 || null == forest.get(0) || 0 == forest.get(0).size()) {
            return -1;
        }

        traverse(forest);

        if (candidateNodeList.isEmpty()) {
            return -1;
        }

        // 初始化到第一个节点
        int minPathLength = traverseBFS(nodes[0][0], candidateNodeList.get(0));
        if (minPathLength == -1) {
            return -1;
        }
        resetColorAndDepth();

        for (int i = 1; i < candidateNodeList.size(); i++) {
            int pathLength = traverseBFS(candidateNodeList.get(i - 1), candidateNodeList.get(i));
            if (pathLength == -1) {
                return -1;
            }
            minPathLength = minPathLength + pathLength;

            resetColorAndDepth();
        }
        return minPathLength;
    }

    void resetColorAndDepth() {
        for (Node node: candidateNodeList) {
            node.depth = 0;
            node.color = 0;
        }
//        for (int r = 0; r < nodes.length; r++) {
//            for (int c = 0; c < nodes[0].length; c++) {
//                nodes[r][c].color = 0;
//                nodes[r][c].depth = 0;
//            }
//        }
    }

    /**
     * 这步骤映射为图的遍历，找两个顶点的最短距离，相邻顶点要么相连且边长为1，要么不相连（当为相邻节点值为0或大于目标节点值时）
     * 再变相的简化理解为找两个顶点的路径，该路径经过的点最少
     * 最短路径还是BFS比DFS更好些，DFS可能需要遍历完所有可能路径
     * 这里BFS搜索，参考《算法导论》
     * @param sourceNode
     * @param targetNode
     * @return
     */
    int traverseBFS(Node sourceNode, Node targetNode) {
        if (sourceNode.equals(targetNode)) {
            return 0;
        }

        LinkedList<Node> parentNodeList = new LinkedList<>();
        parentNodeList.add(sourceNode);
        sourceNode.color = 1;

        while (!parentNodeList.isEmpty()) {
            LinkedList<Node> childNodeList = new LinkedList<>();

            while (!parentNodeList.isEmpty()) {
                Node node = parentNodeList.pollFirst();
                // 表示已经访问过
                node.color = 2;
                for (Node neighborNode: node.connectedNodes) {
                    if (neighborNode.color != 0) {
                        continue;
                    }
                    neighborNode.color = 1;
                    neighborNode.depth = node.depth + 1;
                    if (targetNode.equals(neighborNode)) {
                        return neighborNode.depth;
                    }
                    // 邻接节点的树高度大于目标树高度，不代表不能过去，可过，但如果不是当前最小，不能砍
//                    if (neighborNode.val > targetNode.val) {
//                        continue;
//                    }
                    childNodeList.add(neighborNode);
                }
            }
            parentNodeList = childNodeList;
        }
        return -1;
    }


    private void traverse(List<List<Integer>> forest) {

        nodes = new Node[forest.size()][forest.get(0).size()];
        for (int r = 0; r < forest.size(); r++) {
            for (int c = 0; c < forest.get(0).size(); c++) {
                nodes[r][c] = new Node(forest.get(r).get(c).intValue(), r, c);
            }
        }

        for (int r = 0; r < nodes.length; r++) {
            for (int c = 0; c < nodes[0].length; c++) {
                if (0 == nodes[r][c].val) {
                    continue;
                }
                if (c + 1 < nodes[0].length && nodes[r][c+1].val > 0) {
                    nodes[r][c].addConnectedNode(nodes[r][c+1]);
                }
                if (r + 1 < nodes.length && nodes[r+1][c].val > 0) {
                    nodes[r][c].addConnectedNode(nodes[r+1][c]);
                }
                if (c - 1 >= 0 && nodes[r][c-1].val > 0) {
                    nodes[r][c].addConnectedNode(nodes[r][c-1]);
                }
                if (r - 1 >= 0 && nodes[r-1][c].val > 0) {
                    nodes[r][c].addConnectedNode(nodes[r-1][c]);
                }
                candidateNodeList.add(nodes[r][c]);
            }
        }

        candidateNodeList.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val.compareTo(o2.val);
            }
        });
    }


    // TODO: 27/08/2018 DFS 可能用到
//        if (!forest.get(0).get(0).equals(candidateNodeList.get(0).val)) {
//            return -1;
//        }
//
//        int minPathSum = 0;
//        for (int i = 1; i < candidateNodeList.size(); i++) {
//            AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
//            Stack<Integer> stack = new Stack<>();
//            stack.push(candidateNodeList.get(i - 1).val);
//            traverse(stack, min, candidateNodeList.get(i - 1).row, candidateNodeList.get(i - 1).column, forest, candidateNodeList.get(i));
//            if (min.intValue() == Integer.MAX_VALUE) {
//                return -1;
//            }
//            minPathSum = minPathSum + min.intValue();
//        }
//
//        return minPathSum;

    /**
     * 这步骤映射为图的遍历，找两个顶点的最短距离，相邻顶点要么相连且边长为1，要么不相连（当为相邻节点值为0或大于目标节点值时）
     * 再变相的简化理解为找两个顶点的路径，该路径经过的点最少
     * @param stack
     * @param min
     * @param r
     * @param c
     * @param forest
     * @param targetNode
     */
    private void traverse(Stack<Integer> stack, AtomicInteger min, int r, int c, List<List<Integer>> forest,  Node targetNode) {
        if (targetNode.row == r && targetNode.column == c) {
            min.set(Math.min(stack.size(), min.intValue()));
            return;
        }
        if (stack.isEmpty()) {
            return;
        }

        if (stack.size() >= min.intValue()) {
            return;
        }

        // 向上走
        if (r > 0 && forest.get(r - 1).get(c) != 0 && forest.get(r - 1).get(c) <= targetNode.val) {
            if (!stack.contains(forest.get(r - 1).get(c))) {
                stack.push(forest.get(r - 1).get(c));
                traverse(stack, min, r - 1, c, forest, targetNode);
                stack.pop();
                return;
            } else {
                stack.pop();
            }
        }
        // 向右走
        else if (c < forest.get(0).size() - 1 && forest.get(r).get(c + 1) != 0 && forest.get(r).get(c + 1) <= targetNode.val) {
            if (!stack.contains(forest.get(r).get(c + 1))) {
                stack.push(forest.get(r).get(c + 1));
                traverse(stack, min, r, c + 1, forest, targetNode);
                stack.pop();
                return;
            } else {
                stack.pop();
            }
        }
        // 向下走
        else if (r < forest.size() - 1 && forest.get(r + 1).get(c) != 0 && forest.get(r + 1).get(c) <= targetNode.val) {
            if (!stack.contains(forest.get(r + 1).get(c))) {
                stack.push(forest.get(r + 1).get(c));
                traverse(stack, min, r + 1, c, forest, targetNode);
                stack.pop();
                return;
            }stack.pop();
        }
        // 向左走
        else if (c > 0 && forest.get(r).get(c - 1) != 0 && forest.get(r).get(c - 1) <= targetNode.val) {
            if (!stack.contains(forest.get(r).get(c - 1))) {
                stack.push(forest.get(r).get(c - 1));
                traverse(stack, min, r, c - 1, forest, targetNode);
                stack.pop();
                return;
            } else {
                stack.pop();
            }
        }
        return;
    }


    public static void main(String[] args) {
        List<List<Integer>> testList = new ArrayList<>();
//        testList.add(Arrays.asList(1, 2, 3));
//        testList.add(Arrays.asList(0, 0, 4));
//        testList.add(Arrays.asList(7, 6, 5));
        testList.add(Arrays.asList(54581641,64080174,24346381,69107959));
        testList.add(Arrays.asList(86374198,61363882,68783324,79706116));
        testList.add(Arrays.asList(668150,92178815,89819108,94701471));
        testList.add(Arrays.asList(83920491,22724204,46281641,47531096));
        testList.add(Arrays.asList(89078499,18904913,25462145,60813308));

//        testList.add(Arrays.asList(63750247,40643210,95516857,89928134,66334829,58741187,76532780,45104329));
//        testList.add(Arrays.asList(3219401,97566322,9135413,75944198,93735601,33923288,50116695,83660397));
//        testList.add(Arrays.asList(64460750,53045740,31903386,78155821,90848739,38769489,99349027,85982891));
//        testList.add(Arrays.asList(30628785,51077683,70534803,67460877,91077770,74197235,5696362,91459886));
//        testList.add(Arrays.asList(56105195,82479378,45937951,52817583,2768114,43329099,28189138,21418604));

        int i = new CutOffTreesForGolfEvent().cutOffTree(testList);
        System.out.println(i);
    }

}
