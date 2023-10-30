/**
 * 218. The Skyline Problem
 * theSkylineProblem
 * C++书上提供了比原先的java解法更快的O(NlogN)解法, 虽然大O是一样的, 但是实测上要快
 * 首先分析原java解法, 脱胎于差分数组, 数组记录的是位置上的+height和-height
 * 这样我们sort数组然后循环一遍, 每一次位置变化时相应更新PQ. +height的时候推上去, -height的时候拿下来相应的,
 * 相当于用PQ保持当前的height, 一旦出现变化(和prev比较)就可以记录.
 * 一定要用PQ的原因是当前的最高值pop下来之后, 得第二高的顶上, 所以不能只用一个variable, 只能用PQ保持
 * 
 * 这个新方法不需要新建数组sort, 直接用原数组 (原数组也是根据coordinates sort好的)
 * 两个方向, 第一什么时候加入pq. 当pq为空或者当前building的左边界小于当前pq top的右边界,
 * 我的理解就是这个building还和之前的buildings处在一坨里, 这个时候我们推上去, 然后如果高度变化的话, 就可以记录一个答案
 * 这里有一个小细节, 把所有之后左边界相同的一起加进来, 因为不然无法正确得到这个位置上真正的最高点.
 * 第二个什么时候pop, 就是出现当前building左边界大于的时候, 这个时候后面的building就和前面的那一坨完全无关了,
 * 我们可以把前面那一坨处理掉. 这里主要处理的下降的那些天际线, 因上升的那些前一个部分搞过了.
 * 对于当前height最高的, 任何比它右边界小的, 我们都不用管了; 而比它右边界大的, 就是pop当前之后要顶上去的.
 * 所以这里我们pop掉右边界>=当前最高的右边界, 记录转折, 然后不动index, 下一次进来, 就是处理顶上来的了.
 * 这样一直到pq清空, 也就是前面一坨完全处理完毕.
*/
class Solution {
public:
    vector<vector<int>> getSkyline(vector<vector<int>>& buildings) {
        vector<vector<int>> answer;
        priority_queue<pair<int, int>> pq; // (height, right), default最大堆
        int i = 0, n = buildings.size(), cur_left, cur_height;
        while(i < n || !pq.empty()) { // 即便buildings循环完了pq如果还有值也是要清理的
            if(pq.empty() || (i < n && buildings[i][0] <= pq.top().second)) {
                cur_left = buildings[i][0];
                while(i < n && buildings[i][0] == cur_left) {
                    pq.emplace(buildings[i][2], buildings[i][1]);
                    i++;
                }
            }else{
                cur_left = pq.top().second;
                while(!pq.empty() && pq.top().second <= cur_left) pq.pop(); 
            }
            cur_height = pq.empty() ? 0 : pq.top().first;
            if(answer.empty() || cur_height != answer.back()[1]) answer.push_back({cur_left, cur_height});
        }
        return answer;
    }
};