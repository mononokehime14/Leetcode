class SortColors {
    public void sortColors(int[] nums) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == 0){
                count1++;
            }else if(nums[i] == 1){
                count2++;
            }else{
                count3++;
            }
        }
        for(int i = 0;i < nums.length;i++){
            if(count1 > 0){
                nums[i] = 0;
                count1--;
            }else if(count2 > 0){
                nums[i] = 1;
                count2--;
            }else{
                nums[i] = 2;
            }
        }
        //FK merge sort, FK ALL
        //mergeSort(0, mem.length-1); 
    }
    // private void mergeSort(int low, int high){
    //     if(low < high){
    //         int mid = (int)(low + high) / 2; //explicit cast to int should round down
    //         mergeSort(low, mid);
    //         mergeSort(mid+1, high);
    //         merge(low, mid, high);
    //     }
    // }
    // private void merge(int low, int mid, int high){
    //     int[] temp = new int[high - low + 1];
    //     int pos1 = low;
    //     int pos2 = mid + 1;
    //     int pos3 = 0;
    //     while(pos1 <= mid && pos2 <= high){
    //         if(mem[pos1] <= mem[pos2]){
    //             temp[pos3++] = mem[pos1++];
    //         }else{
    //             temp[pos3++] = mem[pos2++];
    //         }
    //     }
    //     while(pos1 <= mid){
    //         temp[pos3++] = mem[pos1++];
    //     }
    //     while(pos2 <= high){
    //         temp[pos3++] = mem[pos2++];
    //     }
    //     for(int i = 0;i < high - low + 1;i++){
    //         mem[low + i] = temp[i];
    //     }
    // }  
}