public class FirstBadVersion {
    public int firstBadVersion(int n) {
        /* Binary Search 寻找区间
         * 这个模版是好理解的模版 但是代价是时间成本会稍微高一点 因为每次多一个if check的原因
         * 原来的模版也附上了 万一有一天通透了
         */
        int left = 1, right = n;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(isBadVersion(mid)) {
                if(mid - 1 >= 1 && isBadVersion(mid - 1)) {
                    right = mid - 1;
                }else{
                    return mid;
                }
            }else{
                left = mid + 1;
            }
        }
        return 1; // 随便一个return 题目应该是保证有bad version的 找不到这里可以return -1
    }
    // public int firstBadVersion(int n) {
    //     int l = 1, r = n;
    //     while(l < r)
    //     {
    //         int mid = l + ( r - l )/2;
    //         if(isBadVersion(mid))
    //         {
    //             r = mid;
    //         }
    //         else
    //         {
    //             l = mid + 1;
    //         }
    //     }
    //     return l;
    // }
}