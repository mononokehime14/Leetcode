/**
 * 69. Sqrt(x)
 * 这道题是用二分查找很好想, 这就是二分法的很大一种使用方向: 二分尝试正确的数字直到搜到
 * 我们首先需要知道min max, max应该是x而不是x-1因为1就是1的平方根, min为1是因为不想碰到division by 0, 这样单独处理0的情况
 * 这里除了套用搜索的模版以外, 还有几个重要细节, 第一个是比较用什么呢? 这里用的是相除, 其实mid * mid和x比较思考了一下
 * 应该也是可以的, 只不过有溢出风险.
 * 第二个是return值, return要挂right. 循环结束的时候有两种情况, 第一种right=mid-1, right<left, 可以通过8的情况分析推理出,
 * 这是因为left==right时候mid超出了实际平方根, 故而我们想要再去right=mid-1, right就小了, 这个时候right实际上是返回值因为round down
 * 第二种left=mid+1, right<left, 这种情况发生于mid实际上小于平方根, 这个时候right仍然是返回值, 因为round down
 * 故此应该返回right 可见最后这个return的选择要考虑循环退出的时候是什么情况 不过循环退出left right永远是差1的 这样实际上就是两种不同的情况
*/
class Solution {
public:
    int mySqrt(int x) {
        if(x==0) return 0;
        int left = 1, right = x; // r cannot be x-1, 1 is the case
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int sqrt = x / mid;
            if(sqrt == mid) {
                return mid;
            }else if(sqrt > mid) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return right; // analyse base on 8 -> 2, right = mid - 1 will be the last step
    }
};