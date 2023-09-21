/**
 * 31. Next Permutation
 * 这道题的思路非常的巧妙 我一开始想暴力 permutation算出来然后sort 但是实现起来就知道
 * 复杂度难以想象 而且我只需要下一个 没必要之前的之前后的全部算出来
 * 接下来我想来个sort的版本 然后BS找第一个比当前的数字大的 但是想法不对 不是把一个数字换成更大的就可以了
 * 必须找到下一个 也就是增加幅度最小的 这样我们就肯定要从后面开始做文章
 * 正确的思路是主要到 对于一个1 2 3 6 5 4 3 1, 6 5 4 3 1是没办法更大的, 因为6顶在最前面
 * 那么我们如果考虑从后往前最大的前面一个 也就是3 然后将3变成后面第一个能比其大的数也就是4 然后从小到大排列
 * 4 1 3 3 5 6 这就是下一个permutation了
 * 注意这里不能3直接和6交换 我们需要的是提升幅度最小的
*/
class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        int size = nums.size();
        int index = size - 1;
        while(index > 0 && nums[index-1] >= nums[index]) --index;

        int left = index - 1, right = size - 1;
        if(left >= 0) {
            while(nums[left] >= nums[right]) --right;
            swap(nums[left], nums[right]);
        }
        left = index;
        right = size - 1;
        while(left < right) swap(nums[left++], nums[right--]);
    }
};