class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length];

        for(int i = 0; i < nums1.length; i++){
            int currentNum = nums1[i];

            int startIndex = -1;
            for(int j=0; j < nums2.length; j++){
                if(nums2[j] == currentNum){
                    startIndex = j;
                    break;
                }
            }
            int foundGreater = -1;
            for(int k = startIndex + 1; k < nums2.length; k++){
                if(nums2[k] > currentNum){
                    foundGreater = nums2[k];
                    break;
                }
            }
            arr[i] = foundGreater;
        }
        return arr;
    }
}