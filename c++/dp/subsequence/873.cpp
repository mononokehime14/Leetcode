#include <include/listnode.h>
using namespace std;
/**
 * 873. Length of Longest Fibonacci Subsequence
 * 这道题目可以借用palindrome subsequence的思路, 也就是dp[i][j]是从i到j的最长斐波那契sequence
 * 然后对于j, 设其为斐波那契数列的第三个, 要从前面找一个第二个, 然后从差值得到第一个的值, 看看有没有
 * 有的话, 就用第一个到第二个的dp更新当前
 * 我本来的切入点是套用最长增长子序列, 但是那个一维的思路放到这里有一点不好搭配
*/
class Solution {
public:
    int lenLongestFibSubseq(vector<int>& arr) {
        unordered_map<int, int> pos;
        int n = arr.size(), res = 0;
        vector<vector<int>> dp(n, vector<int>(n));
        for (int j = 0; j < n; ++j) {
            pos[arr[j]] = j;
            for (int i = 0; i < j; ++i) {
                int first = arr[j] - arr[i];
                int k = pos.find(first) != pos.end() ? pos[first]: -1;
                dp[i][j] = first < arr[i] && k >= 0? dp[k][i] + 1 : 2;
                res = max(res, dp[i][j]);
            }
        }
        return res > 2? res: 0;
    }
};