package oa;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ccEbay2022OA {
    // public static void main(String[] args) {
    //     // int row = 3, col = 3;
    //     // int[][] black = new int[][]{{0,0},{0,1},{1,0}};
    //     // int[] result = solution1(black, row, col);
    //     // System.out.println(result[0] + " " + result[1] + " " + result[2]+ " "+ result[3]+ " "+ result[4]);
    //     int[] elements = new int[]{1,4,2,3};
    //     System.out.println(solution3(elements));
    // }
    public static int[] solution1(int[][] black, int row, int col) {
        int[][] map = new int[row][col];
        int[] output = new int[5];
        for(int[] cur: black) {
            map[cur[0]][cur[1]] = 1;
        }
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }
        for(int i = 0;i < row - 1;i++) {
            for(int j = 0;j < col - 1;j++) {
                int sum = map[i][j] + map[i+1][j] + map[i][j+1] + map[i+1][j+1];
                output[sum]++;
            }
        }
        return output;
    }

    // public static String[] solution2(String[] members, String messages) {
    //     HashMap<String, Integer> mem = new HashMap<>();
    //     for(String member: members) mem.put(member, 1);
    //     for(String message: messages) checkMessage(message, mem);
    //     List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer>>(mem.entrySet());
        // Collections.sort(list, (a, b) -> a.getValue() >);
    // }
    private void checkMessage(String message, HashMap<String, Integer> mem) {
        Set<String> tempMem = new HashSet<>();
        for(String s: message.split(" ")) {
            if(s.charAt(0) != '@') continue;
            s = s.substring(1, s.length());
            for(String id: s.split(",")) {
                if(!tempMem.contains(id)) {
                    tempMem.add(id);
                    mem.put(id, mem.get(id) + 1);
                }
            }
        }
    }
    public static int solution3(int[] elements) {
        if(elements.length == 0) return -1;
        int turnpoint = 0, frontMin = elements[0];
        boolean turnFlag = false;
        for(int i = 1;i < elements.length;i++) {
            if(elements[i] < elements[i-1]){
                if(turnFlag) return -1;
                turnFlag = true;
                turnpoint = i - 1;
            }else{
                if(turnFlag && elements[i] > frontMin) return -1;
            }
        }
        return turnpoint;
    }
}
