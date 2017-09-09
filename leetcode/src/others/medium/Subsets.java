package others.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/subsets/description/" />
 * 78. Subsets
 * Given a set of distinct integers, nums, return all possible subsets.

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,3], a solution is:

 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 * <p>
 * 0. 本质：集合-》combinatoric->generating
 * 1. 建模：找出所有的子集，一个集合有n个元素，那么就有2的n次方个子集
 * 2. 算法范式：backtracking
 * 3. 算法：dfs+bit string
 * 4. 数据结构：数组、stack
 * 5. 改进：在建模找子集过程，bit string有很多好处。但是这里dfs下来，通过栈和计数来实现，不需要bit string的额外判断
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/09/2017
 * @see
 * @since cgs-leetcode on  08/09/2017
 */
public class Subsets {

    // -----------------------------version 1--------------------------
    /*
    List<List<Integer>> allSolutions = new ArrayList();
    public List<List<Integer>> subsets(int[] nums) {
        for(int i = 0; i <= nums.length; i++) {
            Stack<Integer> pathStack = new Stack();
            boolean[] flag = new boolean[nums.length]; // bit string
            generate(nums, 0, i, pathStack, flag);
        }
        return allSolutions;

    }

    void generate(int[] nums, int index, int elementNum, Stack<Integer> pathStack, boolean[] flag) {
        if(elementNum <= 0) {
            allSolutions.add(new ArrayList(pathStack));
            return;
        }
        for(int i = index; i < nums.length; i++) {
            if(!flag[i]) {
                flag[i] = true;
                pathStack.push(nums[i]);
                generate(nums, i + 1, elementNum - 1, pathStack, flag);
                pathStack.pop();
                flag[i] = false;
            }
        }
    }
    */
    // -----------------------------version 1--------------------------

    List<List<Integer>> allSolutions = new ArrayList();
    public List<List<Integer>> subsets(int[] nums) {
        for(int i = 0; i <= nums.length; i++) {
            Stack<Integer> pathStack = new Stack();
            generate(nums, 0, i, pathStack);
        }
        return allSolutions;

    }

    void generate(int[] nums, int index, int elementNum, Stack<Integer> pathStack) {
        if (elementNum <= 0) {
            allSolutions.add(new ArrayList(pathStack));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            pathStack.push(nums[i]);
            generate(nums, i + 1, elementNum - 1, pathStack);
            pathStack.pop();
        }
    }
}
