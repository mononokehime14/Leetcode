#include <vector>
using namespace std;
/**
 * 560. Subarray Sum Equals K
 * 这道题一开始我就想能不能滑动窗口 但是事实上不是subarray就要滑动窗口的 滑动窗口还是要看需不需要保持一个特定的区间做运算
 * 而这道题是要对检查所有的区间的 但是暴力检查所有区间复杂度太高了 可以考虑前缀数组 这样检查所有的区间只要检查任意两个点 复杂度n平方
 * 但是单纯的前缀数组仍然会TLE 我们需要考虑一次遍历的可能 这里我们可以多用一个hashmap来记录前缀和
 * 这就和two sum变成了一个思路 这里要加入所有sum - k的组合 这是因为数组里有负数 前缀和不一定是递增的
 * 所以i1 i2前缀和都符合条件的情况下 i1 - y和i2 - y都是合理的区间
*/
class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        int sum = 0, ans = 0;
        unordered_map<int, int> preSum;
        preSum[0] = 1; // same as previously preSum vector (size + 1)'s index 0, this means no elements
        for(const int num : nums) {
            sum += num;
            ans += preSum[sum - k]; // if not present this has no effect in C++
            preSum[sum]++;
        }
        return ans;
    }
};