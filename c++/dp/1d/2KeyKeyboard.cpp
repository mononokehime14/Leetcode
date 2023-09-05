#include <vector>
using namespace std;
/**
 * 650. 2 Keys Keyboard
 * 这道题的解法非常飞刀. 首先我们沿用基本的思路, 设定dp[i]是最少步骤凑到i长度, 也就是直接用题目所求
 * 这时候我们考虑, 怎么去组成i? 先假设有一个j能被整除, 那么我们需要先凑出j, 然后再用j去凑成i
 * 而第二个部分并不是简单的重复复制拷贝, 而是存在更好的策略的, 那么简单的(i/j)*2就是不对的, 而是要想到
 * 这是等价于用1凑成i/j的, 也就是dp[i/j] (只不过是等比例放大值而已) 所以状态转移就是dp[i] = dp[j] + dp[i/j]
 * 而我们只需要第一个能整除的, 这是为什么呢? 因为这必定是一个必要的, 同时也是最小的因数, 后面的组合排出来都是一样的
 * 比如dp[12] = dp[2] + dp[6] = dp[2] + dp[2] + dp[3]
 * dp[12] = dp[3] + dp[4] = dp[3] + dp[2] + dp[2]
*/
class Solution {
public:
    int minSteps(int n) {
        vector<int> dp(n+1, 0);
        for(int i = 2;i <= n;i++) {
            dp[i] = i;
            for(int j = 2;j * j <= i;j++) {
                if(i % j == 0) {
                    dp[i] = dp[j] + dp[i/j];
                    break;
                }
            }
        }
        return dp[n];
    }
};