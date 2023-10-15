/**
 * 387. First Unique Character in a String
 * 不用复习.
*/
class Solution {
public:
    int firstUniqChar(string s) {
        vector<int> count(26, 0);
        for(char c : s) count[c - 'a']++;
        for(int i = 0;i < s.length();++i) if(count[s[i]-'a'] == 1) return i;
        return -1;
    }
};