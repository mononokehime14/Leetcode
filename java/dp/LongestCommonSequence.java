public class LongestCommonSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        /* DP双指针, two strings双指针值得一试, 然后写出状态转移方程
         * DP[i][j] = max(
         *  DP[i-1][j-1] + 1 if char at i & j are same,
         *  DP[i-1][j], //不要我自己
         *  DP[i][j-1] // 不要另一边的
         * )
         * 这里有一个技巧, 就是pad一下dp table, 不然的话就要initialize index 0的col和row
         * 而pad进来的row和col, 代表word和另一半""比较, 所以是全0而java会calloc 0
         */
        int n = text1.length(), m = text2.length();
        char[] arr1 = text1.toCharArray();//使用char array会将速度提升到98%, 但是会下降memory到10%
        char[] arr2 = text2.toCharArray();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1;i <= n;i++){
            for(int j = 1;j <= m;j++){
                int equal = arr1[i-1] == arr2[j-1] ? dp[i-1][j-1] + 1 : 0;
                dp[i][j] = Math.max(
                    equal,
                    Math.max(dp[i-1][j], dp[i][j-1])
                );
            }
        }
        return dp[n][m];
    }
}
