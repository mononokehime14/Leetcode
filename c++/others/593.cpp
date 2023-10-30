/**
 * 593. Valid Square
 * validSquare
 * 检测变长和相互垂直
 * 这里由于时间的关系我只是拷贝了C++书上的代码, perpendicular的检测如果我来写的话会检查x和y的值
*/
class Solution {
public:
    bool validSquare(vector<int>& p1, vector<int>& p2, vector<int>& p3, vector<int>& p4) {
        vector<pair<int, int>> square{{p1[0], p1[1]}, {p2[0], p2[1]}, {p3[0], p3[1]}, {p4[0], p4[1]}};
        sort(square.begin(), square.end(), [](pair<int, int>& a, pair<int, int>& b){
            if (a.first == b.first) return a.second < b.second;
            return a.first < b.first;
        });
        return dist(square[0], square[3]) == dist(square[1], square[2]) && bisect(square[3], square[0], square[1], square[2]);
    }

    double dist(pair<int, int>& a, pair<int, int>& b){ // 检测边长相等
        double c = b.second - a.second, d = b.first - a.first;
        return c*c + d*d;
    }

    bool perpendicular(pair<int, int>& a, pair<int, int>& b, pair<int, int>& c, pair<int, int>& d) {
        double l, r;
        if (a.first == b.first) {
            if (a.second == b.second) return false;
            return c.second == d.second && c.first != d.first;
        } 
        l = (a.second - b.second) / (double)(a.first - b.first);
        if (c.first == d.first) {
            if (c.second == d.second) return false;
            return a.second == b.second;
        } 
        r = (c.second - d.second) / (double)(c.first - d.first);
        return abs(l * r + 1) < 1e-8;
    }

    bool bisect(pair<int, int>& a, pair<int, int>& b, pair<int, int>& c, pair<int, int>& d) {
        if (!perpendicular(a, b, c, d)) return false;
        return a.second + b.second == c.second + d.second && a.first + b.first == c.first + d.first;
    }
};