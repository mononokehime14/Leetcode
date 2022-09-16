package bst;

public class DeleteNodeInBST {
    /* 首先是二叉树搜索没有疑问 主要的难点在于如何找到node后进行删除
     * 这里首先要明白我们return的是root 如果要在找到的那个block就把整个树改好太困难了 要保持parent的信息等等
     * 所以我们在搜索的时候用比如root.right = deleteNode(root.right, key); 这样直接用递归上来的root更新我们的right 会方便很多
     * 然后明白几种情况
     * 1.leaf 直接return null 代表不要了
     * 2. 有左右两边其中之一 我们就把child替换上来
     * 3. root有两个child 我们要找到右边子树的最小node 把它替换上来
     * 怎么把它替换上来了 当然找到它 然后把它的left更新成root left right更新成root right
     * 在此之前 我们需要从右子树中把它删掉 不然不就circle了 删掉就可以直接递归了 这能保证找到最后是一个leaf的
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) {
            // delete
            if(root.left == null && root.right == null) {
                // this node is leaf
                return null; //为什么return null就可以了呢 这是因为call的那边正在做update 我们不必在这里对树作调整了
            }else if (root.left == null && root.right != null) {
                return root.right;
            }else if(root.right == null && root.left != null) {
                return root.left;
            }else {
                // find the smallest node from the right sub tree
                TreeNode cur = root.right; // right must not be null
                while(cur.left != null) {
                    cur = cur.left;
                }
                root.right = deleteNode(root.right, cur.val);
                cur.left = root.left;
                cur.right = root.right;
                return cur;
            }
        }else if(root.val < key) {
            root.right = deleteNode(root.right, key);
        }else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    } 
}
