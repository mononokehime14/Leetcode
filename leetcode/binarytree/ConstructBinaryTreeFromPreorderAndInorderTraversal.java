package binarytree;

import leetcode.TreeNode;
import java.util.*;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /* 一开始的思路是很粗糙的，我是想办法将inorder补全，比如没有children的也加null在list里
     * 但是实际上有更加优雅的思路，首先认识到这是可以用动规思路分而治之的
     * 如果我们能用左右两边代表的list 分别先构建二叉树 那么剩下的就只是取preorder第一个作为root
     * 关键是如何能够确定左右两边的list呢 这里要用到顺序的特点 对于inorder来说 左子树必然在list中root的左边
     * 对于preorder来说 root必然在list的第一个 然后是左子树 然后是右子树
     * 那么实际上 我们可以先知道root 然后用inorder找到其左子树的list 然后利用其左子树的长度 推算出preorder list
     * 中左子树的区域
     * 妙 实在是妙 高 实在是高
     */
    HashMap<Integer, Integer> inorderm = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder){
        for(int i = 0;i < inorder.length;i++) {
            inorderm.put(inorder[i], i);
        }
        return traverse(preorder, inorder, 0, preorder.length-1, 0, preorder.length-1);
    }
    private TreeNode traverse(int[] preorder, int[] inorder, int pstart, int pend, int istart, int iend) {
        if(pstart > pend) return null; //两个数列必然长度是一样的
        TreeNode root = new TreeNode(preorder[pstart]); //前序第一个必然是root
        int rootindex = inorderm.get(root.val);
        root.left = traverse(preorder, inorder, pstart + 1, pstart + rootindex - istart, istart, rootindex - 1);
        root.right = traverse(preorder, inorder, pstart + rootindex - istart + 1, pend, rootindex + 1, iend);
        return root;
    }
    // int preorderpointer;
    // Map<Integer, Integer> inorderMap;
    // public TreeNode buildTree(int[] preorder, int[] inorder){
    //     preorderpointer = 0;
    //     inorderMap = new HashMap<>();
    //     for (int i = 0; i < inorder.length; i++) {
    //         inorderMap.put(inorder[i], i);
    //     }
    //     return output(preorder, 0, inorder.length - 1);
    // }
    // private TreeNode output(int[]preorder, int left, int right){
    //     if(right < left){
    //         return null;
    //     }
    //     TreeNode root = new TreeNode(preorder[preorderpointer]);
    //     preorderpointer++;
    //     root.left = output(preorder, left, inorderMap.get(root.val)-1);
    //     root.right = output(preorder, inorderMap.get(root.val)+1, right);
    //     return root;
    // }
}
