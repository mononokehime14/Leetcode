/**
 * 395. Longest Substring with At Least K Repeating Characters
 * 这是一道非常诡异的滑动窗口题目, 要求longest window, 我想不出这个滑动窗口应该怎么搞
 * 结果是尝试26种可能, window里可能有1, 2, 3..种不同的字母
 * 每一次尝试, 右指针扩张, 左指针收缩直到不同的字母数量满足要求. 这时候对count再做一次检查, 确保频率>k再更新
 * 我们知道滑动窗口的问题在于, 不在特殊的window条件下, 就会漏掉正确解. 这里确实逻辑上是涵盖所有正确解的.
 * 因为每一次尝试可能的时候, 比如找窗口中只有一个repeating k次的字母, 这时候左指针收缩才是合理的, 因为窗口中可能囊括多个repeating k次的字母
 * 不然的话, 一次滑动窗口找所有的解, 左指针凭什么收缩
 * 
 * 这题也可以用recursion, 即碰到一个频率低于k的, 说明不可能出现在正确的窗口里, 那么左右各自再检查
*/
class Solution {
public:
    int longestSubstring(string s, int k) {
        int n = s.length(), answer = 0;
        for(int num_uniq = 1; num_uniq <= 26; ++num_uniq) {
            vector<int> count(26, 0);
            int cur_uniq = 0, left = 0;
            for(int right = 0;right < n;++right) {
                char c = s[right];
                if(count[c-'a'] == 0) ++cur_uniq;
                count[c-'a']++;

                while(cur_uniq > num_uniq) {
                    char l_c = s[left++];
                    count[l_c-'a']--;
                    if(count[l_c-'a'] == 0) cur_uniq--;
                }

                bool valid = true;
                for(int i = 0;i < 26;++i) {
                    if(count[i] > 0 && count[i] < k) {
                        valid = false;
                        break;
                    }
                }
                if(valid) answer = max(answer, right - left + 1);
            }
        }
        return answer;
    }
};