package breadthfirstsearch.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/course-schedule/description/" />
 * 207. Course Schedule
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 For example:

 2, [[1,0]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

 2, [[1,0],[0,1]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * 1. 建模：
 * 图
 * 判断有向图是否有环等价于判断有向图是否能进行拓扑排序
 * 设定节点分为in_degree_0_nodes[]、in_degree_non_0_nodes[]两个集合，入度分别为0和非0。
 *
 * （1）breath-first search：
 * 逐渐将in_degree_0_nodes中入度为0的节点删除，同时更新该节点指向的节点们的入度减1，若这些节点的入度变为了0，则加入in_degree_0_nodes集合，
 * 并从in_degree_non_0_nodes集合删除。
 * 不断执行上述步骤，直到in_degree_0_nodes为空，此时看in_degree_non_0_nodes集合是否也为空，若不为空，则说明存在环。
 * // TODO: 17/08/2017  上面的步骤执行完可以看出环就是in_degree_non_0_nodes集合的点组成。若题设改为求其中的环，那此方法也正好可以完成这个目标。
 * （2）depth-first search：
 * 从入度为0的in_degree_0_nodes开始遍历，分别从这些点出发，深度遍历，同时记录走过的路径，若遍历过程的节点是已经走过的节点，那么存在环。
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * 在对ArrayList、LinkedList进行遍历时不能添加和删除元素
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 17/08/2017
 * @see
 * @since cgs-leetcode on  17/08/2017
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List[] adjacencyList = new List[numCourses];

        int[][] degree = new int[numCourses][2];
        for(int i = 0; i < prerequisites.length; i++) {
            int fromNode = prerequisites[i][0];
            int toNode = prerequisites[i][1];

            // in_degree
            degree[toNode][0] += 1;
            // out_degree
            degree[fromNode][1] += 1;

            adjacencyList[fromNode] = adjacencyList[fromNode] != null? adjacencyList[fromNode]: new LinkedList();
            adjacencyList[fromNode].add(toNode);
        }

        LinkedList<Integer> zeroInDegree = new LinkedList();
        LinkedList<Integer> nonZeroInDegree = new LinkedList();
        for (int i = 0; i < degree.length; i++) {
            if(degree[i][0] == 0 && degree[i][1] != 0) {
                // 有out degree no in degree
                zeroInDegree.add(i);
            } else if(degree[i][0] != 0) {
                // 有in degree 就够了
                nonZeroInDegree.add(i);
            }
        }

        // bfs
        while(zeroInDegree.size() > 0) {
            LinkedList<Integer> newZeroInDegree = new LinkedList();
            for (Integer node: zeroInDegree) {
                // 上面adjacencyList没有给出连接的点没初始化
                if (adjacencyList[node] != null) {
                    for (Object toNode : adjacencyList[node]) {
                        degree[(int) toNode][0] -= 1;
                        if (degree[(int) toNode][0] == 0) {
                            newZeroInDegree.addFirst((Integer) toNode);
                            nonZeroInDegree.remove(toNode);
                        }
                    }
                }
            }
            zeroInDegree = newZeroInDegree;
        }

        return nonZeroInDegree.size() == 0;
    }

    public static void main(String[] args) {
        int[][] ints = {{1,0}};
        boolean b = new CourseSchedule().canFinish(2, ints);
        System.out.println(b);
    }
}
