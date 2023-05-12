package array;

public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        /** 287
         * 这道题非常之难 竟然使用的链表检查循环的思路
         * 难度在于 要O(N)时间复杂度 O(1)空间 同时不能更改原来的数组 不然我们可以用更改相应index的正负的方法记录出现次数
         * 于是出现了一个天才的思路 基于value -> index -> value...这样的访问思路 我们可以把数组理解为某种linked list
         * 这是利用了数值一定在1到N之间 和我们之前想用的方法一样 同时关键理解在于 如果没有重复 那么不会访问同一个地方两次 比如array 1 3 4 2
         * 0 -> 1
         * 1 -> 3
         * 2 -> 4
         * 3 -> 2
         * 0 -> 1 -> 3 -> 2 -> 4, 4超出range 但是现在有重复 那么就会有一个地方被访问两次 这样 快慢指针就会相遇了
         * 那么沿用之前快慢指针检查循环出现位置的代码 就是先让其相遇 然后一个回退重走 必然在环起点重遇
         * 这里slow不能改成0 会出现无法相遇的问题 这揭示了这类算法的一个细节 就是slow fast其实要一起起步 slow走一步 fast走两步
         * 这样才能保证他们相遇 在原来的链表当中 停止条件是fast != null && fast.next != null 自然不会有问题
         * 在这道题目中 一开始一起起步停止条件会出问题 那么其实可以是一个do while思路
         */
        int slow = nums[0];
        int fast = nums[slow];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // int slow = 0;
        // int fast = 0;
        // do {
        //     slow = nums[slow];
        //     fast = nums[nums[fast]];
        // } while (slow != fast);
        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
