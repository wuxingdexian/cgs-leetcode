package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/combination-sum/description/" />
 * 39. Combination Sum
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [2, 3, 6, 7] and target 7,
 A solution set is:
 [
 [7],
 [2, 2, 3]
 ]
 * <p>
 * 1. 建模：
 * 决策树
 * 设定有序对(numberList，candidate[i到n], T, tmpSum)，其中numberList存放遍历过程从candidate数组中取到的合法number，candidate数组为要取数的数组，i > 0
 * T为target数，tmpSum为numberList中累加和。
 * 设定条件：（1）tmpSum < T；（2）T - tmp > candidate[i]，i为决策过程本次合法candidate数组中的第一个值；
 *
 * // TODO: 15/08/2017 这个应该是背包问题，使用动态规划试试
 * 2. 算法范式：backtracking
 * 3. 算法：深度遍历决策树，代码角度，需要横向看本次执行时候的数据结构中数据的变化。
 * 4. 数据结构：记忆数字数据结构模型是栈。可以使用arrayList执行，速度会快；使用Stack会慢些，因为底层使用的是vector实现
 * 5. 改进：
 * 6. 启发：决策树深度遍历，对于需要遍历的情况，如遍历candidates的指定范围的所有元素，这个时候（1）决策树的图要画清楚；（
 * 2）写for循环时候要横向来看，横向遍历万所有元素时，数据结构的怎么变化的，如这里栈顶的元素被替换，转化到代码操作就是先压栈，然后执行其他逻辑，最后出栈。别忘了出栈按。。。。stackPop(numberStack);
 * 决策树建模，记住：中间节点是决策，叶子节点是结果
 * 7. 伴随问题：int[]转list？
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> solutionList = new ArrayList<List<Integer>>();
        generate(new Stack<Integer>(), candidates, 0, 0, target, solutionList);
        return  solutionList;
    }
    private void generate(Stack<Integer> numberStack, int[] candidates, int index,
                          int target, int tmpSum, List<List<Integer>> solutionList) {
        if(target == tmpSum) {
            solutionList.add(ListNumbers(numberStack));
            return;
        }
        if(tmpSum > target) {
            return;
        }

        for(int i = index; i < candidates.length; i++)  {
            numberStack.push(candidates[i]);
            generate(numberStack, candidates, i, target, tmpSum + candidates[i], solutionList);
            stackPop(numberStack);
        }
    }

    void stackPop(Stack<Integer> numberStack) {
        if (!numberStack.isEmpty()) {
            numberStack.pop();
        }
    }

    List<Integer> ListNumbers(Stack<Integer> numberStack) {
        return new ArrayList<>(numberStack.subList(0, numberStack.size()));
    }


    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> lists = new CombinationSum().combinationSum(candidates, 15);
        System.out.println(lists);
    }
}
