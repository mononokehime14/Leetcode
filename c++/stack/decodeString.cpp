/**
 * 394. Decode String
 * 自己做出来的, 和C++书上非常接近
 * 这道题和basic calculator是一个思路, 就是利用stack + recursion, 进行从左到右保证顺序的计算
 * 我对这种的基本思路是对open bracket入栈, close bracket pop下来运算
 * 这道题有一个非常有意思的trick, 3[a2[c]], 2[c]很容易算成cc, 但是如何让3[]算得时候里面是acc呢
 * 我们维持一个current, 这个在每次遇到[的时候清空, 这样我们就能在[]计算中使用最近的一段正确的string
 * 清空之前, 我们把当前的current也push到栈上, 就像prefix一样
*/
class Solution {
public:
    string decodeString(string s) {
        int n = s.length();
        stack<pair<string, int>> sk; // [ , position, repeats
        string current;
        for(int i = 0;i < n;++i) {
            if(s[i] == ']') {
                string inner = decodeString(current);
                cout << current << " recursion gives " << inner;
                current = sk.top().first;
                for(int r = 0;r < sk.top().second;++r) {
                    current += inner;
                }
                sk.pop();
            }else if(isdigit(s[i])) {
                int start = i, count = 1;
                while(isdigit(s[++i])) ++count;
                sk.push(make_pair(current, stoi(s.substr(start, count))));
                current = "";
            }else{
                current += s[i];
            }
        }
        return current;
    }
};