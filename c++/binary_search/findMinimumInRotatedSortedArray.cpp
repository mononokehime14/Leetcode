/**
 * 153. Find Minimum in Rotated Sorted Array
 * 祖宗之法不可变 但是这里需要对二分查找的模版做更改
 * 首先 这种题目的思路 通常是二分查找 条件不是普通的和target相比
 * 我一开始觉得是和头一个element相比 如此找出下沉队列 但是这样不大好解决duplicates
 * 而且找出下沉队列之后 还得往下撸到最小值 这里的worst case是O(N)的
 * 正确的思路是和left比较 如果>=鉴定为上层序列 <是下沉序列
 * 这里按照模版 应该停止条件是left <= right然后下沉序列也要right = mid - 1
 * 但是在这道题里 right一旦往左mid - 1, left很难再像正常的accending序列一样回到正确的mid上面
 * 所以这里 mid一旦有可能是正确的 right = mid囊括进去 那么相应的 停止条件就不能是left <= right 不然的话就停不下来了
 * 
 * 哦原来这题没有duplicates II才要考虑的
*/
class Solution {
public:
    int findMin(vector<int>& nums) {
        int n = nums.size(), left = 0, right = n - 1;
        while(left < right && nums[left] >= nums[right]) {
            int mid = left + (right - left) / 2;
            if(nums[mid] >= nums[left]) {
                left = mid + 1;
            }else{
                right = mid;
            }
        }   
        return nums[left];
    }
};