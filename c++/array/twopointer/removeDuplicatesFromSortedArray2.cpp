/**
 * 80. Remove Duplicates from Sorted Array II
 * 比起1 2主要是有一个2次的tolerance
 * 原来的写法改出来有点问题 直接采用了C++书中的写法 非常简洁 当然由于每次检查i < 2的缘故速度并不是最佳
 * 这道题的双指针本质其实并不是快慢 应该理解为一个指针循环原数组 一个指针循环结果数组 只不过是inplace罢了
 * 
*/
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }
};