package bst;
import java.util.ArrayList;
import java.util.List;
import leetcode.TreeNode;

public class KthSmallestElementBST {
    /* 利用BST的中序遍历就是升序的特点 可以进行中序遍历然后择首
     * 但是这么做时间复杂度是O(N) 有没有更加快速的方法呢
     * 如果我们能每个node是第几个node 那么就可以DFS到从最左边开始的第K个node
     * 如果TreeNode中有index 可以用这样的方法 时间复杂度降低到树的高度 O(logN) 假设平衡
     */
    List<Integer> inorder;
    int stop;
    public int kthSmallest(TreeNode root, int k) {
        inorder = new ArrayList<>();
        stop = 0;
        traversal(root, k);
        return inorder.get(k-1);
    } 
    private void traversal(TreeNode root, int k){
        if(root.left != null){
            traversal(root.left, k);
        }
        if(stop < k){
            inorder.add(root.val);
            stop++;
        }else{
            return;
        }
        if(root.right != null){
            traversal(root.right, k);
        }
    }
}
