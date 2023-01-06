package sorting;

public class CountOfSmallerNumbersAfterSelf {
    /* 315
     * 思路是非常巧妙的利用merge sort的merge phase
     * 比如
     * left  mid       right
     * 1 1 3 5   2 4 6 7
     * 这个时候如果left在5 right在6 5 < 6我们可以确定6前面就是5后面小于5的数字
     * 而左边有多少小于5的 必然递归的更下层已经算过了
     * 那么我们就加入到count里面 剩下的细节就是count是base on原来数组的index的 所以需要一个特殊的[n][2]数组 记录值和index
     */
    private int[][] temp;
    private int[] count;
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        temp = new int[n][2];
        int[][] src = new int[n][2];
        count = new int[n];
        for(int i = 0;i < n;i++) {
            src[i][0] = i;
            src[i][1] = nums[i];
        }
        mergeSort(src, 0, n-1);
        return Arrays.stream(count).boxed().collect(Collectors.toList());
    }
    private void mergeSort(int[][] src, int left, int right) {
        if(left == right) return;
        int mid = left + (right - left) / 2;
        mergeSort(src, left, mid);
        mergeSort(src, mid + 1, right);
        merge(src, left, right, mid);
    }
    private void merge(int[][] src, int left, int right, int mid) {
        int l = left, r = mid + 1, index = left;
        while(l <= mid && r <= right) {
            if(src[l][1] <= src[r][1]) {
                temp[index][0] = src[l][0];
                temp[index][1] = src[l][1];
                count[src[l][0]] += r - mid - 1;
                l++;
            }else{
                temp[index][0] = src[r][0];
                temp[index][1] = src[r][1];
                r++;
            }
            index++;
        }
        while(l <= mid) {
            temp[index][0] = src[l][0];
            temp[index][1] = src[l][1];
            count[src[l][0]] += r - mid - 1;
            l++; 
            index++;
        }
        while(r <= right) {
            temp[index][0] = src[r][0];
            temp[index][1] = src[r][1];
            r++; 
            index++;
        }
        for(int i = left;i <= right;i++) {
            src[i][0] = temp[i][0];
            src[i][1] = temp[i][1];
        }
    }
}
