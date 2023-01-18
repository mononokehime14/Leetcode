package backtrackdfs;

public class WaterAndJugProblem {
    /* 365
     * 我用dfs或者说回溯能过 但是效率极其低下 不过好歹也是自己做出来的 思路就是尝试模拟每种operation
     * 如果某一种operation的选择导致回到了原点(track已经记录过的状态)那么这条路径肯定是不对 所以是false
     * 然后就是能够带备忘录 因为操作次数无所谓 所以看到烧杯装水是同样的a b 就是一样的子问题 这样时间复杂度应该是状态的数量 cap1 * cap2
     * 更好的答案是用数学 事实上是可以计算target是不是cap1和cap2的Greatest Common Divisor的倍数
     * 具体推理还没有想懂 GCD的计算我也没搞懂 反正这个计算GCD的方法是叫Euclids算法
     */
    /* 数学方法 */
    // public boolean canMeasureWater(int cap1, int cap2, int target) {
    //     if(target > cap1 + cap2) return false;
    //     if(cap1 == target || cap2 == target || cap1 + cap2 == target) return true;
    //     return target % GCD(cap1, cap2) == 0;
    // }
    // private int GCD(int a, int b) {
    //     // Euclids GCD
    //     if(b == 0) return a;
    //     return GCD(b, a % b);
    // }
    /* 回溯 */
    private int cap1, cap2, target;
    private HashMap<String, Boolean> memo;
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if(targetCapacity > jug1Capacity + jug2Capacity) return false;
        memo = new HashMap<>();
        this.cap1 = jug1Capacity;
        this.cap2 = jug2Capacity;
        this.target = targetCapacity;
        return traverse(new HashSet<String>(), 0, 0);
    }
    private boolean traverse(HashSet<String> track, int a, int b) {
        String state = a + "," + b;
        if(memo.containsKey(state)) return memo.get(state);
        if(track.contains(state)) return false;
        if(a + b == target) return true;
        track.add(state);
        boolean answer = false;
        if(a < cap1 && traverse(track, cap1, b)) answer = true;
        if(!answer && a > 0 && traverse(track, 0, b)) answer = true;
        if(!answer && b > 0 && traverse(track, a, 0)) answer = true;
        if(!answer && b < cap2 && traverse(track, a, cap2)) answer = true;
        if(!answer && a > 0 && b < cap2) {
            int give = Math.min(a, cap2 - b);
            if(traverse(track, a - give, b + give)) answer = true;
        }
        if(!answer && b > 0 && a < cap1) {
            int give = Math.min(b, cap1 - a);
            if(traverse(track, a + give, b - give)) answer = true;
        }
        track.remove(state);
        memo.put(state, answer);
        return answer;
    }
}
