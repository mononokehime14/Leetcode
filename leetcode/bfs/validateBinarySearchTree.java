package bfs;

public class validateBinarySearchTree {
    /*
     * 思路还是分而治之遍历的思路 只是这道题有一些细节 比如 右边的左边也要比root小 那么我们得动态保持一个min 一个max
     * 可是右边的和左边的比较方式不大一样 怎么处理呢 原先想到的是traverseRight和Left分开 但是这是根本行不通的 很眩晕
     * 答案的方法利用了Integer可以为null的特性 右边就把max给null了 左边就把min给null了 然后继承下去
     * 根结点传两个null 就能保证其必然正确 非常巧妙的技巧
     */
    public boolean isValidBST(TreeNode root) {
        return traverse(root, null, null);
    }
    private boolean traverse(TreeNode root, Integer min, Integer max) {
        if(root == null) return true;
        if((min != null && root.val <= min) || (max != null && root.val >= max)) return false;
        return traverse(root.left, min, root.val) && traverse(root.right, root.val, max);
    }
}
