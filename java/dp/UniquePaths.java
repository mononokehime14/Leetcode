public class UniquePaths {
    /* 62
     * 这道题即便用DP 我之前的做法也是不大高效的 base case的理解不是很好
     * 这里可以多补一个row 一个 column 这样我们直接从第一个开始迭代
     * 然后我们要注意第一个应该有一种方法 所以要想办法把第一个变成1 这里有点奇技淫巧 把左二变成1
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        dp[1][0] = 1;
        for(int i = 1;i <= m;i++) {
            for(int j = 1;j <= n;j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
        // int[][] mem = new int[m][n];
        // if(m > 1) mem[1][0] = 1;
        // if(n > 1) mem[0][1] = 1;
        // for(int i = 0;i < m;i++){
        //     for(int j = 0;j < n;j++){
        //         if(i > 0){
        //             mem[i][j] += mem[i-1][j];
        //         }
        //         if(j > 0){
        //             mem[i][j] += mem[i][j-1];
        //         }
        //     }
        // }
        // mem[0][0] = 1; //this is required because testcase 1,1 should give 1...
        // return mem[m-1][n-1];
        /*
         * 以上做法是正宗DP但是是愚蠢的，因为可以直接用数学
         * 利用题目必然从左上出发到右下，每次右或者下，则必然用m - 1 + n - 1步。
         * 下和右内部的顺序没有分别，所以是一个Combination问题
         * C(n, k) = n! / (n - k)! k!
         * 可以直接带入，得(m + n)!/m! * n!
         */
    }
}
