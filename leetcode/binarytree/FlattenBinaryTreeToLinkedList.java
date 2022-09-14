class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        /* 这题为什么不能用回溯思路，对二叉树进行遍历呢，因为有覆盖的问题非常麻烦，题目要求inplace
         * 用动规思路divide & conquer可行，但是要注意细节，在把右边接到左边后面的时候，先把左边到底
         * 然后接上，然后root right改成左边，这里注意root left要设置成null不然结果不正确
         */
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode p = root; // root不可能为null
        while (p.right != null) {
            p = p.right; 
        }
        p.right = right;
    }
}