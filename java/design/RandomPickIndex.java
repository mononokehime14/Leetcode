package design;

public class RandomPickIndex {
    /* 398
     * 这道题如果不考虑空间限制的话 使用hashmap每个target保存一个list
     * 但是考虑空间限制的话 可以用水塘抽样算法 假设第i个数字是第count个符合target的数字
     * 那么应该保证其被选择的概率是1/count 保持原来的选择的概率是1 - 1/count
     * 但是这道题用水塘抽样在最后一个testcase会TLE 但是官方答案是一样的
     * 只能判断是testcase的问题 最后用hashmap的答案过的 确实这样速度是要快的
     */
    // private int[] nums;
    // private Random rand;
    // private int n;
    // public Solution(int[] nums) {
    //     this.nums = nums;
    //     this.rand = new Random();
    //     this.n = nums.length;
    //     System.out.println(n);
    //     for(int i = 0;i < n / 10;i++) System.out.print(i + " ");
    //     System.out.println();
    // }
    
    // public int pick(int target) {
    //     int count = 0, res = -1;
    //     for(int i = 0;i < n;i++) {
    //         if(nums[i] == target) {
    //             count++;
    //             if(rand.nextInt(count) == 0) {
    //                 res = i;
    //             }
    //         }
    //     }
    //     return res;
    // }
    private HashMap<Integer, List<Integer>> indices;
    private Random rand;
    
    public Solution(int[] nums) {
        this.rand = new Random();
        this.indices = new HashMap<Integer, List<Integer>>();
        int l = nums.length;
        for (int i = 0; i < l; ++i) {
            if (!this.indices.containsKey(nums[i])) {
                this.indices.put(nums[i], new ArrayList<>());
            }
            this.indices.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        int l = indices.get(target).size();
        // pick an index at random
        int randomIndex = indices.get(target).get(rand.nextInt(l));
        return randomIndex;
    }
}
