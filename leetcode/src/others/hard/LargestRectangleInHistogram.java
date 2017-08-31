package others.hard;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/largest-rectangle-in-histogram/description/" />
 * 84. Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


 The largest rectangle is shown in the shaded area, which has area = 10 unit.

 For example,
 Given heights = [2,1,5,6,2,3],
 return 10.
 * <p>
 * 0. 本质：子序列
 * 1. 建模：
 * 面积area=bottom * height，两个变量，此时应该固定或减轻一个变量对整体的影响
 * 设定有序对p(a[i], i)，分别对应元素和原数组height的索引；设定分割集cut[]用来分割height为不同的子集。
 *
 * 动态规划：
 * maxArea(i) = max(maxArea(i-1), bottom(i)*height(i))
 * 关键点就在如何找到bottom，预处理，向左向右分别遍历一遍，找到每个高度height(i)的底长。
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）有序对p(a[i], i)根据a[i]升序排序，分割集初始化为空。
 * （2）有序对从最小元素（高）开始计算，从cut[]找到最大的连续区域，此时区域为整个height数组长度，即此时的最大面积为a[i]*length(height)
 * （3）将最小元素（高）的索引i放入分割集cut[]，若存在多个相同值，则把对应的索引都加入分割集。
 * （4）依次遍历剩下元素，找到最大面积。 提示：可以根据area=bottom * height，计算本次要找的连续区域至少为多少长度才能使得面积增大。
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 29/08/2017
 * @see
 * @since cgs-leetcode on  29/08/2017
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Pair[] pairs = getPairs(heights);
        // List<Integer> indexs;
        int maxArea = Integer.MIN_VALUE;
        TreeSet<Integer> cuts = new TreeSet();
        for(int i = 0; i < pairs.length; i++) {
            maxArea = Math.max(maxArea, pairs[i].val * getLagrgerLength(cuts, pairs.length));
            cuts.add(pairs[i].index);
            for(int j = i + 1; j < pairs.length && pairs[i].val == pairs[j].val; j++, i++) {
                cuts.add(pairs[j].index);
            }
        }
        return maxArea;
    }

    // get larger length
    // 这步太耗时间 // FIXME: 29/08/2017
    int getLagrgerLength(TreeSet<Integer> cuts, int heightsLength) {
        if(cuts.size() == 0) {
            return heightsLength;
        }
        int maxLength = cuts.first();

        // can't force to Integer[]
        Object[] indexs = cuts.toArray();

        for(int i = 1; i < indexs.length; i++) {
            maxLength = Math.max((Integer) indexs[i] - (Integer)indexs[i-1] - 1, maxLength);
        }
        maxLength = Math.max(maxLength, heightsLength - (Integer)indexs[cuts.size() - 1] - 1);
        return maxLength;
    }

    // initialize pairs
    Pair[] getPairs(int[] heights) {
        Pair[] pairs = new Pair[heights.length];
        for(int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair(heights[i], i);
        }

        // TODO comparator
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.val - o2.val;
            }
        });

        return pairs;
    }

    class Pair {
        int val;
        int index;
        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }


    //---------------------------------dynamic------------------------------

    public int largestRectangleAreaDP(int[] heights) {
        int[] leftLength = new int[heights.length];
        int[] rightLength = new int[heights.length];
        preProcess(heights, leftLength, rightLength);

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (leftLength[i] + rightLength[i] + 1));
        }

        return maxArea;

    }
    void preProcess(int[] nums, int[] leftLength, int[] rightLength) {
        for(int i = 0; i < nums.length; i++) {
            for (int left = i - 1; left >= 0 && nums[i] <= nums[left]; left--) {
                leftLength[i]++;
            }

            for (int right = i + 1; right < nums.length && nums[right] >= nums[i]; right++) {
                rightLength[i]++;
            }
        }
    }

    //---------------------------------dynamic------------------------------


    //----------------------------------standard--------------------------
    public static int largestRectangleAreaStandard(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;
// TODO: 29/08/2017 ?
            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;
// TODO: 29/08/2017 ?
            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }
    //----------------------------------standard--------------------------

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        int i = new LargestRectangleInHistogram().largestRectangleAreaStandard(heights);
        System.out.println(i);
    }
}
