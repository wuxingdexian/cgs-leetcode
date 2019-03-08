package dynamicprogramming.hard;

import dynamicprogramming.easy.BestTimeToBuyAndSellStock;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/" />
 * 123. Best Time to Buy and Sell Stock III
 *
 * <p>
 * 1. 建模：
 * （1）recurrence relation，动态规划1
 * 参考{@link BestTimeToBuyAndSellStock} 双向执行分别执行一次DP，然后遍历，找到左右相加后最大的值
 * https://segmentfault.com/a/1190000003483697 这里有思路提示
 *
 * （2）分治法
 * 按中点分，找到两边最大的值，然后两边相加，同时注意合并时中间附近数据递增的情况
 *
 * （3）本质应该也是recurrence relation，知识建模的角度放在最大持有收益上
 * 动态规划2
 * ----------------------------网上给出的解释-------------------------------------
 * 参考《https://segmentfault.com/a/1190000003483697》，结合{@link BestTimeToBuyAndSellStockIII#maxProfitStandard(int[])}的代码，
 * 会比较清晰：卖是要后于买的
 * -----------------------------------------------------------------
 *
 * 根据上面给出的思路，把recurrence relation 递推式找出来
 * 四个操作点：b1,s1,b2,s2，粉笔为买入点1、卖出点1、买入点2、卖出点2，在不同点操作后最大收益分别为
 * max_b1(i)=max(max_b1(i-1), 0-p[i]), max_s1(i)=max(max_s1(i-1), max_b1(i-1)+p[i]),
 * max_b2(i)=max(max_b2(i-1),max_s1(i-1)-p[i]), max_s2(i)=max(max_s2(i-1), max_b2(i-1) + p[i])
 * 初始条件：max_b1(0)=-p[0]，max_s1(i)=0，max_b2(i)=-p[0]，max_s2(i)=0；
 * 原始收益都是0；因为原始收益（资本）都是0，初始买入时的最大收益为第一个元素的负数值
 *
 * （4）recurrence relation
 * 类似于（1）的双向DP。
 * 找到每个波峰，然后计算从数组起始位置到该波峰的最大收益。
 * 找到波谷，计算从每个波谷到最后元素的最大收益。
 * 最大收益可能只需要一次交易即可完成（如非递减序列），也可能需要两次交易才能完成。（在允许n次交易的过程，可能在执行1到小于n的某一次就已经使得收益最大）
 * 这就是上面{@link BestTimeToBuyAndSellStock}的DP方法和（2）分治法思想的结合，不是按照中点来，而是按照波峰来划分序列为两部分。
 * // FIXME: 21/08/2017 这个思路在处理两次交易的情况时可以的。但是允许执行n次交易，那这个复杂度就上来了，因为最大可能有2的n次方组合情况（bit string建模可知）。由此，（3）的方法会更优
 *
 * （5）follow up
 * 搜索到有个变形，若给定初始x元，如100￥，如何使得收益最大，若1股9￥，则100块只能买99￥，剩下1￥不能买入。
 * 设max_sum(n)为截止n的最大收益，初始initial_money为初始金钱，p(i)为股票在i点的价格，
 * 股票收益sum(n)=(p(j)-p(i))*(initial_money/p(i))=(p(j)/p(i))*initial_money - initial_money，为在点i买入在j点卖出的股票收益。
 * 可以看到关在在于p(j)/p(i)的比值大小决定了收益，要得到max_sum(n)，则找到p(j)/p(i)最大比例！
 *
 * 注意：把关系搞清楚，问题本质出来。那问题即迎刃而解。。
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 21/08/2017
 * @see
 * @since cgs-leetcode on  21/08/2017
 */
public class BestTimeToBuyAndSellStockIII {


