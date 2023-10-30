/**
 * 215. Kth Largest Element in an Array
 * kthLargestElementInAnArray
 * 最经典的quick select应用. 我们可以直接当做是quick select的模版
 * 分成两部分, 第一部分是二分查找partition, 这里第K个大的只是找sort好的index k罢了
 * 必然能够找到, 所以用搜target的二分就可以了
 * Scan的部分很有讲究, 细节很多. 首先, 两个inner while的条件一定不能是left < right
 * 一定要是left < end和right > start 我们可以画出标尺来理解
 * left停下来的地方是第一个>p的, right则应该是第一个小于p的
 * 由于我们的start是pivot, 到时候肯定是right, 小于p的换过去到start的位置
 * left和right的位置可能是相等的吗, 实际上不会, 一个数字不可能大于p还小于p
 * left在左边 right在右边 那么这是一个普通的场景, 要swap
 * 但是left在右边, right在左边呢 这个是合理的 因为left right中间可能有一段等于p的
 * 这个时候要break, right换到start去. 但是假设inner while的判定是left < right
 * right就不会在该在的位置了, 而是一定停在left右边. 这个时候right不一定是小于p的!
 * 总结, scan的部分有几个关键点:
 * 1. break出来以right为准, 是因为start是pivot, 我们需要<pivot的换到start上
 * 2. inner while的停止条件要考虑left right跳出inner while的时候的位置. 我们需要right一定在
 * <pivot的位置上, 所以要用right > start而不是left < right
*/
class Solution {
public:
    int findKthLargest_pq(std::vector<int>& nums, int k) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for(const int & n : nums) {
            pq.push(n);
            if(pq.size() > k) pq.pop();
        }
        return pq.top();
    }
    int findKthLargest(std::vector<int>& nums, int k) {
        return partition(nums, k);
    }

    int partition(vector<int>& nums, int k) {
        int left = 0, right = nums.size() - 1;
        k = nums.size() - k;
        while (left <= right) {
            int pivot = scan(nums, left, right);
            if (pivot == k) {
                return nums[pivot];
            } else if (pivot > k) {
                right = pivot - 1;
                while (right > k && nums[right] == nums[right + 1]) --right;
            } else {
                left = pivot + 1;
                while (left < k && nums[left] == nums[left - 1]) ++left;
            }
        }
        return -1;
    }
    int scan(vector<int>& nums, int start, int end) {
        int left = start + 1, right = end;
        while(1) {
            while(left < end && nums[left] <= nums[start]) ++left;
            while(right > start && nums[right] >= nums[start]) --right;
            if(left >= right) break;
            swap(nums[left], nums[right]);
        }
        swap(nums[right], nums[start]);
        return right;
    }
};