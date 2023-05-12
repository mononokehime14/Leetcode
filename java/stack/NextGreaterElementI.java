package stack;
import java.util.HashMap;
import java.util.Stack;
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /* 直接linear search nums2 是n平方, 用石知一栈可以减到n 
         * 单调栈
        */
        HashMap<Integer, Integer> num1_h = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(nums2[0]);
        for(int i = 1;i < nums2.length;i++){
            while(!stack.empty() && nums2[i] > stack.peek()){
                num1_h.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        for(int i = 0;i < nums1.length;i++){
            nums1[i] = num1_h.containsKey(nums1[i]) ? num1_h.get(nums1[i]) : -1;
        }
        return nums1;
    }
}
