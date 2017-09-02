package array.easy;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/non-decreasing-array/description/" />
 * 665. Non-decreasing Array
 * Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

 We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

 Example 1:
 Input: [4,2,3]
 Output: True
 Explanation: You could modify the first
 4
 to
 1
 to get a non-decreasing array.
 Example 2:
 Input: [4,2,1]
 Output: False
 Explanation: You can't get a non-decreasing array by modify at most one element.
 Note: The n belongs to [1, 10,000].

 * <p>
 * 0. 本质：combinatorics permutation
 * 1. 建模：
 * 关键点，找到转折点，得到第一个极大值点和随后的一个点(a[i],a[i+1]), where a[i] > a[i+1]，要么a[i]变为a[i+1]或a[i+1]变为a[i]，只要有一个变化能使得有序性保持，则成立
 * 建模一
 * // FIXME: 02/09/2017 从折线图的角度分析有问题，还有很多边界情况没考虑清楚。。。有BUG
 * 找到第一个波峰，然后看（1）波峰后following element 第一个数，是否非递减；
 * （2）看the sub sequence begin with the the following element to the the is non-decreasing?
 *
 * 建模二
 * // FIXME: 02/09/2017 这个有BUG：顶多只有一个数改变，那么可以只要改变数超过一个就失败；这个数是影响整个序列有序性的关键元素。使用单调非递减栈来解决
 * (a[i],a[i+1])中两个点，只要有一个的改变能保证单调性持续，就完成任务。
 *
 * 2. 算法范式：
 * 3. 算法：
 *
 * 建模二算法：
 * 两个栈，分别用来保留(a[i],a[i+1])对中的两个点，只要两个的变化都超过1，那就不行；
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class NonDecreasingArray {
    // -----------------stack------------------
    public boolean checkPossibilityStack(int[] nums) {
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        int counter1 = 0;
        int counter2 = 0;
        for(int i = 0; i < nums.length; i++) {
            while(!stack1.isEmpty() && stack1.peek() > nums[i]) {
                stack1.pop();
                counter1++;
            }
            stack1.push(nums[i]);

            if(!stack2.isEmpty() && stack2.peek() > nums[i]) {
                counter2++;
            } else {
                stack2.push(nums[i]);
            }
            if(counter1 > 1 && counter2 > 1) {
                return false;
            }

        }
        return true;
    }

    // ---------------------------前后两个指针---------------------------
    public boolean checkPossibility(int[] nums) {
        int headIndex = -1;
        int tailIndex = nums.length;
        for(int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                headIndex = i - 1;
                break;
            }
        }
        for(int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                tailIndex = i + 1;
                break;
            }
        }
        if(headIndex == -1 && tailIndex == nums.length) {
            return true;
        }
        if(tailIndex - headIndex != 1) {
            return false;
        }

        return headIndex == 0? true: (tailIndex == nums.length - 1? true:
                (nums[headIndex] <= nums[tailIndex + 1] || nums[tailIndex] >= nums[headIndex - 1]));
    }
    // ---------------------------前后两个指针---------------------------
    public static void main(String[] args) {
//        int[] nums = {1,2,3};
//        int[] nums = {4,2,3};
        int[] nums = {1,2,3,4,5};
//        int[] nums = {4,2,1};
        boolean b = new NonDecreasingArray().checkPossibility(nums);
        System.out.println(b);
    }
}
