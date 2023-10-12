/**
 * 318 Maximum Product of Word Lengths
 * 这道题虽然使用hashmap 但是实际上用vector也是一样
 * 核心和brute force并无两样 但是用位运算做了优化
 * 用bit来表示characters, 这样每个word只循环一遍characters 比较非常快速
*/
class Solution {
public:
    int maxProduct(vector<string>& words) {
        unordered_map<int, int> map;
        int answer = 0;
        for(const string& word : words) {
            int encode = 0, current_size = word.length();
            for(char c : word) encode |= (1 << (c - 'a'));
            map[encode] = max(map[encode], current_size); //一样的encode取最大的
            for(const auto& [k, v] : map) {
                if(!(encode & k)) {
                    answer = max(answer, v * current_size);
                }
            }
        }
        return answer;
    }
};