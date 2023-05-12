package linkedlist;

public class RemoveNthNodeFromEndOfList {
    /* debug还得靠自己！基本思路非常简单 要从后面往前数k个数可以用快慢指针
     * 这里有几个细节 首先我们不需要后面往前k个数 而是要k+1 因为我们要用next = next.next delete
     * 其次就是为什么slow和fast都要从dummy开始呢 具体我也说不清 反正这样能过1,2,3,4,5 5的testcase
     * 正好把1 remove掉 从head开始就不可能把head remove 然后k的位置也happen to be正确
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = new ListNode(), fast = new ListNode();
        slow.next = head;
        fast.next = head;
        ListNode output = slow;
        for(int i = 0;fast != null && i < n + 1;i++) {
            fast = fast.next;
        }
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return output.next;
    }
}
