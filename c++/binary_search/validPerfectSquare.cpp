/**
 * 367. Valid Perfect Square
 * 二分查找的经典用法, 尝试正确答案
 * 这里套用的是搜索target的模版
 * 
 * 而专业的sqrt则应该使用牛顿迭代法 这里不甚理解
*/
class Solution {
public:
    bool isPerfectSquare(int num) {
        int left = 1, right = num;
        while(left <= right) {
            long mid = left + (right - left) / 2;
            if(mid * mid == num) return true;
            else if(mid * mid > num) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }
};