#include <vector>
#include <string>
#include <algorithm>
using namespace std;
/**
 * 524. Longest Word in Dictionary through Deleting
 * 这道题matching需要保持原有的order, 所以不可以只数character的appearance, 而是需要正儿八经的并行双指针检查
 * 然后就是不需要对dictionary排序, 排序的成本是nlogn, 这就还不如扫一遍
 * 小优化就是ans和t的size多次被计算, 就存在int里了, 事实证明速度有很大提升, 说明leetcode c++的编译还无法搞定这个
*/
class Solution {
public:
    string findLongestWord(string s, vector<string>& dictionary) {
        // sort(dictionary.begin(), dictionary.end(), [](string a, string b){
        //     if(a.length()==b.length()) return a < b;
        //     return a.length()>b.length();
        // });
        string ans = "";
        int ans_size = 0;
        for(string t : dictionary) {
            if(singleCheck(s, t)) {
                int t_size = t.length();
                if(t_size > ans_size || (t_size == ans_size && t < ans)) {
                    ans=t;
                    ans_size = t_size;
                }
            }
        }
        return ans;
    }
    bool singleCheck(string s, string t) {
        int p1 = s.length()-1, p2 = t.length()-1;
        while(p1 >= 0 && p2 >= 0) {
            if(s[p1] == t[p2]) {
                --p2;
            }
            --p1;
        }
        return p2 < 0;
    }
};