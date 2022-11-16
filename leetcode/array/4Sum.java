package array;

public class 4Sum {
    /* 这他妈的嵌套的也太复杂了
     * 首先 用他妈的回溯 还是用3sum的那一套 其实我认为我的回溯可能是正确的 因为时间复杂度在O ** 4 只是遇到了一些bug
     * 而且沿用3sum的思路可能更加方便记忆一点
     * 这里沿用3sum就要多加一层for loop
     * 其他没有任何多余的考虑 注意四个数字加起来的时候 用了long 并且每次只加一个数字 防止整形溢出
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> output = new ArrayList<>();
        int n = nums.length;
        for(int i = 0;i < n - 2;i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i + 1;j < n - 1;j++) {
                if(j > i + 1 && nums[j] == nums[j-1]) continue;
                int left = j+1, right = n - 1;
                while(left < right) {
                    long temp = nums[left] + nums[right]; // 防止整形溢出
                    temp += nums[j];
                    temp += nums[i];
                    if(temp > target) {
                        right--;
                        while(left < right && nums[right] == nums[right+1]) right--;
                    }else if(temp < target) {
                        left++;
                        while(left < right && nums[left] == nums[left-1]) left++;
                    }else{
                        output.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while(left < right && nums[left] == nums[left-1]) left++;
                        while(left < right && nums[right] == nums[right+1]) right--;
                    }
                }
            }
        }
        return output;
    }
    // private List<List<Integer>> threeSum(int[] nums, int target, int n, int start) {
    //     List<List<Integer>> output = new ArrayList<>();
    //     for(int i = start;i < n - 1;i++) {
    //         if(i > start && nums[i] == nums[i-1]) continue;
    //         List<List<Integer>> temp = twoSum(nums, target - nums[i], n, i+1);
    //         for(List<Integer> t: temp) {
    //             for(Integer z: t) {
    //                 System.out.print(i);
    //             }
    //             System.out.println();
    //         }
    //         for(List<Integer> t: temp) {
    //             List<Integer> t2 = new ArrayList<>(t);
    //             t2.add(nums[i]);
    //             output.add(new ArrayList<>(t2));
    //         }
    //     }
    //     return output;
    // }
    // private List<List<Integer>> twoSum(int[] nums, int target, int n, int start) {
    //     List<List<Integer>> output = new ArrayList<>();
    //     int left = start, right = n - 1;
    //     while(left < right) {
    //         int temp = nums[left] + nums[right];
    //         if(temp > target) {
    //             right--;
    //             while(left < right && nums[right] == nums[right-1]) right--;
    //         }else if(temp < target) {
    //             left++;
    //             while(left < right && nums[left] == nums[left+1]) left++;
    //         }else{
    //             System.out.println(nums[left] + " " + nums[right]);
    //             output.add(Arrays.asList(nums[left] + nums[right]));
    //             left++;
    //             right--;
    //             while(left < right && nums[left] == nums[left+1]) left++;
    //             while(left < right && nums[right] == nums[right-1]) right--;
    //         }
    //     }
    //     return output;
    // }
    // Set<List<Integer>> output;
    // public List<List<Integer>> fourSum(int[] nums, int target) {
    //     output = new HashSet<>();
    //     backtrack(new LinkedList<Integer>(), nums, 0, target, 0, 0);
    //     return new ArrayList<>(output);
    // }
    // private void backtrack(LinkedList<Integer> track, int[] nums, int start, int target, int partial, int level) {
    //     if(level == 4) {
    //         if(partial == target) output.add(new ArrayList<>(track));
    //         return;
    //     }
    //     for(int i = start; i < nums.length;i++) {
    //         track.add(nums[i]);
    //         partial += nums[i];
    //         backtrack(track, nums, i + 1, target, partial, level+1);
    //         partial -= nums[i];
    //         track.removeLast();
    //     }
    // }
}
