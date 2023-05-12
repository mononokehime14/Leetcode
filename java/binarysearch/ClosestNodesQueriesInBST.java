public class ClosestNodesQueriesInBST {
    /* 2476
     * BST到底适不适合搜索区间呢 比如搜到2 要小于等于3最大的 那是否可以直接return？ 还是应该往右搜
     * 如果往右搜比3大了怎么办 要return的话还得return之前的2 有点不方便
     * 所以中序遍历直接转成了array操作 然后发现之前的模版有点错误 加了新的注释
     */
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> nums = new ArrayList<>();
        traverse(root, nums);
        // for(int i: nums) System.out.print(i + " ");
        List<List<Integer>> output = new ArrayList<>();
        for(int q: queries) {
            int min = searchFirstSmallerEqual(nums, q);
            int max = searchFirstGreaterEqual(nums, q);
            output.add(Arrays.asList(min, max));
        }
        return output;
    }
    private void traverse(TreeNode root, List<Integer> nums) {
        if(root == null) return;
        traverse(root.left, nums);
        nums.add(root.val);
        traverse(root.right, nums);
    }
    private int searchFirstSmallerEqual(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1;
        while(left <= right) { // left == right we still want to search, because right is not nums.length
            int mid = left + (right - left) / 2;
            if(nums.get(mid) > target) { // change to > if smaller/equal
                right = mid - 1; // not possible to be correct answer
            }else {
                left = mid + 1; // may be correct answer, but we try once more
            }
        }
        // System.out.println(right);
        return right < 0 ? -1 : nums.get(right); 
    }
    public int searchFirstGreaterEqual(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1;
        while(left <= right) { // left == right we still want to search, because right is not nums.length
            int mid = left + (right - left) / 2;
            if(nums.get(mid) < target) { // change to < if greater/equal
                left = mid + 1; // not possible to be correct answer
            }else {
                right = mid - 1; // may be correct answer, but we try once more
            }
        }
        // System.out.println(left);
        return left >= nums.size() ? -1 : nums.get(left); 
    }
}
