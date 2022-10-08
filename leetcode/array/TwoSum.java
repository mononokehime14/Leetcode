package array;

public class TwoSum {
    /* 通过target - i 我们知道前面需要的值是多少 如果前面有这个值那么我们将得到一个解 */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++) {
            int y = target - nums[i];
            if(map.containsKey(y)) {
                return new int[] {map.get(y), i};
            }
            map.put(nums[i],i);
        }
        return new int[] {0,0};
    }
}
