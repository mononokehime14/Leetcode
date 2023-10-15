/**
 * 400. Nth Digit
 * 很烦, 但是思路又不得不去学习这个pattern 一旦发现这种以digits数量做分类的题型
 * 1 - 9, 10 - 99, 100 - 999, 可以参照这个代码, base * digits不断消下去
*/
class Solution {
public:
    int findNthDigit(int n) {
        int digits = 1;
        long long base = 1;
        // 找出是在哪一组里
        while(n - base * digits * 9 > 0) {
            n -= base * digits * 9;
            base *= 10;
            digits++;
        }
        int index = (n - 1) % digits; // 在目标数字中的哪一个digit
        long long val = base + (n - 1) / digits; // 目标数字, 从base开始

        return to_string(val)[index] - '0';
    }
};