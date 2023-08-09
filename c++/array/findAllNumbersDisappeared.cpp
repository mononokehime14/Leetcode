#include <vector>
using namespace std;

/**
 * 448. Find All Numbers Disappeared in an Array
 * 这只是一道简单的标记题目, 但是一开始还是想要用set, 这里因为数字是1-n用vector就可以了
 * 尝试了一下reserve, 由于PP作业的缘故对于push_back有种恐惧, 在内存不需要重新分配的情况下push_back是O(1)的
 * 但是如果要重新分配内存就是O(N)因为要重新分配然后copy, 这时候最好用reserve
 * 但是可能刷题的情况下vector的大小都比较小, 所以可能不会出现这个问题
*/
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int n = nums.size();
        vector<int> count(n+1, 0);
        for(const int & i : nums) {
            count[i] = 1;
        }
        vector<int> answer;
        // answer.reserve(n);
        for(int i = 1;i < n+1;i++) {
            if(count[i]==0) answer.push_back(i);
        }
        return answer;
    }
};