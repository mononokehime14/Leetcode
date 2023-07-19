public class BinarySearchTemplate {
    // Search target
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) { // left == right we still want to search, because right is not nums.length
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] > target) {
                right = mid - 1; // mid can not correct answer
            }else {
                left = mid + 1; // mid can not correct answer
            }
        }
        return -1; // cannot find
    }

    // Search first element greater than target
    public int searchFirstGreater(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) { // left == right we still want to search, because right is not nums.length
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) { // change to < if greater/equal
                // 这里可以理解为无可能是正确答案的区域
                left = mid + 1; // not possible to be correct answer
            }else {
                // 这里理解为可能是正确答案的区域 如果mid是正确答案 现在right是mid - 1 一会儿left就会不断的向right靠拢 直到超过right 哎就变成了这个mid 我们还是能拿到正确的解
                // 这也是为什么后面会return left, 只有left才会回去mid
                right = mid - 1; // may be correct answer, but we try once more
            }
        }
        // if left > right and right does not move, no element greater than target
        // if there is some element, when left > right, might be right just get mid - 1, so previous mid = left should be correct
        // or left just get mid + 1, so previous mid is an element smaller than target, not we want, current left is want we want
        // 这和上面是关联的, 注意无可能是正确答案的时候left增加, 所以我们要考虑万一没有正确答案(=left一直增加)的情况
        return left == nums.length ? -1 : left; 
    }

    // Search first element smaller than target
    public int searchFirstSmaller(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) { // left == right we still want to search, because right is not nums.length
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target) { // change to > if smaller/equal
                right = mid - 1; // not possible to be correct answer
            }else {
                left = mid + 1; // may be correct answer, but we try once more
            }
        }
        return right < 0 ? -1 : right; // should be < 0 not == 0, if no element match will out of range.
    }
}
