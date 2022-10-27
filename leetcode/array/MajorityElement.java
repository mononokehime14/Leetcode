package array;
import java.util.HashMap;
public class MajorityElement {
    public int majorityElement(int[] nums) {
        /*
         * 下面的做法能够AC，但是显得人很呆
         * 更快的方法立足于，必然有一个数字超出majority，这就意味着我们只要看某种领先定位就可
         * 纵然比如开始3个a，后面3个b，diff为0，接下来一个c，res就暂时变成了c
         * 但是这时是没有majority的，而必然有一个数字有4以上，abc任意一个必然再加二，使得其夺得
         * diff领先成为res。
         */
        // HashMap<Integer,Integer> mem = new HashMap<>();
        // for(int i = 0;i < nums.length;i++){
        //     if(mem.containsKey(nums[i])){
        //         mem.put(nums[i], mem.get(nums[i]) + 1);
        //     }else{
        //         mem.put(nums[i], 1);
        //     }
        // }
        // int half = nums.length / 2;
        // for(Integer i : mem.keySet()){
        //     if(mem.get(i) > half){
        //         return i;
        //     }
        // }
        // return -1;
        int diff = 0;
        int res = 0;
        for(int i : nums){
            if(diff == 0){
                res = i;
            }
            diff += (i == res) ? 1 : -1;
        }
        return res;
    } 
}
