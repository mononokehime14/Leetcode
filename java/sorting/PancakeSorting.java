package sorting;

public class PancakeSorting {
    /* 969
     * 这道题的思路是非常巧妙的 需要用到的是递归 我们可以把这个问题变成一个子问题 如果我们能把最大的换到最后面 那么只需要
     * 再sort前面的就行了 在这个思路下 怎么把最大的翻上去呢 首先把包括最大的index的前面全部reverse 这样最大的在最前面
     * 然后在reverse全部 这样最大的就到了最后面 如此递归下去
     * 在实现上 我一开始想用一个sort好的新arr 每个element记录值和原先的index 这样只要从后往前就行了
     * 但是这里index会随着翻转更新 不能用一开始的index 所以只好每次用最朴素的迭代找max index 配上最朴素的reverse
     */
    private List<Integer> answer;
    public List<Integer> pancakeSort(int[] arr) {
        answer = new ArrayList<>();
        sort(arr, arr.length);
        return answer;
    }
    private void sort(int[] arr, int n) {
        if(n == 1) return;
        int max = 0, maxIndex = 0;
        for(int i = 0;i < n;i++) {
            if(arr[i] > max) {
                maxIndex = i;
                max = arr[i];
            }
        }
        answer.add(maxIndex + 1);
        reverse(arr, 0, maxIndex);
        answer.add(n);
        reverse(arr, 0, n - 1);
        sort(arr, n - 1);
    }
    private void reverse(int[] arr, int i, int j) {
        while(i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
