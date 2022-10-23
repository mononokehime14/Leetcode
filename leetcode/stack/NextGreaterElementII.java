package stack;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        /* 直接linear search nums2 是n平方, 用石知一栈可以减到n */
        int n = nums.length;
        int[] output = new int[n];
        Arrays.fill(output, -1);
        Stack<Integer> index_stack = new Stack<>();
        int max = -2147483648;
        int max_index = 0;
        int circle = 0;
        int i = 0;
        while(true){
            while(!index_stack.empty() && nums[i] > nums[index_stack.peek()]){
                output[index_stack.pop()] = nums[i];
            }
            index_stack.push(i);
            if(nums[i] > max){
                max = nums[i];
                max_index = i;
            }
            i++;
            if(circle > 0 && i > max_index) break;
            if(i >= n){
                circle++;
                i = 0;
            } 
        }
        return output;
    }
}
