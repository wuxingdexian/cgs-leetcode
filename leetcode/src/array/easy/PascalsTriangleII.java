package array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/pascals-triangle-ii/description/" />
 * 119. Pascal's Triangle II
 *Given an index k, return the kth row of the Pascal's triangle.

 For example, given k = 3,
 Return [1,3,3,1].

 Note:
 Could you optimize your algorithm to use only O(k) extra space?
 * <p>
 * 1. 建模：recurrence relation，公式C(n,k) = C(n-1, k) + C(n-1, k-1)
 * 2. 算法范式：dynamic programming
 * 3. 算法：
 * 4. 数据结构：两个数组，遍历时，第二个数组要依赖于第一个数组的范围
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * （1）TODO: 10/08/2017 自定义了一个swap函数，用来交换两个int[]数组，结果并没有成功？
 *
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/08/2017
 * @see
 * @since DiscreteMathematics on  10/08/2017
 */
public class PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {

        int[] frontRow = new int[rowIndex + 1];
        int[] currentRow = new int[rowIndex + 1];

        for(int j = 0; j <= rowIndex; j++) {
            int[] tmp;
            for(int i = 0; i < j + 1; i++) {
                if(i == 0 || i == j) {
                    currentRow[i] = 1;
                } else {
                    currentRow[i] = frontRow[i - 1] + frontRow[i];
                }
            }
            tmp = frontRow;
            frontRow = currentRow;
            currentRow = tmp;
        }
        currentRow = frontRow;

        List<Integer> numberList = new ArrayList();
        for(int number: currentRow) {
            numberList.add(number);
        }
        return numberList;
    }

    public static void main(String[] args) {
        List<Integer> row = new PascalsTriangleII().getRow(3);
        System.out.println(row);

    }
}
