package math;
public class sqrt {
    public int mySqrt(int x) {
        int left = 0;
        /*
         * INT_MAX的sqrt也不过是46340.几,所以我们可以从该处开始BS
         * 直到试出正确答案, 或者取出round down近似值
         */
        int right = Math.min(46340, x);
        int mid = 0;
        while(right > left){
            mid = (left + right) / 2;
            if(mid * mid == x){
                return mid;
            }else if(mid * mid > x){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return Math.min(left, right);
    } 
}
