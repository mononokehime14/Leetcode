package binarytree;

public class ConstructBinaryTreeFromPreorderPostorder {
    /* 思路在于我们知道root的位置了之后 没有inorder我们无法通过当前的root确定左右子树的边界了
     * 那么怎么办呢 可以用左子树的root 因为在前序中root下一个必然是左子树的root(或者左子树为null 这里
     * 就是题目说的有很多情况的地方 我们可以往左侧构建子树也可以往右侧构建 没有唯一的答案)
     * 我们通过hashmap得到左子树root在postorder中的index 就能知道左子树的边界了
     */
    HashMap<Integer, Integer> postorderm = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for(int i = 0;i < postorder.length;i++) {
            postorderm.put(postorder[i], i);
        }
        return traverse(postorder, preorder, 0, postorder.length-1, 0, postorder.length-1);
    }
    private TreeNode traverse(int[] postorder, int[] preorder, int start1, int end1, int start2, int end2) {
        if(start1 > end1) return null; //两个数列必然长度是一样的
        TreeNode root = new TreeNode(preorder[start2]); //后序最后一个必然是root
        if(start1 != end1) { //only root does not need to recurse again
            int postleftroot = postorderm.get(preorder[start2 + 1]); //前序第二个必然是左子树的root
            int leftsize = postleftroot - start1 + 1;
            root.left = traverse(postorder, preorder, start1, postleftroot, start2 + 1, start2 + leftsize);
            root.right = traverse(postorder, preorder, postleftroot + 1, end1 - 1, start2 + leftsize + 1, end2);
        }
        return root;
    }
}
