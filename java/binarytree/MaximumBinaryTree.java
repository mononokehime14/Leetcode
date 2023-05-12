import java.util.*;
public class MaximumBinaryTree {
    /* 思路非常简单，堪称做的最快的medium，五分钟就写完了
     * 找到最大的，然后分而治之，postorder traverse
     * 这里要注意细节，一开始使用了Arrays.copyOfRange(nums, 0, maxi)
     * 实际上可以用两个pointer，在同一个num上操作
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return traverse(nums, 0, nums.length - 1);
    }
    private TreeNode traverse(int[] nums, int left, int right) {
        if(right < left) return null;
        int max = nums[left], maxi = left;
        for(int i = left + 1;i <= right;i++) {
            if(max < nums[i]) {
                max = nums[i];
                maxi = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = traverse(nums, left, maxi - 1);
        root.right = traverse(nums, maxi + 1, right);
        return root;
    }
    // public TreeNode constructMaximumBinaryTree(int[] nums) {
    //     if(nums.length == 0) return null;
    //     int max = nums[0], maxi = 0;
    //     for(int i = 0;i < nums.length;i++) {
    //         if(max < nums[i]) {
    //             max = nums[i];
    //             maxi = i;
    //         }
    //     }
    //     TreeNode left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, maxi));
    //     TreeNode right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, maxi+1, nums.length));
    //     return new TreeNode(max, left, right);
    // }
    
}
