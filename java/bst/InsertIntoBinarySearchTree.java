package bst;

public class InsertIntoBinarySearchTree {
    /* 701
     * 这道题其实是基于某种search in range的思路 BST用来search特定的val当然方便
     * 但是能不能像数组的binary search一样 搜出第一个smaller than的呢
     * 我思考了一下 应该是需要带一个parent/prev node 搜到底没有搜到就return parent 或者比较一下？
     * 不过这道题其实有点这种意思 但是不需要这个实现 我们类似寻找first smaller 找到小于的就在右边再试试
     * 到底了之后 直接insert
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }else{
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
