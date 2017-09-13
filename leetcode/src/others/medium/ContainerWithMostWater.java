package others.medium;

import others.hard.TrappingRainWater;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/container-with-most-water/description/" />
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.
 * <p>
 * 0. 本质：序列-》关系
 * 1. 建模：
 * 相邻的两个元素（line）能容纳的最多的水取决于最短那个。
 * TODO 这个题设是只能相邻的两个元素么？shit  从测试角度看，不是的。 那么这题就和{@link others.hard.LargestRectangleInHistogram}有点类似了
 *
 * 更正：// FIXME: 11/09/2017 从题设看，不是很清楚是那种情况，下面分两种情况讨论并建模
 * （1）如果题目的意思是选择其中的两个节点，并且保留其他的节点，那么选第一个和最后一个能达到最大容积，只需要计算所有的容积即可；// TODO: 11/09/2017  那这情况就类似"42. Trapping Rain Water"了
 * 对于每个line，
 * 设l(i)为左边大于等于height(i)的line，如果没有，则为自身
 * 设r(i)为右边大于等于height(i)的line，如果没有，则为自身
 * 则最后的最大容积等于累加所有节点的min(l(i), r(i))，同时去掉中间重复的
 *
 * （2）如果题目的意思是选择其中的两个节点，然后删除其他节点，那么就需要选择其中两个节点了。看LeetCode的测试结果和答案，应该是这个情形；
 * 序列转换到有序对ordered-pair的关系relation，则有C(n,2)中情况，bruce-force则O(n*n)复杂度。
 * recurrence relation
 * most_water = max(most_water,(j-i)*min(height[i],height[j])), where j >= i  这个复杂度还是O(n^2)
 * TODO 对比下面的recurrence relation，提高recurrence relation建模能力。
 *
 * 参考：https://discuss.leetcode.com/topic/50763/from-dp-to-greedy-o-n-with-explanation-easy-way-to-see-this-problem 这里的DP解恨清晰
 * S(0..n) = max{v(0, n), S(1...n), S(0...n-1)},O(n^2)复杂度，通过比较height[0]和height[n]的大小，可以得到max(S(1...n), S(0...n-1)),从而减低复杂度
 *            / max{v(i, j), S(i...j-1)};  height(i) >= height(j)
 * S(i..j) = |
 *            \ max{v(i, j), S(i+1...j)};  height(i) < height(j)
 * TODO 对比： 该模型和{@link TrappingRainWater}的模型“对于每个节点i，v(i)=min(height(leftmost(i)), height(rightmost(i))) - height(i), where leftmost(i)<=i<=rightmost(i)， leftmost(i)为i的最左高峰，rightmost(i)为i的最右高峰”相比
 *      1）该题模型可以从整体考虑，所有S(0..n) = max{v(0, n), S(1...n), S(0...n-1)}的模型比上面（1）更好。
 *      2）{@link TrappingRainWater}这题目前没看出可以从整体来考虑的recurrence relation解，但是有针对每个节点i的解v(i)=min(height(leftmost(i)), height(rightmost(i))) - height(i)
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）dynamic programming
 *
 * （2）用单调递减栈，分别从左到右和从右到左获取每个节点的l(i)和r(i)
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 这题和{@link others.hard.LargestRectangleInHistogram}类似，但是最大长方形取决于区域内最小的那个节点，而本题最大容积取决于左右最小那个节点。
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/09/2017
 * @see
 * @since cgs-leetcode on  11/09/2017
 */
public class ContainerWithMostWater {

    //-----------------------------针对情形1的代码------------------------------
    // 单调递减栈实现
    public int maxArea1(int[] height) {

        if(height == null || height.length < 2) {
            return 0;
        }

        Stack<Integer> stack = new Stack();
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        for(int i = 0; i < height.length; i++) {
            // stack存的是下标，所以height[stack.peek()]
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                stack.pop();
            }
            // 不断pop，直到拿到左边最高峰peak
            while(!stack.isEmpty() && stack.size() > 1) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                left[i] = i;
            } else {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        for(int i = height.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                stack.pop();
            }
            // 不断pop，直到拿到右边最高峰peak
            while(!stack.isEmpty() && stack.size() > 1) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                right[i] = i;
            } else {
                right[i] = stack.peek();
            }
            stack.push(i);
        }

        int solution = 0;
        for(int i = 0; i < height.length; i++) {
            // (right[i] - left[i]) 去掉下标相等的情况
            solution += (right[i] - left[i]) * Math.min(height[left[i]], height[right[i]]);
            i = right[i];
        }

        return solution;
    }
    //-----------------------------针对情形1的代码------------------------------

    //-----------------------------针对情形2的代码------------------------------
    public int maxArea(int[] height) {
        if(height == null || height.length < 2) {
            return 0;
        }

        int maxVolumn = 0;
        int i = 0, j = height.length - 1;
        while(i < j) {
            maxVolumn = Math.max(maxVolumn, (j-i) * Math.min(height[i], height[j]));
            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxVolumn;
    }
    //-----------------------------针对情形2的代码------------------------------

    public static void main(String[] args) {
        int[] height = {2,3,10,5,7,8,9};
        int i = new ContainerWithMostWater().maxArea(height);
        System.out.println(i);
    }
}
