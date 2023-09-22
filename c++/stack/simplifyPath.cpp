/**
 * 71. Simplify Path
 * 这种类似运算符的演算 一副要用stack的样子 事实上就是如此
 * 主要原因是..这个操作符方便把前一个直接拉下来 传统意义上的queue的pop是从头上的 双向链表当然没问题
 * 这里我取巧了 因为觉得最后output的时候还是顺着比较好 所以用了vector vector的pop尾部是O(1)的
*/
class Solution {
public:
    string simplifyPath(string path) {
        if(!path.length() || path[0] != '/') return "";
        vector<string> q;
        int index = 0, size = path.length();
        while(index < size) {
            int word_len = 0, right = index + 1;
            while(right < size && path[right] != '/') {
                ++right;
                ++word_len;
            }
            if(!word_len) {
                index = right;
                continue;
            }
            string word = path.substr(index+1, word_len);
            // cout << word << " ";
            if(word == ".") {
            }else if(word == "..") {
                if(!q.empty()) q.pop_back();
            }else{
                q.push_back(word);
            }
            index = right;
        }
        if(q.empty()) return "/";
        string answer;
        for(const string &word : q) answer = answer + "/" + word;
        return answer;
    }
};