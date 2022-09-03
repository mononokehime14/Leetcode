public class ExcelSheetColumnNumber {
    public int titleToNumber(String columnTitle) {
        /* initial: pow, 但是发现可以累积 */
        int sum = 0;
        for(int i = 0;i < columnTitle.length();i++){
            sum *= 26; //第一个数字因此到后面会乘到想要的26的倍数
            sum += (columnTitle.charAt(i) - 'A' + 1);
        }
        return sum;
    }
}
