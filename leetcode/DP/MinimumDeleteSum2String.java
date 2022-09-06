public class MinimumDeleteSum2String {
    public int minimumDeleteSum(String s1, String s2) {
        /* 和编辑距离问题同样思路, 注意base case是累加的 */
        int n = s1.length();
        int m = s2.length();
        char[] w1 = s1.toCharArray();
        char[] w2 = s2.toCharArray();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1;i < n + 1;i++){
            dp[i][0] = dp[i-1][0] + w1[i-1];
        }
        for(int i = 1;i < m + 1;i++){
            dp[0][i] = dp[0][i-1] + w2[i-1];
        }
        for(int i = 1;i < n + 1;i++){
            for(int j = 1;j < m + 1;j++){
                if(w1[i - 1] == w2[j - 1]){
                    dp[i][j] = dp[i-1][j-1]; 
                    // equal不用delete, 这一步显著加速, 但照理来说应该考虑到状态转移里
                }else{
                    dp[i][j] = Math.min(
                        dp[i-1][j] + (int)w1[i-1], 
                        dp[i][j-1] + (int)w2[j-1]
                    ); 
                }
            }
        }
        return dp[n][m];
    }
}
