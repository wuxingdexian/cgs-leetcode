package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/beautiful-arrangement/description/" />
 * 526. Beautiful Arrangement
 Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 ? i ? N) in this array:

 The number at the ith position is divisible by i.
 i is divisible by the number at the ith position.
 Now given N, how many beautiful arrangements can you construct?

 Example 1:
 Input: 2
 Output: 2
 Explanation:

 The first beautiful arrangement is [1, 2]:

 Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

 Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

 The second beautiful arrangement is [2, 1]:

 Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

 Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 Note:
 N is a positive integer and will not exceed 15.
 * <p>
 * 1. 建模：两个集合的有序对(stack_i[], number_i[])，第i个数时。
 *
 * 建立决策树模型
 * 2. 算法范式：backtracking
 * 3. 算法：程序中stack对应stack_i，j对应number_i
 * 4. 数据结构：辅助数组记录结果
 * 5. 改进：
 * 6. 启发：这里符合两个条件中的一个，在一个if中判断，不用分开为两个
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class BeautifulArrangement {
    public int countArrangement(int N) {

        boolean[] flag = new boolean[N + 1];

        List<List<Integer>> solutions = new ArrayList<>();
        generate(flag, N, new ArrayList<>(), solutions);
        System.out.println(solutions);
        return solutions.size();
    }

    private void generate(boolean[] flag, int N, List<Integer> stack, List<List<Integer>> solutions) {
        if (stack.size() == N) {
            solutions.add(new ArrayList<>(stack));
            return;
        }
        for (int j = 1; j < flag.length; j++) {
            if (!flag[j]) {
                if ((stack.size() + 1) % j == 0 || j % (stack.size() + 1) == 0) {
                    flag[j] = !flag[j];
                    stack.add(j);
                    generate(flag, N, stack, solutions);
                    stack.remove(stack.size() - 1);
                    flag[j] = !flag[j];
                }
            }
        }

    }

    public static void main(String[] args) {
        int i = new BeautifulArrangement().countArrangement(3);
        System.out.println(i);
    }
}
