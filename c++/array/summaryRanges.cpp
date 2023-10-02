/**
 * 228. Summary Ranges
 * 不用复习, 遍历一遍就行了
*/
class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        vector<string> answer;
        if(nums.empty()) return answer;
        int left = 0, right = 1;
        for(;right < nums.size();++right) {
            if(nums[right] != nums[right-1] + 1) {
                if(left == right - 1) {
                    answer.push_back(to_string(nums[left]));
                }else{
                    answer.push_back(to_string(nums[left]) + "->" + to_string(nums[right-1]));
                }
                left = right;
                // cout << left << " " << right << endl;
            }
        }
        if(left == right-1) {
            answer.push_back(to_string(nums[left]));
        }else{
            answer.push_back(to_string(nums[left]) + "->" + to_string(nums[right-1]));
        }
        return answer;
    }
};