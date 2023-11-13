/**
 * 862. Shortest Subarray with Sum at Least K
 * 切入点是prefix, 最后需要用到priority queue或者monolithic queue
 * 这里我的思路走错了, 我首先发现传统的滑动窗口是不行, 因为有负数, 左指针收缩的时候没办法判断, 
 * 碰到window sum < k就停是不对的, 可能右边还有解, 所以滑动窗口会错过解, 我就想了一个dp
 * 虽然手搓对了一个DP很让人骄傲, 但是不幸的是TLE了, N**2
 * 
 * 这里的切入角度要用前缀数组, 我们知道substring sum >= k完全可以用前缀, N**2检查所有的数组就可以了
 * 从这一步开始, 如何优化, 这里不是用hashmap简单统计prefix的个数就行的, 而是用priority queue, 记录prefix和对应的i
 * 如此一来, 对于i, 我们找前面最小的prefix, 尝试更新answer, 然后直接pop, 因为往后肯定不可能比现在更短
 * 这就是NlogN的解法. 我们可以进一步用单调队列优化, 因为每个prefix只会用一次嘛. 我们通过push的时候把所有大于自己的pop下来, 使得我们的queue
 * 中存的prefix是单调递增的, 这就替代了pq的功能, 每次我们也能从左边最小的开始.
*/
class Solution {
public:
    int shortestSubarray(vector<int>& nums, int k) {
        int n = nums.size(), answer = INT_MAX;
        long prefix_sum = 0;
        deque<pair<long, int>> mono_q;
        for(int i = 0;i < n;++i) {
            prefix_sum += nums[i];
            if(prefix_sum >= k) answer = min(answer, i + 1); // potential answer
            while(!mono_q.empty() && prefix_sum - mono_q.front().first>= k) {
                answer = min(answer, i - mono_q.front().second);
                mono_q.pop_front();
            }
            while(!mono_q.empty() && prefix_sum <= mono_q.back().first) mono_q.pop_back();
            mono_q.push_back(make_pair(prefix_sum, i));
        }
        return answer == INT_MAX ? -1 : answer;
    }

    int shortestSubarray_dp(vector<int>& nums, int k) {
        int n = nums.size(), answer = INT_MAX;
        vector<vector<int>> dp(n+1, vector<int>(k+1, INT_MAX));
        // for(int i = 1;i <= n;++i) if(nums[i-1] >= 0) dp[i][0] = 1;
        for(int i = 1;i <= n;++i) {
            int value = nums[i-1];
            for(int j = 0;j <= k;++j) {
                if(value >= j) {
                    dp[i][j] = 1;
                }else if(j-value <= k && dp[i-1][j-value] != INT_MAX){
                    dp[i][j] = dp[i-1][j-value] + 1;
                }
            }
            answer = min(answer, dp[i][k]);
        }
        return answer == INT_MAX ? -1 : answer;
    }
};