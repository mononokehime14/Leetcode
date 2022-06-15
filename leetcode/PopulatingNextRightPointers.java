package leetcode;
import leetcode.Node;

public class PopulatingNextRightPointers {
    public Node connect(Node root){
        if(root == null){
            return root;
        }
        Node result = new Node(root.val, root.left, root.right, null);
        return loopTree(result);
    }
    private Node loopTree(Node root){
        if(root.left != null){
            root.left.next = root.right;
            root.left = loopTree(root.left);   
        }
        if(root.right != null){
            if(root.next != null){
                if(root.next.left != null){
                    root.right.next = root.next.left;
                }else{
                    root.right.next = root.next.right;
                }
            }else{
                root.right.next = null;
            }
            root.right = loopTree(root.right);        
        }
        return root;
    }
}
