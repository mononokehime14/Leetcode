#include <vector>
using namespace std;
/**
 * 474. Ones and Zeros
 * 背包问题的变种, 现在代价有两个, m和n, 这就变成了一个三维的DP, 空间压缩之后是2维
 * 注意这里空间压缩导致的反向遍历, 正是因为需要dp[i-1][m-c0][n-c1], 如果正向遍历这个就被覆盖掉了
*/
class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<int>> dp(m+1, vector<int>(n+1, 0));
        for(const string & str: strs) {
            auto [c0, c1] = count(str);
            for(int i = m;i >= c0;--i) {
                for(int j = n;j >= c1;--j) {
                    dp[i][j] = max(dp[i][j], dp[i-c0][j-c1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    pair<int, int> count(const string & str) {
        int c0=0, c1=0;
        for(const char c : str) {
            if(c == '1') {
                c1++;
            }else{
                c0++;
            }
        }
        return make_pair(c0, c1);
    }
};