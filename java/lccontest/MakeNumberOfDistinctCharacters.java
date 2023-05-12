package lccontest;

public class MakeNumberOfDistinctCharacters {
    /* 2531
     * 诡异 我感觉不考察任何的算法技巧 太白痴了
     */
    public boolean isItPossible(String word1, String word2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for(char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for(char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }
        for(int i = 0;i < 26;i++) {
            if(count1[i] == 0) continue;
            
            for(int j = 0;j < 26;j++) {
                if(count2[j] == 0) continue;
                count1[i]--;
                count2[i]++;
                count1[j]++;
                count2[j]--;
                if(isValid(count1, count2)) return true;
                count1[j]--;
                count2[j]++;
                count1[i]++;
                count2[i]--;
            }
        }
        return false;
    }
    private boolean isValid(int[] count1, int[] count2) {
        int c = 0;
        for(int i : count1) {
            if(i > 0) c++;
        }
        for(int i : count2) {
            if(i > 0) c--;
        }
        return c == 0;
    }
}
