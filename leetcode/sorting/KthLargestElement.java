package sorting;
public class KthLargestElement {
    /*
     * 这是快速选择的最优解 min heap的方法是nlogk的 而quick select的平均时间复杂度是O(N)的
     * 这是因为第一次全部动一遍 第二次平均动一半 第三次平均动一半的一半 全部加起来小于2n 虽然层高logn 可以想象成剪枝了的快排
     * 最差时间复杂度仍然是O(N ** 2)发生在已经排好序的数组中 但是用random选择或者取三者平均数可以避免最坏情况
     * 空间复杂度表面上来看in place是没有的 但是有logn的递归空间复杂度 最坏的空间复杂度仍然是O(N) 因为如果partition非常不均匀 下一层的长度和这一层接近的话 那么层高就接近O(N)
     * 空间复杂度quick sort和merge sort相同但是组成部分形成了区别 merge sort是需要辅助数组 O(N)的 当然也有递归成本 两者相加 整体O(N)
     */
    public int findKthLargest(int[] nums, int k) {
        if(nums.length == 1){
            return nums[0];
        }
        return quickselect(nums, 0, nums.length - 1, k);
    }
    private int quickselect(int[] nums, int left, int right, int k){
        //partition
        int s = nums[left];
        int n = nums[right];
        int pivot = (left + right) / 2;
        int m = nums[pivot];
        if((s > m && s <= n) || (s >= n && s < m)){
            pivot = left;
            m = s;
        }else if((n > m && n <= s) || (n < m && n >= s)){
            pivot = right;
            m = n;
        }
        //System.out.println("pivot " + left + " " + right + " " + m);
        //swap pivot to the last
        swap(nums, pivot, right);
        int i = left - 1, j = right;
        while(true){
            while(i < j && nums[++i] > m){}
            while(i < j && nums[--j] < m){}
            if(i < j){
                swap(nums, i, j);
            }else{
                break;
            }
        }
        //swap back
        swap(nums, i, right);
        //recusion
        if(k == i + 1){
            return nums[i];
        }else if(k < i + 1){
            return quickselect(nums, left, i - 1, k);
        }else{
            return quickselect(nums, i + 1, right, k);
        }
    }
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
