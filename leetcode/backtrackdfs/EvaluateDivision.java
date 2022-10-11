package backtrackdfs;

public class EvaluateDivision {
    /*
     * 这道题目DFS的关键难点在于这是一个双向directed weighted graph
     * 因为双向 所以只要按照分子找分母就可以了 如果他们能够相连 则必然能够相除
     * 然后注意停止条件 找到答案的话 要提前判定 当前的node检查adjacent nodes是否match分母
     * 这是因为如果有当前node检查 由于我们的graph构建是map套map 如果它是最后一个node 我们无法知道权重了 只有上一个node可以知道
     * 如果mem里没有 说明寄了 可以return -1 如果几个adjacent node传回来都是-1 那么寄了 也return -1 这是两个停止条件
     * 然后我发现如果一开头visited加入当前node 那么就是等下做选择的时候检查visited 如果是前面检查visited node 那么就是做选择的时候加入visited
     * 这是因为这是DFS而非回溯 visited不用撤回
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Map<String, Double>> mem = new HashMap<>();
        for(int i = 0;i < values.length;i++) {
            List<String> temp = equations.get(i);
            mem.putIfAbsent(temp.get(0), new HashMap<>());
            mem.get(temp.get(0)).put(temp.get(1), values[i]);
            mem.putIfAbsent(temp.get(1), new HashMap<>());
            mem.get(temp.get(1)).put(temp.get(0), 1.0 / values[i]);
        }
        
        //DFS loop up
        double[] output = new double[queries.size()];
        for(int i = 0;i < queries.size();i++) {
            output[i] = traverse(mem, new HashSet<>(), queries.get(i).get(0), queries.get(i).get(1));
        }
        return output;
    }
    private double traverse(HashMap<String, Map<String, Double>> mem, Set<String> visited, String up, String down) {
        if(!mem.containsKey(up)) return -1.0;
        if(mem.get(up).containsKey(down)) return mem.get(up).get(down);
        visited.add(up);
        for(Map.Entry<String, Double> neighbour : mem.get(up).entrySet()) {
            if(!visited.contains(neighbour.getKey())) {
                double ans = traverse(mem, visited, neighbour.getKey(), down);
                if(ans != -1.0) return ans * neighbour.getValue();
            }
        }
        return -1.0;
    }
}
