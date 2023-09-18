/**
 * 207. Course Schedule
 * 仿照Java版本的思路重写的C++ 主要是思路是检测环 这一点实际上并不好想
 * 然后就是track和visited的区别 track是回溯的 专门检测图中的环
 * visited则是避免进入死循环用的
*/
class Solution {
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> graph(numCourses, vector<int>());
        buildGraph(prerequisites, graph);
        bool has_cycle = false;
        vector<bool> visited(numCourses, false);
        vector<bool> track(numCourses, false);
        for(int i = 0;i < numCourses && !has_cycle;++i) {
            if(!visited[i]) traverse(i, graph, visited, track, has_cycle);
        }
        return !has_cycle;
    }

    void traverse(int current, vector<vector<int>>& graph, vector<bool> &visited, vector<bool> &track, bool &has_cycle) {
        if(track[current]) {
            has_cycle = true;
            return;
        }
        if(visited[current] || has_cycle) return;
        visited[current] = true;
        track[current] = true;
        for(const int next : graph[current]) {
            traverse(next, graph, visited, track, has_cycle);
        }
        track[current] = false;
    }

    void buildGraph(vector<vector<int>>& edge_list, vector<vector<int>>& graph) {
        for(const vector<int>& edge: edge_list) {
            graph[edge[1]].push_back(edge[0]);
        }
    }
};