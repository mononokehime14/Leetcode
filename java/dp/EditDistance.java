public class EditDistance {
    public int minDistance(String word1, String word2) {
        /* 这道题一开始的思路是由于题目说minimum number of operations -> DP
         * 状态转移方程非常难写, 基本可以min(三种不同的操作), 认识到需要nm的DP table
         * 但是没有想到双指针的方法. n和m各自代表一个word中的index, 从后开始, 这样状态转移就很好写了
         * DP[i][j] = MIN(
         *  DP[i-1][j-1] + 1 //Replace -> 匹配
         *  DP[i-1][j] + 1 //删除自己->看word1的下一个
         *  DP[i][j-1] + 1 //插入, 假想中已经插入了->匹配, word2下一个, 也可以理解为等价于word2删除
         * ) or 直接DP[i-1][j-1]如果他们就是一样的字母
         * 然后关键就是base case, 这里的base case不能去想index 0的字母, 因为和之前是一样的处理方式, 
         * 而要想如果index 0再往前移动, 也就是word traverse完了的情况, 这种情况就只能看另外一边还有多少
         * 要全部insert/delete, 也就是operation数量直接等于另一边剩下的index + 1
         * 
         * 2023.2.23重做发现了一个问题 如果char match的情况不直接continue 而是和notMatch做比较的话
         * 答案会不对 我match的话给个DP[i-1][j-1] 不match先给个m+1也就是不可能的值 原因暂时还没想明白
         */
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 0;i < n + 1;i++){
            dp[i][0] = i;
        }
        for(int i = 0;i < m + 1;i++){
            dp[0][i] = i;
        }
        for(int i = 1;i < n + 1;i++){
            for(int j = 1;j < m + 1;j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
                dp[i][j] = Math.min(
                    Math.min(dp[i-1][j] + 1,
                            dp[i-1][j-1] + 1),
                    dp[i][j-1] + 1
                );
            }
        }
        return dp[n][m];
    }
}
