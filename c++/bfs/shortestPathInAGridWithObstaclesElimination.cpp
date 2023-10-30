/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * 重点复习, 这里一开始总是想着, 既然终点固定, 不如用DFS. 实际上, 任何minumum path的题目都要先仔细考虑bfs, 这里bfs是完全可行的
 * 这里重要的思路我是能想清楚的, 就是k的状态是需要memorization的. 如果我们两次进入1, 1这个cell, 带着同样的k, 那么结果不会有任何改变
 * 所以这里就是top-down dp (memorization)和bfs的搭配了 这是一个绝妙的模版.
 * 可以看到我们这里把memorization和visited合并了, -1用来记录没有visited, 如果visited, 则记录k的值
 * 这里有一个特殊的考虑, 我们不需要记录(cell + k)对应的path, 因为如果我们再次visit这个node, 带着同样的k, path一定不会优于原先的
 * 这就是bfs的优点. 如果是dfs, 少不得要记录path并保留最小值.
*/
class Solution {
public:
    int shortestPath(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> visited(m, vector<int>(n, -1));
        queue<vector<int>> q;
        q.push({0, 0, 0, k}); // x, y, path, k left
        while(!q.empty()) {
            vector<int>& current = q.front();
            int x = current[0], y = current[1], path = current[2], k = current[3];
            q.pop();
            if(x < 0 || x >= m || y < 0 || y >= n) continue;
            if(x == m - 1 && y == n - 1) return path;
            if(grid[x][y] == 1) {
                if(k) --k;
                else continue; 
            }
            if(visited[x][y] != -1 && visited[x][y] >= k) continue;
            visited[x][y] = k;
            q.push({x+1, y, path + 1, k});
            q.push({x-1, y, path + 1, k});
            q.push({x, y+1, path + 1, k});
            q.push({x, y-1, path + 1, k});
        }
        return -1;
    }
};