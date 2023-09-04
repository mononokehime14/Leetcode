#include <vector>
using namespace std;

/**
 * 451. Sort Characters By Frequency
 * counting sort没什么好说的 可以直接在原string上一个个重写
 * C++ map sort checkpoint
 * 和java一样最好也是转换成pair vector
 * map当然本身就是ordered (by key) 要写custom comparator也可以 可以用multimap
*/
class Solution {
public:
    string frequencySort(string s) {
        int size = s.length();
        unordered_map<char, int> count;
        for(const char c : s) {
            count[c]++;
        }
        vector<pair<char, int>> count_vec(count.begin(), count.end());
        sort(count_vec.begin(), count_vec.end(), [](const pair<char, int> &a, const pair<char, int> &b){
            return a.second > b.second;
        });
        int index = 0;
        for(auto [k, v] : count_vec) {
            for(int i = 0;i < v;i++) {
                s[index++] = k;
            }
        }
        return s;
    }
};