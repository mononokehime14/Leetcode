/**
 * 345. Reverse Vowels of a String
 * 不用复习
*/
class Solution {
public:
    string reverseVowels(string s) {
        int left = 0, right = s.size()-1;
        unordered_set<char> st = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        while (left<right) {
            while(left < right && st.find(s[right]) == st.end()) right--;
            while(left < right && st.find(s[left]) == st.end()) left++;
            if(left < right) swap(s[left++], s[right--]);
        }
        return s;
    }
};