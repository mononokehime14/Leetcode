package linkedlist;

public class MergeTwoSortedLists {
    /* 双指针 注意出了第一个循环 后面直接if append就可以了 不用再while */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode output = head;
        ListNode l1 = list1, l2 = list2;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                head.next = l2;
                l2 = l2.next;
            }else{
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        if(l1 != null){
            head.next = l1;
        }
        if(l2 != null){
            head.next = l2;
        }
        return output.next;
    }
}
