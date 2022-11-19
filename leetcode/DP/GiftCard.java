/**
 * TTBank has gift card, 10, 30, 50, no limit
 * total use N card, cost M, we want no remaining balance, how many different ways?
 * !!!还没解对
 */
public class GiftCard {
    public int giftCard(int N, int M) {
        int[][] dp = new int[N+1][M+1];
        for(int i = 0;i <= N;i++) {
            dp[i][0] = i;
        }
        for(int i = 0;i <= M;i++) {
            dp[0][i] = 0;
        }
        for(int i = 1;i <= N;i++) {
            for(int j = 1;j <= M;j++) {
                dp[i][j] += j - 10 >= 0 ? dp[i-1][j-10] : 0;
                dp[i][j] += j - 30 >= 0 ? dp[i-1][j-30] : 0;
                dp[i][j] += j - 50 >= 0 ? dp[i-1][j-50] : 0;
            }
        }
        return dp[N][M];
    }
}
