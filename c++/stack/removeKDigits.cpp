/**
 * 402. Remove K Digits
 * 精妙的思路, 这道题我土鳖的想要回溯, 尝试每一种可能
 * 实际上, 这是有规律的, 也就是说, 当前的c前面如果有更大的, 尝试移除,直到k用完
 * 原理是尽可能的让most significant高位变小
 * 这样, 我们就从左到右保持顺序, 使用stack 遇到栈顶比自己大的尝试pop掉
*/
class Solution {
public:
    string removeKdigits(string num, int k) {
        string answer = "";
        for(char c : num) {
            while(!answer.empty() && k && answer.back() > c) {
                answer.pop_back();
                --k;
            }
            if(!(answer.empty() && c == '0')) answer.push_back(c); //检查是为了防止0放到最前面
        }
        while(!answer.empty() && k) { // 这一步是因为有可能digits升序, 那这个时候从后面删除能够得到最大
            answer.pop_back();
            --k;
        }
        return answer.empty() ? "0" : answer;
    }
};