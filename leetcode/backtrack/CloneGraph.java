package backtrack;

public class CloneGraph {
    /**
     * 这道题是DFS遍历 我想到了这个思路 缺乏了一点细节 不知道怎样记录visited
     * 事实上 这必然是一个DFS 因为BFS遍历的话 加入node的时候 还没有完全搞定那个node的neighbors
     * 确定了DFS的遍历思路 就要再确定细节 如何避免重复建立node呢 毕竟这个不是树而是图
     * 我们因此需要一个hashmap 记录原来的node和clone的node 这样如果有visited过 说明我们有搞定过这个node 直接拿过来用就可以了
     * 要注意 加入visited要在for loop的回溯/DFS/递归前 不然是没有效果的
     */
    HashMap<Node, Node> visited;
    public Node cloneGraph(Node node) {
        visited = new HashMap<>();
        return traverse(node);
    }
    private Node traverse(Node root) {
        if(root == null) return null;
        if(visited.containsKey(root)) return visited.get(root);
        Node newRoot = new Node(root.val);
        visited.put(root, newRoot);
        for(Node cur: root.neighbors) {
            Node next = traverse(cur);
            if(next != null) newRoot.neighbors.add(next);
        }
        return newRoot;
    }
}
