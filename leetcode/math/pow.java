package math;
public class pow {
    public double myPow(double x, int n) {
        /*
         * 需要divide & conquer, 因为速度的原因
         * 3 ** 4 linear需要四次, （3 ** 2）** 2则只需要乘两次
         */
        if(n == 0) return 1;
        /* INT MIN的处理要在前面, 放在n < 0后面没有办法判断 n == 2147483648超出int range */
        if(n == -2147483648){
            x *= x;
            n /= 2;
        }
        if(n < 0){
            x = 1/x;
            n *= -1;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
