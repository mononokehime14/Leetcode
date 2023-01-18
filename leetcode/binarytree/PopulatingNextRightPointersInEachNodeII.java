public class PopulatingNextRightPointersInEachNodeII {
    /* 117
     * 这道题和Populating Next Right Pointers是要用一个思路的
     * 正常的BFS是非常好做的 但是会用O(N)的空间 而二叉树的层序遍历是可以不用bfs或者递归的 核心是每次cur要处在这一层的最左边
     * 并且这一层的next顺序在上一层完成了 而这一层做的是连接下一层 这里由于不是perfect二叉树 所以要用一个prev 不断更新 和bfs的时候做法是一样
     * 注意这里dummy的使用 不用dummy的话 就要prev init成null然后每次判断prev是不是null
     */
    public Node connect(Node root) {
        Node cur = root;
        while(cur != null) {
            Node dummy = new Node();
            Node prev = dummy;
            while(cur != null) {
                if(cur.left != null) {
                    prev.next = cur.left;
                    prev = cur.left;
                }
                if(cur.right != null) {
                    prev.next = cur.right;
                    prev = cur.right;
                }
                cur = cur.next;
            }
            cur = dummy.next; //restore cur to the first of current level
        }
        return root;
    }
    // public Node connect(Node root) {
    //     if(root == null) return null;
    //     Queue<Node> q = new LinkedList<>();
    //     Node head = root;
    //     q.add(head);
    //     while(!q.isEmpty()) {
    //         int size = q.size();
    //         Node prev = null;
    //         for(int i = 0;i < size;i++) {
    //             Node current = q.poll();
    //             if(prev != null) {
    //                 prev.next = current;
    //             }
    //             prev = current;
    //             if(current.left != null) q.add(current.left);
    //             if(current.right != null) q.add(current.right);
    //         }
    //         if(prev != null) prev.next = null;
    //     }
    //     return root;
    // }
}
