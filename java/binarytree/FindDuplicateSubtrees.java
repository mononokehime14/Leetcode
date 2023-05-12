package binarytree;
import java.util.*;
public class FindDuplicateSubtrees {
    /* 一开始想到了回溯遍历是不行的 但是如何动规分而治之呢 如果考虑两个子树是不是有重复
     * 然后据此再查 根本毫无思路 这里没有想到的是查重复并不是什么很复杂的算法 而仅仅只是需要hashmap
     * hashset也可但是题目不想要重复加入同样的duplicate 所以需要hashmap记录出现的次数
     * 这样的话 我只需要后序遍历 每次构建出我的树的序列化表达 然后交给hashmap查重复就可以了
    */
    List<TreeNode> output;
    HashMap<String, Integer> mem;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        mem = new HashMap<>();
        output = new ArrayList<>();
        traverse(root);
        return output;
    }
    private String traverse(TreeNode root) {
        if(root == null) {
            return " #";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(traverse(root.left));
        sb.append(traverse(root.right));
        sb.append(" " + String.valueOf(root.val));
        if(mem.containsKey(sb.toString())){
            if(mem.get(sb.toString()) == 1) {
                mem.put(sb.toString(), 2);
                output.add(root);
            }
        }else{
            mem.put(sb.toString(), 1);
        }
        return sb.toString();
    }
}
