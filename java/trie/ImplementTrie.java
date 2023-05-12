package trie;

public class ImplementTrie {
    /* 208
     * 参照TrieMap的实现 insert就是put serach就是寻找是否有word 而startswith只需要找到一条通的路径就可以了
     * 但是这个实现方法非常慢 10+% 于是去看了official solution 换了以后还是只有26.20% 可能是一些奇技淫巧？
     */
    class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode node = root; // backup
            for(int i = 0;i < word.length();i++) {
                char c = word.charAt(i);
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode()); //在path上新建node
                }
                node = node.get(c);
            }
            node.setHas();
        }

        private TrieNode getNode(TrieNode node, String key) {
            TrieNode p = node;
            for(char c : key.toCharArray()) {
                if(p == null) return null;
                p = p.children[c];
            }
            return p;
        }
        public boolean search(String word) {
            TrieNode x = getNode(root, word);
            return x != null && x.has();
        }
        
        public boolean startsWith(String prefix) {
            TrieNode x = getNode(root, prefix);
            return x != null;
        }
    }
    
    class TrieNode {
        public int val = 0;
        public TrieNode[] children;

        private boolean has;

        public TrieNode() {
            children = new TrieNode[26];
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
}