    /**
     * 直接整体去思考整个问题的DP解的子问题，好难。先拆解问题到小问题，然后再找小问题的DP解，此时小问题已经可以找到DP解的子问题了
     *
     * 设p[1,...,n]为价格数组
     * max1(i)为从p[1]到p[i]时的最大收益
     * max2(j)为从p[j]到p[n]时的最大收益
     * 则maxProfit = max(max1(i) + max2(j)), 1<=i<j<=n
     *
     * 初始化max1(0)=0，计算max1(i)，从前往后遍历，记录每个到达p[i]时遇到的最小值_min，max1(i)=max(p[i]-_min, max1(i-1))
     *
     * 初始化max1(n+1)=0，计算max2(j)，从后往前遍历，记录每个p[j]的遇到的最大值_max，max2(j)=max(_max-p[j], max2(j+1))
     *  14/10/2018
     * @param prices
     * @return
     */
    public int maxProfitWithDP(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int[] maxLeft2Right = new int[prices.length];
        int[] maxRight2Left = new int[prices.length];

        int min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            }
            maxLeft2Right[i] = Math.max(prices[i]-min, maxLeft2Right[0]);
        }

        int max = prices[prices.length-1];
        for(int j = prices.length-2; j>=0; j--) {
            if(prices[j] > max) {
                max = prices[j];
            }
            maxRight2Left[j] = Math.max(max-prices[j], maxRight2Left[j+1]);
        }

        // maxLeft2Right[prices.length-1] == maxRight2Left[0] 这里取一个就行
        int maxProfit = maxLeft2Right[prices.length-1];
        for(int i = 0; i < prices.length-1; i++) {
            maxProfit = Math.max(maxProfit, maxLeft2Right[i] + maxRight2Left[i+1]);
        }
        return maxProfit;
    }

    /**
     * 和maxProfitStandard类似，maxProfitStandard本质也是用到了DP，知识优化了存储空间。这里讲DP公式给出
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
// 四个操作点：b1,s1,b2,s2，粉笔为买入点1、卖出点1、买入点2、卖出点2，在不同点操作后最大收益分别为
// * max_b1(i)=max(max_b1(i-1), 0-p[i]), max_s1(i)=max(max_s1(i-1), max_b1(i-1)+p[i]),
// * max_b2(i)=max(max_b2(i-1), max_s1(i-1)-p[i]), max_s2(i)=max(max_s2(i-1), max_b2(i-1) + p[i])
// * 初始条件：max_b1(0)=-p[0]，max_s1(i)=0，max_b2(i)=-p[0]，max_s2(i)=0；
// *
        if(null == prices || prices.length == 0) {
            return 0;
        }
        int[] buy1 = new int[prices.length];
        int[] sell1 = new int[prices.length];
        int[] buy2 = new int[prices.length];
        int[] sell2 = new int[prices.length];
        // 因为原始收益（资本）都是0，初始买入时，最大收益为第一个元素的负数值
        buy1[0] = -prices[0];
        buy2[0] = -prices[0];
        // 原始收益都是0
        sell1[0] = 0;
        sell2[0] = 0;

        for (int i = 1; i < prices.length; i++){
            buy1[i] = Math.max(buy1[i-1], 0-prices[i]);
            sell1[i] = Math.max(sell1[i-1], buy1[i-1] + prices[i]);
            buy2[i] = Math.max(buy2[i-1], sell1[i-1] - prices[i]);
            sell2[i] = Math.max(sell2[i-1], buy2[i-1] + prices[i]);
        }

        return sell2[sell2.length - 1];

    }

    /**
     * 该代码拷贝自网上，描述得比较清楚了。
     * @param prices
     * @return
     */
    public int maxProfitStandard(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i = 0; i < prices.length; i++){
            //在该价格点卖出第二笔股票后手里剩的钱，等于上一轮买入第二笔股票后手里剩的钱加上卖出当前股票价格的钱，或者上一轮卖出第二笔股票后手里剩的钱两者中较大的
            release2 = Math.max(release2, hold2 + prices[i]);
            //在该价格点买入第二笔股票后手里剩的钱，等于上一轮卖出第一笔股票后手里剩的钱减去买入当前股票价格的钱，或者上一轮买入第二笔股票后手里剩的钱两者中较大的
            hold2 = Math.max(hold2, release1 - prices[i]);
            //在该价格点卖出第一笔股票后手里剩的钱，等于上一轮买入第一笔股票后手里剩的钱加上卖出当前股票价格的钱，或者上一轮卖出第一笔股票后手里剩的钱两者中较大的
            release1 = Math.max(release1, hold1 + prices[i]);
            //在该价格点买入第一笔股票后手里剩的钱，等于初始资金减去买入当前股票价格的钱或者初始资金（不买）中较大的
            hold1 = Math.max(hold1, -prices[i]);
        }
        return release2;
    }

    public static void main(String[] args) {
        int[] nums = {8,8,9,2,4,3,8,11};
        int i = new BestTimeToBuyAndSellStockIII().maxProfit(nums);
        System.out.println(i);
        int j = new BestTimeToBuyAndSellStockIII().maxProfitWithDP(nums);
        System.out.println(j);
    }
}
