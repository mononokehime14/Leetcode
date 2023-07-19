#include <vector>
#include <string>
using namespace std;
/**
 * 76. Minimum Window Substring
 * 这道题的大概思路是不难想的, 但是细节却十分考验滑动窗口的水平. 思路肯定是滑动窗口, 然后保持一个当前窗口内和target
 * match的set记录. 滑动窗口的题目要考虑两个大思路, 右指针什么时候扩张, 左指针什么时候收缩, 并保证如此运行不会错过正确答案
 * 这里右指针应该一直扩张, 区别只在于我们会检查新加进来的char需不需要. 这里分三种情况, 一种是char不在target里出现, 那这样
 * 这个新人没有任何的影响可以直接下一位;第二种是需要, 但是我们已经有充足的该char了, 这个时候我们要记录窗口里char出现的次数
 * 这个是肯定的, 但是其实不需要做出什么反应, 因为前面的某一个解一定要小于这个解, 换言之这个人是多余的其实. 所以valid cound不必加;
 * 第三种自然是正好需要
 * 
 * 左指针在我们valid count满足条件的时候收缩, 看看能不能缩小window. 同样的道理, 多余的char去掉后可以继续收缩, 但是到了需要的那个
 * 底线, 也就是valid count这里, 就要停止了.
*/
class Solution {
public:
    string minWindow(string s, string t) {
        vector<int> needs(128, 0);
        vector<bool> has(129, false);

        for(char c : t) {
            has[c] = true;
            ++needs[c];
        }

        int l = 0, r = 0, size = s.length(), valid = 0, require=t.length(), min_len=size+1, min_l = 0;
        while(r < size) {
            if(has[s[r]]) {
                if(--needs[s[r]] >= 0) {
                    ++valid;
                }
                while(valid >= require) {
                    if(r-l+1 < min_len) {
                        min_len = r-l+1;
                        min_l = l;
                    }
                    if(has[s[l]] && ++needs[s[l]] > 0) {
                        --valid;
                    }
                    ++l;
                }
            }
            ++r;
        }
        return min_len > size ? "" : s.substr(min_l, min_len);
    }
};