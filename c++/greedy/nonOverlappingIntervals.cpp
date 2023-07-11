#include <vector>
using namespace std;

/**
 * 435 Non-overlapping Intervals
 * 终于好像懂了这道题的贪心思路, 首先我认为有一个总体思想是没错的, 也想到了, 就是把占地大的滚了, 这样才能腾出地方给更多的
 * 但是一旦发生overlap 如果按照interval长度来remove是错误的, 想象一个interval在上面, 区域内还有三个interval
 * 这个interval当然应该滚, 但是如果下面最左边的interval更长, 那它就滚不掉了.
 * 所以这里如果我们让右边届更大的滚, 该interval滚了之后, 其余没有interval, 就对了
 * 所以, 贪心的思路应该是overlap interval中, 让右边界更大的滚, 这样才能腾出更多的空间
 * 
 * 在实现中, 注意我们不需要实际删除掉remove的, 只要不考虑它就行了, 不考虑as in不更新prev. 
*/
class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        if(intervals.empty()) return 0;
        // custome sort using lambda, [captures] (arguments) -> return type {body}
        sort(intervals.begin(), intervals.end(), [] (vector<int>& a, vector<int>& b) -> bool
        {
            return a[1] < b[1];
        });
        int size = intervals.size(), prev = intervals[0][1], remove = 0;
        for(int i = 1;i < size;i++) {
            if(intervals[i][0] < prev) { // overlap
                remove++;
            }else{
                prev = intervals[i][1];
            }
        }
        return remove;
    }
};