package array;

public class ReverseWordsInString {
    /* 这道题和rotate array其实是差不多的思路 直接reverse 全部 然后把单词再reverse回来
     * 但是其实可以用一种一遍遍历就足够的方法 从后往前 查找单词 加入builder
     */
    // public String reverseWords(String s) {
    //     s = s.trim();
    //     StringBuilder sb = new StringBuilder();
    //     sb.append(s);
    //     sb.reverse();
    //     String rs = sb.toString();
    //     StringBuilder sb2 = new StringBuilder();
    //     for(String word: rs.split(" ")) {
    //         if(word.length() == 0) continue;
    //         StringBuilder temp = new StringBuilder(word);
    //         sb2.append(temp.reverse().toString());
    //         sb2.append(" ");
    //     }
    //     return sb2.toString().stripTrailing();
    // }
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') { //跳过“ ”
                i--;
            }
            int end = i;
            while (i >= 0 &&s.charAt(i) != ' ') { //查找字母
                i--;
            }
            int start = i;
            if (end >= 0) {
                sb.append(s.substring(start + 1, end + 1));
                sb.append(' ');
            }
        }
        return sb.substring(0, sb.length() - 1).toString();
        
    }
}
