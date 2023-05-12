package math;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        /* 这是利用了 a ^ b = 0 if a==b
         * a ^ b != 0 if a != b
         * a ^ 0 = a a是0的bit就是0 a是1的bit就是1 所以不变
         * 另外一个重要的特质就是XOR的顺序无关紧要 结果相同
         */
        int sum = 0;
        for(int i = 0;i < nums.length;i++) {
           sum ^= nums[i]; 
        }
        return sum;
    }
}
