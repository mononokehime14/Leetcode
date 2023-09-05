#include <vector>
using namespace std;
/**
 * 338. Counting Bits
 * 实际上思路并不好像, loop一遍每个count 1当然很简单, 但是应该是n平方的复杂度
 * dp的方法状态转移我没想出来, 我想如果依赖于前一个数字, 那么如果前一个数字是奇数
 * 就需要找到从后往前数第一个0的位置, 这部分的1都会消失, 然后补上该位置一个1, 这个计算好像不直观
 * 答案是利用了&操作, 让当前的数字和当前数字-1 &, 这样奇数的最后一位会变成0, 等价于i-1, 也就是dp[i-1] + 1
 * 如果当前数字是偶数, -1的操作会将倒数第一个1变成0, 将这部分的0变成1, 这时候&原数等于从倒数第一个1开始全部清零
 * 相当于这部分先不看, 先看前一部分, dp拿到之后然后再把倒是第一个1补回来(+1)
*/
class Solution {
public:
    vector<int> countBits(int num) {
        vector<int> ret(num+1, 0);
        for (int i = 1; i <= num; ++i)
            ret[i] = ret[i&(i-1)] + 1;
        return ret;
    }
};