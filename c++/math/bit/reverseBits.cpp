/**
 * 190. Reverse Bits
 * 虽然是easy题目 但是还是很妙的
 * 我们知道 想要补一位数字到某个位置 要用|= (x << position) 这样不会影响其他位置
 * 而要消除某一位置上的1 要用 &= ~(1 << position) 这样不会影响其他位置
 * 这里reverse的实现实际上就是暴力思路 用一个新数字一位一位推进去 新数字不断左shift n不断右shift 重复32次
*/
class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t answer = 0;
        for(int i = 0;i < 32;++i) {
            answer <<= 1;
            answer |= (n & 1); //将最右边一位推上去
            n >>=1;
        }
        return answer;
    }
};