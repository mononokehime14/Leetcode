/**
 * 581. Shortest Unsorted Continuous Subarray
 * shortestUnsortedContinuousSubarray
 * 思路完全是自己想出来的, 首先是要反过来, 以其找一段最小的需要反转的subarray, 图一画出来就很明显,
 * 我们实际上要找到的是两端最大的递增和递降序列, 隐含的意思其实是, 比如从左到右递增, 一旦出现比前面小的
 * 断掉递增, 这个数字无论如何都会出现在要翻转的subarray里
 * 这个任务可以用左右双指针, 分别找到两端的递增和递降序列. 但是这里还有一个正确性问题, 中间可能存在比当前的
 * left right更小或者更大的, 到时候sort之后, 不就对上不了吗
 * 所以第二部分思路, 就是要找出中间的min max, 然后左右指针往回滑动, 直到有合适的口子
 * 
 * 这里实现的时候只有一个地方没搞清楚, 就是中间找min max的时候, 不能从left + 1 -> right - 1
 * 我原来想的是从中间一定要翻转的subarray里找, 但是这样有一个问题没有处理, 就是left有可能比right大, 而中间
 * 找出来的max不如left, 这时候实际上right是需要往回走的. 那么把left也囊括进来就解决了问题. 
*/
class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        int n = nums.size(), left = 0, right = n - 1;
        while(left+1 < n && nums[left] <= nums[left+1]) ++left;
        if(left == n - 1) return 0; // already sorted
        while(right-1 >= 0 && nums[right-1] <= nums[right]) --right;
        int _min = INT_MAX, _max = INT_MIN;
        for(int i = left;i <= right;++i) {
            _min = min(_min, nums[i]);
            _max = max(_max, nums[i]);
        }
        cout << left << " " << right << " " << _min << " " << _max << endl;
        while(left >= 0 && nums[left] > _min) --left;
        while(right < n && nums[right] < _max) ++right;
        cout << left << " " << right << endl;
        return right - left - 1;
    }
};