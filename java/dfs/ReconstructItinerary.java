package dfs;

public class ReconstructItinerary {
    /* 332
     * 这道题非常绕 我离AC差了最后一步 停止条件不大对
     * 我的做法是回溯 track加入current 选择用掉current起点的机票 则计入visited 递归 最后再把current从track里拿出来
     * 停止条件设置是track size等于机票数量+1 但是这个停止条件应该错了 可能要扫一遍visited全部true
     * 停止时找到答案加入answer池子 这里使用PQ保持的
     * 但是这个答案是回溯级别的（阶乘？）时间复杂度 因为这个选择和下个选择可能visit同一个点 因为我们有撤回的操作
     * 正确的答案首先是sort了graph 使得每次我们都选择最小的to 然后是用了linkedlist remove first
     * 这样替代掉了visited 我之前是要visited 并且graph不记录to的node 而是记录ticket在tickets中的index
     * 然后就是最重要的 后序遍历 现在我们不是尝试所有的顺序组合 由于每次删掉node就不撤回 每个机票只会用一次
     * 如果在for loop前面加入track 如果这条路径是不对的 后面就要配合撤回
     * 后序遍历之后 即便当前用这张机票不是正确的路径 我们也能过确定 这张机票就用在别的正确路径的后面 比如a -> b, a -> c
     * a -> b不是正确的路径 a就是先去c 最后再绕去b 这个思路正确的前提就是每张机票必然要被用到 而且必然有一个valid的解
     */
    private LinkedList<String> track;
    private HashMap<String, LinkedList<String>> graph;
    public List<String> findItinerary(List<List<String>> tickets) {
        track = new LinkedList<>();
        graph = buildGraph(tickets);
        traverse("JFK");
        return track;
    }
    private void traverse(String current) {
        if(graph.containsKey(current) && graph.get(current).size() != 0){
            LinkedList<String> tlist = graph.get(current);
            while(!tlist.isEmpty()) {
                String to = tlist.removeFirst();
                traverse(to);
            }
        }
        track.addFirst(current); // 后序遍历
    }
    private String formString(List<String> a) {
        StringBuilder sb = new StringBuilder();
        for(String s : a) {
            sb.append(s);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
        return sb.toString();
    }
    private HashMap<String, LinkedList<String>> buildGraph(List<List<String>> tickets) {
        HashMap<String, LinkedList<String>> graph = new HashMap<>();
        for(int i = 0;i < tickets.size();i++) {
            String from = tickets.get(i).get(0);
            String to = tickets.get(i).get(1);
            graph.putIfAbsent(from, new LinkedList<>());
            graph.get(from).add(to);
        }
        graph.forEach((key, value) -> Collections.sort(value));
        return graph;
    }
}
