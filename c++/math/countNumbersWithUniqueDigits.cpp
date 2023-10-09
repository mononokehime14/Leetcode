/**
 * 357 Count Numbers with Unique Digits
 * 这道题就是对于之前n-1的数字, 每个我可以加上一个不一样的数字
 * 如果前面一层是n=1, 每个数字可以配8个其他数字, 加上自己就是9种可能
 * 后面依次类推
*/
class Solution {
public:
    int adder(int n) {
        int res = 9, base = 9;
        while(--n) res *= base--;
        return res;
    }
    int countNumbersWithUniqueDigits(int n) {
        return n ? adder(n) + countNumbersWithUniqueDigits(n-1) : 1;
    }
};