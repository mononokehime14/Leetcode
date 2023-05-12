public class CoinChange2 {
    public int change(int amount, int[] coins) {
        /* 完全背包问题不能想着只用一维dp table!
         * 如果只是DP[i] = sum(DP[i - x] for x in coins)
         * 那会遇到重复叠加的问题, 4(1+3) + 2 = 6, 3(1+2) + 3 = 6两者一样的combination
         * 但是在计算中会重复
         * 这说明这个状态选择思路是错误的!真正的状态选择思路应该是保持2d table, DP[i][j]
         * i代表用了什么coins, j代表凑的amount, 对于当前的第i个coin, 选择加或者不加
         * 这两个是完全不会重合的set, 一个随你怎么组合没有coins[i], 一个随你怎么组合有coins[i]
         * 前面的状态选择思路也没有考虑到可以不加的情况. 所以:
         * DP[i][j] = DP[i-1][j] + DP[i][j - coins[i]],不加coins[i]就等同于DP[i-1][j]
         * 写完了这个之后, 我们再可以考虑memory 优化的问题
        */
        int[] dp = new int[amount+1]; //memory优化后
        dp[0] = 1;
        for(int i = 1;i < amount + 1;i++){
            dp[i] = 0;
        }
        for(int i = 0;i < coins.length;i++) {
            for(int j = 1;j < amount + 1;j++){
                if(j - coins[i] >= 0){
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
        // int n = coins.length;
        // int[][] dp = new int[n+1][amount+1];
        // for(int i = 0;i < amount + 1;i++){
        //     dp[0][i] = 0;
        // }
        // for(int i = 0;i < n + 1;i++){
        //     dp[i][0] = 1;
        // }
        // for(int i = 1;i < n + 1;i++) {
        //     for(int j = 1;j < amount + 1;j++){
        //         if(j - coins[i-1] >= 0){
        //             dp[i][j] = dp[i-1][j] + dp[i][j - coins[i-1]];
        //         }else{
        //             dp[i][j] = dp[i-1][j];
        //         }
        //     }
        // }
        // return dp[n][amount];
    }
}
