#include <vector>
using namespace std;
/**
 * 452 Minimum Number of Arrows to Burst Balloons
 * 这道题的贪心思路确实是和non-overlapping Interval一样, 之前用java刷的时候可能没有完全明白
 * 事实上, 第一步是要稍微转换一下思路, 用最少的箭实际上等价于一支箭射穿更多的气球
 * 这一步就像是non-overlapping interval中 最少移除的interval等价于每次移除腾出尽可能多的空间
 * 那么如何让一支箭射穿更多的气球 答案不是从在那个坐标上射思考 而是思考射向哪个气球
 * 如果我们射向右边界更大的气球, 那么这个气球overlap其他气球一同射穿的可能性就更高
*/
class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        if(points.empty()) return 0;
        sort(points.begin(), points.end(), [](vector<int>& a, vector<int>& b){
            return a[1] < b[1];
        });
        int prev = points[0][1], count = 1;
        for(int i = 1;i < points.size();i++) {
            if(points[i][0] > prev) {
                count++;
                prev = points[i][1];
            }
        }
        return count;
    }
};