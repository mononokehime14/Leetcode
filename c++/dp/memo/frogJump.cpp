/**
 * 403. Frog Jump
 * 虽然最后不是用递归+备忘录的方法实现的, 但是我认为这是一个top down的题目
 * 我认为可以记录的状态是我到这个stone, 并且带着一个当前的jump, 这两个state联合组成的
 * 那我当即就想要写递归, 但是发现这个不是特别的好写, jump的时候要判断一下是不是能jump到一个stone上
 * 不然对于递归方法来讲, 时间成本就太高了, jump可能到water里, 也要递归下去吗? 要快速检查就得stones搞成一个哈希
 * 
 * 直接使用数据结构的方法比较好, 因为不管你怎么更新能跳到哪里, 我都是外层loop一遍stones
 * 然后这里是二维的, 很显然我们也明白要有两个状态
 * 哈希表的使用是因为不然的话就要用stones的最大值建vector, 空间上可能很大
*/
class Solution {
public:
    bool canCross(vector<int>& stones) {
        unordered_map<int, unordered_set<int>> dp;
        dp[0].insert(0);
        for(int stone : stones) {
            for(int current_jump : dp[stone]) {
                if(stone - 1 > 0) dp[stone + current_jump - 1].insert(current_jump - 1);
                dp[stone + current_jump].insert(current_jump);
                dp[stone + current_jump + 1].insert(current_jump + 1);
            }
        }
        return dp.find(stones.back()) != dp.end() && !dp[stones.back()].empty();
    }
};