/**
 * 89. Gray Code
 * 这道题的规律非常妙 我们考虑n=3的情况 前一半可以用n=2时候的数列 然后最前面加个1 再翻过来
 *  00
 *  01
 *  11
 *  10
 * 110
 * 111
 * 101
 * 100
 * 这个规律就妙在10 -> 110必然合法 从这里开始进入n=3多出来的那个bit上 然后再利用n=2现成的序列 后半部分也必然成功
 * 我觉得应该算是dp 从n=0 n=1的base开始 这里C++书里的实现是top down recursion
*/
class Solution {
public:
    vector<int> grayCode(int n) {
        if(!n) return vector<int>{0};
        if(n == 1) return vector<int>{0,1};
        vector<int> prev = grayCode(n-1);
        int base = 1 << (n - 1); // 2 ** n - 1, half point
        for(int i = 0;i < base;++i) prev.push_back(prev[base - i - 1] + base);
        return prev;
    }
};