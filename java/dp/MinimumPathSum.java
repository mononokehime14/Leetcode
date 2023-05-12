public class MinimumPathSum {
    /* 64
     * 感觉是普通的DP 注意base case的处理
     * 这里用回溯也可以 备忘录是i和j 感觉这个地方会重叠 这样分析的话 时间复杂度也可以到m * n
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1;i < m;i++) dp[i][0] = dp[i-1][0] + grid[i][0];
        for(int i = 1;i < n;i++) dp[0][i] = dp[0][i-1] + grid[0][i];
        for(int i = 1;i < m;i++) {
            for(int j = 1;j < n;j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
