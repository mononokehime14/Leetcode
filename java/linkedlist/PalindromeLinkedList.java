package linkedlist;

public class PalindromeLinkedList {
    /* 很棒！自己做出来的 思路就是快慢指针找到中点
     * 然后要反转一半的linkedlist 反转哪一半呢 我觉得可以反转前一半 这样随着快慢指针找中点就已经翻转过来了
     * 然后从prev开始 prev是最后一个反转过的 然后prev往前 slow继续往后
     * 这里注意一个细节 如果list number是奇数 那么fast next为null就会结束循环 这时候fast仍然不为null
     * slow实际上在中点 而我们palindrome奇数list不能那中点和中点前面的比较 所以slow要再进一步
     * 反转在细节在于当前的next指向前一个 当前变成原来的next（这里需要用next的备份）前一个变成当前
    */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        ListNode prev = new ListNode(-1);
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        if(fast != null) slow = slow.next;
        while(slow != null) {
            if(prev.val != slow.val) return false;
            prev = prev.next;
            slow = slow.next;
        }
        return true;
    }
}
