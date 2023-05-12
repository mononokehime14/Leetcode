package linkedlist;

public class LinkedListCycle {
    /* 检测链表或者其他数据结构是否有环的经典算法就是快慢指针 龟兔赛跑
     * 如果无环 fast必然到尾变成null
     * 如果有环 fast每次加二 slow每次加一 必然相遇 数学证明不知道
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}
