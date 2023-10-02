/**
 * 209. Minimum Size Subarray Sum
 * 最标准的滑动窗口 subarray 然后window内又要满足特殊条件>= target
 * 很好决定右指针扩张和左指针收缩
 * 这里有两个细节 一个是min什么时候更新 放在收缩loop里面不大搞笑 相当于每次比较
 * 可以将loop设计成sum - nums[left] >= target才进入 这样留下的是最小的正确答案
 * 当然也可能sum还没到 故此外面还要套一个if
*/
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int left = 0, sum = 0, minimum_len = INT_MAX;
        for(int right = 0;right < nums.size();++right) {
            sum += nums[right];
            if(sum >= target) {
                while(sum - nums[left] >= target) {
                    sum -= nums[left++];
                }
                minimum_len = min(minimum_len, right - left + 1);
            }
        }
        return minimum_len == INT_MAX ? 0 : minimum_len;
    }
};