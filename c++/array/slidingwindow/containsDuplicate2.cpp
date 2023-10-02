/**
 * 220. Contains Duplicate III
 * 非常复杂的一道题目 放在滑动窗口分类里 也是因为第一部分思路要用到滑动窗口
 * 也就是indexDiff可以用滑动窗口
 * 这道题暴力解决(N-indexDiff)*indexDiff是TLE的 需要用到set排序
 * 当前窗口是排序的 那么我只需要找到符合current - b <= valueDiff, 也就是current - valueDiff <= b
 * 这里要搜出b我们就要用lower_bound 找出第一个greater/equal current - valueDiff的
 * 这里因为用了C++的set 没办法只能用c++封装好的二分查找
 * 然后注意lower_bound之后还需要再反过来检查一下 这是因为又可能nums[i] - valueDiff是负数 导致错误答案被纳入进来
 * 时间成本应该是(N-indexDiff)*log(indexDiff)
*/
class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int indexDiff, int valueDiff) {
        int n = nums.size();
        if(n < 2) return false;
        set<int> window;
        for(int i = 0;i < n;++i) {
            if(i > indexDiff) window.erase(nums[i-indexDiff-1]);
            auto it = window.lower_bound(nums[i] - valueDiff);
            if(it != window.end() && *it - nums[i] <= valueDiff) return true;
            window.insert(nums[i]);
        }
        // for(int i = 0;i < n;++i) {
        //     for(int j = i + 1;j <= min(i + indexDiff, n - 1);++j) {
        //         if(abs(nums[i] - nums[j]) <= valueDiff) return true;
        //     }
        // }
        return false;
    }
};