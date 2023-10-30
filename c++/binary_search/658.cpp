/**
 * 658. Find K Closest Elements
 * findKClosestElements
 * 这道题我的思路是二分查找x, 找不到可以return first greater/equal, 
 * 以及first smaller/equal, 然后在扩散双指针补齐k个
 * C++书上的解法非常玄妙, 直接搜索答案的k数组的开头第一个
 * 这样第一个最接近k肯定不能充分论证k数组就是最接近的, 判定条件是第一个 + 第k个最接近2 * x
 * 这里用了first greater的搜索逻辑, 这是为什么呢?
 * 另外我的二分搜索模版套在这里有错, 是因为right无法从最右边, 只能考虑-k的情况,
 * 如此无法处理size为1的测试. 我最近确实感觉到<=的写法有一点点的麻烦.
*/
class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        int left = 0, right = arr.size() - k;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] + arr[mid+k] < 2 * x) { // not correct answer
                left = mid + 1; 
            }else{
                right = mid;
            }
        }
        return vector<int>(arr.begin() + left, arr.begin() + left + k);
    }
};