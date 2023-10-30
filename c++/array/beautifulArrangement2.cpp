/**
 * 667. Beautiful Arrangement II
 * 这道题首先是认识到, 我们可以用前k+1个数字组成满足要求的数组, 而这个数字是一定小于等于n的
 * 但是怎么凑这个数组, 我的想法是间距 1 2 3 4 .. k, 但是这种思路比较难搞, 数值上根本不知道要到什么地步
 * 而正确的方法是波浪型的往中间收敛, 1, k+1, 2, k, ... 这样第一个间距k, 第二个k-1..直到k/2, k/2+1
 * 完美的一个arrangement. 而且这样排列, 我们确定数值上固定使用[1, k+1], 后面的补齐就太方便了
*/
class Solution {
public:
    vector<int> constructArray(int n, int k) {
        vector<int> answer(n);
        answer[0] = 1;
        for(int i = 1, interval = k;i < k + 1;--interval, ++i) {
            answer[i] = i % 2 == 0 ? answer[i-1] - interval : answer[i-1] + interval;
        }
        for(int i = k + 1;i < n;++i) {
            answer[i] = i + 1;
        }
        return answer;
    }
};