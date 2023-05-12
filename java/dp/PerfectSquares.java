public class PerfectSquares {
    /*
     * 思路非常好想 还在犹豫是不是二维dp 但是想来想去 只有一个维度 能够囊括所有的solution space
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, 10001);
        int[] squares = new int[100];
        for(int i = 0;i < 100;i++) {
            squares[i] = (i+1) * (i+1);
            if(squares[i] <= n) dp[squares[i]] = 1;
        }
        for(int i = 1;i <= n;i++) {
            for(int j = 0;j < 100;j++) {
                if(i - squares[j] > 0 && dp[i - squares[j]] != 10001) dp[i] = Math.min(dp[i], dp[i - squares[j]] + 1);
            }
        }
        return dp[n];
    }
}
