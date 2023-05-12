package bfs;

public class BinaryTreeLevelOrderTraversal {
    /*
     * Level order traversal = BFS, 直接套模版
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> output = new LinkedList<>();
        if(root == null) return output;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new LinkedList<>();
            for(int i = 0;i < size;i++) {
                TreeNode current = q.poll();
                level.add(current.val);
                if(current.left != null) q.add(current.left);
                if(current.right != null) q.add(current.right);
            }
            output.add(level);
        }
        return output;
    }
}
