public class FlattenNestedListIterator {
    /**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    /* 341
     * 很有意思的一道题 是Composite Pattern
     * 一开始还没搞懂题目想要hard code遍历一边 但是发现nestedinteger是可以继续嵌套的 像是一个多叉树一样 这样的话
     * 我就想直接遍历一遍多叉树就好了 没看答案就自己做出来了
     */
    private List<Integer> flattenList;
    private int size;
    private int index;
    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList = new ArrayList<Integer>();
        for(NestedInteger i : nestedList) {
            flattenList.addAll(traverse(i));
        }
        this.index = 0;
        this.size = this.flattenList.size();
    }
    private List<Integer> traverse(NestedInteger root) {
        if(root.isInteger()) return Collections.singletonList(root.getInteger());
        List<Integer> answer = new ArrayList<Integer>();
        for(NestedInteger i : root.getList()) {
            answer.addAll(traverse(i));
        }
        return answer;
    }

    @Override
    public Integer next() {
        return this.flattenList.get(index++);
    }

    @Override
    public boolean hasNext() {
        return this.index < this.size;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
