/**
 * 377. Combination Sum IV
 * 牛逼! 自己做出来的, 首先是一般的combination思路, 这里要注意, 他妈的可以同时出现1 2 1和1 1 2
 * 也就是说一般combination的start就不用了, 那么递归什么时候停止呢? 这只能依赖于题目说值必然是正数
 * 那么加超出target就可以停止了 这个方法是基石 但是会TLE
 * 那么很明显 这是由于我们每次对于一个中间数都要重新算一遍 可以用动态规划
 * 这里我都用回溯写到这里了 当然是考虑自上而下的memo
 * 完全理解了备忘录是自上而下的DP 利用递归 memo更新用某种后序 就能保证最优子解确定
*/
class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        unordered_map<int, int> memo;
        return backtrack(nums, target, target, memo);
    }
    int backtrack(vector<int>& nums, int target, int c_sum, unordered_map<int, int>& memo) {
        if(c_sum == 0) return 1;
        if(c_sum < 0) return 0;
        if(memo.find(c_sum) != memo.end()) return memo[c_sum];
        int local_answer = 0;
        for(int i = 0;i < nums.size();++i) {
            local_answer += backtrack(nums, target, c_sum - nums[i], memo);
        }
        memo[c_sum] = local_answer;
        return local_answer;
    }
};