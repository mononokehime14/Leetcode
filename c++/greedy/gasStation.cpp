/**
 * 134. Gas Station
 * 看的答案 首先是要想到整体gas - cost > 0必然能走通 很自然会产生疑问 中途会有负数 连续几个负数为什么能顶住
 * 但是一旦整体是正数 这就意味着负数前面能够积累足够的正数以供消耗 所以在这个circular的情况下必然可以
 * 然后是位置 这里一定是sum最小的时候的下一个 这是为什么呢 可以理解为最大化的积攒正数
 * 我们到最小值为止可能会有很多的损失 那么从最小值的下一个开始 在这个circular的情况下就意味着最大程度的累积
 * 积极的条件 这也match之前我们的思路 整体是正数 必然足够
 * 然后就是答案有一个小错误 sum < min_sum是不对的 如果后面gas cost全0不更新 实际上start index要跟着一起跑
*/
class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int n = gas.size(), sum = 0, min_sum = INT_MAX, start_index = 0;
        for(int i = 0;i < n;++i) {
            sum += gas[i] - cost[i];
            if(sum <= min_sum) {
                min_sum = sum;
                start_index = (i+1)%n;
            }
        }
        return sum >= 0 ? start_index : -1;
    }
};