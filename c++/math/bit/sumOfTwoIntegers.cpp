/**
 * 371. Sum of Two Integers
 * 位运算是真的吊
 * 这里不能用+-, 那么我们从二进制的角度去思考这个问题 二进制如果相加, 可以把carry分开考虑
 * 比如759+674, 如果不考虑进位，可以得到 323, 如果只考虑进位，可以得到 1110, 把上面两个数字假期 323+1110=1433 就是最终结果了
 * 不考虑进位的运算是xor, 注意1和1->0
 * 进位的运算时& 然后shift, 1和1 -> 1, shift到左边一位上
 * 这里要在&0x7fffffff因为signed integer不让左shift
 * 然后我们再把这两部分用递归继续算, 直到carry是0, 那就直接是第一部分
*/
class Solution {
public:
    int getSum(int a, int b) {
        return b ? getSum((a ^ b), (a & b & 0x7fffffff) << 1): a;
    }
};