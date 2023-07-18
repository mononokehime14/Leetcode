#include <vector>
using namespace std;
/**
 * 167 Two Sum II - Input array is sorted
 * 在有序情况下双指针, 无序情况下用set找补足
 * sum小则必然左指针右移, 因为右指针左移只会更小
*/
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int l = 0, r = numbers.size()-1, sum;
        while(l < r) {
            sum = numbers[l] + numbers[r];
            if(sum == target) {
                break;
            }else if(sum < target) {
                l++;
            }else{
                r--;
            }
        }
        return vector<int>{l+1, r+1}; // guarantee an answer
    }
};