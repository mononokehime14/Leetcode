package trie;

public class DesignAddAndSearchWordsDataStructure {
    /* 211
     * 套用的前缀树模版里的hasKeyWithPattern 是用了dfs 如果路径不同自然不行
     * 另一个停止条件是路径通 但是还是要node val不为空才能证明有这个string
     * 加入了通配符后 可以尝试所有的char
     */
    class TrieNode {
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
    private TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root; // shallow copy
        for(int i = 0;i < word.length();i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c)) {
                node.put(c, new TrieNode()); //在path上新建node
            }
            node = node.get(c);
        }
        node.setHas();
    }
    
    public boolean search(String word) {
        TrieNode p = root;
        return hasKeyWithPattern(p, word, 0);
    }

    private boolean hasKeyWithPattern(TrieNode node, String pattern, int i) {
        if (node == null) return false;
        if (i == pattern.length()) return node.has();
        char c = pattern.charAt(i);
        if (c != '.') {
            return hasKeyWithPattern(node.children[c - 'a'], pattern, i + 1);
        }
        for (int j = 0; j < 26; j++) {
            if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                return true;
            }
        }
        return false;
    }
}
