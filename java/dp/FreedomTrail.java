public class FreedomTrail {
    /* 514
     * 直接做出来了hard 我就是用了回溯 因为画出树了之后发现转轮的index + key的index存在重叠子问题 可以用备忘录
     * 层高key的长度 每一次选择该char在ring中的不同位置 时间复杂度由于有备忘录的原因应该是m * n m为ring长度n为key长度
     */
    private HashMap<String, Integer> memo;
    private List[] positions;
    public int findRotateSteps(String ring, String key) {
        memo = new HashMap<>();
        int n = key.length();
        positions = new ArrayList[26];
        for(int i = 0;i < 26;i++) positions[i] = new ArrayList<Integer>();
        for(int i = 0;i < ring.length();i++) {
            positions[ring.charAt(i) - 'a'].add(i);
        }
        return backtrack(key, 0, ring.length(), n, 0) + n;
    }
    private int backtrack(String key, int index, int m, int n, int pos) {
        if(index == n) return 0;
        String state = index + "," + pos;
        if(memo.containsKey(state)) return memo.get(state);
        List<Integer> poses = positions[key.charAt(index) - 'a'];
        int newStep = Integer.MAX_VALUE;
        for(int next : poses) { // 该char在转轮中的所有位置
            int cost = Math.abs(pos - next);
            cost = Math.min(cost, m - cost); // 顺时针还是逆时针转动
            cost += backtrack(key, index + 1, m, n, next);
            newStep = Math.min(cost, newStep);
        }
        memo.put(state, newStep);
        return newStep;
    }
}
