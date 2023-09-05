#include <vector>
using namespace std;
/**
 * 697. Degree of an Array
 * count 需要一些特殊的值辅助 因为同样的count情况下interval小的获胜 所以要额外记录start end index
 * 这里实际上不用sort 可以保持一个max(count和interval)就可以了
*/
class Solution {
public:
    int findShortestSubArray(vector<int>& nums) {
        if(nums.empty()) return 0;
        unordered_map<int, vector<int>> count;
        int size = nums.size();
        for(int i = 0;i < size;++i) {
            if(count.find(nums[i]) == count.end()) {
                count[nums[i]] = {1, i, i};
            }else{
                vector<int>& current = count[nums[i]];
                ++current[0];
                current[2] = i;
                // cout << "update " << nums[i] << " " << current[0] << " " << current[1] << " " << current[2] << endl;
            }
        }
        vector<pair<int, vector<int>>> count_vec(count.begin(), count.end());
        sort(count_vec.begin(), count_vec.end(), [](const pair<int, vector<int>> &a, const pair<int, vector<int>> &b){
            if(a.second[0] == b.second[0]) return a.second[2] - a.second[1] < b.second[2] - b.second[1];
            return a.second[0] > b.second[0];
        });
        pair<int, vector<int>> answer = *(count_vec.begin());
        return answer.second[2] - answer.second[1] + 1;
    }
};