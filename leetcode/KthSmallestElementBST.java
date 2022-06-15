package leetcode;
import java.util.ArrayList;
import java.util.List;
import leetcode.TreeNode;

public class KthSmallestElementBST {
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
