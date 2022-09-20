package linkedlist;

public class LinkedListCycle2 {
    /* 只能说想到这个算法的人是天才 我原来以为 无法再使用快慢指针了
     * 没想到还是可以 这里要利用一个特点 就是快指针比慢指针要快一倍 一个跑k步 一个就跑2k步
     * 那么如果有环 他们相遇 这时候fast比slow多跑k步
     * 如果我们让slow退回head 再跑k步 fast在原地接着跑k步 由于有环 他们还是会在同样的地方相遇
     * 吗？
     * 不一定 因为有环的起点 比如在m处 那么slow到起点时离原来的相遇点k-m距离 而fast到起点时 同样离原来的相遇点k-m
     * 所以 第二次起步他们必然在起点相遇
     * 如果起点在相遇点后面呢 一个道理 只要保证slow和fast现在速度相同 这次他们会错过原相遇点 而在起点相遇
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        if(fast == null || fast.next == null) return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
