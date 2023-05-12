package design;

public class DesignTwitter {
    /* 355
     * 这道题的核心在于getNewsFeed 由于follows动态变化 肯定是每个user要存自己的posts 因为要按照时间排序则要存成有序数组
     * 按照插入时间排序就可以了 因为我们可以用count代替timestamp 所以linkedlist就ok
     * 那么getNewsFeed就是要从follow的user的多个post list中 找到最recent的10个
     */
    class User {
        public int userId;
        public HashSet<Integer> follows;
        public LinkedList<int[]> posts;
        public User(int userId) {
            this.userId = userId;
            follows = new HashSet<>();
            follows.add(userId);
            posts = new LinkedList<>();
        }
        public void post(int tweetid, int count) {
            posts.add(new int[]{tweetid, count});
        }
        public void follow(int id) {
            this.follows.add(id);
        }
        public void unfollow(int id) {
            this.follows.remove(id);
        }
    }
    private int count;
    private HashMap<Integer, User> users;
    public Twitter() {
        users = new HashMap<>();
        count = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        users.putIfAbsent(userId, new User(userId));
        users.get(userId).post(tweetId, count++);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> answer = new ArrayList<>();
        if(!users.containsKey(userId)) return answer;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for(int followeeId : users.get(userId).follows) {
            LinkedList<int[]> temp = users.get(followeeId).posts;
            if(temp.size() == 0) continue;
            pq.offer(new int[]{temp.getLast()[0], temp.getLast()[1], followeeId, temp.size() - 1});
        }
        int count = 0;
        while(count < 10 && !pq.isEmpty()) {
            int[] current = pq.poll();
            if(current[3] > 0) {
                int[] temp = users.get(current[2]).posts.get(current[3] - 1);
                pq.offer(new int[]{temp[0], temp[1], current[2], current[3] - 1});
            }
            answer.add(current[0]);
            count++;
        }
        return answer;
    }
    
    public void follow(int followerId, int followeeId) {
        users.putIfAbsent(followerId, new User(followerId));
        users.putIfAbsent(followeeId, new User(followeeId));
        users.get(followerId).follow(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        users.get(followerId).unfollow(followeeId);
    }
}
