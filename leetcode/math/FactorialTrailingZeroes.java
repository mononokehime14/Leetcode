package math;
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        /*
         * 发现碰到5必然多一个0, 因为5之前是4, 偶数乘任何数都是偶数, 然后再乘5必然10的倍数
         * 乘10的倍数也会多一个0, 那么答案就是factorial中完全展开有多少个5
         * 这里一定要注意, 一开始错误的原因是答案不是5的多少倍, 而是factorial展开能有多少5
         * 这两者不同的原因是25的存在, 这是能搞出两个0的
         * O(logn), just counting multiples of 5
         */
        // return (int)(n / 5);
        int sum = 0;
        while(n > 0){
            n /= 5; // 这一步相当于计算之前有多少个数字能提供一个5 然后下一个循环看看还有没有数字提供两个5
            sum += n;
        }
        return sum;
    }
}
