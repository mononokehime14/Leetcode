package interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TikTokVO2022 {
    public static LinkedList<int[]> findPath(int[][] board, int[] start, int[] end) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        LinkedList<int[]> track = new LinkedList<>();
        HashMap<int[], int[]> parents = new HashMap<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0;i < size;i++) {
                int[] current = queue.poll();
                boolean isWall = board[current[0]][current[1]] == 0;
                if(current[0] < 0 || current[0] >= m || current[1] < 0 || current[1] >= n || isWall) continue;
                //if(parents.containsKey(current)) continue;
                // System.out.println(current[0] + " " + current[1]);
                if(current[0] == end[0] && current[1] == end[1]) {
                    System.out.println("Reach dest");
                    int[] cur = current;
                    track.addFirst(cur);
                    while(parents.containsKey(cur)) {
                        cur = parents.get(cur); 
                        track.addFirst(cur);
                        if(cur == start) break;
                    }
                    return track;
                }
                int[] left = new int[]{current[0], current[1] - 1};
                queue.add(left);
                parents.put(left, current);
                int[] right = new int[]{current[0], current[1] + 1};
                queue.add(right);
                parents.put(right, current);
                int[] up = new int[]{current[0] + 1, current[1]};
                queue.add(up);
                parents.put(up, current);
                int[] down = new int[]{current[0] - 1, current[1]};
                queue.add(down);
                parents.put(down, current);
            }
        }
        return track;// empty
    }
}
