/**
 * 389. Find the Difference
 * 不用复习.
*/
class Solution {
public:
    char findTheDifference(string s, string t) {
        vector<int> counting(26, 0);
        for(char c : s) counting[c-'a']++;
        for(char c : t){
            counting[c-'a']--;
            if(counting[c-'a'] < 0) return c;
        }
        return 'a'; // guaranted answer
    }
};