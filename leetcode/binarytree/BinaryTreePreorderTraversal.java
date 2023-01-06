public class BinaryTreePreorderTraversal {
    /* 144
     * Easy题 小酌一杯
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> answer = new ArrayList<>();
        answer.add(root.val);
        answer.addAll(preorderTraversal(root.left));
        answer.addAll(preorderTraversal(root.right));
        return answer;
    }
}
