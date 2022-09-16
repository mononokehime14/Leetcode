package math;

public class NumberOf1Bits {
    /* 利用n & (n - 1) 会消除最后一位1 一直消到全0 */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
