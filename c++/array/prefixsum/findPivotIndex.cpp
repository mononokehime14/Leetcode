#include <vector>
#include <numeric>

using namespace std;
class findPivotIndex {
public:
    /**
     * 724. Find Pivot Index
     * 第一种做法乏善可陈的前缀数组, 也就是中二的三叉戟算法, 使用了intuitive的两个数组
     * 更好的做法是实际上用两个int就可以了, 算出了总和之后, 从左往右遍历, 一边遍历一边更新
     * 理论上这是有提升的, 因为扫描了一遍数组, 而不是两遍, 但是实际上没有, 可能是因为整个vector可以cache的原因?
     * 注意这里accumulate的使用, 最后一个参数是initial sum value.
    */
   int pivotIndex(vector<int>& nums) {
        int n = nums.size();
        int left = 0;
        int sum = accumulate(nums.begin(), nums.end(), 0);
        // int sum = 0;
        // for(int i : nums) {
        //     sum += i;
        // }
        for(int i = 0;i < n;i++) {
            if(left == sum - nums[i] - left) return i;
            left += nums[i];
        }
        return -1;
    }
    // int pivotIndex(vector<int>& nums) {
    //     int n = nums.size();
    //     vector<int> pre(n, 0);
    //     vector<int> post(n, 0);
    //     for(int i = 1;i < n;i++) {
    //         pre[i] = pre[i-1] + nums[i-1];
    //     }
    //     for(int i = n-2;i >= 0;i--) {
    //         post[i] = post[i+1] + nums[i+1];
    //     }
    //     for(int i = 0;i < n;i++) {
    //         if(pre[i] == post[i]) return i;
    //     }
    //     return -1;
    // }
};