package math;

public class MissingNumber {
    /* 其实sum一下就可以了 但是bit operation可能更加快速
     * 可以利用XOR同样的数字消除 然后index和nums中的数字其实是match的特性
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        sum ^= n;
        for(int i = 0;i < n;i++){
            sum ^= i ^ nums[i];
        }
        return sum;
    }
}
