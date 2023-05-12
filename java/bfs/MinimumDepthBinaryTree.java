package bfs;
import leetcode.TreeNode;
import java.util.*;

public class MinimumDepthBinaryTree {
    /* 直接BFS, 只需要注意一个root为null还有root高度为1的小细节 */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int step = 1;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0;i < sz;i++){
                TreeNode current = q.poll();
                if(current.left == null && current.right == null){
                    // reach leaf
                    return step;
                }
                if(current.left != null){
                    q.add(current.left);
                }
                if(current.right != null){
                    q.add(current.right);
                }
            }
            step++;
        }
        //should not reach here
        return 0;
    }
}
