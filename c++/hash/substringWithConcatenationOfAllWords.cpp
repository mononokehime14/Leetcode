/**
 * 30. Substring with Concatenation of All Words
 * 这道题最后还是暴力方法的某种优化实现 首先我的思路是对于每一个window进行检查 检查可以跳着查substr
 * 但是这里由于每次检查words_map都要重置 会TLE
 * 一个优化的实现是如果当前的window不行就把前面更新的words_map恢复回来
 * 但是如果仍然是滑动窗口 那么每次的sub word就是不一样的 不好恢复words_map
 * 所以采用这样一种循环的方法: start position从0-word_len, 然后从start position开始往右一段一段试 (可以看作滑动窗口)
 * 分三种情况 当前word不在words 则任何以这个word结尾的都不行 将start直接移到cur 状态清零
 * 当前word之前用完了 则找到上一个和当前word相同的word 跳过它 相当于当前的word被算进去
 * 当前的word可以用 则更新words_map 这时候如果凑成了一个substring则start右移开始新的尝试
*/
class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        unordered_map<string, int> words_map;
        int w_size = words.size(), word_len = words[0].size(), s_len = s.length();
        vector<int> answer;
        int k = w_size * word_len;
        if(s_len < k) return answer;
        for(const string & word : words) ++words_map[word];
        for(int i = 0;i < word_len;++i) {
            int start = i, cur = i, n_words = w_size;
            while(start + k <= s_len) {
                string current_chop = s.substr(cur, word_len);
                if(words_map.find(current_chop) == words_map.end()) {
                    for(;start < cur;start += word_len) {
                        ++words_map[s.substr(start, word_len)];
                    }
                    start += word_len;
                    n_words = w_size;
                }else if(words_map[current_chop] == 0) {
                    for(;s.substr(start, word_len) != current_chop; start += word_len) {
                        ++words_map[s.substr(start, word_len)];
                        ++n_words;
                    }
                    start += word_len; // 跳过该相同的word 就当现在这个word被算进去 不用更改words map
                }else{
                    --words_map[current_chop];
                    if(--n_words == 0) {
                        answer.push_back(start);
                        ++words_map[s.substr(start, word_len)];
                        start += word_len;
                        ++n_words;
                    }
                } 
                cur += word_len;
            }
            for(; start < cur;start+=word_len) ++words_map[s.substr(start, word_len)]; // 复原右边剩下的words
        }
        return answer;
    }
};