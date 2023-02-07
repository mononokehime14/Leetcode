package math;

public class UglyNumberII {
    /* 264
     * 丑数的进阶 这次要判断的是第n个丑数
     * 首先要借鉴的是快速判断质数的方法 一个质数乘以任何不是一的factor 都不是质数
     * 同理 一个丑数 乘以2 3 5 都是丑数 乘任何别的不是1的factor则不是
     * 那么 在一个足够大的array上 我们已经可以推出所有的丑数了 碰到丑数update它的2 3 5倍就可以了
     * 这道题要求的是第n个 那么我们照理来说 用这种方法推出一个数组 然后数第n个就行了
     * 但是问题是不像快速筛选质数 我们知道最大值所以可以declare一个数组 这里不知道数组多大 所以我们必须动态加入
     * 动态加入就有问题了 x * 2, x * 3, x * 5 2倍是下一个丑数吗？不一定 可能之前的更新中产生了比x大但是比两倍x小的丑数
     * 所以可以这样思考 我们动态维持三个链表 每个记录丑数的2倍 3倍和5倍 然后并在一起去掉重复
     * 这样我们就比较当前链表的最小值 得到当前的丑数 然后更新链表为下一个没搞过的丑数的2 3或者5倍 以供下一次比较 这样就合并出了动态有序的总链表
     */
    public int nthUglyNumber(int n) {
        int p2 = 1, p3 = 1, p5 = 1;
        int product2 = 1, product3 = 1, product5 = 1;
        int[] uglyNums = new int[n+1];
        int p = 1;
        while(p <= n) {
            int min = Math.min(Math.min(product2, product3), product5);
            uglyNums[p++] = min;
            if(min == product2) {
                product2 = 2 * uglyNums[p2++];
            }
            if(min == product3) {
                product3 = 3 * uglyNums[p3++];
            }
            if(min == product5) {
                product5 = 5 * uglyNums[p5++];
            }
        }
        return uglyNums[n];
    }
}
