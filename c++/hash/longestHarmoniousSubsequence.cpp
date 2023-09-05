using namespace std;
/**
 * 594. Longest Harmonious Subsequence
 * 考虑了滑动窗口 前缀数组 hashmap 最终发现它只在乎n-1 n+1两个数字在前面有多少count就行了
 * 这道题看到也有滑动窗口的解 通过right和left的比对 >1收缩 这个好像也是可以的
*/
class Solution {
public:
    int findLHS(vector<int>& nums) {
        unordered_map<int, int> count;
        int answer = 0;
        for(const int n : nums) {
            ++count[n];
            if(count.find(n-1) != count.end()) {
                answer = max(answer, count[n-1]+count[n]);
            }
            if(count.find(n+1) != count.end()) {
                answer = max(answer, count[n+1]+count[n]);
            }
        }
        return answer; 
    }
};