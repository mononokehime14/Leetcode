public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /* 注意可以空间优化, 只refer前一个row同样位置和当前row上一个位置,
         * 也就是不影响我们的更新, 可以只用n的array
         */
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = Math.abs(obstacleGrid[0][0] - 1);
        for(int i = 0;i < obstacleGrid.length;i++){
            for(int j = 0;j < n;j++){
                dp[j] = obstacleGrid[i][j] == 1 ? 0 : j == 0 ? dp[j] : dp[j-1] + dp[j];
            }
        }
        return dp[n-1];
        // int m = obstacleGrid.length;
        // int n = obstacleGrid[0].length;
        // int[][] dp = new int[m][n];
        // dp[0][0] = Math.abs(obstacleGrid[0][0] - 1); // first element有可能是obstacle
        // for(int i = 1;i < m;i++){
        //     dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i-1][0];
        //     // if self is obstacle, then 0
        // }
        // for(int i = 1;i < n;i++){
        //     dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : dp[0][i-1];
        // }
        // for(int i = 1;i < m;i++){
        //     for(int j = 1;j < n;j++){
        //         if(obstacleGrid[i][j] == 1){
        //             dp[i][j] = 0; // self is obstacle
        //         }else{
        //             int up = obstacleGrid[i-1][j] == 1 ? 0 : dp[i-1][j];
        //             int left = obstacleGrid[i][j-1] == 1 ? 0 : dp[i][j-1];
        //             dp[i][j] += (up + left);
        //         }
        //     }
        // }
        // return dp[m-1][n-1];
    }
}
