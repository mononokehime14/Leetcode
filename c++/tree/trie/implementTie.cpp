/**
 * 208. Implement Trie (Prefix Tree)
 * 实现字典树 线段树和二叉搜索树都是logN的层高 但是字典树却不是
 * 只取决于最长的词 因为每一层存了26字母 搜索的成本也是O(N)
 * 注意node有这个children pointer意味着存在着一条路径 也就是前缀
 * 只有node isVal证明这一条路径到这个node有一个词存在 也就是说 此时不存在路径上的
 * 只存在最后一个字母的node上
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