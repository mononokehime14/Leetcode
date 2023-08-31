#include <vector>
#include <numeric>
using namespace std;
/**
 * 413. Arithmetic Slices
 * 最基础的dp是斐波那契数列, 拆分成子问题就能想出思路
 * 这个是基础dp的一个变种, 如果直接把要求的答案拆分, 只能求出最长递增slice
 * 但是要求的数目, 所以可以求以i为结尾的slices数量 然后再求和
*/
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        if(n < 3) return 0;
        vector<int> dp(n, 0);
        for(int i = 2;i < n;i++) {
            // if(nums[i] == nums[i-1] + 1 && nums[i-1] == nums[i-2] + 1) {
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                dp[i] = dp[i-1] + 1;
            }
        }
        return accumulate(dp.begin(), dp.end(), 0);
    }
};