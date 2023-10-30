/**
 * 459. Repeated Substring Pattern
 * 似乎没有什么更好的办法, 只能每个可能的window检查一下
*/
class Solution {
public:
    bool repeatedSubstringPattern(string s) {
        int n = s.length();
        for(int w = 1; w <= n / 2;++w) {
            if(n % w > 0) continue;
            bool flag = true;
            string target = s.substr(0, w);
            for(int i = w;i < n;i+=w) {
                if(s.substr(i, w) != target) {
                    flag = false;
                    break;
                }
            }
            if(flag) return true;
        }
        return false;
    }
};