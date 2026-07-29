class Solution {
    private static final long LIMIT = 1_000_000L;

    public String smallestPalindrome(String s, int k) {
        // count the frequency of each letter
        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }
        
        // get the middle character
        String middle = "";

        // store frequency of left half
        int[] half = new int[26];

        // length of left half
        int len = 0;

        // build half frequency and find the middle character
        for(int i = 0; i < 26; i++){
            // odd frequency character becomes middle
            if(freq[i] % 2 == 1){
                middle = String.valueOf((char)(i + 'a'));
            }

            // only half characters are required
            half[i] = freq[i] / 2;
            len += half[i];
        }

        // count total distinct permutations possible
        long total = getWays(len, half);

        // if total permutations are less than k return ""
        if(total < k) return "";

        StringBuilder left = new StringBuilder();

        // build left half greedily
        while(len > 0){
            for(int c = 0; c < 26; c++){
                if(half[c] == 0){
                    continue;
                }
                // assume this character is chosen
                half[c]--;

                // count remaining permutations
                long ways = getWays(len - 1, half);

                if(ways >= k){
                    // k-th palindrome lies in this branch
                    left.append((char)(c + 'a'));
                    len--;
                    break;
                }
                else{
                    k -= ways;
                    half[c]++;
                }
            }
        }
        // construct final palindrome
        StringBuilder ans = new StringBuilder();
        ans.append(left);
        ans.append(middle);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long getWays(int total, int[] half){
        long res = 1;
        int remaining  = total;
        for(int i = 0; i < 26; i++){
            int cnt = half[i];
            if(cnt == 0) continue;
        
        res *= nCrLimited(remaining , cnt);
        if(res > LIMIT) return LIMIT;

        remaining -= cnt;
    }    
    return Math.min(res , LIMIT);
    }

    private long nCrLimited(int n, int r){
        if(r > n) return 0;

        r = Math.min(r , n - r);
        long ans = 1;

        for(int i = 1; i <= r; i++){
            ans = ans * (n - r + i)/ i;
            if(ans > LIMIT) return LIMIT;
        }
        return ans;
    }
}