import java.util.HashMap;
public class TopKFrequentValue {
    int[] keys;
    int[] values;
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            Integer count = hmap.get(nums[i]);
            if(count == null){
                hmap.put(nums[i], 1);
            }else{
                hmap.put(nums[i], 1 + count);
            }
        }
        keys = new int[hmap.size()];
        values = new int[hmap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> mapEntry : hmap.entrySet()) {
            keys[index] = mapEntry.getKey();
            values[index] = mapEntry.getValue();
            index++;
        }
        mergeSort(0, keys.length - 1);
        return Arrays.copyOfRange(keys, 0, k);
    }
    private void mergeSort(int low, int high){
        if(low < high){
            int mid = (int)(low + high) / 2; //explicit cast to int should round down
            mergeSort(low, mid);
            mergeSort(mid+1, high);
            merge(low, mid, high);
        }
    }
    private void merge(int low, int mid, int high){
        int[] temp1 = new int[high - low + 1];
        int[] temp2 = new int[high - low + 1];
        int pos1 = low;
        int pos2 = mid + 1;
        int pos3 = 0;
        while(pos1 <= mid && pos2 <= high){
            if(values[pos1] >= values[pos2]){
                temp1[pos3] = values[pos1];
                temp2[pos3] = keys[pos1];
                pos3++;
                pos1++;
            }else{
                temp1[pos3] = values[pos2];
                temp2[pos3] = keys[pos2];
                pos3++;
                pos2++;
            }
        }
        while(pos1 <= mid){
            temp1[pos3] = values[pos1];
            temp2[pos3] = keys[pos1];
            pos3++;
            pos1++;
        }
        while(pos2 <= high){
            temp1[pos3] = values[pos2];
            temp2[pos3] = keys[pos2];
            pos3++;
            pos2++;
        }
        for(int i = 0;i < high - low + 1;i++){
            keys[low + i] = temp2[i];
            values[low + i] = temp1[i];
        }
    } 
}
