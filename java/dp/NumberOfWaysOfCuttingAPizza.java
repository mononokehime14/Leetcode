public class NumberOfWaysOfCuttingAPizza {
    public int ways(String[] pizza, int k) {
        /**
         * TT 2022 OA原题. 恐怖的三维DP 这道题理解起来不是很容易 要求的其实是分解成k部分的方法总数
         * 所以total number naturally这个可以想到用DP 或者回溯
         * 这个方法用的是DP 大体思路是bottom up 以i,j开头的矩阵 分成c(0~k)份的分法递推
         * 可以理解为背包问题把背包变成切一刀分出去的二维矩阵
         * 注意preSum的使用 可以让我们快速算出行或者列中有没有Apple
         */
        int m = pizza.length, n = pizza[0].length(), M = (int)1e9 + 7;
        int[][][] dp = new int[m+1][n+1][k+1];
        int[][] preSum = new int[m+1][n+1];
        for(int i = m - 1;i >= 0;i--) {
            for(int j = n - 1;j >= 0;j--) {
                boolean curF = pizza[i].charAt(j) == 'A';
                dp[i][j][1] = (curF || dp[i+1][j][1] == 1 || dp[i][j+1][1] == 1) ? 1 : 0;
                preSum[i][j] = preSum[i+1][j] + preSum[i][j+1] - preSum[i+1][j+1];
                if(curF) preSum[i][j]++;
            }
        }
        for(int i = m - 1;i >= 0;i--) {
            for(int j = n - 1;j >= 0;j--) {
                int pc = preSum[i][j];
                // horizontal cut
                for(int ii = i + 1;ii < m;ii++) { // one row, two row, ...
                    for(int c = 2;c <= k && pc - preSum[ii][j] > 0;c++) {
                        dp[i][j][c] += dp[ii][j][c-1];
                        dp[i][j][c] %= M;
                    }
                }
                //vertical cut
                for(int jj = j + 1;jj < n;jj++) {
                    for(int c = 2;c <= k && pc - preSum[i][jj] > 0;c++) {
                        dp[i][j][c] += dp[i][jj][c-1];
                        dp[i][j][c] %= M;
                    }
                }
            }
        }
        return dp[0][0][k];
    }
}
