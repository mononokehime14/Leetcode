#include <include/listnode.h>
/**
 * 108. Convert Sorted Array to Binary Search Tree
 * 比list要简单多了 直接找中点分而治之就可以了
*/
class Solution {
public:
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        return helper(nums, 0, nums.size()-1);
    }
    TreeNode* helper(vector<int> &nums, int left, int right) {
        if(left > right) return NULL;
        int mid = left + (right - left) / 2;
        TreeNode* current = new TreeNode(nums[mid]);
        current->left = helper(nums, left, mid-1);
        current->right = helper(nums, mid+1, right);
        return current;
    }
};