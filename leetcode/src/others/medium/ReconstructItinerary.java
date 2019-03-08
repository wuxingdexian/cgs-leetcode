package others.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reconstruct-itinerary/description/" />
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

 Note:

 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 All airports are represented by three capital letters (IATA code).
 You may assume all tickets form at least one valid itinerary.
 Example 1:

 Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 Example 2:

 Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 But it is larger in lexical order.
 * <p>
 * 0. 本质：有序对，有向图
 * 1. 建模：
 * 题目要求的是对有向边的遍历，遍历完所有的边，两个顶点间一个方向会存在多个边的情况。
 * 区分于对顶点的遍历
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
 * @author changgan on 23/02/2019
 * @see
 * @since cgs-leetcode on  23/02/2019
 */
public class ReconstructItinerary {
    List<List<String>> solutionList = new LinkedList<>();
    Map<String, List<String>> graph = new HashMap<>();
    Map<String, Integer> pathMapNum = new HashMap<>();
    int pathCount = 0;

    public List<String> findItinerary(String[][] tickets) {

        if (tickets == null) return null;

        for(String[] ticket: tickets) {
            List<String> vals = graph.getOrDefault(ticket[0], new LinkedList<>());
            graph.put(ticket[0], vals);
            vals.add(ticket[1]);

            int pathNum = pathMapNum.getOrDefault(getPathKey(ticket[0], ticket[1]), 0);
            pathMapNum.put(getPathKey(ticket[0], ticket[1]), pathNum + 1);
        }

        Stack<String> nodeCache = new Stack<>();
        nodeCache.add("JFK");

//        dfs("JFK", nodeCache, tickets.length);
        dfs(new HashMap<>(), pathMapNum, "JFK", nodeCache);

        getSolution();

        return solutionList.get(0);
    }

    private void dfs(Map<String, Integer> visitedPath, Map<String, Integer> noVisitedPath, String from, Stack<String> nodePath) {
        if(noVisitedPath.isEmpty()) {
            solutionList.add(new ArrayList<>(nodePath));
            return;
        }

        List<String> toList = graph.get(from);
        if(toList == null) {
            return;
        }

        for(String to: toList) {
            String path = getPathKey(from, to);
            if(noVisitedPath.containsKey(path) && noVisitedPath.get(path) > 0) {
                nodePath.push(to);

                Integer noVisitedNum = noVisitedPath.get(path);
                if(noVisitedNum <= 1) noVisitedPath.remove(path);
                else noVisitedPath.put(path, noVisitedNum - 1);

                int visitedNum = visitedPath.getOrDefault(path, 0);
                visitedPath.put(path, visitedNum + 1);

                dfs(visitedPath, noVisitedPath, to, nodePath);

                visitedPath.put(path, visitedPath.get(path) - 1);

                noVisitedNum = noVisitedPath.getOrDefault(path, 0);
                noVisitedPath.put(path, noVisitedNum + 1);
                nodePath.pop();
            }
        }
    }

    private String getPathKey(String from, String to) {
        return from + to;
    }

    private void dfs(String from, Stack<String> nodeCache, int pathNum) {
        if(pathCount == pathNum) {
            solutionList.add(new ArrayList<>(nodeCache));
            return;
        }

        List<String> toList = graph.get(from);
        if(null == toList) return;

        for(String to: toList) {
            String path = getPathKey(from, to);
            if(pathMapNum.containsKey(path) && pathMapNum.get(path) <= 0) continue;

            pathCount++;
            nodeCache.add(to);
            pathMapNum.put(path, pathMapNum.get(path) - 1);

            dfs(to, nodeCache, pathNum);

            pathMapNum.put(path, pathMapNum.get(path) + 1);
            nodeCache.remove(to);
            pathCount--;
        }
    }

    private void getSolution() {
        solutionList.sort((List<String> o1, List<String> o2) -> {
            for(int i = 0; i < o1.size(); i++) {
                if(o1.get(i).equals(o2.get(i))) continue;
                return o1.get(i).compareTo(o2.get(i));
            }
            return 0;
        });

    }

    public static void main(String[] args) {

//        String[][] test = {{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}};
//        String[][] test = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        String[][] test = {{"AXA","EZE"},{"EZE","AUA"},{"ADL","JFK"},{"ADL","TIA"},{"AUA","AXA"},{"EZE","TIA"},{"EZE","TIA"},{"AXA","EZE"},{"EZE","ADL"},{"ANU","EZE"},{"TIA","EZE"},{"JFK","ADL"},{"AUA","JFK"},{"JFK","EZE"},{"EZE","ANU"},{"ADL","AUA"},{"ANU","AXA"},{"AXA","ADL"},{"AUA","JFK"},{"EZE","ADL"},{"ANU","TIA"},{"AUA","JFK"},{"TIA","JFK"},{"EZE","AUA"},{"AXA","EZE"},{"AUA","ANU"},{"ADL","AXA"},{"EZE","ADL"},{"AUA","ANU"},{"AXA","EZE"},{"TIA","AUA"},{"AXA","EZE"},{"AUA","SYD"},{"ADL","JFK"},{"EZE","AUA"},{"ADL","ANU"},{"AUA","TIA"},{"ADL","EZE"},{"TIA","JFK"},{"AXA","ANU"},{"JFK","AXA"},{"JFK","ADL"},{"ADL","EZE"},{"AXA","TIA"},{"JFK","AUA"},{"ADL","EZE"},{"JFK","ADL"},{"ADL","AXA"},{"TIA","AUA"},{"AXA","JFK"},{"ADL","AUA"},{"TIA","JFK"},{"JFK","ADL"},{"JFK","ADL"},{"ANU","AXA"},{"TIA","AXA"},{"EZE","JFK"},{"EZE","AXA"},{"ADL","TIA"},{"JFK","AUA"},{"TIA","EZE"},{"EZE","ADL"},{"JFK","ANU"},{"TIA","AUA"},{"EZE","ADL"},{"ADL","JFK"},{"ANU","AXA"},{"AUA","AXA"},{"ANU","EZE"},{"ADL","AXA"},{"ANU","AXA"},{"TIA","ADL"},{"JFK","ADL"},{"JFK","TIA"},{"AUA","ADL"},{"AUA","TIA"},{"TIA","JFK"},{"EZE","JFK"},{"AUA","ADL"},{"ADL","AUA"},{"EZE","ANU"},{"ADL","ANU"},{"AUA","AXA"},{"AXA","TIA"},{"AXA","TIA"},{"ADL","AXA"},{"EZE","AXA"},{"AXA","JFK"},{"JFK","AUA"},{"ANU","ADL"},{"AXA","TIA"},{"ANU","AUA"},{"JFK","EZE"},{"AXA","ADL"},{"TIA","EZE"},{"JFK","AXA"},{"AXA","ADL"},{"EZE","AUA"},{"AXA","ANU"},{"ADL","EZE"},{"AUA","EZE"}};
//        String[][] test = {{"a", "b"}, {"b", "c"}, {"c", "a"}, {"c", "d"}, {"b", "a"}, {"a", "c"}};
        List<String> list = new ReconstructItinerary().findItinerary(test);

        System.out.println(list);
    }



}
