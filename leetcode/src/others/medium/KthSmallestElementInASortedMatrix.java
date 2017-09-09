package others.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/" />
 * 378. Kth Smallest Element in a Sorted Matrix
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ n2.
 * <p>
 * 0. 本质：序列
 * 1. 建模：M x M , 对于未知(i,j)，左上角肯定比该元素小，右上角和左下角不能确认。
 * 找到N x N >=k的最小未知(i,j)，然后左上角+右上角+左下角的元素比较
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * ---------------------------------------------------------------
 * // TODO: 07/09/2017 https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/ 看下二叉搜索
 * http://www.cnblogs.com/grandyang/p/5727892.html
 * “上面的解法还可以进一步优化到O(nlgX)，其中X为最大值和最小值的差值，我们并不用对每一行都做二分搜索法，我们注意到每列也是有序的，我们可以利用这个性质，从数组的左下角开始查找，如果比目标值小，我们就向右移一位，而且我们知道当前列的当前位置的上面所有的数字都小于目标值，那么cnt += i+1，反之则向上移一位，这样我们也能算出cnt的值。”
 * 二叉搜索+排序性质+数组下标
 * 通过排序性质+数组下标，可以直接知道这列这行前面有多少个元素小于该元素。。666
 * ---------------------------------------------------------------
 *
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {

        if(k == 1) {
            return matrix[0][0];
        }
        if(k == matrix.length*matrix.length) {
            return matrix[matrix.length-1][matrix.length-1];
        }

        // get position
        int position = 0;
        for(int i = 0 ;i <= matrix.length; i++) {
            if(i * i >=k) {
                position = i;
                break;
            }
        }

        // save possible elements
        // int[] nums = new int[matrix[0]*(positon-1) + (matrix.length - position)*(matrix.length - position) + 1];
        List<Integer> numList = new ArrayList();
        for(int i = 0; i < position; i++) {
            for(int j = 0; j < matrix.length; j++) {
                numList.add(matrix[i][j]);
            }
        }
        for(int i = position; i < matrix.length; i++) {
            for(int j = 0; j < position; j++) {
                numList.add(matrix[i][j]);
            }
        }
        if (position < matrix.length) {
            numList.add(matrix[position][position]);
        }

        Collections.sort(numList);

        return numList.get(k - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {{ 1,  5,  9},{10, 11, 13},{12, 13, 15}};
        int i = new KthSmallestElementInASortedMatrix().kthSmallest(matrix, 8);
        System.out.println(i);
    }
}
