/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * minimumDeletionsToMakeCharacterFrequenciesUnique
 * 先数处frequency, 然后对于每一个frequency, 如果没有被前面占据, 就insert到set里
 * 前面出现过, 就消减直到没有出现过
 * 这里的时间复杂度分析要注意, 第二个loop最多只会跑26次, 因为最后只有26个被占据的frequency
 * 所以这个出来count的O(N), 剩余的是26 * 26.
*/
class Solution {
public:
    int minDeletions(string s) {
        vector<int> counting(26, 0);
        unordered_set<int> used;
        int answer = 0;
        for(char c : s) counting[c-'a']++;
        for(int i = 0;i < 26;++i) {
            char current = 'a' + i;
            int freq = counting[i];
            while(freq > 0 && used.find(freq) != used.end()) {
                --freq;
                answer++;
            }
            used.insert(freq);
        }
        return answer;
    }
};