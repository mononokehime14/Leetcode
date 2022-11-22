public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        /* 10
         * 近期第一次自己做对了hard!我是无敌的! 
         * 思路仍然是基于2 strings 双指针, 比对当前的char
         * DP[i][j] = ANY{
         *  if P[j] = ".", DP[i-1][j-1], //skip
         *  if P[j] = "*", MAX{
         *      DP[i][j-2], use 0 times,
         *      DP[i-1][j-2], if match, use 1 time
         *      DP[i-1][j], if match, use multiple times
         *  },
         *  otherwise, if P[j] == S[i], DP[i-1][j-1], //skip
         *  else, false
         *  注意base case有讲究, 并非除了[0][0] all 0, 而是要看是否有
         *  *通配符, 如果是连着的*, 则可以是true
         *  有一个潜在的优化是用boolean array dp table
         * }
        */
        int n = s.length();
        int m = p.length();
        char[] s1 = s.toCharArray();
        char[] p1 = p.toCharArray();
        int[][] dp = new int[n+1][m+1];
        dp[0][0] = 1;
        boolean flag = m > 1 && p1[1] == '*' ? true : false;
        for(int i = 2;i < m + 1 && flag;i+=2){
            if(p1[i-1] == '*'){
                dp[0][i] = 1;
                dp[0][i-1] = 1;
            }else{
                flag = false;
            }
        }
        for(int i = 1;i < n + 1;i++) {
            for(int j = 1;j < m + 1;j++) {
                if(p1[j - 1] == '.'){
                    dp[i][j] = dp[i-1][j-1]; // skip
                }else if(p1[j - 1] == '*') {
                    // preceding should not be null
                    int zero = dp[i][j-2];
                    int one = 0, other = 0;
                    if(p1[j-2] == s1[i-1] || p1[j-2] == '.') { //match
                        if(p1[j-2] != '.' && i > 1 && s1[i-2] != s1[i-1]){ // prev is different
                            one = dp[i-1][j-2];
                        }else{
                            other = dp[i-1][j]; 
                        } 
                    }
                    dp[i][j] = Math.max(Math.max(zero, one), other);
                }else{
                    dp[i][j] = p1[j - 1] == s1[i - 1] ? dp[i-1][j-1] : 0;
                }
            }
        }
        return dp[n][m] == 1;
    }
}
