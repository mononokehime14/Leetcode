/**
 * 343. Integer Break
 * 这道题反应很快的想到了DP 这一类的问题实际上和割绳子什么的
 * 都可以叫做分割问题, 当前的值可以拆分成前几个算过的值的某种累积
 * 这也是我觉得贪心会漏掉正确解之后的第一反应
 * 这里就是可惜状态转移没想对 一开始的bug是全1 发现dp应该至少有一个最小值,
 * 就是1 * n-1, 至少不应该比它小
 * 然后面临一个问题, 实际上4 = 2 + 2, 2 * 2自己要比dp[2], which is 1, * dp[2]要大
 * 这也就说明 我们不能只算dp[j] * dp[i - j] 而也要看直接乘原数字 就只有这一点没想到
 * 
 * 当然实际上, 数学方法更好. 我想到的是对于一个数字, 拆分成两个尽可能相同的数字, 肯定乘积最大, 
 * 但是如果可以不分成两个, 比如22拆成10 10 2, 这就是11 11要大了
 * 实际上还是一样, 要尽可能相近, 假设每个是n/x, 乘积(n/x)^x, 求导可以得出n/x等于e, 离e最近的是3
 * 所以我们尽可能拆成3就行了, n < 4可以直接return n-1, 但是例外是1, return 0就不行, 所以这里有一些特殊的base case 
*/
class Solution {
public:
    int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        if (n == 5) return 6;
        if (n == 6) return 9;
        return integerBreak(n-3) * 3;
    }
    int integerBreak_dp(int n) {
        vector<int> dp(n+1);
        dp[1] = 1;
        for(int i = 2;i <= n;++i) {
            int local_max = i-1;
            for(int j = 1;j <= i/2;++j) {
                // cout << i << " " << j << " " << i-j << " " << dp[j] * dp[i - j] << endl;
                local_max = max(dp[j] * dp[i - j], local_max);
                local_max = max(j * (i - j), local_max);
                local_max = max(j * dp[i - j], local_max);
                local_max = max(dp[j] * (i - j), local_max);
            }
            dp[i] = local_max;
        }
        return dp[n];
    }
};