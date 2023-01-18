package bfs;

public class ReverseOddLevelsOfBinaryTree {
    /* 2415
     * 思路就只是BFS的层序遍历 一开始我是一个list加入当前层 然后多一个loop 把当前层（如果奇数）的node val对换
     * 实际上答案在偶数层把下一层的加入list 然后下一层loop的时候直接复职 这样都是O(N) 但是这个方法应该是只有一遍
     * 原先的方法则是两遍
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> q = new LinkedList<>();
        LinkedList<Integer> level = new LinkedList<>();
        q.add(root);
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> tempLevel = new LinkedList<>();
            for(int i = 0;i < size;i++) {
                TreeNode current = q.poll();
                if(step % 2 == 1) {
                    current.val = level.removeLast();
                }
                if(current.left != null) {
                    q.add(current.left);
                    tempLevel.addLast(current.left.val);
                }
                if(current.right != null) {
                    q.add(current.right);
                    tempLevel.addLast(current.right.val);
                }
            }
            level = tempLevel;
            // if(step % 2 == 1) {
            //     int left = 0, right = size - 1;
            //     while(left < right) {
            //         int temp = level.get(left).val;
            //         level.get(left).val = level.get(right).val;
            //         level.get(right).val = temp;
            //         left++;
            //         right--;
            //     }
            // }
            step++;
        }
        return root;
    }
}
