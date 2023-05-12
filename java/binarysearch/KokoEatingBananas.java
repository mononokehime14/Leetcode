public class KokoEatingBananas {
    /* 875
     * 和1011 410是一摸一样的思路 只是在区间的选择上有点区别 让人回想起snowflake想出来怎么做但是区间多了1的痛
     * 这里是假设一定有解了 所以不可能一个k=最大值还不成功 right这里如果是sum就会面临痛苦的overflow long也救不了
     */
    public int minEatingSpeed(int[] piles, int h) {
        int right = Arrays.stream(piles).max().getAsInt(); // guarantee to have an answer, so cannot be bigger than max
        int left = 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(!isValid(piles, h, mid)) {
                // System.out.println(mid + " cannot");
                left = mid + 1;
            }else{
                // System.out.println(mid + " can");
                right = mid - 1;
            }
        } 
        return left;
    }
    private boolean isValid(int[] nums, int h, int k) {
        int count = 0;
        for(int i : nums) {
            count += i % k == 0 ? i / k : (i / k) + 1;
            if(count > h) return false;
        }
        return true;
    }
}
