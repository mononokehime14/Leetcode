/**
 * 374. Guess Number Higher or Lower
 * 二分搜索target, 不用复习
*/
class Solution {
public:
    int guessNumber(int n) {
        int left = 1, right = n;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int res = guess(mid);
            if(!res) return mid;
            else if(res == -1) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
};