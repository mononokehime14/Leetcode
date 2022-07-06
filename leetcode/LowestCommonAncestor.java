import leetcode.TreeNode;
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
         * 首先我们发现，如果一个node左边能找到p，右边能找到q，则其必然为LCA，多上一个level都不行
         * 其次，如果我们直接找到了p或者q，则其必然是LCA，因为它的parents已经被搜过了不是，而题目保证了
         * 必然有LCA，那么另外的一个就必然在其下面，所以其本身必然是LCA
         */
        return search(root, p, q);
    } 
    private TreeNode search(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root.val == p.val || root.val == q.val){ //利用提前跳出把null check也合并进来
            return root;
        }
        TreeNode left = search(root.left, p, q);
        TreeNode right = search(root.right, p, q);
        if(left != null && right != null){
            return root;
        }
        return (left != null) ? left : right; //这个语句要快
    }
}
