public class Divide2Integers {
    public int divide(int dividend, int divisor) {
        /* 
         * 整体思路就是加上去直到超出dividend
         * INT MIN必须特殊处理因为abs就会翻成0, 是truncate的
         * -2147483648 : 1 << 31
         * 2147483647 : (1 << 31) - 1
         */
        if(dividend == -2147483648 && divisor == -1){
            return 2147483647;
        }
        boolean flag = (dividend < 0) ^ (divisor < 0);
        divisor = Math.abs(divisor);
        dividend = Math.abs(dividend);
        int count = 0;
        /* 简单加法会造成time limit exceeded
         * 这里需要用2的乘法让divide能够尽快的增加, 但是又不能用乘法,所以bitwise shift比较合适
         * dividend - (divisor << power << 1) >= 0不能写成
         * dividend >= (divisor << power << 1), dividend - divisor同理
         */
        while(dividend - divisor >= 0){
            int power = 0;
            /* 一次尝试divisor * (2 ** (power + 1)),不行的话退出循环后面直接power*/
            while(dividend - (divisor << power << 1) >= 0) {power++;}
            count += 1 << power; // 2 ** power
            dividend -= divisor << power;
        }
        return flag ? count * -1 : count;
    }
}
