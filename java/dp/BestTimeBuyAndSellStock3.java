public class BestTimeBuyAndSellStock3 {
    public int maxProfit(int[] prices) {
        /* 123
         * 和之前121的思路是一样的 只是这次无法省略k 由于k=2仍然较小 所以还是能够用4个variable代替
         * 这里要注意 最后return的比较是无意义的 因为k代表的不是transaction数量 而是max 所以return dp[n][2][0]就可以了
         */
        int prevNo1 = 0, prevYes1 = 0 - prices[0], prevNo2 = 0, prevYes2 = 0 - prices[0];
        for(int i: prices) {
            prevNo1 = Math.max(prevNo1, prevYes1 + i);
            prevYes1 = Math.max(prevYes1, 0 - i);
            prevNo2 = Math.max(prevNo2, prevYes2 + i);
            prevYes2 = Math.max(prevYes2, prevNo1 - i);
        }
        return prevNo2;
        // int n = prices.length;
        // int[][][] dp = new int[n+1][3][2];
        // for(int i = 0;i <= 2;i++) {
        //     dp[0][i][0] = 0;
        //     dp[0][i][1] = 0 - prices[0];
        // }
        // for(int i = 0;i < n;i++) {
        //     dp[i+1][1][0] = Math.max(dp[i][1][0], dp[i][1][1] + prices[i]);
        //     dp[i+1][1][1] = Math.max(dp[i][1][1], dp[i][0][0] - prices[i]);
        //     dp[i+1][2][0] = Math.max(dp[i][2][0], dp[i][2][1] + prices[i]);
        //     dp[i+1][2][1] = Math.max(dp[i][2][1], dp[i][1][0] - prices[i]);
        // }
        // return Math.max(dp[n][2][0], dp[n][1][0]);
    }
}
