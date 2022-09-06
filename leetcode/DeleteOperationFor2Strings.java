public class DeleteOperationFor2Strings {
    public int minDistance(String word1, String word2) {
        /* 编辑距离的简单版, 注意n+1和m+1的设计, 是为base case服务的 */
        int n = word1.length();
        int m = word2.length();
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int[][] dp = new int[n+1][m+1];
        for(int i = 0;i < n + 1;i++){
            dp[i][0] = i;
        }
        for(int i = 0;i < m + 1;i++){
            dp[0][i] = i;
        }
        for(int i = 1;i < n + 1;i++){
            for(int j = 1;j < m + 1;j++){
                if(w1[i - 1] == w2[j - 1]){
                    dp[i][j] = dp[i-1][j-1]; // equal不用delete
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[n][m];
    }
}
