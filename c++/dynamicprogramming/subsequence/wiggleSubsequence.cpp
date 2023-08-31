#include <vector>
using namespace std;

/**
 * 376. Wiggle Subsequence
 * 这道题虽然是subsequence, 但是实际上是一个非常简单的1d dp
 * 正常来讲, subsequence有一个n平方的naive solution, dp[n][2], j去和i比较, 看看是positive还是negative difference
 * 但是这是为了递增subsequence 这里的difference不需要递增, 只需要alternative就行了, 所以生搬硬套有问题
 * 这样当前的dp[i][2]可以从dp[i-1][2]推得, 并可以空间压缩
*/

class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        int size=nums.size(), peak=1, valley=1;
        for(int i=1; i<size; ++i){
                 if(nums[i]>nums[i-1]) peak = valley + 1;
            else if(nums[i]<nums[i-1]) valley = peak + 1;
        }
        return max(peak , valley );
    }
};