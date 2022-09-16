package bst;

public class SearchBST {
    /* 这比南北朝算法不知道要简单到哪里去了 */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) {
            return root;
        }else if(root.val < val) {
            return searchBST(root.right, val);
        }else{
            return searchBST(root.left, val);
        }
    }
}
