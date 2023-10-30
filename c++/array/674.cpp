/**
 * 674. Longest Continuous Increasing Subsequence
 * 简单题, 它只需要找最长递增subarray, 那这就快慢指针一下就行了
*/
class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int left = 0, right = 1, n = nums.size(), answer = 1;
        while(right < n) {
            if(nums[right] > nums[right-1]) {
                answer = max(answer, right - left + 1);
            }else{
                left = right;
            }
            right++;
        }
        return answer;
    }
};