public class BestTimeBuyAndSellStock {
    /* 121
     * 这道题目是一个系列的开始 如果不用DP的思路 那么可能是记录最低谷和最高峰
     * 这类题目有三种状态 有没有股票k 能进行几次买卖j 第几天i
     * 那么我们可以组建一个3D DP table DP[i][j][k] k = 0 or 1, j = max number of transactions i = number of prices/days
     * 那么 我们有如下状态转移方程:
     * DP[i][j][0] = Max(DP[i-1][j][0], DP[i-1][j][1] + prices[i]) // 要么前面也没买 今天继续摆烂 要么卖掉之前有的股票 今天就没有了
     * DP[i][j][1] = Max(DP[i-1][j][1], DP[i-1][j-1][0] - prices[i]) // 要么昨天有股票今天继续持有观望 要么昨天没有今天买入 则花了钱
     * 最后 我们只需要找DP[last day][max transaction][0] 为什么是0呢 因为卖掉会得到钱 肯定会比最后一天股票还烂在手里利润多
     * base case非常重要 DP[-1][...][0] 第0天没有股票 则利润自然为0 DP[-1][...][1] 是不可能出现的 可以设置为integer min 有的题目状态转移里会用到
     * DP[i-1][...][1] + prices[i] 按照这个推导我们可以设置为 -prices[0] 把这个值清成0
     * DP[...][0][0] = 0, DP[...][0][1]是不可能出现的 没有transaction的情况下不应该有股票 和上面的处理类似
     * 
     * 在这道题目中 j = 0 or 1 我们带入状态转移方程就能发现 DP[i-1][j-1][0] - prices[i]就是base case DP[i-1][j-1][0]只能代表之前还没买 因为这道题只能买一次
     * 故此 第二维可以去掉
     * 然后我们又可以进行空间优化
     */
    public int maxProfit(int[] prices) {
        int prevNo = 0, prevHas = 0 - prices[0];
        for(int i: prices) {
            prevNo = Math.max(prevNo, prevHas + i);
            prevHas = Math.max(prevHas, 0 - i);
        }
        return prevNo;
        // int n = prices.length;
        // int[][] dp = new int[n+1][2];
        // dp[0][1] = 0 - prices[0]; // 本来写的是Integer.MIN_VALUE 后来发现 拿到状态转移方程中比较 还是会和 -prices[i]比较
        // for(int i = 0;i < n;i++) {
        //     dp[i+1][0] = Math.max(dp[i][0], dp[i][1] + prices[i]);
        //     dp[i+1][1] = Math.max(dp[i][1], 0 - prices[i]);
        // }
        // return dp[n][0];
    }
}
