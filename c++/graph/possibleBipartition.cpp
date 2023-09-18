/**
 * 886. Possible Bipartition
 * C++版本的实现 比较不同的是这里用了C++书中的BFS版本
 * 而且在实现过程中我自己合并了visited和colors 0,1,2代表visited和两种color
 * 这会需要我们在一开始给第一个element一个颜色 然后之后所有node的颜色都是由previous node定好的
*/
class Solution {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<vector<int>> graph(n, vector<int>());
        buildGraph(dislikes, graph);
        vector<int> visited(n, 0);
        queue<int> q;
        for(int i = 0;i < n; ++i) {
            if(visited[i]) continue;
            q.push(i);
            visited[i] = 1;
            while(!q.empty()) {
                int current = q.front();
                q.pop();
                for(const int & next : graph[current]) {
                    if(!visited[next]) {
                        visited[next] = visited[current] == 1 ? 2 : 1;
                        q.push(next);
                    }else if(visited[next] == visited[current]) return false;
                }
            }
        }
        return true;
    }

    void buildGraph(vector<vector<int>>& edge_list, vector<vector<int>>& graph) {
        for(const vector<int>& edge: edge_list) {
            graph[edge[1]-1].push_back(edge[0]-1);
            graph[edge[0]-1].push_back(edge[1]-1);
        }
    }
};