#include <cmath>
using namespace std;
/**
 * 633. Sum of Square Numbers
 * 这道题实际上是two sum的变种, a**2 + b**2 = c, 这个时候我的第一个想法是a和b肯定要小于c的
 * 那么也就是说, 实际上可以从1到c双指针一下 然后小细节就是可以sqrt一下c因为超过这个数字平方是肯定会加超的
*/
class Solution {
public:
    bool judgeSquareSum(int c) {
        int l = 0, r = static_cast<int>(sqrt(c));
        while(l <= r) {
            long sum = pow(l, 2) + pow(r, 2);
            if(sum == c) return true;
            if(sum < c) ++l;
            else --r;
        }
        return false;
    }
};