public class BestTimeBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        /* 122
         * 和121的思路是一摸一样的 区别在于这一次 dp[i-1][k-1][0]并不是dp[i-1][0][0] 并不是base case
         * 更新的时候注意这个细节
         */
        int n = prices.length, prevNo = 0, prevYes = 0 - prices[0];
        for(int i: prices) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes +i);
            prevYes = Math.max(prevYes, temp - i);
        }
        return prevNo;
        // int answer = 0;
        // for(int i = 1;i < prices.length;i++) {
        //     if(prices[i] > prices[i-1]) answer += prices[i] - prices[i-1];
        // }
        // return answer;
    }
}
