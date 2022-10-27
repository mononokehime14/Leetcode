public class BestTimeBuyAndSellStockCooldown {
    public int maxProfit(int[] prices) {
        /* 309
         * 这是121的变种 区别在于多了一个cooldown状态
         * 我试了一下加入一个维度 dp[n+1][2][2] 记录是否处于cooldown状态 在写状态转移方程的时候我发现 
         * dp[i][1][1]是不可能的 不可能有一个状态处于cooldown 但是手里却还有股票
         * 所以我把维度去掉 保持一个cooldown variable 代表这一天进入cooldown时候的max
         * 但是其实 cooldown的介入 只会在一个地方影响状态转移 就是今天买股票的时候 要追溯到两天前
         * 不过不知道为什么我这个思路也跑通了 逻辑上是合理的
         */
        int prevNo = 0, prevYes = 0 - prices[0], cooldown = 0 - prices[0];
        for(int i: prices) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, cooldown);
            cooldown = prevYes + i;
            prevYes = Math.max(prevYes, temp - i);
        }
        return Math.max(cooldown, prevNo);
        // int n = prices.length;
        // int[][] dp = new int[n+1][2];
        // int cooldown = 0 - prices[0];
        // dp[0][0] = 0;
        // dp[0][1] = cooldown;
        // for(int i = 0;i < n;i++) {
        //     dp[i+1][0] = Math.max(cooldown, dp[i][0]);
        //     cooldown = dp[i][1] + prices[i];
        //     dp[i+1][1] = Math.max(dp[i][1], dp[i][0] - prices[i]);
        // }
        // return Math.max(cooldown, dp[n][0]);
    }
}
