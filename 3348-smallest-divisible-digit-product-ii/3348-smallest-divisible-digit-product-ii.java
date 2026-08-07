class Solution {
    public String smallestNumber(String num, long t) {
        // Store the original value of t
        // we  will divide it repestedly to check if it only contains prime factors 
        // that can be formed using digits (2,3,5,7)
        long remainingFactor = t;

        // Remove every possible factor from 2 to 9
        for(int factor = 2; factor <= 9; factor++){
            while(remainingFactor % factor == 0){
                remainingFactor /= factor;
            }
        }

        // if something is still left
        // it means t contains a prime factor like 11,13..
        // Such factors can never be produced using decimal digits
        if(remainingFactor > 1){
            return "-1";
        }
        int len = num.length();
    
        // requiredFactor[i]
        // stores how much factor is still needed
        // after processing first i digits
        long[] requiredFactor = new long[len + 1];
        requiredFactor[0] = t;

        // Assume initially we will try changing the last digit
        int firstZeroIndex = len - 1;

        // Convert string into character array
        // because character arrays are easy to modify
        char[] digits = num.toCharArray();

        // process every digit from left to right
        for(int i = 0; i < len; i++){
            // Zero is not allowed in the final answer
            // once we see a zero
            // we will start modifying from this position
            if(digits[i]=='0'){
                firstZeroIndex = i;
                break;
            }
            // remove the common factors contributed
            // by the current digit
            requiredFactor[i + 1] = requiredFactor[i] / gcd(requiredFactor[i], digits[i] - '0');
        }
        // if all required factors are already satisfied, then the given number itself is our answer
        if(requiredFactor[len] == 1){
            return num;
        }

        // try modifying digits from right to left
        // this helps us obtain the smallest possible answer
        for(int i = firstZeroIndex; i >= 0; i--){
            // try bigger digit at current position
            while(++digits[i] <= '9'){
                long currentNeed = requiredFactor[i] / gcd(requiredFactor[i], digits[i] - '0');
                int candidateDigit = 9;
                for(int j = len-1 ; j > i; j--){
                    while(currentNeed % candidateDigit != 0){
                        candidateDigit--;
                    }
                    currentNeed /= candidateDigit;
                    digits[j] = (char)('0' + candidateDigit);
                }

                if(currentNeed == 1){
                    return new String(digits);
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        long remaining = t;

        for(int digit = 9; digit >= 2; digit--){
            while(remaining % digit == 0){
                answer.append((char)('0' + digit));
                remaining /= digit;
            }
        }

        int extraOnes = Math.max(len + 1 - answer.length(), 0);

        while(extraOnes-- > 0){
            answer.append('1');
        }
        return answer.reverse().toString();
    }
    private long gcd(long first, long second){
        while(second !=0){
            long temp =  second;
            second = first % second;
            first = temp;
        }
        return first;
    }
}