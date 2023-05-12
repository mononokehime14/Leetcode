package array;

public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        /* Easy的 没有什么好多说的 */
        if(ransomNote.length() == 0) return true;
        if(magazine.length() == 0) return false;
        int[] counting = new int[26];
        for(char c: magazine.toCharArray()) {
            counting[c- 'a']++;
        }
        for(char c: ransomNote.toCharArray()) {
            counting[c- 'a']--;
            if(counting[c- 'a'] < 0) return false;
        }
        return true;
    }
}
