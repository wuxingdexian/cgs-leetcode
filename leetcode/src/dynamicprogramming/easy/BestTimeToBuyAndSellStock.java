package dynamicprogramming.easy;

/**
 * <p>
 * 背景描述：
 * 121. Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.

 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5

 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0

 * In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * 1. 建模：
 * （1）recurrence relation；（2）relation，ordered pairs。
 * Bestn_1=(ai,aj)定义为第n-1次得到的最大收益有序对，i<=j且ai<=aj；
 * recurrence relation:Best(n)=max(Best(n-1),(a(x),a(n)))，ax为遍历数组时的当前最小值
 * （2）分治法，找左右两边最大的，然后合并处理是否有连续递增的特殊部分
 *
 * 2. 算法范式
 * 3. 算法
 * 4. 数据结构
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/08/2017
 * @see
 * @since DiscreteMathematics on  07/08/2017
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if(null==prices || prices.length<2) {
            return 0;
        }
        BestStockChoice bestStockChoice;
        if(prices[1]>prices[0]) {
            bestStockChoice = new BestStockChoice(prices[0],prices[1],prices[0]);
        } else {
            bestStockChoice = new BestStockChoice(prices[1],prices[1],prices[1]);
        }
        for(int i=2;i<prices.length;i++) {
            if(prices[i]-bestStockChoice.getTmpStart() >= bestStockChoice.getEnd()-bestStockChoice.getStart()) {
                bestStockChoice.setStart(bestStockChoice.getTmpStart());
                bestStockChoice.setEnd(prices[i]);
            }
            bestStockChoice.setTmpStart(Math.min(bestStockChoice.getTmpStart(), prices[i]));
        }
        return bestStockChoice.getEnd()-bestStockChoice.getStart();
    }

    private class BestStockChoice {
        int start,end,tmpStart;

        BestStockChoice(int start,int end,int tmpStart) {
            this.start=start;
            this.end=end;
            this.tmpStart=tmpStart;
        }
        int getStart() {
            return this.start;
        }
        int getEnd() {
            return this.end;
        }
        int getTmpStart() {
            return this.tmpStart;
        }
        void setStart(int start) {
            this.start=start;
        }
        void setEnd(int end) {
            this.end=end;
        }
        void setTmpStart(int tmpStart) {
            this.tmpStart=tmpStart;
        }
    }
}
