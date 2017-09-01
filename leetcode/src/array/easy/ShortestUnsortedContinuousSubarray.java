package array.easy;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/" />
 * 581. Shortest Unsorted Continuous Subarray
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

 You need to find the shortest such subarray and output its length.

 Example 1:
 Input: [2, 6, 4, 8, 10, 9, 15]
 Output: 5
 Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 Note:
 Then length of the input array is in range [1, 10,000].
 The input array may contain duplicates, so ascending order here means <=.
 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 算法一：
 * （1）从前向后找到最长的非递减序列；（2）从后往前找到最长的非递减序列；
 * （3）然后得到中间的乱序序列，从乱序序列中找最大值和最小值；
 * （4）从前面最长非递减序列找到乱序最小值该存在的位置i；
 * （5）从后面最长非递减序列找到乱序最大值的位置j；
 * （6）j-i
 *
 * 算法二：排序
 *
 * 算法三：单调栈
 * 	1. 给定两个序列，序列1有序，序列2无序，找序列2的最小值在序列1的位置？
 *（1）序列1正向遍历，构建单调递增栈；（2）序列2和栈顶不断比较，只要比栈顶小，就出栈。
 * 2. 给定两个序列，序列1有序，序列2无序，找序列2的最大值在序列1的位置？
 *（1）序列2逆向遍历，构建单调递减栈；（2）序列2和栈顶不断比较，只要比栈顶大，就出栈。
 *
 * 算法四：序列单调性判断
 * 完全排好序的序列：（1）从左往右得到单调递增序列；（2）从右往左得到单调递减序列；
 * 根据这两个性质，分别从左到右和右到左遍历，找到破坏该性质的那个最远的下标。即得到答案。
 * 666，下面摘自网上的代码在一个for里面同时进行了左到右和右到左的遍历，代码很简洁，TODO 学习了
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 01/09/2017
 * @see
 * @since cgs-leetcode on  01/09/2017
 */
public class ShortestUnsortedContinuousSubarray {

    //---------------------------使用算法一，还有bug TODO ------------------------
    public int findUnsortedSubarray(int[] nums) {
        int index1 = 1, index2 = nums.length - 2;
        for(; index1 < nums.length; index1++) {
            if(nums[index1-1] > nums[index1]) {
                break;
            }
        }

        for(; index2 >=0; index2--) {
            if(nums[index2+1] < nums[index2]) {
                break;
            }
        }
        if(index2 == -1 || index1 == nums.length) {
            return 0;
        }

        int maxInMiddle = Integer.MIN_VALUE, minInMiddle = Integer.MAX_VALUE;
        if (index1 > index2) {
            int tmp = index1;
            index1 = index2;
            index2 = tmp;
        }
        for(int i = index1; i <= index2; i++) {
            maxInMiddle = Math.max(maxInMiddle, nums[i]);
            minInMiddle = Math.min(minInMiddle, nums[i]);
        }
        index1--;
        for(;index1 >=0; index1--) {
            if(minInMiddle >= nums[index1]) {
                break;
            }
        }
        index2++;
        for(;index2 < nums.length; index2++) {
            if(maxInMiddle <= nums[index2]) {
                break;
            }
        }
        return index2 - index1 - 1;
    }
    //---------------------------使用算法一，还有bug------------------------


    //---------------------------使用算法三，TODO 锻炼栈的使用------------------------
    public int findUnsortedSubarrayStack(int[] nums) {
        int left = nums.length - 1;
        int right = 0;
        Stack<Integer/*index*/> positionStack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!positionStack.isEmpty() && nums[positionStack.peek()] > nums[i]) {
                left = Math.min(positionStack.pop(), left);
            }
            positionStack.push(i);
        }

        positionStack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!positionStack.isEmpty() && nums[positionStack.peek()] < nums[i]) {
                right = Math.max(right, positionStack.pop());
            }
            positionStack.push(i);
        }

        return right - left > 0? right - left + 1: 0;
    }

    //---------------------------使用算法三，锻炼栈的使用------------------------

    //--------------------------算法四，摘自leetcode的discuss部分---------------
    public int findUnsortedSubarrayStandard(int[] nums) {
        int i = 0, j = -1, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        // 把两次for循环融合到一个for内完成。666 TODO 学习！！！
        for (int l = 0, r = nums.length - 1; r >= 0; l++, r--) {
            max = Math.max(max, nums[l]);
            if (nums[l] != max) j = l;

            min = Math.min(min, nums[r]);
            if (nums[r] != min) i = r;
        }

        return (j - i + 1);
    }
    //--------------------------算法四，摘自leetcode的discuss部分---------------
    public static void main(String[] args) {
//        int[] nums = {2,6,4,8,10,9,15};
        int[] nums = {2,3,2};
        int unsortedSubarray = new ShortestUnsortedContinuousSubarray().findUnsortedSubarrayStack(nums);
        System.out.println(unsortedSubarray);
    }
}
