package trie;

public class MapSumPairs {
    /* 677
     * 调用的前缀树模版的KeysWithPrefix 实际上就是找到prefix的match node
     * 然后从该node开始dfs找到左右有val的node 那些都是满足prefix的string
     */
    class TrieNode {
        public TrieNode[] children;
        public int val;
        private boolean has;
        public TrieNode() {
            children = new TrieNode[26];
            val = 0;
        }
        public boolean containsKey(char c) {
            return children[c -'a'] != null;
        }
        public TrieNode get(char c) {
            return children[c -'a'];
        }
        public void put(char c, TrieNode node) {
            children[c -'a'] = node;
        }
        public void setHas() {
            has = true;
        }
        public boolean has() {
            return has;
        }
    }
    private TrieNode root;
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode node = root; // shallow copy
        for(int i = 0;i < key.length();i++) {
            char c = key.charAt(i);
            if(!node.containsKey(c)) {
                node.put(c, new TrieNode()); //在path上新建node
            }
            node = node.get(c);
        }
        node.setHas();
        node.val = val;
    }
    private int answer;
    public int sum(String prefix) {
        answer = 0;
        TrieNode x = getNode(root, prefix);
        traverse(x);
        return answer;
    }
    private void traverse(TrieNode root) {
        if(root == null) return;
        if(root.has()) answer += root.val;
        for(int i = 0;i < 26;i++) {
            traverse(root.children[i]);
        }
    }
    private TrieNode getNode(TrieNode root, String prefix) {
        TrieNode p = root;
        for(char c : prefix.toCharArray()) {
            if(p == null) return null;
            p = p.children[c-'a'];
        }
        return p; 
    }
}
