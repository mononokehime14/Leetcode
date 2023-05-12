import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        /*
         * 一开始的状态转移方程是不标准的 我没有重写过 直接去的TT OA 导致01背包卡了很久
         * 状态转移应该是放入或者不放入 base case应该是amount为0的时候number为0 coin不用的时候不可能make up amount 应该为无限大
         * DP[i][j] = MIN(DP[i-1][j], DP[i][j - coins[i]])
         * 这里有几个细节 首先最大值应该设置为amount + 1 这样甚至不用处理MAX_VALUE + 1 overflow的问题
         * 然后就是j可以从coins[i]开始 因为低于coin[i]的不可能make up
         */

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0;i < coins.length;i++) {
            for(int j = 1;j < amount + 1;j++) {
                if(j - coins[i] >= 0 && dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        
        // int[] mem = new int[amount + 1]; // base case 在amount 0的时候
        // for(int i = 0;i < amount + 1;i++){
        //     mem[i] = -1;
        // }
        // mem[0] = 0;
        // for(int i = 0;i < amount + 1;i++){
        //     if(mem[i]==-1){
        //         continue;
        //     }
        //     for(int j = 0;j < coins.length;j++){
        //         if(coins[j] + i <= amount && coins[j] + i > 0){ //这里9也要check，因为有[1,2147483647]，2的testcase
        //             if(mem[coins[j] + i] == -1 || mem[i] + 1 < mem[coins[j] + i]){
        //                 mem[coins[j] + i] = mem[i] + 1;
        //             }
        //         }
        //     }
        // }
        // return mem[amount];
    }
}
