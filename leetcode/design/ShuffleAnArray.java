package design;

public class ShuffleAnArray {
    /* 算法是把当前的数字 和随机一个index的数字交换 自然order是random的 */
    private int[] array;
    private int[] original;
    private int size;
    Random rand = new Random();
    public Solution(int[] nums) {
        original = nums.clone();
        array = nums;
        size = nums.length;
    }
    
    public int[] reset() {
        array = original.clone();
        return array;
    }
    
    public int[] shuffle() {
        for(int i = 0;i < size;i++) {
            swap(i, rand.nextInt(size - i) + i);
        }
        return array;
    }
    private void swap(int left, int right) {
        int temp = array[right];
        array[right] = array[left];
        array[left] = temp;
    }
}
