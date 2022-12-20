package graph;

public class FriendCircles {
    /*
     * 并查集
     */
    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        UF unionFind = new UF(m);
        for(int i = 0;i < m;i++) {
            for(int j = i + 1;j < m;j++) {
                if(isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getCount();
    } 
}
class UF {
    private int count;
    private int[] parent;

    public UF(int size) {
        parent = new int[size];
        count = size;
        for(int i = 0;i < size;i++) parent[i] = i;
    }
    
    private int find(int current) {
        if(parent[current] != current) { // not root
            parent[current] = find(parent[current]);
        }
        return parent[current];
    }
    
    public void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return;
        parent[pb] = pa;
        count--;
    }
    
    public boolean connected(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        return pa == pb;
    }
    
    public int getCount() { return this.count; }
}
