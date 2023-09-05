/**
 * 128. Longest Consecutive Sequence
 * 这道题既不在乎顺序 又要求consecutive dp不是很合适
 * 我本来的想法是用hash counting一下 然后从1到最大值找一遍
 * 但是事实上可以随机挑选一个然后扩散 然后删除 直到hash空 这是更快的
*/
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> count;
        for(const int n : nums) {
            count.insert(n);
        }
        int ans = 0;
        while(!count.empty()) {
            int random = *(count.begin()), local = 1;
            count.erase(random);
            int left = random - 1, right = random + 1;
            while(count.find(left) != count.end()) {
                count.erase(left);
                left--;
                local++;
            }
            while(count.find(right) != count.end()) {
                count.erase(right);
                right++;
                local++;
            }
            ans = max(ans, local);
        }
        return ans;
    }
};