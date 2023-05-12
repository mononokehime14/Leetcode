package math;

public class UglyNumber {
    /* 263
     * 这道题的判断是基于一个数学常识 但就是一个数字 除了1 最后一定可以被分解为质数的乘积
     * 除了1只能分解成1之外 任何的数字分解到最后 都是质数乘积 因为不是质数可以进一步分解的
     * 所以丑数就只需要检查 是不是质因数都是2 3 5就可了
     */
    public boolean isUgly(int n) {
        if(n <= 0) return false; // should be a positive integer
        while(n % 2 == 0) n /= 2;
        while(n % 3 == 0) n /= 3;
        while(n % 5 == 0) n /= 5;
        return n == 1;
    }
}
