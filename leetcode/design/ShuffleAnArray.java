package design;

public class ShuffleAnArray {
    /* 算法是把当前的数字 和随机一个index的数字交换 自然order是random的 
     * 分析洗牌算法正确性的准则：产生的结果必须有 n! 种可能。这个很好解释，因为一个长度为 n 的数组的全排列就有 n! 种，也就是说打乱结果总共有 n! 种。算法必须能够反映这个事实，才是正确的。
     * 有了这个原则再看代码应该就容易理解了：对于 nums[0]，我们把它随机换到了索引 [0, n) 上，共有 n 种可能性；对于 nums[1]，我们把它随机换到了索引 [1, n) 上，共有 n - 1 种可能性；
     * 对于 nums[2]，我们把它随机换到了索引 [2, n) 上，共有 n - 2 种可能性；
     * 以此类推，该算法可以生成 n! 种可能的结果，所以这个算法是正确的，能够保证随机性。
    */
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
