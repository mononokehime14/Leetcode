/**
 * 368. Largest Divisible Subset
 * 非常精妙, 我没想出来的; 重点复习
 * 首先是DP思路, 我还以为会是某种背包问题的变种,但是显然这个数字放不放入set, 太难进行set size的计算了...
 * 这里的dp思路是到i为止的max set size, 然后和前面所有的j检查, 这个和longest increasing subsequence的DP是一个思路
 * (这里为什么j一定要从i开始还没明白, 可能是要一个底值)
 * 答案要输出set而不是size 所以我们需要parent, 最后再回头把set复原出来, 这个和bfs输出path是同一个思路
*/
class Solution {
public:
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 0), parents(n,  0);
        sort(nums.begin(), nums.end());
        int max_len = 0, max_pos = 0;
        for(int i = 0;i < n;++i) {
            for(int j = i;j >= 0;--j) {
                if(nums[i] % nums[j] == 0 && dp[i] < 1 + dp[j]) {
                    dp[i] = 1 + dp[j];
                    parents[i] = j; //这是要sort的原因 要找最大的parent
                    if(dp[i] > max_len) {
                        max_len = dp[i];
                        max_pos = i;
                    } 
                }
            }
        }
        vector<int> answer;
        for(int i = 0;i < max_len;++i) {
            answer.push_back(nums[max_pos]);
            max_pos = parents[max_pos];
        }
        reverse(answer.begin(), answer.end());
        return answer;
    }
};