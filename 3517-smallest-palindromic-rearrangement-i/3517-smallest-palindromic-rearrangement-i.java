class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }

        StringBuilder leftHalf = new StringBuilder();
        String middle ="";

        for(int i = 0; i < 26; i++){
            if(freq[i] % 2 == 1){
                middle = String.valueOf((char)(i + 'a'));
            }

            int halfCount = freq[i] / 2;
            for(int j = 0; j < halfCount; j++){
                leftHalf.append((char)(i + 'a'));
            }
        }
        String left = leftHalf.toString();
        String right = leftHalf.reverse().toString();
        
        return left + middle + right;
    }
}