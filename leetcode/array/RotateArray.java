package array;

public class RotateArray {
    /* 先reverse后面 再reverse前面 再reverse 整体 */
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length < 2){
            return;
        }
        k = k % nums.length;
        int n = nums.length;
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - k - 1);
        reverse(nums, 0, n - 1);
    }
    private void reverse(int[] nums, int left, int right) {
        while(left < right) {
            nums[left] ^= nums[right];
            nums[right] ^= nums[left];
            nums[left] ^= nums[right];
            left++;
            right--;
        }
    }
}
