package linkedlist;

public class MiddleOfTheLinkedList {
    /* 只要知道了快慢指针 这道题的思路简直太容易了
     * 和检测环的龟兔赛跑一样 快指针快慢指针两倍 则一个跑完一个正好在中间
     * 有一个细节 fast可能是已经null推出循环 也可能是next是null退出循环
     * 分别对应奇偶两种list长度 分别处理一下就可以了
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = new ListNode(), fast = new ListNode();
        slow.next = head;
        fast.next = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast != null ? slow.next : slow;
    }
}
