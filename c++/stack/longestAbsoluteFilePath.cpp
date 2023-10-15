/**
 * 388. Longest Absolute File Path
 * 非常精妙 最重要要利用的条件是给的system format是dfs的 也就是会一条路径到底
 * 这里我们就可以利用stack 按照level推上去 在一条路径到底部(file)了之后
 * 下一个路径将高于或者同等级的先剃掉, 保证了正确性
 * 重点复习
*/
class Solution {
public:
    int lengthLongestPath(string str) {
        str += '\n';
        bool is_file = false;
        int cur_len = 0, cur_path_len = 0, answer = 0, level = 0;
        stack<pair<int, int>> s;
        for(int i = 0;i < str.length();++i) {
            if(str[i] != '\n') {
                if(str[i] == '\t') level++;
                else if(str[i] == '.') {
                    is_file = true;
                    cur_len++;
                }else{
                    cur_len++;
                }
                continue;
            }
            while(!s.empty() && level <= s.top().second) {
                cur_path_len -= s.top().first;
                s.pop();
            }
            if(is_file) answer = max(answer, cur_path_len + cur_len); // +1 for /
            else{
                s.push(make_pair(cur_len + 1, level));
                cur_path_len += cur_len + 1;
            }
            is_file = false;
            cur_len = level = 0;
        }
        return answer;
    }
};