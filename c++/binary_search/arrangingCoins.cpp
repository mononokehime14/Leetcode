/**
 * 441. Arranging Coins
 * 没想到, 确实是二分查找尝试答案. 一开始还觉得就是while不断地减
*/
class Solution {
public:
    int arrangeCoins(int n) {
        int left = 1, right = n - 1;
        while(left <= right) {
            long mid = left + (right - left) / 2;
            long tmp = (mid) * (mid + 1) / 2;
            if(tmp > n) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return right == 0 ? 1 : right;
    }
};