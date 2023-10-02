/**
 * 154. Find Minimum in Rotated Sorted Array II
 * 相比1 要处理duplicates的问题 这里我其实发现 1的代码能够处理一部分的duplicates
 * 比如2 2 2 0 1 确实left一下子就到了0 但是由于有nums[left] >= nums[right]的停止条件在
 * 一旦left right都在下沉队列的时候 直接return left是没问题的
 * 处理不了的duplicates是堆积在下沉队列尾部 并且和头一个或者某一个上层队列的值相等
 * 比如10 1 10 10 10, 这样left就会错过正确解 在下沉队列的duplicates中一直往后
 * 答案是单独对==的情况进行处理 直接跳过 如此相当于不看这个mid 最终会将mid的位置移到和左边不同的值上
 * 那这个时候就一定能判断是上层还是下沉队列了
*/
class Solution {
public:
    int findMin(vector<int>& nums) {
        int n = nums.size(), left = 0, right = n - 1;
        while(left < right && nums[left] >= nums[right]) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[left]) {
                left = mid + 1;
            }else if(nums[mid] == nums[left]){
                ++left;
            }else{
                right = mid;
            }
        }   
        return nums[left];
    }
};