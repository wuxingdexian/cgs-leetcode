package array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/pascals-triangle/description/" />
 * 118. Pascal's Triangle
 * <p>
 * 0. 本质：function sequence
 * 1. 建模：recurrence relation
 * C(n,i) = C(n-1,i) + C(n-1,i-1)
 * C(n,0) = C(n,n) = 1
 * 2. 算法范式：
 * 3. 算法：
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
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> solutions = new ArrayList();
        for(int i = 0; i < numRows; i++) {
            List<Integer> subList = new ArrayList();
            solutions.add(subList);
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    subList.add(1);
                } else {
                    subList.add(solutions.get(i - 1).get(j - 1) + solutions.get(i - 1).get(j));
                }
            }
        }
        return solutions;
    }
}
