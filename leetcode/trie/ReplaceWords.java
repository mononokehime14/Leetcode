package trie;

public class ReplaceWords {
    /* 648
     * 套用的前缀树模版 使用的是shortestPrefixOf
     * 其实挺简单的 就是早停 如果路径上的node有val 就直接return 细节就是这里找不到是return key的
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
    class TrieMap {
        private TrieNode root;
        
        public TrieMap() {
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

        public String shortestPrefixOf(String key) {
            TrieNode p = root;
            for(int i = 0;i < key.length();i++) {
                if(p == null) return key;
                if(p.has()) return key.substring(0, i); 
                p = p.children[key.charAt(i) - 'a'];
            }
            return key;
        }
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieMap trieMap = new TrieMap();
        for(String s : dictionary) trieMap.insert(s);
        StringBuilder sb = new StringBuilder();
        for(String key : sentence.split(" ")) {
            sb.append(trieMap.shortestPrefixOf(key));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);// remove last " "
        return sb.toString();
    }
}
