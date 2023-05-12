package array;

public class MajorityElements {
    public int majorityElement(int[] nums) {
        /* 这道题当然可以用HashMap count出现的次数 然后再迭代一遍寻找出现次数大于n/2的数字
         * 但是这道题显然有很厉害的方法 可以节省空间
         * 我一开始想的排序 然后直接检查n/2之后的element 但是排序的时间成本要高于原方法
         * 最后的答案证明是非常巧妙的 我们保持一个count 假设遇到一个数字++ 遇到别的数字-- 
         * 如果一个数字出现超过 n/2 次 那么count最后必然是正数 
         */
        int target = 0, count = 0;
        for(int i: nums) {
            if(count == 0) {
                target = i;
            }
            if(target == i) {
                count++;
            }else{
                count--;
            }
        }
        return target;
    }
}
