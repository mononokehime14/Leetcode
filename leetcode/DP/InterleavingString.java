public class InterleavingString {
    /* 97
     * 被substring迷惑了 我在想往前检查每一个substring 但是其实这里只要往前一个char就行了
     * 原理是这样的 如果当前char s1match 那么s1往前推一个 如果还是s1match 只是s1当前的substring多了一个罢了
     * 最终的拼凑不管是怎么样 都会满足n和m的差距小于等于1的
     * 注意比较的时候 s3是用i + j - 1的 可以通过例子 比如两个3的 我们就要比较最后一个char也就是index5来算出 
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), k = s3.length();
        if(n + m != k) return false;
        boolean[][] dp = new boolean[n+1][m+1];
        for(int i = 1;i <= m;i++) {
            if(s2.charAt(i-1) != s3.charAt(i-1)) break;
            dp[0][i] = true;
        }
        for(int i = 1;i <= n;i++) {
            if(s1.charAt(i-1) != s3.charAt(i-1)) break;
            dp[i][0] = true;
        }
        dp[0][0] = true;
        for(int i = 1;i <= n;i++) {
            for(int j = 1;j <= m;j++) {
                dp[i][j] = (s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j])
                    || (s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]);
            }
        }
        return dp[n][m];
    }
    // private int[][] memo;
    // public boolean isInterleave(String s1, String s2, String s3) {
    //     int n = s1.length(), m = s2.length(), k = s3.length();
    //     if(n + m != k) return false;
    //     memo = new int[n+1][m+1];
    //     return dp(s1, 0, s2, 0, s3);
    // }
    // private boolean dp(String s1, int i1, String s2, int i2, String s3) {
    //     if(i1 + i2 == s3.length()) {
    //         return true;
    //     }
    //     if(memo[i1][i2] != 0) return memo[i1][i2] == 2;
    //     boolean res = false;
    //     if(i1 < s1.length() && s1.charAt(i1) == s3.charAt(i1+i2)) {
    //         res = dp(s1, i1+1, s2, i2, s3);
    //     }
    //     if(i2 < s2.length() && s2.charAt(i2) == s3.charAt(i1+i2)) {
    //         res = res || dp(s1, i1, s2, i2+1, s3);
    //     }
    //     memo[i1][i2] = res ? 2 : 1;
    //     return res;
    // }
}
