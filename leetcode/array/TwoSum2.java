package array;

public class TwoSum2 {
    /* 堪称最简单的medium 在一个有序数组的TwoSum情况下 只要保持双指针 */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] output = new int[2];
        while(left < right) {
            if(numbers[left] + numbers[right] == target) {
                output[0] = left + 1;
                output[1] = right + 1;
                return output;
            }else if(numbers[left] + numbers[right] < target) {
                left++;
            }else {
                right--;
            }
        }
        return output;//this line should not be reached
    }
}
