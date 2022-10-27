public class BestTimeBuyAndSellStockFee {
    public int maxProfit(int[] prices, int fee) {
        /* 这道题只需要减去transaction fee就行了 这里注意如果在DP[i][1]更新的时候减掉 那么base case
         * 不能再设置为DP[0][1] = -prices[0] 因为这样就得不到更新了 而要设置成Integer.MIN_VALUE
         */
        int prevNo = 0, prevYes = 0 - prices[0];
        for(int i: prices) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes + i - fee);
            prevYes = Math.max(prevYes, temp - i);
        }
        return prevNo;
        // int n = prices.length;
        // int[][] dp = new int[n+1][2];
        // dp[0][0] = 0;
        // dp[0][1] = 0 - prices[0];
        // for(int i = 0;i < n;i++) {
        //     dp[i+1][0] = Math.max(dp[i][0], dp[i][1] + prices[i] - fee);
        //     dp[i+1][1] = Math.max(dp[i][1], dp[i][0] - prices[i]);
        // }
        // return dp[n][0];
    }
}
