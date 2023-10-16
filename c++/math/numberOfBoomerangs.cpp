/**
 * 447. Number of Boomerangs
 * 这道题我给想复杂了 我想这不得把以i为中心 ij间距为半径的圆的表达式集中 然后再跑一遍
 * 事实上这种题, 不要想O(N)了, 直接以i为中心, 所有其他的j的间距算一遍, count重复的
 * 这里只要间距相同, 必然就是能自由组合的, 比如i j1 j2, i j2 j1, i j3 j2...
 * 这样的话, 如果有n个相同的, 组合就是n * (n-1)
*/
class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int answer = 0, n = points.size();
        for(int i = 0;i < n;++i) {
            unordered_map<long, int> count;
            for(int j = 0;j < n;++j) {
                if(i == j) continue;
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                count[x*x + y*y]++;
            }
            for(auto [k, v] : count) {
                if(v > 1) answer += v * (v - 1);
            }
        }
        return answer;
    }
};