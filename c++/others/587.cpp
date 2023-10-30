/**
 * 587. Erect the Fence
 * erectTheFence
 * 这道题用了Andrew's monotone chain method凸包算法
 * 我并不甚明白, 不知道要不要花时间学习, 只记录了答案在此
*/
class Solution {
public:
    vector<vector<int>> outerTrees(vector<vector<int>>& points) {
        int n = points.size();
        vector<vector<int>> res;
        sort(points.begin(), points.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] < b[0] || (a[0] == b[0] && a[1] < b[1]);
        });
        // build lower hull
        for (int i = 0; i < n; ++i) {
            while (res.size() > 1 && orientation(res[res.size()-2], res.back(), points[i]) < 0) 
                res.pop_back();
            res.push_back(points[i]);
        }
        // if all points along a line, res.size() is n after lower hull procedure
        if (res.size() == n) return res;
        // build upper hull
        for (int i = n - 2; i >= 0; --i) {
            while (res.size() > 1 && orientation(res[res.size()-2], res.back(), points[i]) < 0) 
                res.pop_back();
            res.push_back(points[i]);
        }
        res.pop_back();
        return res;
    }
private:
    int orientation(vector<int>& a, vector<int>& b, vector<int>& c) {
        return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0]);
    }
};