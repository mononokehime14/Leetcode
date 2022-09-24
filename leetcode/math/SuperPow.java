package math;

public class SuperPow {
    /* 这道题的思路不难 只是有几个细节 首先是如何modular
     * 这里要记住 (a * b) % m = ((a % m) * (b % m)) %m
     * 然后就是求a ** b的基本思路是递归
     * a ** b = 
     *  a * (a ** (b-1)), if b odd
     *  (a ** (b/2)) ** 2, if b even
     */
    public int superPow(int a, int[] b) {
        return helper(a, b, b.length-1);
    }
    private int helper(int a, int[] b, int index) {
        if(index < 0) return 1; //empty means 0
        int res1 = myPow(a, b[index]);
        int res2 = myPow(helper(a, b, index-1), 10);
        return (res1 * res2) % 1337;
    }
    private int myPow(int x, int y) { // x**y
        x %= 1337;
        int res = 1;
        for(int i = 0;i < y;i++) {
            res *= x;
            res %= 1337;
        }
        return res;
    } 
    private int efficientPow(int x, int y) {
        if(y == 0) return 1;
        x %= 1337;
        if(y % 2 == 0) {
            int res = myPow(x, y / 2);
            return (res * res) % 1337;
        }else {
            return (x * myPow(x, y-1)) % 1337;
        }
    }
}
