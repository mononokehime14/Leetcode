/**
 * 684. Redundant Connection
 * redundantConnection
 * C++并查集template, 注意find里的路径压缩(以一次N的代价展平, 方便后续这条路径上所有值的查询, 平摊成本之后平均操作是O(1)的)
 * 还有connect merge的时候, 小的放到大的下面, 保证树的大致平衡, 可降低find一次成本为OlogN, 称为启发式合并
 * 并查集的最终实际复杂度比较难搞懂, 有的说是阿克曼函数, 在开启路径压缩和启发式合并的情况下, 可以压缩到一个极小的类似常数的值
 * 注意并查集无法比较快的delete connection, 使用场景还是构建connection和检测
*/
class UF {
    vector<int> parents, size;
    public: 
        UF(int n) : parents(n), size(n, 1) {
            iota(parents.begin(), parents.end(), 0);
        }

        int find(int x) {
            if(parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        void connect(int a, int b) {
            int p_a = find(a);
            int p_b = find(b);
            if(p_a == p_b) return;
            if(size[p_a] > size[p_b]) {
                size[p_a] += size[p_b];
                parents[p_b] = p_a;
            }else{
                size[p_b] += size[p_a];
                parents[p_a] = p_b;
            }
        }

        bool is_connected(int a, int b) {
            return find(a) == find(b);
        }
};

class Solution {
public:
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        int n = edges.size();
        UF uf(n+1); // +1这样数字直接对应
        for(const vector<int>& edge : edges) {
            int u = edge[0], v = edge[1];
            if(uf.is_connected(u, v)) return edge;
            uf.connect(u, v);
        }
        return vector<int>{-1, -1}; // not possible
    }
};