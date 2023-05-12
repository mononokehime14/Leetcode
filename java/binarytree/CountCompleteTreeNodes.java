public class CountCompleteTreeNodes {
    /* 222
     * 完完全全自己想出来的方法 100%时间 我的思路是
     * 首先如果是普通的二叉树计算nodes数量 则遍历一遍O(N) 但是题目要求少于O(N) 则必然要利用Complete Binary Tree的特性
     * 我的思路是 除了最后一层 上面是perfect binary tree 通过数高就可以推出nodes数量 这一步计算层高是O(logN)
     * 怎么确定最后一层的数量呢 先搜右子树有没有leave 有的话就return 找到的最右边的leave
     * 找不到再找左边 这种算法的最坏复杂度是O(N)的 如果最后一层只有第一个 那我们是全部搜完的 只是说平均情况下 能够早停
     * 
     * 另一种解法（comment掉的）的时间复杂度是logN * logN 是计算左右子树的高度 如果相同则是perfect binary tree直接推算nodes
     * 如果不同则继续分而治之 注意时间复杂度不是nlogn的原因 是左右子树中 必然有一个perfect binary tree 不用接着递归下去
     */
    public int countNodes(TreeNode root) {
        int treeHeight = countHeight(root, 0);
        int count = Math.max(0, (int)Math.pow(2, (treeHeight - 1)) - 1);
        int leavesCount = search(root, 0, treeHeight, 1);
        count += leavesCount != -1 ? leavesCount : 0;
        return count;
    }
    private int countHeight(TreeNode root, int height) {
        if(root == null) return height;
        return countHeight(root.left, height + 1);
    }
    private int search(TreeNode root, int height, int treeHeight, int index) {
        if(root == null) return -1; // no leave
        height++;
        if(height == treeHeight) { // leave
            return index;
        }
        int searchRight = search(root.right, height, treeHeight, (index - 1) * 2 + 2);
        if(searchRight != -1) return searchRight;
        return search(root.left, height, treeHeight, (index - 1) * 2 + 1);
    }
    // public int countNodes(TreeNode root) {
    //     TreeNode l = root, r = root;
    //     // 沿最左侧和最右侧分别计算高度
    //     int hl = 0, hr = 0;
    //     while (l != null) {
    //         l = l.left;
    //         hl++;
    //     }
    //     while (r != null) {
    //         r = r.right;
    //         hr++;
    //     }
    //     // 如果左右侧计算的高度相同，则是一棵满二叉树
    //     if (hl == hr) {
    //         return (int)Math.pow(2, hl) - 1;
    //     }
    //     // 如果左右侧的高度不同，则按照普通二叉树的逻辑计算
    //     return 1 + countNodes(root.left) + countNodes(root.right);
    // }
}
