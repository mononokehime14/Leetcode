package dfs;

public class CreateComponentsWithSameValue {
    /* 2440
     * 这道题的核心是基于一种特殊的dfs计算subtree sum的方式 用dfs计算子树的sum当然很简单 但是怎么来算 整个树是否能够分成平等的
     * 每个子树加起来为target的子树呢 一遍dfs还是可以 只需要改动一行return return的时候 如果sum == target 就return 0
     * 这样 这颗子树的上一层 就不会把这颗子树算进去了！这就是sum independent的技巧 至于sum > target的时候的早停 是因为这个target肯定不可以了
     * 所以可以早停
     * 这个算法的复杂度就是maxNumber * N 其中maxNumber是受限于所有数的和与最大值的 这也是这道题的数据限制是2 * 10 ** 4的原因
     * 比n**2稍微低些 又达不到NlogN的程度
     * 但是这样是过不了testcase的 必须要在dfs前判断 sum % cut == 0 首先反正不能整除的肯定不能平等分 其次我们可以发现 假设sum是一个奇数的话 
     * 偶数的就过不了这个条件了 然后再接着看剩下的因数 这应该能大幅度降低剪枝 没有这个是会TLE的
     */
    private int[] nums;
    private List<Integer>[] graph;
    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        int maxValue = 0, sum = 0;
        for(int i : nums) {
            maxValue = Math.max(maxValue, i);
            sum += i;
        }
        int maxNumber = sum / maxValue;
        if (sum % maxNumber == 0 && maxNumber == n) return n - 1; // this means values are identical
        graph = buildGraph(edges, n);
        this.nums = nums;
        for(int cut = maxNumber;cut > 0;cut--) {
            if(sum % cut == 0 && dfs(0, -1, sum / cut) == 0) return cut - 1; // cut is number of pieces, -1 to get number of edges
        } 
        return 0;
    }
    private int dfs(int current, int parent, int target) {
        int sum = nums[current];
        for(int next : graph[current]) {
            if(next == parent) continue;
            sum += dfs(next, current, target);
            if(sum > target) return sum; // early stop, beause this subtree cannot make it anyway
        }
        return sum == target ? 0 : sum;
    }
    private List<Integer>[] buildGraph(int[][] edges, int n) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<>();
        for(int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        return graph;
    }
}
