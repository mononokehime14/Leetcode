/**
 * 453. Minimum Moves to Equal Array Elements
想复杂了, 我想要一个pq, 然后找出min, min先变成当前的max, 当前的max pop, 下一个加上我们补的值, 看看是否和min一样
这个思路本质是模拟, 实现是很复杂的
这里的解法是累积所有的和min的差就可以了
比如, 4 3 2 1, 1一定要至少补到4, 那么这里就是4和1的差值3, 当前4 6 5 4
4至少要补到新的max6, 这里就正好等于5之前的差值, 同理可得最后一步
数学原理未知, 规律倒是确实如此
*/
class Solution {
public:
    int minMoves(vector<int>& nums) {
        int min_val = INT_MAX, answer = 0;
        for(int n : nums) min_val = min(min_val, n);
        for(int n : nums) answer += n - min_val;
        return answer;
    }
};