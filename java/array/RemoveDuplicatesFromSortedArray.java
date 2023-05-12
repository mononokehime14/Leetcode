package array;

public class RemoveDuplicatesFromSortedArray {
    /* 由于array是sorted duplicates都在一块儿
     * 快慢指针适合解决这个问题 快慢指针的思路可以考虑两份array
     * 如果指针指向的数字相同 可能是duplicate 那么fast继续
     * 不同 则slow和fast一定指向不同的数字 slow可以进一 把fast的数字换到新的位置上
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int slow = 0, fast = 0;
        while(fast < nums.length){
            if(nums[fast] != nums[slow]){
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
