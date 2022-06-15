package leetcode;

import leetcode.TreeNode;
import java.util.*;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    int preorderpointer;
    Map<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] preorder, int[] inorder){
        preorderpointer = 0;
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return output(preorder, 0, inorder.length - 1);
    }
    private TreeNode output(int[]preorder, int left, int right){
        if(right < left){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderpointer]);
        preorderpointer++;
        root.left = output(preorder, left, inorderMap.get(root.val)-1);
        root.right = output(preorder, inorderMap.get(root.val)+1, right);
        return root;
    }
}
