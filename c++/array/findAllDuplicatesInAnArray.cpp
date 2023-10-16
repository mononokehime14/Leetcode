/**
 * 442. Find All Duplicates in an Array
 * 经典的[1, n] mark, 这道题要求的O(1) space好像并不是包括answer, anw也可以inplace
 * 只要后面发现出现两次的时候再反正就可以了 不过这里不需要了
 * 注意一下细节:
 * 1. 当前的数字可能被前面反过负数, 要得到正常的数字要先abs
 * 2. [1..n], 所以index的时候要-1
*/
class Solution {
public:
    vector<int> findDuplicates(vector<int>& nums) {
        vector<int> answer;
        for(int i = 0;i < nums.size();++i) {
            int current = nums[i] < 0 ? abs(nums[i]) : nums[i];
            if(nums[current-1] < 0) answer.push_back(current);
            nums[current-1] *= -1;
        }
        return answer;
    }
};