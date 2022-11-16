public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        /* 486
         * 这道题的状态转移方程异常隐晦 但是代表了某一种pattern
         * “博弈问题的前提一般都是在两个聪明人之间进行，编程描述这种游戏的一般方法是二维 dp 数组，数组中通过元组分别表示两人的最优决策”
         * 这里 DP[i][j][0]代表从i - j先手能拿到的最大分数 DP[i][j][1]则是后手
         * 那么先手DP[i][j][0] = Max(dp[i][j-1][1], dp[i+1][j][1]) //先手完之后是后手
         * DP[i][j][1]则是有两种可能 DP[i+1][j][0] 或者 DP[i][j-1][0] 但是这不是由它决定的 而是要看先手选了什么
         * base case呢 我们最无法知道的就是dp[0][n-1] 反而是dp[0][0][0] 先手拿走 dp[0][0][1] 就是后手没有
         * 所以对角线上存在base case
         * 而这道题的答案存在于dp[0][n-1] 这也是为什么我们倒过来迭代
         */
        int n = nums.length;
        int[][][] dp = new int[n][n][2];
        for(int i = 0; i < n;i++) {
            dp[i][i][0] = nums[i];
            dp[i][i][1] = 0;
        }
        for(int i = n - 2;i >= 0;i--) {
            for(int j = i + 1;j < n;j++) {
                int left = nums[i] + dp[i+1][j][1];
                int right = nums[j] + dp[i][j-1][1];
                if(left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1] = dp[i+1][j][0];
                }else{
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j-1][0];
                }
            }
        }
        return dp[0][n-1][0] >= dp[0][n-1][1];
    }
}
