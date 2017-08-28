package tree.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/next-greater-element-i/description/" />
 * 496. Next Greater Element I
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

 The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

 Example 1:
 Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 Output: [-1,3,-1]
 Explanation:
 For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 For number 1 in the first array, the next greater number for it in the second array is 3.
 For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 Example 2:
 Input: nums1 = [2,4], nums2 = [1,2,3,4].
 Output: [3,-1]
 Explanation:
 For number 2 in the first array, the next greater number for it in the second array is 3.
 For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 Note:
 All elements in nums1 and nums2 are unique.
 The length of both nums1 and nums2 would not exceed 1000.
 * <p>
 * 0. 本质：
 * 组合，关系(有序对)
 * 1. 建模：
 * FIXME 分析错误 min_greater(a[i])=min(greater(a[i])[])
 *
 *
 * min_greater(a[i]) = the first a[j]>a[i] in its right.
 *
 * 2. 算法范式：
 * 3. 算法：
 * //----------FIXME 分析错误--------------
 * 排序
 * （1）求difference = nums2-nums1，得到差集；
 * （2）然后求difference排序，
 *      1）可以直接排序，利用二分法来搜索
 *      2）可以构建大（小）顶堆，然后二分搜索
 * //----------FIXME 分析错误--------------
 *
 * 算法一：
 * 维持栈底到栈顶的递减顺序，当遍历所有的nums2，和栈顶比较，若栈顶nums[i]比nums[j]小，则得到有序对(nums2[i],nums2[j])，
 * 继续比较num2[i-1]和nums2[j]，直到num2[j]<栈顶num2[i-x]元素，然后num2[j]压栈
 *
 * 算法二：
 * 对于nums2，双for遍历，找到大于nums2[i]右边的第一个值，然后内for循环break；循环执行这个操作。在极端情况，复杂度会为O(n*n)，即递减序列时。
 *
 * 4. 数据结构：
 * stack：store descending sequence
 * hashMap：store ordered-pair (nums[i],nums[j])
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 28/08/2017
 * @see
 * @since cgs-leetcode on  28/08/2017
 */
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> orderedPair = preProcess(nums2);

        return getSolution(nums1, orderedPair);
    }

    private Map<Integer, Integer> preProcess(int[] nums2) {
        Stack<Integer> descendingSeqence = new Stack();
        Map<Integer, Integer> orderedPair = new HashMap();
        for(int num: nums2) {
            while(!descendingSeqence.isEmpty() && descendingSeqence.peek() < num) {
                orderedPair.put(descendingSeqence.pop(), num);
            }
            descendingSeqence.push(num);
        }
        return orderedPair;
    }

    private int[] getSolution(int[] nums1, Map<Integer, Integer> orderedPair) {
        int[] solution = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            solution[i] = null != orderedPair.get(nums1[i])? orderedPair.get(nums1[i]): -1;
        }
        return solution;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        int[] findNums = {1,2,3};

        int[] ints = new NextGreaterElementI().nextGreaterElement(findNums, nums);
        for (int num: ints) {
            System.out.print(num + ",");
        }
    }
}
