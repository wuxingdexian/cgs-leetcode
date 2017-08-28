package stack.medium;

import tree.easy.NextGreaterElementI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/next-greater-element-ii/description/" />
 * 503. Next Greater Element II
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

 Example 1:
 Input: [1,2,1]
 Output: [2,-1,2]
 Explanation: The first 1's next greater number is 2;
 The number 2 can't find next greater number;
 The second 1's next greater number needs to search circularly, which is also 2.
 * <p>
 * 0. 本质：
 * 排列、组合、关系（有序对）
 * 1. 建模：
 * next_greater(nums[i])=a[j mod length(nums)], when a[j mod length(nums)] is first greater than it begin from its right to its left.
 * TODO 画个折线图，容易看出
 * （1）从左到右一次遍历，能保证在最高峰左边的点肯定能找到最近的greater较大的数，右边的就不能保证，因为最高峰右边肯定存在某些值大于后续小山峰的。
 * （2）右边不能保证的点，需要依次遍历最高峰左边的点。
 *
 * 2. 算法范式：
 * 3. 算法：
 * 参考{@link NextGreaterElementI}，优先使用栈来表示
 * TODO 为了练习stack，这里使用栈来实现
 * （1）第一遍，和<code>NextGreaterElementI</code>一样，维护descending sequence的stack；
 * （2）当左到右遍历结果，栈的元素大于1，也即最高峰右边还有元素，此时从数组左边遍历到最高峰，依次和栈顶比较，
 *      1）若nums[i]大于栈顶的nums[j]，则有有序对(nums[j], nums[i])。然后栈顶出栈，继续比较
 *      2) 若nums[i]小于栈顶nums[j]，则i+1，使用下一个元素和栈顶比较。
 * （3）重复（2）直到最高峰。也即栈最后只有一个元素（因为不可能有比这个元素更大元素），或判断数组的元素是否和栈顶相等（因为数组的元素唯一，不重复）
 *
 * // FIXME: 28/08/2017 因为允许重复，需要额外处理，使用包装类
 * 4. 数据结构：
 * 5. 改进：TODO 因为只有一个数组，其实可以省略掉map
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 28/08/2017
 * @see
 * @since cgs-leetcode on  28/08/2017
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        Map<String, Integer> orderedPairMap = preProcess(nums);
        return getSolution(nums, orderedPairMap);
    }

    Map<String, Integer> preProcess(int[] nums) {
        Map<String, Integer> orderedPairMap = new HashMap();
        Stack<Pair> descendingPairStack = new Stack();

        for(int i = 0; i < nums.length; i++) {
            while(!descendingPairStack.isEmpty() && descendingPairStack.peek().val < nums[i]) {
                orderedPairMap.put(formateKey(descendingPairStack.pop()), nums[i]);
            }
            descendingPairStack.push(new Pair(nums[i], i));
        }

        for(int i = 0; i < nums.length && descendingPairStack.size() > 1; i++) {
            while(descendingPairStack.peek().val < nums[i]) {
                orderedPairMap.put(formateKey(descendingPairStack.pop()), nums[i]);
            }
        }
        return orderedPairMap;
    }

    int[] getSolution(int[] nums, Map<String, Integer> orderedPair) {
        int[] solution = new int[nums.length];
        for(int i = 0; i < solution.length; i++) {
            solution[i] = orderedPair.getOrDefault(formateKey(nums[i], i), -1);
        }
        return solution;
    }

    String formateKey(Pair pair) {
        return pair.val + "-" + pair.index;
    }
    String formateKey(int val, int index) {
        return val + "-" + index;
    }

    class Pair {
        int val;
        int index;
        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }



    //-----------------------------------improvement--------------------------
    int[] improvement(int[] nums) {
        Stack<Pair> descendingPairStack = new Stack();
        int[] solution = new int[nums.length];
        Arrays.fill(solution, -1);

        for(int i = 0; i < nums.length; i++) {
            while(!descendingPairStack.isEmpty() && descendingPairStack.peek().val < nums[i]) {
                solution[descendingPairStack.pop().index] = nums[i];
            }
            descendingPairStack.push(new Pair(nums[i], i));
        }

        for(int i = 0; i < nums.length && descendingPairStack.size() > 1; i++) {
            while(descendingPairStack.peek().val < nums[i]) {
                solution[descendingPairStack.pop().index] = nums[i];
            }
        }
        return solution;
    }

    public static void main(String[] args) {
        int[] nums = {100,1,11,1,120,111,123,1,-1,-100};
        int[] ints = new NextGreaterElementII().improvement(nums);

        for (int num: ints) {
            System.out.printf(num + ",");
        }
    }
}
