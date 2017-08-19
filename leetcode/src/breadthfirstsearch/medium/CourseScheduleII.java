package breadthfirstsearch.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/course-schedule-ii/description/" />
 * 210. Course Schedule II
 * <p>
 * 1. 建模：
 * {@link CourseSchedule}的BFS分析
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 17/08/2017
 * @see
 * @since cgs-leetcode on  17/08/2017
 */
public class CourseScheduleII {

    // TODO: 17/08/2017 下面代码真是一坨，需要拆分才是。。。。
    public int[] findOrder(int numCourses, int[][] prerequisites) {

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
            if(degree[i][0] == 0) {
                // 有out degree no in degree
                zeroInDegree.add(i);
            } else if(degree[i][0] != 0) {
                // 有in degree 就够了
                nonZeroInDegree.add(i);
            }
        }

        int[] topologicalSort = new int[zeroInDegree.size() + nonZeroInDegree.size()];
        // bfs
        List<List<Integer>> solutions = new LinkedList();
        while(zeroInDegree.size() > 0) {
            solutions.add(zeroInDegree);
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
        // 存在环的情况
        if(nonZeroInDegree.size() != 0) {
            return new int[0];
        }

        int i = 0;
        for (int j = solutions.size() - 1; j >=0; j--) {
            for (Object node: solutions.get(j)) {
                topologicalSort[i++] = (int) node;
            }
        }
        return topologicalSort;
    }


}
