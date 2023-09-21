/**
 * 58. Length of Last Word
 * 没有意义 太简单的遍历
*/
class Solution {
public:
    int lengthOfLastWord(string s) {
        int right = s.length() - 1, count = 0;
        while(right >= 0 && s[right] == ' ') --right;
        while(right >= 0 && s[right] != ' ') {
            --right;
            ++count;
        }
        return count;
    }
};