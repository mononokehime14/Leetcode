#include <vector>
using namespace std;

/**
 * 455 Assing Cookie
 * 一道简单的贪心, 只要考虑把最小的先尝试给最小的小孩, 不行它也无法满足更大的小孩
 * 有意思的是我一开始想到的是反着来的, 也就是最大的尝试给最大的小孩, 这两个应该是一个意思
 * 速度上前者更快, 应该是少了count的操作, 当然后者也可以不要count return的时候减一下就好了
*/
class assignCookie {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(), g.end());
        sort(s.begin(), s.end());
        int child = 0, cookie = 0;
        while(child < g.size() && cookie < s.size()) {
            if(g[child] <= s[cookie]) child++;
            cookie++;
        }
        return child;
    }
    // int findContentChildren(vector<int>& g, vector<int>& s) {
    //     sort(g.begin(), g.end(), greater<int>());
    //     sort(s.begin(), s.end(), greater<int>());
    //     int count = 0, child = g.size() - 1, cookie = s.size() - 1;
    //     while(child >= 0 && cookie >= 0) {
    //         if(s[cookie] >= g[child]) {
    //             count++;
    //             child--;
    //         }
    //         cookie--;
    //     }
    //     return count;
    // }
};