/**
 * 628. Maximum Product of Three Numbers
 * maximumProductOfThreeNumbers
 * 找三个最大的就行, 注意, 也要尝试两个最小的负数的情况
*/
class Solution {
public:
    int maximumProduct(vector<int>& nums) {
        int max1, max2, max3, min1, min2;
        max1 = max2 = max3 = INT_MIN;
        min1 = min2 = 0;
        for(int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        return max(max1*max2*max3, min1*min2*max1);
    }
};