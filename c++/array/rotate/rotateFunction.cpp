/**
 * 396. Rotate Function
 * 太精妙了 通常这种rotate array就是把前一部分放到后面
 * 这里利用了一点, 在基础的weighted sum上, 把第一个数字换到最后
 * 等同于其余数字的系数纷纷-1, 该数字 * n是考虑到直接-sum, 所以减完刚好(n-1)
 * 这样我们就通过这个操作 神奇的尝试了所有的加权和 这是rotate的特殊情况导致的
*/
class Solution {
public:
    int maxRotateFunction(vector<int>& nums) {
        int n = nums.size();
        long long weighted_sum = 0, sum = 0;
        for(int i = 0;i < n;++i) {
            weighted_sum += i * nums[i];
            sum += nums[i];
        }
        long long answer = weighted_sum; // 考虑负数, 这里不能为0
        for(int i = 0;i < n;++i) {
            weighted_sum += nums[i] * n - sum;
            answer = max(weighted_sum, answer);
        }
        return answer;
    }
};