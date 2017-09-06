package others.hard;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/expression-add-operators/description/" />
 * 282. Expression Add Operators
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []
 * <p>
 * 0. 本质：combinatorics permutation generating
 * 1. 建模：往数字中间插入不同的操作符，即操作符排列，然后根据运算优先级法则运算后看是否得到目标数
 * 设有序对(numStr[1,..,n], target)
 * 2. 算法范式：backtracking
 * 3. 算法：dfs
 * （1）对于有序对(numStr[1,..,n], target)，将字符串划分两部分，第一部分仅包含第一个字符，第二部分包含剩下所有数字字符；得到(numStr[1], operation, numStr[2,..,n])
 * （2）此时对于剩下的numStr[2,..,n]，继续定义有序对(numStr[2,..,n], target operation numStr[1])
 * 重复上述步骤，直到最后一个数字字符成立，则得到一个有效的组合
 * FIXME 在2*3+2 乘法时，要特殊处理。
 * // FIXME: 06/09/2017 在0字符时处理不到10-5
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link Integer#valueOf(int)}valueOf对于char是试用的，会直接把char的acsii整数值拿出来。
 * <code>nums[i] = Integer.valueOf(num.charAt(i));</code>和<code>nums[i] = (int)(num.harAt(i));</code>都不行，
 * 要么（1）使用{@link Integer#valueOf(String)}转为string的参数，要么（2）char减去'0'
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 06/09/2017
 * @see
 * @since cgs-leetcode on  06/09/2017
 */
public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        if(num == null || num.length() == 0) {
            return new ArrayList();
        }
        Deque<String> path = new LinkedList();
        Set<String> solutions = new HashSet<>();
        int[] nums = new int[num.length()];
        for(int i = 0; i < num.length(); i++) {
            nums[i] = Integer.valueOf(num.substring(i, i+1));
//            nums[i] = Integer.valueOf(num.charAt(i));
//            nums[i] = (int)(num.charAt(i));
        }


        dfs(nums,0, target, path, solutions, nums[0]);
        return new ArrayList<>(solutions);
    }


    boolean dfs(int[] nums, int index, int target, Deque<String> path, Set<String> solutions, int preValue) {


        if (index >= nums.length) {
            return 0 == target;
        }

        path.add(String.valueOf(nums[index]));
        path.add("+");
        boolean legalOne = dfs(nums, index + 1, target - preValue, path, solutions, index < nums.length - 1 ? nums[index + 1] : preValue);
        path.removeLast();
        if (legalOne) {
            solutions.add(getSolution(path));
        }

        path.add("-");
        legalOne = dfs(nums, index + 1, preValue - target, path, solutions, index < nums.length-1? nums[index+1]: preValue);
        path.removeLast();
        if (legalOne) {
            solutions.add(getSolution(path));
        }

        path.add("*");
        legalOne = formult(nums, index, target, path, solutions, preValue);
        path.removeLast();
        if (legalOne) {
            solutions.add(getSolution(path));
        }

        path.removeLast(); // 不要忘记这个对称的remove

        return false;
    }

    boolean formult(int[] nums, int index, int target, Deque<String> path, Set<String> solutions, int preValue) {
        if(index < nums.length - 1) {
            int multi = preValue * nums[index + 1];
            return dfs(nums, index + 1, target, path, solutions, multi);
        }
        return false;
    }

    String getSolution(Deque<String> path) {
        StringBuilder sb = new StringBuilder();
//        while(!path.isEmpty()) {
//            sb.append(path.remove()); FIXME 不能remove
//        }
        Iterator<String> iterator = path.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        List<String> list = expressionAddOperators.addOperators("1234", 9);
        System.out.println(list);
    }
}
