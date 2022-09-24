package math;

public class SetMismatch {
    /* 思路就是对于给定[1..N]数字的数组这种题目 往往是XOR 或者考虑用index
     * 核心在于成对消除 然后看剩下的 这道题用XOR不行 我们考虑用index配对 用nums的值匹配index
     * 匹配的index将对应的nums改成负数标志 那么如果这个index被匹配到两次 也就是我们碰到nums值已经被改成负数
     * 那么是该index是重复 如果循环结束index没有被匹配过那么这是missing
     */
    public int[] findErrorNums(int[] nums) {
        int missing = 0, repetition = 0;
        for(int i = 0;i < nums.length;i++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] < 0) {
                repetition = index + 1;
            }else{
                nums[index] *= -1;
            }
        }
        for(int i = 0;i < nums.length;i++) {
            if(nums[i] > 0) missing = i + 1;
        }
        return new int[]{repetition, missing};
    }
}
