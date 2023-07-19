#include <string>
using namespace std;
/**
 * 680. Valid Palindrome II
 * palindrome的检查时左右双指针, 这道题的问题在于, delete一次, 是删左边还是右边呢
 * 我一开始想过, 直接检查删除后的string, 但是因为要写一个helper觉得不应该这么麻烦就挂在那里
 * 然后去思考判断l+1和r-1是不是匹配 可是事实就是就算l+1和r匹配, 这样不一定是正确的删法
 * 所以正确的答案还是检查删除后的substring, 当然你不必真的删除就是了
*/
class Solution {
public:
    bool validPalindrome(string s) {
        int l = 0, size = s.length(), r = size-1;
        while(l < r) {
            if(s[l] != s[r]) return subCheck(s, l+1, r) || subCheck(s, l, r-1);
            l++;
            r--;
        }
        return true;
    }
    bool subCheck(string s, int left, int right) {
        while(left < right) {
            if(s[left] != s[right]) return false;
            left++;
            right--;
        }
        return true;
    }
};