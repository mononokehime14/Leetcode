/**
 * 2787. Ways to Express an Integer as Sum of Powers
 * 我想到的状态转移是dp[i][n] = SUM(dp[j][n-k] for j = 1 to i-1, k = pow(i, x))
 * 实际上就是类似最长增长子序列的DP写法, 这个思路总是我最先想到的, 也是比较直观的
 * 答案是说这道题可以理解为背包问题, i有两种可能嘛, 加入或者不加入, 答案加一起就可以了
 * 答案用的递归+备忘录, 可能要好写一点, 我还是选择了使用dp matrix bottom up
 * 这里可以画矩阵推演 可知base case有两种, 第一种随便你用什么数字, n是0, 这时候只要不用那个数字, 就是一个解
 * 而另外一行, 随便你n是什么, 如果i是0, 什么数字都不用, 那就不可能有解.
 * 这里由于答案组合里的数字要是unique的, 所以状态转移直接取决于前一个, dp[i][j] = dp[i-1][j] + dp[i-1][j-current]
 * 所以这里第二个循环的方向就无所谓啦
*/
class Solution {
public:
    int numberOfWays(int n, int x) {
        vector<vector<long>> dp(n+1, vector<long>(n+1, 0));
        int mod = 1e9 + 7;
        for(int i = 0;i <= n;++i) dp[i][0] = 1;
        for(int i = 1;i <= n;++i) {
            long current = pow(i, x);
            for(int j = 1;j <= n;++j) {
                long temp = dp[i-1][j];
                if(j - current >= 0) {
                    temp += dp[i-1][j-current];
                    temp %= mod;
                }
                dp[i][j] = temp;
            }
        }
        return dp[n][n];
    }
};