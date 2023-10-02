/**
 * 201. Bitwise AND of Numbers Range
 * 这道题我想到的是取决于left right的间隔 对于AND逻辑来说 出现任何一个0都会清零
 * 在第一位上, 0每隔一位出现, 在第二位上, 每隔两位出现, 以此类推
 * 这样我们就循环检查就可以了 但是这个思路的实现代码可能有点问题
 * 
 * 这是光荣的正确思路 但是还需要再一步发现 考虑1010 - 1111
 * 那么最后3位必然会全部清零 一开始会觉得这不科学 但是实际上确实如此 它们的common prefix停止在第4位
 * 实际上意味着它们之间没有8的间隔 但是任何小于8的间隔(1, 2, 4)都存在 这样的话也就意味着后三位必然会有0出现
 * 这样直接停在common prefix就行了 后面全部清零 太妙了
*/
class Solution {
public:
    int rangeBitwiseAnd(int left, int right) {
        int right_part = 0;
        while(left != right) {
            left >>= 1;
            right >>= 1;
            ++right_part;
        }
        return left << right_part;
        // if(left == right) return left;
        // uint32_t answer = 0;
        // long exp = 1;
        // for(int i = 0;i < 32;i++) {
        //     answer >>= 1;
        //     if(right - left >= exp || right % exp == 0 || left % exp == 0) {
        //         answer &= ~(1 << 31);
        //     }else{
        //         answer |= (1 << 31);
        //     }
        //     exp *= 2;
        // }
        // return answer;
    }
};