package graph.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/redundant-connection/description/" />
 * n this problem, a tree is an undirected graph that is connected and has no cycles.

 The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

 The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

 Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

 Example 1:
 Input: [[1,2], [1,3], [2,3]]
 Output: [2,3]
 Explanation: The given undirected graph will be like this:
   1
  / \
 2 - 3
 Example 2:
 Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 Output: [1,4]
 Explanation: The given undirected graph will be like this:
 5 - 1 - 2
     |   |
     4 - 3
 Note:
 The size of the input 2D-array will be between 3 and 1000.
 Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 * <p>
 * 0. 本质：
 * 1. 建模：图
 * 树种多了一条边，则存在一个环，
 * （1）找到这个环；（2）然后根据环，找出最后一个出现的边
 *
 * 找到这个环的使用DFS：
 * 使用Set和Stack，Set记录访问过哪些节点；Stack记录访问路径
 * 注意：只有至少三个节点才能构成环，所有两个节点和其边不能看做环。则栈顶元素能做此判断
 *
 *
 * 2. 算法范式：回溯法 -》 DFS
 * 3. 算法：
 * 4. 数据结构：
 * set、stack、adjacency list
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 16/10/2018
 * @see
 * @since cgs-leetcode on  16/10/2018
 */
public class RedundantConnection {

    Integer startNodeInPathStack = null;
    Stack<Integer> path = new Stack<>();
    Set<Integer> visitedNodeSet = new HashSet<>();
    Node[] nodes;
    public int[] findRedundantConnection(int[][] edges) {

        nodes = new Node[edges.length];
        for (int i = 0; i < edges.length; i++) {
            nodes[i] = new Node(i);
        }

        // 构建邻接表
        for(int[] edge: edges) {
            nodes[edge[0] - 1].adjacencyList.add(edge[1] - 1);
            nodes[edge[1] - 1].adjacencyList.add(edge[0] - 1);
        }

        path.push(0);
        visitedNodeSet.add(0);

        getCycle();

        // 找到栈头部都不在环内的节点
        while(!path.isEmpty()) {
            if(path.pop().equals(startNodeInPathStack)) {
                break;
            }
        }
        // 不在环内的都剔除
        while(!path.isEmpty()) {
            visitedNodeSet.remove(path.pop());
        }

        // 后往前找第一个
        for(int i = edges.length-1; i >=0; i--) {
            if(visitedNodeSet.contains(edges[i][0]-1) && visitedNodeSet.contains(edges[i][1]-1)) {
                return edges[i];
            }
        }

        return null;
    }

    private void getCycle() {
        if(null != startNodeInPathStack) {
            return;
        }

        List<Integer> adjacencyList = nodes[path.peek()].adjacencyList;
        for(Integer adjacentNode: adjacencyList) {
            if(visitedNodeSet.contains(adjacentNode)) {
                if (path.get(path.size() - 2).equals(adjacentNode)) {
                    continue;
                } else {
                    startNodeInPathStack = adjacentNode;
                    return;
                }
            } else {
                path.push(adjacentNode);
                visitedNodeSet.add(adjacentNode);
                getCycle();
                if(null != startNodeInPathStack) {
                    // 这里注意退出条件
                    return;
                }
                path.pop();
                visitedNodeSet.remove(adjacentNode);
            }
        }
    }

    private class Node {
        Integer index;
        List<Integer> adjacencyList = new ArrayList<>();

        public Node(Integer index) {
            this.index = index;
        }
    }

    public static void main(String[] args) {
        RedundantConnection re = new RedundantConnection();

        int[][] in = {{1,2},{2,3},{3,4},{1,4},{1,5}};
        int[] redundantConnection = re.findRedundantConnection(in);
        System.out.println(redundantConnection);
    }
}
