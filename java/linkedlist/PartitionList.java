package linkedlist;

public class PartitionList {
    /* 和merge有序链表几乎如出一辙的双指针 注意在concat前 要把next断掉 因为最后一个连的还是原来的list里的node
     * 后面可能还跟着别的node 如果l2的next不是空的就会指到l1里的node 造成环
     */
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();
        ListNode h2 = head;
        ListNode l1_1 = l1;
        ListNode l2_2 = l2;
        while(h2 != null){
           if(h2.val < x){
               l1.next = h2;
               l1 = l1.next;
           }else{
               l2.next = h2;
               l2 = l2.next;
           }
            h2 = h2.next;
        }
        l2.next = null; //必须断掉next
        l1.next = l2_2.next;
        return l1_1.next;
    }
}
