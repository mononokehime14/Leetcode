import java.util.Arrays;
import java.util.Comparator;
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        /* 如果是普通构建DP就像LIS那样是不对的, LIS虽然sequence不是有序的, 但是
         * sequence一定从左到右的!这就给了我们从头一个element递推的可能, 只要在递推的时候loop全部比我大的.
         * 但是这个问题是不行的, 前面算过的不一定是maximum!
         * 所以需要更改题目的条件, 把envelopes变成有序的, 这样前面算过的就一定是极值了
         * 排序之后可以正常DP, 但是我们发现只要看第二个column就可以了
         * 普通的DP的局限在于时间是O(N)要过这道题, 需要对第二个column用nlogn的快速LIS
         * 这里有一个case要解决: [[4,5],[4,6],[6,7],[2,3],[1,1]]
         * 如果第二个col正向排序, 那么4,5 4,6会变成能嵌套, 解决的方法就是第二个col倒序!
         * 排序后一切不变, 除了同样col1的envelope, col2大的会在前面, 后面就回去它的牌堆从而防止他们生成新牌堆
         */
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            } 
        });
        int[] col2 = new int[envelopes.length];
        for(int i = 0;i < envelopes.length;i++){
            col2[i] = envelopes[i][1];
        }
        return fastLIS(col2);
        // int[] dp = new int[envelopes.length];
        // Arrays.fill(dp, 1);
        // for(int i = 0;i < dp.length;i++){
        //     int w = envelopes[i][1];
        //     for(int j = i + 1;j < dp.length;j++){
        //         if(envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > w){
        //             dp[j] = Math.max(dp[i] + 1, dp[j]);
        //         }
        //     }
        // }
        // return Arrays.stream(dp).max().getAsInt();
    } 
    private int fastLIS(int[] nums){
        int[] top = new int[nums.length];
        top[0] = nums[0];
        int number_piles = 1;
        for(int i = 1;i < nums.length;i++){
            int left = 0;
            int right = number_piles;
            while(left < right){
                int mid = left + (right - left) / 2;
                if(top[mid] >= nums[i]){
                    right = mid;
                }else if(top[mid] < nums[i]){
                    left = mid + 1;
                }
            }
            if(left == number_piles || top[right] < nums[i]){
                top[number_piles] = nums[i];
                number_piles++;
            }else{
                top[right] = nums[i];
            }
        }
        return number_piles;
    }
}
