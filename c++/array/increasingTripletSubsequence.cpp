/**
 * 334. Increasing Triplet Subsequence
 * 一开始还想复杂了, 想着sort(value, index) 然后以3的window开始滑
 * 但是实际上 我只要记录两个最小值 就一定能保证我找到一个可能的解
*/
class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        int min1 = INT_MAX, min2 = INT_MAX;
        for(int i : nums) {
            if(i <= min1){
                min1 = i;
            }else if(i <= min2) {
                min2 = i;
            }else{
                return true;
            }
        }
        return false;
    }
};