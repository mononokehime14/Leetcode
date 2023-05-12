package graph;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        /*
         * 并查集算法 原先的模版感觉不是特别的好套 但是实际上原先的模版会更加清晰一些
         * 不用原来的结构的话 我们就需要使用一个parents hashmap了
         * 仍然在find里做路径压缩 我们把每一个email的parent设置为自己
         * 然后把每一个account email的parent改到第一个email的parent 这样完成了合并 后面的会囊括前面的
         * 最后我们把每一个parent下面的emial加入到TreeSet当中 这会保持它的排序
         * 最后最后我们output到list中
         */
        HashMap<String, String> owners = new HashMap<>();
        HashMap<String, String> parents = new HashMap<>();
        HashMap<String, TreeSet<String>> union = new HashMap<>();
        for(List<String> account: accounts) {
            for(int i = 1;i < account.size();i++) {
                parents.put(account.get(i), account.get(i));
                owners.put(account.get(i), account.get(0));
            }
        }
        for(List<String> account: accounts) {
            String p = find(account.get(1), parents);
            for(int i = 2;i < account.size();i++) {
                parents.put(find(account.get(i), parents), p);
            }
        }
        for(List<String> account: accounts) {
            String p = find(account.get(1), parents);
            if(!union.containsKey(p)) union.put(p, new TreeSet<>());
            for (int i = 1; i < account.size(); i++) {
                union.get(p).add(account.get(i));
            }
        }
        List<List<String>> res = new LinkedList<>();
        for (String p : union.keySet()) {
            List<String> emails = new LinkedList(union.get(p));
            emails.add(0, owners.get(p));
            res.add(emails);
        }
        return res;
    }
    private String find(String s, Map<String, String> p) {
        if(p.get(s) != s) {
            p.put(s, find(p.get(s), p));
        }
        return p.get(s);
    }
}
