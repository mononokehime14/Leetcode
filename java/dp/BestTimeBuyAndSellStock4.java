public class BestTimeBuyAndSellStock4 {
    public int maxProfit(int k, int[] prices) {
        /* 188
         * 有了121 和 123 的铺垫 这道题虽然很复杂 但是显得毫无难度了 一样的思路 三种状态 一种都省略不了
         * 存在空间优化的可能性
         */
        int n = prices.length;
        int[][] dp = new int[k+1][2];
        for(int i = 0;i <= k;i++) {
            dp[i][0] = 0;
            dp[i][1] = 0 - prices[0];
        }
        for(int i: prices) {
            for(int j = 1;j <= k;j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + i);
                dp[j][1] = Math.max(dp[j][1], dp[j-1][0] - i);
            }
        }
        return dp[k][0];
        // int[][][] dp = new int[n+1][k+1][2];
        // for(int i = 0;i <= k;i++) {
        //     dp[0][i][0] = 0;
        //     dp[0][i][1] = 0 - prices[0];
        // }
        // for(int i = 0;i < n;i++) {
        //     for(int j = 1;j <= k;j++) {
        //         dp[i+1][j][0] = Math.max(dp[i][j][0], dp[i][j][1] + prices[i]);
        //         dp[i+1][j][1] = Math.max(dp[i][j][1], dp[i][j-1][0] - prices[i]);
        //     }
        // }
        // return dp[n][k][0];
    }
}
