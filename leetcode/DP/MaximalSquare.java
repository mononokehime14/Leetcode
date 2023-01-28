public class MaximalSquare {
    /* 221
     * 虽然击败的比率很低 但是再怎么样也是自己写出来的嘛 借鉴了maximal rectangle的思路
     * 保持一个height 一个left 我们能够知道在当前的ij上 往左延伸和往上延伸能够有的最大的1的数量
     * 如果我们dp规定是右下角以ij结尾的最大正方形的边长 那么我们就能通过left height和左上角dp[i-1][j-1]的最大正方形边长
     * 推得当前最大的正方形边长
     * 
     * 我艹 其实更好的答案很接近 可以直接用dp[i-1][j]和dp[i][j-1] 他们不久代表着往左和往上最大的1数量吗
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(int i = 1;i <= m;i++) {
            for(int j = 1;j <= n;j++) {
                if(matrix[i-1][j-1] == '0') continue;
                int local = Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j])) + 1;
                // System.out.println(i + " " + j + " " + dp[i-1][j-1] + " " + dp[i][j-1] + " " + dp[i-1][j] + " " + local);
                dp[i][j] = local;
                max = Math.max(local * local, max);
            }
        }
        return max;
    }
    // public int maximalSquare(char[][] matrix) {
    //     int m = matrix.length, n = matrix[0].length;
    //     int[] height = new int[n+1];
    //     int[][] dp = new int[m+1][n+1];
    //     int max = 0;
    //     for(int i = 1;i <= m;i++) {
    //         int[] left = new int[n+1];
    //         for(int j = 1;j <= n;j++) {
    //             if(matrix[i-1][j-1] == '0') {
    //                 height[j] = 0;
    //                 left[j] = 0;
    //                 continue;
    //             }
    //             height[j] = height[j] + 1;
    //             left[j] = left[j-1] + 1;
    //             int localMax = 1;
    //             if(dp[i-1][j-1] > 0) {
    //                 localMax = Math.min(left[j], Math.min(dp[i-1][j-1] + 1, height[j]));
    //             }
    //             dp[i][j] = localMax;
    //             max = Math.max(localMax * localMax, max);
    //         }
    //     }
    //     return max;
    // }
}
