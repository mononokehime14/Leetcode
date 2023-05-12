class CopyListWithRandomPointer {
    /* 138
     * 这道题的初始解法是用hashmap 将index和node对应起来 这样做是用了额外的空间的
     * 有一种非常巧妙的思路 将copy的node先放在原来的node旁边 作成一个link list 然后random的对应就非常简单 利用原来的random就可以了
     * 最后再将copy的list抽出来
     * 1 -> 2 -> 3, 1 -> 1' -> 2 -> 2' -> 3 -> 3'
     */
    public Node copyRandomList(Node head) {
        Node iter = head, next;
        //first iteration, copy original node, put next to original node
        while(iter != null) {
            next = iter.next;
            Node copy = new Node(iter.val);
            iter.next = copy;
            copy.next = next;
            iter = next;
        }
        //second iteration, assign random 
        iter = head;
        while(iter != null) {
            if(iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }
        
        // third iteration, restore original, extract copy list
        iter = head;
        Node dummy = new Node(-1);
        Node newItr = dummy;
        while(iter != null) {
            next = iter.next.next;
            
            newItr.next = iter.next;
            newItr = iter.next; 
            
            iter.next = next; // restore
            iter = next;
        }
        return dummy.next;
        
        // if(head == null || head.next == null) return head;
        // HashMap<Integer, Node> map = new HashMap<>();
        // Node oriCur = head;
        // Node newCur = new Node(head.val);
        // Node newHead = newCur;
        // int index = 0;
        // while(oriCur != null) {
        //     newCur = new Node(oriCur.val);
        //     if(oriCur.next == null) newCur.next = null;
        //     else
        //         newCur.next = new Node(oriCur.next.val);
        //     map.put(index++, newCur);
        //     newCur = newCur.next;
        //     oriCur = oriCur.next;
        // }
        // oriCur = head;
        // newCur = newHead;
        // while(newCur != null) {
        //     System.out.println(newCur.val);
        //     newCur = newCur.next;
        // }
        // while(oriCur != null) {
        //     if(oriCur.random != null) System.out.println(oriCur.random.val);
        //     newCur.random = map.get(oriCur.random);
        //     newCur = newCur.next;
        //     oriCur = oriCur.next; 
        // }
        // return newHead;
    }
}