#include <vector>
using namespace std;
/**
 * 870. Advantage Shuffle
 * 我还是理解为2 pointer sort之后比不过就用另外一边的混一下
 * java版本的解释足够了 C++再实现一遍罢了
*/
class Solution {
public:
    vector<int> advantageCount(vector<int>& nums1, vector<int>& nums2) {
        int size = nums1.size();
        vector<pair<int, int>> nums2_2(size);
        for(int i = 0;i < size;++i) nums2_2[i] = make_pair(nums2[i], i);
        sort(nums1.begin(), nums1.end());
        sort(nums2_2.begin(), nums2_2.end());
        vector<int> answer(size);
        int right = size - 1, left = 0;
        for(int i = size - 1;i >= 0;--i) {
            if(nums2_2[i].first >= nums1[right]) {
                answer[nums2_2[i].second] = nums1[left++]; 
            }else{
                answer[nums2_2[i].second] = nums1[right--]; 
            }
        }
        return answer;
    }
};