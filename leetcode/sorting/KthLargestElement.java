package sorting;
public class KthLargestElement {
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
            while(j > i && nums[--j] < m){}
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
