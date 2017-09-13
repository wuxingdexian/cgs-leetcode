package others.hard;

import java.net.HttpCookie;
import java.util.Arrays;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/trapping-rain-water/description/" />
 * 42. Trapping Rain Water
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * <p>
 * 0. 本质：序列-》关系
 * 1. 建模：
 * {@link others.medium.ContainerWithMostWater}和情形1类似
 *
 * （1）一般解
 * 对于每个节点i，v(i)=min(height(leftmost(i)), height(rightmost(i))) - height(i), where leftmost(i)<=i<=rightmost(i)， leftmost(i)为i的最左高峰，rightmost(i)为i的最右高峰
 * 则v(0,n-1)=累加所有的v(i)，
 *
 *
 * （2）recurrence relation DP解
 * 上述的一般解，在求leftmost(i)和rightmost(i)时，存在重复子问题，是可以用recurrence relation来表示的。
 * leftmost(i) = max(i, leftmost(i-1))
 * rightmost(i) = max(i, rightmost(i+1))
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）一般解
 *  找每个节点的leftmost(i)和rightmost(i)，用栈单调递减栈完成。
 *
 * （2）DP解
 *      1）找每个节点的leftmost(i)和rightmost(i)，存在重复子问题，除了单调栈，还可以用dp来解，从前往后和从后往前两次遍历即可
 *      2）步骤1）的解需要两个数组，可以优化为只需要O(1)空间复杂度即可。直接前后分别遍历
 *
 * 4. 数据结构：
 * 数组+栈
 *
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/09/2017
 * @see
 * @since cgs-leetcode on  11/09/2017
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if(height == null || height.length < 2) {
            return 0;
        }

//        int[] left = getLeftMostUsingStack(height);
//        int[] right = getRightMostUsingStack(height);
        int[] left = getLeftMostUsingDP(height);
        int[] right = getRightMostUsingDP(height);

        int solution = 0;
        for(int i = 0; i < height.length; i++) {
            // (right[i] - left[i]) 去掉下标相等的情况
            solution += (i == left[i] || i == right[i])? 0: Math.min(height[left[i]], height[right[i]]) - height[i];
        }

        return solution;
    }

    // -----------用栈的形式获取leftmost 和 rightmost----------------
    int[] getLeftMostUsingStack(int[] height) {
        int[] left = new int[height.length];
        Stack<Integer> stack = new Stack();
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
        return left;
    }

    int[] getRightMostUsingStack(int[] height) {
        int[] right = new int[height.length];
        Stack<Integer> stack = new Stack();
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
        return right;
    }
    // -----------用栈的形式获取leftmost 和 rightmost----------------

    // -----------用DP的形式获取leftmost 和 rightmost----------------
    int[] getLeftMostUsingDP(int[] height) {
        int[] left = new int[height.length];
        int leftMost = 0;
        for(int i = 0; i < height.length; i++) {
            leftMost = height[leftMost] < height[i]? i: leftMost; // 记得更新leftmost
            left[i] = leftMost;
        }
        return left;
    }

    int[] getRightMostUsingDP(int[] height) {
        int[] right = new int[height.length];
        int rightMost = height.length - 1;
        for(int i = height.length - 1; i >= 0; i--) {
            rightMost = height[rightMost] < height[i]? i: rightMost; // 记得更新rightmost
            right[i] = rightMost;
        }
        return right;
    }
    // -----------用DP的形式获取leftmost 和 rightmost----------------

    // -----------用DP的形式解，但优化空间----------------
    public int trapDPImprovement(int[] height) {
        int leftmost = 0,rightmost = 0, left = 0, right = height.length - 1, volume = 0;
        while (left <= right){
            // 外层的height[left] <= height[right] 已经将DP解的一部分提取到此处
            if(height[left] <= height[right]) {
                if (height[left] > height[leftmost]) {
                    leftmost = left;
                } else {
                    // height[left] <= height[right]已经判断了，所以这里不在判断min(height[leftmost], height[rightmost])
                    volume += height[leftmost] - height[left];
                }
                left++;
            } else {
                if (height[right] > height[rightmost]) {
                    rightmost = right;
                } else {
                    volume += height[rightmost] - height[right];
                }
                right--;
            }
        }
        return volume;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = new TrappingRainWater().trapDPImprovement(nums);
        System.out.println(trap);
    }
}
