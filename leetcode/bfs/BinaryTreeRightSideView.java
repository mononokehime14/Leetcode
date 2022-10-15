package bfs;

public class BinaryTreeRightSideView {
    /* 这道题目的思路和层序遍历是一样的 直接BFS 每一层都找到最右边的值就可以了 */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size(), rightmost = 101;
            for(int i = 0;i < size;i++) {
                TreeNode current = q.poll();
                if(current == null) continue;
                rightmost = current.val;
                if(current.left != null) q.add(current.left);
                if(current.right != null) q.add(current.right);
            }
            if(rightmost != 101) output.add(rightmost);
        }
        return output;
    }
}
