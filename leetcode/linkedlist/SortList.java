package linkedlist;

public class SortList {
    /* 148
     * 感觉思路并不难 就是归并排序merge sort 重点在于细节
     * 首先是寻找中点 由于不仅仅是要找中间点 而且还要linkedlist分成两半
     * 不然的话两个子list就会重复 所以感觉之前找中间点的代码会出现问题 加入一个prev之后 prev得不到更新 如果从dummy开始
     * 故此 先判断是否head head.next为null 然后可以让slow fast直接从head 和head.next开始 注意fast != null && fast.next == null
     * 意味着偶数size slow要再前进一格
     * merge的细节在于剩下的不用while再加入 直接copy listnode就可以了
     */
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }
    private ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode prev = head;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null) {
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(slow);
        return merge(left, right);
    }
    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(a != null && b != null) {
            if(a.val < b.val) {
                cur.next = a;
                cur = cur.next;
                a = a.next;
            }else{
                cur.next = b;
                cur = cur.next;
                b = b.next;
            }
        }
        if(a != null) {
            cur.next = a;
        }
        if(b != null) {
            cur.next = b;
        }
        return dummy.next;
    }
}
