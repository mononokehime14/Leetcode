/**
 * 892. Surface Area of 3D Shapes
 * 思路比较简单, 前缀数组就能计算从一个角度看能看到的面, 答案是直接抄录的
*/
class Solution {
public:
    int surfaceArea(vector<vector<int>>& grid) {
        int sum = 0;
        // up and under
        for (auto i: grid) for (auto j: i) if (j) ++sum;
        sum *= 2;
        
        // four directions
        int localsum = 0, prev;
        for (int i = 0; i < grid.size(); ++i) {
            prev = 0;
            for (int j = 0; j < grid.size(); ++j) {
                if (grid[i][j] >= prev) localsum += grid[i][j] - prev;
                prev = grid[i][j];
            }
        }
        sum += localsum;
        
        localsum = 0;
        for (int i = 0; i < grid.size(); ++i) {
            prev = 0;
            for (int j = grid.size() - 1; j >= 0; --j) {
                if (grid[i][j] >= prev) localsum += grid[i][j] - prev;
                prev = grid[i][j];
            }
        }
        sum += localsum;
        
        localsum = 0;
        for (int j = 0; j < grid.size(); ++j) {
            prev = 0;
            for (int i = 0; i < grid.size(); ++i) {
                if (grid[i][j] >= prev) localsum += grid[i][j] - prev;
                prev = grid[i][j];
            }
        }
        sum += localsum;
        
        localsum = 0;
        for (int j = 0; j < grid.size(); ++j) {
            prev = 0;
            for (int i = grid.size() - 1; i >= 0; --i) {
                if (grid[i][j] >= prev) localsum += grid[i][j] - prev;
                prev = grid[i][j];
            }
        }
        sum += localsum;
        
        return sum;
    }
};