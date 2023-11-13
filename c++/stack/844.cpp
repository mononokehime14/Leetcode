/**
 * 844. Backspace String Compare
 * 非常经典的思路, 虽然是easy题目
 * 如果要保持一定的运算顺序, 就要考虑stack
*/
class Solution {
public:
    bool backspaceCompare(string s, string t) {
        stack<int> s_s, t_s;
        for(char c : s) {
            if(c == '#') {
                if(!s_s.empty()) s_s.pop();
            }else {
                s_s.push(c);
            }
        }
        for(char c : t) {
            if(c == '#') {
                if(!t_s.empty()) t_s.pop();
            }else {
                t_s.push(c);
            }
        }
        if(s_s.size() != t_s.size()) return false;
        while(!s_s.empty()) {
            if(s_s.top() != t_s.top()) return false;
            s_s.pop();
            t_s.pop();
        }
        return true;
    }
};