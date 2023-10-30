/**
 * 115. Distinct Subsequences
 * 这道题给了两个字符串 subsequence的dp就一定要考虑2D i, j双指针
 * 然后就要考虑怎么利用过去的状态 这里的核心就是如果char不match 应该继承S的前一个位置
 * 然后就是longmax完全因为testcase intermediate有overflow, 甚至long long都不能容纳 %一下就行了
 * 因为最后的答案4B int 说明overflow的部分实际不影响
*/
class Solution {
public:
    int numDistinct(string s, string t) {
        //2D
        int m = s.length(), n = t.length();
        long long longmax = 1000000000000000000;
        vector<vector<long long>> dp(n+1, vector<long long>(m+1, 0));
        for(int i = 0;i <= m;++i) dp[0][i] = 1;
        for(int i = 1;i <= n;++i) {
            for(int j = 1;j <= m;++j) {
                dp[i][j] = t[i-1] == s[j-1] ? dp[i-1][j-1]%longmax  + dp[i][j-1]%longmax : dp[i][j-1];
            }
        }
        return dp[n][m];
    }
};