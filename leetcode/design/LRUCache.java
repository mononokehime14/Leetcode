public class LRUCache {
    /* 重点在于hashmap和list配合 list存node 按照顺序 hashmap则存key对应的node
     * 这里为什么不存key对应的index呢 因为这样remove就不是O1了 必须要传node 这样直接操作prev next
     * 而且必定要用doubly linked list 以保证remove O1
     */
    private int capacity;
    private DoubleList cache;
    private HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new DoubleList();
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        makeRecently(key);
        return map.get(key).value;
    }
    
    public void put(int key, int value) {
        //delete original
        if(map.containsKey(key)) {
            deleteKey(key);
            addRecently(key, value);
            return;
        }
        if(this.capacity == cache.getSize()){
            deleteFirst();
        }
        addRecently(key, value);
    }
    private void makeRecently(int key) {
        Node n = map.get(key);
        cache.remove(n);
        cache.addLast(n);
    }
    private void addRecently(int key, int value) {
        Node n = new Node(key, value);
        cache.addLast(n);
        map.put(key, n);
    }
    private void deleteKey(int key) {
        Node n = map.get(key);
        cache.remove(n);
        map.remove(key);
    }
    private void deleteFirst() {
        Node n = cache.removeFirst();
        map.remove(n.key);
    }
}
class Node{
    public int key, value;
    public Node prev, next;
    public Node(int k, int v) {
        this.key = k;
        this.value = v;
    }
}

class DoubleList{
    public Node head, tail;
    public int size;
    public DoubleList() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    
    public void addLast(Node n) {
        n.prev = tail.prev;
        n.next = tail;
        tail.prev.next = n;
        tail.prev = n;
        size++;
    }
    
    public void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        size--;
    }
    // remove first and return
    public Node removeFirst() {
        Node n = head.next;
        if(n == null) return n;
        remove(n);
        return n;
    }
    public int getSize() {
        return this.size;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
