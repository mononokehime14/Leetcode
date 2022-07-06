import leetcode.TreeNode;
public class LowestCommonAncestorBinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
         * 此题不同之处在于是Binary Search Tree，因而如果一个root，大于等于p的值但是小于等于q的值
         * 则其必然为LCA。这是因为从上而下的loop，我们发现一个数大于等于比如p，必然不是在p下面右边，
         * 而是p自己或者其parent，q同理，则一个处于中间的parent，必然为LCA。
         */
        int small, large;
        if(p.val > q.val){
            large = p.val;
            small = q.val;
        }else{
            small = p.val;
            large = q.val;
        }
        return search(root, small, large);
    }
    private TreeNode search(TreeNode root, int p, int q){
        if(root == null){ 
            return root;
        }
        if(root.val < p){
            return search(root.right, p, q);
        }else if(root.val > q){
            return search(root.left, p, q);
        }else{
            return root;
        }
    }
}
