#include <vector>
using namespace std;
/**
 * 88 Merge Sorted Array
 * 如果是建立一个新的数组, 这道题的双指针会非常非常简单
 * 但是inplace如果操作呢 这就只能从后往前 因为后面是没有被assigned值 这样就不会覆盖掉还没见过的nums1的值
 * 这里循环的条件不能是p>=0因为两个数组中的一个可能会先用完 这样就index出问题了
 * 还有一个小细节是, 如果nums2先用完了, 那么nums1剩下的部分就不用管了, 因为本来就是nums1的 所以后续的两个while只需要一个
*/
class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        int p1 = m-1, p2 = n-1, p=m+n-1;
        while(p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] >= nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        while(p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
};