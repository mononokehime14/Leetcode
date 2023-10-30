/**
 * 462. Minimum Moves to Equal Array Elements II
 * 这道题初始的思路就是大的和小的一起往中间移动, 那我一开始是想分类讨论众数和平均数
 * 实际上这里找到中位数Median就可以了, 那与其sort, 我们用quick select找到这个中间的index
 * quick select比较清晰的写法还是两部分分开写, 一部分类似二分, 找到一个特定的index, 正因如此我觉得是不可能找不到的
 * 第二部分是sort的置换部分, 牢记第一pivot的选择, 第二是跳过满足order的, swap不满足的, 三是i >= j break
*/
class Solution {
public:
    int minMoves2(vector<int>& nums) {
        int n = nums.size();
        if (n < 2) return 0;
        if (n == 2) return abs(nums[0] - nums[1]); 
        int answer = 0, median = partition(nums);
        for (int i: nums) answer += abs(i - median);
        return answer;
    }
    int partition(vector<int>& nums) {
        int l = 0, r = nums.size() - 1, target = r / 2;
        while (l <= r) {
            int mid = quickSelect(nums, l, r);
            if (mid == target) return nums[mid];
            if (mid < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1; // not possible
    }

    int scan(vector<int>& nums, int left, int right) {
        int l = left + 1, r = right; // first as pivot
        while(1) {
            while (l < right && nums[l] <= nums[left]) ++l;
            while (left < r && nums[r] >= nums[left]) --r;
            if(l >= r) break;
            swap(nums[l], nums[r]);
        }
        swap(nums[r], nums[left]);
        return r;
    }
};