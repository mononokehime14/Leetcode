package oa;

public class shiyuZoom2022OA {
    public int solution1(int n) {
        int nCopy = n;
        int count = 0;
        while(nCopy != 0) {
            nCopy /= 10;
            count++;
        }
        boolean positive = true;
        if(count % 2 == 0) positive = false;
        int answer = 0;
        while(n != 0) {
            int digit = n % 10;
            n /= 10;
            answer += positive ? digit : -1 * digit;
            positive = !positive;
        }
        return answer;
    }
    public static int solution2(int[] arr1, int[] arr2) {
        int n = arr2.length, globlMin = Integer.MAX_VALUE;
        int left = 0;
        while(left < n) {
            int sum = 0;
            for(int i = 0;i < n;i++) {
                int index = (i + left) % n;
                //System.out.print(arr1[index]);
                sum += Math.abs(arr1[index] - arr2[i]);
            }
            // System.out.print("\n");
            globlMin = Math.min(globlMin, sum);
            left++;
        }
        return globlMin;
    }
}
