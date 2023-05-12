package linkedlist;

public class RemoveNodesFromLinkedList {
    /* 2487
     * 这道题本来是想用单调队列做的 但是有一个问题就是deletenode和queue里拿下来的可能是对不上的
     * 比如13 3 8 我这个算法就会在8加入的时候 想要删3却把13删掉
     * 所以屈服了 最后拿着一个list记录右边的max 这样过了
     */
    public ListNode removeNodes(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        List<Integer> postMax = new ArrayList<Integer>();
        ListNode temp = head;
        while(temp != null) {
            postMax.add(temp.val);
            temp = temp.next;
        }
        for(int i = postMax.size() - 2;i >= 0;i--) {
            postMax.set(i, Math.max(postMax.get(i+1), postMax.get(i)));
        }
        int index = 0;
        while(head != null) {
            if(postMax.get(index) > head.val) {
                prev.next = head.next;
            }else{
                prev = head;
            }
            index++;
            head = head.next;
        }
        return dummy.next;
        // ListNode dummy = new ListNode(-1, head);
        // //ListNode prev = dummy;
        // ListNode deletePrev = dummy;
        // ListNode deleteHead = head;
        // LinkedList<Integer> q = new LinkedList<>();
        // while(head != null) {
        //     while(!q.isEmpty() && q.getLast() < head.val) {
        //         // remove
        //         System.out.println(deleteHead.val + " " + q.getLast() + " vs " + head.val);
        //         deletePrev.next = deleteHead.next;
        //         deleteHead = deleteHead.next;
        //         q.removeLast();
        //     }
        //     q.addLast(head.val);
        //     // prev = head;
        //     head = head.next;
        // }
        // return dummy.next;
    }
}
