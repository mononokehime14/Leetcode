public class SplitArrayLargestSum {
    /* 410
     * 无他 和1011是一摸一样的思路 甚至是一摸一样的代码
     * 这里用一个loop代替两个stream会提速 可能是loop两次和loop一次的区别
     */
    public int splitArray(int[] nums, int k) {
        // int left = Arrays.stream(nums).max().getAsInt();
        // int right = Arrays.stream(nums).sum();
        int left = 0, right = 0;
        for(int i : nums) {
            left = Math.max(i, left);
            right += i;
        }
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(!isValid(nums, k, mid)) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left; // answer seems to be guaranteed by the question
    }
    private boolean isValid(int[] nums, int k, int largest) {
        int sum = 0, count = 1;
        for(int i : nums) {
            if(sum + i <= largest) {
                sum += i;
            }else{
                sum = i;
                count++;
            }
        }
        return count <= k;
    }
}
