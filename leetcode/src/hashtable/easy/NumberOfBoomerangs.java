package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/number-of-boomerangs/description/" />
 * 447. Number of Boomerangs
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

 Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

 Example:
 Input:
 [[0,0],[1,0],[2,0]]

 Output:
 2

 Explanation:
 The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

 * <p>
 * 0. 本质：relation FIXME 还有排序
 * 1. 建模：because not need to find the specific tuple, so define order pair Map(point(i), Map(distance, count)),
 * point i has a distance from a point x, find another point y has the same distance with i.
 * 2. 算法范式：
 * 3. 算法：
 * (1) traversal every point, calculate its distance with other points, check  whether there have been other points have the distance.
 * (2) when calculate the distance of two points, save it as value with two points as different keys.
 * 4. 数据结构：map
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * TODO map操作次数太多也会这么慢。。。。
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class NumberOfBoomerangs {

    //-----------------------------------time limit exceeded---------------------------------
    // 主要思路：两个for循环，计算一次，然后拿到以这个点为主的距离的点共有多少个，最后将每个点的每个距离的个数计算排列数，累加起来得到最后的答案。
    // FIXME 可能是因为执行了很多次map操作导致时间超时。TODO 那就代码精简，执行每个点，就只保存该点为中心的距离，不相对保存其他值。
    public int numberOfBoomerangs(int[][] points) {
        if(points == null || points.length == 0) {
            return 0;
        }

        Map<String, Map<Long, Integer>> point2distanceMap = new HashMap();
        for(int i = 0; i < points.length; i++) {
            for(int j = i + 1; j < points.length; j++) {
                long distance = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0])
                        + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                Map<Long, Integer> distanceMap = point2distanceMap.get(points[i][0] + "" + points[i][1]);

                if(distanceMap == null) {
                    distanceMap = new HashMap();
                }
                if(!distanceMap.containsKey(distance)) {
                    distanceMap.put(distance, 1);
                } else {
                    distanceMap.put(distance, distanceMap.get(distance) + 1);
                }
                point2distanceMap.put(points[i][0] + "" + points[i][1], distanceMap);


                Map<Long, Integer> distanceMap2 = point2distanceMap.get(points[j][0] + "" + points[j][1]);
                if(distanceMap2 == null) {
                    distanceMap2 = new HashMap();
                }
                if(!distanceMap2.containsKey(distance)) {
                    distanceMap2.put(distance, 1);
                } else {
                    distanceMap2.put(distance, distanceMap2.get(distance) + 1);
                }
                point2distanceMap.put(points[j][0] + "" + points[j][1], distanceMap2);

            }
        }
        return getTotalCount(point2distanceMap);
    }

    int getTotalCount(Map<String, Map<Long, Integer>> point2distanceMap) {
        int counter = 0;
        for (Map.Entry<String, Map<Long, Integer>> entry: point2distanceMap.entrySet()) {
            for (Map.Entry<Long, Integer> distanceEntry: entry.getValue().entrySet()) {
                if(distanceEntry.getValue() > 1) {
                    counter += calculate(distanceEntry.getValue());
                }
            }
        }
        return counter;
    }

    // C(n, 2) * 2 = P(n, 2) 排列
    int calculate(int count) {
        return count * (count - 1);
    }
    //-----------------------------------time limit exceeded---------------------------------
    // improvement
    public int numberOfBoomerangsImprovement(int[][] points) {
        if(points == null || points.length == 0) {
            return 0;
        }

        int totalNum = 0;
        // 放在这里速度回快很多
        Map<Long, Integer> distanceMap = new HashMap<>();
        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points.length; j++) {
                long distance = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0])
                        + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);

                if(!distanceMap.containsKey(distance)) {
                    distanceMap.put(distance, 1);
                } else {
                    // put操作返回V，这里可以合并操作。。
                    distanceMap.put(distance, distanceMap.get(distance) + 1);
                }
            }
            totalNum += getTotalPermutationCount(distanceMap);
            distanceMap.clear();
        }
        return totalNum;
    }

    int getTotalPermutationCount(Map<Long, Integer> distanceMap) {
        int counter = 0;
        for (Map.Entry<Long, Integer> distanceEntry : distanceMap.entrySet()) {
            counter += calculate(distanceEntry.getValue());
        }
        return counter;
    }



    public static void main(String[] args) {
        int[][] points = {{0,0},{1,0},{2,0}};
        int i = new NumberOfBoomerangs().numberOfBoomerangsImprovement(points);
        System.out.println(i);
    }
}
