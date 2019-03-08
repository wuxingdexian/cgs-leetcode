package graph.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/evaluate-division/description/" />
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].

 The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

 According to the example above:

 equations = [ ["a", "b"], ["b", "c"] ],
 values = [2.0, 3.0],
 queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * <p>
 * 0. 本质：关系
 * 1. 建模：图
 * 2. 算法范式：
 * （1）dfs，找两点的path
 *
 * （2）DP+dfs，使用邻接矩阵，把遍历路劲有序对的值都打印出来
 *      使用栈保存路径，得到栈底和栈顶 pair 的有序对值
 *      使用set保存访问过的节点
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/10/2018
 * @see
 * @since cgs-leetcode on  20/10/2018
 */
public class EvaluateDivision {

    // 假设都是小写字母标记节点
    double[][] matrix = new double[26][26];
    Set<Integer> existingNodeSet = new HashSet<>();



    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        for(int i=0; i<26; i++) {
            matrix[i][i] = -1;
        }

        for(int i=0; i<equations.length; i++) {
            matrix[equations[i][0].charAt(0)-'a'][equations[i][1].charAt(0)-'a'] = values[i];
            if (values[i] != 0) {
                matrix[equations[i][1].charAt(0) - 'a'][equations[i][0].charAt(0) - 'a'] = 1 / values[i];
            } else {
                matrix[equations[i][1].charAt(0) - 'a'][equations[i][0].charAt(0) - 'a'] = -1;
            }

            matrix[equations[i][0].charAt(0)-'a'][equations[i][0].charAt(0)-'a'] = 1.0;
            matrix[equations[i][1].charAt(0)-'a'][equations[i][1].charAt(0)-'a'] = 1.0;

            existingNodeSet.add(equations[i][0].charAt(0)-'a');
            existingNodeSet.add(equations[i][1].charAt(0)-'a');
        }

        double[] solutions = new double[queries.length];
        for(int i=0; i<queries.length; i++) {
            Set<Integer> visitedNodeSet = new HashSet<>();
            Stack<Integer> path = new Stack<>();
            path.push(queries[i][0].charAt(0) - 'a');
            visitedNodeSet.add(queries[i][0].charAt(0) - 'a');
            dfs(queries[i], path, visitedNodeSet);
            solutions[i] = matrix[queries[i][0].charAt(0) - 'a'][queries[i][1].charAt(0) - 'a'];
        }
        return solutions;

    }

    private void dfs(String[] query, Stack<Integer> path, Set<Integer> visitedNodeSet) {
        double v = matrix[query[0].charAt(0) - 'a'][query[1].charAt(0) - 'a'];
        if(v != 0 && v != -1) {
            return;
        }
        if(!existingNodeSet.contains(query[0].charAt(0) - 'a') || !existingNodeSet.contains(query[1].charAt(0) - 'a')) {
            return;
        }

        for(int i=0; i<26; i++) {
            if(path.peek().equals(i) || matrix[path.peek()][i] == -1 || visitedNodeSet.contains(i)) {
                continue;
            }

            // 增加记录
            double latestVal = matrix[query[0].charAt(0) - 'a'][path.peek()];
            matrix[query[0].charAt(0) - 'a'][i] = latestVal / matrix[path.peek()][i];
            matrix[i][query[0].charAt(0) - 'a'] = 1 / matrix[query[0].charAt(0) - 'a'][i];

            path.push(i);
            visitedNodeSet.add(i);

            dfs(query, path, visitedNodeSet);
            path.pop();
            visitedNodeSet.remove(i);
        }
    }

    public static void main(String[] args) {
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};

        EvaluateDivision evaluateDivision = new EvaluateDivision();
        double[] doubles = evaluateDivision.calcEquation(equations, values, queries);
        System.out.println(doubles);
    }
}
