package breadthfirstsearch.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/minimum-height-trees/description/" />
 * 310. Minimum Height Trees
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

 Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 Example 1:

 Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

 0
 |
 1
 / \
 2   3
 return [1]

 Example 2:

 Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

 0  1  2
 \ | /
 3
 |
 4
 |
 5
 return [3, 4]

 Note:

 (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

 (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * <p>
 * 1. 建模：
 * 关键词：degree=1说明为叶子节点
 *
 * 审题：“For a undirected graph with tree characteristics,” 题设给出了这句话，也就是说，这个图有n个节点，有n-1个边，
 * 此情况下，https://leetcode.com/problems/minimum-height-trees/discuss/ “Share some thoughts”的解释对的。
 * 设定有序对(internal_nodes[] , leave_nodes[])，当internal_nodes只有一个或两个时即得到答案。
 * 因为是连通图，且没有回路（n个节点，n-1条边），删除一个叶子节点即删除一条边，最后剩下要么一个节点，要么两个节点+一条边
 *
 * -----------------------扩展-----------------------
 * 若这个图有n个节点，有大于等于n条边，那此时上面的解答就不成立，需要变化。
 * 给出思路：因为有大于等于n条边，则必定有有一个环，根节点肯定有环中的其中一个或多个符合条件。
 * 此时根据上面给出的思路，稍作变化：
 * 初始化所有节点的level为1。对于叶子节点（即只有条边），删除叶子节点，则其相连内部节点的level为max(currentLevel, level + 1)。
 * 依次执行上述步骤，直到没有入度为0的节点，此时找到环，再循环遍历（适合广度优先遍历）环的不同节点都环其他节点(length+level)，找到使得(length+level)最小的那个节点，可能存在多个相同。为中点middle
 * -----------------------扩展-----------------------
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：图的edge lists形式(int[][]数组表示)，可以转换到adjacency list邻接链表形式，较容易处理
 * 5. 改进：
 * 6. 启发：
 * （1）breath-first search可以想象为嵌套的多个圆周，一环套一环，此时适合从外网内广度遍历，不是从内部开始。
 * 因为内部节点可能很多，找到的不一定是最内圈的节点。但是外围很容易找到，因为这些节点的degree只为1，即只有一条边。
 * 适用于图搜索
 * （2）也可以想象为波纹，从内部某个节点一层层逐渐往外走，适合在固定某个节点起开始广度遍历。
 * 适合树和图搜索
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 17/08/2017
 * @see
 * @since cgs-leetcode on  17/08/2017
 */
public class MinimumHeightTrees {
    // 有序对(internal_nodes[] , leave_nodes[])，当internal_nodes只有一个或两个时即得到答案
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n < 1 || null == edges || edges.length == 0) {
            List<Integer> emptyList = new ArrayList<>();
            emptyList.add(0);
            return emptyList;
        }

        LinkedList[] adjacencyList = new LinkedList[n];
        for(int i = 0; i < edges.length; i++) {
            int index1 = edges[i][0];
            int index2 = edges[i][1];
            adjacencyList[index1] = adjacencyList[index1] != null? adjacencyList[index1]: new LinkedList<Integer>();
            adjacencyList[index2] = adjacencyList[index2] != null? adjacencyList[index2]: new LinkedList<Integer>();

            adjacencyList[index1].add(index2);
            adjacencyList[index2].add(index1);
        }

        List<Integer> leaves = new LinkedList();
        for(int i = 0; i < adjacencyList.length; i++) {
            if(adjacencyList[i].size() == 1) {
                leaves.add(i);
            }
        }

        // bfs
        while(n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new LinkedList();
            for(Integer leave: leaves) {
                int adjacencyNode = (int) adjacencyList[leave].get(0);
                adjacencyList[adjacencyNode].removeFirstOccurrence(leave);
                if(adjacencyList[adjacencyNode].size() == 1) {
                    newLeaves.add(adjacencyNode);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,0},{1,2},{1,3}};
        List<Integer> minHeightTrees = new MinimumHeightTrees().findMinHeightTrees(4, nums);
        System.out.println(minHeightTrees);
    }
}
