package bst;

public class RecoverBinarySearchTree {
    /* 99
     * 好久不做二叉搜索树的题目完全不知道利用bst的特点
     * 这道题我的想法是遍历然后保持一些up down parent treenode 拿现在的node和这些比较 < down则和down swap
     * 但是一些细节没有考虑好 而且还没有搞清楚swap只要换val就可以了 在那里搞swap node 搞不出来 最后的解法还是有问题
     * 一个简单的答案是inorder traverse 形成list 然后检查颠倒顺序的两个 互换val
     */
    private List<TreeNode> inorder;
    public void recoverTree(TreeNode root) {
        inorder = new ArrayList<>();
        traverse(root);
        int size = inorder.size();
        TreeNode a = null, b = null;
        for(int i = 0;i < size - 1;i++) {
            if(inorder.get(i).val > inorder.get(i+1).val){
                a = inorder.get(i);
                break;
            }
        }
        for(int i = size - 1;i > 0;i--) {
            if(inorder.get(i).val < inorder.get(i-1).val){
                b = inorder.get(i);
                break;
            }
        }
        swap(a, b);
    }
    private void traverse(TreeNode root) {
        if(root == null) return;
        traverse(root.left);
        inorder.add(root);
        traverse(root.right);
    }
    private void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
    /* 错误答案 2 3 1会把2 3互换 实际答案应该是1 3互换 */
    // private boolean earlyStop = false;
    // public void recoverTree(TreeNode root) {
    //     traverse(root, null, null);
    // }
    // private void traverse(TreeNode root, TreeNode up, TreeNode down) {
    //     if(earlyStop) return;
    //     if(root == null) return;
    //     if(up != null && root.val > up.val) {
    //         swap(root, up);
    //         earlyStop = true;
    //     }else if(down != null && root.val < down.val) {
    //         swap(root, down);
    //         earlyStop = true;
    //     }
    //     traverse(root.left, root, down);
    //     traverse(root.right, up, root);
    // }
}
