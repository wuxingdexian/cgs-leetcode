package others.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/top-k-frequent-elements/description/" />
 * 347. Top K Frequent Elements
 * Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 * <p>
 * 0. 本质：序列
 * 1. 建模：序列转有序对(value, counter)
 * TreeMap
 * 2. 算法范式：
 * 3. 算法：
 * 拿到所有有序对，存入TreeMap
 * TreeMap逆向排序，拿出k
 * 4. 数据结构：TreeMap
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> valCounterMap = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            valCounterMap.put(nums[i], valCounterMap.getOrDefault(nums[i], 0) + 1);
        }

        Pair[] pairs = new Pair[valCounterMap.size()];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry: valCounterMap.entrySet()) {
            pairs[i++] = new Pair(entry.getKey(), entry.getValue());
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.counter - o1.counter;
            }
        });
        List<Integer> solutions = new ArrayList();
        for(int j = 0; j < k && j < pairs.length; j++) {
            solutions.add(pairs[j].val);
        }
        return solutions;
    }

    class Pair {
        int val;
        int counter;
        Pair(int val, int counter) {
            this.val = val;
            this.counter = counter;
        }
    }

    // TODO 使用数组，在nums的最大数不大的情况使用
//    public List<Integer> topKFrequentUsingArray(int[] nums, int k) {
//        Arrays.sort(nums);
//        int[] newNums = new int[nums[nums.length - 1] + 1];
//
//        for(int i = 0; i < nums.length; i++) {
//            newNums[nums[i]]++;
//        }
//        List<Pair> pairList = new ArrayList<>();
//        for(int i = 0; i < nums.length; i++) {
//            pairList.add(new Pair(nums[i], newNums[nums[i]]));// FIXME 这里加进去会有问题
//        }
//        Collections.sort(pairList, new Comparator<Pair>() {
//            @Override
//            public int compare(Pair o1, Pair o2) {
//                return o2.counter - o1.counter;
//            }
//        });
//
//        List<Integer> solutons = new ArrayList<>();
//        for(int i = pairList.size() - 1; i >= 0 && pairList.size() - 1 - i < k; i--) {
//            solutons.add(pairList.get(i).val);
//        }
//        return solutons;
//    }
}
