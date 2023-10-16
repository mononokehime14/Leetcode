/**
 * 208. Implement Trie (Prefix Tree)
 * 实现字典树 线段树和二叉搜索树都是logN的层高 但是字典树却不是
 * 只取决于最长的词 因为每一层存了26字母 搜索的成本也是O(n), n = string length
 * 注意node有这个children pointer意味着存在着一条路径 也就是前缀
 * 只有node isVal证明这一条路径到这个node有一个词存在 也就是说 此时不存在路径上的
 * 只存在最后一个字母的node上
 * 
 * 字典树和哈希表相比, 字典树是大数据的更有选择. 首先是搜索成本O(n) 这里的N是target string的长度
 * 普遍理解上哈希表O(1), 但是哈希表是存在hash collision的, 最差情况是O(N)的, 
 * 这里的N甚至是hash表中存的数据的数量, 而不是取决于input string length, 故此优势就在这里了
 * 
 * 另外, 在数据量十分大的时候, 不会显著增加空间需求, 毕竟26字母index放在那里了.
*/
class TrieNode {
public:
    TrieNode* children[26];
    bool isVal;
    TrieNode(): isVal(false) {
        for(int i = 0;i < 26;++i) children[i] = nullptr;
    }
};

class Trie {
    TrieNode* root;
public:
    Trie(): root(new TrieNode()){}
    
    void insert(string word) {
        TrieNode* current = root;
        for(const char c : word) {
            if(!current->children[c-'a']) current->children[c-'a'] = new TrieNode();
            current = current->children[c-'a'];
        }
        current->isVal = true;
    }
    
    bool search(string word) {
        TrieNode* current = root;
        for(const char c : word) {
            if(!current) return false;
            current = current->children[c-'a'];
        }
        return current ? current->isVal : false;
    }
    
    bool startsWith(string prefix) {
        TrieNode* current = root;
        for(const char c : prefix) {
            if(!current) return false;
            current = current->children[c-'a'];
        }
        return current;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */