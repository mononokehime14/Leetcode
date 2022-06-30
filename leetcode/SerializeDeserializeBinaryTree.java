import leetcode.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class SerializeDeserializeBinaryTree {
    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));
    // Encodes a tree to a single string.
    //注意当在loop中想要string concatenation时可用string builder提升performance
    //事实上任何考虑performance的情况都应该使用string builder
    public String serialize(TreeNode root) {
        StringBuilder output = new StringBuilder();
        preorder(root, output);
        if(output != null && output.length() > 0 && output.charAt(output.length()-1) == ','){
            return output.substring(0, output.length() - 1);
        }
        return output.toString();
    }
    private void preorder(TreeNode root, StringBuilder output){
        if(root == null){
            output.append("null,");
            return;
        }
        output.append(String.valueOf(root.val)).append(",");
        preorder(root.left, output);
        preorder(root.right, output);
    }

    // Decodes your encoded data to tree.
    //注意tree的recursive,并非一定要pass一个root, 如果root null否要决,不如直接开始, 最后return一个treenode回
    int index;
    public TreeNode deserialize(String data) {
        String[] datal = data.split(",");
        int l = datal.length;
        index = 0;
        return preorder_construct(datal, l);
        //preorder_traversal(output); 
    }
    private TreeNode preorder_construct(String[] datal, int data_length){
        if(datal[index].equals("null")){
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(datal[index]));
        index++;
        root.left = preorder_construct(datal, data_length);
        root.right = preorder_construct(datal, data_length);
        return root;
    }
    private void preorder_traversal(TreeNode root){
        if(root == null){
            System.out.print("null ");
            return;
        }
        System.out.print(root.val);
        preorder_traversal(root.left);
        preorder_traversal(root.right);
    }
}
