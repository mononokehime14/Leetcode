package lccontest;

public class NumberOfDistinctAverages {
    public int distinctAverages(int[] nums) {
        Set<Double> s = new HashSet<>();
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            s.add((nums[left] + nums[right]) / 2.0);
            left++;
            right--;
        }
        return s.size();
    }
}
