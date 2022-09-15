package leetcode;
import leetcode.Node;

public class PopulatingNextRightPointers {
    /* 这道题目的基本思路其实是层序遍历，在每一层遍历的时候把下一层的配对搞了
     * 要处理的细节就是跨越式的配对，通过观察可以发现这必然发生在自己是左边child的情况
     * 所以做一下判断就可以了
     * 这里要注意BFS有O(N)的时间成本，而我们这个方法O(1)
     * 能这么做的原因正是在于完美二叉树，它必然有左child 右child，不需要再用queue来记录下一层的内容
     * 而可以直接开始配对
     */
    public Node connect(Node root){
        Node head = root;
        while(head != null) { //树的层序遍历
            Node cur = head;
            while(cur != null) { //这一层的遍历
                if(cur.left != null) cur.left = cur.right; // must have right
                if(cur.right != null && cur.next != null) { // right.next to next.left
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            head = head.left;
        }
        return root;
    }
    /* 原解法，可能意思和上面的解法是相同的，但是写的比较复杂 */
    // public Node connect(Node root){
    //     if(root == null){
    //         return root;
    //     }
    //     Node result = new Node(root.val, root.left, root.right, null);
    //     return loopTree(result);
    // }
    // private Node loopTree(Node root){
    //     if(root.left != null){
    //         root.left.next = root.right;
    //         root.left = loopTree(root.left);   
    //     }
    //     if(root.right != null){
    //         if(root.next != null){
    //             if(root.next.left != null){
    //                 root.right.next = root.next.left;
    //             }else{
    //                 root.right.next = root.next.right;
    //             }
    //         }else{
    //             root.right.next = null;
    //         }
    //         root.right = loopTree(root.right);        
    //     }
    //     return root;
    // }
    /* 另一个很有意思的遍历解法，利用题目给的完美二叉树的条件，也就是必然有一个左边一个右边 
     * 然后由这一层直接递归下一层的配对
    */
    // public Node connect(Node root){
    //     if(root == null){
    //         return root;
    //     }
    //     traverse(root.left, root.right);
    //     return root;
    // }
    // private void traverse(Node nodeleft, Node noderight){
    //     if(nodeleft == null || noderight == null) return;
    //     nodeleft.next = noderight;
    //     traverse(nodeleft.left, nodeleft.right);
    //     traverse(nodeleft.right, noderight.left);
    //     traverse(noderight.left, noderight.right);
    // }
}
