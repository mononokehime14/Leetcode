#include <vector>
#include <unordered_map>
using namespace std;

class keyboardRow {
public:
    /**
     * 500. Keyboard Row
     * 纯浪费时间, 除了熟悉一下c++ string中char的处理别的毫无意义
     * 不会有人不知道只需要和前一个比较就能得出整个word中的char是否都在同一行吧
    */
    vector<string> findWords(vector<string>& words) {
        unordered_map<char, int> keyboard = {
            {'q', 0}, 
            {'w', 0},
            {'e', 0},
            {'r', 0},
            {'t', 0},
            {'y', 0},
            {'u', 0},
            {'i', 0},
            {'o', 0},
            {'p', 0},
            {'a', 1},
            {'s', 1},
            {'d', 1},
            {'f', 1},
            {'g', 1},
            {'h', 1},
            {'j', 1},
            {'k', 1},
            {'l', 1},
            {'z', 2},
            {'x', 2},
            {'c', 2},
            {'v', 2},
            {'b', 2},
            {'n', 2},
            {'m', 2}
        };
        vector<string> output;
        for(auto w : words) {
            bool isValid = true;
            for(int i = 1;i < w.size();i++) {
                if(keyboard[tolower(w[i])] != keyboard[tolower(w[i-1])]) {
                    isValid = false;
                    break;
                }
            }
            if(isValid) output.push_back(w);
        }
        return output;
    }
};