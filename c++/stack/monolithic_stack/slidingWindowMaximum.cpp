#include <vector>
using namespace std;
/**
 * 239. Sliding Window Maximum
 * 思路可以参照java 可以这样想: 首先要记录窗口内的最大值这一定是某种队列或者数据结构 不然左边最大值滚了之后不知道下一个最大值是多少
 * pq是不合适的因为左边如果不是最大值 还得花力气找出来删掉 那么可以用单调栈 小的清算大的塞入 一直保留最大的值
 * 由于窗口移动的缘故 不像之前的单调栈我们不需要考虑顶部怎么变化 这里顶部可能出窗口 所以要用一个deque
*/

class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> answer;
        deque<int> dq;
        for(int i = 0;i < nums.size();i++) {
            //pop max if it left is max
            if(!dq.empty() && dq.front() == i - k) { //顶部是最大值
                dq.pop_front();
            }
            // pop back if smaller: useless
            while(!dq.empty() && nums[dq.back()] < nums[i]) dq.pop_back();
            dq.push_back(i);
            if(i >= k - 1) answer.push_back(nums[dq.front()]);
        }
        return answer;
    }
};