package array;

public class 3Sum {
    /**
     * 一开始用的方法并不是很行 思路是对于一个数 从其右边的数组中找到另外的两个数 用two sum的方法
     * 但是这里主要是两个问题 第一two sum找到一个解就返回了 如何找到全部的解 第二 我们不能有duplicate的解
     * 故此 其实对原方法进行一下改动 改成set<list<integer>>能避免解法重复 找到不return接着找能找到全部的解 不要用helper function
     * 有两个非常重要的优化 能够让速度从15%+提升到70%+
     * 第一个就是如果两个数字相同 则可以跳过 解是一模一样的
     * 第二个就是如果在2sum时下一个数字相同 也可以跳过 解同样是一模一样的
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> output = new HashSet<>();
        int n = nums.length;
        for(int i = 0;i < n - 2;i++) { // n - 2 avoid index越界
            if(i > 0 && nums[i] == nums[i-1]) continue; // optimize
            int left = i+1;
            int right = n - 1;
            while(left < right) {
                int sum = nums[right] + nums[left] + nums[i];
                if(sum == 0) {
                    output.add(Arrays.asList(nums[left], nums[right], nums[i]));
                    left++;
                    right--;
                    while(left < right && nums[left] == nums[left - 1]) left++; //optimize
                    while(left < right && nums[right] == nums[right + 1]) right--; //optimize
                }else if(sum > 0) {
                    right--;
                }else {
                    left++;
                }
            }
        }
        return new LinkedList<>(output);
    }
    // public List<List<Integer>> threeSum(int[] nums) {
    //     Arrays.sort(nums);
    //     List<List<Integer>> output = new LinkedList<>();
    //     int n = nums.length;
    //     for(int i = 0;i < n;i++) {
    //         List<Integer> temp = twoSum(nums, 0 - nums[i], i+1, n-1, nums[i]);
    //         if(temp != null) output.add(temp);
    //     }
    //     return output;
    // }
    // private List<Integer> twoSum(int[] nums, int target, int left, int right, int mself) {
    //     while(left < right) {
    //         int tl = nums[left], tr = nums[right];
    //         if(tl + tr == target) {
    //             if(tl != tr && tl != mself && tr != mself) {
    //                 return Arrays.asList(tl, tr, mself)
    //             }
    //             left++;
    //             right--;
    //         }else if(tl + tr > target) {
    //             right--;
    //         }else {
    //             left++;
    //         }
    //     }
    //     return null;
    // }
}
