package linkedlist;

public class MergeKSortedLists {
    /* 这道题目基于merge有序链表的理解 区别在于有k个
     * 简单的思路是保持k个node 每次比较全部然后跳出最小的加入
     * 这里的解法使用了二叉heap 也就是priority queue 注意by default这是最大的在上面的
     * 我们更改了comparator (a.val - b.val) 也就是说最小的在最上面
     * 这样每次只拿最小val的node 剩下的就只是基本的了
     * 优先队列 pq 中的元素个数最多是 k，所以一次 poll 或者 add 方法的时间复杂度是 O(logk)；
     * 所有的链表节点都会被加入和弹出 pq，所以算法整体的时间复杂度是 O(Nlogk)，其中 k 是链表的条数，
     * N 是这些链表的节点总数
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a,b)->(a.val - b.val));
        for(ListNode i: lists){
            if(i != null) pq.add(i);
        }
        while(!pq.isEmpty()){
            ListNode current = pq.poll();
            curr.next = current;
            curr = curr.next;
            if(current.next != null) pq.add(current.next);
        }
        return dummy.next;
    }
}
