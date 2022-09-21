package linkedlist;

public class ReverseLinkedList {
    /* 可以用for loop翻转 细节在于保持一个previous node（一开始dummy）和当前node进行交换
     * 可以用递归实现 如果我们假设reverseList能够成功 那么其会返回翻转后的head 也就是翻转前的last
     * 而原先的head next 现在其next指向null 但是head的next仍然指向head next
     * 所以根据这个情况 我们只要把head next的next从null修改成head 然后将head的next修改成null就可以了
     * 递归的停止条件是null或者next是null 如果next是null的话 翻转就是自己做head 可以直接return自己
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
