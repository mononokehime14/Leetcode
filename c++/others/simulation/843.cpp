/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Master {
 *   public:
 *     int guess(string word);
 * };
 */
/**
 * 843. Guess the Word
 * 每一次猜测可以知道有多少不同, 找出和guessword的不同字母数量一样的string, 这些就是可能的candidates
*/
class Solution {
public:
    int countMatches(const string& a, const string& b) {
        int count = 0;
        for(int i = 0;i < 6;++i) {
            if(a[i] == b[i]) count++;
        }
        return count;
    }
    void shrinkList(vector<string>& words, int matches, string& guess) {
        vector<string> new_list;
        for(string& s : words) {
            if (guess != s && countMatches(guess, s) == matches) {
                new_list.push_back(s);
            }
        }
        words = new_list;
    }
    void findSecretWord(vector<string>& words, Master& master) {
        string guess;
        int matches = 0;
        while(matches != 6){
            guess = words[words.size()/2];
            matches = master.guess(guess);
            if(matches == -1) matches = 0;
            shrinkList(words, matches, guess);
        }
    }
};