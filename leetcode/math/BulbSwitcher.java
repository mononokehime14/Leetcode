package math;

public class BulbSwitcher {
    /* 319
     * 这道题确实要从toggle次数来思考
     * 一个质数会被toggle两次 比如第7个 在Round1和7是被toggle 因此最后是灭的
     * 正常来说 因数是成对出现 比如6 可以分解为6 = 1 * 6 = 2 * 3 在1 2 3 6Round toggle 最后是toggle
     * 有例外 就是平方 比如16 16 = 1 * 16 = 2 * 8 = 4 * 4 这里4重复了 最后toggle次数是奇数 所以最后是亮的
     * 那么如何知道有多少这样的数字 我们可以从n的平方根中知道最大的这个数字 那么我们就知道了次数
     */
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
