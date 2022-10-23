package array;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        /* 就是数一遍 然后检查match */
        if(s.length() != t.length()) return false;
        int[] counting = new int[26];
        for(char c: s.toCharArray()) {
            counting[c - 'a']++;
        }
        for(char c: t.toCharArray()) {
            counting[c - 'a']--;
            if(counting[c - 'a'] < 0) return false;
        }
        // return Arrays.stream(counting).max().getAsInt() == 0;
        return true;
    }
}
