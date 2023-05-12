package math;

public class PowerOfTwo {
    /* 思路是二的指数次方必然是只有一个bit在binary representation上
     * 那么我们把它消除到不就是0吗
     * 然后注意如果传进来的是负数其天生就带了一个1就不是二的幂数
     */
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        return (n &= (n - 1)) == 0;
    }
}
