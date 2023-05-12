package linkedlist;

public class ReverseNodesInK-Group {
    /* LC25
     * 问题感觉思路不难想到 数出k个 然后经典翻转链表的四步
     * 巧妙的地方在于使用递归 如果不用递归的话 必须使用大loop 会稍微的麻烦一些些 递归的代码非常干净
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode b = head;
        for(int i = 0;i < k;i++) {
            if(b == null) return head; // no need reverse, not enough elements
            b = b.next;
        }
        ListNode newHead = reverse(head, k); // newHead is the start of list, head is not the last element
        head.next = reverseKGroup(b, k);// notice we have to know the start of next k elements, here we need b
        return newHead;
    }
    private ListNode reverse(ListNode head, int k) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = head;
        while(k > 0) {
            nxt = cur.next; // classic 4 steps
            cur.next = pre;
            pre = cur;
            cur = nxt;
            k--;
        }
        return pre;
    }
}
