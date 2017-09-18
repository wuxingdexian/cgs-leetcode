package others.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/gray-code/description/" />
 * 89. Gray Code
 * The gray code is a binary numeral system where two successive values differ in only one bit.

 Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

 For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

 00 - 0
 01 - 1
 11 - 3
 10 - 2
 Note:
 For a given n, a gray code sequence is not uniquely defined.

 For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

 For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * <p>
 * 0. 本质：combinatorics-》permutation-》generating
 * 1. 建模：
 *
 * （1）递归生成码表
 * https://baike.baidu.com/item/%E6%A0%BC%E9%9B%B7%E7%A0%81/6510858?fr=aladdin
 * 1位格雷码有两个码字
 * (n+1)位格雷码中的前2n个码字等于n位格雷码的码字，按顺序书写，加前缀0
 * (n+1)位格雷码中的后2n个码字等于n位格雷码的码字，按逆序书写，加前缀1[4]
 * n+1位格雷码的集合 = n位格雷码集合(顺序)加前缀0 + n位格雷码集合(逆序)加前缀1
 *
 * （2）二进制码转格雷码
 * http://blog.csdn.net/jingfengvae/article/details/51691124
 * 二进制数：B(n-1)B(n-2)...B(0)
 * 格雷码：G(n-1)G(n-2)...G(0)
 * 对应公式：G(i)=B(i+1)^B(i)
 *
 * 2. 算法范式：backtracking
 *
 * 3. 算法：
 * dfs // FIXME: 18/09/2017 存在漏元素的情况
 * （1）从0开始，变化范围为0~n-1，每次基于上一个元素，从右到左变动一个bit，如果是1则变0，如果是0则变1；
 * （2）变化过程需要判断即将变为的数是否已经存在，若跳过该bit变化
 * （3）依次执行上述步骤，列表个数为2^n
 *
 * bit操作，参考https://discuss.leetcode.com/topic/40401/simplest-fastest-easiest-solution
 *
 * 4. 数据结构：int，bit操作
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/09/2017
 * @see
 * @since cgs-leetcode on  18/09/2017
 */
public class GrayCode {

    // FIXME: 18/09/2017 按照上面模型提示，修改
    List<Integer> solution;
    boolean[] cache;
    public List<Integer> grayCode(int n) {
        if(n < 0 || n > 32) {
            return new ArrayList();
        }

        cache = new boolean[(int)Math.pow(2, n)];
        solution = new ArrayList(cache.length);

        solution.add(0);
        cache[0] = true;
        dfs(n);
        return solution;
    }

    void dfs(int n) {
        if(solution.size() >= cache.length - 1) {
            return;
        }
        for(int i = 0; i < n; i++) {

            if(cache[solution.get(solution.size() - 1) ^ 1 << i]) {
                continue;
            }
            solution.add(solution.get(solution.size() - 1) ^ 1 << i);
            cache[solution.get(solution.size() - 1)] = true;

        }
        dfs(n);
    }

    // bit 操作 格雷码公式
    public List<Integer> grayCodeStandard(int n) {
        int counter = (int)Math.pow(2, n);
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            solution.add(i ^ i >> 1);
        }
        return solution;
    }

    public static void main(String[] args) {
        List<Integer> integers = new GrayCode().grayCodeStandard(3);
        System.out.println(integers);
    }
}
