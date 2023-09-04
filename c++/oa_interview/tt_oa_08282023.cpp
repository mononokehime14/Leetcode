#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    int maximizeTaskPriority(int n, vector<int> & priority, int x, int y) {
        sort(priority.begin(), priority.end(), greater<int>());
        int adjust_x = min(static_cast<int>(priority.size()), x+1), interval = 0, answer = 0;
        for(int i = 0;i < adjust_x;++i) {
            interval += priority[i];
        } 
        answer = interval * (y / x);
        adjust_x = y % adjust_x;
        for(int i = 0;i < adjust_x;++i) {
            answer += priority[i];
        } 
        cout << "answer: " << answer << endl;
        return answer;
    }
    /**
     * 实际上这道题没有那么复杂, 不用combination或者dp
     * 只需要选出能compatible两种的cpu 先用掉 然后剩下的再搞
    */
    vector<vector<int>> res;
    vector<int> track = {0, 0, 0};
    void backtrack(const vector<int> & cost, const vector<int> & compatible1, const vector<int> & compatible2, int start, int minCompatible) {
        if(track[1] >= minCompatible && track[2] >= minCompatible) res.push_back(track);
        for(int i = start;i < cost.size();i++) {
            track[0] += cost[i];
            track[1] += compatible1[i];
            track[2] += compatible2[i];
            backtrack(cost, compatible1, compatible2, i + 1, minCompatible);
            track[0] -= cost[i];
            track[1] -= compatible1[i];
            track[2] -= compatible2[i];
        }
    }

    int getMinCost(vector<int> cost, vector<int> compatible1, vector<int> compatible2, int min_compatible) {
        backtrack(cost, compatible1, compatible2, 0, min_compatible);
        int min_cost = 1000000;
        for(const vector<int> & track : res) min_cost = min(min_cost, track[0]);
        cout << "answer: " << min_cost << endl;
        return min_cost;
    }
};