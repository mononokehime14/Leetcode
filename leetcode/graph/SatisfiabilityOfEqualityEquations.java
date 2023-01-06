package graph;

public class SatisfiabilityOfEqualityEquations {
    /* 精彩绝伦的并查集算法 Union Find 我的理解是这个算法在检查两个node是否相连的时候非常有效
     * 基本的思想就是现在有很多node 相连的node组成了一个子网 我如何检测一个node和一个node相连呢
     * 我们让子网里的node都保持一个parent 记录parent parent层层往上 每个子网有一个最终的root 
     * 然后检测相连的时候 寻找两个node的root 如果相同说明在同一个子网内 说明是相连的
     * union负责在构建网络的时候将两个node相连 方法是寻找两者的root 直接root相连
     * 这里有一个优化的问题 因为寻找root 也就是find方法是O（N）的
     * 原因在于我们的搜索成本是树的高度 但是树并不一定是平衡的 故此我们可以对每一个group 保持一个size union的时候把size小的union到size大的那里
     * 这样能大致将树高保持在logN 使得find方法的成本也是logN
     * 另一种优化方法就是路径压缩 也就是如果所有的node和root都是直接的上下级 扁平化 那么find就是O（1）
     * 这个方法简直就是经典而完美的网络思路
     * 方法就是在find的时候 如果发现node不是root 那么将其parent改成find(parent[x])
     * 也就是一直递归 直到拿到root 然后return root回来 这样整个子网直接拍扁
     * 如果进行路径压缩 则UF的所有操作都是O(1)的了
     */
    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for(String s: equations) { // 先建立==网络 不然如果一起loop 那么前面可能!=判断的时候还没连上后面连上了 这就漏了
            if(s.charAt(1) == '!') continue;
            uf.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
        }
        for(String s: equations) {
            if(s.charAt(1) == '=') continue;
            if(uf.connected(s.charAt(0) - 'a', s.charAt(3) - 'a')) return false;
        }
        return true;
    }
}
class UF {
    private int count;
    private int[] parent;
    public UF(int n){
        this.count = n;
        parent = new int[n];
        for(int i = 0;i < n;i++) {
            parent[i] = i;
        }
    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return;
        parent[rootX] = rootY;
        count--;
    }
    public boolean connected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        return rootX == rootY;
    }
    public int find(int x){
        if(parent[x] != x){ // not root
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public int count() {
        return count;
    }
}
