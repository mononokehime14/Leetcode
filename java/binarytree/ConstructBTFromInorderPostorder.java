package binarytree;

public class ConstructBTFromInorderPostorder {
    /* 和preorder + inorder一模一样的思路 
     * 扩展一下思路的话 如果只给preorder和postorder我们能够构建二叉树吗 我认为起码无法通过同样的方法分而治之
     * 打脸来的也太快了 请看下回分解 确实不能准确的重构出二叉树因为比如前序1 2 3和后序3 2 1有两种二叉树的可能
     * 但是仍然可以构建出来
    */
    HashMap<Integer, Integer> inorderm = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0;i < inorder.length;i++) {
            inorderm.put(inorder[i], i);
        }
        return traverse(postorder, inorder, 0, postorder.length-1, 0, postorder.length-1);
    }
    private TreeNode traverse(int[] postorder, int[] inorder, int pstart, int pend, int istart, int iend) {
        if(pstart > pend) return null; //两个数列必然长度是一样的
        TreeNode root = new TreeNode(postorder[pend]); //后序最后一个必然是root
        int rootindex = inorderm.get(root.val);
        int leftsize = rootindex - 1 - istart + 1;
        root.left = traverse(postorder, inorder, pstart, pstart + leftsize - 1, istart, rootindex - 1);
        root.right = traverse(postorder, inorder, pstart + leftsize, pend - 1, rootindex + 1, iend);
        return root;
    }
}
