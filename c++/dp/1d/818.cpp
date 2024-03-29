#include <vector>
using namespace std;
/**
 * 818. Race Car
 * 这道题当然一看就知道要dp, 而且很可能就是1d, dp[i]记录到i的最短steps
 * 但是具体的思路非常玄妙. 首先是要发现, 步数是1, 2, 4, 8..., 因此正走到任何一个点, 都是用2**r - 1表示, r表示步数
 * 那么如果dp[i] i就在2**r - 1的节点上, 正走就能走到, 这必然是最短的
 * 如果不在, 那么有两种可能, 我们先越过i, 然后再从后面回来; 要么我们在i前面做一次折返, 再正着过去
 * 第一种情况下, 我们必然正走一次到2**r - 1的节点上, 然后翻转, 剩下的距离可以用dp
 * 这里最短的情况必然是到2**r - 1, 再往后面走再折返回来只能走更多的steps
 * 第二种情况需要一些讨论, 下图展示了这种走法
 * --------->
 *     <-----
 *     ---------> Target
 * 
 * 为什么是这种曲折的走法呢? 因为这种情况是正走到target的, 除了target直接就在节点上的情况(一次正走就到), 这是唯一的走法
 * 一次折返也必然就够了, 不用讨论更多的曲折, 因为不会比这种走法更省
 * 第一次正走必然走到2 ** (r-1) - 1, 也就是前一个节点是最近的, 因为如果再前面就停, 那么也就是后面第二次正走的时候多走, 不会更少
 * 这里的算法推理起来都是不容易的, 我们尝试所有往回走的步数的情况(因为只尝试回头1步不一定能最后到target), 设为j
 * 第一次正走r-1, 回头1, 反走j, 回头1, 这时候离i的距离是i - (2**(r-1) - 1 - (2**j - 1)) 
 * = i - (l - (2**j - 1)) = i - l + 2**j - 1
*/
class Solution {
public:
    int racecar(int target) {
        vector<int> dp(target+1);
        int r = 1;
        for(int i = 1;i <= target;++i) {
            if(i == pow(2, r) - 1) {
                dp[i] = r;
                r++;
            }else{
                int l = pow(2, r-1) - 1;
                int u = pow(2, r) - 1;
                int temp = INT_MAX;
                for(int j = 0;j < r-1;++j) {
                    temp = min(temp, (r-1) + 1 + j + 1 + dp[i - l - 1 + pow(2, j)]);
                }
                temp = min(temp, r + 1 + dp[u - i]);
                dp[i] = temp;
            }
        }
        return dp[target];
    }
};
    