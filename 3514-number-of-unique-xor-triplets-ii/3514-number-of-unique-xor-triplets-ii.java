class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Maximum possible XOR values for numbers upto 1500 is 2048
        final int MAX_VAL =2048;

        boolean[] s1 = new boolean[MAX_VAL];
        boolean[] s2 = new boolean[MAX_VAL];
        boolean[] s3 = new boolean[MAX_VAL];

        // Populating s1 with elements in nums
        for(int num : nums){
            s1[num] = true;
        }

        // Populating s2 with pair combinations
        for(int i= 0; i < MAX_VAL; i++){
            if(s1[i]){
                for(int num : nums){
                    s2[i ^ num] = true;
                }
            }
        }

        // Populating s3 
        int uniqueTripletsCount = 0;
        for(int i = 0; i < MAX_VAL; i++){
            if(s2[i]){
                for(int num : nums){
                    if(!s3[i ^ num]){
                        s3[i ^ num] = true;
                        uniqueTripletsCount++;
                    }
                }
            }
        }
        return uniqueTripletsCount;
    }
}