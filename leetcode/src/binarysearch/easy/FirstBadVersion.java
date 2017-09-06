package binarysearch.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/tag/binary-search/" />
 * 278. First Bad Version
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * 0. 本质：序列 集合
 * 1. 建模：好的连续，坏的也连续，将整个序列划分为两个子集
 * 2. 算法范式：
 * 3. 算法：二叉查找
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：“// int middle = (start + end) / 2;”两个整数相加会溢出
 * “>>”右移的 优先级比加好低，加括号
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {

        int start = 1, end = n;
        // binary search
        while(start < end) {
            // int middle = (start + end) / 2;
            int middle = start + ((end - start) >> 1);
            if(isBadVersion(middle)) {
                end = middle;
            } else {
                start = middle + 1;
            }

        }
        return start;
    }

    // test
    private boolean isBadVersion(int middle) {
        return false;
    }
}
