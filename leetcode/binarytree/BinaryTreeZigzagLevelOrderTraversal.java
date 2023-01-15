
import leetcode.TreeNode;
import java.util.*;

class BinaryTreeZigzagLevelOrderTraversal {    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        int level = 0;
        traversal(root, level, output);
        return output;
    }

    private void traversal(TreeNode parent, int level, List<List<Integer>> output){
        if(parent == null){
            return;
        }
        if(output.size() <= level){
            List<Integer> layer = new ArrayList<Integer>();
            output.add(layer);
        }
        if (level % 2 == 0){
            output.get(level).add(parent.val);
        }else{
            output.get(level).add(0, parent.val);
        }
        level++;
        traversal(parent.left, level, output);
        traversal(parent.right, level, output);
    }
}