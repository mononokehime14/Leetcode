#include<vector>
using namespace std;
/**
 * 934. Shortest Bridge
 * 两个岛之间的最短距离, 思路很好想, 先dfs找到一个岛, 然后bfs找最短距离
 * 第一个要注意的是queue里可以加入相邻的sea cell 只需dfs的时候判断是不是0 是0就加入
 * 这样应该要比加入所有第一个岛屿的cell快一半
 * 这样的话就要注意, 37行就要把当前的cell visited先记录 因为本来人家是0
 * 同时steps++要移到34行 因为第一个cell也要算进去
 * 第二个要注意的47行的visited 在真正轮到该cell之前我们就要mark好 这行看起来多余 但是实际上
 * 如果不提前mark好 同一层的其他cell可能又把它加进去
*/
class Solution {
public:
    vector<vector<int>> direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int shortestBridge(vector<vector<int>>& grid) {
        int n = grid.size();
        queue<pair<int, int>> q;
        // Step 1: Find the first island using DFS and mark its cells as 2
        // add cell to the queue
        bool found = false;
        for (int i = 0; i < n && !found; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    dfs(i, j, n, grid, q);
                    found = true;
                    break;
                }
            }
        }

        // Step 2: Perform a BFS search to find the second island
        int steps = 0;

        while (!q.empty()) {
            int size = q.size();
            while (size--) {
                auto [i, j] = q.front();
                q.pop();
                // grid[i][j] = 2;
                for (int x = 0; x < 4; ++x) {
                    int ni = i + direction[x][0];
                    int nj = j + direction[x][1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                        if (grid[ni][nj] == 1) {
                            return steps;
                        }
                        if (grid[ni][nj] == 0) {
                            grid[ni][nj] = 2;
                            q.push({ni, nj});
                        }
                    }
                }
            }
            steps++;
        }
        return -1; // No path found
    }

    void dfs(int i, int j, int n, vector<vector<int>>& grid, queue<pair<int, int>>& q) {
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == 2)
            return;
        if(grid[i][j] == 0) {
            return;
        }
        q.push({i, j});
        cout << "found " << i << " " << j << endl;
        grid[i][j] = 2;
        dfs(i - 1, j, n, grid, q);
        dfs(i + 1, j, n, grid, q);
        dfs(i, j - 1, n, grid, q);
        dfs(i, j + 1, n, grid, q);
    }
};