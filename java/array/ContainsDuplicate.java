package array;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        /* 我他妈的还查了答案 想来想去难道就用hashset就过了吗 真的是只用hashset就可以了 */
        Set<Integer> mem = new HashSet<>();
        for(int i: nums) {
            if(mem.contains(i)) return true;
            mem.add(i);
        }
        return false;
    }
}
