/**
 * 9. Palindrome Number
 * 数字的palindrome检查, 思路是一样的还是双指针
 * 但是这里digits只能从右往左一个一个拿出来, 所以巧妙的一边消一边涨形成reverse的digits
 * 两个一比就知道了
 * 那么再延伸下去, 双指针可是比较到left < right就结束了 那么这里也是一样的
 * reverse和x可以相比作为停止条件
 * 但是这里有两个edge, 第一个是有奇数个数的digits怎么办 这里在return的时候可以额外做个比较
 * 还有一个edge是如果右边第一个数字是0, 这个时候reverse还是0, 但是因为这是数字的缘故, 00和0没区别, 下次还是从0开始
 * 这样如果最左边是1, 这个是会误判为正确的 所以只能在最开始额外检查
*/
class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int reverse = 0;
        while(x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return reverse == x || (x == reverse / 10);
        // long reverse = 0, x_copy = x;
        // while(x > 0) {
        //     reverse = reverse * 10 + x % 10;
        //     x /= 10;
        // }
        // return reverse == x_copy;
    }
};