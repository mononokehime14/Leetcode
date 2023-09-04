#include <vector>
using namespace std;
/**
 * 417. Pacific Atlantic Water Flow
 * dfs复健 这道题的dfs总体思路很明晰 主要是visited怎么去搞
 * 如果我们从最高点开始 那么visit一个cell不代表最后就能到边上 所以是否能到边上这个状态最后需要传递上来
 * 那么visited的cell return什么呢 所以visited要记录状态(能否到达海洋) 最终决定visited 0 1 2三种值
 * 但是反正都是从边上传递上来的 为什么不直接从海洋开始呢
 * 答案就是直接从海洋开始 去visit能visit的cell 这里visited只需要bool 因为visited过的就是满足条件的 visited和状态是一致的
*/
class Solution {
public:
    vector<vector<int>> direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& g) {
        int m = g.size(), n = g[0].size();
        vector<vector<bool>> visitedPacific(m, vector<bool>(n, false));
        vector<vector<bool>> visitedAtlantic(m, vector<bool>(n, false));
        for(int i = 0;i < m;i++) {
            dfs(g, visitedPacific, i, 0);
            dfs(g, visitedAtlantic, i, n-1);
        }
        for(int j = 0;j < n;j++) {
            dfs(g, visitedPacific, 0, j);
            dfs(g, visitedAtlantic, m-1, j);
        }
        vector<vector<int>> answer;
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                if(visitedPacific[i][j] && visitedAtlantic[i][j]) answer.push_back(vector<int>{i, j});
            }
        }
        return answer;
    }
    
    void dfs(vector<vector<int>>&g, vector<vector<bool>> &visited, int i, int j) {
        if(visited[i][j]) return; // visited
        visited[i][j] = true;
        for (int d = 0; d < 4; ++d) {
            int new_i = i + direction[d][0], new_j = j + direction[d][1];
            if(new_i < 0 || new_i >= g.size() || new_j < 0 || new_j >= g[0].size()
                || g[new_i][new_j] < g[i][j]) continue;
            dfs(g, visited, new_i, new_j);
        }
    }
};