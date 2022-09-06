public class JumpGame {
    public boolean canJump(int[] nums) {
        /*
         * 以下方法是O(N平方), Space O(N), 可以过但是很呆
         * 可以greedy，利用nums是maximum steps，换言之在maximum steps
         * 的限制下可以随便跳，这样我们只要从右到左，看smallest到最后一个的
         * index，可否reach就行了。
         */
        // boolean[] mem = new boolean[nums.length];
        // mem[0] = true;
        // for(int i = 1;i < mem.length;i++){
        //     mem[i] = false;
        // }
        // for(int i = 1;i < nums.length;i++){
        //     for(int j = 0;j < i;j++){
        //         if(mem[j] && nums[j] + j >= i){
        //             mem[i] = true;
        //             break;
        //         }
        //     }
        // }
        // return mem[mem.length - 1];
        int last_reachable = nums.length - 1;
        for(int i = nums.length - 2;i >= 0;i--){
            if(nums[i] + i >= last_reachable){
                last_reachable = i;
            }
        }
        return last_reachable == 0;
    }
}
