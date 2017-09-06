package others.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/largest-number/description/" />
 * 179. Largest Number
 * Given a list of non negative integers, arrange them such that they form the largest number.

 For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

 Note: The result may be very large, so you need to return a string instead of an integer.
 * <p>
 * 0. 本质：combinatorics permutation generating
 * 1. 建模：
 * lexicographer order 逆向排序各个整数，然后最大的放在前面
 *
 * // FIXME: 06/09/2017 上面的建模有个bug，当字母排序时，优先比较字符大小；若两个字符串长度不同，前面比较都相同，则最后长串较大，如121和12比较，121大。
 * 当如果是123而不是121，则没问题。
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 06/09/2017
 * @see
 * @since cgs-leetcode on  06/09/2017
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        List<String> numList = new ArrayList();
        for(int num: nums) {
            numList.add(String.valueOf(num));
        }

        // TODO 从二叉比较树（决策树）角度去思考
        Collections.sort(numList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String num: numList) {
            sb.append(num);
        }
        // 开头为0的情况
        return sb.indexOf("0") == 0? "0": sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {45,421,42,423,4,43};
        String s = new LargestNumber().largestNumber(nums);
        System.out.println(s);
    }
}
